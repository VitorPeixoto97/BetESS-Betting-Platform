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
public class Equipa {
    private int id;
    private String nome;
    private boolean estado;
    
    public Equipa(){
        this.id=0;
        this.nome="";
        this.estado=false;
    }
    
    public Equipa(int id, String nome, boolean estado){
        this.id=id;
        this.nome=nome;
        this.estado=estado;
    }
    
    public Equipa(Equipa e){
        this.id = e.getID();
        this.nome = e.getNome();
        this.estado = e.getEstado();
    }
    
    public int getID(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public boolean getEstado(){
        return this.estado;
    }
    
    public void setID(int id){
        this.id=id;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setEstado(boolean estado){
        this.estado=estado;
    }
}
