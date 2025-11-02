package modelo;

import java.sql.Timestamp;

public class TutorCurso{
	
	private long id_tutorC;
	private String especialidad;
	
	private long id_usuario;
	
	public TutorCurso(long id_tutorC, String especialidad, long id_usuario) {
		this.id_tutorC = id_tutorC;
		this.especialidad = especialidad;
		this.id_usuario = id_usuario;
	}

	public long getId_tutorC() {
		return id_tutorC;
	}

	public void setId_tutorC(long id_tutorC) {
		this.id_tutorC = id_tutorC;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
	
}
