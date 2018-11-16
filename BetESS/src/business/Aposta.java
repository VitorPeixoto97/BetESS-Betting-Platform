/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author vitorpeixoto
 */
public class Aposta {
    private int id;
    private int resultado; //resultado em que o apostador apostou (V/E/D)
    private int valor;
    private Evento evento;
    
    public int getID(){
        return this.id;
    }
    public int getResultado(){
        return this.resultado;
    }
    public int getValor(){
        return this.valor;
    }
    public Evento getEvento(){
        return this.evento;
    }
    
    public void setID(int id){
        this.id=id;
    }
    public void setResultado(int resultado){
        this.resultado=resultado;
    }
    public void setValor(int valor){
        this.valor=valor;
    }
    public void setEvento(Evento evento){
        this.evento=evento;
    }
}
