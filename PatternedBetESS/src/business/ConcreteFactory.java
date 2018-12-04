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
public class ConcreteFactory implements Factory{

    @Override
    public Evento newEvento(int id, double oddV, double oddE, double oddD, boolean estado, String resultado, Equipa c, Equipa f) {
        return new FutebolEvento(id, oddV, oddE, oddD, estado, resultado, c, f);
    }

    @Override
    public Evento newEvento(Evento e) {
        Evento ne = null;
        
        if("FutebolEvento".equals(e.getClass().getSimpleName()))
            ne = new FutebolEvento((FutebolEvento) e);
        
        return ne;  
    }

    @Override
    public Equipa newEquipa(int id, String nome, boolean estado, String simbolo) {
        return new Equipa(id,nome,estado,simbolo);
    }
    
}
