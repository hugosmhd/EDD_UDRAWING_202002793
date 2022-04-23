package objetos;


import estructuras.ArbolAVL;
import estructuras.ArbolBB;
import estructuras.ListaDoble;

public class Cliente {
    Long dpi;
    String nombreCliente;
    String username;
    String correo;
    String password;
    String telefono;
    String direccion;
    int idMunicipio;
    ArbolAVL arbolitoAVL;
    ArbolBB arbolitoBB;
    ListaDoble listaAlbumes;

    public Cliente(Long dpi, String nombreCliente, String username, String correo,
        String password, String telefono, String direccion, int idMunicipio) {
        this.dpi = dpi;
        this.nombreCliente = nombreCliente;
        this.username = username;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idMunicipio = idMunicipio;
        this.arbolitoAVL = new ArbolAVL();
        this.arbolitoBB = new ArbolBB();
        this.listaAlbumes = new ListaDoble();
    }

    public Long getDpi() {
        return dpi;
    }

    public void setDpi(Long dpi) {
        this.dpi = dpi;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArbolAVL getArbolitoAVL() {
        return arbolitoAVL;
    }

    public void setArbolitoAVL(ArbolAVL arbolitoAVL) {
        this.arbolitoAVL = arbolitoAVL;
    }

    public ArbolBB getArbolitoBB() {
        return arbolitoBB;
    }

    public void setArbolitoBB(ArbolBB arbolitoBB) {
        this.arbolitoBB = arbolitoBB;
    }

    public ListaDoble getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(ListaDoble listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }

    

    
}
