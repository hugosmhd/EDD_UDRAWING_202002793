
package estructuras;

import nodos.NodoMD;

public class MatrizDispersa {

    NodoMD raiz;

    public MatrizDispersa() {
        raiz = new NodoMD("raiz", -1, -1);
    }

    public NodoMD insertEnFila(NodoMD nuevoNodo, NodoMD encabezadoFila) {
        NodoMD actual = encabezadoFila;
        Boolean isMayor = false;
        while (actual != null) {
            if (actual.getColumna() > nuevoNodo.getColumna()) {
                isMayor = true;
                break;
            } else if (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            } else {
                break;
            }
        }

        if (isMayor == true) {
            nuevoNodo.setSiguiente(actual);
            nuevoNodo.setAnterior(actual.getAnterior());
            actual.getAnterior().setSiguiente(nuevoNodo);
            actual.setAnterior(nuevoNodo);
        } else {
            actual.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(actual);
        }
        return nuevoNodo;
    }

    public NodoMD insertEnColumna(NodoMD nuevoNodo, NodoMD encabezadoColumna) {
        NodoMD actual = encabezadoColumna;
        Boolean isMayor = false;

        while (actual != null) {
            if (actual.getFila() > nuevoNodo.getFila()) {
                isMayor = true;
                break;
            } else if (actual.getInferior() != null) {
                actual = actual.getInferior();
            } else {
                break;
            }
        }

        if (isMayor == true) {
            nuevoNodo.setInferior(actual);
            nuevoNodo.setSuperior(actual.getSuperior());
            actual.getSuperior().setInferior(nuevoNodo);
            actual.setSuperior(nuevoNodo);
        } else {
            actual.setInferior(nuevoNodo);
            nuevoNodo.setSuperior(actual);
        }
        return nuevoNodo;
    }

    public NodoMD encontrarColumna(int columna) {
        NodoMD actual = raiz;

        while (actual != null) {
            if (actual.getColumna() == columna) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public NodoMD encontrarFila(int fila) {
        NodoMD actual = raiz;

        while (actual != null) {
            if (actual.getFila() == fila) {
                return actual;
            }
            actual = actual.getInferior();
        }
        return null;
    }
    
    public NodoMD crearColumna(int columna){
        return insertEnFila(new NodoMD("Col", columna, -1), raiz);
    }
    
    public NodoMD crearFila(int fila){
        return insertEnColumna(new NodoMD("Row", -1, fila), raiz);
    }
    
    public void insertNodo(Object data, int col, int fil){
        NodoMD nuevo= new NodoMD(data, col, fil);
        
        NodoMD columna= encontrarColumna(col);
        NodoMD fila= encontrarFila(fil);
        
        
        if (columna == null && fila == null){
            columna=crearColumna(col);
            fila=crearFila(fil);
            
            nuevo= insertEnFila(nuevo, fila);
            nuevo=insertEnColumna(nuevo, columna);
        }else if(columna != null && fila ==null){
            fila=crearFila(fil);
            nuevo=insertEnFila(nuevo, fila);
            nuevo=insertEnColumna(nuevo, columna);
        }else if(columna==null && fila!=null){
            columna=crearColumna(col);
            nuevo=insertEnFila(nuevo, fila);
            nuevo=insertEnColumna(nuevo, columna);
        }else{
            nuevo=insertEnFila(nuevo, fila);
            nuevo=insertEnColumna(nuevo, columna);
        }
    }
    
    public void imprimir(){
        NodoMD actual=raiz;
        while(actual !=null){
            System.out.print("["+actual.getData() +"  "+ actual.getColumna() +"  "+actual.getFila()+"]");
            NodoMD actual2=actual.getSiguiente();
            while(actual2!=null){
                System.out.print("["+actual2.getData() +"  "+actual2.getColumna() +"  "+actual2.getFila()+"]");
                actual2=actual2.getSiguiente();
            }
            System.out.println("");
            actual=actual.getInferior();
        }
    }
    
    
    
}


