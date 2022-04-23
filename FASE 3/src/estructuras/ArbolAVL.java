package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import nodos.NodoAVL;
import objetos.Imagen;

public class ArbolAVL {
    public NodoAVL raiz = null;
    String graphviz = "";
    String nodos = "";
    String relaciones = "";
    String capas = "";
    int totalImagenes = 0;
    int totalImgs = 0;
    private static final int ALT_DIFERENCIA=1;

	
    public void insertar(Imagen data){
        if (data==null){
            throw new IllegalArgumentException("Los datos están vacíos");
        }
         raiz= insertar(data, this.raiz);
    }

    private NodoAVL insertar(Imagen data, NodoAVL temporal){
        if (temporal==null){
            totalImgs++;
            return new NodoAVL(data,null,null);
        }
        
        if (data.getId() < temporal.getImg().getId()){
            temporal.setIzq(insertar(data,temporal.getIzq()));
        }else if (data.getId() > temporal.getImg().getId()){
            temporal.setDer(insertar(data,temporal.getDer()));
        }else {
            JOptionPane.showMessageDialog(null, "La imagen con el ID " + data.getId() + " ya existe.");
        }
        return balancear(temporal);
    }

    public int getTotalImg() {
        return totalImgs;
    }

    private NodoAVL rotacionDerecha(NodoAVL nodo){
        NodoAVL izq=nodo.getIzq();
        nodo.setIzq(izq.getDer());
        izq.setDer(nodo);
        nodo.setAlt(Math.max(altura(nodo.getIzq()),altura(nodo.getDer()))+1);
        izq.setAlt(Math.max(altura(nodo.getIzq()),nodo.getAlt())+1);
        return izq;
    }

    private NodoAVL rotacionIzquierda(NodoAVL nodo){
        NodoAVL der=nodo.getDer();
        nodo.setDer(der.getIzq());
        der.setIzq(nodo);
        nodo.setAlt(Math.max(altura(nodo.getIzq()),altura(nodo.getDer()))+1);
        der.setAlt(Math.max(nodo.getAlt(),altura(der.getDer()))+1);
        return der;
    }

    private NodoAVL dobleIzqDer(NodoAVL nodo){
        nodo.setIzq(rotacionIzquierda(nodo.getIzq()));
        return rotacionDerecha(nodo);
    }

    private NodoAVL dobleDerIzq(NodoAVL nodo){
        nodo.setDer(rotacionDerecha(nodo.getDer()));
        return rotacionIzquierda(nodo);
    }

    private NodoAVL balancear(NodoAVL temporal) {
        if (temporal==null){
            return null;
        }
        if (altura(temporal.getIzq())-altura(temporal.getDer())>ALT_DIFERENCIA){
            if (altura(temporal.getIzq().getIzq())>=altura(temporal.getIzq().getDer())){
                temporal=rotacionDerecha(temporal);
            }else {
                temporal=dobleIzqDer(temporal);
            }

        }else if (altura(temporal.getDer())-altura(temporal.getIzq())>ALT_DIFERENCIA){
            if (altura(temporal.getDer().getIzq())>altura(temporal.getDer().getDer())){
                temporal=dobleDerIzq(temporal);
            }else {
                temporal=rotacionIzquierda(temporal);
            }
        }
        temporal.setAlt(Math.max(altura(temporal.getIzq()),altura(temporal.getDer()))+1);
        return temporal;
    }
    
    private int altura(NodoAVL temporal){
        return temporal==null?-1:temporal.getAlt();
    }

    public void eliminar(int x)
    {
        raiz = eliminar( x, raiz );
    }
    
    private NodoAVL eliminar(int data, NodoAVL raiz){

        if (raiz==null){
            return null;
        }
        if (data < raiz.getImg().getId()){
            raiz.setIzq(eliminar(data,raiz.getIzq()));
        }else if (data > raiz.getImg().getId()){
            raiz.setDer(eliminar(data,raiz.getDer()));
        }else if (raiz.getIzq()!=null&&raiz.getDer()!=null){
            raiz.setImg(encontrarMenor(raiz.getDer()).getImg());
            raiz.setDer(eliminar(raiz.getImg().getId(),raiz.getDer()));
        }else {
            raiz=raiz.getIzq()==null?raiz.getDer():raiz.getIzq();
        }
        return balancear(raiz);
    }

    public NodoAVL encontrarMenor(NodoAVL raiz){
        while (raiz.getIzq()!=null){
            raiz=raiz.getIzq();
        }
        return raiz;
    }
    
    private void escribirArchivo(String ruta, String contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.write(contenido);
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(pw != null) {
                pw.close();
            }            
        }
    }

	public void generarAVL() {
        this.graphviz = this.nodos = this.relaciones = "";
        this.graphviz = "graph \"\" {\n" +
            "node [shape=box]\n";
        generarAVL(this.raiz);
        this.graphviz += this.nodos;
        this.graphviz += this.relaciones;
        this.graphviz += "\n}";
        // System.out.println(this.graphviz);
        escribirArchivo("./reporte.dot", this.graphviz);
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void generarAVL(NodoAVL raiz) {
        if (raiz != null) {            
            this.nodos += raiz.hashCode() + "[label=\"" + raiz.getImg().getId() + "\"];\n";
            if (raiz.getIzq() != null) {
                this.relaciones += raiz.hashCode() + " -- " + raiz.getIzq().hashCode() + ";\n";
            }
            if (raiz.getDer() != null) {
                this.relaciones += raiz.hashCode() + " -- " + raiz.getDer().hashCode() + ";\n";
            }
            generarAVL(raiz.getIzq());
            generarAVL(raiz.getDer());
        }
    }
    
    public void generarAVLCapa(int idImagen) {
        this.graphviz = this.nodos = this.relaciones = this.capas = "";
        this.graphviz = "graph \"\" {\n";
        generarAVLCapa(this.raiz, idImagen);
        this.graphviz += this.nodos;
        this.graphviz += this.relaciones;
        this.graphviz += this.capas;
        this.graphviz += "\n}";
        escribirArchivo("./reporte.dot", this.graphviz);
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void generarAVLCapa(NodoAVL raiz, int idImagen) {
        if (raiz != null) {            
            this.nodos += raiz.hashCode() + "[label=\"" + raiz.getImg().getId() + "\", shape=box];\n";
            if (idImagen == raiz.getImg().getId()) {
                this.capas = raiz.getImg().getCapas().generarABB(false, raiz);
            }
            if (raiz.getIzq() != null) {
                this.relaciones += raiz.hashCode() + " -- " + raiz.getIzq().hashCode() + ";\n";
            }
            if (raiz.getDer() != null) {
                this.relaciones += raiz.hashCode() + " -- " + raiz.getDer().hashCode() + ";\n";
            }
            generarAVLCapa(raiz.getIzq(), idImagen);
            generarAVLCapa(raiz.getDer(), idImagen);
        }
    }

    public void preorder(NodoAVL tmp) {
		if (tmp != null) {
			preorder(tmp.getIzq());
			preorder(tmp.getDer());
		}
	}

	public void enorder(NodoAVL tmp) {
		if (tmp != null) {			
			enorder(tmp.getIzq());
			enorder(tmp.getDer());
		}
	}
	
	public void postorder(NodoAVL tmp) {
		if (tmp != null) {			
			postorder(tmp.getIzq());
			postorder(tmp.getDer());
		}
	}

    public NodoAVL buscar(int dato){
        return buscar(this.raiz, dato);
    }

    private NodoAVL buscar(NodoAVL raiz, int dato){
        if (raiz ==  null) return null;
        else if (dato == raiz.getImg().getId()) return raiz;
        else if(dato < raiz.getImg().getId()) return buscar(raiz.getIzq(), dato);
        else return buscar(raiz.getDer(), dato);
    }

    public ListaSimple top5MasCapas() {
        ListaSimple capas = new ListaSimple();
        this.top5MasCapas(this.raiz, capas);
        capas.ordenarMasCapas();
        capas.visualizar();
        return capas;
    }

    public void top5MasCapas(NodoAVL raiz, ListaSimple capas) {
        if (raiz != null) {
			// System.out.print(raiz.getImg().getId()+" ");
            capas.insertarAlFinal(raiz.getImg());
			top5MasCapas(raiz.getIzq(), capas);
			top5MasCapas(raiz.getDer(), capas);
		}
    }

    public void listarImagenes(DefaultTableModel modelo, JLabel label) {
        this.totalImagenes = 0;
        this.listarImagenes(this.raiz, modelo);
        label.setText("Total de imagenes: " + this.totalImagenes);
    }

    private void listarImagenes(NodoAVL tmp, DefaultTableModel modelo) {
		if (tmp != null) {
            this.totalImagenes++;
            Object[] datosPFila =  {tmp.getImg().getId(), tmp.getImg().getCapas().devolverCapas()};
            modelo.addRow(datosPFila);
			listarImagenes(tmp.getIzq(), modelo);
			listarImagenes(tmp.getDer(), modelo);
		}
	}


}