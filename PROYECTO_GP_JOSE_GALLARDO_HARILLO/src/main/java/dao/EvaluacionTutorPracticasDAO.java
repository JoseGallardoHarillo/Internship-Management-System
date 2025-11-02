package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.EvaluacionTutorP;

public class EvaluacionTutorPracticasDAO {

    public void addEvaluacionTutorP(long id_alumno, long id_tutorP, long id_capacidad,
                                   double puntuacion, String observaciones, Date fecha, Timestamp fechaCreacion) throws Exception {
        
        String sql = "INSERT INTO evaluaciontutorpracticas (id_alumno, id_tutorP, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion) VALUES (?,?,?,?,?,?,?)";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id_alumno);
            ps.setLong(2, id_tutorP);
            ps.setLong(3, id_capacidad);
            ps.setDouble(4, puntuacion);
            ps.setString(5, observaciones);
            ps.setDate(6, fecha);
            ps.setTimestamp(7, fechaCreacion);

            ps.executeUpdate();
        }
    }

    // 🧩 SELECT ALL
    public List<EvaluacionTutorP> getAllEvaluacionesTutorP() throws Exception {
        List<EvaluacionTutorP> lista = new ArrayList<>();
        String sql = "SELECT * FROM evaluaciontutorpracticas";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                EvaluacionTutorP e = new EvaluacionTutorP(
                		rs.getLong("id_alumno"),
                		rs.getLong("id_capacidad"),
                        rs.getDouble("puntuacion"),
                        rs.getString("observaciones"),
                        rs.getDate("fecha"),
                        rs.getTimestamp("fechaCreacion"),
                        rs.getLong("id_evaluacion"),
                        rs.getLong("id_tutorP")
                );
                lista.add(e);
            }
        }
        return lista;
    }

    public EvaluacionTutorP getEvaluacionTutorPById(long id_evaluacion) throws Exception {
        String sql = "SELECT * FROM evaluaciontutorpracticas WHERE id_evaluacion = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id_evaluacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EvaluacionTutorP(
                		rs.getLong("id_alumno"),
                        rs.getLong("id_capacidad"),
                        rs.getDouble("puntuacion"),
                        rs.getString("observaciones"),
                        rs.getDate("fecha"),
                        rs.getTimestamp("fechaCreacion"),
                        rs.getLong("id_evaluacion"),
                        rs.getLong("id_tutorP")
                );
            }
        }
        return null;
    }

    public void updateEvaluacionTutorP(long id_evaluacion, long id_capacidad, double puntuacion,
                                       String observaciones, Date fecha) throws Exception {
        String sql = "UPDATE evaluaciontutorpracticas SET id_capacidad =?, puntuacion=?, observaciones=?, fecha=? WHERE id_evaluacion=?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setLong(1, id_capacidad);
            ps.setDouble(2, puntuacion);
            ps.setString(3, observaciones);
            ps.setDate(4, fecha);
            ps.setLong(5, id_evaluacion);

            ps.executeUpdate();
        }
    }

    public void deleteEvaluacionTutorP(long id_evaluacion) throws Exception {
        String sql = "DELETE FROM evaluaciontutorpracticas WHERE id_evaluacion = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id_evaluacion);
            ps.executeUpdate();
        }
    }

    public List<EvaluacionTutorP> getEvaluacionesTutorPyAlumno(long id_alumno) throws Exception {
        List<EvaluacionTutorP> lista = new ArrayList<>();
        String sql = "SELECT * FROM evaluaciontutorpracticas WHERE id_alumno = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EvaluacionTutorP e = new EvaluacionTutorP(
                		rs.getLong("id_alumno"),
                        rs.getLong("id_capacidad"),
                        rs.getDouble("puntuacion"),
                        rs.getString("observaciones"),
                        rs.getDate("fecha"),
                        rs.getTimestamp("fechaCreacion"),
                        rs.getLong("id_evaluacion"),
                        rs.getLong("id_tutorP")
                );
                lista.add(e);
            }
        }
        return lista;
    }
    
    public List<Long> getCapacidadesEvaluadasPorAlumno(long id_alumno) throws Exception {
        List<Long> capacidades = new ArrayList<>();
        String sql = "SELECT id_capacidad FROM evaluaciontutorpracticas WHERE id_alumno = ?";
        
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