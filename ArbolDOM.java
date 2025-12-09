package com.mycompany.simuladordom;

/**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 */
public class ArbolDOM {
    private final NodoDOM raiz;
    
    /**
     * Constructor: crea un árbol con una estructura HTML básica
     */
    public ArbolDOM() {
        // Crear estructura HTML básica
        raiz = new NodoDOM("html", "");
        
        NodoDOM head = new NodoDOM("head", "");
        NodoDOM title = new NodoDOM("title", "Mi Página Web");
        head.agregarHijo(title);
        
        NodoDOM body = new NodoDOM("body", "");
        raiz.agregarHijo(head);
        raiz.agregarHijo(body);
    }
    
    /**
     * Obtiene el nodo raíz del árbol
     */
    public NodoDOM getRaiz() {
        return raiz;
    }
    
    /**
     * Genera el HTML completo del documento
     * @return 
     */
    public String generarHTMLCompleto() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append(raiz.generarHTML(0));
        return html.toString();
    }
    
    /**
     * Busca un nodo en el árbol por su contenido
     * @param nodoActual
     * @param criterio
     * @return 
     */
    public NodoDOM buscarNodo(NodoDOM nodoActual, String criterio) {
        if (nodoActual.toString().contains(criterio)) {
            return nodoActual;
        }
        
        for (NodoDOM hijo : nodoActual.getHijos()) {
            NodoDOM encontrado = buscarNodo(hijo, criterio);
            if (encontrado != null) {
                return encontrado;
            }
        }
        
        return null;
    }
}
