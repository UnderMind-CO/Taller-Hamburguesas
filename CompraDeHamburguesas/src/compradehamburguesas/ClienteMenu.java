package compradehamburguesas;


import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class ClienteMenu extends JFrame {
    private JTextField txtCedula, txtNombres, txtApellidos, txtTelefono;
    private JComboBox<String> cmbOrden;
    private JButton btnRegistrar, btnVolver;
    CompraDeHamburguesas test;
    
        private void evento_jbLimpiar() {
        txtTelefono.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        cmbOrden.setSelectedIndex(0);
    
//        if(modo == 2){ // modo Consultar y Actualizar  
//            habilitarCampos(false); // deshabilitar todos los campos del formulario
//            btnRegistrar.setEnabled(false);
//        }
    }
        private void evento_jbVolver() {
        setVisible(false);
        dispose();
        test.setVisible(true);
    }
        

    public ClienteMenu(CompraDeHamburguesas ej) {
        super("Gestión de Cliente");
        test = ej;
        setSize(400, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Registro de Cliente");
        lblTitulo.setBounds(0, 20, 400, 30);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 18));
        add(lblTitulo);

        JLabel lblOrden = new JLabel("Orden:");
        lblOrden.setBounds(50, 80, 100, 25);
        //add(lblOrden);

        cmbOrden = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        cmbOrden.setBounds(150, 80, 180, 25);
        //add(cmbOrden);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(50, 110, 100, 25);
        add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(150, 110, 180, 25);
        add(txtCedula);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(50, 140, 100, 25);
        add(lblNombres);
        txtNombres = new JTextField();
        txtNombres.setBounds(150, 140, 180, 25);
        add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(50, 170, 100, 25);
        add(lblApellidos);
        txtApellidos = new JTextField();
        txtApellidos.setBounds(150, 170, 180, 25);
        add(txtApellidos);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 200, 100, 25);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 200, 180, 25);
        add(txtTelefono);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(80, 260, 120, 30);
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });
        add(btnRegistrar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(230, 260, 120, 30);
        btnVolver.addActionListener(e -> {
            dispose();
            test.setVisible(true);
        });
        add(btnVolver);

        setVisible(true);
    }

private void registrarCliente() {
    ArrayList<String> datos = new ArrayList<>();
   
    
    datos.add(txtCedula.getText());                  // Cédula
    datos.add(txtNombres.getText());                 // Nombres
    datos.add(txtApellidos.getText());               // Apellidos
    datos.add(txtTelefono.getText());                // Teléfono

    boolean camposVacios = datos.stream().anyMatch(d -> d == null || d.trim().isEmpty());

    if (!txtCedula.getText().matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "La cédula debe contener solo números.");
        return;
    }

    if (!txtTelefono.getText().matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.");
        return;
    }

    if (camposVacios) {
        JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
    } else {
       if(new conDB().insertar("clientes", datos)){
                int resp = JOptionPane.showConfirmDialog(null, 
                        "Se guardo el registro.\nDesea guardar otro?", "Confirmación", 0);
                if(resp == 0) evento_jbLimpiar();
                else evento_jbVolver();
            }
    }
    
}

}
