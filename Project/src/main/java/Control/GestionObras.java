package Control;
import Model.Artista;
import Model.Obra;
import java.util.HashSet;
import java.util.Calendar;

public class GestionObras {

    //Retorna una lista de obras para comenzar el dia
    public HashSet <Obra> listaObras(){

        HashSet <Obra> obras = new HashSet<Obra>();
        Calendar fecha = Calendar.getInstance();
        Calendar fecha1 = Calendar.getInstance();
        Calendar fecha2 = Calendar.getInstance();
        Calendar fecha3 = Calendar.getInstance();
        Calendar fecha4 = Calendar.getInstance();
        Calendar fecha5 = Calendar.getInstance();
        fecha.set(2020, 11, 01);
        //Creación de instancias
        obras.add(new Obra(1324567, "La gorda", fecha, 20000, "20x5"));
        fecha1.set(2010, 5, 20);
        obras.add(new Obra(2435678, "Eva pilla", fecha1, 20000, "10x8"));
        fecha2.set(2001, 8, 6);
        obras.add(new Obra(3456789, "Sociopata", fecha2, 15000, "10x2"));
        fecha3.set(2000, 10, 10);
        obras.add(new Obra(1234567, "Michuelo", fecha3, 20000, "20x5"));
        fecha4.set(1999, 9, 22); 
        obras.add(new Obra(5432198, "Okalokas", fecha4, 20000, "10x8"));
        fecha5.set(1984, 2, 3);
        obras.add(new Obra(7654321, "Machupichu", fecha5, 15000, "10x2"));
        return obras;
    }
    //Retorna una lista de artistas para comenzar el dia
    public HashSet <Artista> startArtistas(){
    	Calendar fecha=Calendar.getInstance();
    	fecha.set(2001, 11, 11);
    	HashSet<Artista>artistas=new HashSet<Artista>();
        artistas.add(new Artista(1000471976,"Sebastian","Herrera Guaitero",fecha,350612646));
        artistas.add(new Artista(1000512331,"Natalia","Castro Sepulveda",fecha,314231233));
        return artistas;
    }
}
