package objetos;

import estructuras.ListaSimple;

public class Album {
    String nombre;
    ListaSimple imgs;
    
    public Album(String nombre, ListaSimple imgs) {
        this.nombre = nombre;
        this.imgs = imgs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaSimple getImgs() {
        return imgs;
    }

    public void setImgs(ListaSimple imgs) {
        this.imgs = imgs;
    }    
    
}
