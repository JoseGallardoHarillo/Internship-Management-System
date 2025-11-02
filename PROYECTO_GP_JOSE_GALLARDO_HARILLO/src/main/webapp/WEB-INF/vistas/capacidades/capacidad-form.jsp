<%@page import="modelo.CapacidadEvaluacion"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	
	//identificador del criterio para volver al apartado de detalles del criterio de la capacidad
	long id_criterio = (long) request.getAttribute("id_criterio");
	
	//Fila posiblemente creada recibida desde servlet
    CapacidadEvaluacion capacidad = (CapacidadEvaluacion) request.getAttribute("capacidad");
	
    //Parámetros para rellenar el formulario en caso de edite
	String id_capacidad = (capacidad != null) ? String.valueOf(capacidad.getId_capacidad()) : "";
    String nombre = (capacidad != null) ? capacidad.getNombre() : "";
    String descripcion = (capacidad != null) ? capacidad.getDescripcion() : "";
    String puntuacionMaxima = (capacidad != null) ? String.valueOf(capacidad.getPuntuacionMaxima()): "";
%>

<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2><%= (capacidad != null) ? "Editar Capacidad" : "Registro Capacidad" %></h2>
<a href="criterios?action=view&id_criterio=<%= id_criterio %>">⬅️ Volver al listado</a>

<form action="capacidades" method="post">
    <input type="hidden" name="id_capacidad" value="<%= id_capacidad %>">
    <input type="hidden" name="id_criterio" value="<%= id_criterio %>">
    
    Nombre: <input type="text" name="nombre" value="<%= nombre %>" required><br>
    Descripción: <input type="text" name="descripcion" value="<%= descripcion %>" required><br>
    Puntuación máxima: <input type="number" name="puntuacionMaxima" step="1" value="<%= puntuacionMaxima %>" min="0" required><br>

    <input type="submit" value="Guardar">
</form>
</body>
</html>