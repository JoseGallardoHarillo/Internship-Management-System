<%@page import="modelo.CriterioEvaluacion"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	//Fila posiblemente creada recibida desde servlet
	CriterioEvaluacion criterio = (CriterioEvaluacion) request.getAttribute("criterio");

	//Parámetros para rellenar el formulario en caso de edite
    String id_criterio = (criterio != null) ? String.valueOf(criterio.getId_criterio()) : "";
    String nombre = (criterio != null) ? criterio.getNombre() : "";
    String descripcion = (criterio != null) ? criterio.getDescripcion() : "";
    String peso = (criterio != null) ? String.valueOf(criterio.getPeso()): "";
%>

<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2><%= (criterio != null) ? "Editar Criterio" : "Registro Criterio" %></h2>
<a href="criterios?action=list">⬅️ Volver al listado</a>

<form action="criterios" method="post">
    <input type="hidden" name="id_criterio" value="<%= id_criterio %>">
    
    Nombre: <input type="text" name="nombre" value="<%= nombre %>" required><br>
    Descripción: <input type="text" name="descripcion" value="<%= descripcion %>" required><br>
    Peso: <input type="number" name="peso" value="<%= peso %>"  step="0.01" min="0" max="999.99" required><br>

    <input type="submit" value="Guardar">
</form>
</body>
</html>