package nodos;

import objetos.Conexion;

public class NodoGrafo {
    Conexion data;
    NodoGrafo siguiente;

    public NodoGrafo(Conexion data) {
        this.data = data;
        this.siguiente = null;
    }

    public Conexion getData() {
        return data;
    }

    public void setData(Conexion data) {
        this.data = data;
    }

    public NodoGrafo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGrafo siguiente) {
        this.siguiente = siguiente;
    }

    
}
