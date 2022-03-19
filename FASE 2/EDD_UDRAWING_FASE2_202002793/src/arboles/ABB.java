package arboles;

import estructuras.ArbolBB;
import objetos.Capa;

public class ABB {
    ArbolBB<Capa> capas;

    public ABB() {
        this.capas = new ArbolBB<>();
    }

    public void listarCapas() {
        this.capas.preOrden();
    }
    
    
}
