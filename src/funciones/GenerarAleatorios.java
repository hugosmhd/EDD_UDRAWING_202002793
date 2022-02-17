package funciones;

import listas.ColaRecepcion;
import objetos.Cliente;

public class GenerarAleatorios {
    public static void aleatorios(ColaRecepcion colaClientes) {
        int numeroClientes = (int) Math.floor(Math.random()*4);

        System.out.println("DESDE ALEATORIOS");
        System.out.println(numeroClientes);
        String[] nombres = { "Andrea", "David", "Baldomero", "Balduino", "Baldwin", "Baltasar", "Barry", "Bartolo",
				"Bartolomé", "Baruc", "Baruj", "Candelaria", "Cándida", "Canela", "Caridad", "Carina", "Carisa",
				"Caritina", "Carlota", "Baltazar"};
		String[] apellidos = { "Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
				"Carion", "Castiyo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
				"Grigalva" };
                for (int i = 0; i < numeroClientes; i++) {
                    String nombreAleatorio = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))] + " "
                            + apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
                    int cantidadColor = (int) Math.floor(Math.random()*5+1);
                    int cantidadBW = (int) Math.floor(Math.random()*5+1);
                    Cliente nuevo = new Cliente("", nombreAleatorio, cantidadColor, cantidadBW);
                    nuevo.setIdCliente(""+nuevo.hashCode());
                    colaClientes.encolar(nuevo);
                    System.out.println(nombreAleatorio);
                }
    }
}
