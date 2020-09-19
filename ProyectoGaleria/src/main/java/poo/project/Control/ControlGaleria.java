package poo.project.Control;

import java.util.Calendar;
import poo.project.Model.*;
import java.util.HashSet;

public class ControlGaleria {

    private HashSet<Obra> listaObras;
    private HashSet<Cliente> listaClientes;
    private HashSet<Compra> listaCompras;
    private GestionObras gestionObras;
    private GestionClientes gestionClientes;

    //Métodos
    //Accessors
    //ListaObras
    public HashSet<Obra> getListaObras() {
        return this.listaObras;
    }

    public void setListaObras(HashSet<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    //gestionClientes
    public GestionClientes getGestionClientes() {
        return this.gestionClientes;
    }

    public void setGestionClientes(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    //listaClientes
    public HashSet<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    public void setListaClientes(HashSet<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    //listaCompras
    public HashSet<Compra> getListaCompras() {
        return this.listaCompras;
    }

    public void setListaCompras(HashSet<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    //gestionObras
    public GestionObras getGestionObras() {
        return this.gestionObras;
    }

    public void setGestionObras(GestionObras gestionObras) {
        this.gestionObras = gestionObras;
    }

    //Imprimir listaClientes
    public void printClientes() {
        System.out.println("Imprimiendo la lista de Clientes:");
        for (Cliente cliente : this.listaClientes) {
            System.out.println(cliente);
        }
    }

    //Imprimir listaCompras
    public void printCompras() {
        System.out.println("Imprimiendo la lista de Compras:");
        for (Compra compra : this.listaCompras) {
            System.out.println(compra);
        }
    }

    //Imprimir listaObras
    public void printObras() {
        System.out.println("Imprimiendo la lista de Obras:");
        for (Obra obra : this.listaObras) {
            System.out.println(obra);
        }
    }
    //Main (Solo para probar que todo funcione bien)
    public static void main(String[] args) {
        ControlGaleria controlGaleria = new ControlGaleria();
        GestionClientes gc=controlGaleria.gestionClientes;
        Cliente pruebasClientes[]=new Cliente[4];
        GestionObras go = controlGaleria.gestionObras;
        Obra obras[] = new Obra[3];
        Calendar proof = Calendar.getInstance();
        proof.set(2020,11,01);
        obras[0] = new Obra(1234567, "Mera", proof, 20000, "20x5");
        obras[1] = new Obra(5432198, "Okaloka", proof, 20000, "10x8");
        obras[2] = new Obra(7654321, "Machupichu", proof,15000, "10x2");
        go.addObra(obras[0]);
        go.addObra(obras[1]);
        go.addObra(obras[2]);
        System.out.println(go);
        go.modificarObra(1234567);
        pruebasClientes[0]=new Cliente(1, 14223, "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212);
        pruebasClientes[1]=new Cliente(5, 12933, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
        pruebasClientes[2]=new Cliente(6, 11837, "Juan", "Acosta", "Calle 100 #20-29", 3208426);
        pruebasClientes[3]=new Cliente(3, 3982, "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426);
        gc.addCliente(pruebasClientes[0]);
        gc.addCliente(pruebasClientes[1]);
        //go.eliminarObra(1234567);
        controlGaleria.printObras();
        controlGaleria.printClientes();
        //Son el mismo objeto entonces, lo que pasa es que muestro aquí todo lo que ejecute en gestionObras
        gc.modificarCliente();
        gc.eliminarCliente();

    //Constructor
    public ControlGaleria() {
        this.gestionClientes = new GestionClientes();
        this.gestionObras = new GestionObras();
        this.listaClientes = new HashSet<Cliente>();
        this.listaCompras = new HashSet<Compra>();
        this.listaObras = new HashSet<Obra>();
        //Exactamente aquí estoy diciendo que mi objeto de gestion obras y el de gestion cliente tienen 
        //acceso a mi listaObras de aquí, por eso se modifican las de aquí allá
        this.gestionObras.addListaObras(this.listaObras);
        this.gestionClientes.addListaClientes(this.listaClientes);
    }
}
