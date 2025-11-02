package controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EmpresaDAO;
import modelo.Empresa;

@WebServlet("/empresas")
public class EmpresaServlet extends HttpServlet {
	
	//Servicios DAO
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html; charset=UTF-8");

        // Lectura de accion a realizar por el servlet
        String action = req.getParameter("action");
        
        if (action == null) action = "list"; 

        try {
            
        	switch (action) {
            
            case "insert":
            	
            	//Pasamos a la página del formulario
                req.getRequestDispatcher("/WEB-INF/vistas/empresas/empresa-form.jsp").forward(req, resp);
				break;
            	
                case "list":
                	
                	//Registro completo
                    List<Empresa> empresas = empresaDAO.getAllEmpresas();
                    req.setAttribute("empresas", empresas);
                    
                    //Pasamos a la página de muestreo
                    req.getRequestDispatcher("/WEB-INF/vistas/empresas/empresas.jsp").forward(req, resp);
                    break;

                case "edit":
                    
                	// Obtencion de la fila concreta a editar
                    long idEdit = Long.parseLong(req.getParameter("id_empresa"));
                    Empresa empresa = empresaDAO.getEmpresaById(idEdit);
                    req.setAttribute("empresa", empresa);

                    // Pasamos a la página de formulario
                    req.getRequestDispatcher("/WEB-INF/vistas/empresas/empresa-form.jsp").forward(req, resp);
                    break;

                case "delete":
                	
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_empresa"));
                    empresaDAO.deleteEmpresa(idDelete);

                    //Pasamos a la página de muestreo
                    resp.sendRedirect("empresas?action=list");
                    break;
                
                case "activate":
                	
                	// Obtencion de la fila concreta a reactivar
                    long idActivate = Long.parseLong(req.getParameter("id_empresa"));
                    empresaDAO.activateEmpresa(idActivate);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("empresas?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html; charset=UTF-8");

        try {
        	
            // Obtencion de los parametros enviados por el formulario
            
    		String id = req.getParameter("id_empresa");
    		String nombre = req.getParameter("nombre");
    		String cif = req.getParameter("cif");
    		String direccion = req.getParameter("direccion");
    		String telefono = req.getParameter("telefono");
            String email = req.getParameter("email");
            String personaContacto = req.getParameter("personaContacto");
            String sector = req.getParameter("sector");
            Timestamp tsFechaCreacion = new Timestamp(System.currentTimeMillis()); // Siempre ahora
            
            // Verificar si existe una empresa con el mismo CIF
            Empresa existente = empresaDAO.findByCIF(cif);
            
            //Si no existe la id se crea y si existe se edita
            if (id == null || id.isEmpty()) {
            	
            	if (existente != null) {
                    // CIF duplicado, mostrar mensaje de error en formulario
                    req.setAttribute("error", "Ya existe una empresa con ese CIF.");
                    req.getRequestDispatcher("/WEB-INF/vistas/empresas/empresa-form.jsp").forward(req, resp);
                    return;
                }
            	
                empresaDAO.addEmpresa(nombre, cif, direccion, telefono, email, personaContacto, sector, tsFechaCreacion);
            } else {
            	
            	if (existente != null && existente.getId_empresa() != Long.parseLong(id)) {
                    // CIF duplicado en otra empresa
                    req.setAttribute("error", "El CIF ingresado pertenece a otra empresa.");
                    req.getRequestDispatcher("/WEB-INF/vistas/empresas/empresa-form.jsp").forward(req, resp);
                    return;
                }
            	
                empresaDAO.updateEmpresa(Long.parseLong(id), nombre,cif, direccion, telefono, email, personaContacto, sector);
            }

            //Pasamos a la página de muestreo
            resp.sendRedirect("empresas?action=list");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}