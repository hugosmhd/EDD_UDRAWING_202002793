
package nodos;


public class NodoMD {
    NodoMD superior, inferior, anterior, siguiente;
    Object data;
    int columna;
    int fila;
    public NodoMD (Object data, int columna, int fila){
        this.superior = this.inferior = this.anterior = this.siguiente = null;
        this.data = data;
        this.columna = columna;
        this.fila = fila;
    }
    public NodoMD getSuperior() {
        return superior;
    }
    public void setSuperior(NodoMD superior) {
        this.superior = superior;
    }
    public NodoMD getInferior() {
        return inferior;
    }
    public void setInferior(NodoMD inferior) {
        this.inferior = inferior;
    }
    public NodoMD getAnterior() {
        return anterior;
    }
    public void setAnterior(NodoMD anterior) {
        this.anterior = anterior;
    }
    public NodoMD getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoMD siguiente) {
        this.siguiente = siguiente;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public int getFila() {
        return fila;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }

    
}