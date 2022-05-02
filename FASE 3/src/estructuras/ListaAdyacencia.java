package estructuras;

import nodos.NodoGrafo;
import nodos.NodoVector;
import objetos.Conexion;
import objetos.Lugar;

public class ListaAdyacencia {
    
    NodoVector primero;
    NodoVector ultimo;

    public ListaAdyacencia() {
        this.primero = this.ultimo = null;
    }

    public boolean estaVacia(){
        return this.primero==null;
    }

    public void insertar(Lugar lugar){
        NodoVector nuevo = new NodoVector(lugar);
        if (this.estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public NodoVector buscar(Lugar lugar) {
        NodoVector actual = this.primero;
        while (actual != null && actual.getData().getId() != lugar.getId()) {            
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public void conexion(Lugar inicio, Conexion conex) {
        NodoVector vertice = buscar(inicio);
        NodoGrafo actualGrafo = vertice.getAdyacencia();
        if (actualGrafo == null) {
            vertice.setAdyacencia(new NodoGrafo(conex));
        } else {            
            while (true) {
                if (actualGrafo.getSiguiente() == null) {
                    actualGrafo.setSiguiente(new NodoGrafo(conex));
                    break;
                }
                actualGrafo = actualGrafo.getSiguiente();
            } 
        }
        
    }

    public void imprimir(){

        NodoVector actual= this.primero;

        while( actual != null) {
            // System.out.println("--------- VERTICE --------------");
            System.out.println("ID: " + actual.getData().getId());                
            // System.out.println("DEPARTAMENTO: " + actual.getData().getDepartamento());                
            // System.out.println("NOMBRE: " + actual.getData().getNombre());                
            NodoGrafo actualGrafo = actual.getAdyacencia();
            while(actualGrafo != null) {
                System.out.print(" -> " + actual.getData().getId());    
                actualGrafo = actualGrafo.getSiguiente();
            }                
            actual = actual.getSiguiente();
        }
    }
        

    

}
