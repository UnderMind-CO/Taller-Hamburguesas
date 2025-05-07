package compradehamburguesas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegistroVenta extends JFrame {

    private JTextField txtCliente, txtTotal;
    private JComboBox<String> cmbHamburguesa, cmbMetodoPago;
    private JSpinner spnCantidad;
    private JButton btnRegistrar, btnCancelar;

    public static ArrayList<String> ventas = new ArrayList<>();

    public RegistroVenta(JFrame padre) {
        super("Registrar Venta");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Cliente (cedula):"));
        txtCliente = new JTextField();
        panel.add(txtCliente);

        panel.add(new JLabel("Tipo de Hamburguesa:"));
        cmbHamburguesa = new JComboBox<>(new String[]{"Clásica", "Queso", "Doble", "Vegana"});
        panel.add(cmbHamburguesa);

        panel.add(new JLabel("Cantidad:"));
        spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        panel.add(spnCantidad);

        panel.add(new JLabel("Método de Pago:"));
        cmbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta", "Transferencia"});
        panel.add(cmbMetodoPago);

        panel.add(new JLabel("Total a Pagar:"));
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        panel.add(txtTotal);

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");
        panel.add(btnRegistrar);
        panel.add(btnCancelar);

        add(panel);

        cmbHamburguesa.addActionListener(e -> actualizarTotal());
        spnCantidad.addChangeListener(e -> actualizarTotal());

        actualizarTotal();

        // Acción registrar
        btnRegistrar.addActionListener(e -> registrarVenta(padre));

        // Acción cancelar
        btnCancelar.addActionListener(e -> {
            dispose();
            padre.setVisible(true);
        });

        setVisible(true);
    }

    private void actualizarTotal() {
        String tipo = (String) cmbHamburguesa.getSelectedItem();
        int cantidad = (int) spnCantidad.getValue();
        double precio = switch (tipo) {
            case "Clásica" -> 8000;
            case "Queso" -> 9500;
            case "Doble" -> 11000;
            case "Vegana" -> 10500;
            default -> 0;
        };
        txtTotal.setText(String.valueOf(precio * cantidad));
    }

    private void registrarVenta(JFrame padre) {
        ArrayList<String> datos = new ArrayList<>();
        datos.add(txtCliente.getText().trim());
        datos.add((String) cmbHamburguesa.getSelectedItem());
        datos.add(spnCantidad.getValue().toString());
        datos.add((String) cmbMetodoPago.getSelectedItem());
        datos.add(txtTotal.getText());

        boolean camposVacios = datos.stream().anyMatch(String::isEmpty);

        if (camposVacios) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }

        String venta = "Cliente: " + datos.get(0)
                + ", Tipo: " + datos.get(1)
                + ", Cantidad: " + datos.get(2)
                + ", Pago: " + datos.get(3)
                + ", Total: $" + datos.get(4);
        ventas.add(venta);

        JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.");
        dispose();
        padre.setVisible(true);
    }
}
