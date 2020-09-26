package Control;
import Model.Cliente;
import java.util.HashSet;
public class GestionClientes
{
    public HashSet <Cliente> listaClientes()
    {
        HashSet <Cliente> clientes=new HashSet <Cliente> ();
        clientes.add(new Cliente(1, 1422373, "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212));
        clientes.add(new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432));
        clientes.add(new Cliente(6, 1183937, "Juan", "Acosta", "Calle 100 #20-29", 3208426));
        clientes.add(new Cliente(3, 9253620, "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426));
        return clientes;
    }
}
