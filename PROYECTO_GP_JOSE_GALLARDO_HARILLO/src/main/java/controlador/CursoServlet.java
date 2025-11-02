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
import javax.servlet.http.HttpSession;

import dao.CursoDAO;
import dao.UsuarioDAO;
import dto.CursoDTO;
import modelo.Rol;
import modelo.TutorCurso;
import modelo.Usuario;

@WebServlet("/cursos")
public class CursoServlet extends HttpServlet {

	//Servicios DAO
	private CursoDAO cursoDAO = new CursoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html; charset=UTF-8");

    	// Lectura de accion a realizar por el servlet
        String action = req.getParameter("action");
        
        if (action == null) action = "list"; 

        try {
        	
        	//Variables extras
        		//-->Obtencion de la lista de tutores de cursos para su implementacion en el formulario
    		List<Usuario> l_tutoresC = usuarioDAO.getUsuariosByRol(Rol.TUTOR_CURSO);

    			// Filtrar tutores disponibles (activos)
	    	List<Usuario> tutoresCDisponibles = new ArrayList<>();
	    	for (Usuario tc : l_tutoresC) {
	    		if (tc.isActivo()) {
	    			tutoresCDisponibles.add(tc);
	    		}
	    	}
	
	    		// Usar esta lista filtrada en lugar de todas
	    	req.setAttribute("l_tutoresC", tutoresCDisponibles);
        		
        		//-->Obtencion de la lista de id de tutores de cursos para su implementacion en el formulario
        	List<Long> l_id_tutoresC = new ArrayList<>();
        	for(Usuario tutor : tutoresCDisponibles) {
        		long id_tutorActual = (usuarioDAO.getTutorCursoByUsuarioId(tutor.getId_usuario())).getId_tutorC();
        		l_id_tutoresC.add(id_tutorActual);
        	}
        	req.setAttribute("l_id_tutoresC", l_id_tutoresC);
           
        	switch (action) {
            
            	case "insert":
            	
            	//Pasamos a la página del formulario
                req.getRequestDispatcher("/WEB-INF/vistas/cursos/curso-form.jsp").forward(req, resp);
				break;
            	
                case "list":
                	
                	//Registro completo
                    List<CursoDTO> cursos = cursoDAO.getAllCursosDTO();
                    //req.setAttribute("cursos", cursos); EN ESTE CASO ENVIAREMOS UNA LISTA QUE FILTRE LOS CURSOS
                    
                    //EXTRA: Lista filtrada de cursos: Si es administrador los visualiza todos, si es el tutor de curso solo los suyos
                	
                	HttpSession session = req.getSession(false); // No crea una nueva si no existe

                	if (session == null || session.getAttribute("usuarioLogueado") == null) {
                	    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                	    return;
                	}
                	
                	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
                	TutorCurso tutorC_sesion = usuarioDAO.getTutorCursoByUsuarioId(usuario.getId_usuario());
                	
                	List<CursoDTO> cursosFiltrados = new ArrayList<CursoDTO>();
                	
                	for(CursoDTO c : cursos) {
                		if(usuario.getRol() == Rol.ADMIN){ 
                    		cursosFiltrados = cursos;
                    		break;
                    	}
                		
                		else if(c.getTutorC() == tutorC_sesion.getId_tutorC()){
                    		cursosFiltrados.add(c);
                		}
                	}
                	req.setAttribute("cursosFiltrados", cursosFiltrados);

                	//Pasamos a la página de muestreo
                    req.getRequestDispatcher("/WEB-INF/vistas/cursos/cursos.jsp").forward(req, resp);
                    break;

                case "edit": 
                	
                	// Obtencion de la fila concreta a editar
                    long idEdit = Long.parseLong(req.getParameter("id_curso"));
                    CursoDTO curso = cursoDAO.getCursoDTOById(idEdit);
                    req.setAttribute("curso", curso);
                    
                    //Usuario del tutor de curso
                    TutorCurso tutorC = usuarioDAO.getTutorCursoById(curso.getTutorC());
                    Usuario usu_tutorC = usuarioDAO.getUsuarioById(tutorC.getId_usuario());
                    req.setAttribute("usu_tutorC", usu_tutorC);
                    
                    //id del tutor curso actual
                    
                    long id_tutorActual = (usuarioDAO.getTutorCursoByUsuarioId(tutorC.getId_usuario())).getId_tutorC();
                    req.setAttribute("id_tutorActual", id_tutorActual);
                    
                    // Pasamos a la página de formulario
                    req.getRequestDispatcher("/WEB-INF/vistas/cursos/curso-form.jsp").forward(req, resp);
                    break;

                case "delete":
                	
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_curso"));
                    cursoDAO.deleteCurso(idDelete);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("cursos?action=list");
                    break;
                
                case "activate":
                	
                	// Obtencion de la fila concreta a reactivar
                    long idActivate = Long.parseLong(req.getParameter("id_curso"));
                    cursoDAO.activateCurso(idActivate);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("cursos?action=list");
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
            
    		String id = req.getParameter("id_curso");
            String nombre = req.getParameter("nombre");
            String descripcion = req.getParameter("descripcion");
            int duracion = Integer.parseInt(req.getParameter("duracion"));
            Date fechaInicio = java.sql.Date.valueOf(req.getParameter("fechaInicio"));
            Date fechaFin = java.sql.Date.valueOf(req.getParameter("fechaFin"));
			Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
			long id_tutor = Long.parseLong(req.getParameter("id_tutorC"));
			
			//Si no existe la id se crea y si existe se edita
            if (id == null || id.isEmpty()) {
                cursoDAO.addCurso(nombre, descripcion, duracion, fechaInicio, fechaFin, fechaCreacion, id_tutor);
            } else {
                cursoDAO.updateCurso(Long.parseLong(id), nombre,descripcion, duracion, fechaInicio, fechaFin, id_tutor);
            }

          //Pasamos a la página de muestreo
            resp.sendRedirect("cursos?action=list");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
