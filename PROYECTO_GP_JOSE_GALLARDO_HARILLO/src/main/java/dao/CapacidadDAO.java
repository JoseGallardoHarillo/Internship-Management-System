package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.CapacidadEvaluacion;

public class CapacidadDAO {

    public void addCapacidad(long id_criterio, String nombre, String descripcion, int puntuacionMaxima) throws Exception {
        
        String sql = "INSERT INTO capacidadevaluacion(id_criterio, nombre, descripcion, puntuacionMaxima) VALUES (?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setLong(1, id_criterio);
        	ps.setString(2, nombre);
        	ps.setString(3, descripcion);
        	ps.setInt(4, puntuacionMaxima);

            ps.executeUpdate();
        }
    }
    
    public List<CapacidadEvaluacion> getAllCapacidades() throws Exception {
        
    	List<CapacidadEvaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM capacidadevaluacion";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
            	CapacidadEvaluacion c = new CapacidadEvaluacion(
            			rs.getLong("id_capacidad"),
                		rs.getLong("id_criterio"),
                		rs.getString("nombre"),
                		rs.getString("descripcion"),
                		rs.getInt("puntuacionMaxima"),
                		rs.getBoolean("activo")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }


    public CapacidadEvaluacion getCapacidadById(long id) throws Exception {
        String sql = "SELECT * FROM capacidadevaluacion WHERE id_capacidad=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);   // sustituimos el ? por el id
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CapacidadEvaluacion(
                		rs.getLong("id_capacidad"),
                		rs.getLong("id_criterio"),
                		rs.getString("nombre"),
                		rs.getString("descripcion"),
                		rs.getInt("puntuacionMaxima"),
                        rs.getBoolean("activo")
                );
            }
        }
        return null;
    }


    public void updateCapacidad(long id, long id_criterio, String nombre, String descripcion, int puntuacionMaxima) throws Exception {
        String sql = "UPDATE capacidadevaluacion SET id_criterio=?, nombre=?, descripcion=?, puntuacionMaxima=?  WHERE id_capacidad=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setLong(1, id_criterio);
        	ps.setString(2, nombre);
        	ps.setString(3, descripcion);
        	ps.setInt(4, puntuacionMaxima);
            ps.setLong(5, id);
            
            ps.executeUpdate();
        }
    }

    
    public void deleteCapacidad(long id) throws Exception {
        String sql = "UPDATE capacidadevaluacion SET activo = false WHERE id_capacidad=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    public List<CapacidadEvaluacion> getCapacidadesByCriterio(long id_criterio) throws Exception {
        
        List<CapacidadEvaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM capacidadevaluacion WHERE id_criterio = ? AND activo = 1";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id_criterio);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                CapacidadEvaluacion c = new CapacidadEvaluacion(
                    rs.getLong("id_capacidad"),
                    rs.getLong("id_criterio"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("puntuacionMaxima"),
                    rs.getBoolean("activo")
                );
                lista.add(c);
            }
        }
        return lista;
    }
}
