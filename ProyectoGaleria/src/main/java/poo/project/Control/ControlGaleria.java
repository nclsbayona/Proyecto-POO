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
    public static void main(String [] args){
        ControlGaleria controlGaleria=new ControlGaleria();
    }
    public ControlGaleria(){
        this.gestionClientes=new GestionClientes();
        this.gestionObras=new GestionObras();
        this.listaClientes=new HashSet<Cliente>();
        this.listaCompras=new HashSet<Compra>();
        this.listaObras=new HashSet<Obra>();
        this.gestionObras.addListaObras(this.listaObras);
    }
}