/**
 *
 * @author Luis Angel Valencia Jantes
 * luisvalencia1810@gmail.com
 * GTID141
 */
public class PruebaArbol {
    public static void main(String[] args) {
        // 1. Crear una instancia del árbol
        ArbolBinario arbol = new ArbolBinario();

        // 2. Insertar los valores paso a paso (con duplicados ignorados)
        System.out.println("Insertando valores: 50, 30, 70, 30, 50, 20, 70");
        arbol.insertar(50); // raíz
        arbol.insertar(30); // izquierda de 50
        arbol.insertar(70); // derecha de 50
        arbol.insertar(30); // duplicado, se ignora
        arbol.insertar(50); // duplicado, se ignora
        arbol.insertar(20); // izquierda de 30
        arbol.insertar(70); // duplicado, se ignora

        // 3. Ejecutar el recorrido Inorden
        System.out.println("Recorrido Inorden esperado: 20 30 50 70");
        arbol.recorrerInorden();

        // 4. También puedes probar otros recorridos si ya los implementaste
        System.out.println("Recorrido Preorden esperado: 50 30 20 70");
        arbol.recorrerPreorden();

        System.out.println("Recorrido Postorden esperado: 20 30 70 50");
        arbol.recorrerPostorden();
    }
}
