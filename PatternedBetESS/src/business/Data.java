/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static com.sun.javafx.util.Utils.split;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author danie
 */
public class Data implements Serializable{
    private HashMap<Integer,Apostador> apostadores;
    private HashMap<Integer,Evento> eventos;
    private HashMap<Integer,Equipa> equipas;
    
    public Data(){
        this.apostadores = new HashMap<>();
        this.eventos = new HashMap<>();
        this.equipas = new HashMap<>();
    }
    
    public Data(HashMap<Integer,Apostador> a, HashMap<Integer,Evento> ev, HashMap<Integer,Equipa> eq){
        this.apostadores = a;
        this.eventos = ev;
        this.equipas = eq;
    }
    
    public Data(Data d){
        this.apostadores = d.getApostadores();
        this.eventos = d.getEventos();
        this.equipas = d.getEquipas();
    }
    
    public HashMap<Integer,Apostador> getApostadores(){
        return this.apostadores;
    }
    public HashMap<Integer,Evento> getEventos(){
        return this.eventos;
    }
    public HashMap<Integer,Equipa> getEquipas(){
        return this.equipas;
    }
    
    public void addEvento(Evento e){
        this.eventos.put(e.getID(), e);
    }
    
    public void endEvento(Evento e, String res){
        String[] venc = split(res,"-");
                int result;
                if(Integer.parseInt(venc[0])>Integer.parseInt(venc[1])){ //equipa casa venceu
                    System.out.println("equipa casa venceu\n");
                    result = 1;
                }
                else if(Integer.parseInt(venc[1])>Integer.parseInt(venc[0])){ //equipa fora venceu
                    System.out.println("equipa fora venceu\n");
                    result = 3;
                }
                else{ //empate
                    System.out.println("empate\n");
                    result = 2;
                }
        this.eventos.get(e.getID()).setEstado(false);
        this.eventos.get(e.getID()).setResultado(result);
        e.notifyApostadores();
    }
    
    public void newApostador(Apostador a){
        this.apostadores.put(a.getID(),a);
    }
    
    public void addEquipa(Equipa e){
        this.equipas.put(e.getID(),e);
    }
    
    public void removeEquipa(Equipa e){
        if(equipas.containsKey(e.getID()))
            equipas.get(e.getID()).setEstado(false);
    }
    
    public void removeAposta(Aposta a, int apostadorID){
        apostadores.get(apostadorID).removerAposta(a);
    }
    
    public void save(){
        try {
            FileOutputStream fileOut = new FileOutputStream("betessData.obj");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.flush();
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in betess.obj\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public void povoar(){
        this.povoarEquipas();
        this.povoarApostadores();
        this.povoarEventos();
        System.out.println("Dados inseridos!\n");
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
        Equipa famalicao    = new Equipa(18, "FC Famalicão", false, "resources/equipas/famalicao.png");
        Equipa pacos        = new Equipa(19, "FC Paços de Ferreira", false, "resources/equipas/pacos.png");
        
        equipas.put(0,belenenses);
        equipas.put(1,boavista);
        equipas.put(2,tondela);
        equipas.put(3,aves);
        equipas.put(4,feirense);
        equipas.put(5,nacional);
        equipas.put(6,maritimo);
        equipas.put(7,porto);
        equipas.put(8,chaves);
        equipas.put(9,moreirense);
        equipas.put(10,portimonense);
        equipas.put(11,rioave);
        equipas.put(12,santaclara);
        equipas.put(13,benfica);
        equipas.put(14,braga);
        equipas.put(15,sporting);
        equipas.put(16,setubal);
        equipas.put(17,guimaraes);
    }
    public void povoarApostadores(){
        Apostador a = new Apostador(0, "joaonunes@gmail.com", "joaonunes", "João Nunes", 25.00, new HashMap<>());
        Apostador b = new Apostador(1, "saramoreno@gmail.com", "saramoreno", "Sara Moreno", 5.00, new HashMap<>());
        Apostador c = new Apostador(2, "pauloprazeres@gmail.com", "pauloprazeres", "Paulo Prazeres", 2.92, new HashMap<>());
        Apostador d = new Apostador(3, "albanojeronimo@gmail.com", "albanojeronimo", "Albano Jerónimo", 89.20, new HashMap<>());
        Apostador e = new Apostador(4, "nunolopes@gmail.com", "nunolopes", "Nuno Lopes", 102.36, new HashMap<>());
        Apostador f = new Apostador(5, "marcomartins@gmail.com", "marcomartins", "Marco Martins", 19.76, new HashMap<>());
        Apostador g = new Apostador(6, "miguelguilherme@gmail.com", "miguelguilherme", "Miguel Guilherme", 15.58, new HashMap<>());
        Apostador h = new Apostador(7, "beatrizbatarda@gmail.com", "beatrizbatarda", "Beatriz Batarda", 5.01, new HashMap<>());
        
        this.apostadores.put(a.getID(),a);
        this.apostadores.put(b.getID(),b);
        this.apostadores.put(c.getID(),c);
        this.apostadores.put(d.getID(),d);
        this.apostadores.put(e.getID(),e);
        this.apostadores.put(f.getID(),f);
        this.apostadores.put(g.getID(),g);
        this.apostadores.put(h.getID(),h);
    }
    public void povoarEventos(){
        Evento j1j1 = new Evento(0, 1.11d, 3.94d, 8.71d, true, 0, equipas.get(7), equipas.get(9), new HashMap<>());
        Evento j1j2 = new Evento(1, 2.10, 2.81, 4.50, true, 0, equipas.get(12), equipas.get(16), new HashMap<>());
        Evento j1j3 = new Evento(2, 1.72, 3.81, 7.21, true, 0, equipas.get(17), equipas.get(10), new HashMap<>());
        Evento j1j4 = new Evento(3, 1.68, 3.54, 7.02, true, 0, equipas.get(0), equipas.get(1), new HashMap<>());
        Evento j1j5 = new Evento(9, 2.13, 2.81, 2.50, true, 0, equipas.get(14), equipas.get(13), new HashMap<>());
        Evento j1j6 = new Evento(5, 1.42, 3.36, 3.47, true, 0, equipas.get(8), equipas.get(6), new HashMap<>());
        Evento j1j7 = new Evento(6, 1.09, 3.81, 8.98, true, 0, equipas.get(15), equipas.get(5), new HashMap<>());
        Evento j1j8 = new Evento(7, 1.87, 2.98, 3.49, true, 0, equipas.get(3), equipas.get(2), new HashMap<>());
        Evento j1j9 = new Evento(8, 1.42, 3.25, 3.99, true, 0, equipas.get(11), equipas.get(4), new HashMap<>());
        
        this.eventos.put(j1j1.getID(),j1j1);
        this.eventos.put(j1j2.getID(),j1j2);
        this.eventos.put(j1j3.getID(),j1j3);
        this.eventos.put(j1j4.getID(),j1j4);
        this.eventos.put(j1j5.getID(),j1j5);
        this.eventos.put(j1j6.getID(),j1j6);
        this.eventos.put(j1j7.getID(),j1j7);
        this.eventos.put(j1j8.getID(),j1j8);
        this.eventos.put(j1j9.getID(),j1j9);

    }
    
    public static Data load() throws FileNotFoundException, IOException{
        Data d = new Data();
        try{
            FileInputStream fileIn = new FileInputStream("betessData.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            d = (Data) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Data loaded!\n");
        //} catch (IOException i) {
        //    i.printStackTrace();
        } catch (Exception e) {
            d.povoar();
            d.save();
        }
        
        return d;
    }

    void addAposta(Aposta a, int apostadorID) {
        this.apostadores.get(apostadorID).efetuarAposta(a);
    }
}
