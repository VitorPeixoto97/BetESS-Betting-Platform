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
public class Odds {
    private double vitoria;
    private double empate;
    private double derrota;
    
    public Odds(){
        this.vitoria=0.0;
        this.empate=0.0;
        this.derrota=0.0;
    }
    public Odds(double v, double e, double d){
        this.vitoria=v;
        this.empate=e;
        this.derrota=d;
    }
    
    public double getOddV(){
        return this.vitoria;
    }
    public double getOddE(){
        return this.empate;
    }
    public double getOddD(){
        return this.derrota;
    }
}
