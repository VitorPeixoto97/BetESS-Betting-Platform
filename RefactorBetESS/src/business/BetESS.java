package business;

import data.Data;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BetESS implements Serializable{
    
    private Data data;
    
    public BetESS(){
        this.data = new Data();
    }
    public BetESS(Data d){
        this.data=d;
    }
    public BetESS(BetESS b){
        this.data=b.getData();
    }
    
    public Data getData(){
        return this.data;
    }
    
    public boolean efetuarAposta(Evento e, Apostador a, int res, int val, double odd){
        boolean apostou = false;
        for (Aposta ap : this.data.getApostadores().get(a.getID()).getApostas())
            if(ap.getEvento().getID() == e.getID())
                apostou = true;
        if(apostou) {
            this.notification(3, "Já registou uma aposta neste evento.", "Aviso");
        }
        else if(a.getESSCoins()-val >= 0){
            Aposta ap = new Aposta(res, val, e, true);
            this.data.getApostadores().get(a.getID()).efetuarAposta(ap);
            this.notification(1, "Aposta registada com sucesso!", "Sucesso");
            return true;
        }
        else{
            this.notification(3, "Não tem saldo suficiente para realizar a aposta.", "Aviso");
        }
        return false;
    }
    
    public boolean criarEvento(String c, String f, double oddV, double oddE, double oddD){
        Equipa casa = new Equipa();
        Equipa fora = new Equipa();
        for(Equipa a : data.getEquipas().values()){
            if(a.getNome().equals(c)) casa = a;
            else if(a.getNome().equals(f)) fora = a;
        }
        if(c.equals(f)) this.notification(3, "As equipas selecionadas são a mesma. Por favor escolha outra.", "Aviso");
        else{
            Evento evento = new Evento(data.getEventos().size()+1, oddV, oddE, oddD, true, "", casa, fora); 
            data.getEventos().put(evento.getID(), evento);
            this.notification(1, "Evento criado e disponível.", "Sucesso");
            
            return true;
        }
        return false;
    }
    
    public void finalizarEvento(String jogo, String res){
        String[] equipas = jogo.split(" X ");
        for(Evento e : getEventosAtivos()){
            if(e.getEquipaC().getNome().equals(equipas[0]) && e.getEquipaF().getNome().equals(equipas[1])){
                data.getEventos().get(e.getID()).setEstado(false);
                data.getEventos().get(e.getID()).setResultado(res);
                int vencedor = e.getRes(res);
                this.getData().getApostadores().values().forEach((a) -> {
                    a.getApostas().stream().filter((ap) -> (ap.getEvento().equals(e))).map((ap) -> {
                        ap.distribuirGanhos(a,vencedor);
                        return ap;
                    }).forEachOrdered((ap) -> {
                        ap.notificaApostador();
                    });
                });
                this.notification(1, "Evento encerrado e prémios distribuídos.", "Sucesso");
            }
        }
    }
    
    public ArrayList<Evento> getEventosAtivos(){
        ArrayList<Evento> ativos = new ArrayList<>();
        for(Evento e : data.getEventos().values()){
            if(e.getEstado()){
                ativos.add(e);
            }
        }
        return ativos;
    }
    
    public void registarApostador(Apostador a){
        data.getApostadores().put(a.getID(),a);
    }
    
    public void adicionarEquipa(Equipa e){
        data.getEquipas().put(e.getID(),e);
    }
    
    public void removerEquipa(Equipa e){
        if(data.getEquipas().containsKey(e.getID()))
            data.getEquipas().get(e.getID()).setEstado(false);
    }
    
    public void notification(int tipo, String texto, String titulo){
        ImageIcon icon = new ImageIcon();
        if(tipo==1) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/check.png"));
        else if(tipo==2) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/warning.png"));
        else if(tipo==3) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/forbidden.png"));
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public Apostador login(String email, String pass){
        if(email.equals("admin") && pass.equals("admin")){
            return new Apostador();
        }
        else{
            for (Apostador a : data.getApostadores().values()) {
                if (a.getEmail().compareTo(email)==0){
                    if (a.getPassword().compareTo(pass)==0){
                        return a;
                    }
                }
            }
            notification(3, "Dados incorretos!", "Aviso");
        }
        return null;
    }
}