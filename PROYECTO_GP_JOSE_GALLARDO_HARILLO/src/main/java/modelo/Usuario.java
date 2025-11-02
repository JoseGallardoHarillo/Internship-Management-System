package modelo;

import java.sql.Timestamp;

public class Usuario {
    private long id_usuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String password;
    private Rol rol;
    private String telefono;
    private boolean activo;
    private Timestamp ultimoAcceso;
    private Timestamp fechaCreacion;

	public Usuario(long id_usuario, String nombre, String apellidos, String dni, String email, String password, Rol rol,
			String telefono, boolean activo, Timestamp ultimoAcceso, Timestamp fechaCreacion) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.telefono = telefono;
		this.activo = activo;
		this.ultimoAcceso = ultimoAcceso;
		this.fechaCreacion = fechaCreacion;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Timestamp getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(Timestamp ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getActivoSN() {
    	if(activo == true) return "Si ✅";
    	else return "No ❌";
    }
}
