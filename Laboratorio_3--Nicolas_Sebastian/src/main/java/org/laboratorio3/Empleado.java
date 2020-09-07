package org.laboratorio3;

public class Empleado {
    
    private String nombre;
    private long cedula;
    private double salario;
    // No está en mayusculas entonces no es constante
    private static double salarioMin;
    private final static double SUBSIDIOT=102854;
    /*
     * En el caso de que no halla ningún problema con que los accessors sean
     * estáticos //Accessors de nombre public static String getNombre() { return
     * this.nombre; } public static void setNombre(String _nombre) {
     * this.nombre=_nombre; } //Accessors de cédula public static long getCedula() {
     * return this.cedula; } public static void setCedula(long _cedula) {
     * this.cedula=_cedula; } //Accessors de salario public static long getSalario()
     * { return this.salario; } public static void setSalario(double _salario) {
     * this.salario=_salario; }
     */
    // Accessors de nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String _nombre) {
        this.nombre = _nombre;
    }

    // Accessors de cédula
    public long getCedula() {
        return this.cedula;
    }

    public void setCedula(long _cedula) {
        this.cedula = _cedula;
    }

    // Accessors de salario
    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double _salario) {
        this.salario = _salario;
    }

    // Accessors de salario minimo
    public static double getSalarioMinimo() {
        return Empleado.salarioMin;
    }

    public static void setSalarioMinimo(double _salario) {
        Empleado.salarioMin = _salario;
    }

    // Métodos
    // Metodos constructores
    // Vacío
    public Empleado() {
    }

    // Con paramétros
    public Empleado(String nombre, long cedula, double salario) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.salario = salario;
    }

    // Métodos que calculan
    // Calculo del subsidio de transporte
    public double SubsidioTransporte() {
        return ((this.salario / Empleado.salarioMin) <= 2) ? Empleado.SUBSIDIOT : 0;
    }

    // Calculo del total devengado
    public double TotalDevengado() {
        return this.salario + this.SubsidioTransporte();
    }

    // Calculo del Ingreso Base de Liquidacion
    public double CalcularIBC() {
        return 0.75 * this.TotalDevengado();
    }

    // Calculo del descuento de pensión
    public double CalcularDescuentoPension() {
        return 0.03875 * this.CalcularIBC();
    }

    // Calculo del descuento de salud
    public double CalcularDescuentoSalud() {
        return 0.045 * this.CalcularIBC();
    }

    // Calculo del descuento de Fondo Solidaridad
    public double CalcularDescuentoFS() {
        return (this.salario > (4 * Empleado.salarioMin)) ? 0.01 * this.CalcularIBC() : 0;
    }

    // Calculo del total de los descuentos
    public double CalcularDescuentoTotal() {
        return this.CalcularDescuentoPension() + this.CalcularDescuentoSalud() + this.CalcularDescuentoFS();
    }

    // Calculo del total de nómina
    public double CalcularTotalNomina() {
        return this.TotalDevengado() - this.CalcularDescuentoTotal();
    }
}