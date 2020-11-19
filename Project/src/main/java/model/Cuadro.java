package model;

import java.util.Date;

public class Cuadro extends Obra {
    private String tema;
    private String tecnica;
    private Clasificacion clasificacion;

    public String getTema() {
        return this.tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTecnica() {
        return this.tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public Clasificacion getClasificacion() {
        return this.clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Cuadro(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones, String tema,
            String tecnica, Clasificacion valorA) {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.tema = tema;
        this.tecnica = tecnica;
        this.clasificacion = valorA;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tCuadro\n\tTema: " + this.tema + " -- Tecnica: " + this.tecnica
                + " -- Clasificacion: " + this.clasificacion.toString();
    }

    @Override
    public double calcularPrecio() {
        double precio = super.getPrecioRef();
        switch (clasificacion.ordinal()) {
            case 0: {
                precio *= 1.05;
                break;
            }
            case 1: {
                precio *= 1.03;
                break;
            }
        }
        return precio;
    }
}
