package listas;

import java.io.FileWriter;
import java.io.PrintWriter;

import nodos.NodoDobleCircular;
import nodos.NodoSimple;
import objetos.Cliente;
import objetos.Imagen;

public class ListaCircularEspera {
    NodoDobleCircular lc;

    public ListaCircularEspera() {
        this.lc = null;
    }

    public boolean estaVacia(){
        return this.lc==null;
    }

    public void insertar(Cliente cliente){
        NodoDobleCircular nuevo = new NodoDobleCircular(cliente);
        System.out.println("EL CLIENTE " + cliente.idCliente + " HA SIDO ATENDIDO E INGRESA A LA LISTA DE ESPERA");
        if (this.estaVacia()) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
        } else {
            NodoDobleCircular primero = this.lc.getSiguiente();  
            nuevo.setSiguiente(primero);  
            nuevo.setAnterior(this.lc); 

            this.lc.setSiguiente(nuevo);
            primero.setAnterior(nuevo);
        }
        this.lc = nuevo;
    }

    public void imprimir(){
        if(this.lc != null) {
            NodoDobleCircular actual= this.lc.getSiguiente();

            do {
                Cliente aux = (Cliente) actual.getData();
                System.out.println("--------- CLIENTE EN ESPERA --------------");
                System.out.println("ID: " + aux.getIdCliente());                
                System.out.println("Nombre: " + aux.getNombre());                
                System.out.println("A Color: " + aux.getCantidadColor());                
                System.out.println("A Blanco y Negro: " + aux.getCantidadBW());
                NodoSimple actualImg = actual.getImagenes();
                while(actualImg != null) {
                    Imagen img = (Imagen) actualImg.getData();
                    System.out.println("--------- IMAGEN ENTREGADA --------------");
                    System.out.println("ID Cliente: " + img.getIdCliente());                
                    System.out.println("A color: " + img.isColor());
                    System.out.println("Pasos: " + img.getPasos());
                    actualImg = actualImg.getSiguiente();
                }                
                actual=actual.getSiguiente();
            } while( actual != this.lc.getSiguiente());
        }
            
    }

    public void insertarImagen(Imagen imgImpresa) {
        NodoDobleCircular actual= this.lc.getSiguiente();

        if(actual != null) {
            do {
                Cliente cliente = (Cliente) actual.getData();
                if(cliente.getIdCliente() == imgImpresa.getIdCliente()) {
                    NodoSimple imgLista = new NodoSimple(imgImpresa);
                    if(actual.getImagenes() == null) {
                        actual.setImagenes(imgLista);
                    } else {
                        NodoSimple actualImagen = actual.getImagenes();
                        imgLista.setSiguiente(actualImagen);
                        actual.setImagenes(imgLista);
                    }
                    if(imgImpresa.isColor()) {
                        cliente.setCantidadColor(cliente.getCantidadColor() + 1);
                    } else {
                        cliente.setCantidadBW(cliente.getCantidadBW() + 1);
                    }
                }                
                actual=actual.getSiguiente();
            } while(actual != this.lc.getSiguiente());
        }
    }

    public void retirarCliente(String idProxClienteColor, String idProxClienteBW, ListaClientesAtendidos listaClientesAtendidos) {
        
        if(this.lc != null) {
            NodoDobleCircular actual= this.lc.getSiguiente();
            do {
                Cliente aux = (Cliente) actual.getData();
                if(actual.getImagenes() != null && (aux.getCantidadColor() + aux.getCantidadBW() == aux.getTotalImagenes())) {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                    Cliente espera = listaClientesAtendidos.buscar(aux.getIdCliente());
                    espera.totalPasos = aux.getTotalPasos();
                    espera.setTerminoImpresion(true);
                    System.out.println("EL CLIENTE " + espera.getIdCliente() + " HA TERMINADO DE RECIBIR SUS IMANGES Y SE RETIRA DE LA EMPRESA");
                    if(actual == this.lc && this.lc.getAnterior() != this.lc) {
                        this.lc = this.lc.getAnterior();
                    } else if(actual == this.lc && this.lc.getAnterior() == this.lc) {
                        this.lc = null;
                        break;
                    }
                }
                actual=actual.getSiguiente();
            } while(actual != this.lc.getSiguiente());
        }
    }

    public void aumentarPaso() {
        
        if(this.lc != null) {
            NodoDobleCircular actual= this.lc.getSiguiente();

            do {
                Cliente aux = (Cliente) actual.getData();
                aux.setTotalPasos();             
                actual=actual.getSiguiente();
            } while( actual != this.lc.getSiguiente());
        }
    }
    
    ////////////////////////////////////////

    public String codigoGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("node[shape=box, color=red];\n");
        dot.append("label=\"LISTA CIRCULAR CLIENTES EN ESPERA\";\n");            
        dot.append("fontsize = 40;\n");   
        
        String nombresNodos = "";
        String nodosRank = "";
        String conexiones = "";
        int grupo = 1;

        if(this.lc != null) {
            NodoDobleCircular actual= this.lc.getSiguiente();
            do {
                Cliente clienteActual = (Cliente) actual.getData();
                nombresNodos += "nodo" + actual.hashCode() + "[label=\" ID: " +  clienteActual.getIdCliente() +
                "\\n" + clienteActual.getNombre() +  "\\n COLOR: " + clienteActual.getCantidadColor() + 
                "\\n BW: " + clienteActual.getCantidadBW() +"\" " + ", style = filled, fillcolor = lightskyblue, group = " + grupo + "]" + "\n";
                      
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.getSiguiente().hashCode());
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.getAnterior().hashCode());
                nodosRank += "nodo" + actual.hashCode() + "; ";
               
                NodoSimple actualImg = actual.getImagenes();
                if(actualImg != null) {
                    conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actualImg.hashCode());
                }
                while(actualImg != null) {
                    Imagen imagenActual = (Imagen) actualImg.getData();
                    String color = imagenActual.isColor() ? "Color" : "Blanco y Negro";
                    nombresNodos += "nodo" + actualImg.hashCode() + "[label=\"" +  color + "\" " +
                    ",width = 1.8, group = " + grupo + "]" + "\n";     
                    if(actualImg.getSiguiente() != null) {
                        conexiones += String.format("nodo%d -> nodo%d;\n", actualImg.hashCode(), actualImg.getSiguiente().hashCode());               
                    }
                    actualImg = actualImg.getSiguiente();
                }
                actual=actual.getSiguiente();
                grupo += 1;
            } while( actual != this.lc.getSiguiente());
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("{ rank = same; " + nodosRank + " }\n");

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
            escribirArchivo("clientesespera.dot", codigoGraphviz());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tpng","-o","clientesespera.png","clientesespera.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
