package nodos;

public class NodoSimple {
    Object data;    
    NodoSimple siguiente;

    public NodoSimple(Object data) {
        this.data = data;
        this.siguiente = null;
    }

    public void setSiguiente(NodoSimple siguiente){
        this.siguiente=siguiente;
    }

    public NodoSimple getSiguiente() {
        return this.siguiente;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    

}
