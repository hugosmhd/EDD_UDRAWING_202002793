package funciones;

import listas.ColaImpresion;
import listas.ColaRecepcion;
import listas.ListaCircularEspera;
import listas.ListaVentanillas;
import objetos.Imagen;
import objetos.Ventanilla;

public class EjecutarPaso {
    public static void ejecutarPaso(ColaRecepcion colaClientes, ListaVentanillas listaVentanillas,
    ListaCircularEspera listaClientesEspera, ColaImpresion colaColor, ColaImpresion colaBW) {
        Imagen imagenImpresaColor =  colaColor.imprimir();
        Imagen imagenImpresaBW = colaBW.imprimir();
        if(imagenImpresaColor != null) {
            listaClientesEspera.insertarImagen(imagenImpresaColor);
        }
        if(imagenImpresaBW != null) {
            listaClientesEspera.insertarImagen(imagenImpresaBW);
        }
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
