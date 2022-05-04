/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import estructuras.ArbolAVL;
import estructuras.ArbolB;
import estructuras.ArbolBB;
import estructuras.ListaAdyacencia;
import estructuras.ListaDoble;
import estructuras.TablaHash;
import funciones.CargaMasiva;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author hugom
 */
public class MetodosAdmin {
    private String filePath;

    public MetodosAdmin() {
        this.filePath = "";
    }
    
    public void openFile(String text, ArbolB arbolitoB, TablaHash tablaMsj, ListaAdyacencia listaAdyacencia){
        StringBuffer content = new StringBuffer();
        
        JFileChooser openFile = new JFileChooser();
        openFile.setAcceptAllFileFilterUsed(false);
        openFile.setFileFilter(new FileNameExtensionFilter("*." + "json", "json"));
        openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        openFile.setCurrentDirectory(new File("./"));

        int result = openFile.showOpenDialog(null);

        if (result != JFileChooser.CANCEL_OPTION) {
            File file = openFile.getSelectedFile();
            try{   
                this.filePath = file.getAbsoluteFile().toString();
                System.out.println(this.filePath);
                if(text.equalsIgnoreCase("usuarios")) {
                    CargaMasiva.cargarClientes(this.filePath, arbolitoB);
                } else if(text.equalsIgnoreCase("mensajeros")) {
                    CargaMasiva.cargarMensajeros(this.filePath, tablaMsj);
                    // tablaMsj.imprimir();
                } else if(text.equalsIgnoreCase("lugares")) {
                    CargaMasiva.cargarLugares(this.filePath, listaAdyacencia);
                    // tablaMsj.imprimir();
                } else if(text.equalsIgnoreCase("rutas")) {
                    CargaMasiva.cargarRutas(this.filePath, listaAdyacencia);
                    // tablaMsj.imprimir();
                }
                JOptionPane.showMessageDialog(null, "Carga con éxito");
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error en la lectura verifique su entrada");
            }
        }
    }
    
}
