
package estructuras;

import nodos.NodoABB;

public class ArbolBB <E extends Comparable<E>>{
    NodoABB<E> raiz;
    
    public ArbolBB(){
        this.raiz=null;
    }
    
    public void insertar(E data) {
        raiz = insertar(raiz, data);
    }
    
    private NodoABB<E> insertar(NodoABB<E> raiz, E data){
        if (raiz == null)
            raiz = new NodoABB<E>(data);
        else if (data.compareTo(raiz.getData())< 0)
            raiz.setIzq(insertar(raiz.getIzq(), data));
        else if(data.compareTo(raiz.getData())> 0)
            raiz.setDer(insertar(raiz.getDer(), data));
        return raiz;
    }
    
    public void preOrden(){
        preOrden(this.raiz);
    }

    private void preOrden(NodoABB raiz){
        if(raiz != null){
            System.out.println(raiz.getData());
            preOrden(raiz.getIzq());
            preOrden(raiz.getDer());
        }
    }
}
