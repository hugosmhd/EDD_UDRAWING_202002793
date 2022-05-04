package estructuras;

import javax.swing.table.DefaultTableModel;

import nodos.NodoLD;
import nodos.NodoSimple;
import objetos.Imagen;

public class ListaSimple {
    NodoSimple primero;
    NodoSimple ultimo;
    int cantidadNodos;

    public ListaSimple() {
        this.primero = null;
        this.ultimo = null;
        this.cantidadNodos = 0;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public void insertarAlInicio(Object data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            this.cantidadNodos++;
        } else {
            nuevo.setSiguiente(this.primero);
            this.primero = nuevo;
            this.cantidadNodos++;
        }
    }

    public void insertarAlFinal(Object data) {
        NodoSimple nuevo = new NodoSimple(data);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            this.cantidadNodos++;
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
            this.cantidadNodos++;
        }
    }

    public void eliminarImagen(int idImagen) {
        NodoSimple actual= this.primero;
        boolean encontrado = false;
        NodoSimple anterior = null;

        while(actual!= null && !encontrado){            
            Imagen imgActual = (Imagen) actual.getData();            
            if (imgActual.getId() == idImagen)  {
                encontrado = true;
                break;
            } else
                anterior = actual;
            actual=actual.getSiguiente();
        }

        if (anterior == null && encontrado == true) {
            this.primero = actual.getSiguiente();
            actual.setSiguiente(null); 
        } else if(encontrado == true) {
            anterior.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(null);            
        }
    }

    public String codigoGraphviz(int grupo, NodoLD album) {
        StringBuilder dot = new StringBuilder(); 
        
        String nombresNodos = "";
        String conexiones = "";
        NodoSimple actual= this.primero;
        if (actual != null) {
            conexiones += String.format("nodo%d -> nodo%d;\n", album.hashCode(), actual.hashCode());
        }
        while( actual!= null){
            Imagen imgActual = (Imagen) actual.getData();
            nombresNodos += "nodo" + actual.hashCode() + "[label=\" ID img: " +  imgActual.getId() + "\", group = " + grupo + "]" + "\n";
            if (actual.getSiguiente() != null)            
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.getSiguiente().hashCode());
            actual=actual.getSiguiente();
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        
        return dot.toString();
    }

    public void ordenarMasCapas() {
        Imagen aux;
        int contadorUno = 0;
        NodoSimple actualRecorrido = this.primero;
        while(actualRecorrido != null && contadorUno<(this.cantidadNodos-1)){
            NodoSimple actualRecorridoDos = this.primero;
            int contadorDos = 0;
            while(actualRecorridoDos != null && contadorDos < (this.cantidadNodos-contadorUno-1)){
                Imagen clienteEvaluar = (Imagen) actualRecorridoDos.getData();
                Imagen clienteEvaluarDos = (Imagen) actualRecorridoDos.getSiguiente().getData();
                if(clienteEvaluar.getCapas().cantidadCapas < clienteEvaluarDos.getCapas().cantidadCapas) {
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

    public void agregarDatos(DefaultTableModel modelo) {
        
        modelo.setRowCount(0);
        Object[] datosFila;
        int contador = 1;
        
        NodoSimple actual= this.primero;
        if (primero != null) {
            Imagen aux = (Imagen) actual.getData();
            Object[] datosPFila =  {contador, aux.getId(), aux.getCapas().cantidadCapas};
            datosFila = datosPFila;
            modelo.addRow(datosPFila);
            NodoSimple actualDos= this.primero.getSiguiente();
            while( actualDos!= null && contador != 5){
                contador++;
                Imagen auxDos = (Imagen) actualDos.getData();
                datosFila[0] = contador;
                datosFila[1] = auxDos.getId();
                datosFila[2] =  auxDos.getCapas().cantidadCapas;
                actualDos=actualDos.getSiguiente();
                modelo.addRow(datosFila);
            }
        }

        
    }

    public void visualizar(){
        NodoSimple actual= this.primero;

        while( actual!= null){
            Imagen aux = (Imagen) actual.getData();
            System.out.println("--------- IMAGEN --------------");
            System.out.println("ID Imagen: " + aux.getId());                
            System.out.println("Cantidad capas: " + aux.getCapas().cantidadCapas);
            actual=actual.getSiguiente();
        }
            
    }

    public String imagenes(){
        String imagenes = "";
        NodoSimple actual= this.primero;

        while( actual!= null){
            Imagen aux = (Imagen) actual.getData();
            if (actual.getSiguiente()!=null) imagenes += aux.getId()+ ", ";
            else imagenes += aux.getId();
            actual=actual.getSiguiente();
        }
        return imagenes;
            
    }

}
