package model;

import java.util.Date;

@XmlRootElement
public class Cuadro extends Obra {

    @XmlElement
    private String tema;
    @XmlElement
    private String tecnica;
    @XmlElement
    private Clasificacion clasificacion;

    public Cuadro(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones, String tema,
            String tecnica, Clasificacion valorA) {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.tema = tema;
        this.tecnica = tecnica;
        this.clasificacion = valorA;
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

    public Clasificacion getClasificacion() {
        return this.clasificacion;
    }

    public String getTecnica() {
        return this.tecnica;
    }

    public String getTema() {
        return this.tema;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tCuadro\n\tTema: " + this.tema + " -- Tecnica: " + this.tecnica
                + " -- Clasificacion: " + this.clasificacion.toString();
    }
}
