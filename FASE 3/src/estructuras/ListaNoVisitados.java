package estructuras;

import nodos.NodoSimple;
import objetos.Lugar;

public class ListaNoVisitados {
    NodoSimple primero;
    NodoSimple ultimo;

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlFinal(Lugar data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public Boolean noVisitadosVacio() {
        if (this.primero == null) {
            return true;
        }
        return false;
    }

    public void eliminarLugar(int idLugar) {
        NodoSimple actual= this.primero;
        boolean encontrado = false;
        NodoSimple anterior = null;

        while(actual!= null && !encontrado){            
            Lugar lugarActual = (Lugar) actual.getData();            
            if (lugarActual.getId() == idLugar)  {
                encontrado = true;
                break;
            } else
                anterior = actual;
            actual=actual.getSiguiente();
        }

        if (anterior == null && encontrado == true) {
            this.primero = actual.getSiguiente();
            actual.setSiguiente(null); 
        } else if(encontrado == true) {
            anterior.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(null);            
        }
    }

    public Lugar devolverPrimero() {
        if (this.primero != null) {
            Lugar primerLugar = (Lugar) this.primero.getData();
            return primerLugar;            
        }
        return null;        
    }

    public NodoSimple devolverPrimerNodo() {
        if (this.primero != null) {
            // Lugar primerLugar = (Lugar) this.primero.getData();
            return this.primero;            
        }
        return null;        
    }
}
