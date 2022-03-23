
package estructuras;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import nodos.NodoMD;

public class MatrizDispersa {

    NodoMD raiz;

    public MatrizDispersa() {
        raiz = new NodoMD("raiz", -1, -1);
    }

    public NodoMD insertEnFila(NodoMD nuevoNodo, NodoMD encabezadoFila) {
        NodoMD actual = encabezadoFila;
        Boolean isMayor = false;
        while (actual != null) {
            if (actual.getColumna() > nuevoNodo.getColumna()) {
                isMayor = true;
                break;
            } else if (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            } else {
                break;
            }
        }

        if (isMayor == true) {
            nuevoNodo.setSiguiente(actual);
            nuevoNodo.setAnterior(actual.getAnterior());
            actual.getAnterior().setSiguiente(nuevoNodo);
            actual.setAnterior(nuevoNodo);
        } else {
            actual.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(actual);
        }
        return nuevoNodo;
    }

    public NodoMD insertEnColumna(NodoMD nuevoNodo, NodoMD encabezadoColumna) {
        NodoMD actual = encabezadoColumna;
        Boolean isMayor = false;

        while (actual != null) {
            if (actual.getFila() > nuevoNodo.getFila()) {
                isMayor = true;
                break;
            } else if (actual.getInferior() != null) {
                actual = actual.getInferior();
            } else {
                break;
            }
        }

        if (isMayor == true) {
            nuevoNodo.setInferior(actual);
            nuevoNodo.setSuperior(actual.getSuperior());
            actual.getSuperior().setInferior(nuevoNodo);
            actual.setSuperior(nuevoNodo);
        } else {
            actual.setInferior(nuevoNodo);
            nuevoNodo.setSuperior(actual);
        }
        return nuevoNodo;
    }

    public NodoMD encontrarColumna(int columna) {
        NodoMD actual = raiz;

        while (actual != null) {
            if (actual.getColumna() == columna) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public NodoMD encontrarFila(int fila) {
        NodoMD actual = raiz;

        while (actual != null) {
            if (actual.getFila() == fila) {
                return actual;
            }
            actual = actual.getInferior();
        }
        return null;
    }

    public Object buscarRepetidos(int fila, int columna) {
        NodoMD actual = raiz;
        while (actual != null) {
            if (actual.getFila() == fila) {
                NodoMD actual2=actual.getSiguiente();
                while (actual2 != null) {
                    if (actual2.getColumna() == columna) {
                        return actual.getData();
                    }
                    actual2 = actual2.getSiguiente();
                }
                break;
            }
            actual = actual.getInferior();
        }
        return null;
    }
    
    public NodoMD crearColumna(int columna){
        return insertEnFila(new NodoMD("Col", columna, -1), raiz);
    }
    
    public NodoMD crearFila(int fila){
        return insertEnColumna(new NodoMD("Row", -1, fila), raiz);
    }
    
    public void insertNodo(Object data, int col, int fil){
        Object repetido = buscarRepetidos(fil, col);
        if (repetido == null) {            
            NodoMD nuevo= new NodoMD(data, col, fil);
            
            NodoMD columna= encontrarColumna(col);
            NodoMD fila= encontrarFila(fil);
            
            
            if (columna == null && fila == null){
                columna=crearColumna(col);
                fila=crearFila(fil);
                
                nuevo= insertEnFila(nuevo, fila);
                nuevo=insertEnColumna(nuevo, columna);
            }else if(columna != null && fila ==null){
                fila=crearFila(fil);
                nuevo=insertEnFila(nuevo, fila);
                nuevo=insertEnColumna(nuevo, columna);
            }else if(columna==null && fila!=null){
                columna=crearColumna(col);
                nuevo=insertEnFila(nuevo, fila);
                nuevo=insertEnColumna(nuevo, columna);
            }else{
                nuevo=insertEnFila(nuevo, fila);
                nuevo=insertEnColumna(nuevo, columna);
            }
        }
    }
    
    public void imprimir(){
        NodoMD actual=raiz;
        while(actual !=null){
            System.out.print("["+actual.getData() +"  "+ actual.getColumna() +"  "+actual.getFila()+"]");            
            NodoMD actual2=actual.getSiguiente();
            while(actual2!=null){                
                System.out.print("["+actual2.getData() +"  "+actual2.getColumna() +"  "+actual2.getFila()+"]");                
                actual2=actual2.getSiguiente();
            }
            System.out.println("");
            actual=actual.getInferior();
        }
    }

    public void generarGraphvizLogico(){
        String graphvizNodos = "digraph G {\n" +
            "node[shape=box, width=1, height=1];\n" +
            "edge[dir = \"both\"];\n";
        String nodos = "";
        String columnas = "";
        String relacionesFilas = "";
        String relacionesFilasH = "";
        String rankDir = "";
        NodoMD actual=this.raiz;
        while(actual !=null){
            if (actual.getFila() == -1 && actual.getColumna() == -1)
                columnas += "nodo" + actual.hashCode()+"[label =RAIZ" + ", group = " + (actual.getColumna()) + " ];\n";                
            else {
                nodos += "nodo" + actual.hashCode()+"[label =fila" + actual.getFila() + ", group = " + (actual.getColumna()) + " ];\n";
                relacionesFilasH += "nodo" + actual.getSuperior().hashCode() + " -> nodo" + actual.hashCode() + ";\n";  
            }
                    
            /*if (actual.getInferior() != null) {
                relaciones += actual.hashCode() + " ->" + actual.getInferior().hashCode() + ";\n";
            }*/
            NodoMD actual2=actual.getSiguiente();
            while(actual2!=null){ 
                if (actual2.getFila() == -1) {
                    columnas += "nodo" + actual2.hashCode()+"[label =Columna" + actual2.getColumna() + ", style=filled, fillcolor=\"white\" ,  group = " + (actual2.getColumna()) + " ];\n";
                    // relaciones += actual2.hashCode() + " ->" + actual2.getAnterior().hashCode() + ";\n";
                    
                } else {
                    nodos += "nodo" + actual2.hashCode() + "[label=\"\", style = filled, fillcolor = \"" + actual2.getData() + "\", group = " + (actual2.getColumna()) + " ];\n";
                    relacionesFilas += "nodo" + actual2.getSuperior().hashCode() + " -> nodo" + actual2.hashCode() + ";\n";  
                }
                rankDir += "{rank=same nodo" + actual2.getAnterior().hashCode() + "-> nodo" + actual2.hashCode() + "; }\n";
                actual2=actual2.getSiguiente();
            }
            actual=actual.getInferior();
        }
        // System.out.println(columnas);
        // System.out.println(nodos);
        // System.out.println(rankDir);
        // System.out.println(nodos);
        // System.out.println(relaciones);
        // System.out.println(rankDir);
        graphvizNodos += columnas;
        graphvizNodos += nodos;
        graphvizNodos += rankDir;
        graphvizNodos += relacionesFilasH;
        graphvizNodos += relacionesFilas;
        // graphvizNodos += nodos;
        // graphvizNodos += relaciones;
        // graphvizNodos += rankDir;
        graphvizNodos += "}";
        // System.out.println(graphvizNodos);
        escribirArchivo("reporte.txt", graphvizNodos);
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".txt -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            System.out.println("Error " + e);
            //TODO: handle exception
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

    public void generarTabla() {
        String graphviz = "digraph G {\n" +
            "node [shape=plaintext];\n" +
            "some_node [\n" +
            "label=<\n" +
            "<table border=\"0\" cellborder=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">\n";
        
        int totalCol = 0;
        
        NodoMD actual=raiz;
        while(actual !=null){
            int colActual = 0;
            String tRow = "<tr>\n";
            String tData = "";
            NodoMD actual2=actual.getSiguiente();
            while(actual2!=null){
                if (actual2.getFila() == -1) {
                    totalCol++;
                    actual2=actual2.getSiguiente();                  
                } else {                    
                    while (colActual < actual2.getColumna()) {
                        tData += "<td bgcolor=\"white\" width=\"1\" height=\"1\"></td>\n";     
                        colActual++;        
                    }
                    tData += "<td bgcolor=\"" + actual2.getData() + "\" width=\"1\" height=\"1\"></td>\n";                   
                    actual2=actual2.getSiguiente();
                    colActual++;                    
                }                
            }
            while (colActual < totalCol && actual.getFila() != -1) {
                tData += "<td bgcolor=\"white\" width=\"1\" height=\"1\"></td>\n";     
                colActual++;        
            }
            tRow += tData;
            tRow += "</tr>\n";
            if(actual.getFila() != -1) {
                graphviz += tRow;
            }
            actual=actual.getInferior();
        }
        graphviz += "</table>>\n" +
            "];\n" +
            "}";
        // System.out.println(graphviz);
        escribirArchivo("./prueba.dot", graphviz);

        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./prueba" + ".dot -o " + "prueba" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    public void recorrerMatriz(MatrizDispersa matriz){
        NodoMD actual=raiz.getInferior();
        while(actual !=null){
            NodoMD actual2=actual.getSiguiente();
            while(actual2!=null){                
                // System.out.print("["+actual2.getData() +"  "+actual2.getColumna() +"  "+actual2.getFila()+"]");
                matriz.insertNodo(actual2.getData(), actual2.getColumna(), actual2.getFila());                
                actual2=actual2.getSiguiente();
            }
            // System.out.println("");
            actual=actual.getInferior();
        }
    }
    
    
    
}


