package listas;

import nodos.NodoSimple;
import objetos.Cliente;
import objetos.Imagen;
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

    public Ventanilla disponible(){
        NodoSimple actual= this.primero;

        while( actual != null){
            Ventanilla aux = (Ventanilla) actual.getData();
            if(aux.isDisponible()) {
                return aux;
            }
            actual=actual.getSiguiente();
        }
        return null;
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

      

    }

    public void entregarImagenes() {
        NodoSimple actual= this.primero;

        while( actual != null){
            Ventanilla aux = (Ventanilla) actual.getData();
            if(!aux.isDisponible()) {
                Cliente clienteAtendiendo = aux.getCliente();
                System.out.println("------------***** ------");
                System.out.println(clienteAtendiendo);
                System.out.println("------------***** ------");
                if(clienteAtendiendo.getCantidadColor() > 0) {
                    System.out.println("Entra aqui porque hay de color");
                    int nuevaCantidadColor = clienteAtendiendo.getCantidadColor() - 1;
                    clienteAtendiendo.setCantidadColor(nuevaCantidadColor);
                    Imagen nuevaImagen = new Imagen(clienteAtendiendo.getIdCliente(), true);
                    aux.getImagenesCliente().apilar(nuevaImagen);
                } else if(clienteAtendiendo.getCantidadBW() > 0) {
                    int nuevaCantidadBW = clienteAtendiendo.getCantidadBW() - 1;
                    clienteAtendiendo.setCantidadBW(nuevaCantidadBW);
                    Imagen nuevaImagen = new Imagen(clienteAtendiendo.getIdCliente(), false);
                    aux.getImagenesCliente().apilar(nuevaImagen);
                } else {
                    aux.setCliente(null);
                    aux.setDisponible(true);
                }
            }
            actual=actual.getSiguiente();
        }

    }

}
