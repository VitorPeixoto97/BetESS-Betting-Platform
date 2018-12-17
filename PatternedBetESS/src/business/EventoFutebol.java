/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vitorpeixoto
 */
public class EventoFutebol implements Serializable, Evento{
    private int id;
    private double oddV;
    private double oddE;
    private double oddD;
    private boolean estado;
    private int resultado;
    private Equipa equipaC;
    private Equipa equipaF;
    private Map<String, User> utilizadores;
    
    public EventoFutebol(){
        this.id = 9999;
        this.oddV = 0.0;
        this.oddE = 0.0;
        this.oddD = 0.0;
        this.estado = false;
        this.resultado = 0;
        this.equipaC = new Equipa();
        this.equipaF = new Equipa();
        this.utilizadores = new HashMap<>();
    }
    public EventoFutebol(int id, double oddV, double oddE, double oddD, boolean estado, int resultado, Equipa c, Equipa f, User u){
        this.id = id;
        this.oddV = oddV;
        this.oddE = oddE;
        this.oddD = oddD;
        this.estado = estado;
        this.resultado = resultado;
        this.equipaC = c;
        this.equipaF = f;
        this.utilizadores = new HashMap<>();
        if(u != null)
            utilizadores.put(u.getEmail(), u);
    }
    public EventoFutebol(EventoFutebol e){
        this.id = e.getID();
        this.oddV = e.getOddV();
        this.oddE = e.getOddE();
        this.oddD = e.getOddD();
        this.estado = e.getEstado();
        this.resultado = e.getResultado();
        this.equipaC = e.getEquipaC();
        this.equipaF = e.getEquipaF();
        this.utilizadores = e.getUtilizadores();
    }
    
    public int getID(){
        return this.id;
    }
    public double getOddV(){
        return this.oddV;
    }
    public double getOddE(){
        return this.oddE;
    }
    public double getOddD(){
        return this.oddD;
    }
    public boolean getEstado(){
        return this.estado;
    }
    public int getResultado(){
        return this.resultado;
    }
    public Equipa getEquipaC(){
        return this.equipaC;
    }
    public Equipa getEquipaF(){
        return this.equipaF;
    }
    public Map<String, User> getUtilizadores(){
        return this.utilizadores;
    }
    public void setID(int id){
        this.id=id;
    }
    public void setOddV(double oddV){
        this.oddV=oddV;
    }
    public void setOddE(double oddE){
        this.oddE=oddE;
    }
    public void setOddD(double oddD){
        this.oddD=oddD;
    }
    public void setEstado(boolean estado){
        this.estado=estado;
    }
    public void setResultado(int resultado){
        this.resultado=resultado;
    }
    public void setEquipaC(Equipa equipaC){
        this.equipaC=equipaC;
    }
    public void setEquipaF(Equipa equipaF){
        this.equipaF=equipaF;
    }
    
    public boolean equals(EventoFutebol e) {
        if(e.getID() == this.id) {
            return true;
        }
        return false;
    }
    
    @Override
    public void notifyUtilizadores() {
        double total = utilizadores.values().stream()
                                            .filter(u -> u.getClass().getSimpleName().equals("Apostador") && this.getUtilizadores().containsKey(u.getEmail()))
                                            .mapToDouble(a -> a.update(this, 0)).sum();
        
        utilizadores.values().stream()
                             .filter(u -> u.getClass().getSimpleName().equals("Bookie") && this.getUtilizadores().containsKey(u.getEmail()))
                             .forEach(b -> b.update(this, total));
    }
    
    @Override
    public void removeUser(User u){
        this.utilizadores.remove(u);
    }
    
    @Override
    public void registaUser(User u){
        this.utilizadores.put(u.getEmail(),u);
    }
    
    public int vencedor(String res){
        String[] venc = res.split("-");
        if(Integer.parseInt(venc[0])>Integer.parseInt(venc[1])){ //equipa casa venceu
            return 1;
        }
        else if(Integer.parseInt(venc[1])>Integer.parseInt(venc[0])){ //equipa fora venceu
            return 3;
        }
        else{ //empate
            return 2;
        }
    }
    
    public void finalizar(int res){
        this.setEstado(false);
        this.setResultado(res);
        this.notifyUtilizadores();
    }
    
}
