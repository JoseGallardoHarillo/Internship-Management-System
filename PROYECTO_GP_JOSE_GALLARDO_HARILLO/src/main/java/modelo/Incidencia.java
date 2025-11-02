package modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class Incidencia {
	
	private long id_incidencia;
	
	private long id_alumno;
	private long id_curso;
	private long id_tutorP;
	
	private Date fecha;
	private Tipo tipo;
	private String descripcion;
	private String resolucion;
	private Estado estado;
	
	private Timestamp fechaCreacion;
	private Timestamp fechaResolucion;
	public Incidencia(long id_incidencia, long id_alumno, long id_curso, long id_tutorP, Date fecha, Tipo tipo,
			String descripcion, String resolucion, Estado estado, Timestamp fechaCreacion, Timestamp fechaResolucion) {
		super();
		this.id_incidencia = id_incidencia;
		this.id_alumno = id_alumno;
		this.id_curso = id_curso;
		this.id_tutorP = id_tutorP;
		this.fecha = fecha;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.resolucion = resolucion;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaResolucion = fechaResolucion;
	}
	public long getId_incidencia() {
		return id_incidencia;
	}
	public void setId_incidencia(long id_incidencia) {
		this.id_incidencia = id_incidencia;
	}
	public long getId_alumno() {
		return id_alumno;
	}
	public void setId_alumno(long id_alumno) {
		this.id_alumno = id_alumno;
	}
	public long getId_curso() {
		return id_curso;
	}
	public void setId_curso(long id_curso) {
		this.id_curso = id_curso;
	}
	public long getId_tutorP() {
		return id_tutorP;
	}
	public void setId_tutorP(long id_tutorP) {
		this.id_tutorP = id_tutorP;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getResolucion() {
		return resolucion;
	}
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Timestamp getFechaResolucion() {
		return fechaResolucion;
	}
	public void setFechaResolucion(Timestamp fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}
	
	
	
	
	
}
