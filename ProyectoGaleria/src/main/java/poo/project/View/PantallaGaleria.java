package poo.project.View;

import poo.project.Control.ControlGaleria;
import java.util.Calendar;
import java.util.Scanner;

import poo.project.Model.Obra;// No deberia Existir esta coneccion

//Boundary
public class PantallaGaleria {

    private ControlGaleria controlGaleria;
    //Limpia la Pantalla
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Muestra el Menu al Usuario
    public void printMenu(){
        clearScreen();//Limpia la Pantalla

        System.out.println("-----------------------------");
        System.out.println("- Galeria de Arte Javeriana -");
        System.out.println("-----------------------------");
        System.out.println(" ");
        System.out.println("----------- Menu ------------");
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
        /*System.out.println(" ");
        System.out.println("16. Salir");
        System.out.println(" ");*/
        System.out.println("---------------------------------");

    }

    //Controla las deciciones del usuario
    public void controlMenu(){

        //Variables internas
    	Scanner entrada=new Scanner(System.in);
        ControlGaleria controlGaleria=this.getControlGaleria();
        int opcionObras = 0;
        long buscarObraporCodigo = 0;
        Calendar buscarObraporFecha = null;
        String buscarObraporArtista = " ";
        String buscarObraporTitulo =  " ";

        //Registra la decicion del Usuario
        System.out.println("Digita el Numero de la Opcion: ");
        int opcionSeleect = entrada.nextInt();
        System.out.println("---------------------------------");
        System.out.println(" ");

        // Procesa la decición del Usuario
        switch (opcionSeleect) {

            //Obras
            //Lista Obras
            case 1: {

                //Imprime por pantalla todas las obras Disponibles
                System.out.println("Lista De Obras Disponibles: ");
                System.out.println("---------------------------------");
                controlGaleria.printObras();
                System.out.println("---------------------------------");
                break;
            }

            //Busca obras por titulo, autor, fecha o codigo
            case 2: {

                //Menu de Busqueda Interno para Obras
                System.out.println("Menu Busca Obras por: ");
                System.out.println("1. Titulo");
                System.out.println("2. Autor");
                System.out.println("3. Codigo");
                System.out.println("4. Fecha");

                //Registra la decicion del Usuario
                System.out.println("Selecciona la Opcion: ");
                opcionObras=entrada.nextInt();
                System.out.println(" ");

                //Procesa la Decición del Usuario
                switch (opcionObras){

                    //Busqueda por Titulo
                    case 1:{
                        entrada.nextLine();

                        System.out.println("Busca Obras por Titulo");
                        System.out.println("Escribe el Titulo:");
                        buscarObraporTitulo= entrada.nextLine();

                        System.out.println("--Obras del Titulo "+ buscarObraporTitulo);

                        controlGaleria.buscarObra(buscarObraporTitulo);
                        System.out.println("---------------------------------");
                        break;
                    }

                    //Busqueda Por Autor
                    case 2:{
                        entrada.nextLine();

                        System.out.println("Busca Obras por Autor");
                        System.out.println("Escribe el Nombre:");
                        buscarObraporArtista= entrada.nextLine();

                        System.out.println("--Obras del Autor "+ buscarObraporArtista);

                        controlGaleria.buscarObraporArtista(buscarObraporArtista);
                        System.out.println("---------------------------------");
                        break;

                    }

                    //Busqueda Por Codigo
                    case 3:{
                        entrada.nextLong();

                        System.out.println("Busca Obras por Codigo");
                        System.out.println("Escribe el Codigo:");
                        buscarObraporCodigo= entrada.nextLong();

                        System.out.println("--Obra del Codigo "+ buscarObraporCodigo);

                        controlGaleria.buscarObra(buscarObraporCodigo);
                        System.out.println("---------------------------------");
                        break;
                    }

                    //Busqueda Por Fecha
                    case 4: {
                        entrada.nextLine();

                        System.out.println("Busca Obras por Fecha");
                        System.out.println("Escribe la Fecha:");
                        buscarObraporFecha = null; // Ingresar Fecha

                        System.out.println("--Obras de la Fecha "+ buscarObraporFecha);

                        controlGaleria.buscarObra(buscarObraporFecha);
                        System.out.println("---------------------------------");
                        break;
                    }

                }
                break;//Case 2
            }

            //Inserta Obra
            case 3:{
                System.out.println("Insertar Obra");

                //Metodo Crear Obra

                //controlGaleria.getGestionObras().addObra(null);
                System.out.println("---------------------------------");
                break;
            }
            //Modifica Obra por Codigo
            case 4: {
                System.out.println("Modificar Obra");

                System.out.println("Escribe el Codigo:");
                buscarObraporCodigo=entrada.nextLong();
                controlGaleria.modificarObra(buscarObraporCodigo);
                System.out.println("---------------------------------");
                break;
            }

            //Elimina Obra Por Codigo
            case 5:{
                System.out.println("Eliminar Obra");

                System.out.println("Escribe el Codigo:");
                buscarObraporCodigo= entrada.nextLong();
                controlGaleria.eliminarObra(buscarObraporCodigo);
                System.out.println("---------------------------------");
                break;
            }

            //Clientes
            //Lista Clientes Activos
            case 6:{
                System.out.println("Listar Cliente");

                controlGaleria.printClientes();

                System.out.println("---------------------------------");
                break;
            }
        }
    }

    //Main Boundary del Sistema
    public static void main(String[] args) {

        //Creaciones de Objetos
        Scanner entrada = new Scanner(System.in);
        Scanner entrada2Scanner = new Scanner(System.in);
        entrada.useDelimiter("\\n");
        PantallaGaleria pantallaGaleria=new PantallaGaleria();
        Integer opc;
        //Incerta Clientes, Obras y Autores.
        pantallaGaleria.getControlGaleria().startDay();
        do{
            //Muestra el Menu al Usuario
            pantallaGaleria.printMenu();
            //Controla las deciciones del usuario
            pantallaGaleria.controlMenu();
            System.out.println("Desea ingresar otra opción (1/0)");
            opc=entrada2Scanner.nextInt();
        }while (!(opc==0));
        entrada2Scanner.close();
    }

    public ControlGaleria getControlGaleria() {
        return this.controlGaleria;
    }

    public void setControlGaleria(ControlGaleria controlGaleria) {
        this.controlGaleria = controlGaleria;
    }

    public PantallaGaleria()
    {
        this.controlGaleria=new ControlGaleria();
    }
}
