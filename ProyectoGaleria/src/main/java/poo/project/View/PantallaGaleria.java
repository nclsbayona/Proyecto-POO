package poo.project.View;
import poo.project.Control.*;
import poo.project.Model.Cliente;
import poo.project.Model.Obra;

import java.util.Calendar;

public class PantallaGaleria {

    public void prueba(){

        ControlGaleria controlGaleria = new ControlGaleria();
        GestionClientes gc = controlGaleria.getGestionClientes();
        Cliente pruebasClientes[] = new Cliente[4];
        GestionObras go = controlGaleria.getGestionObras();
        Obra obras[] = new Obra[3];
        Calendar proof = Calendar.getInstance();
        proof.set(2020, 11, 01);
        obras[0] = new Obra(1234567, "Mera", proof, 20000, "20x5");
        obras[1] = new Obra(5432198, "Okaloka", proof, 20000, "10x8");
        obras[2] = new Obra(7654321, "Machupichu", proof, 15000, "10x2");
        go.addObra(obras[0]);
        go.addObra(obras[1]);
        go.addObra(obras[2]);
        System.out.println(go);
        go.modificarObra(1234567);
        pruebasClientes[0] = new Cliente(1, 14223, "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212);
        pruebasClientes[1] = new Cliente(5, 12933, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
        pruebasClientes[2] = new Cliente(6, 11837, "Juan", "Acosta", "Calle 100 #20-29", 3208426);
        pruebasClientes[3] = new Cliente(3, 3982, "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426);
        gc.addCliente(pruebasClientes[0]);
        gc.addCliente(pruebasClientes[1]);
        // go.eliminarObra(1234567);
        controlGaleria.printObras();
        controlGaleria.printClientes();
        // Son el mismo objeto entonces, lo que pasa es que muestro aquítodo lo que
        // ejecute en gestionObras
        gc.modificarCliente();
        gc.eliminarCliente();
        controlGaleria.printClientes();
    }

    public static void main(String[] args) {

        System.out.println("-------------------------");
        System.out.println("Galeria de Arte Javeriana");
        System.out.println("-------------------------");
        System.out.println("---------- Menu ---------");
        System.out.println("");

    }
    
}
