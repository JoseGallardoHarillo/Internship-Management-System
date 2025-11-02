package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CapacidadDAO;
import modelo.CapacidadEvaluacion;

@WebServlet("/capacidades")
public class CapacidadEvaServlet extends HttpServlet {

	//Servicios DAO
	private CapacidadDAO capacidadDAO = new CapacidadDAO();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html; charset=UTF-8");

    	// Lectura de accion a realizar por el servlet
        String action = req.getParameter("action");
        
        if (action == null) action = "insert"; 

        try {
        	
        	//Variables extras para su posible uso
        	//-> id criterio para seguir identificando la pagina de visualizacion por el criterio de las capacidades mostradas en el formulario
        	long id_criterio = Long.parseLong(req.getParameter("id_criterio"));
        	req.setAttribute("id_criterio", id_criterio);
            
        	switch (action) {
            
            case "insert":
            	//Pasamos a la página del formulario
                req.getRequestDispatcher("/WEB-INF/vistas/capacidades/capacidad-form.jsp").forward(req, resp);
				break;

                case "edit":
                    
                	// Obtencion de la fila concreta a editar
                    long idEdit = Long.parseLong(req.getParameter("id_capacidad"));
                    CapacidadEvaluacion capacidad = capacidadDAO.getCapacidadById(idEdit);
                    req.setAttribute("capacidad", capacidad);
                    
                    // Pasamos a la página de formulario
                    req.getRequestDispatcher("/WEB-INF/vistas/capacidades/capacidad-form.jsp").forward(req, resp);
                    break;

                case "delete":
                	
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_capacidad"));
                    capacidadDAO.deleteCapacidad(idDelete);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("criterios?action=view&id_criterio=" + id_criterio);
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
        	
        	// Obtencion de la variable extra
        	long id_criterio = Long.parseLong(req.getParameter("id_criterio"));
        	
        	// Obtencion de los parametros enviados por el formulario
    		String id = req.getParameter("id_capacidad");
    		String nombre = req.getParameter("nombre");
    		String descripcion = req.getParameter("descripcion");
    		int puntuacionMaxima = Integer.parseInt(req.getParameter("puntuacionMaxima"));
            
    		//Si no existe la id se crea y si existe se edita
            if (id == null || id.isEmpty()) {
                capacidadDAO.addCapacidad(id_criterio, nombre, descripcion, puntuacionMaxima);
            } else {
            	capacidadDAO.updateCapacidad(Long.parseLong(id), id_criterio, nombre, descripcion, puntuacionMaxima);
            }
            
          //Pasamos a la página de criterio
          resp.sendRedirect("criterios?action=view&id_criterio=" + id_criterio);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}