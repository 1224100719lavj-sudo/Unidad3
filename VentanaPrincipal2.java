
**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 */import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Ventana principal de la aplicación
 */
public class VentanaPrincipal extends JFrame {
    private final ArbolBinarioBusqueda arbol;
    private PanelArbol panelArbol;
    
    // Componentes de la interfaz
    private JTextField txtValor;
    private JButton btnInsertar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnInOrden;
    private JButton btnPreOrden;
    private JButton btnPostOrden;
    private JLabel lblRecorrido;
    private JTextArea txtAreaRecorrido;
    private JScrollPane scrollRecorrido;
    
    public VentanaPrincipal() {
        arbol = new ArbolBinarioBusqueda();
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        // Configuración de la ventana
        setTitle("Visualizador de Árbol Binario de Búsqueda (ABB)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con controles
        JPanel panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con el árbol
        panelArbol = new PanelArbol();
        panelArbol.setArbol(arbol);
        panelArbol.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            "Visualización del Árbol",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        add(panelArbol, BorderLayout.CENTER);
        
        // Panel inferior con recorridos
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(230, 230, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Campo de texto para valor
        JLabel lblValor = new JLabel("Valor:");
        lblValor.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(lblValor);
        
        txtValor = new JTextField(8);
        txtValor.setFont(new Font("Arial", Font.PLAIN, 14));
        txtValor.addActionListener(e -> insertarNodo());
        panel.add(txtValor);
        
        // Botón Insertar
        btnInsertar = new JButton("+ Insertar");
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 12));
        btnInsertar.setBackground(new Color(34, 139, 34));
        btnInsertar.setForeground(Color.WHITE);
        btnInsertar.setFocusPainted(false);
        btnInsertar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInsertar.addActionListener(e -> insertarNodo());
        panel.add(btnInsertar);
        
        // Botón Eliminar
        btnEliminar = new JButton("− Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> eliminarNodo());
        panel.add(btnEliminar);
        
        // Botón Buscar
        btnBuscar = new JButton("🔍 Buscar");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setBackground(new Color(70, 130, 220));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(e -> buscarNodo());
        panel.add(btnBuscar);
        
        // Botón Limpiar
        btnLimpiar = new JButton("🗑 Limpiar Árbol");
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLimpiar.setBackground(new Color(128, 128, 128));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(e -> limpiarArbol());
        panel.add(btnLimpiar);
        
        return panel;
    }
    
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de botones de recorrido
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBotones.setBackground(new Color(245, 245, 245));
        
        lblRecorrido = new JLabel("Recorridos:");
        lblRecorrido.setFont(new Font("Arial", Font.BOLD, 13));
        panelBotones.add(lblRecorrido);
        
        // Botón InOrden
        btnInOrden = new JButton("Recorrido InOrden");
        btnInOrden.setFont(new Font("Arial", Font.PLAIN, 12));
        btnInOrden.setBackground(new Color(100, 149, 237));
        btnInOrden.setForeground(Color.WHITE);
        btnInOrden.setFocusPainted(false);
        btnInOrden.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInOrden.addActionListener(e -> mostrarRecorrido("InOrden"));
        panelBotones.add(btnInOrden);
        
        // Botón PreOrden
        btnPreOrden = new JButton("Recorrido PreOrden");
        btnPreOrden.setFont(new Font("Arial", Font.PLAIN, 12));
        btnPreOrden.setBackground(new Color(147, 112, 219));
        btnPreOrden.setForeground(Color.WHITE);
        btnPreOrden.setFocusPainted(false);
        btnPreOrden.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPreOrden.addActionListener(e -> mostrarRecorrido("PreOrden"));
        panelBotones.add(btnPreOrden);
        
        // Botón PostOrden
        btnPostOrden = new JButton("Recorrido PostOrden");
        btnPostOrden.setFont(new Font("Arial", Font.PLAIN, 12));
        btnPostOrden.setBackground(new Color(255, 140, 0));
        btnPostOrden.setForeground(Color.WHITE);
        btnPostOrden.setFocusPainted(false);
        btnPostOrden.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPostOrden.addActionListener(e -> mostrarRecorrido("PostOrden"));
        panelBotones.add(btnPostOrden);
        
        panel.add(panelBotones, BorderLayout.NORTH);
        
        // Área de texto para mostrar recorridos
        txtAreaRecorrido = new JTextArea(3, 50);
        txtAreaRecorrido.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtAreaRecorrido.setEditable(false);
        txtAreaRecorrido.setLineWrap(true);
        txtAreaRecorrido.setWrapStyleWord(true);
        txtAreaRecorrido.setBackground(Color.WHITE);
        txtAreaRecorrido.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        scrollRecorrido = new JScrollPane(txtAreaRecorrido);
        scrollRecorrido.setBorder(BorderFactory.createTitledBorder("Resultado del Recorrido"));
        panel.add(scrollRecorrido, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void insertarNodo() {
        try {
            String texto = txtValor.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un valor numérico.",
                    "Campo vacío",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int valor = Integer.parseInt(texto);
            
            if (arbol.buscar(valor)) {
                JOptionPane.showMessageDialog(this,
                    "El valor " + valor + " ya existe en el árbol.",
                    "Valor duplicado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            arbol.insertar(valor);
            panelArbol.setArbol(arbol);
            txtValor.setText("");
            txtValor.requestFocus();
            
            JOptionPane.showMessageDialog(this,
                "Valor " + valor + " insertado correctamente.",
                "Inserción exitosa",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingrese un valor numérico válido.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE);
            txtValor.setText("");
            txtValor.requestFocus();
        }
    }
    
    private void eliminarNodo() {
        try {
            String texto = txtValor.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese el valor a eliminar.",
                    "Campo vacío",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int valor = Integer.parseInt(texto);
            
            if (!arbol.buscar(valor)) {
                JOptionPane.showMessageDialog(this,
                    "El valor " + valor + " no existe en el árbol.",
                    "Valor no encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            arbol.eliminar(valor);
            panelArbol.setArbol(arbol);
            panelArbol.setNodoResaltado(null);
            txtValor.setText("");
            txtValor.requestFocus();
            
            JOptionPane.showMessageDialog(this,
                "Valor " + valor + " eliminado correctamente.",
                "Eliminación exitosa",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingrese un valor numérico válido.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE);
            txtValor.setText("");
            txtValor.requestFocus();
        }
    }
    
    private void buscarNodo() {
        try {
            String texto = txtValor.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese el valor a buscar.",
                    "Campo vacío",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int valor = Integer.parseInt(texto);
            
            if (arbol.buscar(valor)) {
                // Encontrar el nodo para resaltarlo
                Nodo nodoEncontrado = buscarNodoEnArbol(arbol.getRaiz(), valor);
                panelArbol.setNodoResaltado(nodoEncontrado);
                
                JOptionPane.showMessageDialog(this,
                    "¡Valor " + valor + " encontrado en el árbol!",
                    "Búsqueda exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                panelArbol.setNodoResaltado(null);
                JOptionPane.showMessageDialog(this,
                    "El valor " + valor + " no existe en el árbol.",
                    "Valor no encontrado",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingrese un valor numérico válido.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE);
            txtValor.setText("");
            txtValor.requestFocus();
        }
    }
    
    private Nodo buscarNodoEnArbol(Nodo nodo, int valor) {
        if (nodo == null) return null;
        if (nodo.getValor() == valor) return nodo;
        if (valor < nodo.getValor()) {
            return buscarNodoEnArbol(nodo.getIzquierdo(), valor);
        } else {
            return buscarNodoEnArbol(nodo.getDerecho(), valor);
        }
    }
    
    private void limpiarArbol() {
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea limpiar todo el árbol?",
            "Confirmar limpieza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (opcion == JOptionPane.YES_OPTION) {
            arbol.limpiar();
            panelArbol.setArbol(arbol);
            panelArbol.setNodoResaltado(null);
            txtAreaRecorrido.setText("");
            txtValor.setText("");
            txtValor.requestFocus();
            
            JOptionPane.showMessageDialog(this,
                "Árbol limpiado correctamente.",
                "Limpieza exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void mostrarRecorrido(String tipo) {
        if (arbol.estaVacio()) {
            JOptionPane.showMessageDialog(this,
                "El árbol está vacío. Inserte algunos valores primero.",
                "Árbol vacío",
                JOptionPane.WARNING_MESSAGE);
            txtAreaRecorrido.setText("");
            return;
        }
        
        List<Integer> recorrido;
        String descripcion;
        
        switch (tipo) {
            case "InOrden":
                recorrido = arbol.recorridoInOrden();
                descripcion = "InOrden (Izq, Raíz, Der): ";
                break;
            case "PreOrden":
                recorrido = arbol.recorridoPreOrden();
                descripcion = "PreOrden (Raíz, Izq, Der): ";
                break;
            case "PostOrden":
                recorrido = arbol.recorridoPostOrden();
                descripcion = "PostOrden (Izq, Der, Raíz): ";
                break;
            default:
                return;
        }
        
        StringBuilder sb = new StringBuilder(descripcion);
        for (int i = 0; i < recorrido.size(); i++) {
            sb.append(recorrido.get(i));
            if (i < recorrido.size() - 1) {
                sb.append(" → ");
            }
        }
        
        txtAreaRecorrido.setText(sb.toString());
    }
    
    public static void main(String args[]) {
        // Configurar look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}