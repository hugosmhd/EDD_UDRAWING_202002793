package listas;

import nodos.NodoSimple;
import objetos.Imagen;

public class ColaImpresion {
    NodoSimple primero;
    NodoSimple ultimo;

    public ColaImpresion() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void encolar(Imagen data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public Imagen desencolar() {
        NodoSimple actual = this.primero.getSiguiente();
        Imagen auxiliar = (Imagen)this.primero.getData();
        this.primero.setSiguiente(null);
        this.primero = actual;
        return auxiliar;
    }

    public void imprimir() {
        if(this.primero != null) {
            Imagen actual = (Imagen) this.primero.getData();
            int pasosActuales = actual.getPasos();
            actual.setPasos(pasosActuales + 1);
            if(actual.isColor() && actual.getPasos() == 2) {
                Imagen imagenImpresa = this.desencolar();
            } else if (!actual.isColor() && actual.getPasos() == 1) {
                Imagen imagenImpresa = this.desencolar();
            }
        }
        
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            Imagen aux = (Imagen) actual.getData();
            System.out.println("--------- IMAGEN --------------");
            System.out.println("ID Cliente: " + aux.getIdCliente());                
            System.out.println("A color: " + aux.isColor());
            System.out.println("Pasos: " + aux.getPasos());
            actual=actual.getSiguiente();
        }
            
    }
}
