<%@page import="modelo.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
    Usuario dto = (Usuario) request.getAttribute("dto");
	boolean perfil = (Boolean) request.getAttribute("perfil");
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
		<% if (perfil) { %>
	    <p>
	        <strong>Contraseña: </strong>
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