package listas;

import nodos.NodoDobleCircular;
import nodos.NodoSimple;
import objetos.Cliente;
import objetos.Imagen;

public class ListaCircularEspera {
    // NodoDobleCircular primero;
    // NodoDobleCircular ultimo;
    NodoDobleCircular lc;

    public ListaCircularEspera() {
        // this.primero = null;
        // this.ultimo = null;
        this.lc = null;
    }

    public boolean estaVacia(){
        return this.lc==null;
    }

    public void insertar(Cliente cliente){
        NodoDobleCircular nuevo = new NodoDobleCircular(cliente);
        if (this.estaVacia()) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
        } else {
            NodoDobleCircular primero = this.lc.getSiguiente();  // bueno
            nuevo.setSiguiente(primero);  // bueno
            nuevo.setAnterior(this.lc); // bueno

            this.lc.setSiguiente(nuevo);
            primero.setAnterior(nuevo);
        }
        this.lc = nuevo;
    }

    public void imprimir(){
        if(this.lc != null) {
            NodoDobleCircular actual= this.lc.getSiguiente();

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
            } while( actual != this.lc.getSiguiente());
        }
            
    }


    public void insertarImagen(Imagen imgImpresa) {
        NodoDobleCircular actual= this.lc.getSiguiente();

        if(actual != null) {
            do {
                Cliente cliente = (Cliente) actual.getData();
                if(cliente.getIdCliente() == imgImpresa.getIdCliente()) {
                    NodoSimple imgLista = new NodoSimple(imgImpresa);
                    cliente.setTotalImagenes();
                    if(actual.getImagenes() == null) {
                        actual.setImagenes(imgLista);
                    } else {
                        NodoSimple actualImagen = actual.getImagenes();
                        imgLista.setSiguiente(actualImagen);
                        actual.setImagenes(imgLista);
                    }
                    if(imgImpresa.isColor()) {
                        cliente.setCantidadColor(cliente.getCantidadColor() + 1);
                    } else {
                        cliente.setCantidadBW(cliente.getCantidadBW() + 1);
                    }
                }                
                actual=actual.getSiguiente();
            } while(actual != this.lc.getSiguiente());
        }
    }

    public void retirarCliente(String idProxClienteColor, String idProxClienteBW) {
        
        if(this.lc != null) {
            NodoDobleCircular actual= this.lc.getSiguiente();
            do {
                Cliente aux = (Cliente) actual.getData();
                if(actual.getImagenes() != null && (aux.getIdCliente() != idProxClienteColor && aux.getIdCliente() != idProxClienteBW)) {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                    if(actual == this.lc && this.lc.getAnterior() != this.lc) {
                        this.lc = this.lc.getAnterior();
                    } else if(actual == this.lc && this.lc.getAnterior() == this.lc) {
                        this.lc = null;
                        break;
                    }
                }
                actual=actual.getSiguiente();
            } while(actual != this.lc.getSiguiente());
        }
    }

}
