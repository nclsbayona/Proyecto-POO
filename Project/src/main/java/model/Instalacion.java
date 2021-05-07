package model;

import java.util.Date;
import java.util.HashSet;

import exceptions.TypoException;
import jakarta.xml.bind.annotation.XmlElement;

public class Instalacion extends Obra {
    private String descripcion;
    private HashSet<Material> materiales;

    public Instalacion(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones,
            String descripcion) throws TypoException {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.descripcion = descripcion;
        this.materiales = new HashSet<Material>();
    }

    @Override
    public double calcularPrecio() {
        int numMateriales = this.materiales.size();
        double precio = this.getPrecioRef();
        double add = 0.05 * this.getPrecioRef();
        precio += (add * numMateriales);
        return precio;
    }

    @XmlElement
    public String getDescripcion() {
        return this.descripcion;
    }

    @XmlElement
    public HashSet<Material> getMateriales() {
        return this.materiales;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMateriales(HashSet<Material> materiales) {
        this.materiales = materiales;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tInstalacion\n\tDescripcion: " + this.descripcion + '\n';
    }
}
