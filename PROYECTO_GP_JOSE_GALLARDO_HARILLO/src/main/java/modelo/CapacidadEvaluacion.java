package modelo;

public class CapacidadEvaluacion {
	private long id_capacidad;
	
	private long id_criterio;
	private String nombre;
	private String descripcion;
	private int puntuacionMaxima;
	private boolean activo;
	
	public CapacidadEvaluacion(long id_capacidad, long id_criterio, String nombre, String descripcion, int puntuacionMaxima,
			boolean activo) {
		this.id_capacidad = id_capacidad;
		this.id_criterio = id_criterio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntuacionMaxima = puntuacionMaxima;
		this.activo = activo;
	}
	
	public long getId_capacidad() {
		return id_capacidad;
	}

	public void setId_capacidad(long id_capacidad) {
		this.id_capacidad = id_capacidad;
	}

	public long getId_criterio() {
		return id_criterio;
	}

	public void setId_criterio(long id_criterio) {
		this.id_criterio = id_criterio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPuntuacionMaxima() {
		return puntuacionMaxima;
	}

	public void setPuntuacionMaxima(int puntuacionMaxima) {
		this.puntuacionMaxima = puntuacionMaxima;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
    public String getActivoSN() {
    	if(activo == true) return "Si ✅";
    	else return "No ❌";
    }
	
}
