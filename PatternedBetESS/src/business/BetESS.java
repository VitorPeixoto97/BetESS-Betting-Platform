package business;

import persistence.Data;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author vitorpeixoto
 */
public class BetESS implements Serializable{
    Data data;
    
    public BetESS() {
        this.data = new Data();
    }
    public BetESS(HashMap<String,User> u, HashMap<Integer,Evento> ev, HashMap<Integer,Equipa> eq, HashMap<Integer,Aposta> ap) {
        this.data = new Data(u,ev,eq,ap);
    }
    public BetESS(Data d) {
        this.data = d;
    }
    
    public Map<Integer, Evento> getEventos(){
        return data.getEventos();
    }
    
    public Map<Integer, Equipa> getEquipas(){
        return data.getEquipas();
    }
    
    public Map<String, User> getUtilizadores(){
        return data.getUtilizadores();
    }
    
    public Map<Integer, Aposta> getApostas(){
        return data.getApostas();
    }
    
    public ArrayList<Aposta> getApostas(Apostador ap){
        ArrayList<Aposta> apostas = new ArrayList<Aposta>();
        for(Aposta a : data.getApostas().values()){
            if(a.getApostador().equals(ap)){
                apostas.add(a);
            }
        }
        return apostas;
    }
    
    public User login(String email, String password){
        User erro = this.data.getUtilizadores().get(email);
        if(erro != null && !erro.verifyPassword(password)){
            erro = null;
        }
        return erro;
    }
    
    public int registar(String nome, String email, String password, int coins, boolean aut){
        if(aut){
            boolean flag = true;
            for(User a : this.getUtilizadores().values()){
                if(a.getEmail().equals(email) && flag){
                    flag = false;
                    System.out.println("Já existe!!!\n");
                    return 1;
                }
            }
            if(flag){
                Apostador novo = new Apostador(email, password, nome, coins);
                data.newApostador(novo);
                this.save();
            }
        }
        else{
            return 2;
        }
        return 0;
    }
    
    public void criarEvento(String equipaC, String equipaF, Double oddV, Double oddE, Double oddD, User u){
        Equipa casa = new Equipa();
        Equipa fora = new Equipa();
        for(Equipa a : this.data.getEquipas().values()){
            if(a.getNome().equals(equipaC)) casa = a;
            else if(a.getNome().equals(equipaF)) fora = a;
        }
        if(equipaC.equals(equipaF)){
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/forbidden.png"));
            JOptionPane.showMessageDialog(null, "As equipas selecionadas são a mesma. Por favor escolha outra.", "Aviso", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        else{
            int id = this.data.getEventos().size()+1;
            Evento evento = new EventoFutebol(id, oddV , oddE, oddD, true, 0, casa, fora, u); 
            data.addEvento(evento);
            this.save();
            
            StringBuilder sb = new StringBuilder();
            sb.append("Evento criado e disponível.");
            if(u != null)
                sb.append("\nSerá notificado quando o evento terminar.");
            
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/check.png"));
            JOptionPane.showMessageDialog(null, sb.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }
    
    public void fecharEvento(String evento, String resultado){
        String[] equipas = evento.split(" X ");
        ArrayList<Evento> evAtiv = new ArrayList<>();
        this.getEventos().values().stream().filter(e -> e.getEstado()).forEach((e) -> evAtiv.add(e));
        for(Evento e : evAtiv){
            if(e.getEquipaC().getNome().equals(equipas[0]) && e.getEquipaF().getNome().equals(equipas[1])){
                
                String[] venc = resultado.split("-");
                int res;
                if(Integer.parseInt(venc[0])>Integer.parseInt(venc[1])){ //equipa casa venceu
                    System.out.println("equipa casa venceu\n");
                    res = 1;
                }
                else if(Integer.parseInt(venc[1])>Integer.parseInt(venc[0])){ //equipa fora venceu
                    System.out.println("equipa fora venceu\n");
                    res = 3;
                }
                else{ //empate
                    System.out.println("empate\n");
                    res = 2;
                }

                this.data.endEvento(e,res);
            }
        }
        this.save();
    }
    
    public void adicionarEquipa(Equipa e){
        data.addEquipa(e);
    }
    
    public void removerEquipa(Equipa e){
        data.removeEquipa(e);
    }

    public void efetuarAposta(Evento e, Apostador a, int res, int val, double odd){
        
            boolean apostou = false;
            for(Aposta ap : data.getApostas().values()){
                if(ap.getApostador().equals(a) && ap.getEvento().equals(e))
                    apostou=true;
            }

            if(apostou) {
                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/forbidden.png"));
                JOptionPane.showMessageDialog(null, "Já registou uma aposta neste evento.", "Aviso", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            else if(a.getESSCoins()-val >= 0){
                Aposta ap = new Aposta(data.getApostas().size()+1, res, val, odd, a, e);
                data.getEventos().get(e.getID()).getUtilizadores().put(a.getEmail(), a);
                data.addAposta(ap, a.getEmail());
                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/check.png"));
                JOptionPane.showMessageDialog(null, "Aposta registada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, icon);
                this.save();
                
            }
            else{
                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/forbidden.png"));
                JOptionPane.showMessageDialog(null, "Não tem saldo suficiente para realizar a aposta.", "Aviso", JOptionPane.INFORMATION_MESSAGE, icon);
            }
    }
    
    public void cancelarAposta(Aposta a, String apostadorID){
        data.removeAposta(a);
    }
    
    public User getApostador(String email){
        return data.getUtilizadores().get(email);
    }
    
    public void levantarCoins(Apostador a, double quantia){
        if (a.getESSCoins()-quantia < 5){
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/forbidden.png"));
            JOptionPane.showMessageDialog(null, "O seu saldo atual não lhe permite levantar essa quantia. Tem de manter um saldo mínimo de 5 ESScoins!", "Aviso", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        else{
            Apostador ap = (Apostador) this.getUtilizadores().get(a.getEmail());
            ap.levantarESSCoins(quantia);
            this.save();
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/check.png"));
            JOptionPane.showMessageDialog(null, "Quantia depositada na sua conta bancária!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, icon);
        }
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
