package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.EvaluacionTutorC;

public class EvaluacionTutorCursoDAO {

    public void addEvaluacionTutorC(long id_alumno, long id_tutorC, long id_capacidad,
                                   double puntuacion, String observaciones, Date fecha, Timestamp fechaCreacion) throws Exception {
        
        String sql = "INSERT INTO evaluaciontutorcurso (id_alumno, id_tutorC, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion) VALUES (?,?,?,?,?,?,?)";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id_alumno);
            ps.setLong(2, id_tutorC);
            ps.setLong(3, id_capacidad);
            ps.setDouble(4, puntuacion);
            ps.setString(5, observaciones);
            ps.setDate(6, fecha);
            ps.setTimestamp(7, fechaCreacion);

            ps.executeUpdate();
        }
    }

    public List<EvaluacionTutorC> getAllEvaluacionesTutorC() throws Exception {
        List<EvaluacionTutorC> lista = new ArrayList<>();
        String sql = "SELECT * FROM evaluaciontutorcurso";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                EvaluacionTutorC e = new EvaluacionTutorC(
                		rs.getLong("id_alumno"),
                		rs.getLong("id_capacidad"),
                        rs.getDouble("puntuacion"),
                        rs.getString("observaciones"),
                        rs.getDate("fecha"),
                        rs.getTimestamp("fechaCreacion"),
                        rs.getLong("id_evaluacionTC"),
                        rs.getLong("id_tutorC")
                );
                lista.add(e);
            }
        }
        return lista;
    }

    public EvaluacionTutorC getEvaluacionTutorCById(long id_evaluacionTC) throws Exception {
        String sql = "SELECT * FROM evaluaciontutorcurso WHERE id_evaluacionTC = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id_evaluacionTC);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EvaluacionTutorC(
                		rs.getLong("id_alumno"),
                        rs.getLong("id_capacidad"),
                        rs.getDouble("puntuacion"),
                        rs.getString("observaciones"),
                        rs.getDate("fecha"),
                        rs.getTimestamp("fechaCreacion"),
                        rs.getLong("id_evaluacionTC"),
                        rs.getLong("id_tutorC")
                );
            }
        }
        return null;
    }

    public void updateEvaluacionTutorC(long id_evaluacionTC, long id_capacidad, double puntuacion,
                                       String observaciones, Date fecha) throws Exception {
        String sql = "UPDATE evaluaciontutorcurso SET id_capacidad=?, puntuacion=?, observaciones=?, fecha=? WHERE id_evaluacionTC=?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setLong(1, id_capacidad);
            ps.setDouble(2, puntuacion);
            ps.setString(3, observaciones);
            ps.setDate(4, fecha);
            ps.setLong(5, id_evaluacionTC);

            ps.executeUpdate();
        }
    }

    public void deleteEvaluacionTutorC(long id_evaluacionTC) throws Exception {
        String sql = "DELETE FROM evaluaciontutorcurso WHERE id_evaluacionTC = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id_evaluacionTC);
            ps.executeUpdate();
        }
    }

    public List<EvaluacionTutorC> getEvaluacionesTutorCByAlumno(long id_alumno) throws Exception {
        List<EvaluacionTutorC> lista = new ArrayList<>();
        String sql = "SELECT * FROM evaluaciontutorcurso WHERE id_alumno = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EvaluacionTutorC e = new EvaluacionTutorC(
                		rs.getLong("id_alumno"),
                        rs.getLong("id_capacidad"),
                        rs.getDouble("puntuacion"),
                        rs.getString("observaciones"),
                        rs.getDate("fecha"),
                        rs.getTimestamp("fechaCreacion"),
                        rs.getLong("id_evaluacionTC"),
                        rs.getLong("id_tutorC")
                );
                lista.add(e);
            }
        }
        return lista;
    }
    
    
    public List<Long> getCapacidadesEvaluadasPorAlumno(long id_alumno) throws Exception {
        List<Long> capacidades = new ArrayList<>();
        String sql = "SELECT id_capacidad FROM evaluaciontutorcurso WHERE id_alumno = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_alumno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    capacidades.add(rs.getLong("id_capacidad"));
                }
            }
        }
        return capacidades;
    }
}