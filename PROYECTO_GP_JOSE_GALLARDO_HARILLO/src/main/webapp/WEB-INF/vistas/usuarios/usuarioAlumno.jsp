<%@page import="modelo.Estado"%>
<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.CriterioEvaluacion"%>
<%@page import="modelo.EvaluacionTutorP"%>
<%@page import="modelo.CapacidadEvaluacion"%>
<%@page import="modelo.EvaluacionTutorC"%>
<%@page import="modelo.ObservacionDiaria"%>
<%@page import="modelo.Incidencia"%>
<%@page import="java.util.List"%>
<%@ page import="dto.AlumnoDTO, dto.AlumnoDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
	//Con ello podremos modificar cosas en base al usuario que ha iniciado sesión
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	//Variables de gestión del perfil
    AlumnoDTO dto = (AlumnoDTO) request.getAttribute("dto");
	Alumno a = (Alumno) request.getAttribute("alumno");
	boolean perfil = (Boolean) request.getAttribute("perfil");
	long id_alumno = a.getId_alumno();
	long id_curso = a.getId_curso();
	long id_tutorP = a.getId_tutorP();
%>
<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<main class = "typical">
	<h2><%= dto.getNombre() + " " + ((dto.getApellidos() != null && !dto.getApellidos().isEmpty()) ? dto.getApellidos() : "") %></h2>
	<p><strong>DNI: </strong><%= dto.getDni() %></p>
	<p><strong>Email: </strong><%= dto.getEmail() %></p>
	<p><strong>Teléfono: </strong><%= dto.getTelefono() %></p>
	<p><strong>Fecha de nacimiento: </strong><%= dto.getFechaNacimiento() %></p>
	<% if (perfil) { %>
	    <p>
	        <strong>Contraseña:</strong>
	        <input type="password" id="passwordField" value="<%= dto.getPassword() %>" readonly>
	        <button type="button" id="togglePassword" style="background:none; border:none; cursor:pointer;">👁️</button>
	    </p>
	
	    <script>
	    document.getElementById("togglePassword").addEventListener("click", function() {
	        const input = document.getElementById("passwordField");
	        const isHidden = input.type === "password";
	        input.type = isHidden ? "text" : "password";
	        this.textContent = isHidden ? "🙈" : "👁️";
	    });
	    </script>
	<% } %>
	<p><strong>Duración de las prácticas: </strong><%= dto.getDuracionPracticas() + " horas"%></p>
	<p><strong>Horario: </strong><%= dto.getHorario() %></p>
	<p><strong>Fecha de inicio: </strong><%= dto.getFechaInicio() %></p>
	<p><strong>Fecha de fin: </strong><%= dto.getFechaFin() %></p>
	<p><strong>Curso inscrito: </strong><%= dto.getNombreCurso() %></p>
	<p><strong>Empresa: </strong><%= dto.getNombreEmpresa() %></p>
	<p><strong>Tutor de prácticas asignado: </strong><%= dto.getNombreTutorP() + " " + ((dto.getApellidosTutorP() != null && !dto.getApellidosTutorP().isEmpty()) ? " " + dto.getApellidosTutorP() : "")  %></p>
	<br>
	<h3>Incidentes</h3>
	<table border="1">
	    <tr>
	        <th>ID</th><th>Fecha</th><th>Tipo</th><th>Descripción</th>
	        <th>Resolución</th><th>Estado</th><th>Fecha de creación</th>
	        <th>Fecha de resolución</th><%if (usuario.getRol() == Rol.ADMIN) {%><th>Acción</th><%}%>
	    </tr>
	
	<%
	List<Incidencia> incidencias = (List<Incidencia>) request.getAttribute("l_incidencias");
	if (incidencias == null || incidencias.isEmpty()) {
	 if((usuario.getRol() == Rol.ADMIN)) {%><tr><td colspan="9" style="text-align:center;">🚫 No hay incidencias disponibles de momento.</td></tr>
		<%} else {%><tr><td colspan="8" style="text-align:center;">🚫 No hay incidencias disponibles de momento.</td>
	<%
	}} else {
	    for (Incidencia i : incidencias) {
	%>
	    <tr>
	        <td><%= i.getId_incidencia() %></td>
	        <td><%= i.getFecha() %></td>
	        <td><%= i.getTipo() %></td>
	        <td><%= i.getDescripcion() %></td>
	        <td><%= (i.getResolucion() != null && !i.getResolucion().isEmpty()) ? " " + i.getResolucion() : "Pendiente de resolución" %></td>
	        <td><%= i.getEstado() %></td>
	        <td><%= i.getFechaCreacion() %></td>
	        <td><%= (i.getFechaResolucion() != null) ? " " + i.getFechaResolucion() : "Pendiente de resolución" %></td>
	        
	        
	        <% if(usuario.getRol() == Rol.ADMIN) {%>
	        <td>
	        <a href="incidencias?action=edit&id_incidencia=<%= i.getId_incidencia() %>&id_alumno=<%= i.getId_alumno() %>&id_curso=<%= i.getId_curso() %>&id_tutorP=<%= i.getId_tutorP() %>">✏️ Editar</a>
	        <%if(i.getEstado() != Estado.RESUELTA){%>
	       	 |<a href="incidencias?action=resolve&id_incidencia=<%= i.getId_incidencia() %>&id_alumno=<%= i.getId_alumno() %>&id_curso=<%= i.getId_curso() %>&id_tutorP=<%= i.getId_tutorP() %>"
	               onclick="return confirm('¿Dar incidente como resuelto?')">✅ Resolver</a>
	       <%}%>
	        </td>
	        <%}%>
	       
	    </tr>
	<%
	    }
	}
	%>
	</table>
	<% if((usuario.getRol() == Rol.ADMIN)) {%>
	<br>
	<a href="incidencias?action=insert&id_alumno=<%= id_alumno %>&id_curso=<%= id_curso %>&id_tutorP=<%= id_tutorP %>" class="btn btn-success">➕ Nueva incidencia</a>
	<%} %>
	<br>
	<br>

<h3>Observaciones</h3>
<table border="1">
    <tr><th>Observación</th><th>Fecha de creación</th><th>Detalles</th><%if (usuario.getRol() == Rol.ADMIN) {%><th>Acción</th><%}%></tr>

<%
List<ObservacionDiaria> observaciones = (List<ObservacionDiaria>) request.getAttribute("l_observaciones");
if (observaciones == null || observaciones.isEmpty()) {
    if((usuario.getRol() == Rol.ADMIN)) {%><tr><td colspan="4" style="text-align:center;">🚫 No hay observaciones disponibles de momento.</td></tr>
	<%} else {%> <tr><td colspan="3" style="text-align:center;">🚫 No hay observaciones disponibles de momento.</td></tr>
<%
} } else {
    int cont = 1;
    for (ObservacionDiaria o : observaciones) {
%>
    <tr>
        <td><%= "Observación " + cont %></td>
        <td><%= o.getFechaCreacion() %></td>
        <td><a href="observaciones?action=viewDetailsObs&id_observacion=<%= o.getId_observacion() %>&id_alumno=<%= o.getId_alumno() %>">👁️ Ver</a></td>
        
        <% if((usuario.getRol() == Rol.ADMIN)) {%>
        <td>
            <a href="observaciones?action=edit&id_observacion=<%= o.getId_observacion() %>&id_alumno=<%= o.getId_alumno() %>&id_curso=<%= id_curso %>&id_tutorP=<%= id_tutorP %>">✏️ Editar</a> |
            <a href="observaciones?action=delete&id_observacion=<%= o.getId_observacion() %>&id_alumno=<%= o.getId_alumno() %>&id_curso=<%= id_curso %>&id_tutorP=<%= id_tutorP %>"
               onclick="return confirm('¿Eliminar esta observación?')">🗑️ Borrar</a>
        </td>
        <%}%>
    </tr>
<%
        cont++;
    }
}
%>
</table>

<% if((usuario.getRol() == Rol.ADMIN)) {%>
<br>
<a href="observaciones?action=insert&id_alumno=<%= id_alumno %>&id_curso=<%= id_curso %>&id_tutorP=<%= id_tutorP %>" class="btn btn-success">➕ Nueva observación</a>
<%} %>
<br>
<br>

<h3>Evaluación del tutor de curso</h3>
<table border="1">
    <tr>
        <th>Criterio</th>
        <th>Capacidad</th>
        <th>Puntuación</th>
        <th>Observaciones</th>
        <th>Fecha</th>
        <th>Fecha de creación</th>
        <% if (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_CURSO) { %><th>Acción</th><% } %>
    </tr>

<%
List<EvaluacionTutorC> evaluacionesc = (List<EvaluacionTutorC>) request.getAttribute("l_tutorC");
List<CapacidadEvaluacion> capacidades = (List<CapacidadEvaluacion>) request.getAttribute("l_capacidadC");
List<CriterioEvaluacion> criterios = (List<CriterioEvaluacion>) request.getAttribute("l_criterio");

if (evaluacionesc == null || evaluacionesc.isEmpty()) {
%>
    <tr>
        <td colspan="<%= (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_CURSO) ? 7 : 6 %>" style="text-align:center;">
            🚫 No hay evaluaciones del tutor de curso disponibles de momento.
        </td>
    </tr>
</table>
<h4>Nota media: Sin datos</h4>
<%
} else {
    double sum = 0;
    int t_cap = 0;
    for (modelo.CriterioEvaluacion crit : criterios) {
        boolean printedHeader = false;
        for (CapacidadEvaluacion cap : capacidades) {
            if (cap.getId_criterio() == crit.getId_criterio()) {
                for (EvaluacionTutorC evc : evaluacionesc) {
                    if (evc.getId_capacidad() == cap.getId_capacidad()) {
%>
    <tr>
        <% if (!printedHeader) { 
               int rowCount = 0;
               for (CapacidadEvaluacion caux : capacidades) {
                   if (caux.getId_criterio() == crit.getId_criterio()) rowCount++;
               }
        %>
            <td rowspan="<%= rowCount %>"><%= crit.getNombre() %></td>
        <% printedHeader = true; } %>

        <td><%= cap.getNombre() %></td>
        <td><%= evc.getPuntuacion() %></td>
        <td><%= evc.getObservaciones() %></td>
        <td><%= evc.getFecha() %></td>
        <td><%= evc.getFechaCreacion() %></td>

        <% if (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_CURSO) { %>
        <td>
            <a href="evaluacionesC?action=edit&id_evaluacionTC=<%= evc.getId_evaluacionC() %>&id_alumno=<%= evc.getId_alumno() %>">✏️ Editar</a> |
            <a href="evaluacionesC?action=delete&id_evaluacionTC=<%= evc.getId_evaluacionC() %>&id_alumno=<%= evc.getId_alumno() %>"
               onclick="return confirm('¿Eliminar esta evaluación?')">🗑️ Borrar</a>
        </td>
        <% } %>
    </tr>
<%
                        t_cap++;
                        sum += evc.getPuntuacion();
                    }
                }
            }
        }
    }
%>
</table>
<h4>Nota media: <%= (t_cap > 0) ? String.format("%.2f", sum / t_cap) : "Sin datos" %></h4>
<%
} // fin else
%>

<% if (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_CURSO) { %>
<br>
<a href="evaluacionesC?action=insert&id_alumno=<%= id_alumno %>&id_curso=<%= id_curso %>&id_tutorP=<%= id_tutorP %>" class="btn btn-success">➕ Nueva evaluación</a>
<% } %>
<br><br>
<h3>Evaluación del tutor de prácticas</h3>
<table border="1">
    <tr>
        <th>Criterio</th>
        <th>Capacidad</th>
        <th>Puntuación</th>
        <th>Observaciones</th>
        <th>Fecha</th>
        <th>Fecha de creación</th>
        <% if (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_PRACTICAS) { %><th>Acción</th><% } %>
    </tr>

<%
List<EvaluacionTutorP> evaluacionesp = (List<EvaluacionTutorP>) request.getAttribute("l_tutorP");
capacidades = (List<CapacidadEvaluacion>) request.getAttribute("l_capacidadP");

if (evaluacionesp == null || evaluacionesp.isEmpty()) {
%>
    <tr>
        <td colspan="<%= (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_PRACTICAS) ? 7 : 6 %>" style="text-align:center;">
            🚫 No hay evaluaciones del tutor de prácticas disponibles de momento.
        </td>
    </tr>
</table>
<h4>Nota media: Sin datos</h4>
<%
} else {
    double sum = 0;
    int t_cap = 0;
    for (modelo.CriterioEvaluacion crit : criterios) {
        boolean printedHeader = false;
        for (CapacidadEvaluacion cap : capacidades) {
            if (cap.getId_criterio() == crit.getId_criterio()) {
                for (EvaluacionTutorP evp : evaluacionesp) {
                    if (evp.getId_capacidad() == cap.getId_capacidad()) {
%>
    <tr>
        <% if (!printedHeader) { 
               int rowCount = 0;
               for (CapacidadEvaluacion caux : capacidades) {
                   if (caux.getId_criterio() == crit.getId_criterio()) rowCount++;
               }
        %>
            <td rowspan="<%= rowCount %>"><%= "Criterio: " + crit.getNombre() %></td>
        <% printedHeader = true; } %>

        <td><%= cap.getNombre() %></td>
        <td><%= evp.getPuntuacion() %></td>
        <td><%= evp.getObservaciones() %></td>
        <td><%= evp.getFecha() %></td>
        <td><%= evp.getFechaCreacion() %></td>

        <% if (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_PRACTICAS) { %>
        <td>
            <a href="evaluacionesP?action=edit&id_evaluacion=<%= evp.getId_evaluacionP() %>&id_alumno=<%= evp.getId_alumno() %>">✏️ Editar</a> |
            <a href="evaluacionesP?action=delete&id_evaluacion=<%= evp.getId_evaluacionP() %>&id_alumno=<%= evp.getId_alumno() %>"
               onclick="return confirm('¿Eliminar esta evaluación?')">🗑️ Borrar</a>
        </td>
        <% } %>
    </tr>
<%
                        t_cap++;
                        sum += evp.getPuntuacion();
                    }
                }
            }
        }
    }
%>
</table>
<h4>Nota media: <%= (t_cap > 0) ? String.format("%.2f", sum / t_cap) : "Sin datos" %></h4>
<%
} // fin else
%>

<% if (usuario.getRol() == Rol.ADMIN || usuario.getRol() == Rol.TUTOR_PRACTICAS) { %>
<br>
<a href="evaluacionesP?action=insert&id_alumno=<%= id_alumno %>&id_curso=<%= id_curso %>&id_tutorP=<%= id_tutorP %>" class="btn btn-success">➕ Nueva evaluación</a>
<% } %>
</main>

	<p>
		<a href="index.jsp">🏠 Volver a la página de inicio</a>	
		<% if (perfil) { %>
				<%} else { %>
				| <a href="usuarios?action=list">⬅️ Volver al listado</a>
		<%} %>
	</p>

</body>
</html>