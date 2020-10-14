package Model;

import java.util.Date;
import java.util.HashSet;

public class Instalacion extends Obra
{
    private String descripcion;
    private HashSet<Material> materiales;

    public HashSet<Material> getMateriales() {
        return this.materiales;
    }

    public void setMateriales(HashSet<Material>materiales) {
		this.materiales = materiales;
	}

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instalacion(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones, String descripcion) 
    {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.descripcion=descripcion;
        this.materiales=new HashSet<Material>();
    }
}