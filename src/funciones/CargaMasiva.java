package funciones;

import listas.ColaRecepcion;
import objetos.Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


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

         String linea;
         while((linea=br.readLine())!=null)
            archivoString += linea;

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
                    estado = 6;
                }
            } else if(estado == 6) {
                if(archivoString.charAt(i) != '"') {
                    identificadorActual += archivoString.charAt(i);
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
                    if(identificadorActual.equalsIgnoreCase("id_cliente")) {
                        idCliente += archivoString.charAt(i);
                    } else if(identificadorActual.equalsIgnoreCase("img_color")) {
                        imgColor += archivoString.charAt(i);
                    } else if(identificadorActual.equalsIgnoreCase("img_bw")) {
                        imgBW += archivoString.charAt(i);
                    } 
                } else if(archivoString.charAt(i) == ',') {
                    identificadorActual = "";
                    estado = 5;
                } else if(archivoString.charAt(i) == '}') {
                    identificadorActual = "";
                    estado = 10;
                }
            } else if(estado == 9) {
                if(identificadorActual.equalsIgnoreCase("nombre_cliente") && archivoString.charAt(i) != ',' && archivoString.charAt(i) != '}' && archivoString.charAt(i) != '"') {
                    nombreCliente += archivoString.charAt(i);
                } else if(identificadorActual.equalsIgnoreCase("id_cliente") && archivoString.charAt(i) != ',' && archivoString.charAt(i) != '}' && archivoString.charAt(i) != '"') {
                    idCliente += archivoString.charAt(i);
                } else if(identificadorActual.equalsIgnoreCase("img_color") && archivoString.charAt(i) != ',' && archivoString.charAt(i) != '}' && archivoString.charAt(i) != '"') {
                    imgColor += archivoString.charAt(i);
                } else if(identificadorActual.equalsIgnoreCase("img_bw") && archivoString.charAt(i) != ',' && archivoString.charAt(i) != '}' && archivoString.charAt(i) != '"') {
                    imgBW += archivoString.charAt(i);
                } else if(archivoString.charAt(i) == ',') {
                    identificadorActual = "";
                    estado = 5;
                } else if(archivoString.charAt(i) == '}') {
                    identificadorActual = "";
                    estado = 10;
                }
            } else if(estado == 10) {
                if(idCliente != "" && nombreCliente != "" && imgColor != "" && imgBW != "") {
                    imgColor = imgColor.replaceAll("\\s","");
                    imgBW = imgBW.replaceAll("\\s","");
                    Cliente nuevo = new Cliente(idCliente, nombreCliente, Integer.parseInt(imgColor), Integer.parseInt(imgBW));
                    colaClientes.encolar(nuevo);
                    idCliente = "";
                    nombreCliente = "";
                    imgColor = "";
                    imgBW = "";
                }
                if(archivoString.charAt(i) == ',') {                    
                    estado = 1;
                } else if(archivoString.charAt(i) == '}') {
                    System.out.println("Se termino de leer el archivo :)");
                }
            }
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
