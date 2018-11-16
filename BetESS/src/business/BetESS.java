package business;

import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author vitorpeixoto
 */
public class BetESS {
    private HashMap<Integer,Apostador> apostadores;
    private HashMap<Integer,Evento> eventos;
    private HashMap<Integer,Equipa> equipas;
    
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
    
}
