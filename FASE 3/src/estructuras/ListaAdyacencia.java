package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.table.DefaultTableModel;

import nodos.NodoGrafo;
import nodos.NodoSimple;
import nodos.NodoVector;
import objetos.Conexion;
import objetos.Lugar;

public class ListaAdyacencia {
    
    NodoVector primero;
    NodoVector ultimo;

    public ListaAdyacencia() {
        this.primero = this.ultimo = null;
    }

    public boolean estaVacia(){
        return this.primero==null;
    }

    public void insertar(Lugar lugar){
        NodoVector nuevo = new NodoVector(lugar);
        if (this.estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public NodoVector buscar(Lugar lugar) {
        NodoVector actual = this.primero;
        while (actual != null && actual.getData().getId() != lugar.getId()) {            
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public Lugar buscar(int idLugar) {
        NodoVector actual = this.primero;
        while (actual != null && actual.getData().getId() != idLugar) {            
            actual = actual.getSiguiente();
        }
        return actual.getData();
    }

    public NodoVector buscarA(int idLugar) {
        NodoVector actual = this.primero;
        while (actual != null && actual.getData().getId() != idLugar) {            
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public void conexion(NodoVector inicio, Conexion conex) {
        NodoVector vertice = inicio;
        NodoGrafo actualGrafo = vertice.getAdyacencia();
        if (actualGrafo == null) {
            vertice.setAdyacencia(new NodoGrafo(conex));
        } else {            
            while (true) {
                if (actualGrafo.getSiguiente() == null) {
                    actualGrafo.setSiguiente(new NodoGrafo(conex));
                    break;
                }
                actualGrafo = actualGrafo.getSiguiente();
            } 
        }
        
    }

    public void dijkstra(Lugar inicio) {
        inicio.setDistancia(0);
        NodoVector actual = buscar(inicio);
        ListaNoVisitados noVisitados = new ListaNoVisitados();
        
        NodoVector actualAdy = this.primero;
        while (actualAdy != null) {
            if (actualAdy.getData().getId() != actual.getData().getId()) {
                actualAdy.getData().setDistancia(Float.POSITIVE_INFINITY);                
            }
            actualAdy.getData().setPadre(null);
            noVisitados.insertarAlFinal(actualAdy.getData()); // AQUI SE PUEDE COLOCAR QUE SEAN SOLO LOS VECINOS DE ESTE MISMO
            actualAdy = actualAdy.getSiguiente();
        }

        while (!noVisitados.estaVacia()) {
            NodoGrafo actualGrafo = actual.getAdyacencia();
            while(actualGrafo != null) {
                if (actualGrafo.getData().getDestino().getData().isVisitado() == false) {
                    if (actual.getData().getDistancia() + actualGrafo.getData().getPeso() < actualGrafo.getData().getDestino().getData().getDistancia()) {
                        actualGrafo.getData().getDestino().getData().setDistancia(actual.getData().getDistancia() + actualGrafo.getData().getPeso());
                        actualGrafo.getData().getDestino().getData().setPadre(actual.getData());
                    }
                }
                actualGrafo = actualGrafo.getSiguiente();
            }
            actual.getData().setVisitado(true);
            noVisitados.eliminarLugar(actual.getData().getId());
            
            actual = this.minimo(noVisitados);
        }
    }

    public NodoVector minimo(ListaNoVisitados noVisitados) {
        if (!noVisitados.estaVacia()) {
            float m = noVisitados.devolverPrimero().getDistancia();
            Lugar v = noVisitados.devolverPrimero();

            NodoSimple actualNoVis = noVisitados.devolverPrimerNodo();
            while (actualNoVis != null) {

                Lugar lugarAux = (Lugar) actualNoVis.getData();
                Lugar verticeE = buscar(lugarAux.getId());
                if (m > verticeE.getDistancia()) {
                    m = verticeE.getDistancia();
                    v = verticeE;
                }
                actualNoVis = actualNoVis.getSiguiente();
            }
            NodoVector nuevoRetorno = buscar(v);
            return nuevoRetorno;

        } 
        return null;
    }

    public void imprimirGrafo() {
        NodoVector actualAdy = this.primero;
        while (actualAdy != null) {
            System.out.println("La distancia del vertice " +  actualAdy.getData().getDepartamento() + " es " + actualAdy.getData().getDistancia() + " llegando desde " + actualAdy.getData().getPadre().getDepartamento());            
            actualAdy = actualAdy.getSiguiente();
        }

    }

    public ListaSimple camino(Lugar a, Lugar b) {
        ListaSimple rutaOptima = new ListaSimple();
        Lugar actual = b;

        while (actual != null) {
            rutaOptima.insertarAlFinal(actual);
            System.out.println(actual.getId() + " " + actual.getDepartamento() + " " + actual.getNombre());
            actual = actual.getPadre();            
        }
        System.out.println(b.getDistancia());
        // recorrer();
        return rutaOptima;
    }

    public void recorrer(){

        NodoVector actual= this.primero;

        while( actual != null) {
            actual.getData().setVisitado(false);           
            actual.getData().setDistancia(Float.POSITIVE_INFINITY);
            NodoGrafo actualGrafo = actual.getAdyacencia();
            while(actualGrafo != null) {
                actualGrafo.getData().getDestino().getData().setVisitado(false);           
                actualGrafo.getData().getDestino().getData().setDistancia(Float.POSITIVE_INFINITY); 
                actualGrafo = actualGrafo.getSiguiente();
            }                
            actual = actual.getSiguiente();
        }
    }

    public void imprimir(){

        NodoVector actual= this.primero;

        while( actual != null) {
            NodoGrafo actualGrafo = actual.getAdyacencia();
            while(actualGrafo != null) {
                System.out.print(" -> " + actualGrafo.getData().getDestino().getData().getId());    
                actualGrafo = actualGrafo.getSiguiente();
            }                
            actual = actual.getSiguiente();
        }
    }

    public void listarSucursales(DefaultTableModel modelo){

        NodoVector actual= this.primero;

        while( actual != null) {
            if (actual.getData().isSucursal()) {
                Object[] datosPFila =  {actual.getData().getId(), actual.getData().getNombre() + ", " + actual.getData().getDepartamento()};
                modelo.addRow(datosPFila);                
            }
            actual = actual.getSiguiente();
        }
    }

    // public void listarSucursales(DefaultTableModel modelo) {
    //     this.listarSucursales(this.raiz, modelo);
    // }

    // private void listarSucursales(NodoAVL tmp, DefaultTableModel modelo) {
	// 	if (tmp != null) {
    //         Object[] datosPFila =  {tmp.getImg().getId(), tmp.getImg().getCapas().devolverCapas()};
    //         modelo.addRow(datosPFila);
	// 		listarImagenes(tmp.getIzq(), modelo);
	// 		listarImagenes(tmp.getDer(), modelo);
	// 	}
	// }

    public void codigoGraphviz(){

        StringBuilder dot = new StringBuilder();
        dot.append("graph G { \n");
        dot.append("labelloc=\"t\";\n");
        dot.append("label=\"Grafo de rutas\";\n");         
        dot.append("fontsize = 40;\n"); 
        dot.append("rankdir=LR;\n"); 

        String nodos = "";
        String relaciones = "";

        NodoVector actual= this.primero;

        while( actual != null) {
            nodos += actual.getData().getId()+ "[label=\"" + actual.getData().getDepartamento() + "\\n" +
            actual.getData().getNombre() + "\", group=" + actual.getData().getId() + "]\n";               
            NodoGrafo actualGrafo = actual.getAdyacencia();
            while(actualGrafo != null) {
                if (actualGrafo.getData().isGraficado()) {
                    relaciones += actual.getData().getId() + " -- " + actualGrafo.getData().getDestino().getData().getId() +  
                    "[label=\"" + actualGrafo.getData().getPeso() + "\"];" + "\n";                    
                }
                actualGrafo = actualGrafo.getSiguiente();
            }                
            actual = actual.getSiguiente();
        }

        dot.append(nodos);
        dot.append(relaciones);

        dot.append("}\n"); 
        // System.out.println(dot.toString());

        escribirArchivo("./reporte.dot", dot.toString());
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void codigoGraphvizAdy(){

        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("labelloc=\"t\";\n");
        dot.append("label=\"Lista de adyacencia\";\n");     
        dot.append("fontsize = 40;\n");     
        dot.append("nodesep=.05;\n"); 
        dot.append("rankdir=LR;\n"); 
        dot.append("node [shape=record,width=.1,height=.1];\n"); 

        String cabeceras = "node0 [label = \"";
        String nodosAdyacentes = "";
        String relaciones = "";
        int contadorFilas = 0;
        

        NodoVector actual= this.primero;

        while( actual != null) {
            // System.out.println("--------- VERTICE --------------");
            // System.out.print("ID: " + actual.getData().getId());
            cabeceras += "<f" + contadorFilas + "> " + actual.getData().getNombre() + ", " +  actual.getData().getDepartamento();
            if (actual.getSiguiente() != null) {
                cabeceras += " | \n";
            }               
            NodoGrafo actualGrafo = actual.getAdyacencia();
            nodosAdyacentes += "nodo" + actual.getData().getId() + "[label = \"{<n> ";
            while(actualGrafo != null) {
                // System.out.print(" -> " + actualGrafo.getData().getDestino().getData().getId()); 
                // if (actualGrafo.getData().isGraficado()) {
                //     relaciones += actual.getData().getId() + " -- " + actualGrafo.getData().getDestino().getData().getId() +  
                //     "[label=\"" + actualGrafo.getData().getPeso() + "\"];" + "\n";                    
                // }
                nodosAdyacentes += actualGrafo.getData().getDestino().getData().getNombre() + ", " +  actualGrafo.getData().getDestino().getData().getDepartamento();
                actualGrafo = actualGrafo.getSiguiente();
                if (actualGrafo != null) {
                    nodosAdyacentes += " | ";
                } else {
                    relaciones += "node0:f" + contadorFilas + " -> " + "nodo" + actual.getData().getId() + ";\n";
                }
            }
            nodosAdyacentes += "}\"]; \n"; 
            contadorFilas++;               
            actual = actual.getSiguiente();
        }

        cabeceras += "\",height=2.5];\n";

        dot.append(cabeceras);
        dot.append(nodosAdyacentes);
        dot.append(relaciones);

        dot.append("}\n"); 
        // System.out.println(dot.toString());
        // System.out.println(cabeceras);
        // System.out.println(nodosAdyacentes);
        // System.out.println(relaciones);

        escribirArchivo("./reporte.dot", dot.toString());
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
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
        

    

}
