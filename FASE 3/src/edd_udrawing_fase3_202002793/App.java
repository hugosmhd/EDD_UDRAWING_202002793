
package edd_udrawing_fase3_202002793;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

import estructuras.ArbolB;
import estructuras.ListaAdyacencia;
import estructuras.TablaHash;
import funciones.CargaMasiva;
import funciones.DispersionHash;
import funciones.StopWatch;
import gui.Login;
import objetos.Cliente;
import objetos.Lugar;



public class App {

    public static void main(String[] args) throws IOException {

        
        ArbolB arbolitoB = new ArbolB();
        TablaHash tablaMsj = new TablaHash();
        ListaAdyacencia listaAdyacencia = new ListaAdyacencia();

        Login login = new Login(arbolitoB, tablaMsj, listaAdyacencia);
        login.setVisible(true);
        login.setLocationRelativeTo(null);


        
    }

    
    
}
