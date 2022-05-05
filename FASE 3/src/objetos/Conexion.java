package objetos;

import nodos.NodoVector;

public class Conexion {
    private NodoVector destino;
    private int peso;
    private boolean graficado;
    
    public Conexion(NodoVector destino, int peso, boolean graficado) {
        this.destino = destino;
        this.peso = peso;
        this.graficado = graficado;
    }
    public NodoVector getDestino() {
        return destino;
    }
    public void setDestino(NodoVector destino) {
        this.destino = destino;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public boolean isGraficado() {
        return graficado;
    }
    
    public void setGraficado(boolean graficado) {
        this.graficado = graficado;
    }   

    
}
