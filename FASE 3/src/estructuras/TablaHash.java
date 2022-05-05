package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

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
        // System.out.println("CLAVE " + clave);
        // System.out.println(posicion);
        posicion = posAuxDos + posAux;
        posicion=posAuxDos%M;
        return posicion;
    }

    // VALIDAR LA BUSQUEDA
    // public String buscar(String clave) {
    //     int posicion = indice(clave);
    //     return tabla[posicion];
    // }

    private void escribirArchivo(String ruta, String contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.write(contenido);
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(pw != null) {
                pw.close();
            }            
        }
    }

    public void codigoGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("labelloc=\"t\";\n");
        dot.append("label=\"Tabla Hash Mensajeros\";\n");         
        dot.append("fontsize = 40;\n"); 
        dot.append("nodesep=.05;");  
        dot.append("rankdir=LR;");  
        dot.append("node [shape=record,width=.1,height=.1];");

        String tabla = "node0 [label = \"";

        for (int i = 0; i < this.tabla.length; i++) {
            tabla += "Posicion: " + "[" + i + "] \\n"; 
            if (this.tabla[i] != null) {
                // System.out.println(i + "\t" + "MENSAJERO");
                tabla += "DPI: " + this.tabla[i].getDpi() + "\\n \n";
                tabla += "Nombre: " + this.tabla[i].getNombres() + " " + this.tabla[i].getApellidos() + "\\n \n";
                tabla += "Genero: " + this.tabla[i].getGenero() + "\\n \n";
                tabla += "Telefono: " + this.tabla[i].getTelefono() + "\\n \n";
                tabla += "Tipo Licen: " + this.tabla[i].getTipoLicencia() + "\\n \n";
                tabla += "Direccion: " + this.tabla[i].getDireccion() + "\\n \n";
            }
            tabla += " | ";
        }

        tabla += "\",height=2.5];";

        
        dot.append(tabla);
        dot.append("node [width = 1.5]; \n");  
        dot.append("} \n");  
        System.out.println(dot.toString());
        
        escribirArchivo("./reporte.dot", dot.toString());
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
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
