package poo.project.Control;

import poo.project.Model.Artista;
import poo.project.Model.Obra;
import java.util.HashSet;
import java.util.Calendar;
import java.util.Scanner;

public class GestionObras {

    /*
     * Preguntar si se puede o no tener la misma tanto en control galeria como acá,
     * en cuyo caso se pueden quitar estos comentarios y texto basura y el de
     * control galería
     */
    private HashSet<Obra> listaObras;

    public HashSet<Obra> getListaObras() {
        return this.listaObras;
    }

    private void setListaObras(HashSet<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    // 1. Listar Obras Disponibles
    public void listarObrasDisponibles(){

        for (Obra obra : listaObras) {

            System.out.println(obra.getTitulo());

        }

    }

    // Buscar obra por titulo
    public void buscarObra(String titulo) {

        for (Obra obra : listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                System.out.println(obra);
            }
        }
    }

    // Buscar obra por código
    public Obra buscarObra(long codigo) {

        for (Obra obra : listaObras) {
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

    public void addListaObras(HashSet<Obra> listaObras) {

        this.setListaObras((this.listaObras == null) ? listaObras : this.listaObras);
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

    @Override
    public String toString() {
        String print = "\tLista de obras:Tam: " + this.listaObras.size() + "\n\n";
        for (Obra obra : this.listaObras) {
            print += obra.toString() + "\n";
        }
        return print;
    }
}
