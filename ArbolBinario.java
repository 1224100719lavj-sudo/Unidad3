/**
 *
 * @author Luis Angel Valencia Jantes
 * luisvalencia1810@gmail.com
 * GTID141
 */
public class ArbolBinario {
    private NodoArbol raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    // Método público de inserción
    public void insertar(int valor) {
        this.raiz = insertarRecursivo(this.raiz, valor);
    }

    // Método privado recursivo
    private NodoArbol insertarRecursivo(NodoArbol actual, int valor) {
        if (actual == null) {
            return new NodoArbol(valor); // caso del cual partimos del nodo vacio 
        }
        if (valor < actual.getDato()) {
            actual.hijoIzquierdo = insertarRecursivo(actual.hijoIzquierdo, valor);
        } else if (valor > actual.getDato()) {
            actual.hijoDerecho = insertarRecursivo(actual.hijoDerecho, valor);
        }
        // Si el valor ya existe lo ignoramos e imprimimos el actual  
        return actual;
    }

    // Método público de recorrido Inorden
    public void recorrerInorden() {
        System.out.print("Recorrido Inorden: ");
        recorrerInordenRecursivo(this.raiz);
        System.out.println();
    }

    // Método privado recursivo
    private void recorrerInordenRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            recorrerInordenRecursivo(nodo.hijoIzquierdo); // recorrido con direccion a la Izquierda
            System.out.print(nodo.getDato() + " ");       // Raíz
            recorrerInordenRecursivo(nodo.hijoDerecho);  // recorrido con direccion a la Derecha
        }
    }

    // Método público de recorrido Preorden
    public void recorrerPreorden() {
        System.out.print("Recorrido Preorden: ");
        recorrerPreordenRecursivo(this.raiz);
        System.out.println();
    }

    // Método privado recursivo
    private void recorrerPreordenRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");       // Raíz
            recorrerPreordenRecursivo(nodo.hijoIzquierdo); // recorrido con direccion a la Izquierda
            recorrerPreordenRecursivo(nodo.hijoDerecho);  // recorrido con direccion a la Derecha
        }
    }

    // Método público de recorrido Postorden
    public void recorrerPostorden() {
        System.out.print("Recorrido Postorden: ");
        recorrerPostordenRecursivo(this.raiz);
        System.out.println();
    }

    // Método privado recursivo
    private void recorrerPostordenRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            recorrerPostordenRecursivo(nodo.hijoIzquierdo); // recorrido con direccion a la Izquierda
            recorrerPostordenRecursivo(nodo.hijoDerecho);  // recorrido con direccion a la Derecha
            System.out.print(nodo.getDato() + " ");        // Raíz
        }
    }
}


