package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cliente implements Comparable<Cliente> {
    @XmlElement
    private long codigoCliente;
    @XmlElement
    private long cedula;
    @XmlElement
    private String nombre;
    @XmlElement
    private String apellidos;
    @XmlElement
    private String direccionEntrega;
    @XmlElement
    private long telefono;

    // Costructor del Cliente
    public Cliente(long codigoCliente, long cedula, String nombre, String Apellidos, String direccionEntrega,
            long telefono){
        this.codigoCliente = codigoCliente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = Apellidos;
        this.direccionEntrega = direccionEntrega;
        this.telefono = telefono;
    }

    @Override
    public int compareTo(Cliente cliente) {
        /*
         * It is used to compare the current object with the specified object. It
         * returns positive integer, if the current object is greater than the specified
         * object. negative integer, if the current object is less than the specified
         * object. zero, if the current object is equal to the specified object.
         */
        int a = -1;
        try {
            a = (this.codigoCliente < cliente.nGetCodigoCliente()) ? -1
                    : (this.codigoCliente == cliente.nGetCodigoCliente()) ? 0 : 1;
        } catch (Exception e) {
            a = -1;
        }
        return a;
    }

    // Organización
    @Override
    public boolean equals(Object obj) {
        try {
            Cliente c = (Cliente) (obj);
            return (this.codigoCliente == c.nGetCodigoCliente() || this.cedula == c.nGetCedula()) ? true : false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String nGetApellidos() {
        return this.apellidos;
    }

    public long nGetCedula() {
        return this.cedula;
    }

    // Getters
    public long nGetCodigoCliente() {
        return this.codigoCliente;
    }

    public String nGetDireccionEntrega() {
        return direccionEntrega;
    }

    public String nGetNombre() {
        return nombre;
    }

    public long nGetTelefono() {
        return telefono;
    }

    // Para la búsqueda ewn hash Set
    @Override
    public int hashCode() {
        int a = (int) this.codigoCliente;
        return String.valueOf(a).hashCode();
    }

    // ;Modificar cliente
    public void printC() {
        String retorno = "1 CodigoCliente: " + String.valueOf(this.codigoCliente) + '\n';
        retorno += "2 Cedula: " + this.cedula + "\n3 Nombre: " + this.nombre + "\n4 Apellidos: " + this.apellidos
                + "\n";
        retorno += "5 DireccionEntrega: " + this.direccionEntrega + "\n6 Telefono: " + this.telefono;
        System.out.println(retorno);
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    // Setters
    public void setCodigoCliente(long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    // Imprimir cliente
    @Override
    public String toString() {
        // identificación, nombre completo, cantidad de personas que lo acompañan y
        // teléfono.
        String retorno = String.valueOf(this.codigoCliente) + ' ' + this.cedula + "\t\t" + this.nombre + ' '
                + this.apellidos + "\t" + this.direccionEntrega + " -- " + this.telefono;
        /*
         * retorno+='\nLo acompañan: "+this.acompañantes+" personas\tTelefono:
         * "+this.telefono
         */;
        return retorno;
    }

    public Cliente() {
    }

}
