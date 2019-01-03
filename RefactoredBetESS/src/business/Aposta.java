package business;

import java.io.Serializable;

public class Aposta implements Serializable{
    private int palpite; //resultado em que o apostador apostou (V/E/D)
    private int valor;
    private Evento evento;
    private Apostador apostador;
    private boolean visto;
    
    public Aposta(){
        this.palpite=0;
        this.valor=0;
        this.evento = new Evento();
        this.apostador = new Apostador();
        this.visto = true;
    }
    
    public Aposta(int resultado, int valor, Evento evento, Apostador a, boolean visto){
        this.palpite = resultado;
        this.valor= valor;
        this.evento = evento;
        this.apostador = apostador;
        this.visto = visto;
    }
    
    public Aposta(Aposta a){
        this.palpite = a.getPalpite();
        this.valor = a.getValor();
        this.evento = a.getEvento();
        this.apostador = a.getApostador();
        this.visto = a.getVisto();
    }

    public int getPalpite(){
        return this.palpite;
    }
    public int getValor(){
        return this.valor;
    }
    public Evento getEvento(){
        return this.evento;
    }
    public int getEventoID(){
        return evento.getID();
    }
    public boolean getEstadoEvento(){
        return evento.getEstado();
    }
    public String getResultadoEvento(){
        return evento.getResultado();
    }
    public Apostador getApostador(){
        return this.apostador;
    }
    public boolean getVisto(){
        return this.visto;
    }
    public String getEquipaCasaNome(){
        return evento.getEquipaCasaNome();
    }
    public String getEquipaForaNome(){
        return evento.getEquipaForaNome();
    }
    public double getOddV(){
        return evento.getOddV();
    }
    public double getOddE(){
        return evento.getOddE();
    }
    public double getOddD(){
        return evento.getOddD();
    }

    public void notificaApostador(){
        this.visto=false;
    }
    public void visto(){
        this.visto=true;
    }

    public double ganhos(){
        double ganhos = 0.0;
        int p = evento.getRes();
        if(palpite==p){
            if(p==1)      ganhos = evento.getOddV()*valor;
            else if(p==2) ganhos = evento.getOddE()*valor;
            else if(p==3) ganhos = evento.getOddD()*valor;
        }
        return ganhos;
    }
}
