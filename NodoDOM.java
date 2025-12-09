package com.mycompany.simuladordom;

/**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 */
mport java.util.ArrayList;
import java.util.List;

/**
 * Representa un nodo del árbol DOM
 * Cada nodo tiene una etiqueta HTML, atributos opcionales y nodos hijos
 */
public class NodoDOM {
    private String etiqueta;        // Ejemplo: "div", "p", "h1"
    private String contenido;       // Contenido de texto del nodo
    private List<NodoDOM> hijos;    // Lista de nodos hijos
    private NodoDOM padre;          // Referencia al nodo padre
    private String atributos;       // Atributos HTML (class, id, etc.)
    
    /**
     * Constructor principal
     * @param etiqueta Nombre de la etiqueta HTML
     * @param contenido Texto dentro del elemento
     */
    public NodoDOM(String etiqueta, String contenido) {
        this.etiqueta = etiqueta;
        this.contenido = contenido;
        this.hijos = new ArrayList<>();
        this.atributos = "";
    }
    
    /**
     * Constructor con atributos
     */
    public NodoDOM(String etiqueta, String contenido, String atributos) {
        this(etiqueta, contenido);
        this.atributos = atributos;
    }
    
    // Método para agregar un hijo al nodo actual
    public void agregarHijo(NodoDOM hijo) {
        hijo.setPadre(this);
        this.hijos.add(hijo);
    }
    
    // Método para eliminar un hijo
    public boolean eliminarHijo(NodoDOM hijo) {
        return this.hijos.remove(hijo);
    }
    
    // Genera el HTML completo del nodo y sus hijos
    public String generarHTML(int nivel) {
        StringBuilder html = new StringBuilder();
        String indentacion = "  ".repeat(nivel);
        
        // Etiqueta de apertura
        html.append(indentacion).append("<").append(etiqueta);
        if (!atributos.isEmpty()) {
            html.append(" ").append(atributos);
        }
        html.append(">");
        
        // Si hay contenido o hijos
        if (!contenido.isEmpty() || !hijos.isEmpty()) {
            if (!contenido.isEmpty()) {
                html.append(contenido);
            }
            
            // Agregar hijos recursivamente
            if (!hijos.isEmpty()) {
                html.append("\n");
                for (NodoDOM hijo : hijos) {
                    html.append(hijo.generarHTML(nivel + 1));
                }
                html.append(indentacion);
            }
        }
        
        // Etiqueta de cierre
        html.append("</").append(etiqueta).append(">\n");
        
        return html.toString();
    }
    
    // Representación en el árbol visual
    @Override
    public String toString() {
        if (!contenido.isEmpty()) {
            return "<" + etiqueta + "> " + contenido;
        }
        return "<" + etiqueta + ">";
    }
    
    // Getters y Setters
    public String getEtiqueta() { return etiqueta; }
    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }
    
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    
    public List<NodoDOM> getHijos() { return hijos; }
    
    public NodoDOM getPadre() { return padre; }
    public void setPadre(NodoDOM padre) { this.padre = padre; }
    
    public String getAtributos() { return atributos; }
    public void setAtributos(String atributos) { this.atributos = atributos; }
}