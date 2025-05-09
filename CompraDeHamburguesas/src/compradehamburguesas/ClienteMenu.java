
package compradehamburguesas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class ClienteMenu extends JFrame {

    private JTextField txtCedula, txtNombres, txtApellidos, txtTelefono;
    private JButton btnRegistrar, btnActualizar, btnVolver;
    private CompraDeHamburguesas padre;

    public ClienteMenu(CompraDeHamburguesas padre) {
        this.padre = padre;

        setTitle("Gestión de Cliente");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("Registro de Cliente");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(100, 20, 250, 30);
        add(titulo);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(50, 70, 100, 25);
        add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(150, 70, 200, 25);
        add(txtCedula);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(50, 100, 100, 25);
        add(lblNombres);
        txtNombres = new JTextField();
        txtNombres.setBounds(150, 100, 200, 25);
        add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(50, 130, 100, 25);
        add(lblApellidos);
        txtApellidos = new JTextField();
        txtApellidos.setBounds(150, 130, 200, 25);
        add(txtApellidos);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 160, 100, 25);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 160, 200, 25);
        add(txtTelefono);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(20, 230, 100, 30);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> datos = new ArrayList<>();
                datos.add(txtCedula.getText());
                datos.add(txtNombres.getText());
                datos.add(txtApellidos.getText());
                datos.add(txtTelefono.getText());

                conDB db = new conDB();
                boolean registrado = db.insertar("clientes", datos);
                if (registrado) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el cliente.");
                }
            }
        });
        add(btnRegistrar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(140, 230, 120, 30);
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText().trim();
                String nombres = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String telefono = txtTelefono.getText().trim();

                if (!cedula.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !telefono.isEmpty()) {
                    ArrayList<String> datos = new ArrayList<>();
                    datos.add(cedula);
                    datos.add(nombres);
                    datos.add(apellidos);
                    datos.add(telefono);

                    conDB db = new conDB();
                    boolean actualizado = db.actualizar("clientes", datos, "cedula = '" + cedula + "'");
                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Información del cliente actualizada.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                }
            }
        });
        add(btnActualizar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(280, 230, 100, 30);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                padre.setVisible(true);
            }
        });
        add(btnVolver);

        setVisible(true);
    }
}
