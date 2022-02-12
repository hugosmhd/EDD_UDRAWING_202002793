package nodos;

public class NodoDobleCircular {
    Object data;
    NodoDobleCircular siguiente;
    NodoDobleCircular anterior;

    public NodoDobleCircular(Object data){
        this.data=data;
        siguiente=anterior=null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NodoDobleCircular getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDobleCircular siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDobleCircular getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDobleCircular anterior) {
        this.anterior = anterior;
    }

    

}
