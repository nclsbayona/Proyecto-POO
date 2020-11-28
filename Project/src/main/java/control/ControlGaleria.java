package control;

import java.io.FileWriter;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import model.*;
import exceptions.*;

public class ControlGaleria {
    // Colecciones
    private HashSet<Obra> listaObras;
    private HashMap<Long, Cliente> listaClientes;
    private HashSet<Compra> listaCompras;
    private HashMap<Long, Artista> listaArtistas;
    // Controladores
    private GestionObras gestionObras;
    private GestionClientes gestionClientes;

    // Constructor
    public ControlGaleria() {
        this.gestionClientes = new GestionClientes();
        this.gestionObras = new GestionObras();
        this.listaArtistas = new HashMap<Long, Artista>();
        this.listaClientes = new HashMap<Long, Cliente>();
        this.listaCompras = new HashSet<Compra>();
        this.listaObras = new HashSet<Obra>();
        this.startDay();
    }

    // Añade una obra a un artista y viceversa
    public boolean addCircObryArt(Obra o, Artista a) throws TypoException {
        try {
            this.buscarObra(o.getCodigoObra());
        } catch (ArtworkDoesntExistException e) {
            o.getArtista().add(a);
            this.listaObras.add(o);
            a.getObras().add(o);
            this.listaArtistas.put(a.getCedula(), a);
            return true;
        }
        throw new TypoException();
    }

    // Agregar Cliente
    public Cliente addCliente(Cliente cliente) throws ClientExistsException {
        try {
            this.buscarCliente(cliente.getCodigoCliente());
        } catch (ClientNotFoundException e) {
            try {
                this.buscarCliente(cliente.getCedula(), "");
            } catch (ClientNotFoundException e2) {
                this.listaClientes.put(cliente.getCedula(), cliente);
                return cliente;
            }
        }
        throw new ClientExistsException();
    }

    // Añade una obra
    public Obra addObra(Obra obra) throws ArtworkExistsException {
        try {
            this.buscarObra(obra.getCodigoObra());
        } catch (ArtworkDoesntExistException e) {
            this.listaObras.add(obra);
            return obra;
        }
        throw new ArtworkExistsException(String.valueOf(obra.getCodigoObra()));
    }

    // Agregar un artista
    public Artista agregarArtista(Artista artista) throws ArtistExistsException {
        for (Artista art : this.listaArtistas.values()) {
            if (art.equals(artista))
                throw new ArtistExistsException();
        }
        this.listaArtistas.put(artista.getCedula(), artista);
        return artista;
    }

    // Busca un artista
    public Artista buscarArtista(long cedula) throws EmptyArtistListException, ArtistNotFoundException {
        try {
            for (Artista a : this.listaArtistas.values()) {
                if (a.getCedula() == cedula)
                    return a;
            }
        } catch (NullPointerException e) {
            throw new EmptyArtistListException();
        }
        throw new ArtistNotFoundException(String.valueOf(cedula));
    }

    // Buscar un cliente
    public Cliente buscarCliente(long codigoCliente) throws ClientNotFoundException {
        try {
            for (Cliente cliente : this.listaClientes.values()) {
                if (cliente.getCodigoCliente() == codigoCliente) {
                    return cliente;
                }
            }
        } catch (NullPointerException e) {
        }
        throw new ClientNotFoundException(String.valueOf(codigoCliente));
    }

    // Buscar un cliente por cedula
    public Cliente buscarCliente(long cedula, String s) throws ClientNotFoundException {
        try {
            for (Cliente cliente : this.listaClientes.values()) {
                if (cliente.getCedula() == cedula) {
                    return cliente;
                }
            }
        } catch (Exception e) {
        }
        throw new ClientNotFoundException(String.valueOf(cedula));
    }

    // Busca un cliente en las compras
    public boolean buscarClienteYObraEnCompra(Cliente cliente, Obra obra)
            throws EmptyPurchasesListException, PurchaseNotFoundException {
        try {
            for (Compra compra : this.listaCompras) {
                if (compra.getCliente() == cliente && compra.getObra() == obra) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new EmptyPurchasesListException();
        }
        if (this.listaCompras.size() == 0)
            throw new EmptyPurchasesListException();
        throw new PurchaseNotFoundException();
    }

    /*
     * 12.[5]Eliminar compra de obra a.Solicitar el número de compra a eliminar, si
     * este no existe, se debe mostrar un mensaje y volver al menú principal. b.Se
     * debe mostrar un mensaje de confirmación para eliminar la compra
     */
    public Compra buscarCompra(String codigo) throws EmptyPurchasesListException, PurchaseNotFoundException {
        try {
            for (Compra c : this.listaCompras) {
                if (c.getCodigoCompra() == Long.parseLong(codigo))
                    return c;
            }
        } catch (Exception e) {
            throw new EmptyPurchasesListException();
        }
        throw new PurchaseNotFoundException();
    }

    // Retorna todas las esculturas en el sistema
    public TreeSet<Escultura> buscarEsculturas() {
        TreeSet<Escultura> esculturas = new TreeSet<>();
        for (Obra o : this.listaObras)
            if (o instanceof Escultura)
                esculturas.add((Escultura) (o));
        return esculturas;
    }

    // BUSCAR SOLO POR AÑO
    public HashSet<Obra> buscarObra(Calendar fecha) {
        HashSet<Obra> obras = new HashSet<>();
        for (Obra obra : this.listaObras) {
            if (obra.getFecha().get(Calendar.YEAR) == fecha.get(Calendar.YEAR)) {
                try {
                    this.obraEnCompra(obra);
                } catch (PurchaseDoesntExistException e) {
                    obras.add(obra);
                }
            }
        }
        return obras;
    }

    // Buscar obra por código
    public Obra buscarObra(long codigo) throws ArtworkDoesntExistException {
        for (Obra obra : this.listaObras) {
            if (obra.getCodigoObra() == codigo) {
                return obra;
            }
        }
        throw new ArtworkDoesntExistException(String.valueOf(codigo));
    }

    // Obras
    // Buscar obra por titulo
    public HashSet<Obra> buscarObra(String titulo) {
        HashSet<Obra> obras = new HashSet<>();
        for (Obra obra : this.listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                try {
                    this.obraEnCompra(obra);
                } catch (PurchaseDoesntExistException e) {
                    obras.add(obra);
                }
            }
        }
        return obras;
    }

    // Busca una obra en las compras
    public boolean buscarObraEnCompras(Obra obra) throws ArtworkNotPurchasedException {
        for (Compra c : this.listaCompras) {
            if (c.getObra().getCodigoObra() == obra.getCodigoObra())
                return true;
        }
        throw new ArtworkNotPurchasedException(String.valueOf(obra.getCodigoObra()));
    }

    // Ver si una obra le pertenece a un artista
    public HashSet<Obra> buscarObraporArtista(String nombre_artista) {
        HashSet<Obra> obras = new HashSet<>();
        for (Obra obra : this.listaObras) {
            for (Artista artista : obra.getArtista()) {
                if (artista.getNombre().equals(nombre_artista)) {
                    obras.add(obra);
                }
            }
        }
        return obras;
    }

    // Retorna el precio total de todas las obras en el sistema
    public double calcularPrecioTotal() {
        double precio = 0;
        for (Compra co : this.listaCompras) {
            if (co.getPagado())
                precio += co.getObra().calcularPrecio();
        }
        return precio;
    }

    // Retorna todas las compras asociadas a una Obra tipo Cuadro en el sistema
    public HashSet<Compra> comprasAsociadasACuadro() throws EmptyPurchasesListException {
        if (this.listaCompras.size() == 0)
            throw new EmptyPurchasesListException();
        HashSet<Compra> compras = new HashSet<Compra>();
        for (Compra c : this.listaCompras)
            if (c.getObra() instanceof Cuadro)
                compras.add(c);
        return compras;
    }

    // Crear un cliente
    public boolean crearCliente(int codigoC, long cedula, String nombre, String apellido, String direccion,
            long telefono) throws ClientExistsException {

        try {
            this.buscarCliente(codigoC);
        } catch (ClientNotFoundException e) {
        }
        try {
            this.buscarCliente(cedula, "");
        } catch (ClientNotFoundException e) {
        }
        Cliente c = new Cliente(codigoC, cedula, nombre, apellido, direccion, telefono);
        try {
            c = this.addCliente(c);
        } catch (ClientExistsException ce) {
            throw ce;
        }
        return true;
    }

    // Eliminar Cliente
    public boolean eliminarCliente(long codigo) throws ClientNotFoundException {
        Cliente c = this.buscarCliente(codigo);
        this.listaClientes.remove(c.getCedula());
        return true;
    }

    public boolean eliminarObra(long codigo) throws ArtworkDoesntExistException {
        Obra obra;
        obra = this.buscarObra(codigo);
        if (obra != null) {
            try {
                this.obraEnCompra(obra);
            } catch (PurchaseDoesntExistException e) {
                this.listaObras.remove(obra);
                return true;
            }
        }
        throw new ArtworkDoesntExistException(String.valueOf(codigo));
    }

    public Compra eliminCompra(String codigo) throws EmptyPurchasesListException, PurchaseNotFoundException {
        Compra compra = this.buscarCompra(codigo);
        this.listaCompras.remove(compra);
        return compra;
    }

    public HashSet<Obra> listaObrasDisponibles(){
        HashSet<Obra> obras=new HashSet<>();
        for (Obra obra : this.getListaObras()) {
            try {
                this.obraEnCompra(obra);
            } catch (PurchaseDoesntExistException e) {
                obras.add(obra);
            }
        }
        return obras;
    }

    // Compras
    public boolean existeCodCompra(long cod) throws PurchaseDoesntExistException {
        for (Compra compra : this.listaCompras) {
            if (compra.getCodigoCompra() == cod) {
                return true;
            }
        }
        throw new PurchaseDoesntExistException(String.valueOf(cod));
    }

    // gestionClientes
    public GestionClientes getGestionClientes() {
        return this.gestionClientes;
    }

    // Métodos
    // Accessors
    // gestionObras
    public GestionObras getGestionObras() {
        return this.gestionObras;
    }

    // Lista Artistas
    public HashMap<Long, Artista> getListaArtistas() {
        return listaArtistas;
    }

    // listaClientes
    public HashMap<Long, Cliente> getListaClientes() {
        return this.listaClientes;
    }

    // listaCompras
    public HashSet<Compra> getListaCompras() {
        return this.listaCompras;
    }

    // listaObras
    public HashSet<Obra> getListaObras() {
        return this.listaObras;
    }

    public void setGestionClientes(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    public void setGestionObras(GestionObras gestionObras) {
        this.gestionObras = gestionObras;
    }

    public void setListaArtistas(HashMap<Long, Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public void setListaClientes(HashMap<Long, Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void setListaCompras(HashSet<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public void setListaObras(HashSet<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    // Insertar una instalacion
    // Recibe artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, Artista artista, String descripcion) throws TypoException {
        Calendar fecha = Calendar.getInstance();
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Instalacion(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                dimensiones, descripcion);
        this.addCircObryArt(obra, artista);
        return true;
    }

    // Insertar una escultura
    // Recibe artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, Artista artista, String material, double peso) throws TypoException {
        Calendar fecha = Calendar.getInstance();
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Escultura(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                dimensiones, material, peso);
        this.addCircObryArt(obra, artista);
        return true;
    }

    /*
     * //Insertar una obra public void insertarObra(String Titulo, String precioRef,
     * String cedula, String codigoObra, String dimensiones, String ano, String mes,
     * String dia, String nombre, String apellido, String telefono) {
     * System.out.println(); Calendar fecha = Calendar.getInstance(); Artista
     * artista=new Artista(Long.parseLong(cedula), nombre, apellido,
     * Long.parseLong(telefono)); fecha.set(Integer.parseInt(ano),
     * Integer.parseInt(mes), Integer.parseInt(dia)); Obra obra = new
     * Obra(Long.parseLong(codigoObra), Titulo, fecha, Float.parseFloat(precioRef),
     * dimensiones); this.addCircObryArt(obra, artista); this.printArtistas(); }
     */
    // Insertar un cuadro
    // Recibe artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, Artista artista, String tema, String tecnica, Clasificacion valorA)
            throws TypoException {

        Calendar fecha = Calendar.getInstance();
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Cuadro(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                dimensiones, tema, tecnica, valorA);
        this.addCircObryArt(obra, artista);
        return true;
    }

    // Crea artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, String nombre, String apellido, String telefono, String descripcion)
            throws TypoException {

        Calendar fecha = Calendar.getInstance();
        Artista artista = new Artista(Long.parseLong(cedula), nombre, apellido, Long.parseLong(telefono));
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Instalacion(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                dimensiones, descripcion);
        this.addCircObryArt(obra, artista);
        return true;
    }

    // Crea artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, String nombre, String apellido, String telefono, String material,
            double peso) throws TypoException {
        Calendar fecha = Calendar.getInstance();
        Artista artista = new Artista(Long.parseLong(cedula), nombre, apellido, Long.parseLong(telefono));
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Escultura(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                dimensiones, material, peso);
        this.addCircObryArt(obra, artista);
        return true;
    }

    // Crea artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, String nombre, String apellido, String telefono, String tema,
            String tecnica, Clasificacion valorA) throws TypoException {
        Calendar fecha = Calendar.getInstance();
        Artista artista = new Artista(Long.parseLong(cedula), nombre, apellido, Long.parseLong(telefono));
        fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        Obra obra = new Cuadro(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                dimensiones, tema, tecnica, valorA);
        this.addCircObryArt(obra, artista);
        return true;

    }

    /*
     * 14. [5] Ver listado de Compras para un mes y año específico insertado por el
     * usuario a. Se debe solicitar mes y año al usuario y mostrar listado de Obras
     * que hayan sido compradas, cliente que la compró, fecha y precio.
     */
    public HashSet<String> listadoDeCompra(String mes, String anio) {
        HashSet<String> retornar = new HashSet<>();
        String append;
        for (Compra compra : this.listaCompras) {
            if ((compra.getFecha().get(Calendar.YEAR) == Integer.parseInt(anio))
                    && (compra.getFecha().get(Calendar.MONTH) == Integer.parseInt(mes))) {
                append = "";
                append += "Obra: " + compra.getObra().getTitulo();
                append += "\nComprador: " + compra.getCliente().getNombre();
                append += "\nFecha: " + compra.getFecha().get(Calendar.DATE) + '/'
                        + compra.getFecha().get(Calendar.MONTH) + '/' + compra.getFecha().get(Calendar.YEAR);
                append += "\nPrecio: " + compra.getObra().getPrecioRef();
                retornar.add(append);
            }
        }
        return retornar;
    }

    // Modificar Cliente
    public boolean modificarCliente(Cliente cliente, int respuesta, String valor)
            throws NumberFormatException, TypoException {
        switch (respuesta) {
            case 1:
                try {
                    this.buscarCliente(Long.parseLong(valor));
                    throw new TypoException();
                } catch (ClientNotFoundException e) {
                    cliente.setCodigoCliente(Long.parseLong(valor));
                }
                break;
            case 2:
                try {
                    this.buscarCliente(Long.parseLong(valor), "");
                    throw new TypoException();
                } catch (ClientNotFoundException e) {
                    cliente.setCedula(Long.parseLong(valor));
                }
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
                throw new TypoException("campo");
        }
        return true;
    }

    // Modifica una obra
    public boolean modificarObra(Obra obra, int criterio, String value) throws ArtworkDoesntExistException,
            CodeSizeException, TypoException, ArtworkExistsException, ArtworkAlreadyPurchasedException {
        boolean retornar = false;
        if (obra == null)
            throw new ArtworkDoesntExistException();
        try {
            if (this.buscarObraEnCompras(obra))
                throw new ArtworkAlreadyPurchasedException();
        } catch (ArtworkNotPurchasedException e) {
        }
        switch (criterio) {
            case 1: {
                try {
                    if (value.length() != 7)
                        throw new TypoException();
                    this.buscarObra(Long.parseLong(value));
                    throw new ArtworkExistsException();
                } catch (ArtworkDoesntExistException ae) {
                    if (value.length() == 7) {
                        obra.setCodigoObra(Long.parseLong(value));
                        retornar = true;
                    } else
                        throw new CodeSizeException();
                }
                break;
            }
            case 2: {
                obra.setTitulo(value);
                retornar = true;
                break;
            }
            case 3: {
                String[] fecha;
                fecha = value.split("/");
                obra.setFecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]));
                retornar = true;
                break;
            }
            case 4: {
                try {
                    this.buscarObraEnCompras(obra);
                } catch (ArtworkNotPurchasedException e) {
                    obra.setPrecioRef(Long.parseLong(value));
                    retornar = true;
                    break;
                }

            }
            case 5: {
                obra.setDimensiones(value);
                retornar = true;
                break;
            }
            default:
                throw new TypoException("campo");
        }
        return retornar;
    }

    // Ver si una obra ya fue comprada
    public boolean obraEnCompra(Obra obra) throws PurchaseDoesntExistException {
        for (Compra compra : this.listaCompras) {
            if (compra.getObra().equals(obra)) {
                return true;
            }
        }
        throw new PurchaseDoesntExistException(String.valueOf(obra.getCodigoObra()));
    }

    // Realizar una compra
    public boolean realizarCompra(Cliente clien, Obra obr)
            throws ArtworkDoesntExistException, ClientDoesntExistException {
        if (obr == null)
            throw new ArtworkDoesntExistException();
        if (clien == null)
            throw new ClientDoesntExistException();
        Compra comp;
        long cod;
        Calendar fecha = Calendar.getInstance();
        cod = this.listaCompras.size();
        boolean ac = true;
        do {
            cod += 1;
            try {
                this.existeCodCompra(cod);
            } catch (PurchaseDoesntExistException w) {
                ac = false;
            }
        } while (ac);
        try {
            this.buscarClienteYObraEnCompra(clien, obr);
        } catch (EmptyPurchasesListException | PurchaseNotFoundException e) {
            comp = new Compra(cod, fecha, true);
            comp.setCliente(clien);
            comp.setObra(obr);
            this.listaCompras.add(comp);
            return true;
        }
        return false;
    }

    // Este método añade Clientes, Obras y Artistas a la galería
    public void startDay() {
        for (Cliente c : this.gestionClientes.listaClientes().values()) {
            if (c != null)
                this.listaClientes.put(c.getCedula(), c);

        }
        for (Artista c : this.gestionObras.startArtistas().values()) {
            if (c != null) {
                this.listaArtistas.put(c.getCedula(), c);
            }
        }
        try {
            for (Obra c : this.gestionObras.listaObras()) {
                if (c != null)
                    this.listaObras.add(c);
            }
        } catch (TypoException e) {
        }
    }

    // Artistas
    /*
     * 15. [5] Ver listado de Artistas más vendidos a. Mostrar los artistas más
     * vendidos ordenados de mayor a menor ventas
     */
    public Map<Integer, Artista> verListadoArtistas() throws EmptyPurchasesListException {
        HashMap<Artista, Integer> mapsold = new HashMap<Artista, Integer>();
        HashSet<Artista> artistas = new HashSet<Artista>();
        Map<Integer, Artista> sort = new TreeMap<Integer, Artista>(Collections.reverseOrder());
        if (this.listaCompras.size()==0)
            throw new EmptyPurchasesListException();
        try {
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
        } catch (NullPointerException e) {
            throw new EmptyPurchasesListException();
        }
        artistas.clear();
        for (Map.Entry<Artista, Integer> entry : mapsold.entrySet()) {
            sort.put(entry.getValue(), entry.getKey());
        }
        return sort;
        // https://devqa.io/4-different-ways-iterate-map-java/
        // https://howtodoinjava.com/java/sort/java-sort-map-by-key/
        // https://es.stackoverflow.com/questions/2464/c%C3%B3mo-iterar-a-trav%C3%A9s-de-un-hashmap
    }

    // Exportar a xml
    public <T> boolean exportarReporteXML(String route, Class<T> clase, T info) throws TypoException {
        try (FileWriter out = new FileWriter(route)) {
            JAXBContext context = JAXBContext.newInstance(clase);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(info, out);
        } catch (Exception e) {
            throw new TypoException("ruta");
        }
        return true;
    }


}