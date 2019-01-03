package data;

import business.Aposta;
import business.Apostador;
import business.Equipa;
import business.Evento;
import business.Odds;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Data implements Serializable {
    private ArrayList<Apostador> apostadores;
    private ArrayList<Evento> eventos;
    private ArrayList<Equipa> equipas;
    
    public Data() {
        this.apostadores = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.equipas = new ArrayList<>();
    }
    public Data(ArrayList<Apostador> a, ArrayList<Evento> ev, ArrayList<Equipa> eq) {
        this.apostadores = a;
        this.eventos = ev;
        this.equipas = eq;
    }
    public Data(Data d) {
        this.apostadores = d.getApostadores();
        this.eventos = d.getEventos();
        this.equipas = d.getEquipas();
    }
    
    public ArrayList<Apostador> getApostadores(){
        return this.apostadores;
    }
    public ArrayList<Evento> getEventos(){
        return this.eventos;
    }
    public ArrayList<Equipa> getEquipas(){
        return this.equipas;
    }
    
    public ArrayList<Evento> getEventosAtivos(){
        ArrayList<Evento> ativos = new ArrayList<>();
        for(Evento e : eventos)
            if(e.getEstado())
                ativos.add(e);
        return ativos;
    }
    
    public void addEvento(Evento e){
        eventos.add(e);
    }
    public void addApostador(Apostador a){
        apostadores.add(a);
    }
    
    public Data load() throws IOException {
        Data d = new Data();
        try{
            FileInputStream fileIn = new FileInputStream("betess.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            d = (Data) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Data loaded!\n");
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found\n");
        }
        return d;
    }
    
    public void save(Data data) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("betess.obj");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.flush();
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in betess.obj\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Data povoar(){
        this.povoarEquipas();
        this.povoarApostadores();
        this.povoarEventos();
        System.out.println("New data reloaded!\n");
        return this;
    }
    public void povoarEquipas(){
        Equipa belenenses   = new Equipa(0, "Belenenses SAD", true, "resources/equipas/belenenses.png");
        Equipa boavista     = new Equipa(1, "Boavista FC", true, "resources/equipas/boavista.png");
        Equipa tondela      = new Equipa(2, "CD Tondela", true, "resources/equipas/tondela.png");
        Equipa aves         = new Equipa(3, "CD Aves", true, "resources/equipas/aves.png");
        Equipa feirense     = new Equipa(4, "CD Feirense", true, "resources/equipas/feirense.png");
        Equipa nacional     = new Equipa(5, "CD Nacional", true, "resources/equipas/nacional.png");
        Equipa maritimo     = new Equipa(6, "CS Marítimo", true, "resources/equipas/maritimo.png");
        Equipa porto        = new Equipa(7, "FC Porto", true, "resources/equipas/porto.png");
        Equipa chaves       = new Equipa(8, "GD Chaves", true, "resources/equipas/chaves.png");
        Equipa moreirense   = new Equipa(9, "Moreirense FC", true, "resources/equipas/moreirense.png");
        Equipa portimonense = new Equipa(10, "Portimonense SC", true, "resources/equipas/portimonense.png");
        Equipa rioave       = new Equipa(11, "Rio Ave FC", true, "resources/equipas/rioave.png");
        Equipa santaclara   = new Equipa(12, "Santa Clara", true, "resources/equipas/santaclara.png");
        Equipa benfica      = new Equipa(13, "SL Benfica", true, "resources/equipas/benfica.png");
        Equipa braga        = new Equipa(14, "SC Braga", true, "resources/equipas/braga.png");
        Equipa sporting     = new Equipa(15, "Sporting CP", true, "resources/equipas/sporting.png");
        Equipa setubal      = new Equipa(16, "Vitória FC", true, "resources/equipas/setubal.png");
        Equipa guimaraes    = new Equipa(17, "Vitória SC", true, "resources/equipas/guimaraes.png");
       
        equipas.add(belenenses);
        equipas.add(boavista);
        equipas.add(tondela);
        equipas.add(aves);
        equipas.add(feirense);
        equipas.add(nacional);
        equipas.add(maritimo);
        equipas.add(porto);
        equipas.add(chaves);
        equipas.add(moreirense);
        equipas.add(portimonense);
        equipas.add(rioave);
        equipas.add(santaclara);
        equipas.add(benfica);
        equipas.add(braga);
        equipas.add(sporting);
        equipas.add(setubal);
        equipas.add(guimaraes);
    }
    public void povoarApostadores(){
        Apostador a = new Apostador(0, "joaonunes@gmail.com", "joaonunes", "João Nunes", 25.00, new ArrayList<>());
        Apostador b = new Apostador(1, "saramoreno@gmail.com", "saramoreno", "Sara Moreno", 5.00, new ArrayList<>());
        Apostador c = new Apostador(2, "pauloprazeres@gmail.com", "pauloprazeres", "Paulo Prazeres", 2.92, new ArrayList<>());
        Apostador d = new Apostador(3, "albanojeronimo@gmail.com", "albanojeronimo", "Albano Jerónimo", 89.20, new ArrayList<>());
        Apostador e = new Apostador(4, "nunolopes@gmail.com", "nunolopes", "Nuno Lopes", 102.36, new ArrayList<>());
        Apostador f = new Apostador(5, "marcomartins@gmail.com", "marcomartins", "Marco Martins", 19.76, new ArrayList<>());
        Apostador g = new Apostador(6, "miguelguilherme@gmail.com", "miguelguilherme", "Miguel Guilherme", 15.58, new ArrayList<>());
        Apostador h = new Apostador(7, "beatrizbatarda@gmail.com", "beatrizbatarda", "Beatriz Batarda", 5.01, new ArrayList<>());
        
        this.apostadores.add(a);
        this.apostadores.add(b);
        this.apostadores.add(c);
        this.apostadores.add(d);
        this.apostadores.add(e);
        this.apostadores.add(f);
        this.apostadores.add(g);
        this.apostadores.add(h);
    }
    public void povoarEventos(){
        Evento j1j1 = new Evento(0, new Odds(1.11, 3.94, 8.71), true, "", equipas.get(7), equipas.get(9));
        Evento j1j2 = new Evento(1, new Odds(2.10, 2.81, 4.50), true, "", equipas.get(12), equipas.get(16));
        Evento j1j3 = new Evento(2, new Odds(1.72, 3.81, 7.21), true, "", equipas.get(17), equipas.get(10));
        Evento j1j4 = new Evento(3, new Odds(1.68, 3.54, 7.02), true, "", equipas.get(0), equipas.get(1));
        Evento j1j5 = new Evento(9, new Odds(2.13, 2.81, 2.50), true, "", equipas.get(14), equipas.get(13));
        Evento j1j6 = new Evento(5, new Odds(1.42, 3.36, 3.47), true, "", equipas.get(8), equipas.get(6));
        Evento j1j7 = new Evento(6, new Odds(1.09, 3.81, 8.98), true, "", equipas.get(15), equipas.get(5));
        Evento j1j8 = new Evento(7, new Odds(1.87, 2.98, 3.49), true, "", equipas.get(3), equipas.get(2));
        Evento j1j9 = new Evento(8, new Odds(1.42, 3.25, 3.99), true, "", equipas.get(11), equipas.get(4));
        
        this.eventos.add(j1j1);
        this.eventos.add(j1j2);
        this.eventos.add(j1j3);
        this.eventos.add(j1j4);
        this.eventos.add(j1j5);
        this.eventos.add(j1j6);
        this.eventos.add(j1j7);
        this.eventos.add(j1j8);
        this.eventos.add(j1j9);
    }
}