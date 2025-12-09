
**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
import java.util.*;


public class GestorMascotas {
    
    // === 5 CONJUNTOS PRINCIPALES ===
    
    /**
     * Conjunto de perros registrados.
     */
    private Set<Mascota> perros;
    
    /**
     * Conjunto de gatos registrados.
     */
    private Set<Mascota> gatos;
    
    /**
     * Conjunto de mascotas vacunadas.
     */
    private Set<Mascota> mascotasVacunadas;
    
    /**
     * Conjunto de mascotas adoptadas.
     */
    private Set<Mascota> mascotasAdoptadas;
    
    /**
     * Conjunto de razas disponibles.
     */
    private Set<String> razas;
    
    /**
     * Constructor que inicializa todos los conjuntos.
     */
    public GestorMascotas() {
        // Inicializar conjuntos vacíos
        this.perros = new HashSet<>();
        this.gatos = new HashSet<>();
        this.mascotasVacunadas = new HashSet<>();
        this.mascotasAdoptadas = new HashSet<>();
        this.razas = new TreeSet<>(); // TreeSet para orden alfabético
        
        // Inicializar razas predefinidas
        inicializarRazas();
    }
    
    /**
     * Inicializa el conjunto de razas con valores comunes.
     */
    private void inicializarRazas() {
        // Razas de perros
        razas.add("Labrador");
        razas.add("Chihuahua");
        razas.add("Pastor Alemán");
        razas.add("Golden Retriever");
        razas.add("Bulldog");
        razas.add("Mestizo");
        
        // Razas de gatos
        razas.add("Persa");
        razas.add("Siamés");
        razas.add("Angora");
        razas.add("Común Europeo");
        razas.add("Maine Coon");
    }
    
    // =========================================================================
    // OPERACIÓN 1: AGREGAR (add) - Agregar elementos al conjunto
    // =========================================================================
    
    /**
     * Registra una nueva mascota en el sistema.
     * OPERACIÓN DE CONJUNTO: add()
     * 
     * @param mascota Mascota a registrar
     * @return true si se agregó correctamente, false si ya existía
     */
    public boolean registrarMascota(Mascota mascota) {
        if (mascota == null) return false;
        
        // Agregar la raza al conjunto de razas
        razas.add(mascota.getRaza());
        
        // Agregar al conjunto correspondiente según el tipo
        if (mascota.getTipo().equalsIgnoreCase("Perro")) {
            return perros.add(mascota);
        } else if (mascota.getTipo().equalsIgnoreCase("Gato")) {
            return gatos.add(mascota);
        }
        
        return false;
    }
    
    /**
     * Marca una mascota como vacunada.
     * OPERACIÓN DE CONJUNTO: add()
     * 
     * @param id ID de la mascota
     * @return true si se marcó como vacunada
     */
    public boolean marcarComoVacunada(String id) {
        Mascota mascota = buscarMascotaPorId(id);
        if (mascota != null) {
            return mascotasVacunadas.add(mascota);
        }
        return false;
    }
    
    /**
     * Marca una mascota como adoptada.
     * OPERACIÓN DE CONJUNTO: add()
     * 
     * @param id ID de la mascota
     * @return true si se marcó como adoptada
     */
    public boolean marcarComoAdoptada(String id) {
        Mascota mascota = buscarMascotaPorId(id);
        if (mascota != null) {
            return mascotasAdoptadas.add(mascota);
        }
        return false;
    }
    
    // =========================================================================
    // OPERACIÓN 2: ELIMINAR (remove) - Eliminar elementos del conjunto
    // =========================================================================
    
    /**
     * Elimina una mascota del registro.
     * OPERACIÓN DE CONJUNTO: remove()
     * 
     * @param id ID de la mascota a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarMascota(String id) {
        Mascota mascota = buscarMascotaPorId(id);
        if (mascota == null) return false;
        
        // Eliminar de todos los conjuntos
        mascotasVacunadas.remove(mascota);
        mascotasAdoptadas.remove(mascota);
        
        if (mascota.getTipo().equalsIgnoreCase("Perro")) {
            return perros.remove(mascota);
        } else if (mascota.getTipo().equalsIgnoreCase("Gato")) {
            return gatos.remove(mascota);
        }
        
        return false;
    }
    
    // =========================================================================
    // OPERACIÓN 3: UNIÓN (addAll) - Combinar conjuntos
    // =========================================================================
    
    /**
     * Obtiene todas las mascotas registradas (perros + gatos).
     * OPERACIÓN DE CONJUNTO: Unión (A ∪ B)
     * 
     * @return Conjunto con todas las mascotas
     */
    public Set<Mascota> obtenerTodasLasMascotas() {
        Set<Mascota> todas = new HashSet<>();
        
        // Unión: agregar todos los perros
        todas.addAll(perros);
        
        // Unión: agregar todos los gatos
        todas.addAll(gatos);
        
        return todas;
    }
    
    // =========================================================================
    // OPERACIÓN 4: INTERSECCIÓN (retainAll) - Elementos comunes
    // =========================================================================
    
    /**
     * Obtiene perros que están vacunados.
     * OPERACIÓN DE CONJUNTO: Intersección (perros ∩ vacunadas)
     * 
     * @return Conjunto de perros vacunados
     */
    public Set<Mascota> obtenerPerrosVacunados() {
        Set<Mascota> perrosVacunados = new HashSet<>(perros);
        
        // Intersección: mantener solo los que están vacunados
        perrosVacunados.retainAll(mascotasVacunadas);
        
        return perrosVacunados;
    }
    
    /**
     * Obtiene gatos que están vacunados.
     * OPERACIÓN DE CONJUNTO: Intersección (gatos ∩ vacunadas)
     * 
     * @return Conjunto de gatos vacunados
     */
    public Set<Mascota> obtenerGatosVacunados() {
        Set<Mascota> gatosVacunados = new HashSet<>(gatos);
        
        // Intersección: mantener solo los que están vacunados
        gatosVacunados.retainAll(mascotasVacunadas);
        
        return gatosVacunados;
    }
    
    /**
     * Obtiene mascotas que están vacunadas Y adoptadas.
     * OPERACIÓN DE CONJUNTO: Intersección (vacunadas ∩ adoptadas)
     * 
     * @return Conjunto de mascotas vacunadas y adoptadas
     */
    public Set<Mascota> obtenerMascotasVacunadasYAdoptadas() {
        Set<Mascota> resultado = new HashSet<>(mascotasVacunadas);
        
        // Intersección: mantener solo las que están adoptadas
        resultado.retainAll(mascotasAdoptadas);
        
        return resultado;
    }
    
    // =========================================================================
    // OPERACIÓN 5: DIFERENCIA (removeAll) - Elementos no comunes
    // =========================================================================
    
    /**
     * Obtiene perros que NO están vacunados.
     * OPERACIÓN DE CONJUNTO: Diferencia (perros - vacunadas)
     * 
     * @return Conjunto de perros sin vacunar
     */
    public Set<Mascota> obtenerPerrosSinVacunar() {
        Set<Mascota> perrosSinVacunar = new HashSet<>(perros);
        
        // Diferencia: eliminar los que están vacunados
        perrosSinVacunar.removeAll(mascotasVacunadas);
        
        return perrosSinVacunar;
    }
    
    /**
     * Obtiene mascotas disponibles para adopción (no adoptadas).
     * OPERACIÓN DE CONJUNTO: Diferencia (todas - adoptadas)
     * 
     * @return Conjunto de mascotas disponibles
     */
    public Set<Mascota> obtenerMascotasDisponibles() {
        Set<Mascota> disponibles = obtenerTodasLasMascotas();
        
        // Diferencia: eliminar las adoptadas
        disponibles.removeAll(mascotasAdoptadas);
        
        return disponibles;
    }
    
    // =========================================================================
    // OPERACIÓN 6: CONTIENE (contains) - Verificar pertenencia
    // =========================================================================
    
    /**
     * Verifica si una mascota está vacunada.
     * OPERACIÓN DE CONJUNTO: contains()
     * 
     * @param id ID de la mascota
     * @return true si está vacunada
     */
    public boolean estaVacunada(String id) {
        Mascota mascota = buscarMascotaPorId(id);
        if (mascota == null) return false;
        
        return mascotasVacunadas.contains(mascota);
    }
    
    /**
     * Verifica si una mascota está adoptada.
     * OPERACIÓN DE CONJUNTO: contains()
     * 
     * @param id ID de la mascota
     * @return true si está adoptada
     */
    public boolean estaAdoptada(String id) {
        Mascota mascota = buscarMascotaPorId(id);
        if (mascota == null) return false;
        
        return mascotasAdoptadas.contains(mascota);
    }
    
    // =========================================================================
    // OPERACIÓN 7: TAMAÑO (size) - Obtener cantidad de elementos
    // =========================================================================
    
    /**
     * Obtiene estadísticas usando size().
     * OPERACIÓN DE CONJUNTO: size()
     * 
     * @return Mapa con todas las estadísticas
     */
    public Map<String, Integer> obtenerEstadisticas() {
        Map<String, Integer> stats = new LinkedHashMap<>();
        
        stats.put("Total Perros", perros.size());
        stats.put("Total Gatos", gatos.size());
        stats.put("Total Mascotas", obtenerTodasLasMascotas().size());
        stats.put("Mascotas Vacunadas", mascotasVacunadas.size());
        stats.put("Mascotas Adoptadas", mascotasAdoptadas.size());
        stats.put("Mascotas Disponibles", obtenerMascotasDisponibles().size());
        stats.put("Razas Registradas", razas.size());
        
        return stats;
    }
    
    // =========================================================================
    // OPERACIÓN 8: VACÍO (isEmpty) - Verificar si está vacío
    // =========================================================================
    
    /**
     * Verifica si el sistema está vacío.
     * OPERACIÓN DE CONJUNTO: isEmpty()
     * 
     * @return true si no hay mascotas registradas
     */
    public boolean sistemaEstaVacio() {
        return perros.isEmpty() && gatos.isEmpty();
    }
    
    /**
     * Verifica si hay mascotas sin vacunar.
     * OPERACIÓN DE CONJUNTO: isEmpty()
     * 
     * @return true si hay mascotas sin vacunar
     */
    public boolean hayMascotasSinVacunar() {
        return !obtenerPerrosSinVacunar().isEmpty() || 
               !obtenerGatosSinVacunar().isEmpty();
    }
    
    /**
     * Obtiene gatos sin vacunar.
     * @return Conjunto de gatos sin vacunar
     */
    private Set<Mascota> obtenerGatosSinVacunar() {
        Set<Mascota> gatosSinVacunar = new HashSet<>(gatos);
        gatosSinVacunar.removeAll(mascotasVacunadas);
        return gatosSinVacunar;
    }
    
    // =========================================================================
    // MÉTODOS AUXILIARES
    // =========================================================================
    
    /**
     * Busca una mascota por su ID en todos los conjuntos.
     * 
     * @param id ID de la mascota
     * @return Mascota encontrada o null
     */
    private Mascota buscarMascotaPorId(String id) {
        // Buscar en perros
        for (Mascota perro : perros) {
            if (perro.getId().equals(id)) {
                return perro;
            }
        }
        
        // Buscar en gatos
        for (Mascota gato : gatos) {
            if (gato.getId().equals(id)) {
                return gato;
            }
        }
        
        return null;
    }
    
    // =========================================================================
    // GETTERS
    // =========================================================================
    
    public Set<Mascota> getPerros() {
        return new HashSet<>(perros);
    }
    
    public Set<Mascota> getGatos() {
        return new HashSet<>(gatos);
    }
    
    public Set<Mascota> getMascotasVacunadas() {
        return new HashSet<>(mascotasVacunadas);
    }
    
    public Set<Mascota> getMascotasAdoptadas() {
        return new HashSet<>(mascotasAdoptadas);
    }
    
    public Set<String> getRazas() {
        return new TreeSet<>(razas);
    }
}