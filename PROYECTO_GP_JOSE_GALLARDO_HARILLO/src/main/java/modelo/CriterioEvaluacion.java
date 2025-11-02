package modelo;

public class CriterioEvaluacion {
	private long id_criterio;
	private String nombre;
	private String descripcion;
	private double peso;
	private boolean activo;
	
	public CriterioEvaluacion(long id_criterio, String nombre, String descripcion, double peso,
			boolean activo) {
		this.id_criterio = id_criterio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.peso = peso;
		this.activo = activo;
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

	public void setNombreCriterio(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
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
