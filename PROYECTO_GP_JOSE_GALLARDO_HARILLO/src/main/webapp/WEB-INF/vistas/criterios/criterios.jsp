<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.CriterioEvaluacion"%>
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
<h2>📋 Criterios de Evaluación</h2>
<a href="index.jsp">🏠 Inicio</a>
<%if (usuario.getRol() == Rol.ADMIN) {%> | <a href="criterios?action=insert" class="btn btn-success">➕ Nuevo criterio</a> <%} %>

<table border="1">
<tr><th>ID</th><th>Nombre</th><th>Descripción</th><th>Peso</th><th>Capacidades de evaluación</th><th>Activo</th><%if (usuario.getRol() == Rol.ADMIN) {%><th>Acción</th><%}%></tr>
<%
	//Recoleccion de filas
    List<CriterioEvaluacion> criterios = (List<CriterioEvaluacion>) request.getAttribute("criterios");

if (criterios == null || criterios.isEmpty()) { 
	if((usuario.getRol() == Rol.ADMIN)) {%> <tr><td colspan="7" style="text-align:center;">🚫 No hay criterios disponibles de momento.</td></tr>
	<%} else {%>  <tr><td colspan="6" style="text-align:center;">🚫 No hay criterios disponibles de momento.</td></tr>
<% }
}

else {
for (CriterioEvaluacion ce : criterios) {
%>
<tr>
  <td><%= ce.getId_criterio() %></td>
  <td><%= ce.getNombre() %></td>
  <td><%= ce.getDescripcion() %></td>
  <td><%= ce.getPeso() %></td>
  <td><a href="criterios?action=view&id_criterio=<%= ce.getId_criterio() %>" class="btn btn-sm btn-info">👁️ Ver</a></td>
  <td><%= ce.getActivoSN() %></td>

	<% if((usuario.getRol() == Rol.ADMIN)) {%>
	
					<td>
					<% if(ce.isActivo()){ %>
					    <a href="criterios?action=edit&id_criterio=<%= ce.getId_criterio() %>">✏️ Editar</a> |
					    <a href="criterios?action=delete&id_criterio=<%= ce.getId_criterio() %>" 
					       onclick="return confirm('¿Dar de baja a este criterio de evaluación?')">🗑️ Borrar</a>
				<% } else { %>
					    <a href="criterios?action=activate&id_criterio=<%= ce.getId_criterio() %>" 
					       onclick="return confirm('¿Dar de alta a este criterio?')">✅ Reactivar</a>
					<% } %>
					</td>
					</tr>
  	<%}%>
<% }} %>
</table>
</body>
</html>
