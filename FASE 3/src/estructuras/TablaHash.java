package estructuras;

import funciones.DispersionHash;
import objetos.Mensajero;

public class TablaHash {
    static final int M = 37;
    private int noElementos;
    private Mensajero []tabla;
    // Atributo de factor de carga

    public TablaHash() {
        tabla = new Mensajero[M];
        for (int i = 0; i < M; i++) {
            tabla[i] = null;
        }
        noElementos = 0;
    }

    private int indice(long clave) {
        return DispersionHash.dispersion(clave, M);
    }

    // public void insertar(String clave) {
    //     int posicion = indice(clave);
    //     if (tabla[posicion] != null && tabla[posicion] != clave)
    //     posicion = pruebaLineal(posicion, clave);
    //     tabla[posicion] = clave;
    //     noElementos++;
    //     // validar el factor de carga y crecimiento del arreglo
    // }

    public void insertar(Mensajero mensajero) {
        int posicion = indice(mensajero.getDpi());
        if (tabla[posicion] != null && !String.valueOf(mensajero.getDpi()).equals(String.valueOf(tabla[posicion].getDpi())))
        posicion = pruebaLineal(posicion, mensajero.getDpi());
        tabla[posicion] = mensajero;
        noElementos++;
        // validar el factor de carga y crecimiento del arreglo
    }

    public int pruebaLineal(int posicion, long clave) {
        while (tabla[posicion] != null) {
            posicion++;
            posicion=posicion%M;
        }
        return posicion;
    }

    // VALIDAR LA BUSQUEDA
    // public String buscar(String clave) {
    //     int posicion = indice(clave);
    //     return tabla[posicion];
    // }

    public void imprimir() {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != null) {
                System.out.println(i + "\t" + "MENSAJERO");
                System.out.println(tabla[i].getDpi());
                System.out.println(tabla[i].getNombres());
                System.out.println(tabla[i].getApellidos());
                System.out.println(tabla[i].getGenero());
                System.out.println(tabla[i].getTelefono());
                System.out.println(tabla[i].getTipoLicencia());
                System.out.println(tabla[i].getDireccion());
                System.out.println("------------------------------------------");
            }
        }
    }
}
