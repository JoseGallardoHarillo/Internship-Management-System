<%@page import="java.util.List"%>
<%@page import="modelo.CapacidadEvaluacion"%>
<%@page import="modelo.EvaluacionTutorC"%>
<%@page import="modelo.Incidencia"%>
<%@page import="modelo.Rol"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	
	//Variable extra vacia
	
	CapacidadEvaluacion capacidad = null;	

	//Lista de capacidades y las ids de estas
	
	List<Long> l_id_capacidad =  (List<Long>) request.getAttribute("l_id_capacidad");
	List<CapacidadEvaluacion> l_capacidad =  (List<CapacidadEvaluacion>) request.getAttribute("l_capacidad");
	
	//Variables para volver al perfil
	long id_usuario = (long) request.getAttribute("id_usuario");
	Rol rol = (Rol) request.getAttribute("rol");
	
	//Fila posiblemente creada recibida desde servlet
    EvaluacionTutorC evaluacionC = (EvaluacionTutorC) request.getAttribute("evaluacionC");
	
  	//Parámetros para rellenar el formulario en caso de edite (+ variables extras)
  	if(evaluacionC != null) capacidad = (CapacidadEvaluacion) request.getAttribute("capacidad");
	String id_evaluacionC = (evaluacionC != null) ? String.valueOf(evaluacionC.getId_evaluacionC()) : "";
	String id_alumno = (evaluacionC != null)? String.valueOf(evaluacionC.getId_alumno()): String.valueOf(request.getAttribute("id_alumno"));
	String id_curso = String.valueOf(request.getAttribute("id_curso"));
	String id_tutorP = String.valueOf(request.getAttribute("id_tutorP"));
	String id_tutorC = String.valueOf(request.getAttribute("id_tutorC"));
	String id_capacidad = (evaluacionC != null) ? String.valueOf(evaluacionC.getId_capacidad()) : "";
    String puntuacion = (evaluacionC != null) ? String.valueOf(evaluacionC.getPuntuacion()): "";
    String observaciones = (evaluacionC != null) ? evaluacionC.getObservaciones() : "";
    String fecha = (evaluacionC != null) ? String.valueOf(evaluacionC.getFecha()) : "";
    String fechaCreacion = (evaluacionC != null) ? String.valueOf(evaluacionC.getFechaCreacion()) : "";
%>


<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2><%= (evaluacionC != null) ? "Editar Evaluación" : "Registro Evaluación Curso" %></h2>
<a href="usuarios?action=view&id_usuario=<%= id_usuario %>&rol=<%= rol.name() %>&perfil=<%= false %>">⬅️ Volver al listado</a>

<form action="evaluacionesC" method="post">
    <input type="hidden" name="id_evaluacionTC" value="<%= id_evaluacionC %>">
    <input type="hidden" name="id_alumno" value="<%= id_alumno %>">
    <input type="hidden" name="id_curso" value="<%= id_curso %>">
    <input type="hidden" name="id_tutorP" value="<%= id_tutorP %>">
    <input type="hidden" name="id_tutorC" value="<%= id_tutorC %>">
    <input type="hidden" name="fechaCreacion" value="<%= fechaCreacion %>">
    
        Capacidad a evaluar:
    
        <select id="id_capacidad" name="id_capacidad"  required>
            
            <%
            
            if(evaluacionC != null){
            %> <option value="<%= id_capacidad %>"><%= capacidad.getNombre() %></option>
            <%} else { %>
            	
            	<option value="">-- Selecciona una capacidad --</option> <% }
            	
            for (int i = 0; i < l_capacidad.size(); i++) {

                long id_capacidadActual = l_id_capacidad.get(i);

                if (evaluacionC != null && capacidad != null && id_capacidadActual == capacidad.getId_capacidad())
                    continue; // evita duplicar

            %>
            <option value="<%= id_capacidadActual %>">
                <%= l_capacidad.get(i).getNombre()%>
            </option>
            <%
            }
            %>
        </select><br>
    
    
    
    
    
    
    
    
    Puntuación: <input type="number" name="puntuacion" value="<%= puntuacion %>"  step="0.01" min="0" max="999.99" required><br>
    Observaciones: <input type="text" name="observaciones" value="<%= observaciones %>" required><br>
    Fecha: <input type="date" name="fecha" value="<%= fecha %>" required><br>
    
    <input type="submit" value="Guardar">
</form>
</body>
</html>