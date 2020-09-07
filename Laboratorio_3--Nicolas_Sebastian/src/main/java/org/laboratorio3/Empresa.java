package org.laboratorio3;

import java.util.Scanner;

public class Empresa {

    /***
     * Método para crear instancias de empleado; recibe el nombre, cedula y
     * salario;el método retorna un empleado. 
     * b. Método que recibe como parámetro un
     * empleado y muestra en pantalla para el empleado: nombre, cédula,total
     * devengado, total descuentos, total nómina
     */
    // Atributos
    // Métodos
    // a
    public static Empleado CrearEmpleado(String Nombre, long Cedula, double Salario) {
        Empleado E1 = new Empleado(Nombre, Cedula, Salario);
        return E1;
    }

    // b
    public static void MostrarEmpleado(Empleado empleado) {
        System.out.println("Nombre del empleado: " + empleado.getNombre());
        System.out.println("Cedula del empleado: " + empleado.getCedula());
        System.out.println("Total devengado del empleado: " + empleado.TotalDevengado());
        System.out.println("Total de descuentos del empleado: " + empleado.CalcularDescuentoTotal());
        System.out.println("Total nómina del empleado: " + empleado.CalcularTotalNomina());
    }

    /*
     * 12. En el main realice la invocación de los métodos anteriores: a. Invoque el
     * método para crear tres empleados (uno con 1SMLV, 1 con 2 SMLV, 1 con 5 SMLV
     * utilizando el atributo salarioMin).
     * 
     * b. Invoque el método para mostrar la información de los empleados.
     * 
     * c. Calcule el total de la nómina de la empresa para los 3 empleados: se deben
     * sumar los totales de nómina decada empleado.
     * 
     * d. Calcule el total a pagar del SENA: corresponde al 4% del total general de
     * la nómina.
     */
    public static void main(String[] args) {
        Empleado.setSalarioMinimo(877803);
        Empleado employees[] = new Empleado[3];
        double nomina = 0, sena = 0;
        String nombres[] = { "Pepe", "Juan", "Nicolas" };
        long cedulas[] = { 1000471976, 1000111222, 1001333333 };
        int cantidades[] = { 1, 2, 5 };
        for (int i = 0; i < 3; i++) {
            employees[i] = CrearEmpleado(nombres[i], cedulas[i], cantidades[i] * Empleado.getSalarioMinimo());
        }
        for (int i = 0; i < 3; i++) {
            MostrarEmpleado(employees[i]);
            System.out.println();
        }
        // C
        for (int i = 0; i < 3; i++) {
            nomina += employees[i].CalcularTotalNomina();
        }
        // D
        sena = nomina * 0.04;
        /*
         * 13. Modifique la clase Main, para que los datos sean insertados de forma más
         * dinámica: 
         * a. Agregue un arreglo de Empleados 
         * b. Pregunte por consola cuantos empleados tiene la empresa y cree el arreglo de ese tamaño. 
         * c. Haga un ciclo
         * para insertar los datos de los empleados por consola e ir llenando el
         * arreglo. d. Calcule el total de la nómina de la empresa para todos los
         * empleados: se deben sumar los totales de nómina de cada empleado. e. Calcule
         * el total a pagar del SENA: corresponde al 4% del total general de la nómina.
         */

        // a
        Empleado arregloEmpleados[];
        // b
        int canti;
        Scanner Input = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de empleados: ");
        canti = Input.nextInt();
        arregloEmpleados = new Empleado[canti];

        // c
        String nombre;
        long cedula;
        double salario;
        double nomina_ = 0;
        double sena_ = 0;

        for (int i = 0; i < canti; i++) {
            Input.nextLine();
            System.out.print("\nIngrese el nombre del empleado " + i + ": ");
            nombre = Input.nextLine();
            System.out.print("Ingrese la cedula del empleado " + i + ": ");
            cedula = Input.nextLong();
            System.out.print("Ingrese el salario del empleado " + i + ": ");
            salario = Input.nextDouble();
            arregloEmpleados[i] = CrearEmpleado(nombre, cedula, salario);
        }
        // d
        for (int i = 0; i < canti; i++) {
            nomina_ += arregloEmpleados[i].CalcularTotalNomina();
        }
        // e
        sena_ = nomina_ * 0.04;
    }
}