
package edd_udrawing_fase2_202002793;

import estructuras.ArbolB;
import gui.Login;



public class App {

    public static void main(String[] args) {
        ArbolB arbolitoB = new ArbolB();
        System.out.println("");
        /*InicioAdmin admin = new InicioAdmin(arbolitoB);
        admin.setVisible(true);
        admin.setLocationRelativeTo(null);*/
        /*ArbolB arbolitoB = new ArbolB();
        String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 2\\EDD_UDRAWING_FASE2_202002793\\clientes.json";
        try {
            CargaMasiva.cargarClientes(ruta, arbolitoB);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        arbolitoB.preOrden();
        Long dpi = 6999062130101L;
        Cliente clienteEncontrado = arbolitoB.buscar(dpi);
        if (clienteEncontrado != null) {
            System.out.println(clienteEncontrado.getNombreCliente());            
        } else {
            System.out.println("Cliente no encontrado");
        }*/

        // arbolB.insertar(75);
        // arbolB.insertar(40);
        // arbolB.insertar(23);
        // arbolB.insertar(8);
        // arbolB.insertar(6);
        // arbolB.insertar(37);
        // arbolB.insertar(32);
        // arbolB.insertar(45);
        // arbolB.insertar(25);
        // arbolB.insertar(1);
        // arbolB.insertar(88);
        // arbolB.insertar(29);
        // arbolB.insertar(4);
        // arbolB.insertar(11);
        // arbolB.insertar(39);
        // arbolB.insertar(14);
        // arbolB.insertar(66);
        // arbolB.insertar(57);
        // arbolB.insertar(90);
        // arbolB.insertar(24);
        // arbolB.insertar(12);
        // arbolB.insertar(7);
        // arbolB.preOrden();


       /*ArbolAVL arbolitoAVL = new ArbolAVL();
        ArbolBB arbolitoBB = new ArbolBB();
        ListaDoble listaAlbumes = new ListaDoble();
        
        InicioUsuario user = new InicioUsuario(arbolitoAVL, arbolitoBB, listaAlbumes);
        user.setVisible(true);
        user.setLocationRelativeTo(null);*/


        /*ArbolAVL arbolito = new ArbolAVL();
        arbolito.add(5);arbolito.add(10);
        arbolito.add(20);arbolito.add(25);
		arbolito.add(30);arbolito.add(35);              
        arbolito.add(40);
		arbolito.preorder(arbolito.root);
		System.out.println();
		arbolito.enorder(arbolito.root);
		System.out.println();
		arbolito.postorder(arbolito.root);*/
	
        /*MatrizDispersa matriz = new MatrizDispersa();
        matriz.insertNodo("a", 9, 5);
        matriz.insertNodo("b", 0, 4);
        matriz.insertNodo("c", 8, 9);
        matriz.insertNodo("d", 8, 10);
        matriz.insertNodo("e", 5, 4);
        matriz.insertNodo("f", 5, 9);
        matriz.imprimir();*/
        // matriz.imprimirFila(matriz.getRaiz());
        // matriz.imprimirColumna(matriz.getRaiz());
        
        /*InicioAdmin login = new InicioAdmin();
         login.setVisible(true);
        login.setLocationRelativeTo(null);*/
        
        Login login = new Login(arbolitoB);
         login.setVisible(true);
        login.setLocationRelativeTo(null);
        /*ArbolBB arbolito = new ArbolBB();

        arbolito.insertar(10);
        arbolito.insertar(5);
        arbolito.insertar(3);
        arbolito.insertar(3);
        arbolito.insertar(7);
        arbolito.insertar(1);
        arbolito.insertar(4);
        arbolito.insertar(9);
        arbolito.insertar(14);
        arbolito.insertar(17);
        arbolito.insertar(16);
        arbolito.insertar(28);
        //RECORRIDO PREODEN
        System.out.println("RECORRIDO EN PREORDEN");
        arbolito.preOrden();*/
        /*//RECORRIDO INORDEN
        System.out.println("RECORRIDO EN INORDEN");
        arbolito.inOrden();
        //RECORRIDO POST
        System.out.println("RECORRIDO EN POSTORDEN");
        arbolito.postOrden();
        
        ListaDoble listaD = new ListaDoble();
        listaD.insertarF("Ana");
        listaD.insertarF("Pedro");
        listaD.insertarF("Sofia");
        listaD.insertarF("Julio");
        System.out.println("LISTA DOBLEMENTE ENLAZADA");
        listaD.imprimir();
        */
        
        
        /*ArbolBB arbolitoBB = new ArbolBB();
        String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 2\\EDD_UDRAWING_FASE2_202002793\\prueba.json";
        try {
            CargaMasiva.cargarCapas(ruta, arbolitoBB);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        // arbolito.inOrden();
        ArbolAVL arbolitoAVL = new ArbolAVL();
        String rutaImg = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 2\\EDD_UDRAWING_FASE2_202002793\\sonic.json";
        try {
            CargaMasiva.cargarImagenes(rutaImg, arbolitoAVL, arbolitoBB);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("YA LLEGO AL AVL");
        arbolitoAVL.enorder(arbolitoAVL.root);*/
        
        /*ArbolBB arbolitoBB = new ArbolBB();
        String ruta = "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_202002793\\FASE 2\\EDD_UDRAWING_FASE2_202002793\\prueba.json";
        try {
            CargaMasiva.cargarCapas(ruta, arbolitoBB);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        arbolitoBB.inOrden(2);*/
        
        
    }
    
}
