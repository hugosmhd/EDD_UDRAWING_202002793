package listas;

import java.io.FileWriter;
import java.io.PrintWriter;

import nodos.NodoSimple;
import objetos.Cliente;

public class ListaClientesAtendidos {
    NodoSimple primero;
    NodoSimple ultimo;
    int cantidadClientes;

    public ListaClientesAtendidos() {
        this.primero = null;
        this.ultimo = null;
        this.cantidadClientes = 0;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlFinal(Cliente data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            this.cantidadClientes += 1;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
            this.cantidadClientes += 1;
        }
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while(actual != null){
            Cliente aux = (Cliente) actual.getData();
            System.out.println("--------- CLIENTE --------------");
            System.out.println("ID: " + aux.getIdCliente());                
            System.out.println("Nombre: " + aux.getNombre());                
            System.out.println("Ventanilla atencion: " + aux.getVentanillaAtencion());                
            System.out.println("A Color: " + aux.getCantidadColor());                
            System.out.println("A Blanco y Negro: " +aux.getCantidadBW());
            System.out.println("Total imagenes: " +aux.getTotalImagenes());
            System.out.println("Cantidad total de pasos: " + aux.getTotalPasos());
            actual=actual.getSiguiente();
        }
            
    }
    
    public Cliente buscar(String idCliente){
        NodoSimple actual= this.primero;

        Cliente aux = (Cliente) actual.getData();
        while(actual != null && aux.getIdCliente() != idCliente){
            aux = (Cliente) actual.getData();
            actual=actual.getSiguiente();
        }
        return aux;
            
    }
        
    public String codigoGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("node[shape=box, color=red];\n");
        dot.append("label=\"LISTA DE CLIENTES ATENDIDOS\";\n");            
        dot.append("fontsize = 40;\n");   
        
        String nombresNodos = "";
        String conexiones = "";
        NodoSimple actual= this.primero;
        while( actual!= null){
            Cliente clienteActual = (Cliente) actual.getData();
            if(clienteActual.isTerminoImpresion()) {
                String terminoProceso = clienteActual.isTerminoImpresion() ? "Si" : "No";
                nombresNodos += "nodo" + actual.hashCode() + "[label=\" ID:" +  clienteActual.getIdCliente() + "\\n" + 
                "Cliente: " +  clienteActual.getNombre() + "\\n" +  "IMG COLOR: " +  clienteActual.getCantidadColor() + 
                "\\n" +  "IMG BW: " +  clienteActual.getCantidadBW() + 
                "\\n" +  "PASOS TOTAL: " +  clienteActual.getTotalPasos() + 
                "\\n" +  "VENT. ATENCION: " +  clienteActual.getVentanillaAtencion() + 
                "\\n" +  "TERMINO PROCESO: " +  terminoProceso + "\"]" + "\n";
                Cliente sigCliente = (Cliente) actual.getSiguiente().getData();
                if (actual.getSiguiente() != null && sigCliente.isTerminoImpresion())            
                    conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.getSiguiente().hashCode());
            }
            actual=actual.getSiguiente();
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("rankdir=LR;\n");
        dot.append("} \n");    
        
        return dot.toString();
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
    
    public void dibujarGraphviz() {
        try {
            escribirArchivo("clientesatendidos.dot", codigoGraphviz());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tpng","-o","clientesatendidos.png","clientesatendidos.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ordenamientoClienteMasPasos() {
        Cliente aux;
        int contadorUno = 0;
        NodoSimple actualRecorrido = this.primero;
        while(actualRecorrido != null && contadorUno<(this.cantidadClientes-1)){
            NodoSimple actualRecorridoDos = this.primero;
            int contadorDos = 0;
            while(actualRecorridoDos != null && contadorDos < (this.cantidadClientes-contadorUno-1)){
                Cliente clienteEvaluar = (Cliente) actualRecorridoDos.getData();
                Cliente clienteEvaluarDos = (Cliente) actualRecorridoDos.getSiguiente().getData();
                if(clienteEvaluar.getTotalPasos() < clienteEvaluarDos.getTotalPasos()) {
                    aux = clienteEvaluar;
                    actualRecorridoDos.setData(clienteEvaluarDos);
                    actualRecorridoDos.getSiguiente().setData(aux);
                }
                contadorDos += 1;
                actualRecorridoDos=actualRecorridoDos.getSiguiente();
            }            
            contadorUno += 1;
            actualRecorrido=actualRecorrido.getSiguiente();
        }     
    }

    public void ordenamientoBurbujaImgColor() {
        Cliente aux;
        int contadorUno = 0, contadorDos = 0;
        NodoSimple actualRecorrido = this.primero;
        while(actualRecorrido != null && contadorUno<(this.cantidadClientes-1)){
            NodoSimple actualRecorridoDos = this.primero;
            while(actualRecorridoDos != null && contadorDos < (this.cantidadClientes-contadorUno-1)){
                Cliente clienteEvaluar = (Cliente) actualRecorridoDos.getData();
                Cliente clienteEvaluarDos = (Cliente) actualRecorridoDos.getSiguiente().getData();
                if(clienteEvaluar.getCantidadColor() < clienteEvaluarDos.getCantidadColor()) {
                    aux = clienteEvaluar;
                    actualRecorridoDos.setData(clienteEvaluarDos);
                    actualRecorridoDos.getSiguiente().setData(aux);
                }
                contadorDos += 1;
                actualRecorridoDos=actualRecorridoDos.getSiguiente();
            }            
            contadorUno += 1;
            contadorDos = 0;
            actualRecorrido=actualRecorrido.getSiguiente();
        }     
    }

    public void ordenamientoBurbujaImgBw() {
        Cliente aux;
        int contadorUno = 0;
        NodoSimple actualRecorrido = this.primero;
        while(actualRecorrido != null && contadorUno<this.cantidadClientes-1){
            NodoSimple actualRecorridoDos = this.primero;
            int contadorDos = 0;
            while(actualRecorridoDos != null && contadorDos < (this.cantidadClientes-contadorUno-1)){
                Cliente clienteEvaluar = (Cliente) actualRecorridoDos.getData();
                Cliente clienteEvaluarDos = (Cliente) actualRecorridoDos.getSiguiente().getData();
                if(clienteEvaluar.getCantidadBW() > clienteEvaluarDos.getCantidadBW()) {
                    aux = clienteEvaluar;
                    actualRecorridoDos.setData(clienteEvaluarDos);
                    actualRecorridoDos.getSiguiente().setData(aux);
                }
                contadorDos += 1;
                actualRecorridoDos=actualRecorridoDos.getSiguiente();
            }
            
            contadorUno += 1;
            actualRecorrido=actualRecorrido.getSiguiente();
        }     
    }

    public void topCincoMayorImgColor() {
        System.out.println("--------- TOP 5 CLIENTES CON MAS IMAGENES A COLOR ---------");
        this.ordenamientoBurbujaImgColor();
        NodoSimple actual= this.primero;
        int contador = 0;

        while(actual != null && contador < 5){
            Cliente aux = (Cliente) actual.getData();
            if(aux.isTerminoImpresion()) {
                System.out.println("--------- CLIENTE " + (contador + 1) + "--------------");
                System.out.println("ID: " + aux.getIdCliente());                
                System.out.println("Nombre: " + aux.getNombre());                
                System.out.println("Ventanilla atencion: " + aux.getVentanillaAtencion());                
                System.out.println("A Color: " + aux.getCantidadColor());                
                System.out.println("A Blanco y Negro: " +aux.getCantidadBW());
                System.out.println("Total imagenes: " +aux.getTotalImagenes());
                System.out.println("Cantidad total de pasos: " + aux.getTotalPasos());
                contador += 1;
            }
            actual=actual.getSiguiente();
        }

    }

    public void topCincoMenosImgBW() {
        System.out.println("--------- TOP 5 CLIENTES CON MENOS IMAGENES A BW ---------");
        this.ordenamientoBurbujaImgBw();
        NodoSimple actual= this.primero;
        int contador = 0;

        while(actual != null && contador < 5){
            Cliente aux = (Cliente) actual.getData();
            if(aux.isTerminoImpresion()) {
                System.out.println("--------- CLIENTE " + (contador + 1) + "--------------");
                System.out.println("ID: " + aux.getIdCliente());                
                System.out.println("Nombre: " + aux.getNombre());                
                System.out.println("Ventanilla atencion: " + aux.getVentanillaAtencion());                
                System.out.println("A Color: " + aux.getCantidadColor());                
                System.out.println("A Blanco y Negro: " +aux.getCantidadBW());
                System.out.println("Total imagenes: " +aux.getTotalImagenes());
                System.out.println("Cantidad total de pasos: " + aux.getTotalPasos());
                contador += 1;
            }
            actual=actual.getSiguiente();
        }

    }

    public void clienteMasPasos() {
        System.out.println("--------- CLIENTE CON MAS PASOS ---------");
        this.ordenamientoClienteMasPasos();
        NodoSimple actual= this.primero;
        int contador = 1;

        while(actual != null){
            Cliente aux = (Cliente) actual.getData();
            if(actual.getSiguiente() != null && aux.isTerminoImpresion())  {
                Cliente clienteSiguiente = (Cliente) actual.getSiguiente().getData();
                System.out.println("--------- CLIENTE " + (contador) + "--------------");
                System.out.println("ID: " + aux.getIdCliente());                
                System.out.println("Nombre: " + aux.getNombre());                
                System.out.println("Ventanilla atencion: " + aux.getVentanillaAtencion());                
                System.out.println("A Color: " + aux.getCantidadColor());                
                System.out.println("A Blanco y Negro: " +aux.getCantidadBW());
                System.out.println("Total imagenes: " +aux.getTotalImagenes());
                System.out.println("Cantidad total de pasos: " + aux.getTotalPasos());
                contador += 1;
                if((aux.getTotalPasos() != clienteSiguiente.getTotalPasos())) {
                    break;
                }
                
                
            }           
            actual=actual.getSiguiente();
        }

    }

    public void datosCliente(String idCliente) {
        System.out.println("--------- DATOS DEL CLIENTE ---------");
        NodoSimple actual = this.primero;
        if (this.primero != null) {
            Cliente clienteActual = (Cliente) actual.getData();
            while (!clienteActual.getIdCliente().equalsIgnoreCase(idCliente)) {
                if (actual.getSiguiente() != null) {
                    clienteActual = (Cliente) actual.getSiguiente().getData();
                } else {
                    actual = actual.getSiguiente();
                    break;
                }
                actual = actual.getSiguiente();
            }
            if (actual == null) {
                System.out.println("Cliente no encontrado verifique que el ID exista");
            } else {
                System.out.println("--------- CLIENTE --------------");
                System.out.println("ID: " + clienteActual.getIdCliente());                
                System.out.println("Nombre: " + clienteActual.getNombre());                
                System.out.println("Ventanilla atencion: " + clienteActual.getVentanillaAtencion());                
                System.out.println("A Color: " + clienteActual.getCantidadColor());                
                System.out.println("A Blanco y Negro: " +clienteActual.getCantidadBW());
                System.out.println("Total imagenes: " +clienteActual.getTotalImagenes());
                System.out.println("Cantidad total de pasos: " + clienteActual.getTotalPasos());
    
            }
        } else {
            System.out.println("No hay clientes en la lista de clientes atendidos");
        }

        

    }
    
}
