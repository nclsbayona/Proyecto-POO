package model;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Escultura extends Obra {

    @XmlElement
    private String material;
    @XmlElement
    private double peso;
    @XmlElement
    private Material materiales;

    public Escultura(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones,
            String material, double peso) {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.material = material;
        this.peso = peso;
    }

    @Override
    public double calcularPrecio() {
        double precio = super.getPrecioRef();
        double add = 0.01 * precio;
        if (this.peso > 10)
            precio += (Math.ceil(this.peso - 10) * add);
        return precio;
    }

    public String getMaterial() {
        return this.material;
    }

    public Material getMateriales() {
        return this.materiales;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setMateriales(Material materiales) {
        this.materiales = materiales;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tEscultura\n\tMaterial: " + this.material + " -- Peso: " + this.peso + " kg\n";
    }
}
