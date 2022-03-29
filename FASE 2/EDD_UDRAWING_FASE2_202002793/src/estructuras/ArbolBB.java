
package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import nodos.NodoABB;
import nodos.NodoAVL;
import objetos.Capa;

public class ArbolBB {
    NodoABB raiz;
    int cantidadCapas;
    int contador;
    int profundidad;
    String graphviz = "";
    String nodos = "";
    String relaciones = "";
    String capas = "";
    String recorrido = "";
    public ArbolBB(){
        this.raiz=null;
        this.cantidadCapas = this.contador = this.profundidad = 0;
        
    }
    
    public void insertar(Capa data) {
        raiz = insertar(raiz, data);
    }
    
    private NodoABB insertar(NodoABB raiz, Capa data){
        if (raiz == null) {
            raiz = new NodoABB(data);
            this.cantidadCapas++;
        }
        else if (data.getIdCapa() < raiz.getData().getIdCapa())
            raiz.setIzq(insertar(raiz.getIzq(), data));
        else if(data.getIdCapa() > raiz.getData().getIdCapa())
            raiz.setDer(insertar(raiz.getDer(), data));
        else {
            ;
        }
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
    
    public String preOrden(int n){
        this.contador = 0;
        this.recorrido = "";
        MatrizDispersa matriz = new MatrizDispersa();
        preOrden(this.raiz, n, matriz);
        matriz.generarTabla();
        return this.recorrido;
    }

    private void preOrden(NodoABB raiz, int n, MatrizDispersa matriz){
        if(raiz != null && this.contador < n){
            raiz.getData().getPixeles().recorrerMatriz(matriz);
            // System.out.println(raiz.getData().getIdCapa());
            this.recorrido += raiz.getData().getIdCapa() + " | ";
            this.contador++;
            preOrden(raiz.getIzq(), n, matriz);
            preOrden(raiz.getDer(), n, matriz);
        }
    }
    
    public String inOrden(int n){
        this.contador = 0;
        this.recorrido = "";
        MatrizDispersa matriz = new MatrizDispersa();
        inOrden(this.raiz, n, matriz);
        // System.out.println("MATRIZ DISPERSA UNIFICADA");
        // matriz.imprimir();
        matriz.generarTabla();
        return this.recorrido;
        // matriz.generarGraphvizLogico();
        // inOrden(this.raiz, n);
    }

    private void inOrden(NodoABB raiz, int n, MatrizDispersa matriz){
        if(raiz != null && this.contador <= n){
            inOrden(raiz.getIzq(), n, matriz);
            this.contador++;
            if (this.contador <= n) {
                raiz.getData().getPixeles().recorrerMatriz(matriz);
                this.recorrido += raiz.getData().getIdCapa() + " | ";
            }
            inOrden(raiz.getDer(), n, matriz);
        }
    }
    
    public String postOrden(int n){
        this.contador = 0;
        this.recorrido = "";
        MatrizDispersa matriz = new MatrizDispersa();
        postOrden(this.raiz, n, matriz);
        // System.out.println("MATRIZ DISPERSA UNIFICADA");
        // matriz.imprimir();
        matriz.generarTabla();
        return this.recorrido;
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
                this.recorrido += raiz.getData().getIdCapa() + " | ";
                // System.out.println(raiz.getData().getIdCapa());                
            }
        }
    }

    public String amplitud() {
        this.recorrido = "";
        amplitud(this.raiz);
        return this.recorrido;
    }

    private void amplitud(NodoABB root) {
        if (root == null)
          return;

        Cola q = new Cola();      
        q.encolar(root);      
        q.encolar(null);
        MatrizDispersa matriz = new MatrizDispersa();
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
                curr.getData().getPixeles().recorrerMatriz(matriz);
                this.recorrido += curr.getData().getIdCapa() + " | ";
                // System.out.print(curr.getData().getIdCapa() + " ");
            }
        }

        matriz.generarTabla();
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

    private void escribirArchivo(String ruta, String contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.write(contenido);
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(pw != null) {
                pw.close();
            }            
        }
    }

    public String generarABB(boolean generar, NodoAVL imagen) {
        this.graphviz = this.nodos = this.relaciones = "";
        this.graphviz = "graph \"\" {\n";
        generarABB(this.raiz);
        this.graphviz += this.nodos;
        if (imagen != null) {
            this.relaciones += imagen.hashCode() + " -- " + this.raiz.hashCode() + ";\n";
        }
        this.graphviz += this.relaciones;
        this.graphviz += "\n}";
        // System.out.println(this.graphviz);
        if (generar) {
            
            escribirArchivo("./reporte.dot", this.graphviz);
            try {
                Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png").waitFor();
            } catch (Exception e) {
                //TODO: handle exception
            }
            return null;
        }
        
        return nodos + relaciones;
    }

    private void generarABB(NodoABB raiz) {
        if (raiz != null) {            
            this.nodos += raiz.hashCode() + "[label=\"" + raiz.getData().getIdCapa() + "\"];\n";
            if (raiz.getIzq() != null) {
                this.relaciones += raiz.hashCode() + " -- " + raiz.getIzq().hashCode() + ";\n";
            }
            if (raiz.getDer() != null) {
                this.relaciones += raiz.hashCode() + " -- " + raiz.getDer().hashCode() + ";\n";
            }
            generarABB(raiz.getIzq());
            generarABB(raiz.getDer());
        }
    }
  
    public void capasHojas(DefaultTableModel modelo){
        this.contador = 0;
        capasHojas(this.raiz, modelo);
    }

    private void capasHojas(NodoABB raiz, DefaultTableModel modelo){
        if(raiz != null){
            capasHojas(raiz.getIzq(), modelo);
            if (raiz.getDer() == null && raiz.getIzq() == null) {
                this.contador++;
                Object[] datosPFila =  {this.contador, raiz.getData().getIdCapa(), "Hoja"};
                modelo.addRow(datosPFila);
            }
            capasHojas(raiz.getDer(), modelo);                 
        }
    }

    public void listarCapasPre(DefaultTableModel modelo){
        this.contador = 0;
        this.listarCapasPre(this.raiz, modelo);        
    }

    private void listarCapasPre(NodoABB raiz, DefaultTableModel modelo){
        if(raiz != null){
            this.contador++;
            Object[] datosPFila =  {this.contador, raiz.getData().getIdCapa()};
            modelo.addRow(datosPFila);
            listarCapasPre(raiz.getIzq(), modelo);
            listarCapasPre(raiz.getDer(), modelo);
        }
    }

    public void listarCapasIn(DefaultTableModel modelo){
        this.contador = 0;
        this.listarCapasIn(this.raiz, modelo);        
    }

    private void listarCapasIn(NodoABB raiz, DefaultTableModel modelo){
        if(raiz != null){
            listarCapasIn(raiz.getIzq(), modelo);
            this.contador++;
            Object[] datosPFila =  {this.contador, raiz.getData().getIdCapa()};
            modelo.addRow(datosPFila);
            listarCapasIn(raiz.getDer(), modelo);
        }
    }

    public void listarCapasPost(DefaultTableModel modelo){
        this.contador = 0;
        this.listarCapasPost(this.raiz, modelo);        
    }

    private void listarCapasPost(NodoABB raiz, DefaultTableModel modelo){
        if(raiz != null){
            listarCapasPost(raiz.getIzq(), modelo);
            listarCapasPost(raiz.getDer(), modelo);
            this.contador++;
            Object[] datosPFila =  {this.contador, raiz.getData().getIdCapa()};
            modelo.addRow(datosPFila);
        }
    }

    public void listarCapasAmpli(DefaultTableModel modelo, JLabel label) {
        this.contador = 0;
        listarCapasAmpli(this.raiz, modelo, label);
    }

    private void listarCapasAmpli(NodoABB root, DefaultTableModel modelo, JLabel label) {
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
                }
                this.profundidad++;
                this.contador++;
            } else {
                if (curr.getIzq() != null)
                    q.encolar(curr.getIzq());        
                if (curr.getDer() != null)
                    q.encolar(curr.getDer());

                
                Object[] datosPFila =  {this.contador + 1 , curr.getData().getIdCapa()};
                modelo.addRow(datosPFila);
            }
        }
        label.setText("La profundidad del arbol es de " + this.profundidad);
    }
    
    public void listarCapasAmpliDos(DefaultTableModel modelo, JLabel label) {
        this.contador = 0;
        listarCapasAmpliDos(this.raiz, modelo);
        label.setText("La cantidad de capas es " + (this.contador));
    }

    private void listarCapasAmpliDos(NodoABB root, DefaultTableModel modelo) {
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
                this.profundidad++;
                this.contador++;
            } else {
                if (curr.getIzq() != null)
                    q.encolar(curr.getIzq());        
                if (curr.getDer() != null)
                    q.encolar(curr.getDer());

                
                Object[] datosPFila =  {this.contador + 1 , curr.getData().getIdCapa()};
                modelo.addRow(datosPFila);
            }
        }
        
    }

    public String devolverCapas(){
        this.capas = "";
        this.devolverCapas(this.raiz);
        return this.capas;
    }

    private void devolverCapas(NodoABB raiz){
        if(raiz != null){
            this.capas += raiz.getData().getIdCapa() + ", ";
            // System.out.println(raiz.getData().getIdCapa());
            devolverCapas(raiz.getIzq());
            devolverCapas(raiz.getDer());
        }
    }
    
    
}
