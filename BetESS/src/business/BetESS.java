package business;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author vitorpeixoto
 */
public class BetESS implements Serializable{
    private HashMap<Integer,Apostador> apostadores;
    private HashMap<Integer,Evento> eventos;
    private HashMap<Integer,Equipa> equipas;
    
    public BetESS() {
        this.apostadores = new HashMap<>();
        this.eventos = new HashMap<>();
        this.equipas = new HashMap<>();
    }
    public BetESS(HashMap<Integer,Apostador> a, HashMap<Integer,Evento> ev, HashMap<Integer,Equipa> eq) {
        this.apostadores = a;
        this.eventos = ev;
        this.equipas = eq;
    }
    public BetESS(BetESS b) {
        this.apostadores = b.getApostadores();
        this.eventos = b.getEventos();
        this.equipas = b.getEquipas();
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
    
    public void criarEvento(Evento e){
        eventos.put(e.getID(), e);
    }
    
    public void removerEvento(Evento e){
        eventos.remove(e.getID(), e);
    }
    
    public void registarApostador(Apostador a){
        apostadores.put(a.getID(),a);
    }
    
    public void addEquipa(Equipa e){
        equipas.put(e.getID(),e);
    }
    
    public void removerEquipa(Equipa e){
        if(equipas.containsKey(e.getID()))
            equipas.get(e.getID()).setEstado(false);
    }
    
    public void save() throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("betess.obj");
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
    /**
     * Este método está incompleto. É só para testar o serializable.
     */
    public BetESS populate(){
        Equipa boavista = new Equipa(1, "Boavista FC", true);
        Equipa tondela  = new Equipa(2, "CD Tondela", true);
        equipas.put(1,boavista);
        equipas.put(2,tondela);
        return this;
        
    }
    
    public BetESS load() throws IOException, ClassNotFoundException {
        BetESS b = new BetESS();
        try{
            FileInputStream fileIn = new FileInputStream("betess.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            b = (BetESS) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Data loaded!\n");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found\n");
            c.printStackTrace();
        }
        
        return b;
    }
}
