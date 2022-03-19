
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

    private void preOrden(NodoABB<E> raiz){
        if(raiz != null){
            System.out.println(raiz.getData());
            preOrden(raiz.getIzq());
            preOrden(raiz.getDer());
        }
    }
    
    public void inOrden(){
        inOrden(this.raiz);
    }

    private void inOrden(NodoABB<E> raiz){
        if(raiz != null){
            inOrden(raiz.getIzq());
            System.out.println(raiz.getData());
            inOrden(raiz.getDer());
        }
    }
    
    public void postOrden(){
        postOrden(this.raiz);
    }

    private void postOrden(NodoABB<E> raiz){
        if(raiz != null){
            postOrden(raiz.getIzq());
            postOrden(raiz.getDer());
            System.out.println(raiz.getData());
        }
    }
}
