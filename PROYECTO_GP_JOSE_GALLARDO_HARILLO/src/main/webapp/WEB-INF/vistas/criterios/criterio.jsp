<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.CriterioEvaluacion"%>
<%@page import="dao.CriterioDAO"%>
<%@page import="modelo.CapacidadEvaluacion"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
	//Con ello podremos modificar cosas en base al usuario que ha iniciado sesión
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");	

	//Recoleccion del criterio
    CriterioEvaluacion criterio = (CriterioEvaluacion) request.getAttribute("criterio");
%>
<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<main class = "typical">

	<h2><%= criterio.getNombre() + ": Capacidades de evaluacion"%></h2>
	
	<%
	List<CapacidadEvaluacion> l_capacidad = (List<CapacidadEvaluacion>) request.getAttribute("l_evaluacion");
	
	if (l_capacidad == null || l_capacidad.isEmpty()) { %>
	<br>
	🚫 No hay capacidades ligadas a este criterio de momento.
<% 
}

else {
	
    for (int i = 0; i <= l_capacidad.size() - 1; i++) {
    	CapacidadEvaluacion ce = l_capacidad.get(i);
	%>
	<br>
	<h3>Capacidad </strong><%= (i + 1) + ": " + ce.getNombre() %></h3>
	<p><strong>Descrición: </strong><%= ce.getDescripcion() %></p>
	<p><strong>Puntuación máxima: </strong><%= ce.getPuntuacionMaxima() %></p>
	<% if((usuario.getRol() == Rol.ADMIN)) {%>
	<a href="capacidades?action=edit&id_capacidad=<%= ce.getId_capacidad() %>&id_criterio=<%= criterio.getId_criterio() %>">✏️ Editar</a> |
	<a href="capacidades?action=delete&id_capacidad=<%= ce.getId_capacidad() %>&id_criterio=<%= criterio.getId_criterio() %>"
	   onclick="return confirm('¿Borrar esta capacidad de evaluación?')">🗑️ Borrar</a>
	<%}%>
	<% } }%>
	<% if((usuario.getRol() == Rol.ADMIN)) {%>
	<br>
	<br>
	<a href="capacidades?action=insert&id_criterio=<%= criterio.getId_criterio() %>" class="btn btn-success">➕ Nueva capacidad</a>
	<%}%>
</main>
	<p>
		<a href="index.jsp">🏠 Volver a la página de inicio</a>	| <a href="criterios?action=list">⬅️ Volver al listado</a>
	</p>
</body>
</html>