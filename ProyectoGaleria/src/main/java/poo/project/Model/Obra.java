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
    // Accesor Artista
    public HashSet<Artista> getArtista() {
        return this.artistas;
    }

    public void setArtista(HashSet<Artista> _artista) {
        this.artistas = _artista;
    }

    // Accesor CodigoObra
    public long getCodigoObra() {
        return this.codigoObra;
    }

    public void setCodigoObra(long _codigoObra) {
        this.codigoObra = _codigoObra;
    }

    // Accesor Titulo
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String _titulo) {
        this.titulo = _titulo;
    }

    // Accesor Fecha
    public Calendar getFecha() {
        return this.fecha;
    }

    public void setFecha(Calendar _fecha) {
        this.fecha = _fecha;
    }

    // Accesor Precio
    public float getPrecioRef() {
        return this.precioRef;
    }

    public void setPrecioRef(float _precioRef) {
        this.precioRef = _precioRef;
    }

    // Accesor Dimensiones
    public String getDimensiones() {
        return this.dimensiones;
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
}