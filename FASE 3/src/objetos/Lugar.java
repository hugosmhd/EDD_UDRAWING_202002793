package objetos;

public class Lugar {
    private int id;
    private String departamento;
    private String nombre;
    private boolean sucursal;

    // ATRIBUTOS UTILES PARA DIJKSTRA
    private Lugar padre;
    private boolean visitado;
    private float distancia;

    public Lugar(int id, String departamento, String nombre, boolean sucursal) {
        this.id = id;
        this.departamento = departamento;
        this.nombre = nombre;
        this.sucursal = sucursal;
        this.visitado = false;
        this.distancia = Float.POSITIVE_INFINITY;
        this.padre = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSucursal() {
        return sucursal;
    }

    public void setSucursal(boolean sucursal) {
        this.sucursal = sucursal;
    } 

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public Lugar getPadre() {
        return padre;
    }

    public void setPadre(Lugar padre) {
        this.padre = padre;
    }   
    
    
        
}
