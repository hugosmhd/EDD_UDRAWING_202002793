package listas;

import java.io.FileWriter;
import java.io.PrintWriter;

import nodos.NodoSimple;
import objetos.Cliente;

public class ListaClientesAtendidos {
    NodoSimple primero;
    NodoSimple ultimo;

    public ListaClientesAtendidos() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlFinal(Cliente data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while(actual != null){
            Cliente aux = (Cliente) actual.getData();
            System.out.println("--------- CLIENTE --------------");
            // if (actual.getSiguiente()!=null) {
            System.out.println("ID: " + aux.getIdCliente());                
            System.out.println("Nombre: " + aux.getNombre());                
            System.out.println("Ventanilla atencion: " + aux.getVentanillaAtencion());                
            System.out.println("A Color: " + aux.getCantidadColor());                
            System.out.println("A Blanco y Negro: " +aux.getCantidadBW());
            System.out.println("Total imagenes: " +aux.getTotalImagenes());
            System.out.println("Cantidad total de pasos: " + aux.getTotalPasos());
            actual=actual.getSiguiente();
        }
            
    }
    public Cliente buscar(String idCliente){
        NodoSimple actual= this.primero;

        Cliente aux = (Cliente) actual.getData();
        while(actual != null && aux.getIdCliente() != idCliente){
            aux = (Cliente) actual.getData();
            actual=actual.getSiguiente();
        }
        return aux;
            
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
            nombresNodos += "nodo" + actual.hashCode() + "[label=\" Cliente: " +  clienteActual.getNombre() + "\"]" + "\n";
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
    
    public void dibujarGraphviz() {
        try {
            escribirArchivo("clientesatendidos.dot", codigoGraphviz());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tpng","-o","clientesatendidos.png","clientesatendidos.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
