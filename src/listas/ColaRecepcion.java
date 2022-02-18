package listas;

import java.io.FileWriter;
import java.io.PrintWriter;

import nodos.NodoSimple;
import objetos.Cliente;

public class ColaRecepcion {
    NodoSimple primero;
    NodoSimple ultimo;

    public ColaRecepcion() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void encolar(Cliente data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public Cliente desencolar() {
        NodoSimple actual = this.primero.getSiguiente();
        Cliente auxiliar = (Cliente)this.primero.getData();
        this.primero.setSiguiente(null);
        this.primero = actual;
        return auxiliar;
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            Cliente aux = (Cliente) actual.getData();
            System.out.println("--------- CLIENTE --------------");
            // if (actual.getSiguiente()!=null) {
            System.out.println("ID: " + aux.getIdCliente());                
            System.out.println("Nombre: " + aux.getNombre());                
            System.out.println("A Color: " + aux.getCantidadColor());                
            System.out.println("A Blanco y Negro: " +aux.getCantidadBW()); 
            actual=actual.getSiguiente();
        }
            
    }

    public String codigoGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("node[shape=box, color=red];\n");
        
        String nombresNodos = "";
        String conexiones = "";
        NodoSimple actual= this.primero;
        while( actual!= null){
            Cliente clienteActual = (Cliente) actual.getData();
            nombresNodos += "nodo" + actual.hashCode() + "[label=\" ID:" +  clienteActual.getIdCliente()
            + "\\n" + clienteActual.getNombre() + "\\n" + "COLOR: " + clienteActual.getCantidadColor() +  
            "\\n" + "BW: " + clienteActual.getCantidadBW() +  "\"]" + "\n";
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
    
    public void aumentarPasoClientes() {
        NodoSimple actual= this.primero;
        while( actual!= null){
            Cliente clienteActual = (Cliente) actual.getData();
            clienteActual.setTotalPasos();
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
    
    public void dibujarGraphviz() {
        try {
            escribirArchivo("colarecepcion.dot", codigoGraphviz());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tpng","-o","colarecepcion.png","colarecepcion.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
