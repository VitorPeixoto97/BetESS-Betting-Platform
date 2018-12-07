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
public interface Factory {
    
    public Evento newEvento(int id, double oddV, double oddE, double oddD, boolean estado, String resultado, Equipa c, Equipa f);
    
    public Evento newEvento(Evento fe);
    
    public Equipa newEquipa(int id, String equipa0, boolean estado, String simbolo);
}
