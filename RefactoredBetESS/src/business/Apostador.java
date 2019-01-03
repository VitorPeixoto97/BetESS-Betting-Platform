package business;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Apostador implements Serializable{
    private int id;
    private String email;
    private String password;
    private String nome;
    private double esscoins;
    private ArrayList<Aposta> apostas;
    
    public Apostador(){
        this.id = 9999;
        this.email = "";
        this.password = "";
        this.nome = "";
        this.esscoins = 0.0;
        this.apostas = new ArrayList<>();
    }
    
    public Apostador(int id, String email, String password, String nome, double esscoins, ArrayList<Aposta> apostas){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.esscoins = esscoins;
        this.apostas = apostas;
    }
    
    public Apostador(Apostador a){
        this.id = a.getID();
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.nome = a.getNome();
        this.esscoins = a.getESSCoins();
        this.apostas = a.getApostas();
    }
    
    public int getID(){
        return this.id;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getNome(){
        return this.nome;
    }
    public double getESSCoins(){
        return this.esscoins;
    }
    public ArrayList<Aposta> getApostas(){
        return this.apostas;
    }
    public ArrayList<Aposta> getApostasAtivas(){
        ArrayList<Aposta> aps = new ArrayList<>();
        for(Aposta ap : this.getApostas()){
            if(ap.getEstadoEvento())
                aps.add(ap);
        }
        return aps;
    }
    
    // método responsável por efetuar uma Aposta.
    public boolean efetuarAposta(Aposta a){
        if(podeApostar(a)){
            apostas.add(a);
            levantarESSCoins(a.getValor());
            BetESS ex = new BetESS();
            ex.notification(1, "Aposta registada com sucesso!", "Sucesso");
            return true;
        }
        return false;
    }
    // método auxiliar que verifica se uma Aposta é válida.
    public boolean podeApostar(Aposta aposta){
        BetESS ex = new BetESS();
        boolean saldoInsuf = esscoins-aposta.getValor() < 0;
        boolean jaApostou  = false;
        for (Aposta ap : apostas)
            if(ap.getEventoID() == aposta.getEventoID())
                jaApostou = true;

        if(jaApostou){
            ex.notification(3, "Já registou uma aposta neste evento.", "Aviso");
            return false;}
        
        else if(saldoInsuf){
            ex.notification(3, "Não tem saldo suficiente para realizar a aposta.", "Aviso");
            return false;}
        
        return true;
    }
    
    // método responsável por remover uma Aposta.
    public void removerAposta(Aposta a){
        if(a.getEstadoEvento()){
            apostas.remove(a);
            this.esscoins+=a.getValor();
        }
    }
    
    // métodos responsáveis pelas movimentações monetárias do Apostador.
    public void adicionarESSCoins(double coins){
        this.esscoins+=coins;
    }
    public void levantarESSCoins(double coins){
        this.esscoins-=coins;
    }
    
    // método que apresenta a notificação do resultado das suas Apostas.
    public void notificate(){
        for(Aposta a : apostas){
            if(!a.getVisto()){
                double ganhos = a.ganhos();
                BetESS ex = new BetESS();
                ex.notification(4, "Resultado final: " + 
                                    a.getEquipaCasaNome()  + " " +
                                    a.getResultadoEvento() + " " +
                                    a.getEquipaForaNome()  +
                                    "\nGanhos: " + ganhos + " ESScoins", 
                                    "Evento terminado");
                a.visto();
            }
        }
    }
    public boolean checkEmail(String em){
        return email.equals(em);
    }
}
