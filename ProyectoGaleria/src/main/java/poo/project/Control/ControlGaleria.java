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
        GestionClientes gc=controlGaleria.gestionClientes;
        gc.addCliente(new Cliente(1, 123, "Alfredo", "Santamaria", "20855 NW Traverse Street", 43352482));
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