package objetos;

public class Cliente {
    public String idCliente;
    public String nombre;
    public int cantidadColor;
    public int cantidadBW;
    public int totalImagenes;
    public String ventanillaAtencion;
    public int totalPasos;
    public boolean terminoImpresion;

    public Cliente(String idCliente, String nombre, int cantidadColor, int cantidadBW) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.cantidadColor = cantidadColor;
        this.cantidadBW = cantidadBW;
        this.totalImagenes = 0;
        this.totalPasos = 0;
        this.ventanillaAtencion = null;
        this.terminoImpresion = false;
    }
    public Cliente() {
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

    public String getVentanillaAtencion() {
        return ventanillaAtencion;
    }

    public void setVentanillaAtencion(String ventanillaAtencion) {
        this.ventanillaAtencion = ventanillaAtencion;
    }

    public int getTotalImagenes() {
        return totalImagenes;
    }

    public void setTotalImagenes() {
        this.totalImagenes += 1;
    }

    public int getTotalPasos() {
        return totalPasos;
    }

    public void setTotalPasos() {
        this.totalPasos += 1;
    }

    public boolean isTerminoImpresion() {
        return terminoImpresion;
    }

    public void setTerminoImpresion(boolean terminoImpresion) {
        this.terminoImpresion = terminoImpresion;
    }

    

    

    
}
