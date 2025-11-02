<%@page import="modelo.Empresa"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

	//Fila posiblemente creada recibida desde servlet
    Empresa empresa = (Empresa) request.getAttribute("empresa");
	
	//Parámetros para rellenar el formulario en caso de edite
    String id_empresa = (empresa != null) ? String.valueOf(empresa.getId_empresa()) : "";
    String nombre = (empresa != null) ? empresa.getNombre() : "";
    String cif = (empresa != null) ? empresa.getCif() : "";
    String direccion = (empresa != null) ? empresa.getDireccion() : "";
    String telefono = (empresa != null) ? empresa.getTelefono() : "";
    String email = (empresa != null) ? empresa.getEmail() : "";
    String personaContacto = (empresa != null) ? empresa.getPersonaContacto() : "";
    String sector = (empresa != null) ? empresa.getSector() : "";
    String fechaCreacion = (empresa != null) ? String.valueOf(empresa.getFechaCreacion()) : "";
%>


<html>

<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
	
  	<title>Gestion de Practicas</title>
</head>

<body>
<h2><%= (empresa != null) ? "Editar Empresa" : "Registro Empresa" %></h2>
<a href="empresas?action=list">⬅️ Volver al listado</a>

<form action="empresas" method="post">
    <input type="hidden" name="id_empresa" value="<%= id_empresa %>">
    <input type="hidden" name="fechaCreacion" value="<%= fechaCreacion %>">
    
    Nombre: <input type="text" name="nombre" value="<%= nombre %>" required><br>
    CIF: <input type="text" name="cif" value="<%= cif %>" required><br>
    Dirección: <input type="text" name="direccion" value="<%= direccion %>" required><br>
    Teléfono: <input type="text" pattern="^[0-9]{9}$" title="El teléfono debe contener exactamente 9 dígitos numéricos" name="telefono" value="<%= telefono %>" required><br>
    Email: <input type="email" name="email" value="<%= email %>" required><br>
    Contacto: <input type="text" name="personaContacto" value="<%= personaContacto %>" required><br>
    Sector: <input type="text" name="sector" value="<%= sector %>" required><br>

    <input type="submit" value="Guardar">
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