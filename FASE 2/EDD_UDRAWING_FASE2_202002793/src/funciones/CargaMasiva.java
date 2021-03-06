
package funciones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import estructuras.ArbolAVL;
import estructuras.ArbolB;
import estructuras.ArbolBB;
import estructuras.ListaDoble;
import estructuras.ListaSimple;
import estructuras.MatrizDispersa;
import objetos.Album;
import objetos.Capa;
import objetos.Cliente;
import objetos.Imagen;

public class CargaMasiva {
    public static void cargarCapas(String ruta, ArbolBB arbolito) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray array = (JSONArray) obj;
            
            
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                Long idCapa =  (Long) jsonObject1.get("id_capa");     
                MatrizDispersa matriz = new MatrizDispersa();
                
                JSONArray arrayPix = (JSONArray) jsonObject1.get("pixeles");
                for(int j = 0 ; j < arrayPix.size() ; j++) {
                    JSONObject jsonObject2 = (JSONObject) arrayPix.get(j);
                    Long fila =  (Long) jsonObject2.get("fila");                                        
                    Long columna =  (Long) jsonObject2.get("columna");                                    
                    String color =  (String) jsonObject2.get("color");      
                    matriz.insertNodo(color, columna.intValue(), fila.intValue());                                
                }
                Capa nuevaCapa = new Capa(idCapa.intValue(), matriz);
                arbolito.insertar(nuevaCapa);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }

    public static void cargarImagenes(String ruta, ArbolAVL arbolitoVL, ArbolBB arbolitoBB) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray array = (JSONArray) obj;         
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                Long idImg =  (Long) jsonObject1.get("id");     
                ArbolBB arbolCapas = new ArbolBB();
                
                JSONArray arrayCapas = (JSONArray) jsonObject1.get("capas");
                for(int j = 0 ; j < arrayCapas.size() ; j++) {
                    Long idCapa = (Long)arrayCapas.get(j);
                    Capa capaEncontrada = arbolitoBB.buscar(idCapa.intValue());
                    if (capaEncontrada != null)
                        arbolCapas.insertar(capaEncontrada);                             
                }
                Imagen imgNueva = new Imagen(idImg.intValue(), arbolCapas);
                arbolitoVL.insertar(imgNueva);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }

    public static void cargarAlbumes(String ruta, ArbolAVL arbolitoVL, ListaDoble listaAlbumes) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray array = (JSONArray) obj;         
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                
                
                String nombreAlbum =  (String) jsonObject1.get("nombre_album"); 
                ListaSimple imgs = new ListaSimple();
                
                JSONArray arrayImgs = (JSONArray) jsonObject1.get("imgs");
                for(int j = 0 ; j < arrayImgs.size() ; j++) {
                    Long idImg = (Long)arrayImgs.get(j);
                    try {
                        Imagen imagenEncontrada = arbolitoVL.buscar(idImg.intValue()).getImg();
                        if (imagenEncontrada != null) {
                            System.out.println(imagenEncontrada);
                            imgs.insertarAlFinal(imagenEncontrada);
    
                        }
                    } catch(Exception e) {
                        System.out.println(e);
                    }
                }
                Album albumNuevo = new Album(nombreAlbum, imgs);
                listaAlbumes.insertarF(albumNuevo);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }

    public static void cargarClientes(String ruta, ArbolB arbolitoB) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray array = (JSONArray) obj;         
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                
                
                String dpi =  (String) jsonObject1.get("dpi"); 
                Long dpiN = Long.parseLong(dpi.trim());
                String nombreCliente =  (String) jsonObject1.get("nombre_cliente"); 
                String password =  (String) jsonObject1.get("password"); 
                Cliente nuevoCliente = new Cliente(dpiN, nombreCliente, password);
                arbolitoB.insertar(nuevoCliente);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }
    
    
}
