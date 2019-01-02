package business;

import data.Data;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public boolean efetuarAposta(Aposta aposta, Apostador apostador){
        boolean apostou = false;
        
            for (Aposta ap : apostador.getApostas())
                if(ap.getEventoID() == aposta.getEventoID())
                    apostou = true;
        if(apostou) {
            notification(3, "Já registou uma aposta neste evento.", "Aviso");
        }
        else if(apostador.getESSCoins()-aposta.getValor() >= 0){
            apostador.efetuarAposta(aposta);
            notification(1, "Aposta registada com sucesso!", "Sucesso");
            return true;
        }
        else{
            notification(3, "Não tem saldo suficiente para realizar a aposta.", "Aviso");
        }
        return false;
    }
    
    public boolean criarEvento(String c, String f, double oddV, double oddE, double oddD){
        Equipa casa = new Equipa();
        Equipa fora = new Equipa();
        for(Equipa a : data.getEquipasValues()){
            if(a.getNome().equals(c)) casa = a;
            else if(a.getNome().equals(f)) fora = a;
        }
        if(c.equals(f)) notification(3, "As equipas selecionadas são a mesma. Por favor escolha outra.", "Aviso");
        else{
            Evento evento = new Evento(data.getEventos().size()+1, oddV, oddE, oddD, true, "", casa, fora); 
            data.addEvento(evento);
            this.notification(1, "Evento criado e disponível.", "Sucesso");
            
            return true;
        }
        return false;
    }
    
    public void finalizarEvento(String jogo, String res){
        String[] equipas = jogo.split(" X ");
        for(Evento e : getEventosAtivos()){
            if(e.getEquipaCasaNome().equals(equipas[0]) && e.getEquipaForaNome().equals(equipas[1])){
                e.setEstado(false);
                e.setResultado(res);
                distribuirPremios(e);
                notification(1, "Evento encerrado e prémios distribuídos.", "Sucesso");
            }
        }
    }
    
    public void distribuirPremios(Evento e){
        for(Apostador a : data.getApostadoresValues()){
            for(Aposta ap : a.getApostas()){
                if(ap.getEvento().equals(e)){
                    a.adicionarESSCoins(ap.ganhos());
                    ap.notificaApostador();
                }
            }
        }
    }
    
    public ArrayList<Evento> getEventosAtivos(){
        return data.getEventosAtivos();
    }
    public ArrayList<Equipa> getEquipasValues(){
        ArrayList<Equipa> values = new ArrayList<>(data.getEquipasValues());
        return values;
    }
    public ArrayList<Apostador> getApostadoresValues(){
        ArrayList<Apostador> values = new ArrayList<>(data.getApostadoresValues());
        return values;
    }
    
    public void registarApostador(Apostador a){
        data.addApostador(a);
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
            for (Apostador a : data.getApostadoresValues())
                if (a.getEmail().compareTo(email)==0 && a.getPassword().compareTo(pass)==0) 
                    return a;
            notification(3, "Dados incorretos!", "Aviso");
        }
        return null;
    }
    
    public void save(){
        try {
            data.save(data);
        } catch (IOException ex) {
            Logger.getLogger(BetESS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}