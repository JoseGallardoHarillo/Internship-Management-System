package controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ObservacionDAO;
import dao.UsuarioDAO;
import modelo.Alumno;
import modelo.ObservacionDiaria;
import modelo.Rol;
import modelo.Usuario;

@WebServlet("/observaciones")
public class ObservacionServlet extends HttpServlet {
	
	//Servicios DAO
	private ObservacionDAO observacionDAO = new ObservacionDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html; charset=UTF-8");
    	
    	// Lectura de accion a realizar por el servlet
        String action = req.getParameter("action");
        
        if (action == null) action = "insert"; 

        try {
        	
        	//Variables extras
    			//id del usuario de la incidencia
        	
        	long id_alumno = Long.parseLong(req.getParameter("id_alumno"));
        	long id_usuario = usuarioDAO.getIdUsuarioByAlumnoId(id_alumno);
        	req.setAttribute("id_usuario", id_usuario);
        		//Rol del usuario de la incidencia
        	Usuario u = usuarioDAO.getUsuarioById(id_usuario);
        	Rol rol = u.getRol();
        	req.setAttribute("rol", rol);
            
        	// Lectura de accion a realizar por el servlet
        	switch (action) {
            
            case "insert":
            	
            	//Variables extras
            	Alumno alumno = usuarioDAO.getAlumnoByUsuarioId(id_usuario);
            	long id_curso = alumno.getId_curso();
            	long id_tutorP = alumno.getId_tutorP();
            	req.setAttribute("id_alumno", id_alumno);
            	req.setAttribute("id_curso", id_curso);
            	req.setAttribute("id_tutorP", id_tutorP);
            	
            	//Pasamos a la página del formulario
            	req.getRequestDispatcher("/WEB-INF/vistas/observaciones/observacion-form.jsp").forward(req, resp);
				break;

	            case "edit":
	            	
	            	// Obtencion de la fila concreta a editar
	                long idEdit = Long.parseLong(req.getParameter("id_observacion"));
	                ObservacionDiaria observacion = observacionDAO.getObservacionById(idEdit);
	                req.setAttribute("observacion", observacion);
	
	                //Necesario para que el JSP tenga todos los valores ocultos
	                req.setAttribute("id_alumno", observacion.getId_alumno());
	                Alumno alumnoEdit = usuarioDAO.getAlumnoByUsuarioId(id_usuario);
	                req.setAttribute("id_curso", alumnoEdit.getId_curso());
	                req.setAttribute("id_tutorP", alumnoEdit.getId_tutorP());
	                
	                //Pasamos a la página del formulario
	                req.getRequestDispatcher("/WEB-INF/vistas/observaciones/observacion-form.jsp").forward(req, resp);
	                break;
	            
	            case "viewDetailsObs": //Visualizacion de los detalles de una observacion
	            	
	            	// Obtencion de la fila concreta a visualizar
	                long idDetail = Long.parseLong(req.getParameter("id_observacion"));
	                ObservacionDiaria observacionD = observacionDAO.getObservacionById(idDetail);
	                req.setAttribute("observacion", observacionD);
	                
	                //Necesario para que el JSP tenga todos los valores ocultos
	                req.setAttribute("id_alumno", observacionD.getId_alumno());
	                Alumno alumnoDetail = usuarioDAO.getAlumnoByUsuarioId(id_usuario);
	                req.setAttribute("id_curso", alumnoDetail.getId_curso());
	                req.setAttribute("id_tutorP", alumnoDetail.getId_tutorP());
	                
	                //Pasamos a la página de visualizacion de la observacion
	                req.getRequestDispatcher("/WEB-INF/vistas/observaciones/observacion.jsp").forward(req, resp);
	                break;

                case "delete":
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_observacion"));
                    observacionDAO.deleteObservacion(idDelete);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("usuarios?action=view&id_usuario=" + id_usuario + "&rol=" + rol.name());
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
        	long id_alumno = Long.parseLong(req.getParameter("id_alumno"));
        	long id_usuario = usuarioDAO.getIdUsuarioByAlumnoId(id_alumno);
        	Usuario u = usuarioDAO.getUsuarioById(id_usuario);
        	Rol rol = u.getRol();
    		String id = req.getParameter("id_observacion");
    		long id_curso = Long.parseLong(req.getParameter("id_curso"));
            long id_tutorP = Long.parseLong(req.getParameter("id_tutorP"));
            Date fecha = Date.valueOf(req.getParameter("fecha"));
            String actividades = req.getParameter("actividades");
            String explicaciones = req.getParameter("explicaciones");
			String observacionesAlumno = req.getParameter("observacionesAlumno");
			String observacionesTutor = req.getParameter("observacionesTutor");
			int horasRealizadas = Integer.valueOf(req.getParameter("horasRealizadas"));
			Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
            
			//Si no existe la id se crea y si existe se edita
            if (id == null || id.isEmpty()) {
                observacionDAO.addObservacion(id_alumno, fecha, actividades, explicaciones, observacionesAlumno, observacionesTutor, horasRealizadas, fechaCreacion);
            } else {
            	observacionDAO.updateObservacion(Long.parseLong(id), fecha, actividades, explicaciones, observacionesAlumno, observacionesTutor, horasRealizadas);
            }
            
            //Pasamos a la página de visualizacion
            resp.sendRedirect("usuarios?action=view&id_usuario=" + id_usuario
                    + "&rol=" + rol.name()
                    + "&id_alumno=" + id_alumno);
            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}