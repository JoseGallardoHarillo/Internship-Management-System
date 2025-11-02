<%@page import="modelo.Incidencia"%>
<%@page import="modelo.Rol"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	//Variables para volver al perfil
	long id_usuario = (long) request.getAttribute("id_usuario");
	Rol rol = (Rol) request.getAttribute("rol");
	
	//Fila posiblemente creada recibida desde servlet
    Incidencia incidencia = (Incidencia) request.getAttribute("incidencia");
	
  	//Parámetros para rellenar el formulario en caso de edite
	String id_incidencia = (incidencia != null) ? String.valueOf(incidencia.getId_incidencia()) : "";	
	String id_alumno = (incidencia != null)? String.valueOf(incidencia.getId_alumno()): String.valueOf(request.getAttribute("id_alumno"));
	String id_curso = (incidencia != null)? String.valueOf(incidencia.getId_curso()) : String.valueOf(request.getAttribute("id_curso"));
	String id_tutorP = (incidencia != null)? String.valueOf(incidencia.getId_tutorP()) : String.valueOf(request.getAttribute("id_tutorP"));
	String fecha = (incidencia != null) ? String.valueOf(incidencia.getFecha()) : "";
    String tipo = (incidencia != null) ? String.valueOf(incidencia.getTipo()): "";
    String descripcion = (incidencia != null) ? incidencia.getDescripcion() : "";
    String estado = (incidencia != null) ? String.valueOf(incidencia.getEstado()) : "";
    String fechaCreacion = (incidencia != null) ? String.valueOf(incidencia.getFechaCreacion()) : "";
    String fechaResolucion = (incidencia != null) ? String.valueOf(incidencia.getFechaResolucion()) : "";
%>


<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2><%= (incidencia != null) ? "Editar Incidencia" : "Registro Incidencia" %></h2>
<a href="usuarios?action=view&id_usuario=<%= id_usuario %>&rol=<%= rol.name() %>&perfil=<%= false %>">⬅️ Volver al listado</a>

<form action="incidencias" method="post">
    <input type="hidden" name="id_incidencia" value="<%= id_incidencia %>">
    <input type="hidden" name="id_alumno" value="<%= id_alumno %>">
    <input type="hidden" name="id_curso" value="<%= id_curso %>">
    <input type="hidden" name="id_tutorP" value="<%= id_tutorP %>">
    <input type="hidden" name="fechaCreacion" value="<%= fechaCreacion %>">
    <input type="hidden" name="fechaResolucion" value="<%= fechaResolucion %>">
    <input type="hidden" name="fecha" value="<%= fecha %>">
    <input type="hidden" name="descripcion" value="<%= descripcion %>" >
    <input type="hidden" name="tipo" value="<%= tipo %>" >
    <input type="hidden" name="estado" value="<%= estado %>" >
    
    Resolución: <input type="text" name="resolucion" value="<%= ""  %>"><br>

    <input type="submit" value="Guardar">
</form>
</body>
</html>