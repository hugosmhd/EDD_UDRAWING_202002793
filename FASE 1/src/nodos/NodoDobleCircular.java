package nodos;

public class NodoDobleCircular {
    Object data;
    NodoDobleCircular siguiente;
    NodoDobleCircular anterior;
    NodoSimple imagenes;

    public NodoDobleCircular(Object data){
        this.data=data;
        siguiente=anterior=null;
        imagenes = null;
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

    public NodoSimple getImagenes() {
        return imagenes;
    }

    public void setImagenes(NodoSimple imagenes) {
        this.imagenes = imagenes;
    }

    

    

}
