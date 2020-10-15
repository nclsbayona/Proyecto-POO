package Model;

import java.util.Date;
import java.util.HashSet;

public class Escultura extends Obra 
{
    private String material;
    private double peso;
    private HashSet<Material> materiales;
    
    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public HashSet<Material> getMateriales() {
        return this.materiales;
    }

    public void setMateriales(HashSet<Material> materiales) {
        this.materiales = materiales;
    }

    public Escultura(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones, String material, double peso) 
    {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.material=material;
        this.peso=peso;
        this.materiales=new HashSet<Material>();
    }
    @Override
    public String toString()
    {
        return super.toString()+"\n\tEscultura\n\tMaterial: "+this.material+" -- Peso: "+this.peso+'\n';
    }
}
