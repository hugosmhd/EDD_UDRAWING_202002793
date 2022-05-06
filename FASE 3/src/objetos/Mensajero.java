package objetos;

public class Mensajero {
    Long dpi;
    String nombres;
    String apellidos;
    String tipoLicencia;
    String genero;
    String telefono;
    String direccion;

    public Mensajero(Long dpi, String nombres, String apellidos, String tipoLicencia, String genero, 
        String telefono, String direccion) {
            this.dpi = dpi;
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.tipoLicencia = tipoLicencia;
            this.genero = genero;
            this.telefono = telefono;
            this.direccion = direccion;
    }

    public Long getDpi() {
        return dpi;
    }

    public String toString() {
        return this.getNombres();
    }

    public void setDpi(Long dpi) {
        this.dpi = dpi;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}
