
**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 
public class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;
    
    // Coordenadas para dibujar el nodo
    int x, y;
    
    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    public int getValor() {
        return valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public Nodo getIzquierdo() {
        return izquierdo;
    }
    
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public Nodo getDerecho() {
        return derecho;
    }
    
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
