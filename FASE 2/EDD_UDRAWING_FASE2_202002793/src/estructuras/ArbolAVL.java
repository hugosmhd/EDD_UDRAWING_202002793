package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

import javafx.scene.image.Image;
import nodos.NodoABB;
import nodos.NodoAVL;
import objetos.Imagen;

public class ArbolAVL {
    public NodoAVL root = null;
    String graphviz = "";
    String nodos = "";
    String relaciones = "";
    String capas = "";
    private static final int HEIGHT_DIFFERENCE=1;

	
    public void insert(Imagen data){
        if (data==null){
            throw new IllegalArgumentException("Los datos están vacíos");
        }
         root= insert(data, this.root);
    }

    private NodoAVL insert(Imagen data,NodoAVL t){
        if (t==null){
            return new NodoAVL(data,null,null);
        }
        
        if (data.getId() < t.getImg().getId()){
            t.setIzq(insert(data,t.getIzq()));
        }else if (data.getId() > t.getImg().getId()){
            t.setDer(insert(data,t.getDer()));
        }else {
            ;
        }
        return balance(t);
    }

    private NodoAVL rotateRight(NodoAVL node){
        NodoAVL left=node.getIzq();
        node.setIzq(left.getDer());
        left.setDer(node);
        node.setAlt(Math.max(height(node.getIzq()),height(node.getDer()))+1);
        left.setAlt(Math.max(height(node.getIzq()),node.getAlt())+1);
        return left;
    }

    private NodoAVL rotateLeft(NodoAVL node){
        NodoAVL right=node.getDer();
        node.setDer(right.getIzq());
        right.setIzq(node);
        node.setAlt(Math.max(height(node.getIzq()),height(node.getDer()))+1);
        right.setAlt(Math.max(node.getAlt(),height(right.getDer()))+1);
        return right;
    }

    private NodoAVL doubleLeftAndRight(NodoAVL node){
        node.setIzq(rotateLeft(node.getIzq()));
        return rotateRight(node);
    }

    private NodoAVL doubleRightAndLeft(NodoAVL node){
        node.setDer(rotateRight(node.getDer()));
        return rotateLeft(node);
    }

    private NodoAVL balance(NodoAVL t) {
        if (t==null){
            return null;
        }
        if (height(t.getIzq())-height(t.getDer())>HEIGHT_DIFFERENCE){
            // arriba a la izquierda
            if (height(t.getIzq().getIzq())>=height(t.getIzq().getDer())){
                 // La altura del subárbol izquierdo del subárbol izquierdo
                // Este es el caso 1, inserte un elemento en el subárbol izquierdo del subárbol izquierdo y gírelo directamente
                t=rotateRight(t);
            }else {
                // La altura del subárbol derecho del subárbol izquierdo
                // Este es el caso 2, un elemento se inserta en el subárbol derecho del subárbol izquierdo, primero gira a la izquierda y luego a la derecha
                t=doubleLeftAndRight(t);
            }

        }else if (height(t.getDer())-height(t.getIzq())>HEIGHT_DIFFERENCE){
            // Alta derecha
            if (height(t.getDer().getIzq())>height(t.getDer().getDer())){
                // El subárbol izquierdo del subárbol derecho es más alto
                // Este es el caso 3, un elemento se inserta en el subárbol izquierdo del subárbol derecho, primero gira a la derecha y luego a la izquierda
                t=doubleRightAndLeft(t);
            }else {
                // La altura del subárbol derecho del subárbol derecho
                // Este es el caso 4, se inserta un elemento en el subárbol derecho del subárbol derecho y se gira directamente hacia la izquierda
                t=rotateLeft(t);
            }
        }
        // Recalcula la altura del nodo
        t.setAlt(Math.max(height(t.getIzq()),height(t.getDer()))+1);
        return t;
    }
     private int height(NodoAVL t){
        return t==null?-1:t.getAlt();
    }

    public void remove(int x)
    {
        root = remove( x, root );
    }
    private NodoAVL remove(int data, NodoAVL root){

        if (root==null){
            return null;
        }
        if (data < root.getImg().getId()){
            root.setIzq(remove(data,root.getIzq()));
        }else if (data > root.getImg().getId()){
            root.setDer(remove(data,root.getDer()));
        }else if (root.getIzq()!=null&&root.getDer()!=null){
            // El nodo a eliminar tiene dos hijos
            root.setImg(findMin(root.getDer()).getImg());
            root.setDer(remove(root.getImg().getId(),root.getDer()));
        }else {
            // El nodo a eliminar no tiene o tiene un hijo
            root=root.getIzq()==null?root.getDer():root.getIzq();
        }
        // reequilibrar el árbol
        return balance(root);
    }

    public NodoAVL findMin(NodoAVL raiz){
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
        generarAVL(this.root);
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
            System.out.print(raiz.getImg().getId()+" ");
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
        generarAVLCapa(this.root, idImagen);
        this.graphviz += this.nodos;
        this.graphviz += this.relaciones;
        this.graphviz += this.capas;
        System.out.println(this.capas);
        this.graphviz += "\n}";
        System.out.println(this.graphviz);
        escribirArchivo("./reporte.dot", this.graphviz);
        try {
            Runtime.getRuntime().exec("dot" + " -Tpng " + "./reporte" + ".dot -o " + "reporte" + ".png ").waitFor();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void generarAVLCapa(NodoAVL raiz, int idImagen) {
        if (raiz != null) {            
            System.out.print(raiz.getImg().getId()+" ");
            this.nodos += raiz.hashCode() + "[label=\"" + raiz.getImg().getId() + "\", shape=box];\n";
            if (idImagen == raiz.getImg().getId()) {
                System.out.println("Encontro la imagen");
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