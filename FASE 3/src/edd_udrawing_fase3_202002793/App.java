
package edd_udrawing_fase3_202002793;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import estructuras.ArbolB;
import estructuras.TablaHash;
import funciones.CargaMasiva;
import funciones.DispersionHash;
import gui.Login;

public class App {

    public static void main(String[] args) throws IOException {
        /*ArbolB arbolitoB = new ArbolB();
        Login login = new Login(arbolitoB);
         login.setVisible(true);
        login.setLocationRelativeTo(null);        */

        // String clave;
        // long valor;
        // BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        // for (int k = 1; k <= 10; k++) {
        //     System.out.println("\nClave a dispersas:");
        //     clave = entrada.readLine();
        //     valor = DispersionHash.transformaClave(clave);
        //     System.out.println("TRANSFORMACION A ENTERO: " + valor);
        //     valor = DispersionHash.dispersion(valor);
        //     System.out.println("Dispersion de la clave " + clave + " \t " + valor);
        // }

        /*TablaHash tabla = new TablaHash();
        tabla.insertar("Hola");
        tabla.insertar("mundo");
        tabla.insertar("esta");
        tabla.insertar("es");
        tabla.insertar("una");
        tabla.insertar("estructura");
        tabla.insertar("de");
        tabla.insertar("datos");
        tabla.insertar("llamada");
        tabla.insertar("tabla");
        tabla.insertar("de ");
        tabla.insertar("dispersion");
        tabla.insertar("o hash");
        tabla.imprimir();
        System.out.println("--- Busqueda de elementos ---");*/
        // System.out.println(tabla.buscar("mundo"));

        String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 3\\mensajeros.json";
        try {
            TablaHash tablaMensajeros = new TablaHash();
            CargaMasiva.cargarMensajeros(ruta, tablaMensajeros);
            tablaMensajeros.imprimir();
        } catch (IOException ex) {
            
        }
        
    }
    
}
