package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import dto.CursoDTO;


public class CursoDAO {
	
	//ACLARACION: USAMOS UNA CLASE DTO PARA LA MEJOR GESTION DE LOS ATRIBUTOS A MOSTRAR, SIENDO EN ESTE CASO UN MIX DE CURSO Y SU TUTOR ASIGNADO
	
    public void addCurso(String nombre, String descripcion, int duracion, Date fechaInicio, Date fechaFin, Timestamp fechaCreacion, long id_tutor) throws Exception {
        
        String sql = "INSERT INTO curso(nombre, descripcion, duracion, fechaInicio, fechaFin, fechaCreacion, id_tutorC) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	// Sustituimos el ? por el valor real
        	ps.setString(1, nombre);
        	ps.setString(2, descripcion);
        	ps.setInt(3, duracion);
        	ps.setDate(4, fechaInicio);
            ps.setDate(5, fechaFin);
            ps.setTimestamp(6, fechaCreacion);
            ps.setLong(7, id_tutor);

            // Ejecutamos la inserción
            ps.executeUpdate();
        }
    }

	public List<CursoDTO> getAllCursosDTO() throws Exception {
	    List<CursoDTO> lista = new ArrayList<>();

	    String sql = "SELECT " +
	            "c.id_curso AS c_id, c.nombre AS c_nombre, c.descripcion AS c_desc, c.duracion AS c_dur, " +
	            "c.fechaInicio AS c_fechaInicio, c.fechaFin AS c_fechaFin, c.activo AS c_activo, c.fechaCreacion AS c_fechaCreacion, " +
	            "t.id_tutorC AS t_id, t.id_usuario AS t_id_usuario, " +
	            "u.id_usuario AS u_id, u.nombre AS u_nombre, u.apellidos AS u_apellidos " +
	            "FROM curso c " +
	            "LEFT JOIN tutorcurso t ON c.id_tutorC = t.id_tutorC " +
	            "LEFT JOIN usuario u ON t.id_usuario = u.id_usuario";

	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            CursoDTO cursoDTO = new CursoDTO(
	                    rs.getLong("c_id"),
	                    rs.getString("c_nombre"),
	                    rs.getString("c_desc"),
	                    rs.getInt("c_dur"),
	                    rs.getDate("c_fechaInicio"),
	                    rs.getDate("c_fechaFin"),
	                    rs.getBoolean("c_activo"),
	                    rs.getTimestamp("c_fechaCreacion"),
	                    rs.getLong("t_id"),
	                    rs.getString("u_nombre"),
	                    rs.getString("u_apellidos")
	            );

	            lista.add(cursoDTO);
	        }
	    }

	    return lista;
	}

	public CursoDTO getCursoDTOById(long id) throws Exception {
	    String sql = "SELECT " +
	            "c.id_curso AS c_id, c.nombre AS c_nombre, c.descripcion AS c_desc, c.duracion AS c_dur, " +
	            "c.fechaInicio AS c_fechaInicio, c.fechaFin AS c_fechaFin, c.activo AS c_activo, c.fechaCreacion AS c_fechaCreacion, " +
	            "t.id_tutorC AS t_id, t.id_usuario AS t_id_usuario, " +
	            "u.id_usuario AS u_id, u.nombre AS u_nombre, u.apellidos AS u_apellidos " +
	            "FROM curso c " +
	            "LEFT JOIN tutorcurso t ON c.id_tutorC = t.id_tutorC " +
	            "LEFT JOIN usuario u ON t.id_usuario = u.id_usuario " +
	            "WHERE c.id_curso = ?";

	    try (Connection con = ConexionDB.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            return new CursoDTO(
	                    rs.getLong("c_id"),
	                    rs.getString("c_nombre"),
	                    rs.getString("c_desc"),
	                    rs.getInt("c_dur"),
	                    rs.getDate("c_fechaInicio"),
	                    rs.getDate("c_fechaFin"),
	                    rs.getBoolean("c_activo"),
	                    rs.getTimestamp("c_fechaCreacion"),
	                    rs.getLong("t_id"),
	                    rs.getString("u_nombre"),
	                    rs.getString("u_apellidos")
	            );
	        }
	    }

	    return null;
	}

	public void updateCurso(long id, String nombre, String descripcion, int duracion, 
            Date fechaInicio, Date fechaFin, long id_tutor) throws Exception {

	String sql = "UPDATE curso SET nombre=?, descripcion=?, duracion=?, fechaInicio=?, fechaFin=?, id_tutorC=? WHERE id_curso=?";
		
		try (Connection con = ConexionDB.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
		
			ps.setString(1, nombre);
			ps.setString(2, descripcion);
			ps.setInt(3, duracion);
			ps.setDate(4, fechaInicio);
			ps.setDate(5, fechaFin);
			ps.setLong(6, id_tutor);
			ps.setLong(7, id);
			
			ps.executeUpdate();
		}
	}

    
    public void deleteCurso(long id) throws Exception {
        String sql = "UPDATE Curso SET activo = false WHERE id_curso=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    public void activateCurso(long id) throws Exception {
        String sql = "UPDATE Curso SET activo = true WHERE id_curso=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

}
