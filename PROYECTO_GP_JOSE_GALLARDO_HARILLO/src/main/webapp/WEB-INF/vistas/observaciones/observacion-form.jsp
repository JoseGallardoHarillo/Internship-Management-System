<%@page import="modelo.ObservacionDiaria"%>
<%@page import="modelo.Incidencia"%>
<%@page import="modelo.Rol"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

	//Variables para volver al perfil
	long id_usuario = (long) request.getAttribute("id_usuario");
	Rol rol = (Rol) request.getAttribute("rol");
	
	//Fila posiblemente creada recibida desde servlet
    ObservacionDiaria observacion = (ObservacionDiaria) request.getAttribute("observacion");
	
    //Parámetros para rellenar el formulario en caso de edite
	String id_observacion = (observacion != null) ? String.valueOf(observacion.getId_observacion()) : "";
	String id_alumno = (observacion != null)? String.valueOf(observacion.getId_alumno()): String.valueOf(request.getAttribute("id_alumno"));
	String id_curso = String.valueOf(request.getAttribute("id_curso"));
	String id_tutorP = String.valueOf(request.getAttribute("id_tutorP"));
	String fecha = (observacion != null) ? String.valueOf(observacion.getFecha()) : "";
    String actividades = (observacion != null) ? observacion.getActividades(): "";
    String explicaciones = (observacion != null) ? observacion.getExplicaciones() : "";
    String observacionesAlumno = (observacion != null) ? observacion.getObservacionesAlumno() : "";
    String observacionesTutor = (observacion != null) ? observacion.getObservacionesTutor() : "";
    String horasRealizadas = (observacion != null) ? String.valueOf(observacion.getHorasRealizadas()) : "";
    String fechaCreacion = (observacion != null) ? String.valueOf(observacion.getFechaCreacion()) : "";
%>

<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2><%= (observacion != null) ? "Editar Observación" : "Registro Observación" %></h2>
<a href="usuarios?action=view&id_usuario=<%= id_usuario %>&rol=<%= rol.name() %>&perfil=<%= false %>">⬅️ Volver al listado</a>

<form action="observaciones" method="post">
    <input type="hidden" name="id_observacion" value="<%= id_observacion %>">
    <input type="hidden" name="id_alumno" value="<%= id_alumno %>">
    <input type="hidden" name="id_curso" value="<%= id_curso %>">
    <input type="hidden" name="id_tutorP" value="<%= id_tutorP %>">
    <input type="hidden" name="fechaCreacion" value="<%= fechaCreacion %>">
    
    Fecha: <input type="date" name="fecha" value="<%= fecha %>" required><br>
    Actividades: <input type="text" name="actividades" value="<%= actividades %>" required><br>
    Explicaciones: <input type="text" name="explicaciones" value="<%= explicaciones %>" required><br>
    Observaciones del alumno: <input type="text" name="observacionesAlumno" value="<%= observacionesAlumno %>" required><br>
    Observaciones del tutor: <input type="text" name="observacionesTutor" value="<%= observacionesTutor %>" required><br>
	Horas realizadas: <input type="number" name="horasRealizadas" step="1" value="<%= horasRealizadas %>" required><br>
    
    <input type="submit" value="Guardar">
</form>
</body>
</html>