package listas;

import nodos.NodoDobleCircular;
import objetos.Cliente;

public class ListaCircularEspera {
    NodoDobleCircular primero;
    NodoDobleCircular ultimo;

    public ListaCircularEspera() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia(){
        return primero==null;
    }

    public void insertar(Cliente cliente){
        NodoDobleCircular nuevo=new NodoDobleCircular(cliente);
        if (this.estaVacia()){
            nuevo.setAnterior(nuevo);
            nuevo.setSiguiente(nuevo);
            this.primero=nuevo;            
            this.ultimo=nuevo;            
        }
        else{
            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
            this.ultimo.setSiguiente(this.primero);
            this.primero.setAnterior(this.ultimo);
            

        }
    }

    public void imprimir(){
        NodoDobleCircular actual= this.primero;

        if(actual != null) {
            do {
                // System.out.println("ID: " + actual.getData()); 
                Cliente aux = (Cliente) actual.getData();
                System.out.println("--------- CLIENTE EN ESPERA --------------");
                System.out.println("ID: " + aux.getIdCliente());                
                System.out.println("Nombre: " + aux.getNombre());                
                System.out.println("A Color: " + aux.getCantidadColor());                
                System.out.println("A Blanco y Negro: " + aux.getCantidadBW());
                
                actual=actual.getSiguiente();
            } while( actual != this.primero);
        }
            
    }
}
