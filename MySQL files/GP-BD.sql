-- Base de datos
DROP SCHEMA GP_BD; -- Comentar la primera vez
CREATE DATABASE GP_BD;
USE GP_BD;

-- Tablas:

-- 1. usuario

CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `dni` varchar(9) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` enum('ADMIN','TUTOR_CURSO','TUTOR_PRACTICAS','ALUMNO') NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `ultimoAcceso` timestamp NULL DEFAULT NULL,
  `fechaCreacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`)
 -- UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 2. criterios de evaluacion

CREATE TABLE `criterioevaluacion` (
  `id_criterio` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `peso` decimal(5,2) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_criterio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 3. capacidad de evaluación

CREATE TABLE `capacidadevaluacion` (
  `id_capacidad` bigint NOT NULL AUTO_INCREMENT,
  `id_criterio` bigint DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `puntuacionMaxima` int DEFAULT '10',
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_capacidad`),
  KEY `fk_capacidad_criterio` (`id_criterio`),
  CONSTRAINT `fk_capacidad_criterio` FOREIGN KEY (`id_criterio`) REFERENCES `criterioevaluacion` (`id_criterio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 4. empresa

CREATE TABLE `empresa` (
  `id_empresa` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `cif` varchar(12) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `personaContacto` varchar(100) NOT NULL,
  `sector` varchar(50) NOT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `fechaCreacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_empresa`),
  UNIQUE KEY `cif` (`cif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 5. tutor del curso

CREATE TABLE `tutorcurso` (
  `id_tutorC` bigint NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint NOT NULL,
  `especialidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_tutorC`),
  KEY `fk_tutorC_usuario` (`id_usuario`),
  CONSTRAINT `fk_tutorC_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 6. tutor de prácticas

CREATE TABLE `tutorpracticas` (
  `id_tutorP` bigint NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint DEFAULT NULL,
  `id_usuario` bigint NOT NULL,
  `cargo` varchar(100) DEFAULT NULL,
  `horario` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_tutorP`),
  KEY `fk_tutorP_empresa` (`id_empresa`),
  KEY `fk_tutorP_usuario` (`id_usuario`),
  CONSTRAINT `fk_tutorP_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`), 
  CONSTRAINT `fk_tutorP_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 7. curso

CREATE TABLE `curso` (
  `id_curso` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `id_tutorC` bigint DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `fechaCreacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_curso`),
  KEY `fk_curso_tutorC` (`id_tutorC`),
  CONSTRAINT `fk_curso_tutorC` FOREIGN KEY (`id_tutorC`) REFERENCES `tutorcurso` (`id_tutorC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 8. alumno

CREATE TABLE `alumno` (
  `id_alumno` bigint NOT NULL AUTO_INCREMENT,
  `fechaNacimiento` date NOT NULL,
  `duracionPracticas` int NOT NULL,
  `horario` varchar(200) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `id_curso` bigint DEFAULT NULL,
  `id_empresa` bigint DEFAULT NULL,
  `id_tutorP` bigint DEFAULT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_alumno`),
  KEY `fk_alumno_curso` (`id_curso`),
  KEY `fk_alumno_empresa` (`id_empresa`),
  KEY `fk_alumno_tutorP` (`id_tutorP`),
  KEY `fk_alumno_usuario` (`id_usuario`),
  CONSTRAINT `fk_alumno_curso` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id_curso`),
  CONSTRAINT `fk_alumno_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`),
  CONSTRAINT `fk_alumno_tutorP` FOREIGN KEY (`id_tutorP`) REFERENCES `tutorpracticas` (`id_tutorP`), 
  CONSTRAINT `fk_alumno_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 9. evaluacion del tutor de practicas

CREATE TABLE `evaluaciontutorpracticas` (
  `id_evaluacion` bigint NOT NULL AUTO_INCREMENT,
  `id_alumno` bigint DEFAULT NULL,
  `id_tutorP` bigint DEFAULT NULL,
  `id_capacidad` bigint DEFAULT NULL,
  `puntuacion` decimal(6,2) DEFAULT NULL,
  `observaciones` text,
  `fecha` date NOT NULL,
  `fechaCreacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_evaluacion`),
  KEY `fk_evaluacion_alumno` (`id_alumno`),
  KEY `fk_evaluacion_tutor` (`id_tutorP`),
  KEY `fk_evaluacion_capacidad` (`id_capacidad`),
  CONSTRAINT `fk_evaluacion_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`),
  CONSTRAINT `fk_evaluacion_capacidad` FOREIGN KEY (`id_capacidad`) REFERENCES `capacidadevaluacion` (`id_capacidad`),
  CONSTRAINT `fk_evaluacion_tutor` FOREIGN KEY (`id_tutorP`) REFERENCES `tutorpracticas` (`id_tutorP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 10. evaluacion del tutor de curso

CREATE TABLE `evaluaciontutorcurso` (
  `id_evaluacionTC` bigint NOT NULL AUTO_INCREMENT,
  `id_alumno` bigint NOT NULL,
  `id_tutorC` bigint NOT NULL,
  `id_capacidad` bigint NOT NULL,
  `puntuacion` decimal(6,2) DEFAULT NULL,
  `observaciones` text,
  `fecha` date NOT NULL,
  `fechaCreacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_evaluacionTC`),
  KEY `fk_evalTC_alumno` (`id_alumno`),
  KEY `fk_evalTC_tutorC` (`id_tutorC`),
  KEY `fk_evalTC_capacidad` (`id_capacidad`),
  CONSTRAINT `fk_evalTC_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE CASCADE,
  CONSTRAINT `fk_evalTC_tutorC` FOREIGN KEY (`id_tutorC`) REFERENCES `tutorcurso` (`id_tutorC`) ON DELETE CASCADE,
  CONSTRAINT `fk_evalTC_capacidad` FOREIGN KEY (`id_capacidad`) REFERENCES `capacidadevaluacion` (`id_capacidad`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 11. incidencia

CREATE TABLE `incidencia` (
  `id_incidencia` bigint NOT NULL AUTO_INCREMENT,
  `id_alumno` bigint DEFAULT NULL,
  `id_curso` bigint DEFAULT NULL,
  `id_tutorP` bigint DEFAULT NULL,
  `fecha` date NOT NULL,
  `tipo` enum('FALTA','RETRASO','PROBLEMA_ACTITUD','OTROS') NOT NULL,
  `descripcion` text NOT NULL,
  `resolucion` text,
  `estado` enum('ABIERTA','EN_PROCESO','RESUELTA') DEFAULT 'ABIERTA',
  `fechaCreacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fechaResolucion` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_incidencia`),
  KEY `fk_incidencia_alumno` (`id_alumno`),
  KEY `fk_incidencia_curso` (`id_curso`),
  KEY `fk_incidencia_tutorP` (`id_tutorP`),
  CONSTRAINT `fk_incidencia_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`),
  CONSTRAINT `fk_incidencia_curso` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id_curso`),
  CONSTRAINT `fk_incidencia_tutorP` FOREIGN KEY (`id_tutorP`) REFERENCES `tutorpracticas` (`id_tutorP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 12. observacion diaria

CREATE TABLE `observaciondiaria` (
  `id_observacion` bigint NOT NULL AUTO_INCREMENT,
  `id_alumno` bigint DEFAULT NULL,
  `fecha` date NOT NULL,
  `actividades` text,
  `explicaciones` text,
  `observacionesAlumno` text,
  `observacionesTutor` text,
  `horasRealizadas` int DEFAULT NULL,
  `fechaCreacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_observacion`),
  KEY `fk_observacion_alumno` (`id_alumno`),
  CONSTRAINT `fk_observacion_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE usuario 
MODIFY COLUMN ultimoAcceso TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

INSERT INTO usuario (nombre, apellidos, dni, email, password, rol, telefono, activo)
VALUES
('José', 'Gallardo Harillo', '2913243J', 'jose.gallardo@example.com', '1234', 'ADMIN', '600111222', TRUE);