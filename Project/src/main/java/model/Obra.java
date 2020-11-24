package model;

import java.util.Date;
import java.util.Calendar;
import java.util.HashSet;

import excepciones.CodeSizeException;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class Obra {

    @XmlElement
    private HashSet<Artista> artistas;
    @XmlElement
    private Calendar fecha;
    @XmlElement
    private String titulo;
    @XmlElement
    private float precioRef;
    @XmlElement
    private long codigoObra;
    @XmlElement
    private String dimensiones;

    // Constructor
    public Obra(long _codigoObra, String _titulo, Calendar _fecha, float _precioRef, String _dimensiones)
            throws CodeSizeException {
        // El código de una obra tiene 7 números. Debe validar que nunca exista una obra
        // con más o menos números
        if (String.valueOf(codigoObra).length()!=7)
            throw new CodeSizeException();
        this.codigoObra = _codigoObra;
        this.titulo = _titulo;
        this.fecha = _fecha;
        this.precioRef = _precioRef;
        this.dimensiones = _dimensiones;
        this.artistas = new HashSet<Artista>();
    }

    // Constructor
    public Obra(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones) {
        // El código de una obra tiene 7 números. Debe validar que nunca exista una obra
        // con más o menos números
        if (String.valueOf(_codigoObra).length() != 7) {
            return;
        }
        this.codigoObra = _codigoObra;
        this.titulo = _titulo;
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(_fecha);
        this.precioRef = _precioRef;
        this.dimensiones = _dimensiones;
        this.artistas = new HashSet<Artista>();
    }

    // Abstracto
    public abstract double calcularPrecio();

    // Equals
    @Override
    public boolean equals(Object obj) {
        try {
            Obra o = (Obra) obj;
            return (this.codigoObra == o.codigoObra) ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    // Métodos accesors->
    // Getters
    public HashSet<Artista> getArtista() {
        return this.artistas;
    }

    public long getCodigoObra() {
        return this.codigoObra;
    }

    public String getDimensiones() {
        return this.dimensiones;
    }

    public Calendar getFecha() {
        return this.fecha;
    }

    public float getPrecioRef() {
        return this.precioRef;
    }

    public String getTitulo() {
        return this.titulo;
    }

    // Organización del hash set
    @Override
    public int hashCode() {
        return (int) this.codigoObra;
    }

    // Setters
    public void setArtista(HashSet<Artista> _artista) {
        this.artistas = _artista;
    }

    public void setCodigoObra(long _codigoObra) {
        this.codigoObra = _codigoObra;
    }

    public void setDimensiones(String _dimensiones) {
        this.dimensiones = _dimensiones;
    }

    public void setFecha(int YEAR, int MES, int DIA) {
        this.fecha.set(YEAR, MES, DIA);
    }

    public void setPrecioRef(float _precioRef) {
        this.precioRef = _precioRef;
    }

    public void setTitulo(String _titulo) {
        this.titulo = _titulo;
    }

    // Imprimir
    @Override
    public String toString() {
        String impresion;
        impresion = '\t' + this.titulo + '\t' + this.fecha.get(Calendar.DATE) + '/' + this.fecha.get(Calendar.MONTH)
                + '/' + this.fecha.get(Calendar.YEAR) + ":\t$" + this.precioRef + '\t' + this.dimensiones;
        return impresion;
    }
}
