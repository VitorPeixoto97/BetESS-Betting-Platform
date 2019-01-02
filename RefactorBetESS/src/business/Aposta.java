package business;

import java.io.Serializable;

public class Aposta implements Serializable{
    private int resultado; //resultado em que o apostador apostou (V/E/D)
    private int valor;
    private Evento evento;
    private boolean visto;
    
    public Aposta(){
        this.resultado=0;
        this.valor=0;
        this.evento = new Evento();
        this.visto = true;
    }
    
    public Aposta(int resultado, int valor, Evento evento, boolean visto){
        this.resultado = resultado;
        this.valor= valor;
        this.evento = evento;
        this.visto = visto;
    }
    
    public Aposta(Aposta a){
        this.resultado = a.getResultado();
        this.valor = a.getValor();
        this.evento = a.getEvento();
        this.visto = a.getVisto();
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
    public boolean getVisto(){
        return this.visto;
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
    public void notificaApostador(){
        this.visto=false;
    }
    public void visto(){
        this.visto=true;
    }
    public void distribuirGanhos(Apostador a, int res){
        if(this.getResultado()==res){
            if(res==1)      a.adicionarESSCoins(this.getEvento().getOddV()*this.getValor());
            else if(res==2) a.adicionarESSCoins(this.getEvento().getOddE()*this.getValor());
            else if(res==3) a.adicionarESSCoins(this.getEvento().getOddD()*this.getValor());
        }
    }
    public double ganhos(){
        double ganhos = 0.0;
        String[] venc = this.evento.getResultado().split("-");
        int res;
        if(Integer.parseInt(venc[0])>Integer.parseInt(venc[1])) res = 1;
        else if(Integer.parseInt(venc[1])>Integer.parseInt(venc[0])) res = 3;
        else res = 2;
        if(resultado==res){
            switch (res) {
                case 1:
                    ganhos = evento.getOddV()*valor;
                    break;
                case 2:
                    ganhos = evento.getOddE()*valor;
                    break;
                case 3:
                    ganhos= evento.getOddD()*valor;
                    break;
                default:
                    break;
            }
        }
        return ganhos;
    }
}
