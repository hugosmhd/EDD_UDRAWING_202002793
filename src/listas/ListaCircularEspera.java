package listas;

import nodos.NodoDobleCircular;
import nodos.NodoSimple;
import objetos.Cliente;
import objetos.Imagen;

public class ListaCircularEspera {
    NodoDobleCircular primero;
    NodoDobleCircular ultimo;

    public ListaCircularEspera() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia(){
        return primero==null;
    }

    public void insertar(Cliente cliente){
        NodoDobleCircular nuevo=new NodoDobleCircular(cliente);
        if (this.estaVacia()){
            nuevo.setAnterior(nuevo);
            nuevo.setSiguiente(nuevo);
            this.primero=nuevo;            
            this.ultimo=nuevo;            
        }
        else{
            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
            this.ultimo.setSiguiente(this.primero);
            this.primero.setAnterior(this.ultimo);
            

        }
    }

    public void imprimir(){
        NodoDobleCircular actual= this.primero;

        if(actual != null) {
            do {
                Cliente aux = (Cliente) actual.getData();
                System.out.println("--------- CLIENTE EN ESPERA --------------");
                System.out.println("ID: " + aux.getIdCliente());                
                System.out.println("Nombre: " + aux.getNombre());                
                System.out.println("A Color: " + aux.getCantidadColor());                
                System.out.println("A Blanco y Negro: " + aux.getCantidadBW());
                NodoSimple actualImg = actual.getImagenes();
                while(actualImg != null) {
                    Imagen img = (Imagen) actualImg.getData();
                    System.out.println("--------- IMAGEN ENTREGADA --------------");
                    System.out.println("ID Cliente: " + img.getIdCliente());                
                    System.out.println("A color: " + img.isColor());
                    System.out.println("Pasos: " + img.getPasos());
                    actualImg = actualImg.getSiguiente();
                }                
                actual=actual.getSiguiente();
            } while( actual != this.primero);
        }
            
    }

    public void insertarImagen(Imagen imgImpresa) {
        NodoDobleCircular actual= this.primero;

        if(actual != null) {
            do {
                Cliente cliente = (Cliente) actual.getData();
                if(cliente.getIdCliente() == imgImpresa.getIdCliente()) {
                    NodoSimple imgLista = new NodoSimple(imgImpresa);
                    if(actual.getImagenes() == null) {
                        actual.setImagenes(imgLista);
                    } else {
                        NodoSimple actualImagen = actual.getImagenes();
                        imgLista.setSiguiente(actualImagen);
                        actual.setImagenes(imgLista);
                    }
                }                
                actual=actual.getSiguiente();
            } while(actual != this.primero);
        }
    }

}
