/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.HashMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Apostador implements Serializable{
    private final int id;
    private String email;
    private String password;
    private String nome;
    private double esscoins;
    private Map<Integer, Aposta> apostas;
    private List<String> notifications;
    
    public Apostador(){
        this.id = 9999;
        this.email = "";
        this.password = "";
        this.nome = "";
        this.esscoins = 0.0;
        this.apostas = new HashMap<>();
        this.notifications = new ArrayList<>();
    }
    
    public Apostador(int id, String email, String password, String nome, double esscoins){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.esscoins = esscoins;
        this.apostas = new HashMap<>();
        this.notifications = new ArrayList<>();
    }
    
    public Apostador(Apostador a){
        this.id = a.getID();
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.nome = a.getNome();
        this.esscoins = a.getESSCoins();
        this.apostas = a.getApostas();
        this.notifications = a.getNotif();
    }
    
    public int getID(){
        return this.id;
    }
    public String getEmail(){
        return this.email;
    }
    private String getPassword(){
        return this.password;
    }
    public boolean verifyPassword(String pass){
        return this.password.equals(pass);
    }
    public String getNome(){
        return this.nome;
    }
    public double getESSCoins(){
        return this.esscoins;
    }
    public Map<Integer, Aposta> getApostas(){
        return this.apostas;
    }
  
    public List<String> getNotif(){
        return this.notifications;
    }
    
    public boolean hasNotif(){
        return notifications.size() > 0;
    }
    
    public void clearNotifs(){
        this.notifications = new ArrayList<>();
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
    
    public void efetuarAposta(Aposta a){
        apostas.put(a.getID(), a);
        levantarESSCoins(a.getValor());
    }
    
    public Aposta getAposta(int id){
        return this.apostas.get(id);
    }
    
    public void removerAposta(Aposta a){
            apostas.remove(a.getID());
            this.esscoins+=a.getValor();
    }
        
    public void adicionarESSCoins(double coins){
        this.esscoins+=coins;
    }
    public void levantarESSCoins(double coins){
        this.esscoins-=coins;
    }

    public void update(Evento e) {
        
        if(this.apostas.containsKey(e.getID())){
           
           Aposta a = this.apostas.get(e.getID());
           if(a.getResultado() == e.getResultado()){
               this.esscoins += a.earnings();
           }
           
           StringBuilder sb = new StringBuilder();
           sb.append("Resultado final: ");
           sb.append(e.getEquipaC().getNome()).append(" ");
           sb.append(e.getResultado()).append(" ");
           sb.append(e.getEquipaF().getNome()).append("\n");
           sb.append("Ganhos: ").append(a.earnings()).append("ESScoins.");
           
           this.notifications.add(sb.toString());
           
           this.apostas.remove(id);
        }
    }

}
