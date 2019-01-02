/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.util.List;

/**
 *
 * @author vitorpeixoto
 */
public interface Evento{
    
    public int getID();
    
    public boolean getEstado();
    
    public String getResultado();
    
    public List<Equipa> getEquipas();
    
    public List<Double> getOdds();
    
    public void setEstado(boolean estado);
    
    public void setResultado(String resultado);
    
}
