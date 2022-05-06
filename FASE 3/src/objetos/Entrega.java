package objetos;

import estructuras.ListaSimple;
import nodos.NodoVector;

public class Entrega {
    private NodoVector sucursal;
    private long timestamp;
    private NodoVector destino;
    private Cliente cliente;
    private Mensajero mensajero;
    private ListaSimple ruta;

    

    public Entrega(NodoVector sucursal, NodoVector destino, Cliente cliente, Mensajero mensajero, ListaSimple ruta) {
        this.sucursal = sucursal;
        this.timestamp = System.currentTimeMillis();
        this.destino = destino;
        this.cliente = cliente;
        this.mensajero = mensajero;
        this.ruta = ruta;
    }
    public NodoVector getSucursal() {
        return sucursal;
    }
    public void setSucursal(NodoVector sucursal) {
        this.sucursal = sucursal;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public NodoVector getDestino() {
        return destino;
    }
    public void setDestino(NodoVector destino) {
        this.destino = destino;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Mensajero getMensajero() {
        return mensajero;
    }
    public void setMensajero(Mensajero mensajero) {
        this.mensajero = mensajero;
    }
    public ListaSimple getRuta() {
        return ruta;
    }
    public void setRuta(ListaSimple ruta) {
        this.ruta = ruta;
    }

    

}
