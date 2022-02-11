package objetos;

public class Cliente {
    public String idCliente;
    public String nombre;
    public int cantidadColor;
    public int cantidadBW;

    public Cliente(String idCliente, String nombre, int cantidadColor, int cantidadBW) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.cantidadColor = cantidadColor;
        this.cantidadBW = cantidadBW;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadColor() {
        return cantidadColor;
    }

    public void setCantidadColor(int cantidadColor) {
        this.cantidadColor = cantidadColor;
    }

    public int getCantidadBW() {
        return cantidadBW;
    }

    public void setCantidadBW(int cantidadBW) {
        this.cantidadBW = cantidadBW;
    }

    
}
