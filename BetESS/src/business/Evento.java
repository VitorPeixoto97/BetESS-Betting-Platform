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
public class Evento {
    private int id;
    private double oddV;
    private double oddE;
    private double oddD;
    private boolean estado;
    private String resultado;
    private Equipa equipaC;
    private Equipa equipaF;
    
    public int getID(){
        return this.id;
    }
    public double getOddV(){
        return this.oddV;
    }
    public double getOddE(){
        return this.oddE;
    }
    public double getOddD(){
        return this.oddD;
    }
    public boolean getEstado(){
        return this.estado;
    }
    public String getResultado(){
        return this.resultado;
    }
    public Equipa getEquipaC(){
        return this.equipaC;
    }
    public Equipa getEquipaF(){
        return this.equipaF;
    }
    public void setID(int id){
        this.id=id;
    }
    public void setOddV(double oddV){
        this.oddV=oddV;
    }
    public void setOddE(double oddE){
        this.oddE=oddE;
    }
    public void setOddD(double oddD){
        this.oddD=oddD;
    }
    public void setEstado(boolean estado){
        this.estado=estado;
    }
    public void setResultado(String resultado){
        this.resultado=resultado;
    }
    public void setEquipaC(Equipa equipaC){
        this.equipaC=equipaC;
    }
    public void setEquipaF(Equipa equipaF){
        this.equipaF=equipaF;
    }
    
}
