<%@page import="modelo.Alumno"%>
<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@ page import="java.util.*, modelo.Usuario" %>
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
<h2>👤 Usuarios registrados</h2>
<a href="index.jsp">🏠 Inicio</a>
<%if (usuario.getRol() == Rol.ADMIN) {%> | <a href="usuarios?action=insert" class="btn btn-success">➕ Nuevo Usuario</a> <%} %>

<%if(usuario.getRol() == Rol.ADMIN){%>
<form action="usuarios" method="get" style="margin-top:15px; margin-bottom:15px;">
    <input type="hidden" name="action" value="list">
    <label for="rolFiltro">Filtrar por rol:</label>
    <select name="rolFiltro" id="rolFiltro" onchange="this.form.submit()">
        <option value="TODOS" <%= "TODOS".equals(request.getAttribute("rolFiltro")) ? "selected" : "" %>>Todos</option>
        <option value="ADMIN" <%= "ADMIN".equals(request.getAttribute("rolFiltro")) ? "selected" : "" %>>Admin</option>
        <option value="TUTOR_CURSO" <%= "TUTOR_CURSO".equals(request.getAttribute("rolFiltro")) ? "selected" : "" %>>Tutor de Curso</option>
        <option value="TUTOR_PRACTICAS" <%= "TUTOR_PRACTICAS".equals(request.getAttribute("rolFiltro")) ? "selected" : "" %>>Tutor de Prácticas</option>
        <option value="ALUMNO" <%= "ALUMNO".equals(request.getAttribute("rolFiltro")) ? "selected" : "" %>>Alumno</option>
    </select>
</form>
<% }%>
<table border="1">
<tr><th>ID</th><th>Nombre</th><th>Apellidos</th><th>Rol</th><th>Activo</th><th>Ultimo Acceso</th><th>Fecha de creación</th><th>Detalles</th><%if (usuario.getRol() == Rol.ADMIN) {%><th>Acción</th><%}%></tr>
<%
//Recoleccion de filas
List<Usuario> usuarios = (List<Usuario>) request.getAttribute("l_usuario_filtrado");

if (usuarios == null || usuarios.isEmpty()) {
	
    if((usuario.getRol() == Rol.ADMIN)) {%> <tr><td colspan="9" style="text-align:center;">🚫 No hay usuarios disponibles de momento.</td></tr>
    
    <%} else {%> <tr><td colspan="8" style="text-align:center;">🚫 No hay usuarios disponibles de momento.</td></tr>
    
<%}
}
else {
		
	for(Usuario u: usuarios){
    %>
    		<tr>
    		  <td><%= u.getId_usuario() %></td>
    		  <td><%= u.getNombre() %></td>
    		  <td><%= (u.getApellidos() != null && !u.getApellidos().isEmpty())? u.getApellidos(): "*Sin apellidos indicados*" %></td>
    		  <td><%= u.getRol() %></td>
    		  <td><%= u.getActivoSN() %></td>
    		  <td><%= u.getUltimoAcceso() %></td>
    		  <td><%= u.getFechaCreacion() %></td>
    		  
    		  <td>
    				<a href="usuarios?action=view&id_usuario=<%= u.getId_usuario() %>&rol=<%= u.getRol() %>&perfil=<%= false %>" class="btn btn-sm btn-info">👁️ Ver</a>
    		  </td>

	<% if((usuario.getRol() == Rol.ADMIN)) { %>

		<td>
		<% if(u.isActivo()){ %>
		    <a href="usuarios?action=edit&id_usuario=<%= u.getId_usuario() %>">✏️ Editar</a> |
		    <a href="usuarios?action=delete&id_usuario=<%= u.getId_usuario() %>" 
		       onclick="return confirm('¿Dar de baja a este usuario?')">🗑️ Borrar</a>
		<% } else { %>
		    <a href="usuarios?action=activate&id_usuario=<%= u.getId_usuario() %>" 
		       onclick="return confirm('¿Dar de alta a este usuario?')">✅ Reactivar</a>
		<% } %>
		</td>
		</tr>
		
	<%} }
    	}%>
</table>
</body>
</html>
