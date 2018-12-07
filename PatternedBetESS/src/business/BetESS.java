package business;

import persistence.Data;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vitorpeixoto
 */
public class BetESS implements Serializable{
    Data data;
    
    public BetESS() {
        this.data = new Data();
    }
    public BetESS(HashMap<Integer,Apostador> a, HashMap<Integer,Evento> ev, HashMap<Integer,Equipa> eq) {
        this.data = new Data(a,ev,eq);
    }
    public BetESS(Data d) {
        this.data = d;
    }
    
    public void criarEvento(Evento e){
        data.addEvento(e);
    }
    
    public void finalizarEvento(Evento e, String res){
        data.endEvento(e, res);
    }
    
    public void registarApostador(Apostador a){
        data.newApostador(a);
    }
    
    public void adicionarEquipa(Equipa e){
        data.addEquipa(e);
    }
    
    public void removerEquipa(Equipa e){
        data.removeEquipa(e);
    }
    
    public Map<Integer, Evento> getEventos(){
        return data.getEventos();
    }
    
    public Map<Integer, Equipa> getEquipas(){
        return data.getEquipas();
    }
    
    public Map<Integer, Apostador> getApostadores(){
        return data.getApostadores();
    }
    
    public void efetuarAposta(Aposta a, int apostadorID) {
        data.addAposta(a,apostadorID);
    }
    
    public void cancelarAposta(Aposta a, int apostadorID){
        data.removeAposta(a,apostadorID);
    }
    
    public Apostador getApostador(int id){
        return data.getApostadores().get(id);
    }
    
    public void save() {
        this.data.save();
    }    
    
    public static BetESS load() throws IOException, ClassNotFoundException {
        Data d = Data.load();
        BetESS b = new BetESS(d);
        return b;
    }
}
