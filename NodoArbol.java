/**
 *
 * @author Luis Angel Valencia Jantes
 * luisvalencia1810@gmail.com
 * GTID141
 */
public class NodoArbol {
        private int dato;  
     
    public NodoArbol hijoIzquierdo; 
    public NodoArbol hijoDerecho; 
 
    public NodoArbol(int valor) { 
        this.dato = valor; 
        this.hijoIzquierdo = null; 
        this.hijoDerecho = null; 
    } 

    public NodoArbol getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoArbol getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoIzquierdo(NodoArbol hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(NodoArbol hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
     
   
    // Getter para obtener el dato 
    public int getDato() { 
        return dato; 
    } 
 
    // Setter para modificar el dato (Si fuera necesario) 
    public void setDato(int nuevoDato) { 
        this.dato = nuevoDato; 
    } 
}
    

