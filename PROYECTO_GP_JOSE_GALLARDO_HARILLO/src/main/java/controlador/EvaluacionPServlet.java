package controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CapacidadDAO;
import dao.CriterioDAO;
import dao.EvaluacionTutorPracticasDAO;
import dao.UsuarioDAO;
import modelo.Alumno;
import modelo.CapacidadEvaluacion;
import modelo.CriterioEvaluacion;
import modelo.EvaluacionTutorP;
import modelo.Rol;
import modelo.Usuario;

@WebServlet("/evaluacionesP")
public class EvaluacionPServlet extends HttpServlet {

	//Servicios DAO
	private EvaluacionTutorPracticasDAO evaluacionPDAO = new EvaluacionTutorPracticasDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private CapacidadDAO capacidadDAO = new CapacidadDAO();
	private CriterioDAO criterioDAO = new CriterioDAO();
	
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
        	
        	//-->Obtencion de la lista de capacidades para su implementacion en el formulario de evaluaciones
			List<CapacidadEvaluacion> l_capacidad = capacidadDAO.getAllCapacidades();
			// Obtener las capacidades ya evaluadas por este alumno
			List<Long> capacidadesEvaluadas = evaluacionPDAO.getCapacidadesEvaluadasPorAlumno(id_alumno);

			// Filtrar capacidades disponibles (no evaluadas)
			List<CapacidadEvaluacion> capacidadesDisponibles = new ArrayList<>();
			for (CapacidadEvaluacion c : l_capacidad) {
				
				CriterioEvaluacion cr = criterioDAO.getCriterioById(c.getId_criterio());
				
			    if ((!capacidadesEvaluadas.contains(c.getId_capacidad())) && c.isActivo() && cr.isActivo()) {
			        capacidadesDisponibles.add(c);
			    }
			}

			// Usar esta lista filtrada en lugar de todas
			req.setAttribute("l_capacidad", capacidadesDisponibles);

			// Lista de IDs de capacidades
			List<Long> l_id_capacidad = new ArrayList<>();
			for (CapacidadEvaluacion capacidad : capacidadesDisponibles) {
			    l_id_capacidad.add(capacidad.getId_capacidad());
			}
			req.setAttribute("l_id_capacidad", l_id_capacidad);
            
        	switch (action) {
            
            case "insert":
            	
            	//Necesario para que el JSP tenga todos los valores ocultos
            	Alumno alumno = usuarioDAO.getAlumnoByUsuarioId(id_usuario);
            	long id_curso = alumno.getId_curso();
            	long id_tutorP = alumno.getId_tutorP();
            	req.setAttribute("id_alumno", id_alumno);
            	req.setAttribute("id_curso", id_curso);
            	req.setAttribute("id_tutorP", id_tutorP);
            	
            	//Pasamos a la página del formulario
            	req.getRequestDispatcher("/WEB-INF/vistas/evaluaciones/evaluacionP-form.jsp").forward(req, resp);
				break;

	            case "edit":
	                long idEdit = Long.parseLong(req.getParameter("id_evaluacion"));
	                EvaluacionTutorP evaluacionP = evaluacionPDAO.getEvaluacionTutorPById(idEdit);
	
	                req.setAttribute("evaluacionP", evaluacionP);
	
	                // Necesario para que el JSP tenga todos los valores ocultos
	                req.setAttribute("id_alumno", evaluacionP.getId_alumno());
	                Alumno alumnoEdit = usuarioDAO.getAlumnoByUsuarioId(id_usuario);
	                req.setAttribute("id_curso", alumnoEdit.getId_curso());
	                req.setAttribute("id_tutorP", alumnoEdit.getId_tutorP());
	                
	                //Capacidad
                    
                    CapacidadEvaluacion capacidad = capacidadDAO.getCapacidadById(evaluacionP.getId_capacidad());
                    req.setAttribute("capacidad", capacidad);
	
	                //Pasamos a la página del formulario
	                req.getRequestDispatcher("/WEB-INF/vistas/evaluaciones/evaluacionP-form.jsp").forward(req, resp);
	                break;

                case "delete":
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_evaluacion"));
                    evaluacionPDAO.deleteEvaluacionTutorP(idDelete);
                    
                    //Pasamos a la página del formulario
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
    		String id = req.getParameter("id_evaluacion");
            long id_tutorP = Long.parseLong(req.getParameter("id_tutorP"));
            long id_capacidad = Long.valueOf(req.getParameter("id_capacidad"));
            double puntuacion = Double.valueOf(req.getParameter("puntuacion"));
            String observaciones = req.getParameter("observaciones");
            Date fecha = Date.valueOf(req.getParameter("fecha"));
			Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
            
			//Si no existe la id se crea y si existe se edita
            if (id == null || id.isEmpty()) {
                evaluacionPDAO.addEvaluacionTutorP(id_alumno, id_tutorP, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion);
            } else {
            	evaluacionPDAO.updateEvaluacionTutorP(Long.parseLong(id), id_capacidad, puntuacion, observaciones, fecha);
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