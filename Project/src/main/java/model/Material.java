package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Material {
    @XmlElement
    private long codigo;
    @XmlElement
    private String nombre;
    @XmlElement
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
    public long getCodigo() {
        return this.codigo;
    }

    // Descripcion
    public String getDescripcion() {
        return this.descripcion;
    }

    // Nombre
    public String getNombre() {
        return this.nombre;
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
