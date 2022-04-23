
package nodos;

import objetos.Capa;

public class NodoABB {
    Capa data;
    NodoABB izq;
    NodoABB der;
    
    public NodoABB(Capa data) {
        this.data = data;
        this.izq = null;
        this.der = null;
    }

    public Capa getData() {
        return data;
    }

    public void setData(Capa data) {
        this.data = data;
    }

    public NodoABB getIzq() {
        return izq;
    }

    public void setIzq(NodoABB izq) {
        this.izq = izq;
    }

    public NodoABB getDer() {
        return der;
    }

    public void setDer(NodoABB der) {
        this.der = der;
    }
    
    
    
    
}
