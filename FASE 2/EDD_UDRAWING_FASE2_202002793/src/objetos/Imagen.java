package objetos;

import estructuras.ArbolBB;
import estructuras.MatrizDispersa;

public class Imagen {
    int id;
    ArbolBB capas;
    
    public Imagen(int id, ArbolBB capas) {
        this.id = id;
        this.capas = capas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArbolBB getCapas() {
        return capas;
    }

    public void setCapas(ArbolBB capas) {
        this.capas = capas;
    }    
    
}
