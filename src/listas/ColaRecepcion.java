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

    public Cliente desencolar() {
        NodoSimple actual = this.primero.getSiguiente();
        Cliente auxiliar = (Cliente)this.primero.getData();
        this.primero.setSiguiente(null);
        this.primero = actual;
        return auxiliar;
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            Cliente aux = (Cliente) actual.getData();
            System.out.println("--------- CLIENTE --------------");
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
