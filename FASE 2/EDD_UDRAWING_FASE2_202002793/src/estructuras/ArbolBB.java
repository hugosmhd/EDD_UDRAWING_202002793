
package estructuras;

import nodos.NodoABB;
import objetos.Capa;

public class ArbolBB {
    NodoABB raiz;
    
    public ArbolBB(){
        this.raiz=null;
    }
    
    public void insertar(Capa data) {
        raiz = insertar(raiz, data);
    }
    
    private NodoABB insertar(NodoABB raiz, Capa data){
        // Capa capaNueva = (Capa) data;
        // Capa raizData = (Capa) raiz.getData();
        if (raiz == null)
            raiz = new NodoABB(data);
        else if (data.getIdCapa() < raiz.getData().getIdCapa())
            raiz.setIzq(insertar(raiz.getIzq(), data));
        else if(data.getIdCapa() > raiz.getData().getIdCapa())
            raiz.setDer(insertar(raiz.getDer(), data));
        return raiz;
    }

    public Capa buscar(int dato){
        return buscar(this.raiz,dato);
    }

    private Capa buscar(NodoABB raiz, int dato){
        if (raiz ==  null) return null;
        else if (dato == raiz.getData().getIdCapa()) return raiz.getData();
        else if(dato < raiz.getData().getIdCapa()) return buscar(raiz.getIzq(), dato);
        else return buscar(raiz.getDer(), dato);

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
    
    public void inOrden(){
        inOrden(this.raiz);
    }

    private void inOrden(NodoABB raiz){
        if(raiz != null){
            inOrden(raiz.getIzq());
            System.out.println(raiz.getData().getIdCapa());
            // raiz.getData().getPixeles().imprimir();
            inOrden(raiz.getDer());
        }
    }
    
    public void postOrden(){
        postOrden(this.raiz);
    }

    private void postOrden(NodoABB raiz){
        if(raiz != null){
            postOrden(raiz.getIzq());
            postOrden(raiz.getDer());
            System.out.println(raiz.getData());
        }
    }

    public void agregarAMatrizDispersa() {
        MatrizDispersa matriz = new MatrizDispersa();
        agregarAMatrizDispersa(this.raiz, matriz);
        System.out.println("MATRIZ DISPERSA UNIFICADA");
        matriz.imprimir();
    }

    private void agregarAMatrizDispersa(NodoABB raiz, MatrizDispersa matriz) {
        if(raiz != null){
            agregarAMatrizDispersa(raiz.getIzq(), matriz);
            System.out.println(raiz.getData().getIdCapa());
            raiz.getData().getPixeles().recorrerMatriz(matriz);
            agregarAMatrizDispersa(raiz.getDer(), matriz);
        }
    }
    
}
