package listas;

import nodos.NodoSimple;
import objetos.Cliente;
import objetos.Ventanilla;

public class ListaVentanillas {
    NodoSimple primero;
    NodoSimple ultimo;

    public ListaVentanillas() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlFinal(int cantidadVentanillas) {
        for(int i=0; i < cantidadVentanillas; i++) {
            Ventanilla ventanilla = new Ventanilla(i, true);
            NodoSimple nuevo = new NodoSimple(ventanilla);
            if (estaVacia()) {
                this.primero = nuevo;
                this.ultimo = nuevo;
            } else {
                this.ultimo.setSiguiente(nuevo);
                this.ultimo = nuevo;
            }
        }
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            Ventanilla aux = (Ventanilla) actual.getData();
            System.out.println("--------- VENTANILLA --------------");
            System.out.println("ID: " + aux.getIdVentanilla());                
            System.out.println("Disponible: " + aux.isDisponible());
            if(aux.getCliente() != null) {
                Cliente clienteAtendiendo = aux.getCliente();
                System.out.println("------- CLIENTE ANTENDIENDO -------");
                System.out.println("ID: " + clienteAtendiendo.getIdCliente());                
                System.out.println("Nombre: " + clienteAtendiendo.getNombre());                
                System.out.println("A Color: " + clienteAtendiendo.getCantidadColor());                
                System.out.println("A Blanco y Negro: " +clienteAtendiendo.getCantidadBW());   

            }
            actual=actual.getSiguiente();
        }
            
    }

    public void atenderCliente(Cliente cliente) {
        NodoSimple actual= this.primero;

        while( actual != null){
            Ventanilla aux = (Ventanilla) actual.getData();
            if(aux.isDisponible()) {
                aux.setCliente(cliente);
                aux.setDisponible(false);
                System.out.println("Cliente atendido en ventanilla " + aux.getIdVentanilla());
                break;
            }
            actual=actual.getSiguiente();
        }

        // System.out.println("No hay ventanillas disponibles por ahora");

    }

}
