package dao;

import modelo.Alumno;
import modelo.Rol;
import modelo.TutorCurso;
import modelo.TutorPracticas;
import modelo.Usuario;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import dto.AlumnoDTO;
import dto.TutorCursoDTO;
import dto.TutorPracticasDTO;


public class UsuarioDAO {


    public void addUsuario(String nombre, String apellidos, String dni, String email, String password, Rol rol, String telefono, Timestamp ultimoAcceso, Timestamp fechaCreacion) throws Exception {
        
        String sql = "INSERT INTO usuario(nombre, apellidos, dni, email, password, rol, telefono, ultimoAcceso, fechaCreacion) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setString(1, nombre);
        	ps.setString(2, apellidos);
        	ps.setString(3, dni);
        	ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, rol.name());
            ps.setString(7, telefono);
            ps.setTimestamp(8, ultimoAcceso);
            ps.setTimestamp(9, fechaCreacion);

            ps.executeUpdate();
        }
    }
    
    public long addUsuarioAndGetId(String nombre, String apellidos, String dni, String email, String password, Rol rol, String telefono, Timestamp ultimoAcceso, Timestamp fechaCreacion) throws Exception {
        
    	long generatedId = 0;
    	
        String sql = "INSERT INTO usuario(nombre, apellidos, dni, email, password, rol, telefono, ultimoAcceso, fechaCreacion) VALUES (?,?,?,?,?,?,?,?,?)";
        
        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
        	ps.setString(1, nombre);
        	ps.setString(2, apellidos);
        	ps.setString(3, dni);
        	ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, rol.name());
            ps.setString(7, telefono);
            ps.setTimestamp(8, ultimoAcceso);
            ps.setTimestamp(9, fechaCreacion);

            // Ejecutamos la inserción
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getLong(1);
                }
            }
        }
        
        return generatedId;
    }
    
    public List<Usuario> getAllUsuarios() throws Exception {
        
    	List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
            	Usuario c = new Usuario(
                		rs.getLong("id_usuario"),
                		rs.getString("nombre"),
                		rs.getString("apellidos"),
                		rs.getString("dni"),
                		rs.getString("email"),
                        rs.getString("password"),
                        Rol.valueOf(rs.getString("rol")),
                        rs.getString("telefono"),
                        rs.getBoolean("activo"),
                        rs.getTimestamp("ultimoAcceso"),
                        rs.getTimestamp("fechaCreacion")
                );
                
                lista.add(c);
            }
        }
        return lista;
    }


    public Usuario getUsuarioById(long id) throws Exception {
        String sql = "SELECT * FROM usuario WHERE id_usuario=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                		rs.getLong("id_usuario"),
                		rs.getString("nombre"),
                		rs.getString("apellidos"),
                		rs.getString("dni"),
                		rs.getString("email"),
                        rs.getString("password"),
                        Rol.valueOf(rs.getString("rol")),
                        rs.getString("telefono"),
                        rs.getBoolean("activo"),
                        rs.getTimestamp("ultimoAcceso"),
                        rs.getTimestamp("fechaCreacion")
                );
            }
        }
        return null; 
    }


    public void updateUsuario(long id, String nombre, String apellidos, String dni, String email, String password, String telefono) throws Exception {
        String sql = "UPDATE usuario SET nombre=?, apellidos=?, dni=?, email=?, password=?, telefono=?  WHERE id_usuario=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, nombre);
        	ps.setString(2, apellidos);
        	ps.setString(3, dni);
        	ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, telefono);
            ps.setLong(7, id);
            ps.executeUpdate();
        }
    }

    
    public void deleteUsuario(long id) throws Exception {
        String sql = "UPDATE usuario SET activo = false WHERE id_usuario=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    
    //---------------------------DTOs y roles---------------------------
    
    public void addAlumno(Date fechaNacimiento, int duracionPracticas, String horario, Date fechaInicio, Date fechaFin, long id_curso, long id_empresa, long id_tutorP, long id_usuario) throws Exception {
        
        String sql = "INSERT INTO alumno(fechaNacimiento, duracionPracticas, horario, fechaInicio, fechaFin, id_curso, id_empresa, id_tutorP, id_usuario) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setDate(1, fechaNacimiento);
        	ps.setInt(2, duracionPracticas);
        	ps.setString(3, horario);
        	ps.setDate(4, fechaInicio);
            ps.setDate(5, fechaFin);
            ps.setLong(6, id_curso);
            ps.setLong(7, id_empresa);
            ps.setLong(8, id_tutorP);
            ps.setLong(9, id_usuario);

            ps.executeUpdate();
        }
    }
    
    public void addTutorC(String especialidad, long id_usuario) throws Exception {
        
        String sql = "INSERT INTO tutorcurso(especialidad, id_usuario) VALUES (?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setString(1, especialidad);
        	ps.setLong(2, id_usuario);

            ps.executeUpdate();
        }
    }
    
    public void addTutorP(String cargo, String horario, long id_empresa, long id_usuario) throws Exception {
        
        String sql = "INSERT INTO tutorpracticas(cargo, horario, id_empresa, id_usuario) VALUES (?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             
        	PreparedStatement ps = con.prepareStatement(sql)) {
            
        	ps.setString(1, cargo);
        	ps.setString(2, horario);
            ps.setLong(3, id_empresa);
            ps.setLong(4, id_usuario);

            ps.executeUpdate();
        }
    }
    
    
    public AlumnoDTO getAlumnoDTOByUsuarioId(long id_usuario) throws Exception {
        String sql = "SELECT " +
                "a.id_alumno AS a_id, a.fechaNacimiento AS a_fechaNacimiento, a.duracionPracticas AS a_duracionPracticas, a.horario AS a_horario, " +
                "a.fechaInicio AS a_fechaInicio, a.fechaFin AS a_fechaFin, " +
                "c.id_curso AS c_id, c.nombre AS c_nombre, " +
                "e.id_empresa AS e_id, e.nombre AS e_nombre, " +
                "tp.id_tutorP AS tp_id, u_tutor.nombre AS tp_nombre, u_tutor.apellidos AS tp_apellidos, " +
                "u.id_usuario AS u_id, u.nombre AS u_nombre, u.apellidos AS u_apellidos, u.dni AS u_dni, u.email AS u_email, u.telefono AS u_telefono, u.password AS u_password " +
                "FROM alumno a " +
                "LEFT JOIN curso c ON c.id_curso = a.id_curso " +
                "LEFT JOIN empresa e ON e.id_empresa = a.id_empresa " +
                "LEFT JOIN tutorpracticas tp ON tp.id_tutorP = a.id_tutorP " +
                "LEFT JOIN usuario u_tutor ON u_tutor.id_usuario = tp.id_usuario " +
                "LEFT JOIN usuario u ON u.id_usuario = a.id_usuario " +
                "WHERE a.id_usuario = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new AlumnoDTO(
                        rs.getString("u_nombre"),
                        rs.getString("u_apellidos"),
                        rs.getString("u_dni"),
                        rs.getString("u_email"),
                        rs.getString("u_telefono"),
                        rs.getDate("a_fechaNacimiento"),
                        rs.getString("u_password"),
                        rs.getInt("a_duracionPracticas"),
                        rs.getString("a_horario"),
                        rs.getDate("a_fechaInicio"),
                        rs.getDate("a_fechaFin"),
                        rs.getString("c_nombre"),
                        rs.getString("e_nombre"),
                        rs.getString("tp_nombre"),
                        rs.getString("tp_apellidos")
                );
            }
        }
        return null;
    }

    public TutorCursoDTO getTutorCursoDTOByUsuarioId(long id_usuario) throws Exception {
        String sql = "SELECT " +
                "t.id_tutorC AS t_id, t.especialidad AS t_especialidad, " +
                "u.id_usuario AS u_id, u.nombre AS u_nombre, u.apellidos AS u_apellidos, u.dni AS u_dni, u.email AS u_email, u.telefono AS u_telefono, u.password AS u_password " +
                "FROM tutorcurso t " +
                "LEFT JOIN usuario u ON u.id_usuario = t.id_usuario " +
                "WHERE t.id_usuario = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TutorCursoDTO(
                        rs.getString("u_nombre"),
                        rs.getString("u_apellidos"),
                        rs.getString("u_dni"),
                        rs.getString("u_email"),
                        rs.getString("u_telefono"),
                        rs.getString("u_password"),
                        rs.getString("t_especialidad")
                );
            }
        }
        return null;
    }

    public TutorPracticasDTO getTutorPracticasDTOByUsuarioId(long id_usuario) throws Exception {
        String sql = "SELECT " +
                "tp.id_tutorP AS tp_id, tp.cargo AS tp_cargo, tp.horario AS tp_horario, " +
                "e.id_empresa AS e_id, e.nombre AS e_nombre, " +
                "u.id_usuario AS u_id, u.nombre AS u_nombre, u.apellidos AS u_apellidos, u.dni AS u_dni, u.email AS u_email, u.telefono AS u_telefono, u.password AS u_password " +
                "FROM tutorpracticas tp " +
                "LEFT JOIN empresa e ON e.id_empresa = tp.id_empresa " +
                "LEFT JOIN usuario u ON u.id_usuario = tp.id_usuario " +
                "WHERE tp.id_usuario = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TutorPracticasDTO(
                        rs.getString("u_nombre"),
                        rs.getString("u_apellidos"),
                        rs.getString("u_dni"),
                        rs.getString("u_email"),
                        rs.getString("u_telefono"),
                        rs.getString("u_password"),
                        rs.getString("tp_cargo"),
                        rs.getString("e_nombre"),
                        rs.getString("tp_horario")
                );
            }
        }
        return null;
    }
    public Alumno getAlumnoByUsuarioId(long id_usuario) throws Exception {
        String sql = "SELECT a.id_alumno, a.fechaNacimiento, a.duracionPracticas, a.horario, a.fechaInicio, a.fechaFin, a.id_curso, a.id_empresa, a.id_tutorP, a.id_usuario " +
                     "FROM alumno a " +
                     "WHERE a.id_usuario = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Alumno alumno = new Alumno(rs.getLong("id_alumno"), rs.getDate("fechaNacimiento"), rs.getInt("duracionPracticas")
                		, rs.getString("horario"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getLong("id_curso"), rs.getLong("id_empresa"), rs.getLong("id_tutorP"), rs.getLong("id_usuario"));
                return alumno;
            }
        }
        return null;
    }

    public TutorCurso getTutorCursoByUsuarioId(long id_usuario) throws Exception {
        String sql = "SELECT id_tutorC, especialidad, id_usuario FROM tutorcurso WHERE id_usuario = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TutorCurso tutorC = new TutorCurso(rs.getLong("id_tutorC"),rs.getString("especialidad"), rs.getLong("id_usuario"));
                
                return tutorC;
            }
        }
        return null;
    }

    public TutorPracticas getTutorPracticasByUsuarioId(long id_usuario) throws Exception {
        String sql = "SELECT id_tutorP, cargo, horario, id_empresa, id_usuario FROM tutorpracticas WHERE id_usuario = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TutorPracticas tutorP = new TutorPracticas(rs.getLong("id_tutorP"), rs.getString("cargo"), rs.getString("horario"), rs.getLong("id_empresa"), rs.getLong("id_usuario"));
                return tutorP;
            }
        }
        return null;
    }
    
    


    public void updateAlumno(long id, Date fechaNacimiento, int duracionPracticas, String horario, Date fechaInicio, Date fechaFin, long id_curso, long id_empresa, long id_tutorP) throws Exception {
        String sql = "UPDATE alumno SET fechaNacimiento=?, duracionPracticas=?, horario=?, fechaInicio=?, fechaFin=?, id_curso=?, id_empresa=?, id_tutorP=? WHERE id_alumno=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setDate(1, fechaNacimiento);
        	ps.setInt(2, duracionPracticas);
        	ps.setString(3, horario);
        	ps.setDate(4, fechaInicio);
            ps.setDate(5, fechaFin);
            ps.setLong(6, id_curso);
            ps.setLong(7, id_empresa);
            ps.setLong(8, id_tutorP);
            ps.setLong(9, id);
            ps.executeUpdate();
        }
    }
    
    public void updateTutorC(long id, String especialidad) throws Exception {
        String sql = "UPDATE tutorcurso SET especialidad=? WHERE id_tutorC=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, especialidad);
        	ps.setLong(2, id);
        	
            ps.executeUpdate();
        }
    }    
    
    public void updateTutorPracticas(long id, String cargo, String horario, long id_empresa) throws Exception {
        String sql = "UPDATE tutorpracticas SET cargo=?, horario=?, id_empresa=? WHERE id_tutorP=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, cargo);
        	ps.setString(2, horario);
        	ps.setLong(3, id_empresa);
            ps.setLong(4, id);
            
            ps.executeUpdate();
        }
    }
    
    public List<Usuario> getUsuariosByRol(Rol rol) throws Exception  {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE rol = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rol.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getLong("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Rol.valueOf(rs.getString("rol")),
                        rs.getString("telefono"),
                        rs.getBoolean("activo"),
                        rs.getTimestamp("ultimoAcceso"),
                        rs.getTimestamp("fechaCreacion")
                    );
                
                lista.add(u);
            }
        }
        return lista;
    }
    
    public Long getIdUsuarioByAlumnoId(long id_alumno) throws Exception {
        String sql = "SELECT id_usuario FROM alumno WHERE id_alumno = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getLong("id_usuario");
            }
        }
        
        return null;
    }
    
    
    
    public Usuario getUsuarioByEmail(String email) throws Exception {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getLong("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("dni"),
                    rs.getString("email"),
                    rs.getString("password"),
                    Rol.valueOf(rs.getString("rol")),
                    rs.getString("telefono"),
                    rs.getBoolean("activo"),
                    rs.getTimestamp("ultimoAcceso"),
                    rs.getTimestamp("fechaCreacion")
                );
            }
        }
        return null;
    }
    
    public TutorCurso getTutorCursoById(long id_tutorC) throws Exception {
        String sql = "SELECT id_tutorC, especialidad, id_usuario FROM tutorcurso WHERE id_tutorC = ?";
        
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id_tutorC);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new TutorCurso(
                    rs.getLong("id_tutorC"),
                    rs.getString("especialidad"),
                    rs.getLong("id_usuario")
                );
            }
        }
        return null; 
    }
 
    
    public TutorPracticas getTutorPracticasById(long id_tutorP) throws Exception {
        String sql = "SELECT id_tutorP, cargo, horario, id_empresa, id_usuario FROM tutorpracticas WHERE id_tutorP = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id_tutorP);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TutorPracticas(
                    rs.getLong("id_tutorP"),
                    rs.getString("cargo"),
                    rs.getString("horario"),
                    rs.getLong("id_empresa"),
                    rs.getLong("id_usuario")
                );
            }
        }
        return null; 
    }
    
    public void activateUsuario(long id) throws Exception {
        String sql = "UPDATE usuario SET activo = true WHERE id_usuario=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
