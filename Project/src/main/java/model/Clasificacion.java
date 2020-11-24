package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Clasificacion {
    @XmlElement
    OBRA_MAESTRA("Obra maestra"), OBRA_REPRESENTATIVA("Obra representativa");
    @XmlElement
    private String nombre;

    private Clasificacion(String nombre) {
        this.nombre = nombre;
    }

    public Object nGetOBRA_MAESTRA() {
        return Clasificacion.OBRA_MAESTRA;
    }

    public Object nGetOBRA_REPRESENTATIVA() {
        return Clasificacion.OBRA_REPRESENTATIVA;
    }

    @Override
    public String toString() {
        return this.nombre + "\n";
    }
}
