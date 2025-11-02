package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import modelo.ObservacionDiaria;

public class ObservacionDAO {

    public void addObservacion(long id_alumno, Date fecha, String actividades, String explicaciones, String observacionesAlumno, String observacionesTutor, int horasRealizadas, Timestamp fechaCreacion) throws Exception {
        
        String sql = "INSERT INTO observaciondiaria(id_alumno, fecha, actividades, explicaciones, observacionesAlumno, observacionesTutor, horasRealizadas, fechaCreacion) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setLong(1, id_alumno);
        	ps.setDate(2, fecha);
        	ps.setString(3, actividades);
        	ps.setString(4, explicaciones);
            ps.setString(5, observacionesAlumno);
            ps.setString(6, observacionesTutor);
            ps.setInt(7, horasRealizadas);
            ps.setTimestamp(8, fechaCreacion);

            ps.executeUpdate();
        }
    }
    
    public List<ObservacionDiaria> getAllObservaciones() throws Exception {
        
    	List<ObservacionDiaria> lista = new ArrayList<>();
        String sql = "SELECT * FROM observaciondiaria";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
            	ObservacionDiaria c = new ObservacionDiaria(
            			rs.getLong("id_observacion"),
                    	rs.getLong("id_alumno"),
                    	rs.getDate("fecha"),
                    	rs.getString("actividades"),
                    	rs.getString("explicaciones"),
                        rs.getString("observacionesAlumno"),
                        rs.getString("observacionesTutor"),
                        rs.getInt("horasRealizadas"),
                        rs.getTimestamp("fechaCreacion")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }


    public ObservacionDiaria getObservacionById(long id) throws Exception {
        String sql = "SELECT * FROM observaciondiaria WHERE id_observacion=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);   // sustituimos el ? por el id
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ObservacionDiaria(
            			rs.getLong("id_observacion"),
                    	rs.getLong("id_alumno"),
                    	rs.getDate("fecha"),
                    	rs.getString("actividades"),
                    	rs.getString("explicaciones"),
                        rs.getString("observacionesAlumno"),
                        rs.getString("observacionesTutor"),
                        rs.getInt("horasRealizadas"),
                        rs.getTimestamp("fechaCreacion")
                );
            }
        }
        return null;
    }


    public void updateObservacion(long id_observacion, Date fecha, String actividades, String explicaciones, String observacionesAlumno, String observacionesTutor, int horasRealizadas) throws Exception {
        String sql = "UPDATE observaciondiaria SET fecha=?, actividades=?, explicaciones=?, observacionesAlumno=?, observacionesTutor=?, horasRealizadas=?  WHERE id_observacion=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setDate(1, fecha);
        	ps.setString(2, actividades);
        	ps.setString(3, explicaciones);
            ps.setString(4, observacionesAlumno);
            ps.setString(5, observacionesTutor);
            ps.setInt(6, horasRealizadas);
            ps.setLong(7, id_observacion);
            ps.executeUpdate();
        }
    }

    
    public void deleteObservacion(long id) throws Exception {
        String sql = "DELETE FROM observaciondiaria WHERE id_observacion=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    public List<ObservacionDiaria> getObservacionesByAlumno(long id_alumno) throws Exception {
        
        List<ObservacionDiaria> lista = new ArrayList<>();
        String sql = "SELECT * FROM observaciondiaria WHERE id_alumno = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	ObservacionDiaria c = new ObservacionDiaria(
            			rs.getLong("id_observacion"),
                    	rs.getLong("id_alumno"),
                    	rs.getDate("fecha"),
                    	rs.getString("actividades"),
                    	rs.getString("explicaciones"),
                        rs.getString("observacionesAlumno"),
                        rs.getString("observacionesTutor"),
                        rs.getInt("horasRealizadas"),
                        rs.getTimestamp("fechaCreacion")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }
}
