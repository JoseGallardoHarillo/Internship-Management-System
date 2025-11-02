package modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class ObservacionDiaria {
	
	private long id_observacion;
	private long id_alumno;
	
	private Date fecha;
	private String actividades;
	private String explicaciones;
	private String observacionesAlumno;
	private String observacionesTutor;
	private int horasRealizadas;
	private Timestamp fechaCreacion;
	
	public ObservacionDiaria(long id_observacion, long id_alumno, Date fecha, String actividades, String explicaciones,
			String observacionesAlumno, String observacionesTutor, int horasRealizadas, Timestamp fechaCreacion) {
		this.id_observacion = id_observacion;
		this.id_alumno = id_alumno;
		this.fecha = fecha;
		this.actividades = actividades;
		this.explicaciones = explicaciones;
		this.observacionesAlumno = observacionesAlumno;
		this.observacionesTutor = observacionesTutor;
		this.horasRealizadas = horasRealizadas;
		this.fechaCreacion = fechaCreacion;
	}

	public long getId_observacion() {
		return id_observacion;
	}

	public void setId_observacion(long id_observacion) {
		this.id_observacion = id_observacion;
	}

	public long getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(long id_alumno) {
		this.id_alumno = id_alumno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getActividades() {
		return actividades;
	}

	public void setActividades(String actividades) {
		this.actividades = actividades;
	}

	public String getExplicaciones() {
		return explicaciones;
	}

	public void setExplicaciones(String explicaciones) {
		this.explicaciones = explicaciones;
	}

	public String getObservacionesAlumno() {
		return observacionesAlumno;
	}

	public void setObservacionesAlumno(String observacionesAlumno) {
		this.observacionesAlumno = observacionesAlumno;
	}

	public String getObservacionesTutor() {
		return observacionesTutor;
	}

	public void setObservacionesTutor(String observacionesTutor) {
		this.observacionesTutor = observacionesTutor;
	}

	public int getHorasRealizadas() {
		return horasRealizadas;
	}

	public void setHorasRealizadas(int horasRealizadas) {
		this.horasRealizadas = horasRealizadas;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
