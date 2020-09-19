package poo.project.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class Obra{

    private HashSet<Artista> artistas;
    private long codigoObra;
    private String titulo;
    private Calendar fecha;
    private float precioRef;
    private boolean estado;

    Calendar cal = new GregorianCalendar();

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean _estado) {
        this.estado = _estado;
    }
    private String dimensiones;

    // Métodos accesors->
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

    public void setFecha(int YEAR, int MES) {
        cal.set(YEAR, MES);
    }

    public void setPrecioRef(float _precioRef) {
        this.precioRef = _precioRef;
    }
    public void setDimensiones(String _dimensiones) { this.dimensiones = _dimensiones; }


    // Constructor
    public Obra(long _codigoObra, String _titulo, Calendar _fecha, float _precioRef, String _dimensiones) {
        //El código de una obra tiene 7 números. Debe validar que nunca exista una obra con más o menos números
        if (String.valueOf(_codigoObra).length() != 7) {
            return;
        }
        this.codigoObra = _codigoObra;
        this.titulo = _titulo;
        this.fecha = _fecha;
        this.precioRef = _precioRef;
        this.dimensiones = _dimensiones;
        this.artistas = new HashSet<Artista>();
    }
    //Imprimir
    @Override
    public String toString() {
        String impresion;
        impresion = '\t' + this.titulo + ":\t$" + this.precioRef + '\t' + this.dimensiones;
        return impresion;
    }
    //Equals
    @Override
    public boolean equals(Object obj) {
        try {
            Obra o = (Obra) obj;
            return (this.codigoObra == o.codigoObra) ? true : false;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public int hashCode() {
        return (int) this.codigoObra;
    }
}
