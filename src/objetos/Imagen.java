package objetos;

public class Imagen {
    String idCliente;
    boolean color;
    int pasos;
    
    public Imagen(String idCliente, boolean color) {
        this.idCliente = idCliente;
        this.color = color;
        this.pasos = 0;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    
}
