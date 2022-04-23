package estructuras;

import nodos.NodoABB;
import nodos.NodoSimple;
import nodos.Pagina;

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

    public Pagina desencolarB() {
        NodoSimple actual = this.primero.getSiguiente();
        Pagina auxiliar = (Pagina)this.primero.getData();
        this.primero.setSiguiente(null);
        this.primero = actual;
        return auxiliar;
    }


   

}
