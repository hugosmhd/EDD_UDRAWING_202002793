package listas;

import nodos.NodoSimple;

public class Pila {
    NodoSimple primero;
    NodoSimple ultimo;

    public Pila() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void apilar(Object data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
        } else {
            NodoSimple actual = this.primero;
            this.primero = nuevo;
            this.primero.setSiguiente(actual);
        }
    }

    public void desapilar() {
        NodoSimple actual = this.primero.getSiguiente();
        this.primero.setSiguiente(null);
        this.primero = actual;
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            if (actual.getSiguiente()!=null) System.out.print(actual.getData() + ",");
            else System.out.println(actual.getData());
            actual=actual.getSiguiente();
        }
            
    }

}
