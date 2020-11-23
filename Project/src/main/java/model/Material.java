package model;

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