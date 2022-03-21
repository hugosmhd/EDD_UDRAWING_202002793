
package estructuras;

import nodos.NodoABB;
import objetos.Capa;

public class ArbolBB {
    NodoABB raiz;
    int contador;
    
    public ArbolBB(){
        this.raiz=null;
        contador = 0;
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
    
    public void preOrden(int n){
        this.contador = 0;
        MatrizDispersa matriz = new MatrizDispersa();
        preOrden(this.raiz, n, matriz);
        // System.out.println("MATRIZ DISPERSA UNIFICADA");
        // matriz.imprimir();
        matriz.generarTabla();
        // matriz.generarGraphvizLogico();
        // preOrden(this.raiz, n);
    }

    private void preOrden(NodoABB raiz, int n, MatrizDispersa matriz){
        if(raiz != null && this.contador < n){
            raiz.getData().getPixeles().recorrerMatriz(matriz);
            // System.out.println(raiz.getData().getIdCapa());
            this.contador++;
            preOrden(raiz.getIzq(), n, matriz);
            preOrden(raiz.getDer(), n, matriz);
        }
    }
    
    public void inOrden(int n){
        this.contador = 0;
        MatrizDispersa matriz = new MatrizDispersa();
        inOrden(this.raiz, n, matriz);
        // System.out.println("MATRIZ DISPERSA UNIFICADA");
        // matriz.imprimir();
        matriz.generarTabla();
        // matriz.generarGraphvizLogico();
        // inOrden(this.raiz, n);
    }

    private void inOrden(NodoABB raiz, int n, MatrizDispersa matriz){
        if(raiz != null && this.contador <= n){
            inOrden(raiz.getIzq(), n, matriz);
            this.contador++;
            if (this.contador <= n) {
                raiz.getData().getPixeles().recorrerMatriz(matriz);
            }
            inOrden(raiz.getDer(), n, matriz);
        }
    }
    
    public void postOrden(int n){
        this.contador = 0;
        MatrizDispersa matriz = new MatrizDispersa();
        postOrden(this.raiz, n, matriz);
        // System.out.println("MATRIZ DISPERSA UNIFICADA");
        // matriz.imprimir();
        matriz.generarTabla();
        // matriz.generarGraphvizLogico();
        // postOrden(this.raiz, n);
    }

    private void postOrden(NodoABB raiz, int n, MatrizDispersa matriz){
        if(raiz != null && this.contador < n){
            postOrden(raiz.getIzq(), n, matriz);
            postOrden(raiz.getDer(), n, matriz);
            this.contador++;
            if (this.contador <= n) {
                raiz.getData().getPixeles().recorrerMatriz(matriz);
                // System.out.println(raiz.getData().getIdCapa());                
            }
        }
    }

    public void amplitud() {
        amplitud(this.raiz);
    }

    private void amplitud(NodoABB root) {
        if (root == null)
          return;

        Cola q = new Cola();      
        q.encolar(root);      
        q.encolar(null);
      
        while (!q.estaVacia()) {      
            NodoABB curr = q.desencolar();    
            if (curr == null) {
                if (!q.estaVacia()) {
                    q.encolar(null);
                // System.out.println();
                }
            } else {
                if (curr.getIzq() != null)
                    q.encolar(curr.getIzq());        
                if (curr.getDer() != null)
                    q.encolar(curr.getDer());
        
                // System.out.print(curr.getData().getIdCapa() + " ");
            }
        }
      }

    public void agregarAMatrizDispersa() {
        MatrizDispersa matriz = new MatrizDispersa();
        agregarAMatrizDispersa(this.raiz, matriz);
        // System.out.println("MATRIZ DISPERSA UNIFICADA");
        // matriz.imprimir();
        matriz.generarTabla();
        // matriz.generarGraphvizLogico();
    }

    private void agregarAMatrizDispersa(NodoABB raiz, MatrizDispersa matriz) {
        if(raiz != null){
            agregarAMatrizDispersa(raiz.getIzq(), matriz);
            // System.out.println(raiz.getData().getIdCapa());
            raiz.getData().getPixeles().recorrerMatriz(matriz);
            agregarAMatrizDispersa(raiz.getDer(), matriz);
        }
    }
    
}
