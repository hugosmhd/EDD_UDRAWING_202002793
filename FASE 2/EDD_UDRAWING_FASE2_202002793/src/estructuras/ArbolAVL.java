package estructuras;

import nodos.NodoAVL;
import objetos.Imagen;

public class ArbolAVL {
    public NodoAVL root = null;
	
	public void add(Imagen img) {
		root = add(img, root);
	}
	
	public NodoAVL add(Imagen img, NodoAVL tmp) {
        if (tmp == null) tmp = new NodoAVL(img);
		else if (img.getId() < tmp.getImg().getId()) {
            tmp.setIzq(add(img, tmp.getIzq()));
            if ((altura(tmp.getIzq())-altura(tmp.getDer()))==2) {
                if (img.getId()<tmp.getIzq().getImg().getId()) tmp = srl(tmp);
                else tmp = drl(tmp);
            }            
		}
		else {
            tmp.setDer(add(img, tmp.getDer()));
            if ((altura(tmp.getDer())-altura(tmp.getIzq()))==2) {
                if (img.getId()>tmp.getDer().getImg().getId()) tmp = srr(tmp);
                else tmp = drr(tmp);
            }            

		}
        int d, i, m;
        d = altura(tmp.getDer());
        i = altura(tmp.getIzq());
        m = maxi(d,i);
        tmp.setAlt(m + 1);
        return tmp;
	}

    int altura(NodoAVL tmp) {
        if (tmp == null) return -1;
        else return tmp.getAlt();
    }

    int maxi(int val1, int val2) {
        return ((val1 > val2) ? val1 : val2);
    }

    NodoAVL srl(NodoAVL t1) {
        NodoAVL t2;
        t2 = t1.getIzq();
        t1.setIzq(t2.getDer());
        t2.setDer(t1);
        t1.setAlt(maxi(altura(t1.getIzq()), altura(t1.getDer()))+1);
        t2.setAlt(maxi(altura(t2.getIzq()),t1.getAlt())+1);
        return t2;
    }

    NodoAVL srr(NodoAVL t1) {
        NodoAVL t2;
        t2 = t1.getDer();
        t1.setDer(t2.getIzq());
        t2.setIzq(t1);
        t1.setAlt(maxi(altura(t1.getIzq()), altura(t1.getDer()))+1);
        t2.setAlt(maxi(altura(t2.getDer()),t1.getAlt())+1);
        return t2;
    }

    NodoAVL drl(NodoAVL tmp) {
        tmp.setIzq(srr(tmp.getIzq()));;
        return srl(tmp);
    }

    NodoAVL drr(NodoAVL tmp) {
        tmp.setDer(srl(tmp.getDer()));
        return srr(tmp);
    }
	
	public void preorder(NodoAVL tmp) {
		if (tmp != null) {
			System.out.print(tmp.getImg().getId()+" ");
			preorder(tmp.getIzq());
			preorder(tmp.getDer());
		}
	}

	public void enorder(NodoAVL tmp) {
		if (tmp != null) {			
			enorder(tmp.getIzq());
			System.out.print(tmp.getImg().getId()+" ");
			enorder(tmp.getDer());
		}
	}
	
	public void postorder(NodoAVL tmp) {
		if (tmp != null) {			
			postorder(tmp.getIzq());
			postorder(tmp.getDer());
			System.out.print(tmp.getImg().getId()+" ");			
		}
	}

    public NodoAVL buscar(int dato){
        return buscar(this.root, dato);
    }

    private NodoAVL buscar(NodoAVL raiz, int dato){
        if (raiz ==  null) return null;
        else if (dato == raiz.getImg().getId()) return raiz;
        else if(dato < raiz.getImg().getId()) return buscar(raiz.getIzq(), dato);
        else return buscar(raiz.getDer(), dato);
    }
}
