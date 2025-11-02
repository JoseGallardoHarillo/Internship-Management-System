package modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class EvaluacionTutorC extends EvaluacionBase {
    private long id_evaluacionC;
    private long id_tutorC;
	
	public EvaluacionTutorC(long id_alumno, long id_capacidad, double puntuacion, String observaciones, Date fecha,
			Timestamp fechaCreacion, long id_evaluacionC, long id_tutorC) {
		super(id_alumno, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion);
		this.id_evaluacionC = id_evaluacionC;
		this.id_tutorC = id_tutorC;
	}
	
	public long getId_evaluacionC() {
		return id_evaluacionC;
	}
	public void setId_evaluacionC(long id_evaluacionC) {
		this.id_evaluacionC = id_evaluacionC;
	}
	public long getId_tutorC() {
		return id_tutorC;
	}
	public void setId_tutorC(long id_tutorC) {
		this.id_tutorC = id_tutorC;
	}

    
}