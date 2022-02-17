package funciones;

import listas.ColaImpresion;
import listas.ColaRecepcion;
import listas.ListaCircularEspera;
import listas.ListaClientesAtendidos;
import listas.ListaVentanillas;
import objetos.Cliente;
import objetos.Imagen;
import objetos.Ventanilla;

public class EjecutarPaso {
    public static void ejecutarPaso(ColaRecepcion colaClientes, ListaVentanillas listaVentanillas,
    ListaCircularEspera listaClientesEspera, ColaImpresion colaColor, ColaImpresion colaBW, 
    ListaClientesAtendidos listaClientesAtendidos) {
        // GenerarAleatorios.aleatorios(colaClientes);

        colaClientes.aumentarPasoClientes();
        listaClientesEspera.aumentarPaso();

        // listaClientesEspera.aumentarPaso();
        String idProxClienteColor = colaColor.idClienteProximo();
        String idProxClienteBW = colaBW.idClienteProximo();
        listaClientesEspera.retirarCliente(idProxClienteColor, idProxClienteBW, listaClientesAtendidos);

        Imagen imagenImpresaColor =  colaColor.imprimir();
        Imagen imagenImpresaBW = colaBW.imprimir();
        if(imagenImpresaColor != null) {
            listaClientesEspera.insertarImagen(imagenImpresaColor);
        }
        if(imagenImpresaBW != null) {
            listaClientesEspera.insertarImagen(imagenImpresaBW);
        }
        listaVentanillas.entregarImagenes(listaClientesEspera, colaColor, colaBW, listaClientesAtendidos);        
        if(!colaClientes.estaVacia() && !listaVentanillas.estaVacia()) {
            Ventanilla ventanillaDisponible = listaVentanillas.disponible();
            if(ventanillaDisponible != null) {
                Cliente clienteEspera = colaClientes.desencolar();
                ventanillaDisponible.setCliente(clienteEspera);
                System.out.println("EL CLIENTE " + clienteEspera.getIdCliente() + " INGRESA A LA VENTANILLA " + ventanillaDisponible.getIdVentanilla());
                ventanillaDisponible.setDisponible(false);                
            }
        } else if(!listaVentanillas.estaVacia()) {
            System.out.println("Ya no hay clientes por atender :)");
        }

    }
}
