package model;

public class Cliente implements Comparable<Cliente> {
    private long codigoCliente;
    private long cedula;
    private String nombre;
    private String Apellidos;
    private String direccionEntrega;
    private long telefono;

    // Costructor del Cliente
    public Cliente(long codigoCliente, long cedula, String nombre, String Apellidos, String direccionEntrega,
            long telefono) {

        this.codigoCliente = codigoCliente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.Apellidos = Apellidos;
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
            a = (this.codigoCliente < cliente.getCodigoCliente()) ? -1
                    : (this.codigoCliente == cliente.getCodigoCliente()) ? 0 : 1;
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
            return (this.codigoCliente == c.getCodigoCliente() || this.cedula == c.getCedula()) ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getApellidos() {
        return Apellidos;
    }

    public long getCedula() {
        return cedula;
    }

    // Getters
    public long getCodigoCliente() {
        return codigoCliente;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTelefono() {
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
        retorno += "2 Cedula: " + this.cedula + "\n3 Nombre: " + this.nombre + "\n4 Apellidos: " + this.Apellidos
                + "\n";
        retorno += "5 DireccionEntrega: " + this.direccionEntrega + "\n6 Telefono: " + this.telefono;
        System.out.println(retorno);
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
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
                + this.Apellidos + "\t" + this.direccionEntrega + " -- " + this.telefono;
        /*
         * retorno+='\nLo acompañan: "+this.acompañantes+" personas\tTelefono:
         * "+this.telefono
         */;
        return retorno;
    }
}
