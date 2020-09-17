package poo.project.Control;
import poo.project.Model.Cliente;
import java.util.HashSet;
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
        
        return null;
    }
    //Eliminar Cliente
    public void eliminarCliente(Cliente cliente)
    {

    }
    //Agregar Cliente
    public Cliente addCliente(Cliente cliente)
    {
        this.listaClientes.add(cliente);
        return cliente;
    }
    //Modificar Cliente
    public void modificarCliente(Cliente cliente)
    {

    }
}
