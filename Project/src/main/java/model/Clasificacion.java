package model;

import jakarta.xml.bind.annotation.XmlElement;

public enum Clasificacion {
    OBRA_MAESTRA("Obra maestra"), OBRA_REPRESENTATIVA("Obra representativa");

    private String nombre;

    private Clasificacion(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public Object getOBRA_MAESTRA() {
        return Clasificacion.OBRA_MAESTRA;
    }

    @XmlElement
    public Object getOBRA_REPRESENTATIVA() {
        return Clasificacion.OBRA_REPRESENTATIVA;
    }

    @Override
    public String toString() {
        return this.nombre + "\n";
    }
}
