
package funciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import estructuras.ArbolAVL;
import estructuras.ArbolBB;
import estructuras.MatrizDispersa;
import objetos.Capa;
import objetos.Imagen;

public class CargaMasiva {
    public static void cargarCapas(String ruta, ArbolBB arbolito) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray array = (JSONArray) obj;
            
            
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                // System.out.println("JSON LEIDO: " + jsonObject1);
                
                // System.out.println("DATOS DEL USUARIO: " + i);
                // System.out.println("ID: DESDE AQUI" + jsonObject1.get("id_capa"));
                Long idCapa =  (Long) jsonObject1.get("id_capa");     
                MatrizDispersa matriz = new MatrizDispersa();
                
                JSONArray arrayPix = (JSONArray) jsonObject1.get("pixeles");
                for(int j = 0 ; j < arrayPix.size() ; j++) {
                    JSONObject jsonObject2 = (JSONObject) arrayPix.get(j);
                    // System.out.println("FILA: " + jsonObject2.get("fila"));                                        
                    Long fila =  (Long) jsonObject2.get("fila");                                        
                    // System.out.println("COLUMNA: " + jsonObject2.get("columna"));      
                    Long columna =  (Long) jsonObject2.get("columna");                                    
                    // System.out.println("COLOR: " + jsonObject2.get("color"));   
                    String color =  (String) jsonObject2.get("color");      
                    matriz.insertNodo(color, columna.intValue(), fila.intValue());                                
                }
                // matriz.imprimir();
                Capa nuevaCapa = new Capa(idCapa.intValue(), matriz);
                arbolito.insertar(nuevaCapa);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    
        /*JSONArray jsonDocument =
                (JSONArray)JSONValue
                        .parse(new FileReader(new File(ruta)));
        System.out.println(jsonDocument);*/
    }

    public static void cargarImagenes(String ruta, ArbolAVL arbolitoVL, ArbolBB arbolitoBB) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray array = (JSONArray) obj;
            
            
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                // System.out.println("JSON LEIDO: " + jsonObject1);
                
                // System.out.println("DATOS DEL USUARIO: " + i);
                System.out.println("ID: " + jsonObject1.get("id"));
                Long idImg =  (Long) jsonObject1.get("id");     
                // MatrizDispersa matriz = new MatrizDispersa();
                ArbolBB arbolCapas = new ArbolBB();
                
                JSONArray arrayCapas = (JSONArray) jsonObject1.get("capas");
                for(int j = 0 ; j < arrayCapas.size() ; j++) {
                    System.out.println("CAPA: " + arrayCapas.get(j));
                    Long idCapa = (Long)arrayCapas.get(j);
                    Capa capaEncontrada = arbolitoBB.buscar(idCapa.intValue());
                    System.out.println("CAPA ENCONTRADA: " + capaEncontrada.getIdCapa());
                    arbolCapas.insertar(capaEncontrada);                             
                }
                Imagen imgNueva = new Imagen(idImg.intValue(), arbolCapas);
                arbolitoVL.add(imgNueva);
                // matriz.imprimir();
                // Capa nuevaCapa = new Capa(idCapa.intValue(), matriz);
                // arbolito.add(nuevaCapa);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }
    
    
}
