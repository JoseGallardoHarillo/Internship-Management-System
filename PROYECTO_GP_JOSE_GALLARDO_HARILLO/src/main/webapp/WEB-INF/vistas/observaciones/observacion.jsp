<%@page import="modelo.Rol"%>
<%@page import="modelo.ObservacionDiaria"%>
<%@page import="modelo.CriterioEvaluacion"%>
<%@page import="dao.CriterioDAO"%>
<%@page import="modelo.CapacidadEvaluacion"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
	long id_usuario = (long) request.getAttribute("id_usuario");
	Rol rol = (Rol) request.getAttribute("rol");
    ObservacionDiaria observacion = (ObservacionDiaria) request.getAttribute("observacion");
    
    String id_alumno = (observacion != null)? String.valueOf(observacion.getId_alumno()): String.valueOf(request.getAttribute("id_alumno"));
	String id_curso = String.valueOf(request.getAttribute("id_curso"));
	String id_tutorP = String.valueOf(request.getAttribute("id_tutorP"));
%>
<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
  	<title>Gestion de Practicas</title>
</head>

<body>

<main class = "typical">
	
	<h2><%= "Detalles"%></h2>
	<br>
	<h3>Fecha: <%= observacion.getFecha() %></h3>
	<p><strong>Actividades: </strong> <%= observacion.getActividades() %></p>
	<p><strong>Explicaciones: </strong><%= observacion.getExplicaciones()%></p>
	<p><strong>Observaciones del alumno: </strong><%= observacion.getObservacionesAlumno()%></p>
	<p><strong>Observaciones del tutor: </strong><%= observacion.getObservacionesTutor()%></p>
	<p><strong>Horas realizadas: </strong><%= observacion.getHorasRealizadas()%></p>
	<br>
</main>

	<p>
		<a href="index.jsp">🏠 Volver a la página de inicio</a>	| <a href="usuarios?action=view&id_usuario=<%= id_usuario %>&rol=<%= rol.name() %>&perfil=<%= false %>">⬅️ Volver al listado</a>
	</p>

</body>
</html>