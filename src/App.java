import java.util.Scanner;

import funciones.CargaMasiva;
import funciones.EjecutarPaso;
import listas.ColaRecepcion;
import listas.ListaVentanillas;

public class App {
    public static void main(String[] args) throws Exception {

        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        ColaRecepcion colaClientes = new ColaRecepcion();
        ListaVentanillas listaVentanillas = new ListaVentanillas();

            
        while(!salir){
                
            System.out.println("1. Carga masiva");
            System.out.println("2. Cantidad de ventanillas");
            System.out.println("3. Ejecutar paso");
            System.out.println("4. Salir");
                
            System.out.println("Escribe una de las opciones");
            opcion = sn.nextInt();
                
            switch(opcion){
                case 1:
                    // System.out.println("Has seleccionado la opcion 1");
                    String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_FASE1_202002793\\data.json";
                    CargaMasiva.cargar(ruta, colaClientes);
                    colaClientes.visualizar();
                    break;
                case 2:
                    // System.out.println("Has seleccionado la opcion 2");
                    int cantidadVentanillas;
                    System.out.println("Ingresa la cantidad de ventanillas");
                    cantidadVentanillas = sn.nextInt();
                    listaVentanillas.insertarAlFinal(cantidadVentanillas);
                    listaVentanillas.visualizar();
                    break;
                    case 3:
                    System.out.println("Has seleccionado la opcion 3");
                    EjecutarPaso.ejecutarPaso(colaClientes, listaVentanillas);
                    colaClientes.visualizar();
                    listaVentanillas.visualizar();
                    break;
                    case 4:
                    salir=true;
                    break;
                    default:
                    System.out.println("Solo números entre 1 y 4");
            }
                
        }
        // Cola colaClientes = new Cola();

        // System.out.println("COLA CLIENTES");
        // colaClientes.encolar(1);
        // colaClientes.encolar(2);
        // colaClientes.encolar(3);
        // colaClientes.encolar(4);

        // colaClientes.visualizar();

        // colaClientes.desencolar();
        // colaClientes.desencolar();
        // colaClientes.encolar(5);
        // colaClientes.encolar(6);

        // colaClientes.visualizar();

        // System.out.println("LISTA VENTANILLAS");
        // ListaSimple listaVentanillas = new ListaSimple();
        // listaVentanillas.insertarAlFinal("Ventanilla 1");
        // listaVentanillas.insertarAlFinal("Ventanilla 2");
        // listaVentanillas.visualizar();

        // System.out.println("PILA IMAGENES");
        // Pila pilaImagenes = new Pila();
        // pilaImagenes.apilar("Imagen bw");
        // pilaImagenes.apilar("Imagen c");
        // pilaImagenes.apilar("Imagen bw 2");
        // pilaImagenes.visualizar();

        // pilaImagenes.desapilar();
        // pilaImagenes.visualizar();

        // ListaSimple listaClientesAtendidos = new ListaSimple();
        // System.out.println("LISTA CLIENTES ATENDIDOS");
        // listaClientesAtendidos.insertarAlFinal("Alberto");
        // listaClientesAtendidos.insertarAlFinal("Maria");
        // listaClientesAtendidos.insertarAlFinal("Juan");
        // listaClientesAtendidos.insertarAlFinal("Sofia");
        // listaClientesAtendidos.visualizar();

        // Cola colaColor = new Cola();
        // System.out.println("COLA DE IMPESORAS A COLOR");
        // colaColor.encolar("1 hoja");
        // colaColor.encolar("2 hoja");
        // colaColor.encolar("3 hoja");
        // colaColor.encolar("4 hoja");
        // colaColor.encolar("5 hoja");
        // colaColor.visualizar();
        // colaColor.desencolar();
        // colaColor.visualizar();
    }
}
