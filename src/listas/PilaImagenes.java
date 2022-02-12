package listas;

import nodos.NodoSimple;
import objetos.Imagen;

public class PilaImagenes {
    NodoSimple primero;
    NodoSimple ultimo;

    public PilaImagenes() {
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

        while( actual!= null) {
            System.out.println("-+-+-+-+-+-*/*/*/*/*/*");
            Imagen imagen = (Imagen)actual.getData();
            System.out.println(imagen.getIdCliente());
            System.out.println(imagen.isColor());
            actual=actual.getSiguiente();
        }
            
    }

    public void encolarImpresion(ColaImpresion colaColor, ColaImpresion colaBW) {
        NodoSimple actual = this.primero;
        while( actual!= null) {
            Imagen imagenEncolar = (Imagen) actual.getData();
            if (imagenEncolar.isColor()) {
                colaColor.encolar(imagenEncolar);
            } else {
                colaBW.encolar(imagenEncolar);
            }
            actual=actual.getSiguiente();
            this.desapilar();
        }
    }

}
