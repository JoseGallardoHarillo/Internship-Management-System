SET SQL_SAFE_UPDATES = 0;

-- 1️⃣ Tablas más dependientes (las que hacen referencia a otras)
DELETE FROM evaluaciontutorpracticas;
DELETE FROM evaluaciontutorcurso;
DELETE FROM incidencia;
DELETE FROM observaciondiaria;

-- 2️⃣ Tablas intermedias (que dependen de usuario, empresa o curso)
DELETE FROM alumno;
DELETE FROM curso;
DELETE FROM tutorpracticas;
DELETE FROM tutorcurso;

-- 3️⃣ Tablas base (que no dependen de otras o son raíz)
DELETE FROM empresa;
DELETE FROM usuario;
DELETE FROM capacidadevaluacion;
DELETE FROM criterioevaluacion;


-- Reiniciar el auto_increment a 1
ALTER TABLE criterioevaluacion AUTO_INCREMENT = 1;
ALTER TABLE capacidadevaluacion AUTO_INCREMENT = 1;
ALTER TABLE empresa AUTO_INCREMENT = 1;
ALTER TABLE tutorcurso AUTO_INCREMENT = 1;
ALTER TABLE tutorpracticas AUTO_INCREMENT = 1;
ALTER TABLE curso AUTO_INCREMENT = 1;
ALTER TABLE alumno AUTO_INCREMENT = 1;
ALTER TABLE usuario AUTO_INCREMENT = 1;
ALTER TABLE incidencia AUTO_INCREMENT = 1;
ALTER TABLE observaciondiaria AUTO_INCREMENT = 1;
ALTER TABLE evaluaciontutorcurso AUTO_INCREMENT = 1;
ALTER TABLE evaluaciontutorpracticas AUTO_INCREMENT = 1;

SET SQL_SAFE_UPDATES = 1;

-- Empresas

INSERT INTO empresa (nombre, cif, direccion, telefono, email, personaContacto, sector, activo) VALUES
('Shinra Electric Power Company', 'FF0001A', 'Sector 0, Midgar', '999123456', 'contacto@shinra.ff', 'Reeve Tuesti', 'Energía y Tecnología', TRUE),
('Gold Saucer Entertainment', 'FF0002B', 'Corel del Norte, Midgar', '999654321', 'info@goldsaucer.ff', 'Dio', 'Entretenimiento', TRUE),
('Balamb Garden Institute', 'FF0003C', 'Región de Balamb, Galbadia', '988111222', 'rector@balambgarden.ff', 'Cid Kramer', 'Educación', TRUE),
('Rabanastre Airships Ltd.', 'FF0004D', 'Gran Bazar de Rabanastre', '977333444', 'info@rabanastreair.ff', 'Balthier', 'Transporte Aéreo', TRUE),
('Luca Blitzball Association', 'FF0005E', 'Puerto de Luca, Spira', '966555666', 'admin@lucablitz.ff', 'Wakka', 'Deporte y Eventos', TRUE),
('Avalanche Green Energy', 'FF0006F', 'Sector 7, Midgar', '955777888', 'eco@avalanche.ff', 'Tifa Lockhart', 'Energía Renovable', TRUE),
('Kupo Logistics', 'FF0007G', 'Bosque Moguri, Ivalice', '944999000', 'kupo@kupolog.ff', 'Monty el Moguri', 'Logística', TRUE),
('Cid Highwind Engineering', 'FF0008H', 'Rocket Town', '933888777', 'info@highwindeng.ff', 'Cid Highwind', 'Ingeniería y Aeronáutica', TRUE),
('Narshe Mining Co.', 'FF0009I', 'Montañas de Narshe', '922777666', 'minerals@narshe.ff', 'Arvis', 'Minería y Recursos', TRUE),
('Esthar Research Labs', 'FF0010J', 'Ciudad de Esthar', '911666777', 'research@esthar.ff', 'Dr. Odine', 'Investigación y Desarrollo', TRUE),
('Forest Owls Corporation', 'FF0010K', 'Timber', '911666888', 'info@owls.ff', 'Rinoa Heartilly', 'Operaciones y estrategia', TRUE),
('Talantus S.A', 'FF0010L', 'Alexandria', '911666999', 'research@daga.ff', 'Garnet Til Alexandros', 'Teatro y turismo', TRUE);

-- usuarios

INSERT INTO usuario (nombre, apellidos, dni, email, password, rol, telefono, activo)
VALUES
('José', 'Gallardo Harillo', '29132433J', 'jose.gallardo@example.com', '1234', 'ADMIN', '600111222', TRUE),
('Alicia', 'Claudel', '29132424A', 'alicia.claudel@example.com', '4321', 'ADMIN', '600111233', TRUE),
('Cloud', 'Strife', '12345678A', 'cloud@midgar.ff', 'BusterSword97', 'ALUMNO', '600000001', TRUE),
('Aeris', 'Gainsborough', '12345678B', 'aerith@sector5.ff', 'flower97', 'ALUMNO', '600000002', TRUE),
('Tifa', 'Lockhart', '12345678C', 'tifa@midgar.ff', 'seventhHeaven97', 'TUTOR_CURSO', '600000003', TRUE),
('Barret', 'Wallace', '12345678D', 'barret@midgar.ff', 'marlene97', 'TUTOR_CURSO', '600000004', TRUE),
('Yuffie', 'Kisaraji', '12345678E', 'yuffie@midgar.ff', 'materia97', 'ALUMNO', '600000005', TRUE),
('Cid', 'Highwind', '12345678F', 'cid@highwind.ff', 'password', 'TUTOR_PRACTICAS', '600000006', TRUE),
('Vincent', 'Valentine', '12345678G', 'vincent@turks.ff', '******', 'TUTOR_PRACTICAS', '600000007', FALSE),
('Zack', 'Fair', '12345678H', 'gongaga@midgar.ff', 'aeris97', 'TUTOR_PRACTICAS', '60000008', TRUE),
('Squall', 'Leonhart', '12345678I', 'squall@balamb.ff', 'rinoa99', 'ALUMNO', '600000009', TRUE),
('Quistis', 'Trepe', '12345678J', 'quistis@balamb.ff', 'squall99', 'TUTOR_CURSO', '600000010', TRUE),
('Rinoa', 'Heartilly', '12345678K', 'rinoa@balamb.ff', 'squall99', 'TUTOR_PRACTICAS', '600000011', TRUE),
('Zell', 'Dincht', '12345678L', 'zell@balamb.ff', 'bocadillo99', 'ALUMNO', '6000000012', TRUE),
('Selphie', 'Tilmitt', '12345678M', 'selphie@balamb.ff', 'balamb99', 'ALUMNO', '600000013', TRUE),
('Irvine', 'Kinneas', '12345678N', 'irvine@balamb.ff', 'gunLover99', 'ALUMNO', '600000014', TRUE),
('Seifer', 'Almasy', '12345678O', 'seifer@balamb.ff', 'FUSqual99', 'ALUMNO', '600000015', TRUE),
('Yitán', 'Tribal', '12345678P', 'yitan@talantus.ff', 'garnet00', 'ALUMNO', '600000016', TRUE),
('Garnet', 'Til Alexandros', '12345678Q', 'garnet@talantus.ff', 'yitan00', 'TUTOR_PRACTICAS', '600000017', TRUE),
('Adalbert', 'Steiner', '12345678R', 'steiner@talantus.ff', 'yitan00', 'TUTOR_CURSO', '600000018', TRUE),
('Vivi', 'Ornitier', '12345678S', 'vivi@talantus.ff', 'puppet00', 'ALUMNO', '600000019', TRUE),
('Eiko', 'Carol', '12345678T', 'eiko@talantus.ff', 'wiwiwiwi', 'ALUMNO', '600000020', TRUE),
('Freija', 'Crescent', '12345678U', 'freija@talantus.ff', 'burmecia00', 'TUTOR_CURSO', '600000021', TRUE),
('Amarant', 'Coral', '12345678V', 'amarant@talantus.ff', 'punch00', 'ALUMNO', '600000022', TRUE),
('Quina', 'Quen', '12345678W', 'quina@talantus.ff', 'frogs00', 'TUTOR_CURSO', '600000023', TRUE),
('Cissnei', null, '12345678X', 'cissney@turks.ff', 'zack97', 'TUTOR_PRACTICAS', '6000000024', TRUE),
('Sefirot', null, '6666666J', 'one-wingedangel@mother.ff', 'mother', 'ADMIN', '600111333', TRUE);

-- tutorcurso
INSERT INTO tutorcurso (id_usuario, especialidad) VALUES
(5, 'Defensa personal'), -- Tifa
(6, 'Explosivos y demolisiones'), -- Barret
(12, 'Gestión de Centros Educativos y Magia Azul'), -- Quistis
(20, 'Proteccion a segundos'), -- Steiner
(23, 'Danza y defensa con lanza'), -- Freija
(25, 'Cocina qu'); -- Quina

-- tutorpracticas
INSERT INTO tutorpracticas (id_empresa, id_usuario, cargo, horario) VALUES
(8, 8, 'Ingeniero Jefe de Aeronaves', '9:00 - 17:00'), -- Cid
(1, 9, 'Turco', '9:00 - 17:00'), -- Vincent
(1, 10, 'SOLDADO de elite', '8:00 - 16:00'), -- Zack
(11, 13, 'Coordinadora de equipo', '9:00 - 17:00'), -- Rinoa
(12, 19, 'Directora general', '9:00 - 17:00'), -- Garnet
(1, 26, 'Turco', '9:00 - 17:00'); -- Cissney

-- curso
INSERT INTO curso (nombre, descripcion, duracion, fechaInicio, fechaFin, id_tutorC) VALUES
('Curso de Combate Estratégico', 'Entrenamiento avanzado en tácticas y estrategia de combate.', 180, '2025-01-10', '2025-07-10', 1),
('Curso de Combate Estratégico', 'Entrenamiento avanzado en tácticas y estrategia de combate.', 180, '2025-02-10', '2025-03-10', 2),
('Curso de cocina', 'Aprendrizaje y realización de recetas más populares aprendidas por los qu.', 200, '2025-02-01', '2025-08-01', 6),
('Curso de preparación seed', 'Preparación tanto en daño físico como mágico para ser un seed.', 500, '2025-03-05', '2028-08-08', 3),
('Curso de coreografia de combate', 'Curso para aprender a realizar coreografías avanzadas de combate.', 180, '2025-02-10', '2025-03-10', 4),
('Curso de danza burmeciana', 'Curso para aprender a bailar la danza clásica de Burmecia.', 180, '2025-02-10', '2025-03-10', 5),
('Curso de mixología avanzada', 'Instrucción a las técnicas más avanzadas para crear cócteles que hagan a uno llevarlo al séptimo cielo.', 270, '2025-02-20', '2025-09-12', 1);

-- alumno
INSERT INTO alumno (fechaNacimiento, duracionPracticas, horario, fechaInicio, fechaFin, id_curso, id_empresa, id_tutorP, id_usuario) VALUES
('1976-08-19', 90, '8:00 - 14:00', '2025-03-01', '2025-05-30', 1, 1, 3, 3), -- Cloud en Shinra con Zack
('1975-02-07', 90, '12:00 - 17:00', '2025-03-01', '2025-05-30', 1, 1, 6, 4),  -- Aeris en shinra con cissney
('1991-11-20', 90, '9:00 - 15:00', '2025-04-01', '2025-06-30', 2, 1, 3, 7),  -- Yuffie en shinra con Zack
('1982-08-23', 120, '7:00 - 15:00', '2025-05-03', '2025-06-30', 4, 11, 4, 11), -- Squall en Forest Owls con Rinoa
('1982-03-17', 120, '7:00 - 15:00', '2025-05-03', '2025-06-30', 4, 11, 4, 14), -- Zell en Forest Owls con Rinoa
('1982-06-16', 90, '9:00 - 14:00', '2025-03-01', '2025-05-30', 4, 11, 4, 15), -- Selphie en Forest Owls con Rinoa
('1982-11-24', 90, '10:00 - 15:00', '2025-03-01', '2025-05-30', 4, 11, 4, 16),  -- Irvine en Forest Owls con Rinoa
('1982-11-22', 90, '10:00 - 15:00', '2025-03-01', '2025-05-30', 4, 11, 4, 17),  -- Seifer en Forest Owls con Rinoa
('2000-09-01', 90, '10:00 - 15:00', '2025-03-01', '2025-05-30', 5, 12, 5, 18),  -- Yitán en tantalus con Garnet
('2000-07-02', 90, '10:00 - 15:00', '2025-03-01', '2025-05-30', 6, 12, 5, 21),  -- Vivi en tantalus con Garnet
('2000-03-03', 90, '10:00 - 15:00', '2025-03-01', '2025-05-30', 3, 12, 5, 22),  -- Eiko en tantalus con Garnet
('2000-11-25', 90, '10:00 - 15:00', '2025-03-01', '2025-05-30', 3, 12, 5, 24);  -- Amarant en tantalus con Garnet

-- Inserts coherentes y ambientados
INSERT INTO criterioevaluacion (nombre, descripcion, peso, activo)
VALUES
('Dominio técnico', 'Capacidad del alumno para aplicar conocimientos técnicos de su formación en entornos reales de trabajo.', 25.00, TRUE),
('Trabajo en equipo', 'Participación activa y positiva en equipos multidisciplinares, demostrando cooperación y empatía.', 15.00, TRUE),
('Comunicación', 'Claridad, fluidez y corrección en la comunicación oral y escrita en el entorno profesional.', 10.00, TRUE),
('Autonomía y responsabilidad', 'Nivel de independencia, compromiso y cumplimiento de tareas asignadas sin supervisión constante.', 20.00, TRUE),
('Actitud profesional', 'Respeto, puntualidad, interés por aprender y comportamiento ético durante las prácticas.', 10.00, TRUE),
('Adaptabilidad', 'Capacidad para ajustarse a cambios en las tareas, horarios o métodos de trabajo.', 10.00, TRUE),
('Resolución de problemas', 'Capacidad para analizar situaciones complejas y proponer soluciones eficaces y creativas.', 10.00, TRUE);

-- Criterios especiales:
INSERT INTO criterioevaluacion (nombre, descripcion, peso, activo)
VALUES
('Espíritu Seed', 'Capacidad del alumno para mantener la calma y actuar con precisión en situaciones de alta presión.', 5.00, TRUE),
('Sinergia de grupo', 'Alineación del alumno con los valores del equipo y su capacidad para potenciar las habilidades de los demás.', 5.00, TRUE);

INSERT INTO capacidadevaluacion (id_criterio, nombre, descripcion, puntuacionMaxima, activo) VALUES
-- Criterio 1: Dominio técnico en combate
(1, 'Precisión en ataques', 'Evalúa la capacidad del alumno para ejecutar ataques con precisión y eficiencia en combate.', 10, TRUE),
(1, 'Uso correcto del equipo', 'Analiza si el alumno emplea adecuadamente las armas y materias asignadas.', 10, TRUE),

-- Criterio 2: Trabajo en equipo y cooperación
(2, 'Apoyo al grupo', 'Mide la disposición del alumno a ayudar a sus compañeros durante las prácticas.', 10, TRUE),
(2, 'Comunicación efectiva', 'Evalúa cómo el alumno comparte información clave durante las misiones o tareas grupales.', 10, TRUE),

-- Criterio 3: Responsabilidad y cumplimiento
(3, 'Cumplimiento de horarios', 'Valora si el alumno llega puntualmente y cumple las horas de práctica.', 10, TRUE),
(3, 'Entrega de informes', 'Verifica la presentación correcta de registros y reportes semanales.', 10, TRUE),

-- Criterio 4: Adaptabilidad al entorno laboral
(4, 'Respuesta ante imprevistos', 'Evalúa cómo el alumno reacciona ante situaciones imprevistas o cambios en las tareas.', 10, TRUE),
(4, 'Actitud frente al cambio', 'Analiza la disposición del alumno a aprender nuevos métodos o adaptarse a nuevas herramientas.', 10, TRUE),

-- Criterio 5: Iniciativa y resolución de problemas
(5, 'Capacidad de propuesta', 'Mide si el alumno aporta ideas o sugerencias para mejorar procesos o tareas.', 10, TRUE),
(5, 'Gestión de incidencias', 'Evalúa cómo el alumno identifica y resuelve problemas menores sin necesidad de supervisión directa.', 10, TRUE),

-- Criterio 6: Actitud profesional y ética
(6, 'Respeto a las normas', 'Verifica si el alumno cumple las normas internas de la empresa y mantiene una conducta profesional.', 10, TRUE),
(6, 'Trato con el equipo', 'Evalúa la cortesía, respeto y actitud del alumno con tutores y compañeros.', 10, TRUE);

INSERT INTO incidencia (id_alumno, id_curso, id_tutorP, fecha, tipo, descripcion, resolucion, estado, fechaCreacion, fechaResolucion)
VALUES
-- Cloud - Shinra (Zack como tutor de prácticas)
(1, 1, 3, '2025-04-15', 'OTROS', 'El alumno Cloud reporta que una materia elemental se fragmentó durante el entrenamiento, provocando una pérdida temporal de energía mágica.', NULL, 'ABIERTA', CURRENT_TIMESTAMP, NULL),

-- Aeris - Shinra (Cissney como tutora de prácticas)
(2, 1, 6, '2025-04-18', 'OTROS', 'Aeris notifica un fallo menor en el sistema de ventilación del reactor donde realizaba sus prácticas. No hubo daños personales.', 'Se revisó el sistema y se reemplazó el filtro defectuoso.', 'RESUELTA', CURRENT_TIMESTAMP, '2025-04-19 10:15:00'),

-- Yuffie - Shinra (Zack)
(3, 2, 3, '2025-05-01', 'RETRASO', 'Yuffie ha presentado retrasos de entre 10 y 15 minutos en varias jornadas consecutivas.', 'Se acordó un ajuste en el horario y compromiso de puntualidad.', 'RESUELTA', CURRENT_TIMESTAMP, '2025-05-03 09:00:00'),

-- Squall - Forest Owls (Rinoa)
(4, 4, 4, '2025-06-10', 'OTROS', 'Squall detecta un fallo en el simulador Seed tras una sesión práctica. Equipo técnico en revisión.', NULL, 'EN_PROCESO', CURRENT_TIMESTAMP, NULL),

-- Zell - Forest Owls (Rinoa)
(5, 4, 4, '2025-05-22', 'PROBLEMA_ACTITUD', 'Zell realizó comentarios inapropiados durante la sesión práctica. El tutor solicita seguimiento.', NULL, 'ABIERTA', CURRENT_TIMESTAMP, NULL),

-- Selphie - Forest Owls (Rinoa)
(6, 4, 4, '2025-04-30', 'FALTA', 'Selphie no asistió a la práctica del 30 de abril sin previo aviso.', 'Alumno justificó la falta posteriormente por motivos médicos.', 'RESUELTA', CURRENT_TIMESTAMP, '2025-05-01 08:30:00'),

-- Irvine - Forest Owls (Rinoa)
(7, 4, 4, '2025-05-03', 'RETRASO', 'Irvine ha llegado con retraso en múltiples ocasiones durante la última semana.', 'Tutor estableció recordatorio de puntualidad y revisión semanal.', 'RESUELTA', CURRENT_TIMESTAMP, '2025-05-05 09:10:00'),

-- Seifer - Forest Owls (Rinoa)
(8, 4, 4, '2025-05-10', 'PROBLEMA_ACTITUD', 'Seifer tuvo un altercado verbal con otro alumno durante el trabajo grupal.', NULL, 'EN_PROCESO', CURRENT_TIMESTAMP, NULL),

-- Yitán - Talantus (Garnet)
(9, 5, 5, '2025-03-20', 'OTROS', 'Yitán reportó un ruido inusual en el propulsor durante una prueba de vuelo.', 'El propulsor fue reparado por mantenimiento sin incidencias mayores.', 'RESUELTA', CURRENT_TIMESTAMP, '2025-03-22 15:45:00'),

-- Vivi - Talantus (Garnet)
(10, 6, 5, '2025-04-25', 'FALTA', 'Vivi ha faltado tres días consecutivos sin aviso. Tutor intenta contactar.', NULL, 'ABIERTA', CURRENT_TIMESTAMP, NULL),

-- Eiko - Talantus (Garnet)
(11, 3, 5, '2025-05-07', 'OTROS', 'Eiko rompió accidentalmente material de cocina durante la práctica.', 'La empresa registró el incidente sin sanción.', 'RESUELTA', CURRENT_TIMESTAMP, '2025-05-09 11:30:00'),

-- Amarant - Talantus (Garnet)
(12, 3, 5, '2025-05-12', 'PROBLEMA_ACTITUD', 'Amarant muestra resistencia a las evaluaciones orales y evita el trabajo en grupo.', NULL, 'EN_PROCESO', CURRENT_TIMESTAMP, NULL);

INSERT INTO observaciondiaria (id_alumno, fecha, actividades, explicaciones, observacionesAlumno, observacionesTutor, horasRealizadas)
VALUES
-- Cloud (Shinra, con Zack)
(1, '2025-04-10', 'Revisión de materia elemental y prácticas con espada.', 
 'El tutor explicó técnicas de canalización de energía mágica en combate.', 
 'He mejorado mi control sobre la materia. Menos pérdidas de energía.', 
 'Cloud demuestra un avance notable en la sincronización materia-espada.', 6),

(1, '2025-04-17', 'Entrenamiento en reactor Mako.', 
 'Se realizó una simulación supervisada de mantenimiento energético.', 
 'Las condiciones eran duras, pero logré completar la práctica.', 
 'Buen desempeño, aunque debe mejorar la coordinación con el equipo.', 5),

-- Aeris (Shinra, con Cissney)
(2, '2025-04-12', 'Supervisión del sistema de ventilación de reactor.', 
 'Explicación de flujo de aire y seguridad industrial.', 
 'Interesante aprender sobre el mantenimiento de los reactores.', 
 'Participación activa y muy buena disposición al trabajo en equipo.', 5),

(2, '2025-04-18', 'Reemplazo de filtro en sistema defectuoso.', 
 'Cissney explicó el protocolo de cierre temporal de ventiladores.', 
 'Pude colaborar en la revisión del filtro dañado.', 
 'Excelente colaboración y atención a los detalles técnicos.', 5),

-- Yuffie (Shinra, con Zack)
(3, '2025-04-28', 'Ensayo de técnicas de infiltración y sigilo.', 
 'Se enfatizó la puntualidad en las misiones.', 
 'He tenido algunos retrasos, pero estoy mejorando.', 
 'Yuffie tiene talento, pero necesita ser más constante.', 4),

-- Squall (Forest Owls, con Rinoa)
(4, '2025-06-05', 'Sesión práctica en simulador Seed.', 
 'Análisis de estrategia y liderazgo en combate táctico.', 
 'Dirigí el grupo durante la simulación, sin fallos mayores.', 
 'Excelente concentración, liderazgo firme, debe trabajar la empatía.', 6),

-- Zell (Forest Owls, con Rinoa)
(5, '2025-05-21', 'Entrenamiento físico intensivo.', 
 'Se reforzaron técnicas de combate y control emocional.', 
 'Creo que fui demasiado competitivo.', 
 'Buen rendimiento físico, pero debe controlar impulsos verbales.', 5),

-- Selphie (Forest Owls, con Rinoa)
(6, '2025-04-29', 'Supervisión de tareas logísticas.', 
 'Planificación de horarios de entrenamiento y descanso.', 
 'Fue un día tranquilo. Ayudé a organizar el calendario.', 
 'Responsable y entusiasta. Muestra iniciativa natural.', 5),

-- Irvine (Forest Owls, con Rinoa)
(7, '2025-05-02', 'Práctica de puntería avanzada.', 
 'Se analizaron errores comunes en disparos a larga distancia.', 
 'Ajusté la mira y mejoré la precisión.', 
 'Buen desempeño técnico, aunque sigue siendo algo distraído.', 4),

-- Seifer (Forest Owls, con Rinoa)
(8, '2025-05-09', 'Simulación de trabajo grupal.', 
 'Rinoa explicó el rol del respeto mutuo en la colaboración.', 
 'No todos entienden mi estilo directo.', 
 'Debe aprender a trabajar en grupo sin generar conflictos.', 5),

-- Yitán (Talantus, con Garnet)
(9, '2025-03-19', 'Revisión del sistema de propulsión teatral.', 
 'Demostración del funcionamiento del propulsor escénico.', 
 'Fue divertido, aunque algo peligroso.', 
 'Buen desempeño técnico y actitud positiva.', 6),

-- Vivi (Talantus, con Garnet)
(10, '2025-04-24', 'Ensayo de iluminación mágica.', 
 'Se explicó cómo combinar energía mágica con equipos escénicos.', 
 'Me costó mantener la concentración, pero aprendí mucho.', 
 'Alumno tímido pero constante. Gran potencial.', 5),

-- Eiko (Talantus, con Garnet)
(11, '2025-05-06', 'Práctica de repostería teatral.', 
 'Se detallaron normas de seguridad en cocina.', 
 'Accidentalmente rompí una bandeja, pero la reemplacé.', 
 'Atenta y colaboradora. Pequeños accidentes sin importancia.', 4),

-- Amarant (Talantus, con Garnet)
(12, '2025-05-11', 'Sesión de evaluación oral.', 
 'El tutor explicó los criterios de evaluación profesional.', 
 'No me gustan las evaluaciones habladas.', 
 'Debe aceptar mejor la retroalimentación y trabajar en comunicación.', 5);
 
INSERT INTO evaluaciontutorpracticas (id_alumno, id_tutorP, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion)
VALUES
-- Cloud (Tutor Zack - Shinra)
(1, 3, 1, 9.0, 'Excelente precisión y control técnico durante las pruebas de materia.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 3, 2, 8.5, 'Usa el equipo correctamente, aunque a veces confía demasiado en la fuerza.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 3, 3, 8.0, 'NO deja a ninguno atrás.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 3, 4, 7.5, 'Algo callado pero se hace querer.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 3, 5, 8.0, 'Ninguna queja ante su cumplimiento de horarios.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 3, 6, 8.5, 'Los informes están muy bien hechos, aunque a veces sus descripciones con concisas.', '2025-04-11', CURRENT_TIMESTAMP),

-- Aerith (Tutora Cissney - Shinra)
(2, 6, 1, 9.5, 'Atina muy bien y sin despeinarse.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 6, 2, 9.0, 'Parece la hija de Gandalf el blanco con tal manejo de la vara.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 6, 3, 10.0, 'Muy empática y activa en el grupo.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 6, 4, 10.0, 'Ejemplo de actitud profesional y serenidad.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 6, 5, 9.5, 'El horario lo cumple a la perfección.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 6, 6, 9.5, 'Informes perfectos y ejemplares, con muy buena caligrafía.', '2025-04-13', CURRENT_TIMESTAMP),

-- Yuffie (Tutor Zack - Shinra)
(3, 3, 1, 7.0, 'Buen dominio técnico, pero necesita mayor concentración.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 3, 2, 7.5, 'Casi pierdo la cabeza con su estrella ninja pero guay.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 3, 3, 6.5, 'Participativa, aunque a veces distrae al equipo.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 3, 4, 7.0, 'Trato correcto con compañeros y tutores.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 3, 5, 5.0, 'Debe mejorar la puntualidad.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 3, 6, 8.0, 'Informes muy bien hechos aunque están escritos en japonés y necesito usar el corrector.', '2025-04-29', CURRENT_TIMESTAMP),

-- Squall (Tutora Rinoa - Forest Owls)
(4, 4, 1, 9.5, 'Es todo un profesional a la hora de asertar con su sable.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 4, 2, 9.0, 'su manejo con el sable es de sobresaliente.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 4, 3, 9.0, 'Liderazgo y compromiso, me encanta.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 4, 4, 9.0, 'Algo callado pero cumple con sus tareas de grupo.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 4, 5, 9.5, 'Cumple su horario a la perfección.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 4, 6, 9.0, 'Informes bien redactados.', '2025-06-06', CURRENT_TIMESTAMP),

-- Zell (Tutora Rinoa - Forest Owls)
(5, 4, 1, 8.0, '"Piñaum piñaum piñaum", me hace gracia que diga eso cuando mete puñetazos.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 4, 2, 7.5, 'No usa equipo pero es contundente.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 4, 3, 6.0, 'Hace reir al grupo pero debería realizar más tareas en el grupo.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 4, 4, 7.0, 'Social pero impulsivo.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 4, 5, 8.0, 'Cumple el horario.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 4, 6, 5.5, 'Informes con faltas de ortografía pero en general bien hechos.', '2025-05-22', CURRENT_TIMESTAMP),

-- Selphie (Tutora Rinoa - Forest Owls)
(6, 4, 1, 8.0, 'Precisa y letal.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 4, 2, 9.0, 'Parece la tortuga ninja naranja con los nunchakus jajaja.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 4, 3, 9.0, 'Muy buena iniciativa.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 4, 4, 8.5, 'La quieren mucho en el grupo.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 4, 5, 8.5, 'Tuvo una falta pero la justificó.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 4, 6, 10.0, 'Informes de 10.', '2025-04-30', CURRENT_TIMESTAMP),

-- Irvine (Tutora Rinoa - Forest Owls)
(7, 4, 1, 8.0, 'Buena puntería y precisión en prácticas de tiro.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 4, 2, 8.0, 'Muy buen francotirador.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 4, 3, 7.5, 'Cumple las tareas, aunque con ligera tendencia a la distracción.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 4, 4, 8.0, 'Muy social pero pasa demasiado tiempo con selphie.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 4, 5, 6.5, 'Tiene algún que otro retraso.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 4, 6, 8.0, 'Informes bien hecho y con buena caligrafía, pero en uno de los informes me dejó un poema.', '2025-05-03', CURRENT_TIMESTAMP),

-- Seifer (Tutora Rinoa - Forest Owls)
(8, 4, 1, 7.5, 'Técnicamente muy capaz, pero a veces desafiante.', '2025-05-10', CURRENT_TIMESTAMP),
(8, 4, 2, 9.5, 'Se nota que practica mucho', '2025-05-10', CURRENT_TIMESTAMP),
(8, 4, 3, 7.0, 'Le cuesta aceptar cambios de liderazgo.', '2025-05-10', CURRENT_TIMESTAMP),
(8, 4, 4, 6.5, 'Dificultades en cooperación grupal.', '2025-05-10', CURRENT_TIMESTAMP),
(8, 4, 5, 7.0, 'Cumple los horarios aunque con actitud variable.', '2025-05-10', CURRENT_TIMESTAMP),
(8, 4, 6, 5.0, 'Informes a mejorar.', '2025-05-10', CURRENT_TIMESTAMP),

-- Yitán (Tutora Garnet - Talantus)
(9, 5, 1, 8.5, 'Se mueve muy bien y atina a los blancos.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 5, 2, 9.0, 'Se adapta a imprevistos con creatividad y con variedad de equipos.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 5, 3, 9.0, 'Muy buen trabajo en grupo, aporta energía positiva.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 5, 4, 8.5, 'Muy social pero algo coqueto', '2025-03-20', CURRENT_TIMESTAMP),
(9, 5, 5, 9.0, 'Cumple los horarios.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 5, 6, 9.0, 'Informes muy bien hechos.', '2025-03-20', CURRENT_TIMESTAMP),

-- Vivi (Tutora Garnet - Talantus)
(10, 5, 1, 10.0, 'Precisión increible.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 2, 10.0, 'Manejo impezable de la vara.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 3, 6.5, 'Le falta iniciativa.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 4, 8.0, 'Algo timido pero se lleva muy bien ocn el grupo.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 5, 10.0, 'Cumple los horarios.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 6, 8.5, 'Informes bien hechos.', '2025-04-25', CURRENT_TIMESTAMP),

-- Eiko (Tutora Garnet - Talantus)
(11, 5, 1, 7.0, 'Cura de maravilla y con precisión.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 5, 2, 8.0, 'Te puede desde curar hasta invocar al diablo, y de ahí bailarte una danza irlandesa.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 5, 3, 8.0, 'Cumple las tareas asignadas con regularidad.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 5, 4, 8.5, 'Buen trabajo en grupo, atenta y colaboradora.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 5, 5, 9.5, 'Cumple los horarios.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 5, 6, 10.0, 'Informes de 10.', '2025-05-07', CURRENT_TIMESTAMP),

-- Amarant (Tutora Garnet - Talantus)
(12, 5, 1, 7.0, 'Buen dominio técnico, aunque prefiere trabajar solo.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 5, 2, 8.5, 'Buen manejo de puños.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 5, 3, 6.0, 'Poca adaptabilidad a cambios de método.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 5, 4, 6.5, 'Dificultades para integrarse con el grupo.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 5, 5, 7.0, 'Cumple horarios, pero evita interacción.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 5, 6, 6.5, 'Debe mejorar su actitud hacia el trabajo colaborativo.', '2025-05-12', CURRENT_TIMESTAMP);

-- Evaluaciones de curso
INSERT INTO evaluaciontutorcurso (id_alumno, id_tutorC, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion)
VALUES
-- Cloud - Tifa (Combate estratégico)
(1, 1, 1, 10.0, 'No tengo nada que decir, parece un francotirador solo que usando una espada a base de estocadas.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 1, 2, 9.5, 'Manejo impecable del equipo.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 1, 3, 7.0, 'Es algo callado, pero le queremos mucho.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 1, 4, 5.5, 'Debe hablar un poco más.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 1, 5, 8.0, 'Cumple los horarios.', '2025-04-11', CURRENT_TIMESTAMP),
(1, 1, 6, 8.5, 'Informes bien redactados aunque concisos.', '2025-04-11', CURRENT_TIMESTAMP),

-- Aerith - Tifa
(2, 1, 1, 9.5, 'Atina a todos los enemigos de la central con los hechizos', '2025-04-13', CURRENT_TIMESTAMP),
(2, 1, 2, 10.0, 'Su control con la bara son brillantes como su control con los protocolos energéticos.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 1, 3, 10.0, 'Muy responsable.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 1, 4, 10.0, 'Muy querida en el equipo asignado.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 1, 5, 9.5, 'Nada malo que decir.', '2025-04-13', CURRENT_TIMESTAMP),
(2, 1, 6, 10, 'Informes impecables.', '2025-04-13', CURRENT_TIMESTAMP),

-- Yuffie - Barret (Explosivos y demoliciones)
(3, 2, 1, 8.0, 'Es un piojo loco pero eficaz.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 2, 2, 8.5, 'Muy bueno manejo elemental.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 2, 3, 5.0, 'Las incidencias las resuelve pero que de más la cara en el equipo..', '2025-04-29', CURRENT_TIMESTAMP),
(3, 2, 4, 6.0, 'Mucho corazón pero muy impulsiva.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 2, 5, 6.5, 'Puntualidad a mejorar.', '2025-04-29', CURRENT_TIMESTAMP),
(3, 2, 6, 7.0, 'Informes escritos en japonés andaluz, eso es nuevo.', '2025-04-29', CURRENT_TIMESTAMP),

-- Squall - Quistis (Preparación Seed)
(4, 3, 1, 10, 'La precisión con los disparos de su dable-pistola es impecable.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 3, 2, 8.0, 'Empuña muy bien el sable', '2025-06-06', CURRENT_TIMESTAMP),
(4, 3, 3, 10.0, 'Muy buen lider de equipo.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 3, 4, 10.0, 'Callado pero siempre al servicio del equipo', '2025-06-06', CURRENT_TIMESTAMP),
(4, 3, 5, 8.5, 'A veces se le nota triste pero cumple sus horas.', '2025-06-06', CURRENT_TIMESTAMP),
(4, 3, 6, 10.0, 'Informes bien redactados.', '2025-06-06', CURRENT_TIMESTAMP),

-- Zell - Quistis
(5, 3, 1, 8.0, 'Mete los derechachos muy bien.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 3, 2, 7.0, 'Le digo que utilice algún equipo para mayor facilidad pero prefiere ir a puñitos, aunque debo decir que no le va mal con ello.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 3, 3, 7.0, 'Impulsivo pero efectivo.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 3, 4, 8.0, 'Debe evitar enfadarse con facilidad con alumnos como Seifer.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 3, 5, 6.0, 'Cumple el horario.', '2025-05-22', CURRENT_TIMESTAMP),
(5, 3, 6, 5.5, 'Sus informes tienen faltas de ortografía pero en general están bien.', '2025-05-22', CURRENT_TIMESTAMP),

-- Selphie - Quistis
(6, 3, 1, 9.0, 'Las herramientas logísticas la maneja de escándalo.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 3, 2, 10.0, '¡Go ninja Go ninja Go!', '2025-04-30', CURRENT_TIMESTAMP),
(6, 3, 3, 10.0, 'Iniciativa de oro', '2025-04-30', CURRENT_TIMESTAMP),
(6, 3, 4, 9.5, 'El corazón del grupo.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 3, 5, 9.5, 'Responsabilidad y puntualidad ejemplares.', '2025-04-30', CURRENT_TIMESTAMP),
(6, 3, 6, 10.0, 'Informes sofisticados.', '2025-04-30', CURRENT_TIMESTAMP),


-- Irvine - Quistis
(7, 3, 1, 10.0, 'Se nota que ha visto "La trilogía del dólar".', '2025-05-03', CURRENT_TIMESTAMP),
(7, 3, 2, 9.0, 'Muy bueno con la escopeta.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 3, 3, 5.5, 'Se adapta correctamente a los cambios en las misiones.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 3, 4, 6.0, 'Muy abierto y espontáneo con el equipo, aunque coquetea demasiado con las chicas del grupo (incluido a mí).', '2025-05-03', CURRENT_TIMESTAMP),
(7, 3, 5, 5.5, 'A veces se retrasa.', '2025-05-03', CURRENT_TIMESTAMP),
(7, 3, 6, 10.0, 'Informes bien hechos pero me dejó un poema una de las veces.', '2025-05-03', CURRENT_TIMESTAMP),


-- Seifer - Quistis
(8, 3, 1, 9, 'Muy buena precisión.', '2025-05-10', CURRENT_TIMESTAMP),
(8, 3, 2, 8.5, 'Muy bueno con el sable.', '2025-05-10', CURRENT_TIMESTAMP),
(8, 3, 3, 7.0, 'Aporta ideas originales aunque no siempre bien enfocadas', '2025-05-10', CURRENT_TIMESTAMP),
(8, 3, 4, 5.5, 'Dificultades en cooperación grupal', '2025-05-10', CURRENT_TIMESTAMP),
(8, 3, 5, 5.0, 'Algún retraso.', '2025-05-10', CURRENT_TIMESTAMP),
(8, 3, 6, 5.5, 'Informes mejorables.', '2025-05-10', CURRENT_TIMESTAMP),


-- Yitán - Steiner (Coreografía de combate)
(9, 4, 1, 7.5, 'Golpes precisos.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 4, 2, 8.0, 'Parece que baila mientras lucha.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 4, 3, 8.0, 'Muy disciplinado.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 4, 4, 7.5, 'Se preocupa por el equipo y se lleva muy bien con todos.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 4, 5, 8.0, 'Horarios cumplidos.', '2025-03-20', CURRENT_TIMESTAMP),
(9, 4, 6, 8.0, 'Informes bien hechos.', '2025-03-20', CURRENT_TIMESTAMP),

-- Vivi - Freija (Danza burmeciana)
(10, 5, 1, 7.5, 'Buen control técnico aunque algo inseguro.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 2, 10.0, 'Mago de 10.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 3, 9.5, 'Se adapta bien a nuevas tareas.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 4, 9.0, 'Trato amable y humilde, ejemplo de mejora continua.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 5, 7.5, 'Cumple horarios con constancia.Resuelve pequeños fallos con ayuda mínima.', '2025-04-25', CURRENT_TIMESTAMP),
(10, 5, 6, 9.5, 'Informes impecables', '2025-04-25', CURRENT_TIMESTAMP),

-- Eiko - Quina (Curso de cocina)
(11, 6, 1, 8.0, 'Aún en proceso de dominio técnico, aprende rápido, ñam.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 6, 2, 9.0, 'Muy buena curandera, ñam.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 6, 3, 8.0, 'Actitud responsable y proactiva aunque torpona, ñam.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 6, 4, 9.5, 'Muy social con el equipo, ñam.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 6, 5, 8.5, 'Horarios cumplidos, ñam.', '2025-05-07', CURRENT_TIMESTAMP),
(11, 6, 6, 9.5, 'Informes muy bien hechos, ñam.', '2025-05-07', CURRENT_TIMESTAMP),

-- Amarant - Quina
(12, 6, 1, 6.0, 'Es muy buen luchador pero muy solitario, ñam.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 6, 2, 7.5, 'Usa bien el equipo pero no es muy flexible, ñam.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 6, 3, 6.0, 'Cumple sus tareas pero aislado, ñam', '2025-05-12', CURRENT_TIMESTAMP),
(12, 6, 4, 4.5, 'Debe mejorar su interacción social, ñam.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 6, 5, 8.0, 'Cumple los horarios, ñam.', '2025-05-12', CURRENT_TIMESTAMP),
(12, 6, 6, 7.5, 'Informes bien hechos, ñam.', '2025-05-12', CURRENT_TIMESTAMP);
