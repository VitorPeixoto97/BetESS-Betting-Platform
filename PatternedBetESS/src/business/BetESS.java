package business;

import java.io.IOException;
import java.util.HashMap;


public class BetESS {
    private Data data;
    
    public BetESS() {
        this.data = new Data();
    }
    public BetESS(HashMap<Integer,Apostador> a, HashMap<Integer,Evento> ev, HashMap<Integer,Equipa> eq) {
        this.data = new Data(a,ev,eq);
    }
    public BetESS(Data d) {
        this.data = new Data(d);
    }
    
    public Data getData(){
        return this.data;
    }
    
    public HashMap<Integer, Apostador> getApostadores(){
        return this.data.getApostadores();
    }
    
    public HashMap<Integer, Evento> getEventos(){
        return this.data.getEventos();
    }
        
    public HashMap<Integer, Equipa> getEquipas(){
        return this.data.getEquipas();
    }
    
    public void criarEvento(Evento e){
        this.data.addEvento(e);
    }
    
    public void finalizarEvento(Evento e, String res){
        this.data.endEvento(e,res);
    }
    
    public void registarApostador(Apostador a){
        this.data.newApostador(a);
    }
    
    public void adicionarEquipa(Equipa e){
        this.data.addEquipa(e);
    }
    
    public void removerEquipa(Equipa e){
        this.data.removeEquipa(e);
    }
    
    public void save(BetESS betess) throws IOException {
        this.data.save(betess.getData());
    }
    /**
     * Este método está incompleto. É só para testar o serializable.
     */
    public BetESS povoar(){
        this.data.povoar();
        return this;
    }
    
    public BetESS load() throws IOException {
        BetESS b = new BetESS(this.data.load());
        
        return b;
    }
}
