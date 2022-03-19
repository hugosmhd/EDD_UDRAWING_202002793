package nodos;

import objetos.Imagen;

public class NodoAVL {
    Imagen img;
	NodoAVL izq;
	NodoAVL der;
    int alt;
	
	public NodoAVL(Imagen img){
		this.img = img;
		this.izq = der = null;
        alt = 0;
	}

	public Imagen getImg() {
		return img;
	}

	public void setImg(Imagen img) {
		this.img = img;
	}

	public NodoAVL getIzq() {
		return izq;
	}

	public void setIzq(NodoAVL izq) {
		this.izq = izq;
	}

	public NodoAVL getDer() {
		return der;
	}

	public void setDer(NodoAVL der) {
		this.der = der;
	}

	public int getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
		this.alt = alt;
	}

	

	
}
