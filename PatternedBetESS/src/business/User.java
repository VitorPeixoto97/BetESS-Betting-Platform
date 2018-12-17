/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author danie
 */
public interface User {
    public String getEmail();
    
    public boolean verifyPassword(String s);
    
    public String getNome();
    
    public double update(Evento e, double total);
    
}
