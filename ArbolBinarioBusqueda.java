
**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 */import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa un Árbol Binario de Búsqueda (ABB)
 */
public class ArbolBinarioBusqueda {
    private Nodo raiz;
    
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }
    
    public Nodo getRaiz() {
        return raiz;
    }
    
    /**
     * Inserta un nuevo valor en el árbol
     */
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }
    
    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }
        
        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), valor));
        }
        
        return nodo;
    }
    
    /**
     * Busca un valor en el árbol
     */
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }
    
    private boolean buscarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return false;
        }
        
        if (valor == nodo.getValor()) {
            return true;
        }
        
        if (valor < nodo.getValor()) {
            return buscarRecursivo(nodo.getIzquierdo(), valor);
        } else {
            return buscarRecursivo(nodo.getDerecho(), valor);
        }
    }
    
    /**
     * Elimina un valor del árbol
     */
    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }
    
    private Nodo eliminarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return null;
        }
        
        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), valor));
        } else {
            // Caso 1: Nodo sin hijos
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }
            
            // Caso 2: Nodo con un hijo
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            
            // Caso 3: Nodo con dos hijos
            int menorValor = encontrarMinimo(nodo.getDerecho());
            nodo.setValor(menorValor);
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), menorValor));
        }
        
        return nodo;
    }
    
    private int encontrarMinimo(Nodo nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo.getValor();
    }
    
    /**
     * Recorrido InOrden (Izquierda - Raíz - Derecha)
     */
    public List<Integer> recorridoInOrden() {
        List<Integer> resultado = new ArrayList<>();
        inOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    private void inOrdenRecursivo(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.getIzquierdo(), resultado);
            resultado.add(nodo.getValor());
            inOrdenRecursivo(nodo.getDerecho(), resultado);
        }
    }
    
    /**
     * Recorrido PreOrden (Raíz - Izquierda - Derecha)
     */
    public List<Integer> recorridoPreOrden() {
        List<Integer> resultado = new ArrayList<>();
        preOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    private void preOrdenRecursivo(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            resultado.add(nodo.getValor());
            preOrdenRecursivo(nodo.getIzquierdo(), resultado);
            preOrdenRecursivo(nodo.getDerecho(), resultado);
        }
    }
    
    /**
     * Recorrido PostOrden (Izquierda - Derecha - Raíz)
     */
    public List<Integer> recorridoPostOrden() {
        List<Integer> resultado = new ArrayList<>();
        postOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    private void postOrdenRecursivo(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.getIzquierdo(), resultado);
            postOrdenRecursivo(nodo.getDerecho(), resultado);
            resultado.add(nodo.getValor());
        }
    }
    
    /**
     * Limpia el árbol
     */
    public void limpiar() {
        raiz = null;
    }
    
    /**
     * Verifica si el árbol está vacío
     */
    public boolean estaVacio() {
        return raiz == null;
    }
}
