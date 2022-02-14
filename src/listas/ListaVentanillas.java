package listas;

import java.io.FileWriter;
import java.io.PrintWriter;


import nodos.NodoSimple;
import objetos.Cliente;
import objetos.Imagen;
import objetos.Ventanilla;

public class ListaVentanillas {
    NodoSimple primero;
    NodoSimple ultimo;

    public ListaVentanillas() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlFinal(int cantidadVentanillas) {
        for(int i=0; i < cantidadVentanillas; i++) {
            Ventanilla ventanilla = new Ventanilla(i, true);
            NodoSimple nuevo = new NodoSimple(ventanilla);
            if (estaVacia()) {
                this.primero = nuevo;
                this.ultimo = nuevo;
            } else {
                this.ultimo.setSiguiente(nuevo);
                this.ultimo = nuevo;
            }
        }
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            Ventanilla aux = (Ventanilla) actual.getData();
            System.out.println("--------- VENTANILLA --------------");
            System.out.println("ID: " + aux.getIdVentanilla());                
            System.out.println("Disponible: " + aux.isDisponible());
            if(aux.getCliente() != null) {
                Cliente clienteAtendiendo = aux.getCliente();
                System.out.println("------- CLIENTE ANTENDIENDO -------");
                System.out.println("ID: " + clienteAtendiendo.getIdCliente());                
                System.out.println("Nombre: " + clienteAtendiendo.getNombre());                
                System.out.println("A Color: " + clienteAtendiendo.getCantidadColor());                
                System.out.println("A Blanco y Negro: " +clienteAtendiendo.getCantidadBW());   

            }
            if(aux.getImagenesCliente() != null) {
                aux.getImagenesCliente().visualizar();
            }
            actual=actual.getSiguiente();
        }
            
    }

    public Ventanilla disponible(){
        NodoSimple actual= this.primero;

        while( actual != null){
            Ventanilla aux = (Ventanilla) actual.getData();
            if(aux.isDisponible()) {
                return aux;
            }
            actual=actual.getSiguiente();
        }
        return null;
    }
    
    public void atenderCliente(Cliente cliente) {
        NodoSimple actual= this.primero;

        while( actual != null){
            Ventanilla aux = (Ventanilla) actual.getData();
            if(aux.isDisponible()) {
                aux.setCliente(cliente);
                aux.setDisponible(false);
                System.out.println("Cliente atendido en ventanilla " + aux.getIdVentanilla());
                break;
            }
            actual=actual.getSiguiente();
        }

      

    }

    public void entregarImagenes(ListaCircularEspera listaClientesEspera, ColaImpresion colaColor, 
    ColaImpresion colaBW, ListaClientesAtendidos listaClientesAtendidos) {
        NodoSimple actual= this.primero;

        while( actual != null){
            Ventanilla aux = (Ventanilla) actual.getData();
            if(!aux.isDisponible()) {
                Cliente clienteAtendiendo = aux.getCliente();
                clienteAtendiendo.setTotalPasos();
                if(clienteAtendiendo.getCantidadColor() > 0) {
                    int nuevaCantidadColor = clienteAtendiendo.getCantidadColor() - 1;
                    clienteAtendiendo.setCantidadColor(nuevaCantidadColor);
                    Imagen nuevaImagen = new Imagen(clienteAtendiendo.getIdCliente(), true);
                    aux.getImagenesCliente().apilar(nuevaImagen);
                } else if(clienteAtendiendo.getCantidadBW() > 0) {
                    int nuevaCantidadBW = clienteAtendiendo.getCantidadBW() - 1;
                    clienteAtendiendo.setCantidadBW(nuevaCantidadBW);
                    Imagen nuevaImagen = new Imagen(clienteAtendiendo.getIdCliente(), false);
                    aux.getImagenesCliente().apilar(nuevaImagen);
                } else {
                    aux.setDisponible(true);
                    clienteAtendiendo.setVentanillaAtencion("Ventanilla: " + aux.getIdVentanilla());
                    listaClientesEspera.insertar(aux.getCliente());
                    listaClientesAtendidos.insertarAlFinal(clienteAtendiendo);
                    aux.getImagenesCliente().encolarImpresion(colaColor, colaBW);
                    aux.setCliente(null);
                }
                
            }
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
            Ventanilla vtnActual = (Ventanilla) actual.getData();
            nombresNodos += "nodo" + actual.hashCode() + "[label=\" Ventanilla: " +  vtnActual.getIdVentanilla() + "\"]" + "\n";
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
            escribirArchivo("ventanillas.dot", codigoGraphviz());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tpng","-o","ventanillas.png","ventanillas.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////
    public String codigoGraphvizPila() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("node[shape=box, color=red];\n");
                
        NodoSimple actual= this.primero;
        while( actual!= null){
            Ventanilla vtnActual = (Ventanilla) actual.getData();
            String subgraph = "";
            String conexiones = "";
            subgraph += "subgraph cluster_" + vtnActual.getIdVentanilla() + "{ \n";
            subgraph += "label = \" Ventanilla: " + vtnActual.getIdVentanilla()  +  "\";\n";
            conexiones += vtnActual.getImagenesCliente().codigoGraphviz();
            // if (actual.getSiguiente() != null) {         
            // }
            subgraph += conexiones;
            subgraph += "}";
            dot.append(subgraph);    

            actual=actual.getSiguiente();
        }
        
        // dot.append("rankdir=LR;\n");
        dot.append("} \n");    
        
        return dot.toString();
    }
    
    public void dibujarGraphvizPila() {
        try {
            escribirArchivo("pilaimg.dot", codigoGraphvizPila());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tpng","-o","pilaimg.png","pilaimg.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
