package poo.project.Model;

import java.util.HashSet;
import java.util.Set;

public class Cliente
{
    private long codigoCliente;
    private long cedula;
    private String nombre;
    private String Apellidos;
    private String direccionEntrega;
    private long telefono;

    //Creacion de Coleccion HashSet
    Set<Cliente> Coleccion_Cliente = new HashSet<Cliente>();

    //Getters
    public long getCodigoCliente() { return codigoCliente; }
    public long getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return Apellidos; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public long getTelefono() { return telefono; }

    //Setters
    public void setCodigoCliente(long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public void setCedula(long cedula) {
        this.cedula = cedula;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }
    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    //Costructor del Cliente
    public Cliente(
            long codigoCliente,
            long cedula,
            String nombre,
            String Apellidos,
            String direccionEntrega,
            long telefono ) {

        this.codigoCliente = codigoCliente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.Apellidos = Apellidos;
        this.direccionEntrega = direccionEntrega;
        this.telefono = telefono;

    }
}
