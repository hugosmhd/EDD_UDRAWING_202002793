package estructuras;

import nodos.NodoABB;
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

    public NodoABB desencolar() {
        NodoSimple actual = this.primero.getSiguiente();
        NodoABB auxiliar = (NodoABB)this.primero.getData();
        this.primero.setSiguiente(null);
        this.primero = actual;
        return auxiliar;
    }

    /*public Imagen imprimir() {
        if(this.primero != null) {
            Imagen actual = (Imagen) this.primero.getData();
            int pasosActuales = actual.getPasos();
            actual.setPasos(pasosActuales + 1);
            if(actual.isColor() && actual.getPasos() == 2) {
                Imagen imagenImpresa = this.desencolar();
                System.out.println("HA SIDO IMPRESA UNA IMAGEN A COLOR DEL CLIENTE " + imagenImpresa.getIdCliente());
                return imagenImpresa;
            } else if (!actual.isColor() && actual.getPasos() == 1) {
                Imagen imagenImpresa = this.desencolar();
                System.out.println("HA SIDO IMPRESA UNA IMAGEN A BW DEL CLIENTE " + imagenImpresa.getIdCliente());
                return imagenImpresa;
            }
        }
        return null;
        
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
            
    }*/

   

}
