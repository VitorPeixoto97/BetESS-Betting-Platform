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
    
    // método responsável por criar um novo Evento.
    public boolean criarEvento(String c, String f, Odds odds){
        Equipa casa = new Equipa(getEquipa(c));
        Equipa fora = new Equipa(getEquipa(f));
        if(c.equals(f)) popupWindow(3, "As equipas selecionadas são a mesma. Por favor selecione outra.", "Aviso");
        else{
            Evento evento = new Evento(getEventosSize()+1, odds, true, "", casa, fora); 
            data.addEvento(evento);
            popupWindow(1, "Evento criado e disponível.", "Sucesso");
            return true;
        }
        return false;
    }
    // método responsável por finalizar um Evento e ações consequentes.
    public void finalizarEvento(String jogo, String res){
        String[] equipas = jogo.split(" X ");
        for(Evento e : getEventosAtivos()){
            if(e.getEquipaCasaNome().equals(equipas[0]) && e.getEquipaForaNome().equals(equipas[1])){
                e.setEstado(false);
                e.setResultado(res);
                e.distribuirPremios(getApostadores());
                popupWindow(1, "Evento encerrado e prémios distribuídos.", "Sucesso");
            }
        }
    }
    
    // métodos que retornam as listas de Apostadores, Equipas e Eventos.
    public ArrayList<Apostador> getApostadores(){
        return data.getApostadores();
    }
    public ArrayList<Equipa> getEquipas(){
        return data.getEquipas();
    }
    public ArrayList<Evento> getEventos(){
        return data.getEventos();
    }
    // métodos que retornam o número de Apostadores, Equipas e Eventos.
    public int getApostadoresSize(){
        return getApostadores().size();
    }
    public int getEquipasSize(){
        return getEquipas().size();
    }
    public int getEventosSize(){
        return getEventos().size();
    }
    
    // método que retorna a lista de Eventos registados como ativos.
    public ArrayList<Evento> getEventosAtivos(){
        return data.getEventosAtivos();
    }
    // método que retorna o número de Eventos registados como ativos.
    public int getEventosAtivosSize(){
        return getEventosAtivos().size();
    }
    // método que retorna o Evento ativo dado o seu ID.
    public Evento getEventoAtivo(int i){
        return getEventosAtivos().get(i);
    }
    // método que retorna a Equipa dado o seu nome.
    public Equipa getEquipa(String nome){
        for(Equipa e : data.getEquipas())
            if(e.getNome().equals(nome))
                return e;
        return null;
    }
    
    public void popupWindow(int tipo, String texto, String titulo){
        ImageIcon icon = new ImageIcon();
        if(tipo==1) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/check.png"));
        else if(tipo==2) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/warning.png"));
        else if(tipo==3) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/forbidden.png"));
        else if(tipo==4) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/ball.png"));
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public Apostador login(String email, String pass){
        if(email.equals("admin") && pass.equals("admin")){
            return new Apostador();
        }
        else{
            for (Apostador a : getApostadores())
                if (a.getEmail().compareTo(email)==0 && a.getPassword().compareTo(pass)==0) 
                    return a;
            popupWindow(3, "Dados incorretos!", "Aviso");
        }
        return null;
    }
    public boolean registarApostador(Apostador a, boolean aut){
        boolean flag = true;
        for(Apostador ap : getApostadores()){
            if(ap.checkEmail(a.getEmail()) && flag){
                flag = false;
                popupWindow(3, "Já foi registado um apostador com esse email. Por favor tente outro.", "Aviso");
            }
        }
        if(flag && aut){
            data.addApostador(a);
            popupWindow(1, "Registado com sucesso!", "Sucesso");
            return true;
        } else popupWindow(2, "A autorização é necessária para o registo.", "Aviso");
        return false;
    }
    
    public void save(){
        try {
            data.save(data);
        } catch (IOException ex) {
            Logger.getLogger(BetESS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}