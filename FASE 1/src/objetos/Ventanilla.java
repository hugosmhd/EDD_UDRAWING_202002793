package objetos;

import listas.PilaImagenes;

public class Ventanilla {
    
    int idVentanilla;
    boolean disponible;
    PilaImagenes imagenesCliente;
    Cliente cliente;
    
    public Ventanilla(int idVentanilla, boolean disponible) {
        this.idVentanilla = idVentanilla;
        this.disponible = disponible;
        this.imagenesCliente = new PilaImagenes();
        this.cliente = null;
    }

    public int getIdVentanilla() {
        return idVentanilla;
    }

    public void setIdVentanilla(int idVentanilla) {
        this.idVentanilla = idVentanilla;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public PilaImagenes getImagenesCliente() {
        return imagenesCliente;
    }

    public void setImagenesCliente(PilaImagenes imagenesCliente) {
        this.imagenesCliente = imagenesCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    

}
