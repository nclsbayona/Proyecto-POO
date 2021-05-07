package control;
import java.util.HashMap;

import model.Cliente;

public class GestionClientes {
    // Retorna una lista de clientes para comenzar el dia
    public HashMap<Long, Cliente> listaClientes() {
        HashMap<Long, Cliente> clientes = new HashMap<Long, Cliente>();
        clientes.put(Long.valueOf(1422373000),new Cliente(1, Long.valueOf(1422373000), "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212));
        clientes.put(Long.valueOf(1293723000),new Cliente(2, Long.valueOf(1293723000), "Fred", "Jones", "20822 SW Luxury Park", 98765432));
        clientes.put(Long.valueOf(1183937000), new Cliente(6, Long.valueOf(1183937000), "Juan", "Acosta", "Calle 100 #20-29", 3208426));
        clientes.put(Long.valueOf(1001110000), new Cliente(3, Long.valueOf(1001110000), "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426));
        return clientes;
    }
}
