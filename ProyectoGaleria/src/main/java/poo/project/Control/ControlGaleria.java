package poo.project.Control;

import java.util.Calendar;
import poo.project.Model.*;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class ControlGaleria {
    //Colecciones
    private HashSet<Obra> listaObras;
    private HashSet<Cliente> listaClientes;
    private HashSet<Compra> listaCompras;
    private GestionObras gestionObras;
    private GestionClientes gestionClientes;

    // Métodos
    // Accessors
    // gestionClientes
    public GestionClientes getGestionClientes() {
        return this.gestionClientes;
    }

    public void setGestionClientes(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    // Accessors
    // listaClientes

    // Buscar un cliente
    public Cliente buscarCliente(long codigoCliente) {
        Cliente cliente2 = null;
        for (Cliente cliente : this.listaClientes) {
            if (cliente.getCodigoCliente() == codigoCliente) {
                cliente2 = cliente;
            }
        }
        this.organizarListaClientes();
        return cliente2;
    }

    // Buscar un cliente por cedula
    public Cliente buscarCliente(long cedula, String s) {
        Cliente cliente2 = null;
        for (Cliente cliente : this.listaClientes) {
            if (cliente.getCedula() == cedula) {
                cliente2 = cliente;
            }
        }
        this.organizarListaClientes();
        return cliente2;
    }

    // Eliminar Cliente
    public void eliminarCliente() {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        try{
            Cliente cliente;
            long codigoCliente;
            System.out.println("Ingrese el codigo del cliente a eliminar: ");
            codigoCliente = scan.nextLong();
            cliente = this.buscarCliente(codigoCliente);
            if (cliente == null) {
                System.err.println("No existe");
                return;
            }
            this.listaClientes.remove(cliente);
        }
        catch(Exception e){

        }
        scan.close();
        this.organizarListaClientes();
    }

    // Agregar Cliente
    public Cliente addCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
        this.organizarListaClientes();
        return cliente;
    }

    // Modificar Cliente
    public void modificarCliente() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        Cliente cliente;
        int respuesta;
        System.out.println();
        System.out.print("Ingrese el codigo del cliente a modificar: ");
        respuesta = sc.nextInt();
        cliente = this.buscarCliente(respuesta);
        if (cliente == null) {
            System.out.println("El cliente no existe");
        }
        else{
            cliente.printC();
            do {
                System.out.print("Ingrese numero de atributo a modificar: ");
                respuesta = sc.nextInt();
            } while (respuesta > 6 || respuesta < 0);
            switch (respuesta) {
                case 1:
                    System.out.print("Ingrese el codigo nuevo: ");
                    long codigoCliente2 = sc.nextLong();
                    if (codigoCliente2<1)
                    {
                        System.err.println("Codigo invalido");
                        sc.close();
                        return;
                    }
                    if (this.buscarCliente(codigoCliente2) != null) {
                        System.out.println("Ya existe un cliente con ese codigo");
                        sc.close();
                        return;
                    }
                    cliente.setCodigoCliente(codigoCliente2);
                    break;
                case 2:
                    System.out.print("Ingrese la cedula nueva: ");
                    long cedula2 = sc.nextLong();
                    if (this.buscarCliente(cedula2, "cedula") != null) {
                        System.out.println("Ya existe un cliente con esa cedula");
                        sc.close();
                        return;
                    }
                    cliente.setCedula(cedula2);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre nuevo: ");
                    sc.next();
                    String nombre2 = sc.nextLine();
                    cliente.setNombre(nombre2);
                    break;
                case 4:
                    System.out.print("Ingrese los apellidos nuevos: ");
                    sc.next();
                    String apellidos2 = sc.nextLine();
                    cliente.setApellidos(apellidos2);
                    break;
                case 5:
                    System.out.print("Ingrese la direccion nueva: ");
                    sc.next();
                    String direccion2 = sc.nextLine();
                    cliente.setDireccionEntrega(direccion2);
                    break;
                case 6:
                    System.out.print("Ingrese el telefono nuevo: ");
                    sc.next();
                    long telefono2 = sc.nextLong();
                    cliente.setTelefono(telefono2);
                    break;
                default:
                    System.err.println("Opcion invalida");
                    return;
            }
            while (sc.hasNext()){
                sc.next();
            }
            this.organizarListaClientes();
        }
        sc.close();
        return;
    }
    public void startDay(){
        this.listaClientes.addAll(this.gestionClientes.startClientes());
        this.listaObras.addAll(this.gestionObras.startObras());
    }

    // Organizar la lista (Para mantener un orden)
    public void organizarListaClientes() {
        TreeSet<Cliente> nuevo = new TreeSet<Cliente>();
        nuevo.addAll(this.listaClientes);
        this.listaClientes.clear();
        this.listaClientes.addAll(nuevo);
        nuevo = null;
    }

    // listaCompras
    public HashSet<Compra> getListaCompras() {
        return this.listaCompras;
    }

    public void setListaCompras(HashSet<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public HashSet<Obra> getListaObras() {
        return this.listaObras;
    }

    // Buscar obra por titulo
    public void buscarObra(String titulo) {
        for (Obra obra : this.listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                System.out.println(obra);
            }
        }
    }

    // Buscar obra por código
    public Obra buscarObra(long codigo) {
        for (Obra obra : this.listaObras) {
            if (obra.getCodigoObra() == codigo) {
                return obra;
            }
        }
        return null;
    }

    // BUSCAR SOLO POR AÑO
    public void buscarObra(Calendar fecha) {
        for (Obra obra : this.listaObras) {
            if (obra.getFecha().get(Calendar.YEAR) == fecha.get(Calendar.YEAR)) {
                System.out.println(obra);
            }
        }
    }

    public void buscarObraporArtista(String nombre_artista) {
        for (Obra obra : this.listaObras) {
            for (Artista artista : obra.getArtista()) {
                if (artista.getNombre().equals(nombre_artista)) {
                    System.out.println(obra);
                }
            }
        }
    }

    public Obra addObra(Obra obra) {
        if (this.buscarObra(obra.getCodigoObra()) == null) {
            this.listaObras.add(obra);
            return obra;
        } else {
            return null;
        }
    }

    public void modificarObra(long codigo) {
        Scanner input = new Scanner(System.in);
        Obra obra;
        obra = this.buscarObra(codigo);
        int criterio = 1;
        if (obra != null) {

            System.out.println("Modificando Obra *" + obra.getTitulo() + "*");
            System.out.println("1. Codigo: " + obra.getCodigoObra());
            System.out.println("2. Titulo: " + obra.getTitulo());
            System.out.println("3. Fecha: " + obra.getFecha().get(0)+" / "+ obra.getFecha().get(2)+" / "+ obra.getFecha().get(1));
            System.out.println("4. Precio referencia: " + obra.getPrecioRef());
            System.out.println("5. Dimensiones del cuadro: " + obra.getDimensiones());
            System.out.println("Que opcion desea ingresar: ");
            criterio = input.nextInt();

            switch (criterio) {

                case 1: {
                    System.out.println("Ingrese el codigo nuevo: ");
                    long newCodigo = input.nextLong();
                    if (this.buscarObra(newCodigo) == null) {
                        obra.setCodigoObra(newCodigo);
                        System.out.println(obra + "   " + obra.getCodigoObra());
                        return;
                    } else {
                        System.out.println("No se modifico");
                    }
                    break;
                }
                case 2: {
                    input.nextLine();
                    System.out.println("Ingrese Titulo nuevo: ");
                    String newTittle = input.nextLine();
                    obra.setTitulo(newTittle);
                    break;
                }
                // FALTA EL CASO 3
                case 3: {
                    System.out.print("Ingrese la fecha nueva (YY/MM/DD): ");
                    String fecha = input.nextLine();
                    fecha.split("/");
                    break;
                }
                case 4: {
                    System.out.println("Ingrese el nuevo precio de referencia: ");
                    float newPrecio = input.nextFloat();
                    obra.setPrecioRef(newPrecio);
                    break;
                }
                case 5: {
                    System.out.println("Ingrese la dimension: ");
                    String dimensiones = input.nextLine();
                    obra.setDimensiones(dimensiones);
                    break;
                }
                default:
                    System.out.println("Shit2");

            }
        } else {
            System.out.println("\nLa obra no existe");
        }
    }

    public void eliminarObra(long codigo) {
        Scanner input = new Scanner(System.in);
        Obra obra;
        obra = this.buscarObra(codigo);
        if (obra != null) {
            // RECTIFICAR QUE NO ESTE ASOCIADO A UNA COMPRA PARA ELIMINARLO(FALTA LA CLASE
            // COMPRA)
            /* INCOMPLETO-FALTA EL CONDICIONAL Y LUEGO SI ELIMINAR- */
            if (this.listaObras.remove(obra)) {
                System.out.println("Se ha eliminado con exito la obra");
            }
        } else {
            System.err.println("No se encuentra la obra");
        }
    }

    // Imprimir listaClientes
    public void printClientes() {
        System.out.println("Imprimiendo la lista de Clientes:");
        for (Cliente cliente : this.listaClientes) {
            System.out.println(cliente);
        }
    }

    public boolean existeCodCompra(long cod) {
        for (Compra compra : this.listaCompras) {
            if (compra.getCodigoCompra() == cod) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarClienteYObraEnCompra(Cliente cliente, Obra obra) {
        for (Compra compra : this.listaCompras) {
            if (compra.getCliente() == cliente && compra.getObra() == obra) {
                return true;
            }
        }
        return false;
    }

    /*
     * 12.[5]Eliminar compra de obra a.Solicitar el número de compra a eliminar, si
     * este no existe, se debe mostrar un mensaje y volver al menú principal. b.Se
     * debe mostrar un mensaje de confirmación para eliminar la compra
     */
    public Compra eliminCompra() {
        long codigo;
        Compra compra = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese codigo de la compra");
        codigo = sc.nextLong();
        for (Compra c : this.listaCompras) {
            if (c.getCodigoCompra() == codigo && compra == null)
                compra = c;
        }
        if (compra == null) {
            System.out.println("La compra no existe");
            return compra;
        }
        System.out.println("Seguro? (0/1)");
        codigo = sc.nextLong();
        if (codigo == 0)
            return null;
        this.listaCompras.remove(compra);
        return compra;
    }

    // Realizar una compra
    public void realizarCompra() {
        Compra comp;
        Cliente clien;
        long codigo;
        Obra obr;
        Calendar fecha = Calendar.getInstance();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese codigo del cliente");
        codigo = sc.nextLong();
        clien = this.buscarCliente(codigo);
        if (clien == null)
            return;
        System.out.println("Ingrese codigo de la obra");
        codigo = sc.nextLong();
        obr = this.buscarObra(codigo);
        if (obr == null)
            return;
        if (this.buscarClienteYObraEnCompra(clien, obr)) {
            System.out.println("Esta compra ya existe en el sistema");
            return;
        }
        long cod;
        cod = this.listaCompras.size() - 1;
        do {
            cod += 1;
        } while (this.existeCodCompra(cod));
        comp = new Compra(cod, fecha, true);
        comp.setCliente(clien);
        comp.setObra(obr);
        this.listaCompras.add(comp);
    }

    // Imprimir listaCompras
    public void printCompras() {
        System.out.println("Imprimiendo la lista de Compras:");
        for (Compra compra : this.listaCompras) {
            System.out.println(compra);
        }
    }

    // Imprimir listaObras
    public void printObras() {
        System.out.println("Imprimiendo la lista de Obras:");
        for (Obra obra : this.listaObras) {
            System.out.println(obra);
        }
    }

    /*
     * 14. [5] Ver listado de Compras para un mes y año específico insertado por el
     * usuario a. Se debe solicitar mes y año al usuario y mostrar listado de Obras
     * que hayan sido compradas, cliente que la compró, fecha y precio.
     */
    public void listadoDeCompra(int mes, int ano) {
        for (Compra compra : this.listaCompras) {
            if ((compra.getFecha().get(Calendar.YEAR) == ano) && (compra.getFecha().get(Calendar.MONTH) == mes)) {
                System.out.println("Obra: " + compra.getObra().getTitulo());
                System.out.println("Comprador: " + compra.getCliente().getNombre());
                System.out.println("Fecha: " + compra.getFecha());
                System.out.println("Precio: " + compra.getObra().getPrecioRef());
            }
        }
    }

    /*
     * 15. [5] Ver listado de Artistas más vendidos a. Mostrar los artistas más
     * vendidos ordenados de mayor a menor ventas
     */
    public void verListadoArtistas() {

        HashMap<Artista, Integer> mapsold = new HashMap<Artista, Integer>();
        HashSet<Artista> artistas;
        TreeMap<Integer, Artista> sort = new TreeMap<Integer, Artista>();
        for (Compra compra : this.listaCompras) {
            // Así busco un elemento un en el hashmap
            // Si no lo encuentraS
            artistas = compra.getObra().getArtista();
            for (Artista art : artistas) {
                if (mapsold.containsKey(art)) {
                    mapsold.replace(art, mapsold.get(art) + 1);
                } else {
                    mapsold.put(art, 1);
                }
            }
        }
        /*
         * System.out.println("\nSorted Map......By Key"); Map<String, String> treeMap =
         * new TreeMap<String, String>(unsortMap); printMap(treeMap);
         */
    }

    // Main (Solo para probar que todo funcione bien)
    public static void main(String[] args) {
        ControlGaleria controlGaleria = new ControlGaleria();
        //Añado todo lo inicial
        controlGaleria.startDay();
        controlGaleria.printObras();
        controlGaleria.modificarObra(123456);
        controlGaleria.eliminarObra(765432);
        controlGaleria.printObras();
        System.err.println();
        controlGaleria.printClientes();
        controlGaleria.modificarCliente();
        controlGaleria.eliminarCliente();
        controlGaleria.printClientes();
    }

    // Constructor
    public ControlGaleria() {
        this.gestionClientes = new GestionClientes();
        this.gestionObras = new GestionObras();
        this.listaClientes = new HashSet<Cliente>();
        this.listaCompras = new HashSet<Compra>();
        this.listaObras = new HashSet<Obra>();
    }
}
