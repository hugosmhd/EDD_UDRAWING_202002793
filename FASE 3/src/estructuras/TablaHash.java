package estructuras;

import funciones.DispersionHash;
import objetos.Mensajero;

public class TablaHash {
    private int M = 37;
    private int noElementos;
    private int carga = (int) Math.ceil((0.75) * M);
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
        if (tabla[posicion] != null && !(String.valueOf(mensajero.getDpi()).equals(String.valueOf(tabla[posicion].getDpi()))))
            posicion = dobleDispersion(posicion, mensajero.getDpi());
        tabla[posicion] = mensajero;
        noElementos++;
        validarFactorCarga();

        // validar el factor de carga y crecimiento del arreglo
    }

    private void validarFactorCarga() {
        // System.out.println("FACTOR CARGA " + this.carga);
        if(this.noElementos == this.carga) {
            Mensajero []tablaAux = this.tabla.clone();
            this.tabla = new Mensajero[this.M * 2];
            this.M = M *2;
            this.carga = (int) Math.ceil((0.75) * this.M);
            System.arraycopy(tablaAux, 0, this.tabla, 0, tablaAux.length);
        }
    }

    public int dobleDispersion(int posicion, long clave) {
        int i = 1;
        int posAux = 0;
        int posAuxDos = posicion;
        while (tabla[posAuxDos] != null) {
            posAux = Math.toIntExact(((clave % 7) + 1) * i);
            posAuxDos = posicion + posAux;
            posAuxDos=posAuxDos%M;
            i++;
        }
        System.out.println("CLAVE " + clave);
        System.out.println(posicion);
        posicion = posAuxDos + posAux;
        posicion=posAuxDos%M;
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
                // System.out.println(tabla[i].getNombres());
                // System.out.println(tabla[i].getApellidos());
                // System.out.println(tabla[i].getGenero());
                // System.out.println(tabla[i].getTelefono());
                // System.out.println(tabla[i].getTipoLicencia());
                // System.out.println(tabla[i].getDireccion());
                System.out.println("------------------------------------------");
            } else {
                System.out.println(i + " NULL");
            }
        }
    }
}
