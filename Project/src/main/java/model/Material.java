package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Material {
    private long codigo;
    private String nombre;
    private String descripcion;

    // Constructor
    public Material(long cod, String nom, String desc) {
        this.codigo = cod;
        this.nombre = nom;
        this.descripcion = desc;
    }

    // Metodos
    // Accesors
    // Codigo
    @XmlElement
    public long getCodigo() {
        return this.codigo;
    }

    // Nombre
    @XmlElement
    public String getNombre() {
        return this.nombre;
    }

    // Descripcion
    @XmlElement
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
