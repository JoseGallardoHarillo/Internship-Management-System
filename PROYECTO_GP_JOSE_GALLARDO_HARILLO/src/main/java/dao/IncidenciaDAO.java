package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import modelo.Estado;
import modelo.Incidencia;
import modelo.Tipo;

public class IncidenciaDAO {

    public void addIncidencia(long id_alumno, long id_curso, long id_tutorP, Date fecha, Tipo tipo, String descripcion, String resolucion, Estado estado, Timestamp fechaCreacion, Timestamp fechaResolucion) throws Exception {
        
        String sql = "INSERT INTO incidencia(id_alumno, id_curso, id_tutorP, fecha, tipo, descripcion, resolucion, estado, fechaCreacion, fechaResolucion) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setLong(1, id_alumno);
        	ps.setLong(2, id_curso);
        	ps.setLong(3, id_tutorP);
        	ps.setDate(4, fecha);
        	ps.setString(5, tipo.name());
        	ps.setString(6, descripcion);
        	ps.setString(7, resolucion);
        	ps.setString(8, estado.name());
        	ps.setTimestamp(9, fechaCreacion);
        	ps.setTimestamp(10, fechaResolucion);

            ps.executeUpdate();
        }
    }
    
    public List<Incidencia> getAllIncidencias() throws Exception {
        
    	List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencia";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
            	Incidencia c = new Incidencia(
            			rs.getLong("id_incidencia"),
            			rs.getLong("id_alumno"),
            			rs.getLong("id_curso"),
            			rs.getLong("id_tutorP"),
                		rs.getDate("fecha"),
                		Tipo.valueOf(rs.getString("tipo")),
                		rs.getString("descripcion"),
                		rs.getString("resolucion"),
                		Estado.valueOf(rs.getString("estado")),
                		rs.getTimestamp("fechaCreacion"),
                		rs.getTimestamp("fechaResolucion")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }


    public Incidencia getIncidenciaById(long id) throws Exception {
        String sql = "SELECT * FROM incidencia WHERE id_incidencia=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);   // sustituimos el ? por el id
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Incidencia(
            			rs.getLong("id_incidencia"),
            			rs.getLong("id_alumno"),
            			rs.getLong("id_curso"),
            			rs.getLong("id_tutorP"),
                		rs.getDate("fecha"),
                		Tipo.valueOf(rs.getString("tipo")),
                		rs.getString("descripcion"),
                		rs.getString("resolucion"),
                		Estado.valueOf(rs.getString("estado")),
                		rs.getTimestamp("fechaCreacion"),
                		rs.getTimestamp("fechaResolucion")
                );
            }
        }
        return null;
    }


    public void updateIncidencia(long id, long id_alumno, long id_curso, long id_tutorP, Date fecha, Tipo tipo, String descripcion, String resolucion, Estado estado) throws Exception {
        String sql = "UPDATE incidencia SET id_alumno=?, id_curso=?, id_tutorP=?, fecha=?, tipo=?, descripcion=?, resolucion=?, estado=?  WHERE id_incidencia=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setLong(1, id_alumno);
        	ps.setLong(2, id_curso);
        	ps.setLong(3, id_tutorP);
        	ps.setDate(4, fecha);
            ps.setString(5, tipo.name());
            ps.setString(6, descripcion);
            ps.setString(7, resolucion);
            ps.setString(8, estado.name());
            ps.setLong(9, id);
            
            ps.executeUpdate();
        }
    }

    
    public void resolveIncidencia(long id, String resolucion) throws Exception {
    	String sql = "UPDATE incidencia SET estado = 'RESUELTA', resolucion = ?, fechaResolucion = NOW() WHERE id_incidencia=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, resolucion);
        	ps.setLong(2, id);
            ps.executeUpdate();
        }
    }
    
    public List<Incidencia> getIncidenciasByAlumno(long id_alumno) throws Exception {
        
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencia WHERE id_alumno = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	Incidencia c = new Incidencia(
            			rs.getLong("id_incidencia"),
            			rs.getLong("id_alumno"),
            			rs.getLong("id_curso"),
            			rs.getLong("id_tutorP"),
                		rs.getDate("fecha"),
                		Tipo.valueOf(rs.getString("tipo")),
                		rs.getString("descripcion"),
                		rs.getString("resolucion"),
                		Estado.valueOf(rs.getString("estado")),
                		rs.getTimestamp("fechaCreacion"),
                		rs.getTimestamp("fechaResolucion")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }
}
