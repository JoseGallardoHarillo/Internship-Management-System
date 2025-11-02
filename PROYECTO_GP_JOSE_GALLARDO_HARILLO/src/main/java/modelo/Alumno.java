package modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class Alumno {

    private long id_alumno;
    private Date fechaNacimiento;
    private int duracionPracticas;
    private String horario;
    private Date fechaInicio;
    private Date fechaFin;
    
    private long id_curso;
    private long id_empresa;
    private long id_tutorP;
    private long id_usuario;

    public Alumno(long id_alumno, Date fechaNacimiento, int duracionPracticas, String horario, 
                  Date fechaInicio, Date fechaFin, long id_curso, long id_empresa, long id_tutorP, long id_usuario) {

        this.id_alumno = id_alumno;
        this.fechaNacimiento = fechaNacimiento;
        this.duracionPracticas = duracionPracticas;
        this.horario = horario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.id_curso = id_curso;
        this.id_empresa = id_empresa;
        this.id_tutorP = id_tutorP;
        this.id_usuario = id_usuario;
    }

	public long getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(long id_alumno) {
		this.id_alumno = id_alumno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getDuracionPracticas() {
		return duracionPracticas;
	}

	public void setDuracionPracticas(int duracionPracticas) {
		this.duracionPracticas = duracionPracticas;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
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

	public long getId_curso() {
		return id_curso;
	}

	public void setId_curso(long id_curso) {
		this.id_curso = id_curso;
	}

	public long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}

	public long getId_tutorP() {
		return id_tutorP;
	}

	public void setId_tutorP(long id_tutorP) {
		this.id_tutorP = id_tutorP;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
    
}