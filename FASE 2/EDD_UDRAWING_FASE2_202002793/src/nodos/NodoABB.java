
package nodos;

public class NodoABB<E> {
    E data;
    NodoABB<E> izq;
    NodoABB<E> der;
    
    public NodoABB(E data) {
        this.data = data;
        this.izq = null;
        this.der = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public NodoABB<E> getIzq() {
        return izq;
    }

    public void setIzq(NodoABB<E> izq) {
        this.izq = izq;
    }

    public NodoABB<E> getDer() {
        return der;
    }

    public void setDer(NodoABB<E> der) {
        this.der = der;
    }
    
    
    
    
}
