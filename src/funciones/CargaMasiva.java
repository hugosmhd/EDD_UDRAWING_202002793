package funciones;

import listas.ColaRecepcion;
// import objetos.Cliente;
import objetos.Cliente;

import java.io.BufferedReader;
import java.io.File;
// import java.io.FileNotFoundException;
import java.io.FileReader;
// import java.io.IOException;
// import java.util.Iterator;
// import java.util.Iterator;
// import java.util.Scanner;

// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;


public class CargaMasiva {
    public static void cargar(String ruta, ColaRecepcion colaClientes) {

        File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      int estado = 0;
      String identificadorActual = "";

      // CLIENTE
      String idCliente = "";
      String nombreCliente = "";
      String imgColor = "";
      String imgBW = "";

      try {
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         String archivoString = "";

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            // System.out.println(linea);
            archivoString += linea;

        // archivoString = archivoString.replaceAll("\\s","");
        // System.out.println(archivoString);
        
        for (int i=0; i < archivoString.length(); i++) {

            if(estado == 0) {
                if(archivoString.charAt(i) == '{') {
                    estado = 1;
                }
            } else if(estado == 1) {
                if(archivoString.charAt(i) == '"') {
                    estado = 2;
                }
            } else if(estado == 2) {
                if(archivoString.charAt(i) == '"') {
                    // System.out.println("----------------------");
                    estado = 3;
                }
            } else if(estado == 3) {
                if(archivoString.charAt(i) == ':') {
                    estado = 4;
                }
            } else if(estado == 4) {
                if(archivoString.charAt(i) == '{') {
                    estado = 5;
                }
            } else if(estado == 5) {
                if(archivoString.charAt(i) == '"') {
                    // System.out.println("DESDE EL ESTADO 5");
                    estado = 6;
                }
            } else if(estado == 6) {
                // System.out.println("DESDE EL ESTADO 6");
                if(archivoString.charAt(i) != '"') {
                    identificadorActual += archivoString.charAt(i);
                    // System.out.println("ESTADO 6" + identificadorActual);
                } else {
                    estado = 7;
                }
            } else if(estado == 7) {
                if(archivoString.charAt(i) == ':') {
                    estado = 8;
                }
            } else if(estado == 8) {
                if(archivoString.charAt(i) == '"') {
                    estado = 9;
                } else if(Character.isDigit(archivoString.charAt(i))) {
                    // System.out.println("EMPEZAMOS A LLENAR EN ESTADO 8");
                    // System.out.println(archivoString.charAt(i));
                    // System.out.println("JAJAJA " + identificadorActual);
                    // System.out.println("JAJAJA " + identificadorActual.length());
                    if(identificadorActual.equalsIgnoreCase("id_cliente")) {
                        // System.out.println("TIENE QUE ENTRARRRRRRR AAAAAAAAAAAAAAAAAA");
                        idCliente += archivoString.charAt(i);
                        // System.out.println(idCliente);
                    } else if(identificadorActual.equalsIgnoreCase("img_color")) {
                        imgColor += archivoString.charAt(i);
                        // System.out.println(imgColor);
                    } else if(identificadorActual.equalsIgnoreCase("img_bw")) {
                        imgBW += archivoString.charAt(i);
                        // System.out.println(imgBW);
                    } 
                    // else if(archivoString.charAt(i) == ',') {
                    //     identificadorActual = "";
                    //     estado = 5;
                    // } else if(archivoString.charAt(i) == '}') {
                    //     identificadorActual = "";
                    //     estado = 10;
                    // }
                } else if(archivoString.charAt(i) == ',') {
                    // System.out.println("IDENTs " + identificadorActual);
                    identificadorActual = "";
                    estado = 5;
                } else if(archivoString.charAt(i) == '}') {
                    // System.out.println("IDENTs " + identificadorActual);
                    identificadorActual = "";
                    estado = 10;
                }
            } else if(estado == 9) {
                // System.out.println("EMPEZAMOS A LLENAR EN EL ESTADO 9");
                // System.out.println(archivoString.charAt(i));
                // System.out.println("JAJAJA " + identificadorActual);
                if(identificadorActual.equalsIgnoreCase("nombre_cliente") && archivoString.charAt(i) != ',' && archivoString.charAt(i) != '}' && archivoString.charAt(i) != '"') {
                    nombreCliente += archivoString.charAt(i);
                    // System.out.println("Entre en el nombre del cliente estado 9");
                    // System.out.println(nombreCliente);
                } else if(identificadorActual.equalsIgnoreCase("id_cliente") && archivoString.charAt(i) != ',' && archivoString.charAt(i) == '}') {
                    idCliente += archivoString.charAt(i);
                    // System.out.println(idCliente);
                } else if(identificadorActual.equalsIgnoreCase("img_color") && archivoString.charAt(i) != ',' && archivoString.charAt(i) == '}') {
                    imgColor += archivoString.charAt(i);
                    System.out.println(imgColor);
                } else if(identificadorActual.equalsIgnoreCase("img_bw") && archivoString.charAt(i) != ',' && archivoString.charAt(i) == '}') {
                    imgBW += archivoString.charAt(i);
                    System.out.println(imgBW);
                } else if(archivoString.charAt(i) == ',') {
                    // System.out.println("IDENT " + identificadorActual);
                    identificadorActual = "";
                    estado = 5;
                } else if(archivoString.charAt(i) == '}') {
                    // System.out.println("IDENT " + identificadorActual);
                    identificadorActual = "";
                    estado = 10;
                }
            } else if(estado == 10) {
                if(idCliente != "" && nombreCliente != "" && imgColor != "" && imgBW != "") {
                    imgColor = imgColor.replaceAll("\\s","");
                    imgColor = imgColor.replaceAll("\\s","");
                    Cliente nuevo = new Cliente(idCliente, nombreCliente, Integer.parseInt(imgColor), Integer.parseInt(imgBW));
                    colaClientes.encolar(nuevo);
                    // System.out.println("ID " + idCliente);
                    // System.out.println("NOMBRE " + nombreCliente);
                    // System.out.println("COLOR " + Integer.parseInt(imgColor));
                    // System.out.println("BLANCO Y NEGRO " + Integer.parseInt(imgBW));
                    idCliente = "";
                    nombreCliente = "";
                    imgColor = "";
                    imgBW = "";
                    // System.out.println("SE DEBE CREAR UN NUEVO CLIENTE");
                }
                if(archivoString.charAt(i) == ',') {                    
                    estado = 1;
                } else if(archivoString.charAt(i) == '}') {
                    System.out.println("Se termino el algoritmo");
                }
            }
            // System.out.println();
        }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        
    }
}
