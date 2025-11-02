package modelo;

import java.sql.Timestamp;

public class TutorPracticas{
	
	private long id_tutorP;
	private String cargo;
	private String horario;
	
	private long id_empresa;
	private long id_usuario;

	public TutorPracticas(long id_tutorP, String cargo, String horario, long id_empresa, long id_usuario) {
		this.id_tutorP = id_tutorP;
		this.cargo = cargo;
		this.horario = horario;
		this.id_empresa = id_empresa;
		this.id_usuario = id_usuario;
	}

	public long getId_tutorP() {
		return id_tutorP;
	}

	public void setId_tutorP(long id_tutorP) {
		this.id_tutorP = id_tutorP;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
}
