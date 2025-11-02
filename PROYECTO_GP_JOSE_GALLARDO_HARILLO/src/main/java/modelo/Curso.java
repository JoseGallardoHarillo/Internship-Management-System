package modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class Curso {
	private long id_curso;
	private String nombre;
	private String descripcion;
	private int duracion;
	private Date fechaInicio;
	private Date fechaFin;
	private boolean activo;
	private Timestamp fechaCreacion;
	
	private long id_tutorC;

	public Curso(long id_curso, String nombre, String descripcion, int duracion, Date fechaInicio, Date fechaFin,
			boolean activo, Timestamp fechaCreacion, long id_tutorC) {
		super();
		this.id_curso = id_curso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.id_tutorC = id_tutorC;
	}

	public long getId_curso() {
		return id_curso;
	}

	public void setId_curso(long id_curso) {
		this.id_curso = id_curso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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

	public long getTutorC() {
		return id_tutorC;
	}

	public void setTutorC(long tutorC) {
		this.id_tutorC = tutorC;
	}
	
    public String getActivoSN() {
    	if(activo == true) return "Si ✅";
    	else return "No ❌";
    }
	
	
}
