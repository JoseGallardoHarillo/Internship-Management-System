<%@page import="modelo.Rol"%>
<%@ page import="modelo.Usuario" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	//Con ello podremos modificar cosas en base al usuario que ha iniciado sesión
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/estilo.css?v=<%= System.currentTimeMillis() %>">
    <title>Gestion de Practicas</title>
</head>

<body>

    <header>
        <div id="logo">🎩 Gestión de Prácticas</div>

        <h1>
            ¡Bienvenid@
            <% if (usuario != null) { %>
                <%= usuario.getNombre() + "!" %>
            <% } else { %>
                a bordo!
            <% } %>
        </h1>
        <p>Seleccione una opción:</p>

<nav>
    <ul style="display:flex; align-items:center; gap:15px; margin:0; padding:0 15px; list-style:none; width:100%;">
        <% if (usuario != null) { %>
            <li><a href="usuarios?action=view&id_usuario=<%= usuario.getId_usuario() %>&rol=<%= usuario.getRol() %>&perfil=<%= true %>">👤 Perfil</a></li>
            <% if (usuario.getRol() == Rol.ADMIN) { %>
                <li><a href="usuarios?action=list">👥 Gestión de Usuarios</a></li>
                <li><a href="empresas?action=list">🏢 Gestión de Empresas</a></li>
                <li><a href="cursos?action=list">🎓 Gestión de Cursos</a></li>
                <li><a href="criterios?action=list">📋 Criterios de Evaluación</a></li>
                <li><a href= "estadisticas">📊 Estadísticas</a></li>
            <% } else if (usuario.getRol() == Rol.TUTOR_CURSO || usuario.getRol() == Rol.TUTOR_PRACTICAS) { %>
                <li><a href="usuarios?action=list">👥 Alumnos</a></li>
                <% if(usuario.getRol() == Rol.TUTOR_CURSO){ %>
                    <li><a href="empresas?action=list">🏢 Empresas</a></li>
                    <li><a href="cursos?action=list">🎓 Cursos</a></li>
                <% } %>
                <li><a href="criterios?action=list">📋 Criterios de Evaluación</a></li>
            <% } %>
            <!-- Botón de cerrar sesión alineado a la derecha pero con margen como los demás -->
            <li style="margin-left:auto;">
                <a href="login?action=logout">🔓 Cerrar sesión</a>
            </li>
        <% } else { %>
            <!-- Botón de iniciar sesión alineado a la derecha pero con margen como los demás -->
            <li>
                <a href="login?action=login">🔐 Iniciar sesión</a>
            </li>
        <% } %>
    </ul>
</nav>
    </header>

    <main class="typical">

        <section id="introduccion">
            <h2>Introducción</h2>
            <p>
                Este proyecto ha sido realizado con el fín de obtener un certificado de profesionalidad en desarrollo de aplicaciones con tecnologías web.
                Esta página web permitirá gestionar prácticas académicas de todo tipo, siendo un ejemplo claro de un MVC bien implementado.
            </p>
            <hr>
        </section>

        <section id="sobremi">
            <h2>Sobre mí</h2>
            <p>
                Mi nombre es José Gallardo Harillo, graduado en Ingeniería Informática de Computadores, y desde entonces he estado aventurándome por las distintas ramas de la informática para poder meter cabeza en alguno de los sectores existentes.
            </p>
            <p>
                Me caracterizo por las siguientes características:
            </p>
            <ul>
                <li><strong>Autonomía:</strong><br> Desarrollada gracias al trabajo en equipo durante mi formación, lo que me ha permitido asumir responsabilidades individuales y centrarme en mis tareas de forma eficiente.</li>
                <li><strong>Creatividad:</strong><br> Aporto soluciones originales y cuido los detalles para lograr resultados únicos y diferenciadores.</li>
                <li><strong>Constancia:</strong><br> Me esfuerzo en dedicar el tiempo y la energía necesarios para completar objetivos con compromiso y eficiencia.</li>
            </ul>
            <hr>
        </section>

        <section id="objetivos">
            <h2>Objetivos</h2>
            <ul>
                <li>Implementar el patrón MVC en Java.</li>
                <li>Gestionar bases de datos relacionales con MySQL.</li>
                <li>Implementar el patrón MVC en Java</li>
                <li>Implementar sistemas de autenticación y autorización.</li>
                <li>Crear interfaces de usuario intuitivas.</li>
                <li>Desarrollar funcionalidades CRUD completas</li>
                <li>Generar reportes y estadísticas</li>
                <li>Aplicar buenas prácticas de programación</li>
            </ul>
        </section>

        <section id="atec">
            <h2>Apartado técnico del proyecto</h2>

			<article> 
			
				<h3>Gestión de datos</h3> 
				<p>Con respecto a la gestión de datos se realizan las operaciones básicas CRUD:</p> 	
				<ul> 
				
					<li>Altas</li> 
					<li>Bajas</li> 
					<li>Modificaciones</li> 
				</ul> 
				<p>Se hace uso de <strong>MySQL</strong> como base de datos teniendo la siguiente estructura:</p> 
				<ol> 
					<li> 
						<code>usuario</code>
						 <p>Usuarios del sistema (administradores, tutores de curso, tutores de prácticas, alumnos).</p> 
					</li> 
					
					<li> 
						<code>criterioevaluacion</code> 
						<p>Define criterios generales de evaluación (p. ej. "Comunicación", "Conocimientos técnicos").</p> 
					</li> 
					
					<li> 
						<code>capacidadevaluacion</code> 
						<p>Capacidades/ítems de evaluación específicos ligados a un <code>criterioevaluacion</code>. Cada registro contiene la puntuación máxima y la descripción de la capacidad evaluada.</p> 
					</li> 
					
					<li> 
						<code>empresa</code> 
						<p>Almacena empresas donde los alumnos realizan prácticas: datos fiscales (CIF), contacto, sector, etc. Referenciada por tutores de prácticas y por alumnos asignados.</p> 
					</li> 
					
					<li> 
						<code>tutorcurso</code> 
						<p>Tutores responsables dentro del centro/curso (docentes). Usado para asignar la responsabilidad del curso, siendo uno de los herederos de Usuario.</p> 
					</li> 
					
					<li> 
						<code>tutorpracticas</code> 
						<p>Tutores en la empresa que supervisan al alumno durante las prácticas. Contiene vínculo a <code>empresa</code> y se usa en evaluaciones e incidencias, heredando de Usuario.</p> 
					</li> 
					
					<li> 
						<code>curso</code> 
						<p>Información de los cursos/convocatorias (nombre, duración, fechas). Referencia a <code>tutorcurso</code> como responsable del curso, pudiendo varias tutores dar cursos del mismo tipo.</p> 
					</li> 
					
					<li> 
						<code>alumno</code> 
						<p>Entidad principal que representa a los estudiantes: datos personales, asignación a <code>curso</code>, <code>empresa</code> y <code>tutorpracticas</code>, fechas y estado. Sobre la tabla <code>alumno</code> pivotan evaluaciones, incidencias y observaciones, siendo además otro de los herederos de Usuario.</p> 
					</li> 
					
					<li> 
						<code>evaluaciontutorcurso</code> 
						<p>Registra evaluaciones individuales de los tutores de los cursos hacia los alumnos por capacidad de evaluación (<code>capacidadevaluacion</code>).</p> 
					</li> 
					
					<li> 
						<code>evaluaciontutorpracticas</code> 
						<p>Registra evaluaciones individuales de los tutores de prácticas hacia los alumnos por capacidad de evaluación (<code>capacidadevaluacion</code>).</p> 
					</li> 
					
					<li> 
						<code>incidencia</code> 
						<p>Registro de incidentes relacionados con un alumno (faltas, retrasos, problemas de actitud, etc.). Vinculada a <code>alumno</code> y opcionalmente a <code>tutorpracticas</code>, con estado de seguimiento y resolución.</p> 
					</li> 
					
					<li> 
						<code>observaciondiaria</code> 
						<p>Observaciones diarias o partes de actividad realizadas por el alumno: actividades, explicaciones, horas realizadas y observaciones tanto del alumno como del tutor.</p> 
					</li> 
				</ol> 
			</article>

            <article>
                <h3>Modelo Vista Controlador (MVC)</h3>
                <p>La implementación del MVC se ha realizado utilizando la IDE de <strong>eclipse</strong>, mediante el lenguaje <strong>Java 11</strong>.</p>
                <p>La página se trata de un proyecto web dinámico arrancado por el servidor <strong>Apache Tomcat 9.</strong></p>
            </article>

        </section>
    </main>

    <footer>
        <p>&copy; 2025 Rincón del Sombrero. Todos los derechos reservados.</p>
        <p>Año actual: <span id="year"></span></p>
    </footer>

    <script>
        const yearSpan = document.getElementById("year");
        const currentYear = new Date().getFullYear();
        yearSpan.textContent = currentYear;
    </script>

</body>

</html>