package model;

public class Material {
    private long codigo;
    private String nombre;
    private String descripcion;

    // Metodos
    // Accesors
    // Codigo
    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    // Nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Descripcion
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Constructor
    public Material(long cod, String nom, String desc) {
        this.codigo = cod;
        this.nombre = nom;
        this.descripcion = desc;
    }
}
