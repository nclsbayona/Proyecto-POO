package poo.Model;

import java.util.Date;
import java.util.HashSet;

public class Instalacion extends Obra {
    private String descripcion;
    private HashSet<Material> materiales;

    public HashSet<Material> getMateriales() {
        return this.materiales;
    }

    public void setMateriales(HashSet<Material> materiales) {
        this.materiales = materiales;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instalacion(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones,
            String descripcion) {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.descripcion = descripcion;
        this.materiales = new HashSet<Material>();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tInstalacion\n\tDescripcion: " + this.descripcion + '\n';
    }

    @Override
    public double calcularPrecio() {
        int numMateriales = this.materiales.size();
        double precio = this.getPrecioRef();
        double add = 0.05 * this.getPrecioRef();
        precio += (add * numMateriales);
        return precio;
    }
}
