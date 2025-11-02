package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.Estadistica;

public class EstadisticaDAO {

    //Total de usuarios por rol
    public List<Estadistica> getUsuariosPorRol() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT rol, COUNT(*) AS total FROM usuario GROUP BY rol";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(rs.getString("rol"), rs.getInt("total")));
            }
        }
        return lista;
    }

    //Total de alumnos por curso
    public List<Estadistica> getAlumnosPorCurso() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT c.nombre AS curso, COUNT(a.id_alumno) AS total " +
                     "FROM curso c LEFT JOIN alumno a ON c.id_curso = a.id_curso " +
                     "GROUP BY c.id_curso";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(rs.getString("curso"), rs.getInt("total")));
            }
        }
        return lista;
    }

    //Promedio de puntuación por capacidad de evaluación (tutor curso)
    public List<Estadistica> getPromedioEvaluacionesTutorC() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT cap.nombre AS capacidad, AVG(e.puntuacion) AS promedio " +
                     "FROM evaluaciontutorcurso e " +
                     "JOIN capacidadevaluacion cap ON e.id_capacidad = cap.id_capacidad " +
                     "GROUP BY cap.id_capacidad";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(rs.getString("capacidad"), rs.getDouble("promedio")));
            }
        }
        return lista;
    }

    //Promedio de puntuación por capacidad de evaluación (tutor prácticas)
    public List<Estadistica> getPromedioEvaluacionesTutorP() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT cap.nombre AS capacidad, AVG(e.puntuacion) AS promedio " +
                     "FROM evaluaciontutorpracticas e " +
                     "JOIN capacidadevaluacion cap ON e.id_capacidad = cap.id_capacidad " +
                     "GROUP BY cap.id_capacidad";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(rs.getString("capacidad"), rs.getDouble("promedio")));
            }
        }
        return lista;
    }

 // Total de evaluaciones del tutor de curso por alumno
    public List<Estadistica> getEvaluacionesTutorCursoPorAlumno() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT CONCAT(u.nombre, ' ', COALESCE(u.apellidos, '')) AS alumno, " +
                     "COUNT(DISTINCT e.id_evaluacionTC) AS total " +
                     "FROM usuario u " +
                     "LEFT JOIN alumno a ON u.id_usuario = a.id_usuario " +
                     "LEFT JOIN evaluaciontutorcurso e ON a.id_alumno = e.id_alumno " +
                     "WHERE u.rol = 'ALUMNO' " +
                     "GROUP BY u.id_usuario, u.nombre, u.apellidos " +
                     "ORDER BY alumno ASC";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombreAlumno = rs.getString("alumno").trim();
                double total = rs.getDouble("total");
                lista.add(new Estadistica(nombreAlumno, total));
            }
        }
        return lista;
    }

    // Total de evaluaciones del tutor de prácticas por alumno
    public List<Estadistica> getEvaluacionesTutorPracticasPorAlumno() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT CONCAT(u.nombre, ' ', COALESCE(u.apellidos, '')) AS alumno, " +
                     "COUNT(DISTINCT ep.id_evaluacion) AS total " +
                     "FROM usuario u " +
                     "LEFT JOIN alumno a ON u.id_usuario = a.id_usuario " +
                     "LEFT JOIN evaluaciontutorpracticas ep ON a.id_alumno = ep.id_alumno " +
                     "WHERE u.rol = 'ALUMNO' " +
                     "GROUP BY u.id_usuario, u.nombre, u.apellidos " +
                     "ORDER BY alumno ASC";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombreAlumno = rs.getString("alumno").trim();
                double total = rs.getDouble("total");
                lista.add(new Estadistica(nombreAlumno, total));
            }
        }
        return lista;
    }

    //Distribución de alumnos por tutor de curso
    public List<Estadistica> getAlumnosPorTutorCurso() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT CONCAT(u.nombre,' ', COALESCE(u.apellidos, '')) AS tutor, COUNT(a.id_alumno) AS total " +
                     "FROM tutorcurso t " +
                     "LEFT JOIN usuario u ON t.id_usuario = u.id_usuario " +
                     "LEFT JOIN curso c ON c.id_tutorC = t.id_tutorC " +
                     "LEFT JOIN alumno a ON a.id_curso = c.id_curso " +
                     "GROUP BY t.id_tutorC";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(rs.getString("tutor"), rs.getInt("total")));
            }
        }
        return lista;
    }

    //Distribución de alumnos por tutor de prácticas
    public List<Estadistica> getAlumnosPorTutorPracticas() throws Exception {
        List<Estadistica> lista = new ArrayList<>();
        String sql = "SELECT CONCAT(u.nombre,' ', COALESCE(u.apellidos, '')) AS tutor, COUNT(a.id_alumno) AS total " +
                     "FROM tutorpracticas t " +
                     "LEFT JOIN usuario u ON t.id_usuario = u.id_usuario " +
                     "LEFT JOIN alumno a ON a.id_tutorP = t.id_tutorP " +
                     "GROUP BY t.id_tutorP";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estadistica(rs.getString("tutor"), rs.getInt("total")));
            }
        }
        return lista;
    }
}