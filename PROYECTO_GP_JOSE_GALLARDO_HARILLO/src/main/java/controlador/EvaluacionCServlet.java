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
import dao.CursoDAO;
import dao.EvaluacionTutorCursoDAO;
import dao.UsuarioDAO;
import modelo.Alumno;
import modelo.CapacidadEvaluacion;
import modelo.CriterioEvaluacion;
import modelo.EvaluacionTutorC;
import modelo.Rol;
import modelo.Usuario;

@WebServlet("/evaluacionesC")
public class EvaluacionCServlet extends HttpServlet {

	//Servicios DAO
	private EvaluacionTutorCursoDAO evaluacionCDAO = new EvaluacionTutorCursoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private CursoDAO cursoDAO = new CursoDAO();
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
        	
        		//Necesario para que el JSP tenga todos los valores ocultos
        	Alumno alumno = usuarioDAO.getAlumnoByUsuarioId(id_usuario);
        	long id_curso = alumno.getId_curso();
        	long id_tutorP = alumno.getId_tutorP();
        	long id_tutorC = cursoDAO.getCursoDTOById(id_curso).getTutorC();
        	req.setAttribute("id_alumno", id_alumno);
        	req.setAttribute("id_curso", id_curso);
        	req.setAttribute("id_tutorP", id_tutorP);
        	req.setAttribute("id_tutorC", id_tutorC);
        	
        	//-->Obtencion de la lista de capacidades para su implementacion en el formulario de evaluaciones
			List<CapacidadEvaluacion> l_capacidad = capacidadDAO.getAllCapacidades();
			// Obtener las capacidades ya evaluadas por este alumno
			List<Long> capacidadesEvaluadas = evaluacionCDAO.getCapacidadesEvaluadasPorAlumno(id_alumno);

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
            	//Pasamos a la página del formulario
            	req.getRequestDispatcher("/WEB-INF/vistas/evaluaciones/evaluacionC-form.jsp").forward(req, resp);
				break;

	            case "edit":
	            	
	            	//Obtencion de la fila concreta a editar
	                long idEdit = Long.parseLong(req.getParameter("id_evaluacionTC"));
	                EvaluacionTutorC evaluacionC = evaluacionCDAO.getEvaluacionTutorCById(idEdit);
	
	                req.setAttribute("evaluacionC", evaluacionC);
	
	                //Necesario para que el JSP tenga todos los valores ocultos
	                req.setAttribute("id_alumno", evaluacionC.getId_alumno());
	
	                Alumno alumnoEdit = usuarioDAO.getAlumnoByUsuarioId(id_usuario);
	                req.setAttribute("id_curso", alumnoEdit.getId_curso());
	                req.setAttribute("id_tutorP", alumnoEdit.getId_tutorP());
	                
	                //Capacidad
                    
                    CapacidadEvaluacion capacidad = capacidadDAO.getCapacidadById(evaluacionC.getId_capacidad());
                    req.setAttribute("capacidad", capacidad);
	                
	                //Pasamos a la página del formulario
	                req.getRequestDispatcher("/WEB-INF/vistas/evaluaciones/evaluacionC-form.jsp").forward(req, resp);
	                break;

                case "delete":
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_evaluacionTC"));
                    evaluacionCDAO.deleteEvaluacionTutorC(idDelete);
                    
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
    		String id = req.getParameter("id_evaluacionTC");
    		System.out.println(id);
            long id_tutorC = Long.parseLong(req.getParameter("id_tutorC"));
            System.out.println("id tutorC: " + id_tutorC);
            long id_capacidad = Long.valueOf(req.getParameter("id_capacidad"));
            double puntuacion = Double.valueOf(req.getParameter("puntuacion"));
            String observaciones = req.getParameter("observaciones");
            Date fecha = Date.valueOf(req.getParameter("fecha"));
			Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
			
			//Si no existe la id se crea y si existe se edita
            if (id == null || id.isEmpty()) {
                evaluacionCDAO.addEvaluacionTutorC(id_alumno, id_tutorC, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion);
            } else {
            	evaluacionCDAO.updateEvaluacionTutorC(Long.parseLong(id), id_capacidad, puntuacion, observaciones, fecha);
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