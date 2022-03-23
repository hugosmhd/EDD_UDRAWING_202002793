
package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

import nodos.NodoLD;
import objetos.Album;

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
    
    public void codigoGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("labelloc=\"t\";\n");
        dot.append("label=\"Albumes\";\n");
        dot.append("node[shape=box, color=red];\n");          
        dot.append("fontsize = 40;\n");   
        
        String nombresNodos = "";
        String nodosRank = "";
        String conexiones = "";
        
        int grupo = 1;

        NodoLD actual= this.primero;

        while( actual!= null){
            Album albumActual = (Album) actual.getData();
                nombresNodos += "nodo" + actual.hashCode() + "[label=\"" +  albumActual.getNombre() +
                "\", style = filled, fillcolor = lightskyblue, group = " + grupo + "]" + "\n";
            if (actual.getSiguiente() != null) {
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.getSiguiente().hashCode());
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.getSiguiente().hashCode(), actual.hashCode());
            }
            nodosRank += "nodo" + actual.hashCode() + "; ";

            nombresNodos += albumActual.getImgs().codigoGraphviz(grupo, actual);
            grupo++;            
            actual=actual.getSiguiente();
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("{ rank = same; " + nodosRank + " }\n");

        dot.append("} \n");  
        System.out.println(dot.toString());  
        
        
        escribirArchivo("./reporte.dot", dot.toString());
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}
