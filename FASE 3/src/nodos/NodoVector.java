package nodos;

import objetos.Lugar;

public class NodoVector {
    Lugar data;
    NodoVector siguiente;
    NodoGrafo adyacencia;

    public NodoVector(Lugar data){
        this.data=data;
        siguiente=null;
        adyacencia = null;
    }

    public Lugar getData() {
        return data;
    }

    public void setData(Lugar data) {
        this.data = data;
    }

    public NodoVector getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoVector siguiente) {
        this.siguiente = siguiente;
    }

    public NodoGrafo getAdyacencia() {
        return adyacencia;
    }

    public void setAdyacencia(NodoGrafo adyacencia) {
        this.adyacencia = adyacencia;
    }

    
}
