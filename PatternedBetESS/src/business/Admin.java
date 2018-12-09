/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;


public class Admin implements Serializable, User {
    private final String email;
    private String password;
    private String nome;
    
    public Admin(String email, String password, String nome){
        this.email = email;
        this.password = password;
        this.nome = nome;
    }
    
    public Admin(Admin a){
        this.email = a.getEmail();
        this.password = a.password;
        this.nome = a.getNome();
    }
    
    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean verifyPassword(String s) {
        return this.password.equals(s);
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public double update(Evento e, double d) {
        return 0.0d;
    }
    
}
