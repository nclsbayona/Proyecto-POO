package poo.project.Control;
import poo.project.Model.*;
import java.util.HashSet;
public class ControlGaleria
{
    private HashSet<Obra> listaObras;
    private HashSet<Cliente> listaClientes;
    private HashSet<Compra> listaCompras;
    private GestionObras gestionObras;
    private GestionClientes gestionClientes;

    public HashSet<Obra> getListaObras() {
        return this.listaObras;
    }

    public void setListaObras(HashSet<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    public GestionClientes getGestionClientes() {
        return this.gestionClientes;
    }

    public void setGestionClientes(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    public HashSet<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    public void setListaClientes(HashSet<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public HashSet<Compra> getListaCompras() {
        return this.listaCompras;
    }

    public void setListaCompras(HashSet<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public GestionObras getGestionObras() {
        return this.gestionObras;
    }

    public void setGestionObras(GestionObras gestionObras) {
        this.gestionObras = gestionObras;
    }
    public void printClientes(){
        System.out.println("Imprimiendo la lista de Clientes:");
        for (Cliente cliente:this.listaClientes)
            System.out.println(cliente);
    }
    public void printCompras(){
        System.out.println("Imprimiendo la lista de Compras:");
        for (Compra compra:this.listaCompras)
            System.out.println(compra);
    }
    public void printObras(){
        System.out.println("Imprimiendo la lista de Obras:");
        for (Obra obra:this.listaObras)
            System.out.println(obra);
    }
    public static void main(String [] args){
        ControlGaleria controlGaleria=new ControlGaleria();
        //Se supone que añado un cliente a la lista de clientes de GestionClientes
        //Los clientes se organizan por su código de cliente
        GestionClientes gc=controlGaleria.gestionClientes;
        Cliente pruebasClientes[]=new Cliente[4];
        pruebasClientes[0]=new Cliente(1, 14223, "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212);
        pruebasClientes[1]=new Cliente(5, 12933, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
        pruebasClientes[2]=new Cliente(6, 11837, "Juan", "Acosta", "Calle 100 #20-29", 3208426);
        pruebasClientes[3]=new Cliente(3, 3982, "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426);
        
        gc.addCliente(pruebasClientes[0]);
        gc.addCliente(pruebasClientes[1]);
        controlGaleria.printClientes();
        gc.eliminarCliente(1);
        //Ahora imprimo la lista desde el control como tal
        //controlGaleria.printClientes();
        gc.addCliente(pruebasClientes[2]);
        //controlGaleria.printClientes();
        gc.modificarCliente(pruebasClientes[2], 2);
        System.err.println("Antes de poner al 3: "+controlGaleria.getListaClientes());
        gc.addCliente(pruebasClientes[3]);
        System.err.println("Despues de poner al 3: "+controlGaleria.getListaClientes());
        //Las obras se organizan por su codigo de obras
        //Los artistas se organizan por su codigo de artistas
        //Las compras se organizan por su codigo de obra
        //Ahora imprimo la lista desde el control como tal
        controlGaleria.printClientes();
    }
    public ControlGaleria(){
        this.gestionClientes=new GestionClientes();
        this.gestionObras=new GestionObras();
        this.listaClientes=new HashSet<Cliente>();
        this.listaCompras=new HashSet<Compra>();
        this.listaObras=new HashSet<Obra>();
        this.gestionObras.addListaObras(this.listaObras);
        this.gestionClientes.addListaClientes(this.listaClientes);
    }
}