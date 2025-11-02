package modelo;

public class Estadistica {
    private String nombre;
    private double valor; // puede ser cantidad, promedio, porcentaje, etc.

    public Estadistica(String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }
}