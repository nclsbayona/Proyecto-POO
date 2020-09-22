package poo.project.Control;

import poo.project.Model.Artista;
import poo.project.Model.Obra;
import java.util.HashSet;
import java.util.Calendar;
import java.util.Scanner;

public class GestionObras {

    public HashSet <Obra> startObras(){
        Calendar proof = Calendar.getInstance();
        HashSet <Obra> obras=new HashSet<Obra>();
        proof.set(2020, 11, 01);
        obras.add(new Obra(1234567, "Mera", proof, 20000, "20x5"));
        obras.add(new Obra(5432198, "Okaloka", proof, 20000, "10x8"));
        obras.add(new Obra(7654321, "Machupichu", proof, 15000, "10x2"));
        return obras;
    }
}
