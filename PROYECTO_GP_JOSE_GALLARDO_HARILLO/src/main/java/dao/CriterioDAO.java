package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.CriterioEvaluacion;

public class CriterioDAO {

    public void addCriterio(String nombre, String descripcion, double peso) throws Exception {
        
        String sql = "INSERT INTO criterioevaluacion(nombre, descripcion, peso) VALUES (?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setString(1, nombre);
        	ps.setString(2, descripcion);
        	ps.setDouble(3, peso);

            ps.executeUpdate();
        }
    }
    
    public List<CriterioEvaluacion> getAllCriterios() throws Exception {
        
    	List<CriterioEvaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM criterioevaluacion";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
            	CriterioEvaluacion c = new CriterioEvaluacion(
                		rs.getLong("id_criterio"),
                		rs.getString("nombre"),
                		rs.getString("descripcion"),
                		rs.getDouble("peso"),
                		rs.getBoolean("activo")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }


    public CriterioEvaluacion getCriterioById(long id) throws Exception {
        String sql = "SELECT * FROM criterioevaluacion WHERE id_criterio=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);   // sustituimos el ? por el id
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CriterioEvaluacion(
                		rs.getLong("id_criterio"),
                		rs.getString("nombre"),
                		rs.getString("descripcion"),
                		rs.getDouble("peso"),
                        rs.getBoolean("activo")
                );
            }
        }
        return null;
    }


    public void updateCriterio(long id, String nombre, String descripcion, double peso) throws Exception {
        String sql = "UPDATE criterioevaluacion SET nombre=?, descripcion=?, peso=?  WHERE id_criterio=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, nombre);
        	ps.setString(2, descripcion);
        	ps.setDouble(3, peso);
            ps.setLong(4, id);
            
            ps.executeUpdate();
        }
    }

    
    public void deleteCriterio(long id) throws Exception {
        String sql = "UPDATE criterioevaluacion SET activo = false WHERE id_criterio=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    public void activateCriterio(long id) throws Exception {
        String sql = "UPDATE criterioevaluacion SET activo = true WHERE id_criterio=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
