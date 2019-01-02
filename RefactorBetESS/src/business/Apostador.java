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
        ArrayList<Aposta> apostas = new ArrayList<>();
        for(Aposta ap : this.getApostas()){
            if(ap.getEvento().getEstado())
                apostas.add(ap);
        }
        return apostas;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setApostas(ArrayList<Aposta> apostas){
        this.apostas=apostas;
    }
    
    public void efetuarAposta(Aposta a){
        apostas.add(a);
        levantarESSCoins(a.getValor());
    }
    
    public void removerAposta(Aposta a){
        if(a.getEvento().getEstado()){
            apostas.remove(a);
            this.esscoins+=a.getValor();
        }
    }
    
    public void adicionarESSCoins(double coins){
        this.esscoins+=coins;
    }
    public void levantarESSCoins(double coins){
        this.esscoins-=coins;
    }
    public void notificate(){
        for(Aposta a : apostas){
            if(!a.getVisto()){
                double ganhos = a.ganhos();
                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/ball.png"));
                JOptionPane.showMessageDialog(null, "Resultado final: "+a.getEvento().getEquipaC().getNome()+ " " +
                                            a.getEvento().getResultado()+" " +
                                            a.getEvento().getEquipaF().getNome()+
                                            "\nGanhos: "+ganhos+" ESScoins", "Evento terminado", JOptionPane.INFORMATION_MESSAGE, icon);
                a.visto();
            }
        }
    }
}
