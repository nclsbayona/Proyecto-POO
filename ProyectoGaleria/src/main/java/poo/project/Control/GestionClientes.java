package poo.project.Control;
import poo.project.Model.Cliente;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
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
        this.organizarLista();
        return cliente2;
    }
    //Buscar un cliente por cedula
    public Cliente buscarCliente(long cedula, String s)
    {
        Cliente cliente2=null;
        for (Cliente cliente:this.listaClientes)
        {
            if (cliente.getCedula()==cedula)
            {
                cliente2=cliente;
            }
        }
        this.organizarLista();
        return cliente2;
    }
    //Eliminar Cliente
    public void eliminarCliente()
    {
        Scanner scan=new Scanner(System.in);
        Cliente cliente;
        long codigoCliente;
        System.out.print("Ingrese el codigo del cliente a eliminar: ");
        codigoCliente=scan.nextLong();
        cliente=this.buscarCliente(codigoCliente);
        this.listaClientes.remove(cliente);
        this.organizarLista();
        scan.next();
        scan.close();
    }
    //Agregar Cliente
    public Cliente addCliente(Cliente cliente)
    {
        this.listaClientes.add(cliente);
        this.organizarLista();
        return cliente;
    }
    //Modificar Cliente
    public void modificarCliente()
    {
        Scanner sc=new Scanner(System.in);
        Cliente cliente;
        int respuesta;
        System.out.println();
        System.out.print("Ingrese el codigo del cliente a modificar: ");
        respuesta=sc.nextInt();
        cliente=this.buscarCliente(respuesta);
        if (cliente==null){
            System.out.println("El cliente no existe, vuelva a intentarlo");
            sc.close();
            return;
        }
        cliente.printC();
        do{
            System.out.print("Ingrese numero de atributo a modificar: ");
            respuesta=sc.nextInt();
        } while(respuesta>6||respuesta<0);
        switch (respuesta){
            case 1:
                System.out.print("Ingrese el codigo nuevo: ");
                long codigoCliente2=sc.nextLong();
                if(this.buscarCliente(codigoCliente2)!=null)
                {
                    System.out.println("Ya existe un cliente con ese codigo");
                    sc.close();
                    return;
                }
                cliente.setCodigoCliente(codigoCliente2);
                break;
            case 2:
                System.out.print("Ingrese la cedula nueva: ");
                long cedula2=sc.nextLong();
                if(this.buscarCliente(cedula2, "w")!=null)
                {
                    System.out.println("Ya existe un cliente con esa cedula");
                    sc.close();
                    return;
                }
                cliente.setCedula(cedula2);
                break;
            case 3:
                System.out.print("Ingrese el nombre nuevo: ");
                String nombre2=sc.nextLine();
                cliente.setNombre(nombre2);
                break;
            case 4:
                System.out.print("Ingrese los apellidos nuevos: ");
                String apellidos2=sc.nextLine();
                cliente.setApellidos(apellidos2);
                break;
            case 5:
                System.out.print("Ingrese la direccion nueva: ");
                String direccion2=sc.nextLine();
                cliente.setDireccionEntrega(direccion2);
                break;
            case 6:
                System.out.print("Ingrese el telefono nuevo: ");
                long telefono2=sc.nextLong();
                cliente.setTelefono(telefono2);
                break;
            default:
                break;
        }
        sc.close();
        sc=null;
        this.organizarLista();
        //System.out.println(this.listaClientes);
    }
    public void organizarLista(){
        TreeSet<Cliente> nuevo=new TreeSet<Cliente>();
        nuevo.addAll(this.listaClientes);
        this.listaClientes.clear();
        this.listaClientes.addAll(nuevo);
        nuevo=null;
    }
}