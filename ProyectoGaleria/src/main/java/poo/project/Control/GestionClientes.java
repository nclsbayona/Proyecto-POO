package poo.project.Control;
import poo.project.Model.Cliente;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.event.TreeSelectionEvent;
public class GestionClientes
{
    private HashSet<Cliente> listaClientes;

    public HashSet<Cliente> getListaClientes()
    {
		return this.listaClientes;
	}

    private void setListaClientes(HashSet<Cliente> listaClientes)
    {
		this.listaClientes = listaClientes;
	}
    public void addListaClientes(HashSet<Cliente> listaCliente){
        this.setListaClientes((this.listaClientes==null)?listaCliente:this.listaClientes);
    }
    //Buscar un cliente
    public Cliente buscarCliente(long codigoCliente)
    {
        Cliente cliente2=null;
        for (Cliente cliente:this.listaClientes)
        {
            if (cliente.getCodigoCliente()==codigoCliente)
            {
                cliente2=cliente;
            }
        }
        return cliente2;
    }
    //Eliminar Cliente
    public void eliminarCliente(Cliente cliente)
    {
        this.listaClientes.remove(cliente);
        cliente=null;
    }
    //Eliminar Cliente conociendo el codigoCliente
    public void eliminarCliente(long codigoCliente)
    {
        Cliente cliente=this.buscarCliente(codigoCliente);
        this.listaClientes.remove(cliente);
    }
    //Agregar Cliente
    public Cliente addCliente(Cliente cliente)
    {
        this.listaClientes.add(cliente);
        return cliente;
    }
    //Modificar Cliente
    public void modificarCliente(Cliente cliente, long codigoCliente)
    {
        cliente.setCodigoCliente(codigoCliente);
        TreeSet<Cliente> nuevo=new TreeSet<Cliente>();
        nuevo.addAll(this.listaClientes);
        HashSet<Cliente> nuevo2=new HashSet<Cliente>();
        nuevo2.addAll(nuevo);
        this.setListaClientes(nuevo2);
        System.out.println(this.listaClientes);
    }
    //Modificar Cliente conociendo su codigoCliente actual
    public void modificarCliente(long codigoCliente, long codigoCliente2)
    {
        this.buscarCliente(codigoCliente).setCodigoCliente(codigoCliente2);
    }
}
