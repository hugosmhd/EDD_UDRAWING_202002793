
package edd_udrawing_fase3_202002793;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

import estructuras.ArbolB;
import estructuras.ListaAdyacencia;
import estructuras.TablaHash;
import funciones.CargaMasiva;
import funciones.DispersionHash;
import funciones.StopWatch;
import gui.Login;
import objetos.Cliente;
import objetos.Lugar;



public class App {

    public static void main(String[] args) throws IOException {

        // String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 3\\clientes.json";
        // try {
        //     ArbolB tablaMensajeros = new ArbolB();
        //     CargaMasiva.cargarClientes(ruta, tablaMensajeros);
        //     tablaMensajeros.buscarUsuario("gcrennane");
        //     // System.out.println(user.getDpi());
        //     // System.out.println(user.getDireccion());
        //     // System.out.println(user.getNombreCliente());
        //     // System.out.println(user.getIdMunicipio());
        //     // System.out.println(user.getUsername());
        // } catch (IOException ex) {
            
        // }
        // new StopWatch(180);
        // System.out.println("StopWatch Started.");
        
        ArbolB arbolitoB = new ArbolB();
        TablaHash tablaMsj = new TablaHash();
        ListaAdyacencia listaAdyacencia = new ListaAdyacencia();

        Login login = new Login(arbolitoB, tablaMsj, listaAdyacencia);
        login.setVisible(true);
        login.setLocationRelativeTo(null);

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

        // String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 3\\Mensajeroscopia2.json";
        // try {
        //     TablaHash tablaMensajeros = new TablaHash();
        //     CargaMasiva.cargarMensajeros(ruta, tablaMensajeros);
        //     tablaMensajeros.imprimir();
        //     tablaMensajeros.codigoGraphviz();
        // } catch (IOException ex) {
            
        // }



        // String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 3\\edd_lugares.json";
        // ListaAdyacencia listaAdyacencia = new ListaAdyacencia();
        // try {
        //     // TablaHash tablaMensajeros = new TablaHash();
        //     CargaMasiva.cargarLugares(ruta, listaAdyacencia);
            
        //     // listaAdyacencia.imprimir();
        //     // tablaMensajeros.imprimir();
        // } catch (IOException ex) {
            
        // }
        // String ruta2 = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 3\\edd_rutas.json";
        // try {
        //     // TablaHash tablaMensajeros = new TablaHash();
        //     // ListaAdyacencia listaAdyacencia = new ListaAdyacencia();
        //     CargaMasiva.cargarRutas(ruta2, listaAdyacencia);
        //     // listaAdyacencia.imprimir();
        //     listaAdyacencia.codigoGraphviz();
        //     // tablaMensajeros.imprimir();
        // } catch (IOException ex) {
            
        // }
        // Lugar inicio = listaAdyacencia.buscar(5);
        // Lugar fin = listaAdyacencia.buscar(1);
        // listaAdyacencia.dijkstra(inicio);
        // listaAdyacencia.camino(inicio, fin);

        
    }

    
    
}
