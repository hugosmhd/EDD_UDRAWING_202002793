package funciones;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import listas.ColaRecepcion;
import objetos.Cliente;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// import java.util.Iterator;
import java.util.Iterator;


public class CargaMasiva {
    public static void cargar(String ruta, ColaRecepcion colaClientes) {
        JSONParser parser = new JSONParser();

        try {
            // "D:\\HP DOCUMENTOS\\USAC\\2022\\PRIMER SEMESTRE 2022\\ESTRUCTURA DE DATOS\\LABORATORIO\\PROYECTOS\\EDD_UDRAWING_FASE1_202002793\\data.json"

            Object obj = parser.parse(new FileReader(ruta));
            JSONObject clientes = (JSONObject) obj;
            Iterator<String> keys = clientes.keySet().iterator();            

            // System.out.println(clientes.keySet());

            while(keys.hasNext()) {
                String key = keys.next();
                if (clientes.get(key) instanceof JSONObject) {
                    // System.out.println("Solo una vez");
                    JSONObject cliente = (JSONObject) clientes.get(key);

                    String idCliente = (String) cliente.get("id_cliente"); 
                    String nombre = (String) cliente.get("nombre_cliente"); 
                    String cantidadColor = (String) cliente.get("img_color"); 
                    String cantidadBW = (String) cliente.get("img_bw"); 
                    Cliente nuevo = new Cliente(idCliente, nombre, Integer.parseInt(cantidadColor), Integer.parseInt(cantidadBW));
                    colaClientes.encolarMasiva(nuevo);
  
              }
            }

            // colaClientes.visualizar();
            
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
