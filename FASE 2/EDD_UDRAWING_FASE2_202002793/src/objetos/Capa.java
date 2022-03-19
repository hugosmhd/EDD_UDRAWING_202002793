
package objetos;

import estructuras.MatrizDispersa;

public class Capa implements Comparable<Capa>{
    int idCapa;
    MatrizDispersa pixeles;
    
    public Capa(int idCapa, MatrizDispersa pixeles) {
        this.idCapa = idCapa;
        this.pixeles = pixeles;
    }

    

    public int getIdCapa() {
        return idCapa;
    }



    public void setIdCapa(int idCapa) {
        this.idCapa = idCapa;
    }



    public MatrizDispersa getPixeles() {
        return pixeles;
    }



    public void setPixeles(MatrizDispersa pixeles) {
        this.pixeles = pixeles;
    }



    @Override
    public int compareTo(Capa capa) {
        
        return this.getIdCapa() - capa.getIdCapa();
    }
}
