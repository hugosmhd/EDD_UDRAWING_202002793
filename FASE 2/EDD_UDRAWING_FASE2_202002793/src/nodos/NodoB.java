package nodos;

import objetos.Cliente;

public class NodoB {

    //Valores
    Cliente data;
    //Apuntadores
    NodoB siguiente;
    NodoB anterior;
    Pagina derecha;
    Pagina izquierda;

    public NodoB(Cliente data) {
        this.data = data;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
    }


    public void imprimir() {
        
    }

    public Cliente getData() {
        return data;
    }

    public void setData(Cliente data) {
        this.data = data;
    }

    public NodoB getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoB anterior) {
        this.anterior = anterior;
    }

    public NodoB getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoB siguiente) {
        this.siguiente = siguiente;
    }

    public Pagina getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Pagina izquierda) {
        this.izquierda = izquierda;
    }

    public Pagina getDerecha() {
        return derecha;
    }

    public void setDerecha(Pagina derecha) {
        this.derecha = derecha;
    }    
}

