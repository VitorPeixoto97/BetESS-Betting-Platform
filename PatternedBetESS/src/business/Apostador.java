/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.HashMap;
import java.io.Serializable;
import java.util.Map;


/**
 *
 * @author vitorpeixoto
 */
public class Apostador implements Serializable{
    private int id;
    private String email;
    private String password;
    private String nome;
    private double esscoins;
    private boolean locked;
    private Map<Integer, Aposta> apostas;
    
    public Apostador(){
        this.id = 9999;
        this.email = "";
        this.password = "";
        this.nome = "";
        this.esscoins = 0.0;
        this.apostas = new HashMap<>();
        this.locked = false;
    }
    
    public Apostador(int id, String email, String password, String nome, double esscoins, Map<Integer, Aposta> apostas){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.esscoins = esscoins;
        this.apostas = apostas;
        this.locked = false;
    }
    
    public Apostador(Apostador a){
        this.id = a.getID();
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.nome = a.getNome();
        this.esscoins = a.getESSCoins();
        this.apostas = a.getApostas();
        this.locked = a.isLocked();
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
    public Map<Integer, Aposta> getApostas(){
        return this.apostas;
    }
  
    public boolean isLocked(){
        return this.locked;
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

    public Apostador update(int id, int resultado) {
        
        if(this.apostas.containsKey(id)){
           
           Aposta a = this.apostas.get(id);
           if(a.getResultado() == resultado){
               this.esscoins += a.earnings();
           }
           this.apostas.remove(id);
        }    
        return this;
    }

}
