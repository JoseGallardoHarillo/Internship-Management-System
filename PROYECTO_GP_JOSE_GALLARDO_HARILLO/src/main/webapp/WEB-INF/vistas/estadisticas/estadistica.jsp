<%@page import="modelo.Estadistica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
    <title>Estadísticas</title>
</head>
<body>
<main class="typical">
    <h2>📊 Estadísticas del sistema</h2>

    <!-- Usuarios por rol -->
    <h3>Usuarios por rol</h3>
    <table border="1">
        <tr><th>Rol</th><th>Total</th></tr>
        <%
            List<Estadistica> usuariosPorRol = (List<Estadistica>) request.getAttribute("usuariosPorRol");
            if (usuariosPorRol == null || usuariosPorRol.isEmpty()) {
        %>
            <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
        <%
            } else {
                for (Estadistica e : usuariosPorRol) {
        %>
            <tr>
                <td><%= e.getNombre() %></td>
                <td><%= (int)e.getValor() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>

    <!-- Alumnos por curso -->
    <h3>Alumnos por curso</h3>
    <table border="1">
        <tr><th>Curso</th><th>Total</th></tr>
        <%
            List<Estadistica> alumnosPorCurso = (List<Estadistica>) request.getAttribute("alumnosPorCurso");
            if (alumnosPorCurso == null || alumnosPorCurso.isEmpty()) {
        %>
            <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
        <%
            } else {
                for (Estadistica e : alumnosPorCurso) {
        %>
            <tr>
                <td><%= e.getNombre() %></td>
                <td><%= (int)e.getValor() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>

    <!-- Promedio de evaluaciones tutor curso -->
    <h3>Promedio de evaluaciones por capacidad (Tutor Curso)</h3>
    <table border="1">
        <tr><th>Capacidad</th><th>Promedio</th></tr>
        <%
            List<Estadistica> promedioTutorC = (List<Estadistica>) request.getAttribute("promedioEvaluacionesTutorC");
            if (promedioTutorC == null || promedioTutorC.isEmpty()) {
        %>
            <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
        <%
            } else {
                for (Estadistica e : promedioTutorC) {
        %>
            <tr>
                <td><%= e.getNombre() %></td>
                <td><%= String.format("%.2f", e.getValor()) %></td>
            </tr>
        <%
                }
            }
        %>
    </table>

    <!-- Promedio de evaluaciones tutor prácticas -->
    <h3>Promedio de evaluaciones por capacidad (Tutor Prácticas)</h3>
    <table border="1">
        <tr><th>Capacidad</th><th>Promedio</th></tr>
        <%
            List<Estadistica> promedioTutorP = (List<Estadistica>) request.getAttribute("promedioEvaluacionesTutorP");
            if (promedioTutorP == null || promedioTutorP.isEmpty()) {
        %>
            <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
        <%
            } else {
                for (Estadistica e : promedioTutorP) {
        %>
            <tr>
                <td><%= e.getNombre() %></td>
                <td><%= String.format("%.2f", e.getValor()) %></td>
            </tr>
        <%
                }
            }
        %>
    </table>

<!-- Evaluaciones por alumno (Tutor de Curso) -->
<h3>Evaluaciones del tutor de curso por alumno</h3>
<table border="1">
    <tr><th>Alumno</th><th>Total evaluaciones de curso</th></tr>
    <%
        List<Estadistica> evalCurso = (List<Estadistica>) request.getAttribute("evaluacionesCurso");
        if (evalCurso == null || evalCurso.isEmpty()) {
    %>
        <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
    <%
        } else {
            for (Estadistica e : evalCurso) {
    %>
        <tr>
            <td><%= e.getNombre() %></td>
            <td><%= (int)e.getValor() %></td>
        </tr>
    <%
            }
        }
    %>
</table>

<br>

<!-- Evaluaciones por alumno (Tutor de Prácticas) -->
<h3>Evaluaciones del tutor de prácticas por alumno</h3>
<table border="1">
    <tr><th>Alumno</th><th>Total evaluaciones de prácticas</th></tr>
    <%
        List<Estadistica> evalPracticas = (List<Estadistica>) request.getAttribute("evaluacionesPracticas");
        if (evalPracticas == null || evalPracticas.isEmpty()) {
    %>
        <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
    <%
        } else {
            for (Estadistica e : evalPracticas) {
    %>
        <tr>
            <td><%= e.getNombre() %></td>
            <td><%= (int)e.getValor() %></td>
        </tr>
    <%
            }
        }
    %>
</table>

    <!-- Alumnos por tutor curso -->
    <h3>Alumnos por tutor de curso</h3>
    <table border="1">
        <tr><th>Tutor</th><th>Total alumnos</th></tr>
        <%
            List<Estadistica> alumnosTutorC = (List<Estadistica>) request.getAttribute("alumnosPorTutorCurso");
            if (alumnosTutorC == null || alumnosTutorC.isEmpty()) {
        %>
            <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
        <%
            } else {
                for (Estadistica e : alumnosTutorC) {
        %>
            <tr>
                <td><%= e.getNombre() %></td>
                <td><%= (int)e.getValor() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>

    <!-- Alumnos por tutor prácticas -->
    <h3>Alumnos por tutor de prácticas</h3>
    <table border="1">
        <tr><th>Tutor</th><th>Total alumnos</th></tr>
        <%
            List<Estadistica> alumnosTutorP = (List<Estadistica>) request.getAttribute("alumnosPorTutorPracticas");
            if (alumnosTutorP == null || alumnosTutorP.isEmpty()) {
        %>
            <tr><td colspan="2" style="text-align:center;">🚫 No hay datos disponibles</td></tr>
        <%
            } else {
                for (Estadistica e : alumnosTutorP) {
        %>
            <tr>
                <td><%= e.getNombre() %></td>
                <td><%= (int)e.getValor() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</main>
<p><a href="index.jsp">🏠 Volver a la página de inicio</a></p>
</body>
</html>