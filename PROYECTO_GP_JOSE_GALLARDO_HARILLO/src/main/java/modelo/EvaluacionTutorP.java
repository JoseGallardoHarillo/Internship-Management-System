package modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class EvaluacionTutorP extends EvaluacionBase {
    private long id_evaluacionP;
    private long id_tutorP;
	
    public EvaluacionTutorP(long id_alumno, long id_capacidad, double puntuacion, String observaciones, Date fecha,
			Timestamp fechaCreacion, long id_evaluacionP, long id_tutorP) {
		super(id_alumno, id_capacidad, puntuacion, observaciones, fecha, fechaCreacion);
		this.id_evaluacionP = id_evaluacionP;
		this.id_tutorP = id_tutorP;
	}

	public long getId_evaluacionP() {
		return id_evaluacionP;
	}

	public void setId_evaluacionP(long id_evaluacionP) {
		this.id_evaluacionP = id_evaluacionP;
	}

	public long getId_tutorP() {
		return id_tutorP;
	}

	public void setId_tutorP(long id_tutorP) {
		this.id_tutorP = id_tutorP;
	}

    
}