<%@page import="java.time.LocalDate"%>
<%@page import="modelo.TutorCurso"%>
<%@page import="dto.TutorCursoDTO"%>
<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Curso"%>
<%@page import="dto.CursoDTO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	
	//ADICIONAL: Lista de tutores de cursos y las ids de estos
	List<Long> l_id_tutoresC =  (List<Long>) request.getAttribute("l_id_tutoresC");
	List<Usuario> l_tutoresC =  (List<Usuario>) request.getAttribute("l_tutoresC");
	
	//Fila posiblemente creada recibida desde servlet
    Curso curso = (Curso) request.getAttribute("curso");

	//Parámetros para rellenar el formulario en caso de edite
    String id_curso = (curso != null) ? String.valueOf(curso.getId_curso()) : "";
    String nombre = (curso != null) ? curso.getNombre() : "";
    String descripcion = (curso != null) ? curso.getDescripcion() : "";
    String duracion = (curso != null) ? String.valueOf(curso.getDuracion()) : "";
    String fechaInicio = (curso != null) ? String.valueOf(curso.getFechaInicio()) : "";
    String fechaFin = (curso != null) ? String.valueOf(curso.getFechaFin()) : "";
    String fechaCreacion = (curso != null) ? String.valueOf(curso.getFechaCreacion()) : "";
    String id_tutor = (curso != null) ? String.valueOf(curso.getTutorC()) : "";
    
  //Para la restriccion de fechas

    LocalDate hoy = java.time.LocalDate.now();
    LocalDate maxFuturo = hoy.plusYears(2); // por ejemplo, hasta 2 años adelante
%>

<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2><%= (curso != null) ? "Editar Curso" : "Registro Curso" %></h2>
<a href="cursos?action=list">⬅️ Volver al listado</a>

<form action="cursos" method="post">
    <input type="hidden" name="id_curso" value="<%= id_curso %>">
    <input type="hidden" name="fechaCreacion" value="<%= fechaCreacion %>">
    
    Nombre: <input type="text" name="nombre" value="<%= nombre %>" required><br>
    Descripción: <input type="text" name="descripcion" value="<%= descripcion %>" required><br>
    Duración (horas): <input type="number" name="duracion" value="<%= duracion %>" required><br>
    Fecha Inicio: <input type="date" min="<%= hoy %>" max="<%= maxFuturo %>" name="fechaInicio" value="<%= fechaInicio %>" required><br>
    Fecha Fin: <input type="date" min="<%= hoy %>" max="<%= maxFuturo %>" name="fechaFin" value="<%= fechaFin %>" required><br>
    <!-- Id tutor asignado: <input type="number" name="id_tutorC" value="<%= id_tutor %>" required><br> -->

    Tutor asignado:
    
    
        <select id="id_tutorC" name="id_tutorC"  required>
            
            
            <%
            
            if(curso != null){
            	Usuario usu_tutorC = (Usuario) request.getAttribute("usu_tutorC");   
            %> <option value="<%= id_tutor %>"><%= usu_tutorC.getNombre() + ((usu_tutorC.getApellidos() != null && !usu_tutorC.getApellidos().isEmpty()) ? " " + usu_tutorC.getApellidos() : "")  %></option>
            <%} else { %>
            	
            	<option value="">-- Selecciona un tutor --</option> <% }
            	
            for (int i = 0; i < l_tutoresC.size(); i++) {

                long id_tutorActual = l_id_tutoresC.get(i);

                if (curso != null && id_tutorActual == curso.getTutorC())
                    continue; // evita duplicar el tutor asignado

            %>
            <option value="<%= id_tutorActual %>">
                <%= l_tutoresC.get(i).getNombre() + ((l_tutoresC.get(i).getApellidos() != null && !l_tutoresC.get(i).getApellidos().isEmpty()) ? " " + l_tutoresC.get(i).getApellidos() : "") %>
            </option>
            <%
            }
            %>
        </select><br>

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
</script>
</body>
</html>