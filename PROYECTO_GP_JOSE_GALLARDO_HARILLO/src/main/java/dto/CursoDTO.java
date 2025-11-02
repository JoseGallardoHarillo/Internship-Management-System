package dto;

import java.sql.Date;
import java.sql.Timestamp;

import modelo.Curso;

public class CursoDTO extends Curso{
	
	private String nombreTutor;
	private String apellidosTutor;
	
	public CursoDTO(long id_curso, String nombre, String descripcion, int duracion, Date fechaInicio, Date fechaFin,
			boolean activo, Timestamp fechaCreacion, long id_tutorC, String nombreTutor, String apellidosTutor) {
		super(id_curso, nombre, descripcion, duracion, fechaInicio, fechaFin, activo, fechaCreacion, id_tutorC);
		this.nombreTutor = nombreTutor;
		this.apellidosTutor = apellidosTutor;
	}

	public String getNombreTutor() {
		return nombreTutor;
	}

	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public String getApellidosTutor() {
		return apellidosTutor;
	}

	public void setApellidosTutor(String apellidosTutor) {
		this.apellidosTutor = apellidosTutor;
	}
	
	
}
