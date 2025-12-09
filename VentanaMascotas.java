
**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;


public class VentanaMascotas extends JFrame {
    
    // Gestor de mascotas con la lógica de conjuntos
    private final GestorMascotas gestor;
    
    // Componentes de la interfaz
    private JTabbedPane tabbedPane;
    
    // === TAB 1: REGISTRO DE MASCOTAS ===
    private JTextField txtId, txtNombre, txtEdad;
    private JComboBox<String> cmbTipo, cmbRaza;
    private JButton btnRegistrar, btnEliminar;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    
    // === TAB 2: OPERACIONES DE CONJUNTOS ===
    private JComboBox<String> cmbOperaciones;
    private JButton btnEjecutar;
    private JTextArea txtResultado;
    
    // === TAB 3: ESTADÍSTICAS ===
    private JTextArea txtEstadisticas;
    private JButton btnActualizar;
    
    /**
     * Constructor de la ventana principal.
     */
    public VentanaMascotas() {
        gestor = new GestorMascotas();
        initComponents();
        cargarDatosEjemplo();
        actualizarTabla();
        setLocationRelativeTo(null);
    }
    
    /**
     * Inicializa todos los componentes de la interfaz gráfica.
     */
    private void initComponents() {
        // Configuración de la ventana
        setTitle("🐾 Sistema de Registro de Mascotas - Gestión con Conjuntos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLayout(new BorderLayout());
        
        // Crear panel con pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 13));
        
        // Agregar las 3 pestañas principales
        tabbedPane.addTab("📝 Registro de Mascotas", crearPanelRegistro());
        tabbedPane.addTab("🔧 Operaciones de Conjuntos", crearPanelOperaciones());
        tabbedPane.addTab("📊 Estadísticas", crearPanelEstadisticas());
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    /**
     * Crea el panel de registro de mascotas.
     * @return Panel con formulario y tabla
     */
    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(245, 245, 250));
        
        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
            "Datos de la Mascota",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Fila 1: ID y Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID (Chip):"), gbc);
        
        gbc.gridx = 1;
        txtId = new JTextField(15);
        txtId.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFormulario.add(txtId, gbc);
        
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 3;
        txtNombre = new JTextField(15);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFormulario.add(txtNombre, gbc);
        
        // Fila 2: Tipo y Raza
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Tipo:"), gbc);
        
        gbc.gridx = 1;
        cmbTipo = new JComboBox<>(new String[]{"Perro", "Gato"});
        cmbTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbTipo.addActionListener(e -> actualizarRazas());
        panelFormulario.add(cmbTipo, gbc);
        
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Raza:"), gbc);
        
        gbc.gridx = 3;
        cmbRaza = new JComboBox<>();
        cmbRaza.setFont(new Font("Arial", Font.PLAIN, 13));
        actualizarRazas();
        panelFormulario.add(cmbRaza, gbc);
        
        // Fila 3: Edad y Botones
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Edad (años):"), gbc);
        
        gbc.gridx = 1;
        txtEdad = new JTextField(15);
        txtEdad.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFormulario.add(txtEdad, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.setBackground(Color.WHITE);
        
        btnRegistrar = new JButton("✅ Registrar Mascota");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnRegistrar.setBackground(new Color(34, 139, 34));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(e -> registrarMascota());
        
        btnEliminar = new JButton("❌ Eliminar Seleccionada");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> eliminarMascota());
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnEliminar);
        
        gbc.gridx = 2; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelFormulario.add(panelBotones, gbc);
        
        panel.add(panelFormulario, BorderLayout.NORTH);
        
        // Tabla de mascotas
        String[] columnas = {"ID", "Nombre", "Tipo", "Raza", "Edad", "Vacunada", "Adoptada"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaMascotas = new JTable(modeloTabla);
        tablaMascotas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaMascotas.setRowHeight(25);
        tablaMascotas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaMascotas.getTableHeader().setBackground(new Color(100, 150, 200));
        tablaMascotas.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(tablaMascotas);
        scroll.setBorder(BorderFactory.createTitledBorder("Mascotas Registradas"));
        
        panel.add(scroll, BorderLayout.CENTER);
        
        // Panel inferior con acciones rápidas
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelAcciones.setBackground(new Color(245, 245, 250));
        
        JButton btnVacunar = new JButton("💉 Marcar como Vacunada");
        btnVacunar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVacunar.setBackground(new Color(70, 130, 220));
        btnVacunar.setForeground(Color.WHITE);
        btnVacunar.setFocusPainted(false);
        btnVacunar.addActionListener(e -> marcarVacunada());
        
        JButton btnAdoptar = new JButton("🏠 Marcar como Adoptada");
        btnAdoptar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAdoptar.setBackground(new Color(255, 140, 0));
        btnAdoptar.setForeground(Color.WHITE);
        btnAdoptar.setFocusPainted(false);
        btnAdoptar.addActionListener(e -> marcarAdoptada());
        
        panelAcciones.add(btnVacunar);
        panelAcciones.add(btnAdoptar);
        
        panel.add(panelAcciones, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Crea el panel de operaciones de conjuntos.
     * @return Panel con operaciones
     */
    private JPanel crearPanelOperaciones() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(245, 250, 245));
        
        // Panel superior con selector de operaciones
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Seleccione una Operación"));
        
        cmbOperaciones = new JComboBox<>(new String[]{
            "1. UNIÓN: Todas las mascotas (Perros + Gatos)",
            "2. INTERSECCIÓN: Perros vacunados",
            "3. INTERSECCIÓN: Gatos vacunados",
            "4. INTERSECCIÓN: Mascotas vacunadas Y adoptadas",
            "5. DIFERENCIA: Perros sin vacunar",
            "6. DIFERENCIA: Mascotas disponibles (no adoptadas)",
            "7. TAMAÑO: Estadísticas generales",
            "8. VACÍO: Verificar si hay mascotas sin vacunar"
        });
        cmbOperaciones.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbOperaciones.setPreferredSize(new Dimension(500, 30));
        
        btnEjecutar = new JButton("▶ Ejecutar Operación");
        btnEjecutar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEjecutar.setBackground(new Color(34, 139, 34));
        btnEjecutar.setForeground(Color.WHITE);
        btnEjecutar.setFocusPainted(false);
        btnEjecutar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEjecutar.addActionListener(e -> ejecutarOperacion());
        
        panelSuperior.add(cmbOperaciones);
        panelSuperior.add(btnEjecutar);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        
        // Área de texto para resultados
        txtResultado = new JTextArea();
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado de la Operación"));
        
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea el panel de estadísticas.
     * @return Panel con estadísticas
     */
    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(250, 245, 245));
        
        // Panel superior con botón
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBackground(new Color(250, 245, 245));
        
        btnActualizar = new JButton("🔄 Actualizar Estadísticas");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(70, 130, 220));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(e -> actualizarEstadisticas());
        
        panelSuperior.add(btnActualizar);
        panel.add(panelSuperior, BorderLayout.NORTH);
        
        // Área de estadísticas
        txtEstadisticas = new JTextArea();
        txtEstadisticas.setFont(new Font("Monospaced", Font.BOLD, 14));
        txtEstadisticas.setEditable(false);
        txtEstadisticas.setMargin(new Insets(20, 20, 20, 20));
        txtEstadisticas.setBackground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(txtEstadisticas);
        scroll.setBorder(BorderFactory.createTitledBorder("Estadísticas del Sistema"));
        
        panel.add(scroll, BorderLayout.CENTER);
        
        actualizarEstadisticas();
        
        return panel;
    }
    
    // =========================================================================
    // MÉTODOS DE ACCIÓN
    // =========================================================================
    
    /**
     * Registra una nueva mascota en el sistema.
     */
    private void registrarMascota() {
        try {
            // Validar campos
            if (txtId.getText().trim().isEmpty() || 
                txtNombre.getText().trim().isEmpty() ||
                txtEdad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();
            String raza = (String) cmbRaza.getSelectedItem();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            
            if (edad < 0 || edad > 30) {
                JOptionPane.showMessageDialog(this,
                    "La edad debe estar entre 0 y 30 años.",
                    "Edad inválida",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Crear y registrar mascota
            Mascota mascota = new Mascota(id, nombre, tipo, raza, edad);
            
            if (gestor.registrarMascota(mascota)) {
                JOptionPane.showMessageDialog(this,
                    "¡Mascota registrada exitosamente! 🐾",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
                
                limpiarFormulario();
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this,
                    "El ID ya existe. Use un ID diferente.",
                    "ID duplicado",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "La edad debe ser un número válido.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Elimina la mascota seleccionada.
     */
    private void eliminarMascota() {
        int fila = tablaMascotas.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                "Por favor, seleccione una mascota de la tabla.",
                "Sin selección",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) modeloTabla.getValueAt(fila, 0);
        
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar esta mascota?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            if (gestor.eliminarMascota(id)) {
                JOptionPane.showMessageDialog(this,
                    "Mascota eliminada correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
            }
        }
    }
    
    /**
     * Marca la mascota seleccionada como vacunada.
     */
    private void marcarVacunada() {
        int fila = tablaMascotas.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleccione una mascota de la tabla.",
                "Sin selección",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) modeloTabla.getValueAt(fila, 0);
        
        if (gestor.marcarComoVacunada(id)) {
            JOptionPane.showMessageDialog(this,
                "Mascota marcada como vacunada. 💉",
                "Vacunación registrada",
                JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        }
    }
    
    /**
     * Marca la mascota seleccionada como adoptada.
     */
    private void marcarAdoptada() {
        int fila = tablaMascotas.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleccione una mascota de la tabla.",
                "Sin selección",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) modeloTabla.getValueAt(fila, 0);
        
        if (gestor.marcarComoAdoptada(id)) {
            JOptionPane.showMessageDialog(this,
                "¡Mascota adoptada! 🏠❤️",
                "Adopción registrada",
                JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        }
    }
    
    /**
     * Ejecuta la operación de conjuntos seleccionada.
     */
    private void ejecutarOperacion() {
        int indice = cmbOperaciones.getSelectedIndex();
        StringBuilder resultado = new StringBuilder();
        
        switch (indice) {
            case 0: // Unión
                Set<Mascota> todas = gestor.obtenerTodasLasMascotas();
                resultado.append("═══ OPERACIÓN: UNIÓN ═══\n\n");
                resultado.append("Fórmula: Perros ∪ Gatos\n");
                resultado.append("Total de mascotas: ").append(todas.size()).append("\n\n");
                for (Mascota m : todas) {
                    resultado.append("• ").append(m.toString()).append("\n");
                }
                break;
                
            case 1: // Intersección - Perros vacunados
                Set<Mascota> perrosVac = gestor.obtenerPerrosVacunados();
                resultado.append("═══ OPERACIÓN: INTERSECCIÓN ═══\n\n");
                resultado.append("Fórmula: Perros ∩ Vacunadas\n");
                resultado.append("Perros vacunados: ").append(perrosVac.size()).append("\n\n");
                for (Mascota m : perrosVac) {
                    resultado.append("• ").append(m.toString()).append("\n");
                }
                break;
                
            case 2: // Intersección - Gatos vacunados
                Set<Mascota> gatosVac = gestor.obtenerGatosVacunados();
                resultado.append("═══ OPERACIÓN: INTERSECCIÓN ═══\n\n");
                resultado.append("Fórmula: Gatos ∩ Vacunadas\n");
                resultado.append("Gatos vacunados: ").append(gatosVac.size()).append("\n\n");
                for (Mascota m : gatosVac) {
                    resultado.append("• ").append(m.toString()).append("\n");
                }
                break;
                
            case 3: // Intersección - Vacunadas Y Adoptadas
                Set<Mascota> vacYAdop = gestor.obtenerMascotasVacunadasYAdoptadas();
                resultado.append("═══ OPERACIÓN: INTERSECCIÓN ═══\n\n");
                resultado.append("Fórmula: Vacunadas ∩ Adoptadas\n");
                resultado.append("Mascotas vacunadas Y adoptadas: ").append(vacYAdop.size()).append("\n\n");
                for (Mascota m : vacYAdop) {
                    resultado.append("• ").append(m.toString()).append("\n");
                }
                break;
                
            case 4: // Diferencia - Perros sin vacunar
                Set<Mascota> perrosSinVac = gestor.obtenerPerrosSinVacunar();
                resultado.append("═══ OPERACIÓN: DIFERENCIA ═══\n\n");
                resultado.append("Fórmula: Perros − Vacunadas\n");
                resultado.append("Perros sin vacunar: ").append(perrosSinVac.size()).append("\n\n");
                for (Mascota m : perrosSinVac) {
                    resultado.append("• ").append(m.toString()).append("\n");
                }
                break;
                
            case 5: // Diferencia - Disponibles
                Set<Mascota> disponibles = gestor.obtenerMascotasDisponibles();
                resultado.append("═══ OPERACIÓN: DIFERENCIA ═══\n\n");
                resultado.append("Fórmula: Todas − Adoptadas\n");
                resultado.append("Mascotas disponibles: ").append(disponibles.size()).append("\n\n");
                for (Mascota m : disponibles) {
                    resultado.append("• ").append(m.toString()).append("\n");
                }
                break;
                
            case 6: // Tamaño
                Map<String, Integer> stats = gestor.obtenerEstadisticas();
                resultado.append("═══ OPERACIÓN: TAMAÑO (size) ═══\n\n");
                for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                    resultado.append(String.format("%-30s: %d\n", entry.getKey(), entry.getValue()));
                }
                break;
                
            case 7: // Vacío
                resultado.append("═══ OPERACIÓN: VACÍO (isEmpty) ═══\n\n");
                resultado.append("Sistema vacío: ").append(gestor.sistemaEstaVacio() ? "SÍ" : "NO").append("\n");
                resultado.append("Hay mascotas sin vacunar: ").append(gestor.hayMascotasSinVacunar() ? "SÍ" : "NO").append("\n");
                break;
        }
        
        txtResultado.setText(resultado.toString());
    }
    
    /**
     * Actualiza las estadísticas del sistema.
     */
    private void actualizarEstadisticas() {
        Map<String, Integer> stats = gestor.obtenerEstadisticas();
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════════╗\n");
        sb.append("║     ESTADÍSTICAS DEL SISTEMA               ║\n");
        sb.append("╠════════════════════════════════════════════╣\n");
        
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            sb.append(String.format("║  %-30s: %5d   ║\n", 
                entry.getKey(), entry.getValue()));
        }
        
        sb.append("╚════════════════════════════════════════════╝\n");
        
        txtEstadisticas.setText(sb.toString());
    }
    
    /**
     * Actualiza la tabla de mascotas.
     */
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        
        for (Mascota m : gestor.obtenerTodasLasMascotas()) {
            String vacunada = gestor.estaVacunada(m.getId()) ? "✓ Sí" : "✗ No";
            String adoptada = gestor.estaAdoptada(m.getId()) ? "✓ Sí" : "✗ No";
            
            modeloTabla.addRow(new Object[]{
                m.getId(),
                m.getNombre(),
                m.getTipo(),
                m.getRaza(),
                m.getEdad(),
                vacunada,
                adoptada
            });
        }
    }
    
    /**
     * Actualiza el combo de razas según el tipo seleccionado.
     */
    private void actualizarRazas() {
        cmbRaza.removeAllItems();
        String tipo = (String) cmbTipo.getSelectedItem();
        
        if (tipo.equals("Perro")) {
            cmbRaza.addItem("Labrador");
            cmbRaza.addItem("Chihuahua");
            cmbRaza.addItem("Pastor Alemán");
            cmbRaza.addItem("Golden Retriever");
            cmbRaza.addItem("Bulldog");
            cmbRaza.addItem("Mestizo");
        } else {
            cmbRaza.addItem("Persa");
            cmbRaza.addItem("Siamés");
            cmbRaza.addItem("Angora");
            cmbRaza.addItem("Común Europeo");
            cmbRaza.addItem("Maine Coon");
        }
    }
    
    /**
     * Limpia el formulario de registro.
     */
    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        cmbTipo.setSelectedIndex(0);
        actualizarRazas();
        txtId.requestFocus();
    }
    
    /**
     * Carga datos de ejemplo para pruebas.
     */
    private void cargarDatosEjemplo() {
        // Registrar algunos perros
        gestor.registrarMascota(new Mascota("P001", "Max", "Perro", "Labrador", 3));
        gestor.registrarMascota(new Mascota("P002", "Rocky", "Perro", "Pastor Alemán", 5));
        gestor.registrarMascota(new Mascota("P003", "Luna", "Perro", "Golden Retriever", 2));
        
        // Registrar algunos gatos
        gestor.registrarMascota(new Mascota("G001", "Michi", "Gato", "Persa", 4));
        gestor.registrarMascota(new Mascota("G002", "Pelusa", "Gato", "Siamés", 1));
        gestor.registrarMascota(new Mascota("G003", "Garfield", "Gato", "Común Europeo", 6));
        
        // Marcar algunas como vacunadas
        gestor.marcarComoVacunada("P001");
        gestor.marcarComoVacunada("P002");
        gestor.marcarComoVacunada("G001");
        
        // Marcar algunas como adoptadas
        gestor.marcarComoAdoptada("P001");
        gestor.marcarComoAdoptada("G002");
    }
    
    /**
     * Método principal para ejecutar la aplicación.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Usar el Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            VentanaMascotas ventana = new VentanaMascotas();
            ventana.setVisible(true);
        });
    }
}
