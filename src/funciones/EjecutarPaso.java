package funciones;

import listas.ColaRecepcion;
import listas.ListaVentanillas;

public class EjecutarPaso {
    public static void ejecutarPaso(ColaRecepcion colaClientes, ListaVentanillas listaVentanillas) {
        if(!colaClientes.estaVacia() && !listaVentanillas.estaVacia()) {
            listaVentanillas.atenderCliente(colaClientes.desencolar());
        } else if(!listaVentanillas.estaVacia()) {
            System.out.println("Ya no hay clientes por atender :)");
        }
    }
}
