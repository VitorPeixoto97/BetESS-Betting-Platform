/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class FutebolEvento implements Evento{
    
    private int id;
    private boolean estado;
    private String resultado;
    private List<Equipa> equipas; //{Equipa da casa, Equipa forasteira}
    private List<Double> odds;    //{oddVitoria da casa, oddEmpate, oddVitoria forasteira}
    
    
    public FutebolEvento(int id, double oddV, double oddE, double oddD, boolean estado, String resultado, Equipa equipaC, Equipa equipaF){
        this.id = id;
        this.estado = estado;
        this.resultado = resultado;
        this.equipas = new ArrayList<>(2);
        this.equipas.add(equipaC);
        this.equipas.add(equipaF);
        this.odds = new ArrayList<>(3);
        this.odds.add(oddV);
        this.odds.add(oddE);
        this.odds.add(oddD);
    }
    public FutebolEvento(FutebolEvento e){
        this.id = e.getID();
        this.estado = e.getEstado();
        this.resultado = e.getResultado();
        this.equipas = e.getEquipas();
        this.odds = e.getOdds();
    }
    
    @Override
    public int getID(){
        return this.id;
    }
    public double getOddV(){
        return this.odds.get(0);
    }
    public double getOddE(){
        return this.odds.get(1);
    }
    public double getOddD(){
        return this.odds.get(2);
    }
    @Override
    public boolean getEstado(){
        return this.estado;
    }
    @Override
    public String getResultado(){
        return this.resultado;
    }
    
    @Override
    public List<Equipa> getEquipas(){
        return this.equipas;
    }
    
    public List<Double> getOdds(){
        return this.odds;
    }
    
    public void setID(int id){
        this.id=id;
    }
    public void setOddV(double oddV){
        this.odds.add(0, oddV);
    }
    public void setOddE(double oddE){
        this.odds.add(1, oddE);
    }
    public void setOddD(double oddD){
        this.odds.add(2, oddD);
    }
    public void setEstado(boolean estado){
        this.estado=estado;
    }
    public void setResultado(String resultado){
        this.resultado=resultado;
    }
    
    public boolean equals(FutebolEvento e) {
        return e.getID() == this.id;
    }
    
    public Equipa getEquipaC(){
        return this.equipas.get(0);
    }
    
    public Equipa getEquipaF(){
        return this.equipas.get(1);
    }
    
    public void setEquipaC(Equipa equipaC){
        this.equipas.add(0, equipaC);
    }
    
    public void setEquipaF(Equipa equipaF){
        this.equipas.add(1,equipaF);
    }
}
