
package nodos;

public class NodoLD {
    Object data;
    NodoLD anterior;
    NodoLD siguiente;
    
    public NodoLD(Object data) {
        this.data = data;
        anterior=siguiente=null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NodoLD getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoLD anterior) {
        this.anterior = anterior;
    }

    public NodoLD getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLD siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
