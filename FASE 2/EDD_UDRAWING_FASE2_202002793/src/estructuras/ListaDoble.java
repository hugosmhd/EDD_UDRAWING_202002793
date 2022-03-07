
package estructuras;

import nodos.NodoLD;

public class ListaDoble {
    NodoLD primero;
    NodoLD ultimo;
    
    public ListaDoble() {
        this.primero = null;
        this.ultimo = null;
    }
    
    public boolean estaVacia(){
        return primero==null;
    }
    
    public void insertarF(Object data) {
        NodoLD nuevo=new NodoLD(data);
        if (this.estaVacia()){
            this.primero=nuevo;
            this.ultimo=nuevo;
        }
        else{
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
    }
    
    public void imprimir(){
        NodoLD actual= this.primero;

        while( actual!= null){
            if (actual.getSiguiente()!=null) System.out.print(actual.getData() + ",");
            else System.out.println(actual.getData());
            actual=actual.getSiguiente();
        }
            
    }
    
}
