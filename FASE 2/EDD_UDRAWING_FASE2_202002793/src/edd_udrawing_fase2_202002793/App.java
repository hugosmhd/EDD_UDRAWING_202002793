
package edd_udrawing_fase2_202002793;

import estructuras.ArbolBB;
import estructuras.ListaDoble;
import estructuras.MatrizDispersa;
import funciones.CargaMasiva;
import gui.InicioAdmin;
import gui.Login;
import gui.Registro;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class App {

    public static void main(String[] args) {

        MatrizDispersa matriz = new MatrizDispersa();
        matriz.insertNodo("a", 9, 5);
        matriz.insertNodo("b", 0, 4);
        matriz.insertNodo("c", 8, 9);
        matriz.insertNodo("d", 8, 10);
        matriz.insertNodo("e", 5, 4);
        matriz.insertNodo("f", 5, 9);
        matriz.imprimir();
        // matriz.imprimirFila(matriz.getRaiz());
        // matriz.imprimirColumna(matriz.getRaiz());
        
        /*InicioAdmin login = new InicioAdmin();
         login.setVisible(true);
        login.setLocationRelativeTo(null);*/
        
        /*Login login = new Login();
         login.setVisible(true);
        login.setLocationRelativeTo(null);*/
        /*ArbolBB arbolito = new ArbolBB();

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
        arbolito.preOrden();*/
        /*//RECORRIDO INORDEN
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
        */
        String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 2\\EDD_UDRAWING_FASE2_202002793\\prueba.json";
        try {
            CargaMasiva.cargarCapas(ruta);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
