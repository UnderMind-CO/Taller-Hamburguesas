package compradehamburguesas;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;

public class TipoHamburguesaMenu extends JFrame {
    JButton btnVolver = new JButton("Volver");
    JButton btnLimpiar = new JButton("Limpiar");
    JButton btnGuardar = new JButton("Guardar");

    public TipoHamburguesaMenu(JFrame padre) {
        super("Formulario Hamburguesa");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Adicionar Orden", JLabel.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setBounds(0, 10, 500, 30);
        add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código Orden:");
        lblCodigo.setBounds(50, 60, 130, 25);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 60, 150, 25);
        add(txtCodigo);

        JLabel lblTipo = new JLabel("Tipo Hamburguesa:");
        lblTipo.setBounds(50, 100, 130, 25);
        add(lblTipo);

        String[] tipos = {"Res", "Pollo", "Vegetariana", "Cerdo", "Mixta"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setBounds(180, 100, 150, 25);
        add(cbTipo);

        JLabel lblTam = new JLabel("Tamaño:");
        lblTam.setBounds(50, 140, 130, 25);
        add(lblTam);

        String[] tamanos = {"Pequeña", "Mediana", "Grande"};
        JComboBox<String> cbTam = new JComboBox<>(tamanos);
        cbTam.setBounds(180, 140, 150, 25);
        add(cbTam);

        JLabel lblQueso = new JLabel("Queso:");
        lblQueso.setBounds(50, 180, 130, 25);
        add(lblQueso);

        JRadioButton rbtnConQueso = new JRadioButton("Con Queso");
        JRadioButton rbtnSinQueso = new JRadioButton("Sin Queso");
        rbtnConQueso.setBounds(180, 180, 100, 25);
        rbtnSinQueso.setBounds(280, 180, 100, 25);

        ButtonGroup grupoQueso = new ButtonGroup();
        grupoQueso.add(rbtnConQueso);
        grupoQueso.add(rbtnSinQueso);

        add(rbtnConQueso);
        add(rbtnSinQueso);

        JLabel lblBebida = new JLabel("Bebida:");
        lblBebida.setBounds(50, 220, 130, 25);
        add(lblBebida);

        String[] bebidas = {"Ninguna", "Coca-Cola", "Pepsi", "Jugo", "Agua"};
        JComboBox<String> cbBebida = new JComboBox<>(bebidas);
        cbBebida.setBounds(180, 220, 150, 25);
        add(cbBebida);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(50, 260, 130, 25);
        add(lblCantidad);

        JTextField txtCantidad = new JTextField();
        txtCantidad.setBounds(180, 260, 50, 25);
        add(txtCantidad);

        // Botón Volver
        btnVolver.setBounds(50, 310, 90, 30);
        btnVolver.addActionListener(e -> {
            dispose();
            padre.setVisible(true);
        });
        add(btnVolver);

        // Botón Limpiar
        btnLimpiar.setBounds(200, 310, 90, 30);
        btnLimpiar.addActionListener(e -> {
            txtCodigo.setText("");
            cbTipo.setSelectedIndex(0);
            cbTam.setSelectedIndex(0);
            grupoQueso.clearSelection();
            cbBebida.setSelectedIndex(0);
            txtCantidad.setText("");
        });
        add(btnLimpiar);

        // Botón Guardar
        btnGuardar.setBounds(350, 310, 90, 30);
        btnGuardar.addActionListener(e -> {
            String queso = rbtnConQueso.isSelected() ? "Con Queso"
                        : rbtnSinQueso.isSelected() ? "Sin Queso" : "";

            ArrayList<String> datos = new ArrayList<>();
            datos.add(txtCodigo.getText().trim());                // Código
            datos.add((String) cbTipo.getSelectedItem());         // Tipo
            datos.add((String) cbTam.getSelectedItem());          // Tamaño
            datos.add(queso);                                     // Queso
            datos.add((String) cbBebida.getSelectedItem());       // Bebida
            datos.add(txtCantidad.getText().trim());              // Cantidad

            boolean camposVacios = datos.stream().anyMatch(d -> d.trim().isEmpty());

            if (camposVacios) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos requeridos.");
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(datos.get(5));
                if (cantidad <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad debe ser un número positivo válido.");
                return;
            }

            // Mostrar resumen
            String resumen = "Orden Registrada:\n"
                    + "Código: " + datos.get(0) + "\n"
                    + "Tipo: " + datos.get(1) + "\n"
                    + "Tamaño: " + datos.get(2) + "\n"
                    + "Queso: " + datos.get(3) + "\n"
                    + "Bebida: " + datos.get(4) + "\n"
                    + "Cantidad: " + datos.get(5);

            JOptionPane.showMessageDialog(this, resumen);
        });
        add(btnGuardar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TipoHamburguesaMenu(null);
    }
}
