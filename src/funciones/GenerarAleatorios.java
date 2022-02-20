package funciones;

import listas.ColaRecepcion;
import objetos.Cliente;

public class GenerarAleatorios {
    public static void aleatorios(ColaRecepcion colaClientes) {
        int numeroClientes = (int) Math.floor(Math.random()*4);

        String[] nombres = { "Pedro", "Hugo", "Dante", "Sebastian", "Andrea", "Luis", "Estuardo", "Daniela",
				"Roberto", "Angie", "Ester", "Ana", "Rebeca", "Karina", "Telma", "Sara", "Sofia",
				"Nestor", "Tomas", "Walter"};
		String[] apellidos = { "Guerra", "Enriquez", "Sanchez", "Gomez", "Cardona", "Rosales", "Hernandez", "Ramirez",
				"Carrillo", "Ovando", "Medina", "Martinez", "De Leon", "Gil", "Fuentes", "Altan", "Contreras",
				"Ruiz" };
                for (int i = 0; i < numeroClientes; i++) {
                    String nombreAleatorio = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))] + " "
                            + apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
                    int cantidadColor = (int) Math.floor(Math.random()*4+1);
                    int cantidadBW = (int) Math.floor(Math.random()*4+1);
                    Cliente nuevo = new Cliente("", nombreAleatorio, cantidadColor, cantidadBW);
                    nuevo.setIdCliente(""+nuevo.hashCode());
                    colaClientes.encolar(nuevo);
                }
    }
}
