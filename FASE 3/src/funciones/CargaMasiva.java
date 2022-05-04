
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
import estructuras.ListaAdyacencia;
import estructuras.ListaDoble;
import estructuras.ListaSimple;
import estructuras.MatrizDispersa;
import estructuras.TablaHash;
import objetos.Album;
import objetos.Capa;
import objetos.Cliente;
import objetos.Conexion;
import objetos.Imagen;
import objetos.Lugar;
import objetos.Mensajero;

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
                String usuario =  (String) jsonObject1.get("usuario");
                String correo =  (String) jsonObject1.get("correo");
                String password =  (String) jsonObject1.get("password"); 
                String telefono =  (String) jsonObject1.get("telefono"); 
                String direccion =  (String) jsonObject1.get("direccion"); 
                Long idMunicipio =  (Long) jsonObject1.get("id_municipio");  
                // System.out.println("CLIENTE ------- ");
                // System.out.println(dpiN);
                // System.out.println(nombreCliente);
                // System.out.println(username);
                // System.out.println(correo);
                // System.out.println(password);
                // System.out.println(telefono);
                // System.out.println(direccion);
                // System.out.println(idMunicipio);
                Cliente nuevoCliente = new Cliente(dpiN, nombreCliente, usuario, correo, password, telefono, direccion, idMunicipio.intValue());
                // System.out.println("DESDE CARGA ----------");
                // System.out.println(dpiN);
                // System.out.println(nombreCliente);
                // System.out.println(usuario);
                arbolitoB.insertar(nuevoCliente);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }

    public static void cargarMensajeros(String ruta, TablaHash tablaMensajeros) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        // System.out.println("Hola gola");
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONArray array = (JSONArray) obj;         
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                
                
                String dpi =  (String) jsonObject1.get("dpi"); 
                Long dpiN = Long.parseLong(dpi.trim());
                String nombres =  (String) jsonObject1.get("nombres"); 
                String apellidos =  (String) jsonObject1.get("apellidos");
                String tipoLicencia =  (String) jsonObject1.get("tipo_licencia");
                String genero =  (String) jsonObject1.get("genero"); 
                String telefono =  (String) jsonObject1.get("telefono"); 
                String direccion =  (String) jsonObject1.get("direccion");   
                // System.out.println("MENSAJERO");
                // System.out.println(dpi);              
                // System.out.println(nombres);              
                // System.out.println(apellidos);              
                // System.out.println(tipoLicencia);              
                // System.out.println(genero);              
                // System.out.println(telefono);              
                // System.out.println(direccion);    
                // System.out.println();          
                Mensajero nuevoMensajero = new Mensajero(dpiN, nombres, apellidos, tipoLicencia, genero, telefono, direccion);
                tablaMensajeros.insertar(nuevoMensajero);
                // arbolitoB.insertar(nuevoCliente);
                
            }
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }

    public static void cargarLugares(String ruta, ListaAdyacencia listaAdyacencia) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        // System.out.println("Hola gola");
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("Lugares");  
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                
                // System.out.println("DATOS DEL LUGAR: " + i);
                // System.out.println("ID: " + jsonObject1.get("id"));
                Long id =  (Long) jsonObject1.get("id");   
                String departamento = (String) jsonObject1.get("departamento");
                String nombre = (String) jsonObject1.get("nombre");
                String sucursal_str = (String) jsonObject1.get("sn_sucursal");
                Boolean sucursal = sucursal_str.equalsIgnoreCase("si") ? true : false;
                
                // System.out.println("DEPARTAMENTO: " + jsonObject1.get("departamento"));
                // System.out.println("NOMBRE: " + jsonObject1.get("nombre"));
                // System.out.println("SUCURSAL: " + jsonObject1.get("sn_sucursal"));                
                // System.out.println("-------------------------------");
                Lugar nuevo = new Lugar(id.intValue(), departamento, nombre, sucursal);
                listaAdyacencia.insertar(nuevo);
            }            
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }

    public static void cargarRutas(String ruta, ListaAdyacencia listaAdyacencia) throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        // System.out.println("Hola gola");
        
        try {
            
            Object obj = parser.parse(new FileReader(ruta));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("Grafo");  
            
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                
                // System.out.println("DATOS DEL LUGAR: " + i);
                // System.out.println("ID: " + jsonObject1.get("id"));
                Long inicio =  (Long) jsonObject1.get("inicio");  
                Long fin =  (Long) jsonObject1.get("final");
                Long peso =  (Long) jsonObject1.get("peso");
                
                Lugar inicioLugar = listaAdyacencia.buscar(inicio.intValue());
                Lugar finLugar = listaAdyacencia.buscar(fin.intValue());

                if (inicioLugar != null && finLugar != null) {
                    Conexion nuevaConexion = new Conexion(finLugar, peso.intValue());
                    listaAdyacencia.conexion(inicioLugar, nuevaConexion);
                }


                
                
            }            
            
        } catch(FileNotFoundException e) { }
        catch(IOException e) { }
        catch(ParseException e) { }
        
    }
    
    
}
