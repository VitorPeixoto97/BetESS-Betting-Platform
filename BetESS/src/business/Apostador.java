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
    
    public Apostador(){
        this.id = 9999;
        this.email = "";
        this.password = "";
        this.nome = "";
        this.esscoins = 0.0;
        this.apostas = new HashMap<>();
    }
    
    public Apostador(int id, String email, String password, String nome, double esscoins, HashMap<Integer,Aposta> apostas){
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
    public HashMap<Integer,Aposta> getApostas(){
        return this.apostas;
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
    public void setApostas(HashMap<Integer,Aposta> apostas){
        this.apostas = apostas;
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
