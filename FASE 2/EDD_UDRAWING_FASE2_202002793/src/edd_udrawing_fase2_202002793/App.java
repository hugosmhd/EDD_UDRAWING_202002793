
package edd_udrawing_fase2_202002793;

import estructuras.ArbolBB;
import estructuras.ListaDoble;


public class App {

    public static void main(String[] args) {
        ArbolBB arbolito = new ArbolBB();

        arbolito.insertar(10);
        arbolito.insertar(5);
        arbolito.insertar(3);
        arbolito.insertar(3);
        arbolito.insertar(7);
        arbolito.insertar(1);
        arbolito.insertar(4);
        arbolito.insertar(9);
        arbolito.insertar(14);
        arbolito.insertar(17);
        arbolito.insertar(16);
        arbolito.insertar(28);
        //RECORRIDO PREODEN
        System.out.println("RECORRIDO EN PREORDEN");
        arbolito.preOrden();
        //RECORRIDO INORDEN
        System.out.println("RECORRIDO EN INORDEN");
        arbolito.inOrden();
        //RECORRIDO POST
        System.out.println("RECORRIDO EN POSTORDEN");
        arbolito.postOrden();
        
        ListaDoble listaD = new ListaDoble();
        listaD.insertarF("Ana");
        listaD.insertarF("Pedro");
        listaD.insertarF("Sofia");
        listaD.insertarF("Julio");
        System.out.println("LISTA DOBLEMENTE ENLAZADA");
        listaD.imprimir();
    }
    
}
