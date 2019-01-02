package business;

import data.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author vitorpeixoto
 */
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
    
    public void criarEvento(Evento e){
        data.getEventos().put(e.getID(), e);
    }
    
    public void finalizarEvento(Evento e, String res){
        data.getEventos().get(e.getID()).setEstado(false);
        data.getEventos().get(e.getID()).setResultado(res);
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
    
    //função para remover código duplicado desnecessário
    public void notification(int tipo, String texto, String titulo){
        ImageIcon icon = new ImageIcon();
        if(tipo==1) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/check.png"));
        else if(tipo==2) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/warning.png"));
        else if(tipo==3) icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/forbidden.png"));
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE, icon);
    }
    

}
