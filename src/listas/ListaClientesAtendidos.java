package listas;

import nodos.NodoSimple;
import objetos.Cliente;

public class ListaClientesAtendidos {
    NodoSimple primero;
    NodoSimple ultimo;

    public ListaClientesAtendidos() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlFinal(Cliente data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while(actual != null){
            Cliente aux = (Cliente) actual.getData();
            System.out.println("--------- CLIENTE --------------");
            // if (actual.getSiguiente()!=null) {
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
    
}
