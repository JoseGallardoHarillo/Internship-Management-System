package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.Empresa;

public class EmpresaDAO {

    public void addEmpresa(String nombre, String cif, String direccion, String telefono, String email, String personaContacto, String sector, Timestamp fechaCreacion) throws Exception {
        
    	// SQL con parámetro ? para prevenir inyección SQL
        String sql = "INSERT INTO empresa(nombre, cif, direccion, telefono, email, personaContacto, sector, fechaCreacion) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	// Sustituimos el ? por el valor real
        	ps.setString(1, nombre);
        	ps.setString(2, cif);
        	ps.setString(3, direccion);
        	ps.setString(4, telefono);
            ps.setString(5, email);
            ps.setString(6, personaContacto);
            ps.setString(7, sector);
            ps.setTimestamp(8, fechaCreacion);

            ps.executeUpdate();
        }
    }
    
    public List<Empresa> getAllEmpresas() throws Exception {
        
    	List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Mientras haya filas en el resultado
            while (rs.next()) {
                
            	Empresa c = new Empresa(
                		rs.getLong("id_empresa"),
                		rs.getString("nombre"),
                		rs.getString("cif"),
                		rs.getString("direccion"),
                		rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("personaContacto"),
                        rs.getString("sector"),
                        rs.getBoolean("activo"),
                        rs.getTimestamp("fechaCreacion")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }


    public Empresa getEmpresaById(long id) throws Exception {
        String sql = "SELECT * FROM empresa WHERE id_empresa=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);  
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Empresa(
                		rs.getLong("id_empresa"),
                		rs.getString("nombre"),
                		rs.getString("cif"),
                		rs.getString("direccion"),
                		rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("personaContacto"),
                        rs.getString("sector"),
                        rs.getBoolean("activo"),
                        rs.getTimestamp("fechaCreacion")
                );
            }
        }
        return null; 
    }


    public void updateEmpresa(long id, String nombre, String cif, String direccion, String telefono, String email, String personaContacto, String sector) throws Exception {
        String sql = "UPDATE empresa SET nombre=?, cif=?, direccion=?, telefono=?, email=?, personaContacto=?, sector=?  WHERE id_empresa=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, nombre);
        	ps.setString(2, cif);
        	ps.setString(3, direccion);
        	ps.setString(4, telefono);
            ps.setString(5, email);
            ps.setString(6, personaContacto);
            ps.setString(7, sector);
            ps.setLong(8, id);
            ps.executeUpdate();
        }
    }

    
    public void deleteEmpresa(long id) throws Exception {
        String sql = "UPDATE empresa SET activo = false WHERE id_empresa=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    public boolean existeCIF(String cif) throws Exception {
        String sql = "SELECT COUNT(*) FROM empresa WHERE cif = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cif);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public Empresa findByCIF(String cif) throws Exception {
        String sql = "SELECT * FROM empresa WHERE cif = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cif);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empresa(
                            rs.getLong("id_empresa"),
                            rs.getString("nombre"),
                            rs.getString("cif"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getString("personaContacto"),
                            rs.getString("sector"),
                            rs.getBoolean("activo"),
                            rs.getTimestamp("fechaCreacion")
                    );
                }
            }
        }
        return null;
    }
    
    public void activateEmpresa(long id) throws Exception {
        String sql = "UPDATE empresa SET activo = true WHERE id_empresa=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
