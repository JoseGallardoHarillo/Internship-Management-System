package modelo;

import java.sql.Timestamp;

public class Empresa {
	private long id_empresa;
	private String nombre;
	private String cif;
	private String direccion;
	private String telefono;
	private String email;
	private String personaContacto;
	private String sector;
	private boolean activo;
	private Timestamp fechaCreacion;
	
	public Empresa(long id_empresa, String nombre, String cif, String direccion, String telefono, String email,
			String personaContacto, String sector, boolean activo, Timestamp fechaCreacion) {
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.cif = cif;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.personaContacto = personaContacto;
		this.sector = sector;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
	}

	public long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersonaContacto() {
		return personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
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
