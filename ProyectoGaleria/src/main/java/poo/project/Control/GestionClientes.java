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
    }
    //Eliminar Cliente con el codigoCliente
    public void eliminarCliente(long codigoCliente)
    {
        this.listaClientes.remove(this.buscarCliente(codigoCliente));
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
    }
}
