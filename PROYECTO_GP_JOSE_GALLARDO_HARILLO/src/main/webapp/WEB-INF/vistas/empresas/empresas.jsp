<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Empresa"%>
<%@ page import="java.util.*, modelo.Empresa" %>
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
	<h2>🏢 Empresas registradas</h2>
	<a href="index.jsp">🏠 Inicio</a>
	
	
	<%if (usuario.getRol() == Rol.ADMIN) {%> | <a href="empresas?action=insert" class="btn btn-success">➕ Nueva empresa</a> <%} %>
	
	<table border="1">
	<tr><th>ID</th><th>Nombre</th><th>CIF</th><th>Dirección</th><th>Teléfono</th><th>Email</th><th>Contacto</th><th>Sector</th><th>Activo</th><th>Fecha de creación</th><%if (usuario.getRol() == Rol.ADMIN) {%><th>Acción</th><%}%></tr>
	<%
		//Recoleccion de filas
	    List<Empresa> empresas = (List<Empresa>) request.getAttribute("empresas");
	
	if (empresas == null || empresas.isEmpty()) { 
		if((usuario.getRol() == Rol.ADMIN)) {%> <tr><td colspan="11" style="text-align:center;">🚫 No hay empresas disponibles de momento.</td></tr>
		<%} else {%> <tr><td colspan="10" style="text-align:center;">🚫 No hay empresas disponibles de momento.</td></tr>
	<%} 
	}
	
	else {
	
	    for (Empresa e : empresas) {
	%>
	<tr>
	  <td><%= e.getId_empresa() %></td>
	  <td><%= e.getNombre() %></td>
	  <td><%= e.getCif() %></td>
	  <td><%= e.getDireccion() %></td>
	  <td><%= e.getTelefono() %></td>
	  <td><%= e.getEmail() %></td>
	  <td><%= e.getPersonaContacto() %></td>
	  <td><%= e.getSector() %></td>
	  <td><%= e.getActivoSN() %></td>
	  <td><%= e.getFechaCreacion() %></td>
	
	<% if((usuario.getRol() == Rol.ADMIN)) {%>
	
		<td>
		<% if(e.isActivo()){ %>
		    <a href="empresas?action=edit&id_empresa=<%= e.getId_empresa() %>">✏️ Editar</a> |
	    <a href="empresas?action=delete&id_empresa=<%= e.getId_empresa() %>" 
	       onclick="return confirm('¿Dar de baja a esta empresa?')">🗑️ Borrar</a>
		<% } else { %>
		    <a href="empresas?action=activate&id_empresa=<%= e.getId_empresa() %>" 
		       onclick="return confirm('¿Dar de alta a esta empresa?')">✅ Reactivar</a>
		<% } %>
		</td>
		</tr>
		
	<%}%>
	
	<%} } %>
	</table>
</body>
</html>
