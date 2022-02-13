package funciones;

import listas.ColaImpresion;
import listas.ColaRecepcion;
import listas.ListaCircularEspera;
import listas.ListaVentanillas;
import objetos.Ventanilla;

public class EjecutarPaso {
    public static void ejecutarPaso(ColaRecepcion colaClientes, ListaVentanillas listaVentanillas,
    ListaCircularEspera listaClientesEspera, ColaImpresion colaColor, ColaImpresion colaBW) {
        colaColor.imprimir();
        colaBW.imprimir();
        listaVentanillas.entregarImagenes(listaClientesEspera, colaColor, colaBW);
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
