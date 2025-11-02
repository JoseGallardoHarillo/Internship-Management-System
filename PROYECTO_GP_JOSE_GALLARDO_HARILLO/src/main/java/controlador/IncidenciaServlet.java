package controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.IncidenciaDAO;
import dao.UsuarioDAO;
import modelo.Alumno;
import modelo.Estado;
import modelo.Incidencia;
import modelo.Rol;
import modelo.Tipo;
import modelo.Usuario;

@WebServlet("/incidencias")
public class IncidenciaServlet extends HttpServlet {

	//Servicios DAO
	private IncidenciaDAO incidenciaDAO = new IncidenciaDAO();
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
	            	req.getRequestDispatcher("/WEB-INF/vistas/incidencias/incidencia-form.jsp").forward(req, resp);
					break;

                case "edit":
                    
                	// Obtencion de la fila concreta a editar
                    long idEdit = Long.parseLong(req.getParameter("id_incidencia"));
                    Incidencia incidencia = incidenciaDAO.getIncidenciaById(idEdit);
                    req.setAttribute("incidencia", incidencia);
                    
                    //Pasamos a la página del formulario
                    req.getRequestDispatcher("/WEB-INF/vistas/incidencias/incidencia-form.jsp").forward(req, resp);
                    break;

                case "resolve":
                	// Obtencion de la fila concreta a eliminar
                	long idResolve = Long.parseLong(req.getParameter("id_incidencia"));
                    
                    Incidencia incidenciaResolve = incidenciaDAO.getIncidenciaById(idResolve);
                    req.setAttribute("incidencia", incidenciaResolve);
                    
                    //Pasamos al formulario de resolucion
                    req.getRequestDispatcher("/WEB-INF/vistas/incidencias/incidencia-form_resuelta.jsp").forward(req, resp);
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
    		String id = req.getParameter("id_incidencia");
    		long id_curso = Long.parseLong(req.getParameter("id_curso"));
            long id_tutorP = Long.parseLong(req.getParameter("id_tutorP"));
            Date fecha = Date.valueOf(req.getParameter("fecha"));
            Tipo tipo = Tipo.valueOf(req.getParameter("tipo"));
            String descripcion = req.getParameter("descripcion");
			String resolucion = req.getParameter("resolucion");
			Estado estado = Estado.valueOf(req.getParameter("estado"));
			Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
			Timestamp fechaResolucion = null;
            
			if(resolucion != null && !resolucion.isEmpty()) {
				incidenciaDAO.resolveIncidencia(Long.parseLong(id), resolucion);
			}
			
			//Si no existe la id se crea y si existe se edita
			else if (id == null || id.isEmpty()) {
                incidenciaDAO.addIncidencia(id_alumno, id_curso, id_tutorP, fecha, tipo, descripcion, resolucion, estado, fechaCreacion, fechaResolucion);
            } else {
            	incidenciaDAO.updateIncidencia(Long.parseLong(id), id_alumno, id_curso, id_tutorP, fecha, tipo, descripcion, resolucion, estado);
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