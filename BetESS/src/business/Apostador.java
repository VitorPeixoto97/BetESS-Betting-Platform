/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.HashMap;

/**
 *
 * @author vitorpeixoto
 */
public class Apostador {
    private int id;
    private String email;
    private String password;
    private String nome;
    private double esscoins;
    private HashMap<Integer,Aposta> apostas;
    
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
    
    public void setID(int id){
        this.id=id;
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
    public void setESSCoins(double esscoins){
        this.esscoins=esscoins;
    }
    
    public void efetuarAposta(Aposta a){
        apostas.put(a.getID(),a);
    }
    
    public void removerAposta(Aposta a){
        if(a.getEvento().getEstado())
            apostas.remove(a.getID(), a);
    }
    
    public void adicionarESSCoins(double coins){
        this.esscoins+=coins;
    }
    public void levantarESSCoins(double coins){
        if(this.esscoins-coins > 5.00){
            this.esscoins-=coins;
        }
    }
}
