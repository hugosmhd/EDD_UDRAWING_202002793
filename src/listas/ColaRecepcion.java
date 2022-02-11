package listas;

import nodos.NodoSimple;
import objetos.Cliente;

public class ColaRecepcion {
    NodoSimple primero;
    NodoSimple ultimo;

    public ColaRecepcion() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void encolar(Cliente data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public void encolarMasiva(Cliente data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setSiguiente(this.primero);
            this.primero = nuevo;
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
            Cliente aux = (Cliente) actual.getData();
            System.out.println("--------- ESTAMOS --------------");
            // if (actual.getSiguiente()!=null) {
            System.out.println("ID: " + aux.getIdCliente());                
            System.out.println("Nombre: " + aux.getNombre());                
            System.out.println("A Color: " + aux.getCantidadColor());                
            System.out.println("A Blanco y Negro: " +aux.getCantidadBW());                
            // } else {
                // System.out.println(aux.getIdCliente());                
                // System.out.println(aux.getNombre());                
                // System.out.println(aux.getCantidadColor());                
                // System.out.println(aux.getCantidadBW()); 
            // } 
            actual=actual.getSiguiente();
        }
            
    }
}
