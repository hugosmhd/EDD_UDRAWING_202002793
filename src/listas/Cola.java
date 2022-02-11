package listas;

import nodos.NodoSimple;

public class Cola {
    NodoSimple primero;
    NodoSimple ultimo;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void encolar(Object data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public void desencolar() {
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
