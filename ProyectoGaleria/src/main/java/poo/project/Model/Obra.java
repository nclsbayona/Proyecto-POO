package poo.project.Model;

import java.util.Calendar;
import java.util.HashSet;

public class Obra {
    private HashSet<Artista> artistas;
    private long codigoObra;
    private String titulo;
    private Calendar fecha;
    private float precioRef;
    private String dimensiones;

    // Métodos accesors
    // Getters
    public HashSet<Artista> getArtista() {
        return this.artistas;
    }

    public long getCodigoObra() {
        return this.codigoObra;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Calendar getFecha() {
        return this.fecha;
    }

    public float getPrecioRef() {
        return this.precioRef;
    }

    public String getDimensiones() {
        return this.dimensiones;
    }

    // Setters
    public void setArtista(HashSet<Artista> _artista) {
        this.artistas = _artista;
    }

    public void setCodigoObra(long _codigoObra) {
        this.codigoObra = _codigoObra;
    }

    public void setTitulo(String _titulo) {
        this.titulo = _titulo;
    }

    public void setFecha(Calendar _fecha) {
        this.fecha = _fecha;
    }

    public void setPrecioRef(float _precioRef) {
        this.precioRef = _precioRef;
    }

    public void setDimensiones(String _dimensiones) {
        this.dimensiones = _dimensiones;
    }

    // Constructor
    public Obra(long _codigoObra, String _titulo, Calendar _fecha, float _precioRef, String _dimensiones) {
        this.codigoObra = _codigoObra;
        this.titulo = _titulo;
        this.fecha = _fecha;
        this.precioRef = _precioRef;
        this.dimensiones = _dimensiones;
        this.artistas = new HashSet<Artista>();
    }
    //Imprimir
    public String toString()
    {
        String impresion;
        impresion='\t'+this.titulo+"\n"+this.precioRef+'\t'+this.dimensiones;
        return impresion;
    }
}