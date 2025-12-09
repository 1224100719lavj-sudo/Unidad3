
**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
import java.util.*;


import java.util.Objects;


public class Mascota {
    private String id;           // Identificador único (chip/placa)
    private String nombre;       // Nombre de la mascota
    private String tipo;         // Tipo: Perro o Gato
    private String raza;         // Raza de la mascota
    private int edad;            // Edad en años
    
    /**
     * Constructor completo de la clase Mascota.
     * 
     * @param id Identificador único de la mascota
     * @param nombre Nombre de la mascota
     * @param tipo Tipo de mascota (Perro/Gato)
     * @param raza Raza de la mascota
     * @param edad Edad en años
     */
    public Mascota(String id, String nombre, String tipo, String raza, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
    }
    
    // === GETTERS Y SETTERS ===
    
    /**
     * Obtiene el ID de la mascota.
     * @return ID único de la mascota
     */
    public String getId() {
        return id;
    }
    
    /**
     * Establece el ID de la mascota.
     * @param id Nuevo ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obtiene el nombre de la mascota.
     * @return Nombre de la mascota
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre de la mascota.
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el tipo de mascota.
     * @return Tipo (Perro/Gato)
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Establece el tipo de mascota.
     * @param tipo Nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Obtiene la raza de la mascota.
     * @return Raza de la mascota
     */
    public String getRaza() {
        return raza;
    }
    
    /**
     * Establece la raza de la mascota.
     * @param raza Nueva raza
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    /**
     * Obtiene la edad de la mascota.
     * @return Edad en años
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * Establece la edad de la mascota.
     * @param edad Nueva edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Compara dos mascotas por su ID.
     * Necesario para usar correctamente en conjuntos (Set).
     * 
     * @param obj Objeto a comparar
     * @return true si tienen el mismo ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mascota mascota = (Mascota) obj;
        return Objects.equals(id, mascota.id);
    }
    
    /**
     * Genera código hash basado en el ID.
     * Necesario para el correcto funcionamiento en HashSet.
     * 
     * @return Código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    /**
     * Representación en texto de la mascota.
     * 
     * @return String con información de la mascota
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%s, %d años)", id, nombre, raza, edad);
    }
}