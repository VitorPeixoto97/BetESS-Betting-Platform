/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import java.util.Map;

/**
 *
 * @author vitorpeixoto
 */
public interface Evento {
    
    public int getID();
    public double getOddV();
    public double getOddE();
    public double getOddD();
    public boolean getEstado();
    public int getResultado();
    public Equipa getEquipaC();
    public Equipa getEquipaF();
    public List<User> getUtilizadores();
    
    public void setID(int id);
    public void setOddV(double oddV);
    public void setOddE(double oddE);
    public void setOddD(double oddD);
    public void setEstado(boolean estado);
    public void setResultado(int resultado);
    public void setEquipaC(Equipa equipaC);
    public void setEquipaF(Equipa equipaF);
    
    public boolean equals(Evento e);
    
    public void notifyUsers();
    public void addUser(User u);
    public void removeUser(User u);
    
    public int vencedor(String res);
    public void finalizar(int res);
}
