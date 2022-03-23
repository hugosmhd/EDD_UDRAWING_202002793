package estructuras;

import nodos.NodoLD;
import nodos.NodoSimple;
import objetos.Imagen;

public class ListaSimple {
    NodoSimple primero;
    NodoSimple ultimo;

    public ListaSimple() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlFinal(Object data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public String codigoGraphviz(int grupo, NodoLD album) {
        StringBuilder dot = new StringBuilder(); 
        
        String nombresNodos = "";
        String conexiones = "";
        NodoSimple actual= this.primero;
        if (actual != null) {
            conexiones += String.format("nodo%d -> nodo%d;\n", album.hashCode(), actual.hashCode());
        }
        while( actual!= null){
            Imagen imgActual = (Imagen) actual.getData();
            nombresNodos += "nodo" + actual.hashCode() + "[label=\" ID img: " +  imgActual.getId() + "\", group = " + grupo + "]" + "\n";
            if (actual.getSiguiente() != null)            
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.getSiguiente().hashCode());
            actual=actual.getSiguiente();
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        
        return dot.toString();
    }
}
