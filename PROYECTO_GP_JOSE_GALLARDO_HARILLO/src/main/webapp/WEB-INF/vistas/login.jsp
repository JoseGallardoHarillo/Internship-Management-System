<%@page import="modelo.TutorPracticas"%>
<%@page import="modelo.TutorCurso"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Rol"%>
<%@page import="modelo.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
    <title>Gestión de Prácticas</title>
    <style>
        .rol-section { display: none; margin-top: 15px; border: 1px solid #ccc; padding: 10px; border-radius: 10px; }
    </style>
</head>

<body>
<h2>Inicie sesión</h2>
<a href="index.jsp">⬅️ Volver a la página inicial</a>

<form action="login" method="post">
    
    Email: <input type="email" name="email" required><br>
    Contraseña: <input type="password" name="password" required><br>

    <br>
    <input type="submit" value="Entrar">
</form>


<% 
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <div style="color:red; margin-top:10px;">
        <strong>Error:</strong> <%= error %>
    </div>
<% } %>
</body>
</html>