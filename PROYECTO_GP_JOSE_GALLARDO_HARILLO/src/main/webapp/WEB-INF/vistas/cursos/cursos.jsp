<%@page import="modelo.Curso"%>
<%@page import="modelo.TutorCurso"%>
<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@page import="dto.CursoDTO"%>
<%@ page import="java.util.*, dto.CursoDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
	//Con ello podremos modificar cosas en base al usuario que ha iniciado sesión
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
%>
<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2>🎓 Cursos registrados</h2>
<a href="index.jsp">🏠 Inicio</a>
<%if (usuario.getRol() == Rol.ADMIN) {%> | <a href="cursos?action=insert" class="btn btn-success">➕ Nuevo curso</a> <%} %>

<table border="1">
<tr><th>ID</th><th>Nombre</th><th>Descripción</th><th>Duración</th><th>Fecha Inicio</th><th>Fecha Fin</th><th>Tutor asignado</th><th>Activo</th><th>Fecha Creacion</th><%if (usuario.getRol() == Rol.ADMIN) {%><th>Acción</th><%}%></tr>
<%
	//lista filtro de cursos
	
	List<CursoDTO> cursosFiltrados = (List<CursoDTO>) request.getAttribute("cursosFiltrados");
	
	if (cursosFiltrados == null || cursosFiltrados.isEmpty()) {
		if((usuario.getRol() == Rol.ADMIN)) {%><tr><td colspan="10" style="text-align:center;">🚫 No hay cursos disponibles de momento.</td></tr>
		<%} else {%><tr><td colspan="9" style="text-align:center;">🚫 No hay cursos disponibles de momento.</td></tr>
	<% }
	}
	
	else {
	
    	for (CursoDTO c : cursosFiltrados) {%>
    		<tr>
			  <td><%= c.getId_curso() %></td>
			  <td><%= c.getNombre() %></td>
			  <td><%= c.getDescripcion() %></td>
			  <td><%= c.getDuracion() + " h" %></td>
			  <td><%= c.getFechaInicio() %></td>
			  <td><%= c.getFechaFin() %></td>
			  <td><%= c.getNombreTutor() + ((c.getApellidosTutor() != null && !c.getApellidosTutor().isEmpty()) ? " " + c.getApellidosTutor() : "") %></td>
			  <td><%= c.getActivoSN() %></td>
			  <td><%= c.getFechaCreacion() %></td>
			
			<% if((usuario.getRol() == Rol.ADMIN)) {%>
				<% if((usuario.getRol() == Rol.ADMIN)) {%>
				
					<td>
					<% if(c.isActivo()){ %>
					    <a href="cursos?action=edit&id_curso=<%= c.getId_curso() %>">✏️ Editar</a> |
			    <a href="cursos?action=delete&id_curso=<%= c.getId_curso() %>" 
			       onclick="return confirm('¿Dar de baja a este curso?')">🗑️ Borrar</a>
					<% } else { %>
					    <a href="cursos?action=activate&id_curso=<%= c.getId_curso() %>" 
					       onclick="return confirm('¿Dar de alta a este curso?')">✅ Reactivar</a>
					<% } %>
					</td>
					</tr>
					
				<%}%>			
			 	<%}%>
<% }}%>
</table>
</body>
</html>
