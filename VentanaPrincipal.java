package com.mycompany.simuladordom;

/**
 *
 * @author Luis angel valencia jantes 
 * gtid141 
 * valencialuis1810@gmail.com
 */
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal de la aplicación Simulador DOM
 * Interfaz gráfica creada completamente por código
 * Muestra el árbol de nodos y la vista HTML sincronizadas
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {
    
    // ========== COMPONENTES DE LA INTERFAZ ==========
    private JPanel panelPrincipal;
    private JPanel panelControles;
    private JPanel panelVistas;
    private JSplitPane splitPane;
    
    // Controles de entrada
    private JLabel lblEtiqueta;
    private JTextField txtEtiqueta;
    private JLabel lblContenido;
    private JTextField txtContenido;
    private JLabel lblAtributos;
    private JTextField txtAtributos;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    
    // Visualización del árbol y HTML
    private JTree arbolVisual;
    private JScrollPane scrollArbol;
    private JTextArea txtHTML;
    private JScrollPane scrollHTML;
    
    // ========== MODELO DE DATOS ==========
    private ArbolDOM arbolDOM;
    private DefaultTreeModel modeloArbol;
    private DefaultMutableTreeNode raizVisual;
    
    /**
     * Constructor principal
     * Inicializa todos los componentes y configura la ventana
     */
    public VentanaPrincipal() {
        // Configurar propiedades de la ventana
        configurarVentana();
        
        // Crear todos los componentes
        crearComponentes();
        
        // Organizar componentes en la interfaz
        organizarInterfaz();
        
        // Configurar eventos de los botones
        configurarEventos();
        
        // Inicializar el árbol DOM
        inicializarArbolDOM();
        
        // Actualizar vistas
        actualizarVistas();
        
        // Hacer visible la ventana
        setVisible(true);
    }
    
    /**
     * Configura las propiedades básicas de la ventana
     */
    private void configurarVentana() {
        setTitle("Simulador DOM - Árbol y HTML en Tiempo Real");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        
        // Establecer Look and Feel moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // Si falla Nimbus, usar el predeterminado
            System.out.println("No se pudo cargar Nimbus, usando Look and Feel predeterminado");
        }
    }
    
    /**
     * Crea todos los componentes de la interfaz
     */
    private void crearComponentes() {
        // ===== PANEL PRINCIPAL =====
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // ===== PANEL DE CONTROLES =====
        panelControles = new JPanel();
        panelControles.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Controles de Edición del DOM"
        ));
        panelControles.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        // Labels
        lblEtiqueta = new JLabel("Etiqueta HTML:");
        lblContenido = new JLabel("Contenido:");
        lblAtributos = new JLabel("Atributos:");
        
        // TextFields
        txtEtiqueta = new JTextField(12);
        txtEtiqueta.setToolTipText("Ejemplo: div, p, h1, span, section");
        
        txtContenido = new JTextField(20);
        txtContenido.setToolTipText("Texto que irá dentro del elemento");
        
        txtAtributos = new JTextField(20);
        txtAtributos.setToolTipText("Ejemplo: class=\"container\" id=\"main\"");
        
        // Botones
        btnAgregar = new JButton("➕ Agregar Nodo");
        btnAgregar.setBackground(new Color(76, 175, 80));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnEliminar = new JButton("🗑️ Eliminar Nodo");
        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnLimpiar = new JButton("🔄 Limpiar Campos");
        btnLimpiar.setBackground(new Color(33, 150, 243));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        
        // ===== ÁRBOL VISUAL =====
        arbolVisual = new JTree();
        arbolVisual.setFont(new Font("Monospaced", Font.PLAIN, 13));
        arbolVisual.setRowHeight(25);
        
        // Personalizar el renderizador del árbol con colores
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                
                if (value instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                    if (node.getUserObject() instanceof NodoDOM) {
                        NodoDOM nodo = (NodoDOM) node.getUserObject();
                        
                        // Colorear según el tipo de etiqueta
                        String etiqueta = nodo.getEtiqueta().toLowerCase();
                        if (etiqueta.equals("html")) {
                            setForeground(new Color(211, 47, 47)); // Rojo
                            setFont(getFont().deriveFont(Font.BOLD));
                        } else if (etiqueta.equals("head") || etiqueta.equals("body")) {
                            setForeground(new Color(25, 118, 210)); // Azul
                            setFont(getFont().deriveFont(Font.BOLD));
                        } else if (etiqueta.startsWith("h")) {
                            setForeground(new Color(123, 31, 162)); // Púrpura
                        } else {
                            setForeground(new Color(56, 142, 60)); // Verde
                        }
                    }
                }
                return this;
            }
        };
        arbolVisual.setCellRenderer(renderer);
        
        scrollArbol = new JScrollPane(arbolVisual);
        scrollArbol.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            "Estructura del Árbol DOM"
        ));
        
        // ===== ÁREA DE TEXTO HTML =====
        txtHTML = new JTextArea();
        txtHTML.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtHTML.setEditable(false);
        txtHTML.setLineWrap(false);
        txtHTML.setTabSize(2);
        txtHTML.setBackground(new Color(43, 43, 43)); // Fondo oscuro
        txtHTML.setForeground(new Color(169, 183, 198)); // Texto claro
        txtHTML.setCaretColor(Color.WHITE);
        
        scrollHTML = new JScrollPane(txtHTML);
        scrollHTML.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            "Vista HTML Generada"
        ));
        
        // ===== SPLIT PANE =====
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollArbol, scrollHTML);
        splitPane.setDividerLocation(450);
        splitPane.setResizeWeight(0.4);
        splitPane.setOneTouchExpandable(true);
    }
    
    /**
     * Organiza los componentes en la interfaz usando layouts
     */
    private void organizarInterfaz() {
        // Agregar componentes al panel de controles
        panelControles.add(lblEtiqueta);
        panelControles.add(txtEtiqueta);
        panelControles.add(lblContenido);
        panelControles.add(txtContenido);
        panelControles.add(lblAtributos);
        panelControles.add(txtAtributos);
        panelControles.add(btnAgregar);
        panelControles.add(btnEliminar);
        panelControles.add(btnLimpiar);
        
        // Agregar al panel principal
        panelPrincipal.add(panelControles, BorderLayout.NORTH);
        panelPrincipal.add(splitPane, BorderLayout.CENTER);
        
        // Agregar instrucciones en la parte inferior
        JLabel lblInstrucciones = new JLabel(
            "💡 Instrucciones: Selecciona un nodo padre en el árbol, ingresa los datos y haz clic en 'Agregar Nodo'"
        );
        lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstrucciones.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblInstrucciones.setFont(new Font("Arial", Font.ITALIC, 11));
        panelPrincipal.add(lblInstrucciones, BorderLayout.SOUTH);
        
        // Establecer el panel principal como contenido de la ventana
        setContentPane(panelPrincipal);
    }
    
    /**
     * Configura los eventos de los botones
     */
    private void configurarEventos() {
        // Evento del botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNodo();
            }
        });
        
        // Evento del botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarNodo();
            }
        });
        
        // Evento del botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        // Permitir agregar con Enter en el campo de etiqueta
        txtEtiqueta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNodo();
            }
        });
    }
    
    /**
     * Inicializa el árbol DOM con estructura HTML básica
     */
    private void inicializarArbolDOM() {
        // Crear árbol DOM con estructura básica
        arbolDOM = new ArbolDOM();
        
        // Crear representación visual del árbol
        raizVisual = new DefaultMutableTreeNode(arbolDOM.getRaiz());
        construirArbolVisual(arbolDOM.getRaiz(), raizVisual);
        
        // Asignar modelo al JTree
        modeloArbol = new DefaultTreeModel(raizVisual);
        arbolVisual.setModel(modeloArbol);
        
        // Expandir todos los nodos
        expandirArbol();
    }
    
    /**
     * Construye recursivamente el árbol visual desde el árbol DOM
     * @param nodoDOM Nodo del árbol de datos
     * @param nodoVisual Nodo del árbol visual (JTree)
     */
    private void construirArbolVisual(NodoDOM nodoDOM, DefaultMutableTreeNode nodoVisual) {
        for (NodoDOM hijo : nodoDOM.getHijos()) {
            DefaultMutableTreeNode hijoVisual = new DefaultMutableTreeNode(hijo);
            nodoVisual.add(hijoVisual);
            construirArbolVisual(hijo, hijoVisual); // Llamada recursiva
        }
    }
    
    /**
     * Expande todos los nodos del árbol visual
     */
    private void expandirArbol() {
        for (int i = 0; i < arbolVisual.getRowCount(); i++) {
            arbolVisual.expandRow(i);
        }
    }
    
    /**
     * Actualiza tanto el árbol visual como la vista HTML
     */
    private void actualizarVistas() {
        // Actualizar árbol visual
        raizVisual.removeAllChildren();
        construirArbolVisual(arbolDOM.getRaiz(), raizVisual);
        modeloArbol.reload();
        
        // Expandir todos los nodos
        expandirArbol();
        
        // Actualizar vista HTML
        txtHTML.setText(arbolDOM.generarHTMLCompleto());
        txtHTML.setCaretPosition(0); // Scroll al inicio
    }
    
    /**
     * Obtiene el nodo DOM seleccionado en el árbol visual
     * @return Nodo seleccionado o null si no hay selección
     */
    private NodoDOM obtenerNodoSeleccionado() {
        TreePath path = arbolVisual.getSelectionPath();
        if (path == null) {
            return null;
        }
        
        DefaultMutableTreeNode nodoVisual = (DefaultMutableTreeNode) path.getLastPathComponent();
        return (NodoDOM) nodoVisual.getUserObject();
    }
    
    /**
     * Maneja el evento de agregar un nuevo nodo al DOM
     */
    private void agregarNodo() {
        // Validar que se haya ingresado una etiqueta
        String etiqueta = txtEtiqueta.getText().trim();
        if (etiqueta.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Por favor ingrese una etiqueta HTML (ej: div, p, h1)",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE
            );
            txtEtiqueta.requestFocus();
            return;
        }
        
        // Validar que haya un nodo padre seleccionado
        NodoDOM nodoPadre = obtenerNodoSeleccionado();
        if (nodoPadre == null) {
            JOptionPane.showMessageDialog(
                this,
                "Por favor seleccione un nodo padre en el árbol\n" +
                "Sugerencia: Haga clic en <body> para agregar elementos a la página",
                "Selección requerida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Obtener contenido y atributos
        String contenido = txtContenido.getText().trim();
        String atributos = txtAtributos.getText().trim();
        
        // Crear el nuevo nodo
        NodoDOM nuevoNodo;
        if (!atributos.isEmpty()) {
            nuevoNodo = new NodoDOM(etiqueta, contenido, atributos);
        } else {
            nuevoNodo = new NodoDOM(etiqueta, contenido);
        }
        
        // Agregar al árbol DOM
        nodoPadre.agregarHijo(nuevoNodo);
        
        // Actualizar vistas
        actualizarVistas();
        
        // Limpiar campos
        limpiarCampos();
        
        // Seleccionar el nodo recién creado en el árbol
        buscarYSeleccionarNodo(raizVisual, nuevoNodo);
        
        // Mensaje de éxito
        JOptionPane.showMessageDialog(
            this,
            "✅ Nodo <" + etiqueta + "> agregado exitosamente",
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Busca y selecciona un nodo en el árbol visual
     */
    private void buscarYSeleccionarNodo(DefaultMutableTreeNode raiz, NodoDOM nodoBuscado) {
        if (raiz.getUserObject() == nodoBuscado) {
            TreePath path = new TreePath(raiz.getPath());
            arbolVisual.setSelectionPath(path);
            arbolVisual.scrollPathToVisible(path);
            return;
        }
        
        for (int i = 0; i < raiz.getChildCount(); i++) {
            buscarYSeleccionarNodo((DefaultMutableTreeNode) raiz.getChildAt(i), nodoBuscado);
        }
    }
    
    /**
     * Maneja el evento de eliminar un nodo del DOM
     */
    private void eliminarNodo() {
        NodoDOM nodoSeleccionado = obtenerNodoSeleccionado();
        
        // Validar que haya un nodo seleccionado
        if (nodoSeleccionado == null) {
            JOptionPane.showMessageDialog(
                this,
                "Por favor seleccione un nodo en el árbol para eliminar",
                "Selección requerida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // No permitir eliminar el nodo raíz
        if (nodoSeleccionado == arbolDOM.getRaiz()) {
            JOptionPane.showMessageDialog(
                this,
                "⛔ No se puede eliminar el nodo raíz <html>\n" +
                "Este es el elemento principal del documento",
                "Operación no permitida",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Confirmar eliminación
        String mensaje = String.format(
            "¿Está seguro de eliminar el nodo '%s'?\n" +
            "⚠️ Esta acción también eliminará todos sus nodos hijos",
            nodoSeleccionado.toString()
        );
        
        int opcion = JOptionPane.showConfirmDialog(
            this,
            mensaje,
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            // Eliminar del padre
            NodoDOM padre = nodoSeleccionado.getPadre();
            if (padre != null) {
                padre.eliminarHijo(nodoSeleccionado);
                actualizarVistas();
                
                JOptionPane.showMessageDialog(
                    this,
                    "✅ Nodo eliminado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
    
    /**
     * Limpia todos los campos de entrada
     */
    private void limpiarCampos() {
        txtEtiqueta.setText("");
        txtContenido.setText("");
        txtAtributos.setText("");
        txtEtiqueta.requestFocus();
    }
    
    /**
     * Método main - Punto de entrada de la aplicación
     * @param args Argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}
