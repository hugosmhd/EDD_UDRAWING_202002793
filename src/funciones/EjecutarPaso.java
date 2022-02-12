package funciones;

import listas.ColaRecepcion;
import listas.ListaVentanillas;
import objetos.Ventanilla;

public class EjecutarPaso {
    public static void ejecutarPaso(ColaRecepcion colaClientes, ListaVentanillas listaVentanillas) {
        listaVentanillas.entregarImagenes();
        if(!colaClientes.estaVacia() && !listaVentanillas.estaVacia()) {
            Ventanilla ventanillaDisponible = listaVentanillas.disponible();
            if(ventanillaDisponible != null) {
                ventanillaDisponible.setCliente(colaClientes.desencolar());
                ventanillaDisponible.setDisponible(false);                
            }
        } else if(!listaVentanillas.estaVacia()) {
            System.out.println("Ya no hay clientes por atender :)");
        }

    }
}
