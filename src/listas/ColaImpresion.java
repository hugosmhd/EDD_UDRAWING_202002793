package listas;

import java.io.FileWriter;
import java.io.PrintWriter;

import nodos.NodoSimple;
import objetos.Imagen;

public class ColaImpresion {
    NodoSimple primero;
    NodoSimple ultimo;

    public ColaImpresion() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void encolar(Imagen data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public Imagen desencolar() {
        NodoSimple actual = this.primero.getSiguiente();
        Imagen auxiliar = (Imagen)this.primero.getData();
        this.primero.setSiguiente(null);
        this.primero = actual;
        return auxiliar;
    }

    public Imagen imprimir() {
        if(this.primero != null) {
            Imagen actual = (Imagen) this.primero.getData();
            int pasosActuales = actual.getPasos();
            actual.setPasos(pasosActuales + 1);
            if(actual.isColor() && actual.getPasos() == 2) {
                Imagen imagenImpresa = this.desencolar();
                return imagenImpresa;
            } else if (!actual.isColor() && actual.getPasos() == 1) {
                Imagen imagenImpresa = this.desencolar();
                return imagenImpresa;
            }
        }
        return null;
        
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            Imagen aux = (Imagen) actual.getData();
            System.out.println("--------- IMAGEN --------------");
            System.out.println("ID Cliente: " + aux.getIdCliente());                
            System.out.println("A color: " + aux.isColor());
            System.out.println("Pasos: " + aux.getPasos());
            actual=actual.getSiguiente();
        }
            
    }

    public String idClienteProximo() {
        if(!this.estaVacia()) {
            Imagen img = (Imagen)this.primero.getData();
            return img.getIdCliente();
        }
        return null;
    }

    public String codigoGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("node[shape=box, color=red];\n");
        
        String nombresNodos = "";
        String conexiones = "";
        NodoSimple actual= this.primero;
        while( actual!= null){
            Imagen imagenActual = (Imagen) actual.getData();
            nombresNodos += "nodo" + actual.hashCode() + "[label=\" ID CLIENTE: " +  imagenActual.getIdCliente() + "\"]" + "\n";
            if (actual.getSiguiente() != null)            
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.getSiguiente().hashCode());
            actual=actual.getSiguiente();
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("rankdir=LR;\n");
        dot.append("} \n");    
        
        return dot.toString();
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
    
    public void dibujarGraphviz(String nombre) {
        try {
            escribirArchivo(nombre+".dot", codigoGraphviz());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tpng","-o",nombre+".png",nombre+".dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
