package objetos;

public class Imagen {
    String idCliente;
    boolean color;
    
    public Imagen(String idCliente, boolean color) {
        this.idCliente = idCliente;
        this.color = color;
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

    
}
