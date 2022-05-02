package objetos;

public class Conexion {
    private Lugar destino;
    private int peso;
    
    public Conexion(Lugar destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
    public Lugar getDestino() {
        return destino;
    }
    public void setDestino(Lugar destino) {
        this.destino = destino;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }

    
}
