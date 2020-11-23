package control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import model.*;
import excepciones.*;

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
    public boolean addCircObryArt(Obra o, Artista a) {
        if (this.buscarObra(o.getCodigoObra()) != null)
            o.getArtista().add(a);
        else
            return false;
        this.listaObras.add(o);
        a.getObras().add(o);
        this.listaArtistas.put(a.getCedula(), a);
        return true;
    }

    // Agregar Cliente
    public Cliente addCliente(Cliente cliente) throws ClientExistsException {
        try {
            this.buscarCliente(cliente.retCodigoCliente());
        } catch (ClientNotFoundException e) {
            throw new ClientExistsException(String.valueOf(cliente.retCodigoCliente()));
        }
        try {
            this.buscarCliente(cliente.retCedula(), "");
        } catch (ClientNotFoundException e) {
            throw new ClientExistsException(String.valueOf(cliente.retCedula()));
        }
        this.listaClientes.put(cliente.retCedula(), cliente);
        return cliente;
    }

    // Añade una obra
    public Obra addObra(Obra obra) {
        if (this.buscarObra(obra.getCodigoObra()) == null) {
            this.listaObras.add(obra);
            return obra;
        } else {
            return null;
        }
    }

    // Agregar un artista
    public Artista agregarArtista(Artista artista) {
        for (Artista art : this.listaArtistas.values()) {
            if (art.equals(artista))
                return null;
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
                if (cliente.retCodigoCliente() == codigoCliente) {
                    return cliente;
                }
            }
        } catch (NullPointerException e) {
        }
        throw new ClientNotFoundException(String.valueOf(codigoCliente));
    }

    // Buscar un cliente por cedula
    public Cliente buscarCliente(long cedula, String s) throws ClientNotFoundException{
        try{
            for (Cliente cliente : this.listaClientes.values()) {
                if (cliente.retCedula() == cedula) {
                    return cliente;
                }
        }
        }catch(Exception e){}
        throw new ClientNotFoundException(String.valueOf(cedula));
    }

    // Busca un cliente en las compras
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
    public Compra buscarCompra(String codigo) {
        Compra compra = null;
        for (Compra c : this.listaCompras) {
            if (c.getCodigoCompra() == Long.parseLong(codigo) && compra == null)
                compra = c;
        }
        return compra;
    }

    // Retorna todas las esculturas en el sistema
    public HashSet<Obra> buscarEsculturas() {
        HashSet<Obra> esculturas = new HashSet<Obra>();
        for (Obra o : this.listaObras)
            if (o instanceof Escultura)
                esculturas.add(o);
        return esculturas;
    }

    // BUSCAR SOLO POR AÑO
    public HashSet<Obra> buscarObra(Calendar fecha) {
        HashSet<Obra> obras = new HashSet<>();
        for (Obra obra : this.listaObras) {
            if (obra.getFecha().get(Calendar.YEAR) == fecha.get(Calendar.YEAR)) {
                if (!this.obraEnCompra(obra))
                    obras.add(obra);
            }
        }
        return obras;
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

    // Obras
    // Buscar obra por titulo
    public HashSet<Obra> buscarObra(String titulo) {
        HashSet<Obra> obras = new HashSet<>();
        for (Obra obra : this.listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                if (!this.obraEnCompra(obra))
                    obras.add(obra);
            }
        }
        return obras;
    }

    // Busca una obra en las compras
    public boolean buscarObraEnCompras(Obra obra) {
        boolean existe = false;
        for (Compra c : this.listaCompras) {
            if (!existe && c.getObra().getCodigoObra() == obra.getCodigoObra())
                existe = false;
        }
        return existe;
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
        for (Compra co : this.listaCompras)
            precio += co.getObra().calcularPrecio();
        return precio;
    }

    // Retorna todas las compras asociadas a una Obra tipo Cuadro en el sistema
    public HashSet<Compra> comprasAsociadasACuadro() {
        HashSet<Compra> compras = new HashSet<Compra>();
        for (Compra c : this.listaCompras)
            if (c.getObra() instanceof Cuadro)
                compras.add(c);
        return compras;
    }

    // Crear un cliente
    public boolean crearCliente(int codigoC, long cedula, String nombre, String apellido, String direccion,
            long telefono) {
        try {
            if (this.buscarCliente(codigoC) != null)
                throw new IllegalAccessException();
            if (this.buscarCliente(cedula, "") != null)
                throw new IllegalAccessException();
            Cliente c = new Cliente(codigoC, cedula, nombre, apellido, direccion, telefono);
            c = this.addCliente(c);
            if (c == null)
                throw new IllegalAccessException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Eliminar Cliente
    public boolean eliminarCliente(long codigo) throws ClientNotFoundException {
        Cliente c = this.buscarCliente(codigo);
        this.listaClientes.remove(c.retCedula());
        return true;
    }

    public boolean eliminarObra(long codigo) {
        boolean f = false;
        Obra obra;
        obra = this.buscarObra(codigo);
        if (obra != null) {
            if (!this.obraEnCompra(obra)) {
                this.listaObras.remove(obra);
                f = true;
            }
        }
        return f;
    }

    public Compra eliminCompra(String codigo) {
        Compra compra = this.buscarCompra(codigo);
        this.listaCompras.remove(compra);
        return compra;
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

    // Insertar una instalacion
    // Recibe artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, Artista artista, String descripcion) {
        try {
            Calendar fecha = Calendar.getInstance();
            fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
            Obra obra = new Instalacion(Long.parseLong(codigoObra), Titulo, fecha.getTime(),
                    Float.parseFloat(precioRef), dimensiones, descripcion);
            if (!this.addCircObryArt(obra, artista))
                throw new IllegalAccessException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Insertar una escultura
    // Recibe artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, Artista artista, String material, double peso) {
        try {
            Calendar fecha = Calendar.getInstance();
            fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
            Obra obra = new Escultura(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                    dimensiones, material, peso);
            if (!this.addCircObryArt(obra, artista))
                throw new IllegalAccessException();
            return true;
        } catch (Exception e) {
            return false;
        }
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
            String ano, String mes, String dia, Artista artista, String tema, String tecnica, Clasificacion valorA) {
        try {
            Calendar fecha = Calendar.getInstance();
            fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
            Obra obra = new Cuadro(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                    dimensiones, tema, tecnica, valorA);
            if (!this.addCircObryArt(obra, artista))
                throw new IllegalAccessException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Crea artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, String nombre, String apellido, String telefono, String descripcion) {
        try {
            Calendar fecha = Calendar.getInstance();
            Artista artista = new Artista(Long.parseLong(cedula), nombre, apellido, Long.parseLong(telefono));
            fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
            Obra obra = new Instalacion(Long.parseLong(codigoObra), Titulo, fecha.getTime(),
                    Float.parseFloat(precioRef), dimensiones, descripcion);
            if (!this.addCircObryArt(obra, artista))
                throw new IllegalAccessException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Crea artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, String nombre, String apellido, String telefono, String material,
            double peso) {
        try {
            Calendar fecha = Calendar.getInstance();
            Artista artista = new Artista(Long.parseLong(cedula), nombre, apellido, Long.parseLong(telefono));
            fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
            Obra obra = new Escultura(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                    dimensiones, material, peso);
            if (!this.addCircObryArt(obra, artista))
                throw new IllegalAccessException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Crea artista
    public boolean insertarObra(String Titulo, String precioRef, String cedula, String codigoObra, String dimensiones,
            String ano, String mes, String dia, String nombre, String apellido, String telefono, String tema,
            String tecnica, Clasificacion valorA) {
        try {
            Calendar fecha = Calendar.getInstance();
            Artista artista = new Artista(Long.parseLong(cedula), nombre, apellido, Long.parseLong(telefono));
            fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
            Obra obra = new Cuadro(Long.parseLong(codigoObra), Titulo, fecha.getTime(), Float.parseFloat(precioRef),
                    dimensiones, tema, tecnica, valorA);
            if (!this.addCircObryArt(obra, artista))
                throw new IllegalAccessException();
            return true;
        } catch (Exception e) {
            return false;
        }

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
                append += "\nComprador: " + compra.getCliente().retNombre();
                append += "\nFecha: " + compra.getFecha().get(Calendar.DATE) + '/'
                        + compra.getFecha().get(Calendar.MONTH) + '/' + compra.getFecha().get(Calendar.YEAR);
                append += "\nPrecio: " + compra.getObra().getPrecioRef();
                retornar.add(append);
            }
        }
        return retornar;
    }

    // Modificar Cliente
    public boolean modificarCliente(Cliente cliente, int respuesta, String valor) {
        try {
            if (this.buscarCliente(cliente.retCodigoCliente()) == null
                    || this.buscarCliente(cliente.retCedula(), "") == null)
                throw new IllegalAccessException();
            switch (respuesta) {
                case 1:
                    if (this.buscarCliente(Long.parseLong(valor)) != null)
                        throw new IllegalAccessException();
                    cliente.addCodigoCliente(Long.parseLong(valor));
                    break;
                case 2:
                    if (this.buscarCliente(Long.parseLong(valor), "") != null)
                        throw new IllegalAccessException();
                    cliente.addCedula(Long.parseLong(valor));
                    break;
                case 3:
                    cliente.addNombre(valor);
                    break;
                case 4:
                    cliente.addApellidos(valor);
                    break;
                case 5:
                    cliente.addDireccionEntrega(valor);
                    break;
                case 6:
                    cliente.addTelefono(Long.parseLong(valor));
                    break;
                default:
                    throw new IllegalAccessException();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Modifica una obra
    public boolean modificarObra(Obra obra, int criterio, String value) {
        boolean retornar = false;
        try {
            if (obra == null)
                throw new IllegalAccessException();
            switch (criterio) {
                case 1: {
                    if (this.buscarObra(Long.parseLong(value)) == null) {
                        if (value.length() == 7) {
                            obra.setCodigoObra(Long.parseLong(value));
                            retornar = true;
                        } else
                            retornar = false;
                    } else
                        retornar = false;

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
                    if (this.buscarObraEnCompras(obra))
                        throw new IllegalAccessException();
                    obra.setPrecioRef(Long.parseLong(value));
                    retornar = true;
                    break;
                }
                case 5: {
                    obra.setDimensiones(value);
                    retornar = true;
                    break;
                }
            }
            return retornar;
        } catch (Exception e) {
            return false;
        }
    }

    // Ver si una obra ya fue comprada
    public boolean obraEnCompra(Obra obra) {
        for (Compra compra : this.listaCompras) {
            if (compra.getObra().equals(obra)) {
                return true;
            }
        }
        return false;
    }

    // Realizar una compra
    public boolean realizarCompra(Cliente clien, Obra obr) {
        try {
            if (this.buscarObra(obr.getCodigoObra()) == null || obr == null || clien == null)
                throw new IllegalAccessException();
            Compra comp;
            long cod;
            Calendar fecha = Calendar.getInstance();
            cod = this.listaCompras.size();
            do {
                cod += 1;
            } while (this.existeCodCompra(cod));
            if (this.buscarClienteYObraEnCompra(clien, obr))
                throw new IllegalAccessException();
            comp = new Compra(cod, fecha, true);
            comp.setCliente(clien);
            comp.setObra(obr);
            this.listaCompras.add(comp);
            return true;
        } catch (Exception e) {
            return false;
        }
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

    // Este método añade Clientes, Obras y Artistas a la galería
    public void startDay() {
        for (Cliente c : this.gestionClientes.listaClientes().values()) {
            if (c != null)
                this.listaClientes.put(c.retCedula(), c);

        }
        for (Artista c : this.gestionObras.startArtistas().values()) {
            if (c != null) {
                this.listaArtistas.put(c.getCedula(), c);
            }
        }
        for (Obra c : this.gestionObras.listaObras()) {
            if (c != null)
                this.listaObras.add(c);
        }
    }

    // Artistas
    /*
     * 15. [5] Ver listado de Artistas más vendidos a. Mostrar los artistas más
     * vendidos ordenados de mayor a menor ventas
     */
    public Map<Integer, Artista> verListadoArtistas() {
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
        artistas.clear();
        for (Map.Entry<Artista, Integer> entry : mapsold.entrySet()) {
            sort.put(entry.getValue(), entry.getKey());
        }
        return sort;
        // https://devqa.io/4-different-ways-iterate-map-java/
        // https://howtodoinjava.com/java/sort/java-sort-map-by-key/
        // https://es.stackoverflow.com/questions/2464/c%C3%B3mo-iterar-a-trav%C3%A9s-de-un-hashmap
    }

    private BufferedWriter newBufferedWriter(String route) throws IllegalAccessException {
        FileWriter fw;
        try {
            fw = new FileWriter(route);
        } catch (Exception e) {
            throw new IllegalAccessException("Problem with file");
        }
        return new BufferedWriter(fw);
    }

    // Exportar a xml
    public <T> boolean exportarReporteXML(String route, Class<T> clase, Collection<T> collection) throws TypoException {
        boolean logrado = true;
        int counter = 0;
        System.err.println(clase);
        BufferedWriter bw = null;
        while ((bw == null) && (counter++ < 3)) {
            try {
                bw = this.newBufferedWriter(route);
            } catch (Exception e) {
                // Error abriendo el archivo
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e1) {
                        // No debería
                    }
                }
            }
        }
        if (bw == null) {
            logrado = false;
            throw new TypoException("ruta");
        }
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(clase);
        } catch (JAXBException e) {
            logrado = false;
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e1) {
                    // No debería
                }
            }
            e.printStackTrace();
            throw new TypoException("clase");
        }
        Marshaller m = null;
        try {
            m = context.createMarshaller();
        } catch (JAXBException e) {
            logrado = false;
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e1) {
                    // No debería
                }
            }
            e.printStackTrace();
            throw new TypoException("marshaller");
        }
        try {
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (PropertyException e1) {
            e1.printStackTrace();
        }
        for (T t : collection) {
            try {
                m.marshal(t, bw);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return logrado;
    }
}