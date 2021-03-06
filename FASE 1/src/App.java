import java.util.Scanner;

import funciones.CargaMasiva;
import funciones.EjecutarPaso;
import listas.ColaImpresion;
import listas.ColaRecepcion;
import listas.ListaCircularEspera;
import listas.ListaClientesAtendidos;
import listas.ListaVentanillas;

public class App {
    public static void main(String[] args) throws Exception {             
        Scanner sn = new Scanner(System.in);
        Scanner snString = new Scanner(System.in);
        int contadorPasos = 1;
            boolean salir = false;
            boolean salirParametros = false;
            int opcion; //Guardaremos la opcion del usuario
            ColaRecepcion colaClientes = new ColaRecepcion();
            ListaVentanillas listaVentanillas = new ListaVentanillas();
            ListaCircularEspera  listaClientesEspera = new ListaCircularEspera();
            ListaClientesAtendidos  listaClientesAtendidos = new ListaClientesAtendidos();
            ColaImpresion colaColor = new ColaImpresion();
            ColaImpresion colaBW = new ColaImpresion();

                
            while(!salir){
                System.out.println("------- MENU PRINCIPAL UDRAWING PAPER -------");
                System.out.println("1. Parametros iniciales");
                System.out.println("2. Ejecutar paso");
                System.out.println("3. Estado en memoria de las estructuras");
                System.out.println("4. Reportes");
                System.out.println("5. Acerca de");
                System.out.println("6. Salir");                    
                    
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                    
                switch(opcion){
                    case 1:
                    salirParametros = false;
                    do {
                        System.out.println("------- MENU PARAMTROS INICIALES -------");
                        System.out.println("1. Carga masiva de clientes");
                        System.out.println("2. Cantidad de ventanillas");
                        System.out.println("3. Regresar al menu principal");
                        int opcionParametros = sn.nextInt();
                        switch(opcionParametros) {
                            case 1:
                                String ruta;
                                System.out.println("Ingresa la ruta del archivo");
                                ruta = snString.nextLine();
                                // String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_FASE1_202002793\\data.json";
                                CargaMasiva.cargar(ruta, colaClientes);
                                System.out.println("Presione enter para continuar");
                                snString.nextLine();
                                break;
                            case 2:
                                int cantidadVentanillas;
                                System.out.println("Ingresa la cantidad de ventanillas");
                                cantidadVentanillas = sn.nextInt();
                                listaVentanillas.insertarAlFinal(cantidadVentanillas);
                                System.out.println("Ventanillas creadas");
                                System.out.println("Presione enter para continuar");
                                snString.nextLine();
                                break;
                            case 3:
                                System.out.println("Regresando al menu principal...");
                                salirParametros = true;
                                break;
                        }
                    } while(!salirParametros);
                    break;
                    case 2:
                        System.out.println("---------** PASO " + contadorPasos + " **---------");
                        System.out.println("----------------------------------");
                        EjecutarPaso.ejecutarPaso(colaClientes, listaVentanillas, listaClientesEspera, colaColor, 
                        colaBW, listaClientesAtendidos);
                        contadorPasos += 1;
                        System.out.println("Presione enter para continuar");
                        snString.nextLine();
                        break;
                    case 3:
                        salirParametros = false;
                        do {
                            System.out.println("------- MENU ESTADO EN MEMORIA DE LAS ESTRUCTURAS -------");
                            System.out.println("1. Cola de clientes (Recepcion)");
                            System.out.println("2. Lista de ventanillas");
                            System.out.println("3. Pila de imagenes en ventanillas");
                            System.out.println("4. Lista de clientes atendidos");
                            System.out.println("5. Cola de impresion a color");
                            System.out.println("6. Cola de impresion en blanco y negro");
                            System.out.println("7. Lista de clientes en espera");
                            System.out.println("8. Todas las estructuras");
                            System.out.println("9. Regresar al menu principal");
                            int opcionParametros = sn.nextInt();
                            switch(opcionParametros) {
                                case 1:
                                    colaClientes.dibujarGraphviz();
                                    System.out.println("Grafica generada");
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();
                                    break;
                                case 2:
                                    listaVentanillas.dibujarGraphviz();
                                    System.out.println("Grafica generada");
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                              
                                    break;
                                case 3:
                                    listaVentanillas.dibujarGraphvizPila();
                                    System.out.println("Grafica generada");
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                           
                                    break;
                                case 4:
                                    listaClientesAtendidos.dibujarGraphviz();
                                    System.out.println("Grafica generada");    
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                                
                                    break;
                                case 5:
                                    colaColor.dibujarGraphviz("colacolor");
                                    System.out.println("Grafica generada");   
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                                 
                                    break;
                                case 6:
                                    colaBW.dibujarGraphviz("colabw");
                                    System.out.println("Grafica generada");                                    
                                    break;
                                case 7:
                                    listaClientesEspera.dibujarGraphviz();
                                    System.out.println("Grafica generada");   
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                                 
                                    break;
                                case 8:
                                    colaClientes.dibujarGraphviz();
                                    listaVentanillas.dibujarGraphviz();
                                    listaVentanillas.dibujarGraphvizPila();
                                    listaClientesAtendidos.dibujarGraphviz();
                                    colaColor.dibujarGraphviz("colacolor");
                                    colaBW.dibujarGraphviz("colabw");
                                    listaClientesEspera.dibujarGraphviz();
                                    System.out.println("Grafica generada");   
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                                 
                                    break;
                                case 9:
                                    System.out.println("Regresando al menu principal...");
                                    salirParametros = true;
                                    break;
                            }
                        } while(!salirParametros);
                        break;
                    case 4:
                        salirParametros = false;
                        do {
                            System.out.println("------- MENU REPORTES -------");
                            System.out.println("1. Top 5 clientes con mayor cantidad de imagenes a color");
                            System.out.println("2. Top 5 clientes con menor cantidad de imagenes a blanco y negro");
                            System.out.println("3. Informacion del cliente con mas pasos en el sistema");
                            System.out.println("4. Datos de un cliente en especifico por el ID");
                            System.out.println("5. Regresar al menu principal");
                            int opcionParametros = sn.nextInt();
                            switch(opcionParametros) {
                                case 1:
                                    listaClientesAtendidos.topCincoMayorImgColor();
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();
                                    break;
                                case 2:
                                    listaClientesAtendidos.topCincoMenosImgBW();    
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                                                             
                                    break;
                                case 3:
                                    listaClientesAtendidos.clienteMasPasos();   
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                                 
                                    break;
                                case 4:
                                    System.out.println("Ingrese el ID del cliente:");
                                    String idCliente = snString.nextLine();
                                    listaClientesAtendidos.datosCliente(idCliente);   
                                    System.out.println("Presione enter para continuar");
                                    snString.nextLine();                                 
                                    break;
                                case 5:
                                    System.out.println("Regresando al menu principal...");
                                    salirParametros = true;
                                    break;
                            }
                        } while(!salirParametros);
                        break;
                    case 5:
                        System.out.println("--------- DATOS DEL ESTUDIANTE ---------");
                        System.out.println("Nombre: Hugo Sebastian Mart??nez Hern??ndez");
                        System.out.println("Carn??: 202002793");
                        System.out.println("Estructura de Datos");
                        System.out.println("Secci??n A, Primer Semestre 2022");
                        System.out.println("Presione enter para continuar");
                        snString.nextLine();
                        break;
                    case 6:
                        salir=true;
                        break;
                    default:
                        System.out.println("Solo n??meros entre 1 y 6");
                    
                }
                    
            }
        

    }


}
