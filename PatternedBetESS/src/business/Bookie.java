/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Bookie implements Serializable, User{
    private final String email;
    private String password;
    private String nome;
    private List<String> notifs;
    
    public Bookie(String email, String password, String nome){
        this.email = email;
        this.password = password;
        this.nome = nome;
        notifs = new ArrayList<>();
    }
    
    public Bookie(Bookie b){
        this.email = b.getEmail();
        this.password = b.password;
        this.nome = b.getNome();
        this.notifs = b.getNotifs();
    }
    
    @Override
    public String getEmail(){
        return this.email;
    }
    
    public boolean hasNotifs(){
        return !this.notifs.isEmpty();
    }

    @Override
    public boolean verifyPassword(String s) {
        return this.password.equals(s);
    }

    @Override
    public String getNome() {
        return nome;
    }

    public List<String> getNotifs() {
        return this.notifs;
    }
    
    public void clearNotifs(){
        this.notifs = new ArrayList<>();
    }

    @Override
    public double update(Evento e, double d) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        switch(e.getResultado()){
                case 1:
                    sb.append(e.getEquipaC().getNome()).append(" ganha contra ").append(e.getEquipaF().getNome()).append("!\n");
                    break;
                case 2: 
                    sb.append(e.getEquipaC().getNome()).append(" empata contra ").append(e.getEquipaF().getNome()).append("!\n");
                    break;
                case 3:
                    sb.append(e.getEquipaC().getNome()).append(" perde contra ").append(e.getEquipaF().getNome()).append("!\n");
                    break;
        }
        sb.append("Ganhos ").append(d).append(" ESScoins.");
           
        this.notifs.add(sb.toString());
        
        return d;
    }
}
