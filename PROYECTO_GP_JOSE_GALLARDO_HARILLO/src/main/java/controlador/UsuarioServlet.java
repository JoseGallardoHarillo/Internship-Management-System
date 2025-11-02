package controlador;

import dao.CapacidadDAO;
import dao.CriterioDAO;
import dao.CursoDAO;
import dao.EmpresaDAO;
import dao.EvaluacionTutorCursoDAO;
import dao.EvaluacionTutorPracticasDAO;
import dao.IncidenciaDAO;
import dao.ObservacionDAO;
import dao.UsuarioDAO;
import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.TutorCursoDTO;
import dto.TutorPracticasDTO;
import modelo.Alumno;
import modelo.CapacidadEvaluacion;
import modelo.CriterioEvaluacion;
import modelo.Empresa;
import modelo.EvaluacionTutorC;
import modelo.EvaluacionTutorP;
import modelo.Incidencia;
import modelo.ObservacionDiaria;
import modelo.Rol;
import modelo.TutorCurso;
import modelo.TutorPracticas;
import modelo.Usuario;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

	//Servicios DAO
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private CursoDAO cursoDAO = new CursoDAO();
	private IncidenciaDAO incidenciaDAO = new IncidenciaDAO();
	private ObservacionDAO observacionDAO = new ObservacionDAO();
	private EvaluacionTutorPracticasDAO evaluacionPDAO = new EvaluacionTutorPracticasDAO();
	private EvaluacionTutorCursoDAO evaluacionCDAO = new EvaluacionTutorCursoDAO();
	private CapacidadDAO capacidadDAO = new CapacidadDAO();
	private CriterioDAO criterioDAO = new CriterioDAO();
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
        	
        	//Variables extras
    			//-->Obtencion de la lista de cursos para su implementacion en el formulario
			List<CursoDTO> l_cursos = cursoDAO.getAllCursosDTO();
	    
	    		// Filtrar cursos disponibles (activos)
	    	List<CursoDTO> cursosDisponibles = new ArrayList<>();
	    	for (CursoDTO c : l_cursos) {
	    		if (c.isActivo()) {
	    				        cursosDisponibles.add(c);
	    		}
	    	}

	    		// Usar esta lista filtrada en lugar de todas
	    	req.setAttribute("l_cursos", cursosDisponibles);
	    		
	    		//-->Obtencion de la lista de id de cursos para su implementacion en el formulario
	    	List<Long> l_id_cursos = new ArrayList<>();
	    	for(CursoDTO curso : cursosDisponibles) {
	    		long id_cursoActual = curso.getId_curso();
	    		l_id_cursos.add(id_cursoActual);
	    	}
	    	req.setAttribute("l_id_cursos", l_id_cursos);
	    	
			//-->Obtencion de la lista de empresas para su implementacion en el formulario
			List<Empresa> l_empresas = empresaDAO.getAllEmpresas();

			// Filtrar empresas disponibles (activos)
	    	List<Empresa> empresasDisponibles = new ArrayList<>();
	    	for (Empresa e : l_empresas) {
	    		if (e.isActivo()) {
	    				        empresasDisponibles.add(e);
	    		}
	    	}

	    		// Usar esta lista filtrada en lugar de todas
	    	req.setAttribute("l_empresas", empresasDisponibles);
	    		
	    		//-->Obtencion de la lista de id de empresas para su implementacion en el formulario
	    	List<Long> l_id_empresas = new ArrayList<>();
	    	for(Empresa empresa : empresasDisponibles) {
	    		long id_empresaActual = empresa.getId_empresa();
	    		l_id_empresas.add(id_empresaActual);
	    	}
	    	req.setAttribute("l_id_empresas", l_id_empresas);
        	
	    		//-->Obtencion de la lista de tutores de practicas para su implementacion en el formulario
			List<Usuario> l_tutorP = usuarioDAO.getUsuariosByRol(Rol.TUTOR_PRACTICAS);

				// Filtrar tutores de practicas disponibles (activos)
	    	List<Usuario> tutorPDisponibles = new ArrayList<>();
	    	for (Usuario tp : l_tutorP) {
	    		if (tp.isActivo()) {
	    			tutorPDisponibles.add(tp);
	    		}
	    	}

	    		// Usar esta lista filtrada en lugar de todas
	    	req.setAttribute("l_tutorP", tutorPDisponibles);
	    		
	    	//-->Obtencion de la lista de id de tutores de practicas para su implementacion en el formulario
	    	List<Long> l_id_tutorP = new ArrayList<>();
	    	for(Usuario tutorP : tutorPDisponibles) {
	    		long id_tutorPActual = (usuarioDAO.getTutorPracticasByUsuarioId(tutorP.getId_usuario())).getId_tutorP();
	    		l_id_tutorP.add(id_tutorPActual);
	    	}
	    	req.setAttribute("l_id_tutorP", l_id_tutorP);
        	
        	switch (action) {
            
            case "insert":
            	
            	//Pasamos a la página del formulario
                req.getRequestDispatcher("/WEB-INF/vistas/usuarios/usuario-form.jsp").forward(req, resp);
				break;
            	
            case "list":
            	               
            	//VARIABLE FILTRO
            	String rolFiltro = req.getParameter("rolFiltro");
            	
            	//Registro completo
                List<Usuario> usuarios;
                if (rolFiltro != null && !rolFiltro.isEmpty() && !rolFiltro.equals("TODOS")) {
                    Rol rolEnum = Rol.valueOf(rolFiltro);
                    usuarios = usuarioDAO.getUsuariosByRol(rolEnum);
                } else {
                    usuarios = usuarioDAO.getAllUsuarios();
                }
                //req.setAttribute("usuarios", usuarios); IMPORTANTE: NO LO HACEMOS PARA ENVIAR MEJOR UNA LISTA FILTRADA
                req.setAttribute("rolFiltro", rolFiltro); // para mantener el select
                
            	//Variables extras
        			//--> lista de usuarios filtrados
                
                HttpSession session = req.getSession(false); // No crea una nueva si no existe

            	if (session == null || session.getAttribute("usuarioLogueado") == null) {
            	    resp.sendRedirect(req.getContextPath() + "/login.jsp");
            	    return;
            	}
            	
            	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
                
            	List<Usuario> l_usuario_filtrado = new ArrayList<Usuario>();
            	
            	if(usuario.getRol() == Rol.ADMIN){ 
            		l_usuario_filtrado = usuarios;
            	}
            	
            	else {
            		for (Usuario u : usuarios) {
            			if(u.getRol()==Rol.ALUMNO) {
            				Alumno a = usuarioDAO.getAlumnoByUsuarioId(u.getId_usuario());
            				
            				if(usuario.getRol()== Rol.TUTOR_CURSO){
                    			
                    			long id_curso = a.getId_curso();
                        		long id_tutorC = cursoDAO.getCursoDTOById(id_curso).getTutorC();
                        		
                        		long id_tutorC_sesion = usuarioDAO.getTutorCursoByUsuarioId(usuario.getId_usuario()).getId_tutorC();
                        		
                        		if(id_tutorC == id_tutorC_sesion){
                        			l_usuario_filtrado.add(u);
                        		}
                        		
            				}
            				
            				else{
                				long id_tutorP_sesion = usuarioDAO.getTutorPracticasByUsuarioId(usuario.getId_usuario()).getId_tutorP();
                				        		
                				if(a.getId_tutorP() == id_tutorP_sesion){
                					l_usuario_filtrado.add(u);
                				}
                    		}
                		}
                	}
            	}
            	
            	req.setAttribute("l_usuario_filtrado", l_usuario_filtrado);
                
                //Pasamos a la página de muestreo
                req.getRequestDispatcher("/WEB-INF/vistas/usuarios/usuarios.jsp").forward(req, resp);
                break;

                case "edit":
                    
                	// Obtencion de la fila concreta a editar
                    long idEdit = Long.parseLong(req.getParameter("id_usuario"));
                    Usuario usuarioEdit = usuarioDAO.getUsuarioById(idEdit);
                    req.setAttribute("usuario", usuarioEdit);
                    
                    //-------------ZONA ROL-------------
                    if(usuarioEdit.getRol() == Rol.ALUMNO) {
                    	Alumno alumno = usuarioDAO.getAlumnoByUsuarioId(idEdit);
                    	req.setAttribute("alumno", alumno);
                    	
                    	//Curso, Empresa y Tutor de practicas del alumno
                        
                        CursoDTO curso = cursoDAO.getCursoDTOById(alumno.getId_curso());
                        Empresa empresa = empresaDAO.getEmpresaById(alumno.getId_empresa());
                        
                        TutorPracticas tutorP = usuarioDAO.getTutorPracticasById(alumno.getId_tutorP());
                        Usuario usu_tutorP = usuarioDAO.getUsuarioById(tutorP.getId_usuario());
                        req.setAttribute("curso", curso);
                        req.setAttribute("empresa", empresa);
                        req.setAttribute("usu_tutorP", usu_tutorP);
                    }
                    
                    else if(usuarioEdit.getRol() == Rol.TUTOR_CURSO) {
                    	TutorCurso tutorC = usuarioDAO.getTutorCursoByUsuarioId(idEdit);
                    	req.setAttribute("tutorC", tutorC);
                    }
                    
                    if(usuarioEdit.getRol() == Rol.TUTOR_PRACTICAS) {
                    	TutorPracticas tutorP = usuarioDAO.getTutorPracticasByUsuarioId(idEdit);
                    	
                    	//Variable de empresa
                    	Empresa empresa = empresaDAO.getEmpresaById(tutorP.getId_empresa());
                    	req.setAttribute("empresa", empresa);
                    	
                    	req.setAttribute("tutorP", tutorP);
                    }

                    // Pasamos a la página de formulario
                    req.getRequestDispatcher("/WEB-INF/vistas/usuarios/usuario-form.jsp").forward(req, resp);
                    break;

                case "delete":
                	// Obtencion de la fila concreta a eliminar
                    long idDelete = Long.parseLong(req.getParameter("id_usuario"));
                    usuarioDAO.deleteUsuario(idDelete);

                    //Pasamos a la página de muestreo
                    resp.sendRedirect("usuarios?action=list");
                    break;

                case "activate":
                	
                	// Obtencion de la fila concreta a reactivar
                    long idActivate = Long.parseLong(req.getParameter("id_usuario"));
                    usuarioDAO.activateUsuario(idActivate);
                    
                    //Pasamos a la página de muestreo
                    resp.sendRedirect("usuarios?action=list");
                    break;
                
                case "view": //Visualizacion de los detalles pertenecientes al usuario
                	
                	//VARIABLES EXTRAS:
                		//--> Variable necesaria para comprobar si se entra desde perfil o si se entra al registro
                	boolean perfil = Boolean.valueOf(req.getParameter("perfil"));
                	req.setAttribute("perfil", perfil);
                	
                		//-->Lista de criterios
                	List<CriterioEvaluacion> l_criterio = criterioDAO.getAllCriterios();
                	req.setAttribute("l_criterio", l_criterio);
                	
                		//-->Para saber a que jsp de los disponibles por rol pasar
                	Rol rolView = Rol.valueOf(req.getParameter("rol"));
                	
                	//Obtencion de la fila concreta a visualizar
                	long idView = Long.parseLong(req.getParameter("id_usuario"));
                	
                	if(rolView == Rol.ALUMNO) {
                		AlumnoDTO a_dto = usuarioDAO.getAlumnoDTOByUsuarioId(idView);
                		req.setAttribute("dto", a_dto);
                		
                		//VARIABLES EXTRAS:
                			//--> Alumno de la vista del usuario
                		Alumno a = usuarioDAO.getAlumnoByUsuarioId(idView);
                		req.setAttribute("alumno", a);
                		
                			//--> Incidencias del alumno
                		List<Incidencia> l_incidencias = incidenciaDAO.getIncidenciasByAlumno(usuarioDAO.getAlumnoByUsuarioId(idView).getId_alumno());
                    	req.setAttribute("l_incidencias", l_incidencias);
                    	
                    		//--> Observaciones del alumno
                    	List<ObservacionDiaria> l_observaciones = observacionDAO.getObservacionesByAlumno(usuarioDAO.getAlumnoByUsuarioId(idView).getId_alumno());
                    	req.setAttribute("l_observaciones", l_observaciones);
                    	
                    		//--> Evaluaciones del tutor de practicas del alumno
                    	List<EvaluacionTutorP> l_EvtutorP = evaluacionPDAO.getEvaluacionesTutorPyAlumno(usuarioDAO.getAlumnoByUsuarioId(idView).getId_alumno());
                    	req.setAttribute("l_tutorP", l_EvtutorP);
                    	
                    		//--> Capacidades evaluadas por las evaluaciones del tutor de practicas  
                    	List<CapacidadEvaluacion> l_capacidadP = new ArrayList<CapacidadEvaluacion>();
                    	for (EvaluacionTutorP evp : l_EvtutorP) { //Recorremos la lista del tutorC pero podria ser la de tutorP tambien
                    		long id_c = evp.getId_capacidad();
							l_capacidadP.add(capacidadDAO.getCapacidadById(id_c));
						}
                    	req.setAttribute("l_capacidadP", l_capacidadP);
                    		
                    		//--> Evaluaciones del tutor de cursos del alumno
                    	List<EvaluacionTutorC> l_EvtutorC = evaluacionCDAO.getEvaluacionesTutorCByAlumno(usuarioDAO.getAlumnoByUsuarioId(idView).getId_alumno());
                    	req.setAttribute("l_tutorC", l_EvtutorC);
                    		
                    		//--> Capacidades evaluadas por las evaluaciones del tutor de curso  
                    	List<CapacidadEvaluacion> l_capacidadC = new ArrayList<CapacidadEvaluacion>();
                    	for (EvaluacionTutorC evc : l_EvtutorC) { //Recorremos la lista del tutorC pero podria ser la de tutorP tambien
                    		long id_c = evc.getId_capacidad();
							l_capacidadC.add(capacidadDAO.getCapacidadById(id_c));
						}
                    	req.setAttribute("l_capacidadC", l_capacidadC);
                    	
                    	//Pasamos a la página de muestreo del alumno
                		req.getRequestDispatcher("/WEB-INF/vistas/usuarios/usuarioAlumno.jsp").forward(req, resp);
                	}
                	
                	else if(rolView == Rol.TUTOR_CURSO) {
                		TutorCursoDTO tc_dto = usuarioDAO.getTutorCursoDTOByUsuarioId(idView);
                		req.setAttribute("dto", tc_dto);
                		req.getRequestDispatcher("/WEB-INF/vistas/usuarios/usuarioTutorC.jsp").forward(req, resp);
                	}
                	
                	else if(rolView == Rol.TUTOR_PRACTICAS) {
                		TutorPracticasDTO tp_dto = usuarioDAO.getTutorPracticasDTOByUsuarioId(idView);
                		req.setAttribute("dto", tp_dto);
                		req.getRequestDispatcher("/WEB-INF/vistas/usuarios/usuarioTutorP.jsp").forward(req, resp);
                	}
                	
                	else {
                		Usuario dto = usuarioDAO.getUsuarioById(idView);
                		req.setAttribute("dto", dto);
                		req.getRequestDispatcher("/WEB-INF/vistas/usuarios/usuarioAdmin.jsp").forward(req, resp);
                	}
                	
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
            String idStr = req.getParameter("id_usuario");
            String nombre = req.getParameter("nombre");
            String apellidos = req.getParameter("apellidos");
            String dni = req.getParameter("dni");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String rolStr = req.getParameter("rol");
            String telefono = req.getParameter("telefono");

            Rol rol = Rol.valueOf(rolStr);

            Timestamp tsUltimoAcceso = new Timestamp(System.currentTimeMillis());
            Timestamp tsFechaCreacion = new Timestamp(System.currentTimeMillis());

            // Solo parseamos si el campo no está vacío
            long id_usuario = 0;
            if (idStr != null && !idStr.isEmpty()) {
                id_usuario = Long.parseLong(idStr);
            }

            //Si no existe la id se crea y si existe se edita
            if (id_usuario == 0) {
            	id_usuario = usuarioDAO.addUsuarioAndGetId(nombre, apellidos, dni, email, password, rol, telefono, tsUltimoAcceso, tsFechaCreacion);
            } else {
                usuarioDAO.updateUsuario(id_usuario, nombre, apellidos, dni, email, password, telefono);
            }

            // Campos específicos por rol
            switch (rol) {
                case ALUMNO:
                    String id_alumnoStr = req.getParameter("id_alumno");
                    long id_alumno = 0;
                    if (id_alumnoStr != null && !id_alumnoStr.isEmpty()) {
                        id_alumno = Long.parseLong(id_alumnoStr);
                    }

                    Date fechaNacimiento = Date.valueOf(req.getParameter("fechaNacimiento"));
                    int duracionPracticas = Integer.parseInt(req.getParameter("duracionPracticas"));
                    String alumnoHorario = req.getParameter("horarioAlumno");
                    Date fechaInicio = Date.valueOf(req.getParameter("fechaInicio"));
                    Date fechaFin = Date.valueOf(req.getParameter("fechaFin"));
                    long id_curso = Long.parseLong(req.getParameter("id_curso"));
                    long id_empresa_alumno = Long.parseLong(req.getParameter("id_empresa_alumno"));
                    long id_tutorP = Long.parseLong(req.getParameter("id_tutorP"));

                    if (id_alumno == 0) {
                        usuarioDAO.addAlumno(fechaNacimiento, duracionPracticas, alumnoHorario,
                                fechaInicio, fechaFin, id_curso, id_empresa_alumno, id_tutorP, id_usuario);
                    } else {
                        usuarioDAO.updateAlumno(id_alumno, fechaNacimiento, duracionPracticas, alumnoHorario,
                                fechaInicio, fechaFin, id_curso, id_empresa_alumno, id_tutorP);
                    }
                    break;

                case TUTOR_CURSO:
                    String id_tutorCStr = req.getParameter("id_tutorC");
                    long id_tutorC = 0;
                    if (id_tutorCStr != null && !id_tutorCStr.isEmpty()) {
                        id_tutorC = Long.parseLong(id_tutorCStr);
                    }

                    String especialidad = req.getParameter("especialidad");

                    if (id_tutorC == 0) {
                        usuarioDAO.addTutorC(especialidad, id_usuario);
                    } else {
                        usuarioDAO.updateTutorC(id_tutorC, especialidad);
                    }
                    break;

                case TUTOR_PRACTICAS:
                    String id_tutorPStr = req.getParameter("id_tutorP_tutor");
                    long id_tutorP_tutor = 0;
                    if (id_tutorPStr != null && !id_tutorPStr.isEmpty()) {
                        id_tutorP_tutor = Long.parseLong(id_tutorPStr);
                    }

                    String cargo = req.getParameter("cargo");
                    String tutorHorario = req.getParameter("horarioTutor");
                    long id_empresa_tutor = Long.parseLong(req.getParameter("id_empresa_tutor"));

                    if (id_tutorP_tutor == 0) {
                        usuarioDAO.addTutorP(cargo, tutorHorario, id_empresa_tutor, id_usuario);
                    } else {
                        usuarioDAO.updateTutorPracticas(id_tutorP_tutor, cargo, tutorHorario, id_empresa_tutor);
                    }
                    break;
            }

            resp.sendRedirect("usuarios?action=list");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
