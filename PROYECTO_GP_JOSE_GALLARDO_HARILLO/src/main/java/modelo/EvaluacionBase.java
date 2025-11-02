package modelo;

import java.sql.Date;
import java.sql.Timestamp;

public abstract class EvaluacionBase {
    private long id_alumno;
	protected long id_capacidad;
    protected double puntuacion;
    protected String observaciones;
    protected Date fecha;
    protected Timestamp fechaCreacion;
    
    
    
    public EvaluacionBase(long id_alumno, long id_capacidad, double puntuacion, String observaciones, Date fecha,
			Timestamp fechaCreacion) {
    	this.id_alumno = id_alumno;
		this.id_capacidad = id_capacidad;
		this.puntuacion = puntuacion;
		this.observaciones = observaciones;
		this.fecha = fecha;
		this.fechaCreacion = fechaCreacion;
	}

    

	public long getId_alumno() {
		return id_alumno;
	}



	public void setId_alumno(long id_alumno) {
		this.id_alumno = id_alumno;
	}



	public long getId_capacidad() {
		return id_capacidad;
	}



	public void setId_capacidad(long id_capacidad) {
		this.id_capacidad = id_capacidad;
	}



	public double getPuntuacion() {
		return puntuacion;
	}



	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
    
}