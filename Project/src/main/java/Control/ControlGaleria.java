package Control;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import Model.*;

public class ControlGaleria {
    // Colecciones
    private HashSet<Obra> listaObras;
    private HashSet<Cliente> listaClientes;
    private HashSet<Compra> listaCompras;
    private HashSet<Artista> listaArtistas;
    //Controladores
    private GestionObras gestionObras;
    private GestionClientes gestionClientes;
    // Métodos
    // Accessors
    // gestionObras
    public GestionObras getGestionObras() {
        return this.gestionObras;
    }

    public void setGestionObras(GestionObras gestionObras) {
        this.gestionObras = gestionObras;
    }

    // Lista Artistas
    public HashSet<Artista> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(HashSet<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }
    //Agregar un artista
    public Artista agregarArtista(Artista artista) {
        for (Artista art : this.listaArtistas) {
            if (art.equals(artista))
                return null;
        }
        this.listaArtistas.add(artista);
        return artista;
    }

    // gestionClientes
    public GestionClientes getGestionClientes() {
        return this.gestionClientes;
    }

    public void setGestionClientes(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    // listaCompras
    public HashSet<Compra> getListaCompras() {
        return this.listaCompras;
    }

    public void setListaCompras(HashSet<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    // listaObras
    public HashSet<Obra> getListaObras() {
        return this.listaObras;
    }

    public void setListaObras(HashSet<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    // listaClientes
    public HashSet<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    public void setListaClientes(HashSet<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    // Este método añade Clientes, Obras y Compras a la galería
    public void startDay() {
        for (Cliente c : this.gestionClientes.listaClientes()) {
            if (c != null)
                this.listaClientes.add(c);
        }
        for (Artista c : this.gestionObras.startArtistas()) {
            if (c != null) {
                this.listaArtistas.add(c);
            }
        }
        for (Obra c : this.gestionObras.listaObras()) {
            if (c != null)
                this.listaObras.add(c);
        }
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
    public void eliminarCliente(long codigo) {
        Cliente c=this.buscarCliente(codigo);
        try {
            
            if (c == null) {
                System.err.println("No existe");
                return;
            }
            this.listaClientes.remove(c);
        } catch (Exception e) {
        }
        this.organizarListaClientes();
    }

    // Agregar Cliente
    public Cliente addCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
        this.organizarListaClientes();
        return cliente;
    }
    //Crear un cliente
    public void crearCliente() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        Cliente c;
        String codigoCliente;
        System.out.println("Ingrese el codigo del cliente");
        codigoCliente = sc.next();
        if (Integer.parseInt(codigoCliente) < 0) {
            System.out.println("Codigo invalido");
            return;
        }
        c = this.buscarCliente(Long.parseLong(codigoCliente));
        if (c != null) {
            System.out.println("Ya existe un cliente con este codigo");
            return;
        }
        String cedula;
        System.out.println("Ingrese la cedula del cliente");
        cedula = sc.next();
        if (cedula.length() < 7) {
            System.out.println("Cedula invalida");
            return;
        }
        c = this.buscarCliente(Long.parseLong(cedula), "cc");
        if (c != null) {
            System.out.println("Ya existe un cliente con esta cedula");
            return;
        }
        String nombres;
        System.out.println("Ingrese los nombres del cliente");
        nombres = sc.next();
        String apellidos;
        System.out.println("Ingrese los apellidos del cliente");
        apellidos = sc.next();
        String direccionEntrega;
        System.out.println("Ingrese la direccion de entrega del cliente");
        direccionEntrega = sc.next();
        String telefono;
        System.out.println("Ingrese el telefono del cliente");
        telefono = sc.next();
        c = new Cliente(Long.parseLong(codigoCliente), Long.parseLong(cedula), nombres, apellidos, direccionEntrega,
                Long.parseLong(telefono));
        c = this.addCliente(c);
        this.organizarListaClientes();
    }

    // Modificar Cliente
    public void modificarCliente(Cliente cliente, int respuesta, String valor) {
        System.out.println();
        switch (respuesta){
            case 1:
                cliente.setCodigoCliente(Long.parseLong(valor));
                break;
            case 2:
                cliente.setCedula(Long.parseLong(valor));
                break;
            case 3:
                cliente.setNombre(valor);
                break;
            case 4:
                cliente.setApellidos(valor);
                break;
            case 5:
                cliente.setDireccionEntrega(valor);
                break;
            case 6:
                cliente.setTelefono(Long.parseLong(valor));
                break;
            default:
                break;
        }
        return;
    }

    // Imprimir listaClientes
    public void printClientes() {
        System.out.println("Imprimiendo la lista de Clientes:");
        for (Cliente cliente : this.listaClientes) {
            System.out.println(cliente);
        }
    }

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

    // Organizar la lista de clientes (Para mantener un orden)
    public void organizarListaClientes() {
        TreeSet<Cliente> nuevo = new TreeSet<Cliente>();
        nuevo.addAll(this.listaClientes);
        this.listaClientes.clear();
        this.listaClientes.addAll(nuevo);
        nuevo = null;
    }

    // Obras
    // Buscar obra por titulo
    public void buscarObra(String titulo) {
        for (Obra obra : this.listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                if (!this.obraEnCompra(obra))
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
                if (!this.obraEnCompra(obra))
                    System.out.println(obra);
            }
        }
    }
    //Ver si una obra ya fue comprada
    public boolean obraEnCompra(Obra obra) {
        for (Compra compra : this.listaCompras) {
            if (compra.getObra().equals(obra)) {
                return true;
            }
        }
        return false;
    }
    //Ver si una obra le pertenece a un artista
    public void buscarObraporArtista(String nombre_artista) {
        for (Obra obra : this.listaObras) {
            for (Artista artista : obra.getArtista()) {
                if (artista.getNombre().equals(nombre_artista)) {
                    System.out.println(obra);
                }
            }
        }
    }
    //Añade una obra a un artista y viceversa
    public void addCircObryArt(Obra o, Artista a) {
        o.getArtista().add(a);
        this.listaObras.add(o);
        a.getObras().add(o);
        this.listaArtistas.add(a);
    }
    //Insertar una obra
    public void insertarObra(String Titulo,
    String precioRef, String cedula,
    String codigoObra,
    String dimensiones, String ano, String mes, String dia, Artista artista) {
    System.out.println();
        
        
        Calendar fecha = Calendar.getInstance();
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Obra(Long.parseLong(codigoObra), Titulo, fecha, Float.parseFloat(precioRef), dimensiones);
        this.addCircObryArt(obra, artista);
        this.printArtistas();
    }
    //Insertar una obra
    public void insertarObra(String Titulo,
    String precioRef, String cedula,
    String codigoObra,
    String dimensiones, String ano, String mes, String dia, String nombre, String apellido, String telefono) {
        System.out.println();
        Calendar fecha = Calendar.getInstance();
        Artista artista=new Artista(Long.parseLong(cedula), nombre, apellido, Integer.parseInt(telefono));
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Obra(Long.parseLong(codigoObra), Titulo, fecha, Float.parseFloat(precioRef), dimensiones);
        this.addCircObryArt(obra, artista);
        this.printArtistas();
    }
    //Busca un artista
    public Artista buscarArtista(long cedula) {
        for (Artista a : this.listaArtistas) {
            if (a.getCedula() == cedula)
                return a;
        }
        return null;
    }
    //Imprime los artistas
    public void printArtistas() {
        System.out.println("Lista de artistas:");
        for (Artista artista : this.listaArtistas) {
            System.out.println(artista);
        }
    }
    //Añade una obra
    public Obra addObra(Obra obra) {
        if (this.buscarObra(obra.getCodigoObra()) == null) {
            this.listaObras.add(obra);
            return obra;
        } else {
            return null;
        }
    }
    //Modifica una obra
    public void modificarObra(Obra obra, int criterio, String value) {

        switch (criterio) {
            case 1: {
                if (this.buscarObra(Long.parseLong(value)) == null) {
                    if (value.length() == 7) {
                        obra.setCodigoObra(Long.parseLong(value));
                        System.out.println(obra + "   " + obra.getCodigoObra());
                    } else {
                        System.out.println("No se modifico el tamaño del codigo debe ser de 7");
                    }
                } else
                    System.out.println("No se modifico");

                break;
            }
            case 2: {
                obra.setTitulo(value);
                break;
            }
            case 3: {
                String[] fecha;
                fecha=value.split("/");
                obra.setFecha(Integer.parseInt(fecha[0]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[2]));

                break;
            }
            case 4: {
                obra.setPrecioRef(Long.parseLong(value));
                break;
            }
            case 5: {
                obra.setDimensiones(value);
                break;
            }
            case 6: {
                return;
            }
            default:
                System.out.println("Opcion incorrecta");

        }
    }
    // Imprimir listaObras
    public void listaObras() {
        System.out.println("Imprimiendo la lista de Obras:");
        if (this.listaCompras.isEmpty()) {
            for (Obra obra : this.listaObras) {
                System.out.println(obra);
            }
            return;
        } else {
            for (Obra obra : this.listaObras) {
                if (!this.obraEnCompra(obra))
                    System.out.println(obra);
            }
        }
    }

    // Compras
    public boolean existeCodCompra(long cod) {
        for (Compra compra : this.listaCompras) {
            if (compra.getCodigoCompra() == cod) {
                return true;
            }
        }
        return false;
    }
    //Busca un cliente en las compras
    public boolean buscarClienteYObraEnCompra(Cliente cliente, Obra obra) {
        for (Compra compra : this.listaCompras) {
            if (compra.getCliente() == cliente && compra.getObra() == obra) {
                return true;
            }
        }
        return false;
    }
    public void eliminarObra(long codigo) {
        Scanner sc = new Scanner(System.in);
        Obra obra;
        obra = this.buscarObra(codigo);
        if (obra != null) {
            if (!this.obraEnCompra(obra)) {
                this.listaObras.remove(obra);
                System.out.println("Se ha eliminado con exito la obra");
                return;
            }
        } else {
            System.err.println("No se encuentra la obra");
        }
    }

    /*
     * 12.[5]Eliminar compra de obra a.Solicitar el número de compra a eliminar, si
     * este no existe, se debe mostrar un mensaje y volver al menú principal. b.Se
     * debe mostrar un mensaje de confirmación para eliminar la compra
     */
    public Compra buscarCompra(String codigo){
        Compra compra=null;
        for (Compra c : this.listaCompras) {
            if (c.getCodigoCompra() == Long.parseLong(codigo) && compra == null)
                compra = c;
        }
        return compra;
    }
    public Compra eliminCompra(String codigo) {
        Compra compra=this.buscarCompra(codigo);
        this.listaCompras.remove(compra);
        return compra;
    }
    //Busca una obra en las compras
    public boolean buscarObraEnCompras(Obra obra) {
        boolean existe = false;
        for (Compra c : this.listaCompras) {
            if (!existe && c.getObra().getCodigoObra() == obra.getCodigoObra())
                existe = false;
        }
        return existe;
    }

    // Realizar una compra
    public void realizarCompra(Cliente clien, Obra obr) {
        Compra comp;
        long cod;
        Calendar fecha = Calendar.getInstance();
        cod = this.listaCompras.size();
        do {
            cod += 1;
        } while (this.existeCodCompra(cod));
        comp = new Compra(cod, fecha, true);
        comp.setCliente(clien);
        comp.setObra(obr);
        this.listaCompras.add(comp);
    }

    /*
     * 14. [5] Ver listado de Compras para un mes y año específico insertado por el
     * usuario a. Se debe solicitar mes y año al usuario y mostrar listado de Obras
     * que hayan sido compradas, cliente que la compró, fecha y precio.
     */
    public void listadoDeCompra(String mes, String anio) {
        for (Compra compra : this.listaCompras) {
            if ((compra.getFecha().get(Calendar.YEAR) == Integer.parseInt(anio))
                    && (compra.getFecha().get(Calendar.MONTH) == Integer.parseInt(mes))) {
                System.out.println("Obra: " + compra.getObra().getTitulo());
                System.out.println("Comprador: " + compra.getCliente().getNombre());
                System.out.println("Fecha: " + compra.getFecha().get(Calendar.DATE) + '/'
                        + compra.getFecha().get(Calendar.MONTH) + '/' + compra.getFecha().get(Calendar.YEAR));
                System.out.println("Precio: " + compra.getObra().getPrecioRef());
            }
        }
    }

    // Imprimir listaCompras
    public void printCompras() {
        System.out.println("Imprimiendo la lista de Compras:");
        for (Compra compra : this.listaCompras) {
            System.out.println(compra);
        }
    }

    // Artistas
    /*
     * 15. [5] Ver listado de Artistas más vendidos a. Mostrar los artistas más
     * vendidos ordenados de mayor a menor ventas
     */
    public void verListadoArtistas() {
        HashMap<Artista, Integer> mapsold = new HashMap<Artista, Integer>();
        HashSet<Artista> artistas = new HashSet<Artista>();
        Map<Integer, Artista> sort = new TreeMap<Integer, Artista>(Collections.reverseOrder());
        for (Compra compra : this.listaCompras) {
            artistas = compra.getObra().getArtista();
            for (Artista art : artistas) {
                if (mapsold.containsKey(art)) {
                    mapsold.put(art, mapsold.get(art) + 1);
                } else {
                    mapsold.put(art, 1);
                }
            }
        }
        for (Map.Entry<Artista, Integer> entry : mapsold.entrySet()) {
            sort.put(entry.getValue(), entry.getKey());
        }
        System.out.println("Imprimiendo ");
        // https://devqa.io/4-different-ways-iterate-map-java/
        for (Artista a : sort.values()) {
            System.out.println(a);
        }
        // https://howtodoinjava.com/java/sort/java-sort-map-by-key/
        // https://es.stackoverflow.com/questions/2464/c%C3%B3mo-iterar-a-trav%C3%A9s-de-un-hashmap
    }

    // Constructor
    public ControlGaleria() {
        this.gestionClientes = new GestionClientes();
        this.gestionObras = new GestionObras();
        this.listaArtistas = new HashSet<Artista>();
        this.listaClientes = new HashSet<Cliente>();
        this.listaCompras = new HashSet<Compra>();
        this.listaObras = new HashSet<Obra>();
        this.startDay();
    }
}