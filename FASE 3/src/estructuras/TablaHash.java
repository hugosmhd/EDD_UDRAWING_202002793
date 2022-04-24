package estructuras;

import funciones.DispersionHash;

public class TablaHash {
    static final int M = 47;
    private int noElementos;
    private String []tabla;
    // Atributo de factor de carga

    public TablaHash() {
        tabla = new String[M];
        for (int i = 0; i < M; i++) {
            tabla[i] = null;
        }
        noElementos = 0;
    }

    private int indice(String clave) {
        long valor;
        valor = DispersionHash.transformaClave(clave);
        return DispersionHash.dispersion(valor, M);
    }

    public void insertar(String clave) {
        int posicion = indice(clave);
        if (tabla[posicion] != null && tabla[posicion] != clave)
        posicion = pruebaLineal(posicion, clave);
        tabla[posicion] = clave;
        noElementos++;
        // validar el factor de carga y crecimiento del arreglo
    }

    public int pruebaLineal(int posicion, String clave) {
        while (tabla[posicion] != null) {
            posicion++;
            posicion=posicion%M;
        }
        return posicion;
    }

    // VALIDAR LA BUSQUEDA
    public String buscar(String clave) {
        int posicion = indice(clave);
        return tabla[posicion];
    }

    public void imprimir() {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != null) System.out.println(i + "\t" + tabla[i]);
        }
    }
}
