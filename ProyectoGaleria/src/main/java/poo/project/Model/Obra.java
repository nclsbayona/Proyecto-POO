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
    public HashSet<Artista> getArtista() {
        return this.artistas;
    }

    public void setArtista(HashSet<Artista> _artista) {
        this.artistas = _artista;
    }

    public long getCodigoObra() {
        return this.codigoObra;
    }

    public void setCodigoObra(long _codigoObra) {
        this.codigoObra = _codigoObra;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String _titulo) {
        this.titulo = _titulo;
    }

    public Calendar getCalendar() {
        return this.fecha;
    }

    public void setCalendar(Calendar _fecha) {
        this.fecha = _fecha;
    }

    public float getPrecioRef() {
        return this.precioRef;
    }

    public void setPrecioRef(float _precioRef) {
        this.precioRef = _precioRef;
    }

    public String getDimensiones() {
        return this.dimensiones;
    }

    public void setDimensiones(String _dimensiones) {
        this.dimensiones = _dimensiones;
    }
}