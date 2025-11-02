package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CapacidadDAO;
import dao.CriterioDAO;
import modelo.CapacidadEvaluacion;
import modelo.CriterioEvaluacion;

@WebServlet("/criterios")
public class CriterioEvaServlet extends HttpServlet {

	//Servicios DAO
	private CriterioDAO criterioDAO = new CriterioDAO();
	private CapacidadDAO capacidadDAO = new CapacidadDAO();
	
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
                req.getRequestDispatcher("/WEB-INF/vistas/criterios/criterio-form.jsp").forward(req, resp);				
				break;
            	
                case "list":
                	
                	//Registro completo
                    List<CriterioEvaluacion> criterios = criterioDAO.getAllCriterios();
                    req.setAttribute("criterios", criterios);
                    
                    //Pasamos a la página de muestreo
                    req.getRequestDispatcher("/WEB-INF/vistas/criterios/criterios.jsp").forward(req, resp);
                    break;

                case "edit":
                    
                	// Obtencion de la fila concreta a editar
                    long idEdit = Long.parseLong(req.getParameter("id_criterio"));
                    CriterioEvaluacion criterio = criterioDAO.getCriterioById(idEdit);
                    req.setAttribute("criterio", criterio);
                    
                    // Pasamos a la página de formulario
                    req.getRequestDispatcher("/WEB-INF/vistas/criterios/criterio-form.jsp").forward(req, resp);
                    break;
                    
                case "view": //Visualizacion de las capacidades pertenecientes al criterio
                	
                	//Obtencion de la fila concreta a visualizar
                	long idView = Long.parseLong(req.getParameter("id_criterio"));
                	CriterioEvaluacion criterioView = criterioDAO.getCriterioById(idView);
                	
                	//Obtencion de la lista de capacidades pertenecientes al criterio
                	List<CapacidadEvaluacion> l_evaluacion = capacidadDAO.getCapacidadesByCriterio(idView);
                	
                	//Envio tanto del criterio como de sus capacidades
                	req.setAttribute("l_evaluacion", l_evaluacion);
                	req.setAttribute("criterio", criterioView);
                	
                	//Pasamos a la pagina de visualizacion
            		req.getRequestDispatcher("/WEB-INF/vistas/criterios/criterio.jsp").forward(req, resp);
                    break;
                

                case "delete":
                	
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_criterio"));
                    criterioDAO.deleteCriterio(idDelete);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("criterios?action=list");
                    break;
                
                case "activate":
                	
                	// Obtencion de la fila concreta a reactivar
                    long idActivate = Long.parseLong(req.getParameter("id_criterio"));
                    criterioDAO.activateCriterio(idActivate);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("criterios?action=list");
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
    		String id = req.getParameter("id_criterio");
    		String nombre = req.getParameter("nombre");
    		String descripcion = req.getParameter("descripcion");
    		double peso = Double.parseDouble(req.getParameter("peso"));
            
    		//Si no existe la id se crea y si existe se edita
            if (id == null || id.isEmpty()) {
                criterioDAO.addCriterio(nombre, descripcion, peso);
            } else {
            	criterioDAO.updateCriterio(Long.parseLong(id), nombre, descripcion, peso);
            }
            
          //Pasamos a la página de muestreo
            resp.sendRedirect("criterios?action=list");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}