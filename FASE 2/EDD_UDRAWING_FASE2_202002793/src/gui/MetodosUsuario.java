
package gui;

import estructuras.ArbolAVL;
import estructuras.ArbolBB;
import funciones.CargaMasiva;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MetodosUsuario {
    private boolean flagEdit;
    private String filePath;

    public MetodosUsuario() {
        this.flagEdit = false;
        this.filePath = "";
    }


    private boolean writeFile(String text) {
        FileWriter file = null;

        try {
            file = new FileWriter(this.filePath);
            file.write(text);
            file.close();

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error writing to file");
            if (file != null) {
                try {
                    file.close();
                } catch (IOException eIO) {
                    //
                }
            }
        }
        return false;
    }
    
    public void openFile(String text, ArbolAVL arbolitoAVL, ArbolBB arbolitoBB){
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
                this.flagEdit = false;
                if(text.equalsIgnoreCase("capas")) {
                    CargaMasiva.cargarCapas(this.filePath, arbolitoBB);
                } else if(text.equalsIgnoreCase("img")) {
                    CargaMasiva.cargarImagenes(this.filePath, arbolitoAVL, arbolitoBB);
                }
                JOptionPane.showMessageDialog(null, "Carga con éxito");
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error reading file");
            }
        }
    }
    
}
