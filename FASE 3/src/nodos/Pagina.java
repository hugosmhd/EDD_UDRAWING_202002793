package nodos;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import estructuras.ArbolB;
import estructuras.Cola;
import objetos.Cliente;

public class Pagina {
    boolean hoja;
    int contador;
    NodoB primero;
    String graphviz = "";

    public Pagina() {
        this.hoja = true;
        this.contador = 0;
        this.primero = null;
    }

    public void insertar(NodoB nuevo) {
        if (primero == null) {
            primero = nuevo;
            contador++;
        } else {
            NodoB auxiliar = primero;
            while (auxiliar != null) {
                if (String.valueOf(auxiliar.getData().getDpi()).equals(String.valueOf(nuevo.getData().getDpi())) ) {//------------->ya existe en el arbol
                    JOptionPane.showMessageDialog(null, "El cliente con el DPI " + auxiliar.getData().getDpi() + " ya existe.");
                    break;
                } else {
                    if (auxiliar.getData().getDpi() > nuevo.getData().getDpi()) {
                        if (auxiliar == primero) {
                            auxiliar.anterior = nuevo;
                            nuevo.siguiente = auxiliar;
                            auxiliar.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            primero = nuevo;
                            contador++;
                            break;
                        } else {
                            nuevo.siguiente = auxiliar;
                            auxiliar.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            nuevo.anterior = auxiliar.anterior;
                            auxiliar.anterior.siguiente = nuevo;
                            auxiliar.anterior = nuevo;
                            contador++;
                            break;
                        }
                    } else if (auxiliar.siguiente == null) {
                        auxiliar.siguiente = nuevo;
                        nuevo.anterior = auxiliar;
                        contador++;
                        break;
                    }
                }
                auxiliar = auxiliar.siguiente;
            }

        }
    }  
    
    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public NodoB getPrimero() {
        return primero;
    }

    public void setPrimero(NodoB primero) {
        this.primero = primero;
    }
    
    // public Cliente imprimirB(ArbolB nueva, String user){        
    //     NodoB aux=this.primero;
    //     int contador = 1;
    //     System.out.println(user);
    //     Cliente cliente = this.imprimirBD(user);
    //     while(aux != null ){  
    //         if (aux.getIzquierda() != null) {
    //              nueva.buscarUsuario(aux.getIzquierda(), user);
    //         }          
    //         if (aux.getDerecha() != null && contador == this.getContador()) {
    //             nueva.buscarUsuario(aux.getDerecha(), user); 
    //         }
    //         contador++;    
    //         aux=aux.siguiente;
    //     }
    //     return cliente;
        
    // }

    public Cliente imprimirBD(String username){        
        NodoB aux=this.primero;
        while(aux != null){
            System.out.println("------ HOLA JAJAJA ------");
            System.out.println(aux.getData().getDpi());
            System.out.println(aux.getData().getNombreCliente());
            System.out.println(aux.getData().getUsername());            
            aux=aux.siguiente;
        }
        Cliente nuevo = new Cliente(2070707090116L, "hugo", "hugoses", "hugo", "1234", "55555", "44444", 1);
        return nuevo;
    }


    public String imprimir(ArbolB nueva, String graphviz){        
        NodoB aux=this.primero;
        int contador = 1;
        String nuevoGraphviz = "";
        nuevoGraphviz += this.imprimirD();
        while(aux != null){  
            if (aux.getIzquierda() != null) {
                nuevoGraphviz += nueva.preOrden(aux.getIzquierda(), graphviz);
            }          
            if (aux.getDerecha() != null && contador == this.getContador()) {
                nuevoGraphviz += nueva.preOrden(aux.getDerecha(), graphviz); 
            }
            contador++;    
            aux=aux.siguiente;
        }
        return nuevoGraphviz;
        
    }

    public String imprimirD(){        
        NodoB aux=this.primero;
        String paginaStr = "nodo" + this.hashCode() + "[label=\"<P0>";
        String conexiones = "";
        int contador = 1;
        while(aux != null){
            // System.out.print(aux.id + ", ");
            paginaStr += "|" + "DPI: " + aux.getData().getDpi() + "\\n Nombre: " + aux.getData().getNombreCliente()  + "|<P" + contador + ">";
            if (aux.getIzquierda() != null) {
                conexiones += "nodo" + this.hashCode() + ":P" + (contador-1)  +" -> nodo" + aux.getIzquierda().hashCode() + ";\n";
            }
            if (aux.getDerecha() != null && contador == this.getContador()) {
                conexiones += "nodo" + this.hashCode() + ":P" + (contador)  +" -> nodo" + aux.getDerecha().hashCode() + ";\n";               
                
            }
            
            contador++;
            aux=aux.siguiente;
        }
        paginaStr += "\"];\n";
        return paginaStr + conexiones;
    }

    public void encolar(Cola q) {
        NodoB aux=this.primero;
        int contadorD = 1;
        while(aux != null){  
            if (aux.getIzquierda() != null) {
                q.encolar(aux.getIzquierda());
            }          
            if (aux.getDerecha() != null && contadorD == this.getContador()) {
                q.encolar(aux.getDerecha());
            }
            contadorD++;    
            aux=aux.siguiente;
        }
    }

    public void visualizar(DefaultTableModel modelo){     
        NodoB aux=this.primero;
        while(aux != null){
            Object[] datosPFila =  {aux.getData().getNombreCliente(), aux.getData().getDpi(), aux.getData().getArbolitoAVL().getTotalImg()};
            modelo.addRow(datosPFila);            
            // contador++;
            aux=aux.siguiente;
        }
    }

    public Cliente visualizarDos(String user){     
        NodoB aux=this.primero;
        while(aux != null){
            if (aux.getData().getUsername().equals(user)) {
                return aux.getData();
            }
            // System.out.println("------");
            System.out.println(aux.getData().getDpi());
            // System.out.println(aux.getData().getUsername());
            aux=aux.siguiente;
        }
        return null;
    }

    public void visualizarPass(DefaultTableModel modelo){     
        NodoB aux=this.primero;
        while(aux != null){
            Object[] datosPFila =  {aux.getData().getNombreCliente(), aux.getData().getDpi(), aux.getData().getPassword()};
            modelo.addRow(datosPFila);            
            // contador++;
            aux=aux.siguiente;
        }
    }
}

