<%@page import="dto.CursoDTO"%>
<%@page import="modelo.Empresa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Curso"%>
<%@page import="java.time.LocalDate"%>
<%@page import="modelo.TutorPracticas"%>
<%@page import="modelo.TutorCurso"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

	//Variables extras vacias
	
	CursoDTO curso = null;
	Empresa empresa = null;
	Usuario usu_tutorP = null;
	
	//Lista de tutores de cursos/empresas/tutores de practicas y las ids de estos
	List<Long> l_id_cursos =  (List<Long>) request.getAttribute("l_id_cursos");
	List<CursoDTO> l_cursos =  (List<CursoDTO>) request.getAttribute("l_cursos");
	
	List<Long> l_id_empresas =  (List<Long>) request.getAttribute("l_id_empresas");
	List<Empresa> l_empresas =  (List<Empresa>) request.getAttribute("l_empresas");
	
	List<Long> l_id_tutorP =  (List<Long>) request.getAttribute("l_id_tutorP");
	List<Usuario> l_tutorP =  (List<Usuario>) request.getAttribute("l_tutorP");

	//Fila posiblemente creada recibida desde servlet
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	
	//Parámetros para rellenar el formulario en caso de edite
		// Variables generales
	String id_usuario = "", nombre = "", apellidos = "", dni = "", email = "", password = "", rol = "", telefono = "", ultimoAcceso = "", fechaCreacion = "";
	
		// Variables ALUMNO
	String id_alumno = "", fechaNacimiento = "", duracionPracticas = "", alumnoHorario = "", fechaInicio = "", fechaFin = "", id_curso = "", id_empresa_alumno = "", id_tutorP = "";
	
		// Variables TUTOR_CURSO
	String id_tutorC = "", especialidad = "";
	
		// Variables TUTOR_PRACTICAS
	String id_tutorP_tutor = "", cargo = "", tutorHorario = "", id_empresa_tutor = "";

if(usuario != null) {
    id_usuario = String.valueOf(usuario.getId_usuario());
    nombre = usuario.getNombre();
    //Si no tiene apellidos no indicar directamente nada ("")
    apellidos = (usuario.getApellidos() != null && !usuario.getApellidos().isEmpty())? usuario.getApellidos() : "";
    dni = usuario.getDni();
    email = usuario.getEmail();
    password = usuario.getPassword();
    rol = String.valueOf(usuario.getRol());
    telefono = usuario.getTelefono();
    ultimoAcceso = String.valueOf(usuario.getUltimoAcceso());
    fechaCreacion = String.valueOf(usuario.getFechaCreacion());

    if(usuario.getRol() == Rol.ALUMNO) {
        Alumno alumno = (Alumno) request.getAttribute("alumno");
        if(alumno != null) {
            id_alumno = String.valueOf(alumno.getId_alumno());
            fechaNacimiento = String.valueOf(alumno.getFechaNacimiento());
            duracionPracticas = String.valueOf(alumno.getDuracionPracticas());
            alumnoHorario = alumno.getHorario();
            fechaInicio = String.valueOf(alumno.getFechaInicio());
            fechaFin = String.valueOf(alumno.getFechaFin());
            id_curso = String.valueOf(alumno.getId_curso());
            id_empresa_alumno = String.valueOf(alumno.getId_empresa());
            id_tutorP = String.valueOf(alumno.getId_tutorP());
        }
        
        //Variables extras de alumno
        	//Obtetos individuales
        curso = (CursoDTO) request.getAttribute("curso");
        empresa = (Empresa) request.getAttribute("empresa");
        usu_tutorP = (Usuario) request.getAttribute("usu_tutorP");
        
        
    } else if(usuario.getRol() == Rol.TUTOR_CURSO) {
        TutorCurso tutorC = (TutorCurso) request.getAttribute("tutorC");
        if(tutorC != null) {
            id_tutorC = String.valueOf(tutorC.getId_tutorC());
            especialidad = tutorC.getEspecialidad();
        }
    } else if(usuario.getRol() == Rol.TUTOR_PRACTICAS) {
        TutorPracticas tutorP = (TutorPracticas) request.getAttribute("tutorP");
        if(tutorP != null) {
            id_tutorP_tutor = String.valueOf(tutorP.getId_tutorP());
            cargo = tutorP.getCargo();
            tutorHorario = tutorP.getHorario();
            id_empresa_tutor = String.valueOf(tutorP.getId_empresa());
            
            empresa = (Empresa) request.getAttribute("empresa");
        }
    }
}

//Para la restriccion de fechas

 LocalDate hoy = java.time.LocalDate.now();
 LocalDate edadMinima = hoy.minusYears(18);
 LocalDate maxFuturo = hoy.plusYears(2); // por ejemplo, hasta 2 años adelante
%>

<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
    <title>Gestión de Prácticas</title>
    <style>
        .rol-section { display: none; margin-top: 15px; border: 1px solid #ccc; padding: 10px; border-radius: 10px; }
    </style>
</head>

<body>
<h2><%= (usuario != null) ? "Editar Usuario" : "Registro Usuario" %></h2>
<a href="usuarios?action=list">⬅️ Volver al listado</a>
<form action="usuarios" method="post">
    <input type="hidden" name="id_usuario" value="<%= id_usuario %>">
    <input type="hidden" name="ultimoAcceso" value="<%= ultimoAcceso %>">
    <input type="hidden" name="fechaCreacion" value="<%= fechaCreacion %>">
    
    Nombre: <input type="text" name="nombre" value="<%= nombre %>" required><br>
    Apellidos: <input type="text" name="apellidos" value="<%= apellidos %>"><br>
    DNI: <input type="text" maxlength="9" pattern="^[0-9]{8}[A-Z]$" title="Debe tener 8 números seguidos de una letra mayúscula, por ejemplo 12345678Z" name="dni" value="<%= dni %>" required><br>
    Email: <input type="email" name="email" value="<%= email %>" required><br>
    Contraseña: <input type="password" name="password" value="<%= password %>" required><br>
    Teléfono: <input type="text" pattern="^[0-9]{9}$" title="El teléfono debe contener exactamente 9 dígitos numéricos" name="telefono" value="<%= telefono %>"><br>

    <% if (usuario == null) { %>
        Rol:
        <select id="rol" name="rol" onchange="mostrarCamposRol(this.value)" required>
            <option value="">-- Selecciona un rol --</option>
            <option value="ADMIN">Administrador</option>
            <option value="TUTOR_CURSO">Tutor de Curso</option>
            <option value="TUTOR_PRACTICAS">Tutor de Prácticas</option>
            <option value="ALUMNO">Alumno</option>
        </select><br>
    <% } else { %>
        Rol actual: <strong><%= rol %></strong>
		<input type="hidden" name="rol" value="<%= rol %>"><br>
    <% } %>

    <!-- CAMPOS SEGÚN ROL -->
    <div id="alumnoFields" class="rol-section">
        <input type="hidden" name="id_alumno" value="<%= id_alumno %>">
        <h3>Datos de Alumno</h3>
        Fecha de nacimiento: <input type="date"  min="1900-01-01" max="<%= edadMinima %>" name="fechaNacimiento" value="<%= fechaNacimiento %>"><br>
        Duración prácticas (horas): <input type="number" name="duracionPracticas" min="1" value="<%= duracionPracticas %>"><br>
        Horario: <input type="text" name="horarioAlumno" value="<%= alumnoHorario %>"><br>
        Fecha inicio: <input type="date" name="fechaInicio" value="<%= fechaInicio %>"><br>
        Fecha fin: <input type="date" name="fechaFin" value="<%= fechaFin %>"><br>
        
        Curso a realizar:
    
        <select id="id_curso" name="id_curso"  required>
            
            <%
            
            if(usuario != null && usuario.getRol() == Rol.ALUMNO){
            %> <option value="<%= id_curso %>"><%= curso.getNombre() + " (" + curso.getNombreTutor() + ((curso.getApellidosTutor() != null && !curso.getApellidosTutor().isEmpty()) ? " " + curso.getApellidosTutor() : "") + ")"%></option>
            <%} else { %>
            	
            	<option value="">-- Selecciona un curso --</option> <% }
            	
            for (int i = 0; i < l_cursos.size(); i++) {

                long id_cursoActual = l_id_cursos.get(i);

                if (usuario != null && curso != null && id_cursoActual == curso.getId_curso())
                    continue; // evita duplicar

            %>
            <option value="<%= id_cursoActual %>">
                <%= l_cursos.get(i).getNombre()  + " (" + l_cursos.get(i).getNombreTutor() + ((l_cursos.get(i).getApellidosTutor() != null && !l_cursos.get(i).getApellidosTutor().isEmpty()) ? " " + l_cursos.get(i).getApellidosTutor() : "") + ")"%>
            </option>
            <%
            }
            %>
        </select><br>
        
        
        Empresa asignada: 
        
        <select id="id_empresa_alumno" name="id_empresa_alumno"  required>
            
            
            <%
            
            if(usuario != null && usuario.getRol() == Rol.ALUMNO){
            %> <option value="<%= id_empresa_alumno %>"><%= empresa.getNombre() %></option>
            <%} else { %>
            	
            	<option value="">-- Selecciona una empresa --</option> <% }
            	
            for (int i = 0; i < l_empresas.size(); i++) {

                long id_empresaActual = l_id_empresas.get(i);

                if (usuario != null && empresa != null && id_empresaActual == empresa.getId_empresa())
                    continue; // evita duplicar

            %>
            <option value="<%= id_empresaActual %>">
                <%= l_empresas.get(i).getNombre()%>
            </option>
            <%
            }
            %>
        </select><br>
        
        
        Tutor de Prácticas asignado:
        
        <select id="id_tutorP" name="id_tutorP"  required>
            
            
            <%
            
            if(usuario != null && usuario.getRol() == Rol.ALUMNO){
            %> <option value="<%= id_tutorP %>"><%= usu_tutorP.getNombre() + ((usu_tutorP.getApellidos() != null && !usu_tutorP.getApellidos().isEmpty()) ? " " + usu_tutorP.getApellidos() : "") %></option>
            <%} else { %>
            	
            	<option value="">-- Selecciona un tutor de prácticas --</option> <% }
            	
            for (int i = 0; i < l_tutorP.size(); i++) {

                long id_tutorPActual = l_id_tutorP.get(i);

                if (usuario != null && usu_tutorP != null && id_tutorPActual == usu_tutorP.getId_usuario())
                    continue; // evita duplicar

            %>
            <option value="<%= id_tutorPActual %>">
            	<%= l_tutorP.get(i).getNombre() + ((l_tutorP.get(i).getApellidos() != null && !l_tutorP.get(i).getApellidos().isEmpty()) ? " " + l_tutorP.get(i).getApellidos() : "") %>
            </option>
            <%
            }
            %>
        </select><br>
    </div>

    <div id="tutorCursoFields" class="rol-section">
        <input type="hidden" name="id_tutorC" value="<%= id_tutorC %>">
        <h3>Datos de Tutor de Curso</h3>
        Especialidad: <input type="text" name="especialidad" value="<%= especialidad %>"><br>
    </div>

    <div id="tutorPracticasFields" class="rol-section">
        <input type="hidden" name="id_tutorP_tutor" value="<%= id_tutorP_tutor %>">
        <h3>Datos de Tutor de Prácticas</h3>
        Cargo: <input type="text" name="cargo" value="<%= cargo %>"><br>
        Horario: <input type="text" name="horarioTutor" value="<%= tutorHorario %>"><br>
        
    	Empresa: 
        
        <select id="id_empresa_tutor" name="id_empresa_tutor"  required>
            
            
            <%
            
            if(usuario != null && usuario.getRol() == Rol.TUTOR_PRACTICAS){
            %> <option value="<%= id_empresa_tutor %>"><%= empresa.getNombre() %></option>
            <%} else { %>
            	
            	<option value="">-- Selecciona una empresa --</option> <% }
            	
            for (int i = 0; i < l_empresas.size(); i++) {

                long id_empresaActual = l_id_empresas.get(i);

                if (usuario != null && empresa != null && id_empresaActual == empresa.getId_empresa())
                    continue; // evita duplicar

            %>
            <option value="<%= id_empresaActual %>">
                <%= l_empresas.get(i).getNombre()%>
            </option>
            <%
            }
            %>
        </select><br>
    </div>

    <br>
    <input type="submit" value="Guardar">
</form>

<script>

document.querySelector("form").addEventListener("submit", function (e) {
    const inicio = document.querySelector('input[name="fechaInicio"]').value;
    const fin = document.querySelector('input[name="fechaFin"]').value;

    if (inicio && fin && new Date(inicio) > new Date(fin)) {
        e.preventDefault(); // Evita el envío del formulario
        alert("La fecha de inicio no puede ser posterior a la fecha de fin.");
    }
});

function mostrarCamposRol(rol) {
    const alumnoFields = document.getElementById("alumnoFields");
    const tutorCursoFields = document.getElementById("tutorCursoFields");
    const tutorPracticasFields = document.getElementById("tutorPracticasFields");

    // Ocultar todos los divs y desactivar required de sus inputs/selects
    [alumnoFields, tutorCursoFields, tutorPracticasFields].forEach(div => {
        div.style.display = "none";
        div.querySelectorAll("input, select").forEach(el => el.required = false);
    });

    // Mostrar el div correspondiente y activar required de sus inputs/selects
    if (rol === "ALUMNO") {
        alumnoFields.style.display = "block";
        alumnoFields.querySelectorAll("input, select").forEach(el => el.required = true);
    } else if (rol === "TUTOR_CURSO") {
        tutorCursoFields.style.display = "block";
        tutorCursoFields.querySelectorAll("input, select").forEach(el => el.required = true);
    } else if (rol === "TUTOR_PRACTICAS") {
        tutorPracticasFields.style.display = "block";
        tutorPracticasFields.querySelectorAll("input, select").forEach(el => el.required = true);
    }
}

// Mostrar automáticamente si se está editando
<% if(usuario != null) { %>
    mostrarCamposRol("<%= rol %>");
<% } %>
</script>

</body>
</html>