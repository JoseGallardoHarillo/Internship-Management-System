package dto;

import java.sql.Date;

public class AlumnoDTO {
	
	private String nombre;
	private String apellidos;
	private String dni;
	private String email;
	private String telefono;
	private Date fechaNacimiento;
	private String password;
	private int duracionPracticas;
	private String horario;
	private Date fechaInicio;
	private Date fechaFin;
	private String nombreCurso;
	private String nombreEmpresa;
	private String nombreTutorP;
	private String apellidosTutorP;
	
	public AlumnoDTO(String nombre, String apellidos, String dni, String email, String telefono, Date fechaNacimiento,
			String password, int duracionPracticas, String horario, Date fechaInicio, Date fechaFin, String nombreCurso,
			String nombreEmpresa, String nombreTutorP, String apellidosTutorP) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.password = password;
		this.duracionPracticas = duracionPracticas;
		this.horario = horario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombreCurso = nombreCurso;
		this.nombreEmpresa = nombreEmpresa;
		this.nombreTutorP = nombreTutorP;
		this.apellidosTutorP = apellidosTutorP;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreTutorP() {
		return nombreTutorP;
	}
	public void setNombreTutorP(String nombreTutorP) {
		this.nombreTutorP = nombreTutorP;
	}
	public String getApellidosTutorP() {
		return apellidosTutorP;
	}
	public void setApellidosTutorC(String apellidosTutorP) {
		this.apellidosTutorP = apellidosTutorP;
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
	
	
	
	
}
