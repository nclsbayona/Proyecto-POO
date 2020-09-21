package poo.project.View;
import poo.project.Control.*;
import poo.project.Model.Cliente;
import poo.project.Model.Obra;

import java.util.Calendar;
import java.util.Scanner;

public class PantallaGaleria {

    public static void prueba(){

        ControlGaleria controlGaleria = new ControlGaleria();

        Cliente pruebasClientes[] = new Cliente[4];
        GestionObras go = controlGaleria.getGestionObras();
        Obra obras[] = new Obra[3];
        Calendar proof = Calendar.getInstance();
        proof.set(2020, 11, 01);
        obras[0] = new Obra(1234567, "Mera", proof, 20000, "20x5");
        obras[1] = new Obra(5432198, "Okaloka", proof, 20000, "10x8");
        obras[2] = new Obra(7654321, "Machupichu", proof, 15000, "10x2");

        go.addObra(obras[0]);
        go.addObra(obras[1]);
        go.addObra(obras[2]);
        go.modificarObra(1234567);

    }

    public static void printMenu(){

        System.out.println("---------------------------");
        System.out.println("- Galeria de Arte Javeriana -");
        System.out.println("---------------------------");
        System.out.println("----------- Menu ----------");
        System.out.println(" ");

        System.out.println("-- Obras ------------------");
        System.out.println("1. Listar Obras Disponibles");
        System.out.println("2. Buscar Obra");
        System.out.println("3. Insertar Obra");
        System.out.println("4. Modificar Obra");
        System.out.println("5. Eliminar Obra");
        System.out.println(" ");

        System.out.println("-- Clientes ----------------");
        System.out.println("6. Listar Clientes");
        System.out.println("7. Buscar Cliente");
        System.out.println("8. Insertar Cliente");
        System.out.println("9. Modificar Cliente");
        System.out.println("10. Eliminar Cliente");
        System.out.println(" ");

        System.out.println("-- Compras ------------------");
        System.out.println("11. Compra de Obra");
        System.out.println("12. Eliminar Compra");
        System.out.println("13. Listar Compras");
        System.out.println("14. Listar Compras Filtado");
        System.out.println("15. Listar Artistas mas Vendidos");
        System.out.println("---------------------------------");
        System.out.println(" ");
        System.out.println("16. Salir");
        System.out.println(" ");
        System.out.println("---------------------------------");

    }

    public static void controlMenu(){
        Scanner entrada = new Scanner(System.in);
        GestionObras gestionObras = new GestionObras();
        GestionClientes gestionClientes = new GestionClientes();

        int opcionObras = 0;
        String buscarObraporArtista = " ";
        String buscarObraporTitulo =  " ";
        long buscarObraporCodigo = 0;
        Calendar buscarObraporFecha = null;


        System.out.println("Digita el Numero de la Opcion: ");
        int opcionSeleect = entrada.nextInt();
        System.out.println("---------------------------------");
        System.out.println(" ");

        // Logica de Menu
        switch (opcionSeleect) {

            //Obras
            case 1: {

                //Imprime por pantalla todas las obras Disponibles
                System.out.println("Lista De Obras Disponibles: ");
                System.out.println("---------------------------------");
                gestionObras.listarObrasDisponibles();
                System.out.println("---------------------------------");
                break;
            }
            case 2: {

                System.out.println("Busca Obras por: ");
                System.out.println(" ");

                System.out.println("1. Titulo");
                System.out.println("2. Autor");
                System.out.println("3. Codigo");
                System.out.println("4. Fecha");

                System.out.println("Selecciona la Opcion: ");
                opcionObras=entrada.nextInt();
                System.out.println(" ");

                switch (opcionObras){

                    case 1:{
                        entrada.nextLine();

                        System.out.println("Busca Obras por Titulo");
                        System.out.println("Escribe el Titulo:");
                        buscarObraporTitulo= entrada.nextLine();

                        System.out.println("--Obras del Titulo "+ buscarObraporTitulo);

                        gestionObras.buscarObra(buscarObraporTitulo);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        entrada.nextLine();

                        System.out.println("Busca Obras por Autor");
                        System.out.println("Escribe el Nombre:");
                        buscarObraporArtista= entrada.nextLine();

                        System.out.println("--Obras del Autor "+ buscarObraporArtista);

                        gestionObras.buscarObraporArtista(buscarObraporArtista);
                        System.out.println("---------------------------------");
                        break;

                    }
                    case 3:{
                        entrada.nextLong();

                        System.out.println("Busca Obras por Codigo");
                        System.out.println("Escribe el Codigo:");
                        buscarObraporCodigo= entrada.nextLong();

                        System.out.println("--Obra del Codigo "+ buscarObraporCodigo);

                        gestionObras.buscarObra(buscarObraporCodigo);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 4: {
                        entrada.nextLine();

                        System.out.println("Busca Obras por Fecha");
                        System.out.println("Escribe la Fecha:");
                        buscarObraporFecha = null; // Ingresar Fecha

                        System.out.println("--Obras de la Fecha "+ buscarObraporFecha);

                        gestionObras.buscarObra(buscarObraporFecha);
                        System.out.println("---------------------------------");
                        break;
                    }

                }
                break;//Case 2
            }
            case 3:{
                System.out.println("Insertar Obra");

                //Metodo Crear Obra

                gestionObras.addObra(null);
                System.out.println("---------------------------------");
                break;
            }
            case 4: {
                System.out.println("Modificar Obra");

                System.out.println("Escribe el Codigo:");
                buscarObraporCodigo= entrada.nextLong();
                gestionObras.modificarObra(buscarObraporCodigo);
                System.out.println("---------------------------------");
                break;
            }
            case 5:{
                System.out.println("Eliminar Obra");

                System.out.println("Escribe el Codigo:");
                buscarObraporCodigo= entrada.nextLong();
                gestionObras.eliminarObra(buscarObraporCodigo);
                System.out.println("---------------------------------");
                break;
            }

            //Clientes
            case 6:{
                System.out.println("Listar Cliente");

                gestionClientes.listarClientesDisponibles();

                System.out.println("---------------------------------");
                break;
            }
        }
    }

    public static void main(String[] args) {
        prueba();
        //Se Muestra el Menu al Usuario
        printMenu();

        //Controlador de acciones del Usuario
        controlMenu();

    }
}
