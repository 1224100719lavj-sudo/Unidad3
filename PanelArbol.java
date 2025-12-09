
**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 */import javax.swing.*;
import javax.swing.*;
import java.awt.*;

/**
 * Panel personalizado para dibujar el árbol binario de búsqueda
 */
public class PanelArbol extends JPanel {
    private ArbolBinarioBusqueda arbol;
    private Nodo nodoResaltado;
    private final int RADIO_NODO = 25;
    private final int SEPARACION_HORIZONTAL = 60;
    private final int SEPARACION_VERTICAL = 80;
    
    public PanelArbol() {
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(800, 500));
    }
    
    public void setArbol(ArbolBinarioBusqueda arbol) {
        this.arbol = arbol;
        repaint();
    }
    
    public void setNodoResaltado(Nodo nodo) {
        this.nodoResaltado = nodo;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (arbol != null && arbol.getRaiz() != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Calcular posiciones de los nodos
            calcularPosiciones(arbol.getRaiz(), getWidth() / 2, 60, getWidth() / 4);
            
            // Dibujar líneas primero
            dibujarLineas(g2d, arbol.getRaiz());
            
            // Dibujar nodos encima
            dibujarNodos(g2d, arbol.getRaiz());
        }
    }
    
    /**
     * Calcula las posiciones de los nodos en el panel
     */
    private void calcularPosiciones(Nodo nodo, int x, int y, int separacion) {
        if (nodo == null) return;
        
        nodo.setX(x);
        nodo.setY(y);
        
        if (nodo.getIzquierdo() != null) {
            calcularPosiciones(nodo.getIzquierdo(), 
                             x - separacion, 
                             y + SEPARACION_VERTICAL, 
                             separacion / 2);
        }
        
        if (nodo.getDerecho() != null) {
            calcularPosiciones(nodo.getDerecho(), 
                             x + separacion, 
                             y + SEPARACION_VERTICAL, 
                             separacion / 2);
        }
    }
    
    /**
     * Dibuja las líneas que conectan los nodos
     */
    private void dibujarLineas(Graphics2D g2d, Nodo nodo) {
        if (nodo == null) return;
        
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(100, 100, 100));
        
        if (nodo.getIzquierdo() != null) {
            g2d.drawLine(nodo.getX(), nodo.getY(), 
                        nodo.getIzquierdo().getX(), 
                        nodo.getIzquierdo().getY());
            dibujarLineas(g2d, nodo.getIzquierdo());
        }
        
        if (nodo.getDerecho() != null) {
            g2d.drawLine(nodo.getX(), nodo.getY(), 
                        nodo.getDerecho().getX(), 
                        nodo.getDerecho().getY());
            dibujarLineas(g2d, nodo.getDerecho());
        }
    }
    
    /**
     * Dibuja los nodos del árbol
     */
    private void dibujarNodos(Graphics2D g2d, Nodo nodo) {
        if (nodo == null) return;
        
        // Dibujar hijos primero
        if (nodo.getIzquierdo() != null) {
            dibujarNodos(g2d, nodo.getIzquierdo());
        }
        if (nodo.getDerecho() != null) {
            dibujarNodos(g2d, nodo.getDerecho());
        }
        
        // Determinar color del nodo
        Color colorNodo;
        if (nodo == nodoResaltado) {
            colorNodo = new Color(255, 215, 0); // Amarillo/dorado para resaltar
        } else {
            colorNodo = new Color(70, 130, 220); // Azul
        }
        
        // Dibujar círculo del nodo
        g2d.setColor(colorNodo);
        g2d.fillOval(nodo.getX() - RADIO_NODO, 
                    nodo.getY() - RADIO_NODO, 
                    RADIO_NODO * 2, 
                    RADIO_NODO * 2);
        
        // Dibujar borde del nodo
        g2d.setColor(new Color(40, 80, 150));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(nodo.getX() - RADIO_NODO, 
                    nodo.getY() - RADIO_NODO, 
                    RADIO_NODO * 2, 
                    RADIO_NODO * 2);
        
        // Dibujar valor del nodo
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g2d.getFontMetrics();
        String valor = String.valueOf(nodo.getValor());
        int anchoTexto = fm.stringWidth(valor);
        int altoTexto = fm.getAscent();
        g2d.drawString(valor, 
                      nodo.getX() - anchoTexto / 2, 
                      nodo.getY() + altoTexto / 3);
    }
}
