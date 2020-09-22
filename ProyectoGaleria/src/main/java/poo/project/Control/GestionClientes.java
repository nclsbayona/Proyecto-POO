package poo.project.Control;
import poo.project.Model.Cliente;
import java.util.HashSet;
public class GestionClientes
{
    public HashSet <Cliente> startClientes()
    {
        HashSet <Cliente> clientes=new HashSet <Cliente> ();
        clientes.add(new Cliente(1, 14223, "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212));
        clientes.add(new Cliente(5, 12933, "Fred", "Jones", "20822 SW Luxury Park", 98765432));
        clientes.add(new Cliente(6, 11837, "Juan", "Acosta", "Calle 100 #20-29", 3208426));
        clientes.add(new Cliente(3, 3982, "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426));
        return clientes;
    }
}
