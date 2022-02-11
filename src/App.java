import listas.Cola;
import listas.ListaSimple;
import listas.Pila;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Cola colaClientes = new Cola();

        System.out.println("COLA CLIENTES");
        colaClientes.encolar(1);
        colaClientes.encolar(2);
        colaClientes.encolar(3);
        colaClientes.encolar(4);

        colaClientes.visualizar();

        colaClientes.desencolar();
        colaClientes.desencolar();
        colaClientes.encolar(5);
        colaClientes.encolar(6);

        colaClientes.visualizar();

        System.out.println("LISTA VENTANILLAS");
        ListaSimple listaVentanillas = new ListaSimple();
        listaVentanillas.insertarAlFinal("Ventanilla 1");
        listaVentanillas.insertarAlFinal("Ventanilla 2");
        listaVentanillas.visualizar();

        System.out.println("PILA IMAGENES");
        Pila pilaImagenes = new Pila();
        pilaImagenes.apilar("Imagen bw");
        pilaImagenes.apilar("Imagen c");
        pilaImagenes.apilar("Imagen bw 2");
        pilaImagenes.visualizar();

        pilaImagenes.desapilar();
        pilaImagenes.visualizar();

        ListaSimple listaClientesAtendidos = new ListaSimple();
        System.out.println("LISTA CLIENTES ATENDIDOS");
        listaClientesAtendidos.insertarAlFinal("Alberto");
        listaClientesAtendidos.insertarAlFinal("Maria");
        listaClientesAtendidos.insertarAlFinal("Juan");
        listaClientesAtendidos.insertarAlFinal("Sofia");
        listaClientesAtendidos.visualizar();

        Cola colaColor = new Cola();
        System.out.println("COLA DE IMPESORAS A COLOR");
        colaColor.encolar("1 hoja");
        colaColor.encolar("2 hoja");
        colaColor.encolar("3 hoja");
        colaColor.encolar("4 hoja");
        colaColor.encolar("5 hoja");
        colaColor.visualizar();
        colaColor.desencolar();
        colaColor.visualizar();
    }
}
