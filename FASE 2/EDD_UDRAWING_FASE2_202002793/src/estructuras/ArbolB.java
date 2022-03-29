package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import nodos.NodoB;
import nodos.Pagina;
import objetos.Cliente;

public class ArbolB {
    int ordenArbol = 5;
    Pagina raiz;
    String graphviz = "digraph G {\n" + "node[shape=record]\n";

    public ArbolB() {
        this.raiz = null;
    }

    public void insertar(Cliente cliente) {
        NodoB nodo = new NodoB(cliente);
        if (raiz == null) {
            raiz = new Pagina();
            raiz.insertar(nodo);
        } else {
            NodoB obj = insertarEnRama(nodo, raiz);
            if (obj != null) {
                raiz = new Pagina();
                raiz.insertar(obj);
                raiz.setHoja(false);
            }
        }
    }

    private NodoB insertarEnRama(NodoB nodo, Pagina rama) {
        if (rama.isHoja()) {
            rama.insertar(nodo);
            if (rama.getContador() == ordenArbol) {
                return dividir(rama);
            } else {
                return null;
            }
        } else {
            NodoB temp = rama.getPrimero();
            do {
                if (String.valueOf(nodo.getData().getDpi()).equals(String.valueOf(temp.getData().getDpi()))) {
                    return null;
                } else if (nodo.getData().getDpi() < temp.getData().getDpi()) {
                    NodoB obj = insertarEnRama(nodo, temp.getIzquierda());
                    if (obj instanceof NodoB) {
                        rama.insertar((NodoB) obj);
                        if (rama.getContador() == ordenArbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                } else if (temp.getSiguiente() == null) {
                    NodoB obj = insertarEnRama(nodo, temp.getDerecha());
                    if (obj instanceof NodoB) {
                        rama.insertar((NodoB) obj);
                        if (rama.getContador() == ordenArbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp = (NodoB) temp.getSiguiente();
            } while (temp != null);
        }
        return null;
    }

    private NodoB dividir(Pagina rama) {
        Cliente val = null;
        NodoB temporal, nuevoNodo;
        NodoB auxiliar = rama.getPrimero();
        Pagina ramaDer = new Pagina();
        Pagina ramaIzq = new Pagina();

        int cont = 0;
        while (auxiliar != null) {
            cont++;
            if (cont < 3) {
                temporal = new NodoB(auxiliar.getData());
                temporal.setIzquierda(auxiliar.getIzquierda());
                if (cont == 2) {
                    temporal.setDerecha(auxiliar.getSiguiente().getIzquierda());
                } else {
                    temporal.setDerecha(auxiliar.getDerecha());
                }
                if (temporal.getDerecha() != null && temporal.getIzquierda() != null) {
                    ramaIzq.setHoja(false);
                }

                ramaIzq.insertar(temporal);

            } else if (cont == 3) {
                val = auxiliar.getData();
            } else {
                temporal = new NodoB(auxiliar.getData());
                temporal.setIzquierda(auxiliar.getIzquierda());
                temporal.setDerecha(auxiliar.getDerecha());
                if (temporal.getDerecha() != null && temporal.getIzquierda() != null) {
                    ramaDer.setHoja(false);
                }
                ramaDer.insertar(temporal);
            }
            auxiliar = auxiliar.getSiguiente();
        }
        nuevoNodo = new NodoB(val);
        nuevoNodo.setDerecha(ramaDer);
        nuevoNodo.setIzquierda(ramaIzq);
        return nuevoNodo;
    }

    public Cliente buscar(long dpi) {
        return buscar(dpi, raiz);
    }

    public Cliente buscar(long dpi, Pagina rama) {
        NodoB temp = rama.getPrimero();
        do {
            if (String.valueOf(dpi).equals(String.valueOf(temp.getData().getDpi()))) {
                return temp.getData();
            } else if (dpi < temp.getData().getDpi()) {
                Cliente obj = buscar(dpi, temp.getIzquierda());
                return obj;
            } else if (temp.getSiguiente() == null) {
                Cliente obj = buscar(dpi, temp.getDerecha());
                return obj;
            }
            temp = (NodoB) temp.getSiguiente();
        } while (temp != null);
        return null;

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

    public void preOrden(){
        this.graphviz = "digraph G {\n" + "node[shape=record]\n";    
        this.graphviz += preOrden(this.raiz, "");
        this.graphviz += "}";
        
        escribirArchivo("./reporte.dot", this.graphviz);
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public String preOrden(Pagina raiz, String graphviz){
        if(raiz != null){
            graphviz += raiz.imprimir(this, graphviz);
        }
        return graphviz;
    }

    public void listarClientesAmpli(DefaultTableModel modelo, JLabel label) {
        listarClientesAmpli(this.raiz, modelo);
        // listarClientesAmpli(this.raiz);
        // this.contador = 0;
    }

    private void listarClientesAmpli(Pagina root, DefaultTableModel modelo) {
        if (root == null)
          return;

        Cola q = new Cola();      
        q.encolar(root);      
        q.encolar(null);
      
        while (!q.estaVacia()) {      
            Pagina curr = q.desencolarB();    
            if (curr == null) {
                if (!q.estaVacia()) {
                    q.encolar(null);
                }
                
            } else {
                curr.encolar(q);
                curr.visualizar(modelo);

                
            }
        }
    }

    public void listarClientesAmpliPass(DefaultTableModel modelo) {
        listarClientesAmpliPass(this.raiz, modelo);
    }

    private void listarClientesAmpliPass(Pagina root, DefaultTableModel modelo) {
        if (root == null)
          return;

        Cola q = new Cola();      
        q.encolar(root);      
        q.encolar(null);
      
        while (!q.estaVacia()) {      
            Pagina curr = q.desencolarB();    
            if (curr == null) {
                if (!q.estaVacia()) {
                    q.encolar(null);
                }
                
            } else {
                curr.encolar(q);
                curr.visualizarPass(modelo);

                
            }
        }
    }



}
