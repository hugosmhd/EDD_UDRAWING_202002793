package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

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

    public void camino(Lugar a, Lugar b) {
        ListaSimple rutaOptima = new ListaSimple();
        Lugar actual = b;

        while (actual != null) {
            rutaOptima.insertarAlInicio(actual);
            System.out.println(actual.getId() + " " + actual.getDepartamento() + " " + actual.getNombre());
            actual = actual.getPadre();            
        }
    }

    public void imprimir(){

        NodoVector actual= this.primero;

        while( actual != null) {
            // System.out.println("--------- VERTICE --------------");
            System.out.print("ID: " + actual.getData().getId());                
            // System.out.println("DEPARTAMENTO: " + actual.getData().getDepartamento());                
            // System.out.println("NOMBRE: " + actual.getData().getNombre());                
            NodoGrafo actualGrafo = actual.getAdyacencia();
            while(actualGrafo != null) {
                System.out.print(" -> " + actualGrafo.getData().getDestino().getData().getId());    
                actualGrafo = actualGrafo.getSiguiente();
            }                
            actual = actual.getSiguiente();
            System.out.println("");
        }
    }

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
            // System.out.println("--------- VERTICE --------------");
            // System.out.print("ID: " + actual.getData().getId()); 
            nodos += actual.getData().getId()+ "[label=\"" + actual.getData().getDepartamento() + "\\n" +
            actual.getData().getNombre() + "\", group=" + actual.getData().getId() + "]\n";               
            // System.out.println("DEPARTAMENTO: " + actual.getData().getDepartamento());                
            // System.out.println("NOMBRE: " + actual.getData().getNombre());                
            NodoGrafo actualGrafo = actual.getAdyacencia();
            while(actualGrafo != null) {
                System.out.print(" -> " + actualGrafo.getData().getDestino().getData().getId()); 
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
