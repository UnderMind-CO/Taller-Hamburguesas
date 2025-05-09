package compradehamburguesas;

import java.awt.*;
import javax.swing.*;

public class AcercaDialog extends JFrame {

    public AcercaDialog(JFrame padre) {
        super("Acerca de los Programadores");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(450, 600));

         // Primer programador
        JPanel panel1 = new JPanel(null);
        panel1.setBounds(10, 10, 430, 90);
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(panel1);

        JLabel lblAvatar1 = new JLabel(new ImageIcon(new ImageIcon("imagenes.jpg/WhatsApp Image 2025-05-08 at 11.01.19 PM.jpeg").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        lblAvatar1.setBounds(10, 20, 60, 60);
        panel1.add(lblAvatar1);

        JLabel lblNombre1 = new JLabel("Jhoan David Naranjo Robledo");
        lblNombre1.setBounds(80, 10, 340, 20);
        lblNombre1.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel1.add(lblNombre1);

        JLabel lblCodigo1 = new JLabel("Código: 230242068");
        lblCodigo1.setBounds(80, 35, 340, 20);
        lblCodigo1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel1.add(lblCodigo1);

        JLabel lblCorreo1 = new JLabel("Correo: jhoan.naranjo01@uceva.edu.co");
        lblCorreo1.setBounds(80, 60, 340, 20);
        lblCorreo1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel1.add(lblCorreo1);

        // Segundo programador
        JPanel panel2 = new JPanel(null);
        panel2.setBounds(10, 110, 430, 90);
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(panel2);

        JLabel lblAvatar2 = new JLabel(new ImageIcon(new ImageIcon("imagenes.jpg/WhatsApp Image 2025-03-21 at 10.49.23 AM.jpeg").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        lblAvatar2.setBounds(10, 10, 60, 60);
        panel2.add(lblAvatar2);

        JLabel lblNombre2 = new JLabel("Esteban Lozano Jaramillo");
        lblNombre2.setBounds(80, 10, 340, 20);
        lblNombre2.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel2.add(lblNombre2);

        JLabel lblCodigo2 = new JLabel("Código: 230242048");
        lblCodigo2.setBounds(80, 35, 340, 20);
        lblCodigo2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel2.add(lblCodigo2);

        JLabel lblCorreo2 = new JLabel("Correo: esteban.lozano01@uceva.edu.co");
        lblCorreo2.setBounds(80, 60, 340, 20);
        lblCorreo2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel2.add(lblCorreo2);

        // Tercer programador
        JPanel panel3 = new JPanel(null);
        panel3.setBounds(10, 210, 430, 90);
        panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(panel3);

        JLabel lblAvatar3 = new JLabel(new ImageIcon(new ImageIcon("imagenes.jpg/WhatsApp Image 2025-05-08 at 8.45.01 AM.jpeg").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        lblAvatar3.setBounds(10, 10, 60, 60);
        panel3.add(lblAvatar3);

        JLabel lblNombre3 = new JLabel("Santiago Echeverri Torres");
        lblNombre3.setBounds(80, 10, 340, 20);
        lblNombre3.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel3.add(lblNombre3);

        JLabel lblCodigo3 = new JLabel("Código: 230242047");
        lblCodigo3.setBounds(80, 35, 340, 20);
        lblCodigo3.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel3.add(lblCodigo3);

        JLabel lblCorreo3 = new JLabel("Correo: santiago.echeverri01@uceva.edu.co");
        lblCorreo3.setBounds(80, 60, 340, 20);
        lblCorreo3.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel3.add(lblCorreo3);

        // Cuarto programador
        JPanel panel4 = new JPanel(null);
        panel4.setBounds(10, 310, 430, 90);
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(panel4);

        JLabel lblAvatar4 = new JLabel(new ImageIcon(new ImageIcon("imagenes.jpg/WhatsApp Image 2025-05-06 at 9.14.00 PM.jpeg").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        lblAvatar4.setBounds(10, 10, 60, 60);
        panel4.add(lblAvatar4);

        JLabel lblNombre4 = new JLabel("Dylan Steven Bedon Ramírez");
        lblNombre4.setBounds(80, 10, 340, 20);
        lblNombre4.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel4.add(lblNombre4);

        JLabel lblCodigo4 = new JLabel("Código: 230242066");
        lblCodigo4.setBounds(80, 35, 340, 20);
        lblCodigo4.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel4.add(lblCodigo4);

        JLabel lblCorreo4 = new JLabel("Correo: dylan.bedon01@uceva.edu.co");
        lblCorreo4.setBounds(80, 60, 340, 20);
        lblCorreo4.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel4.add(lblCorreo4);

        // ScrollPane para desplazamiento
        JScrollPane scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 10, 480, 340);
        add(scrollPane);

        // Botón cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(200, 360, 100, 30);
        btnCerrar.addActionListener(e -> {
            padre.setVisible(true); // Mostrar la ventana principal al cerrar
            dispose();
        });
        add(btnCerrar);


        setVisible(true);
    }
}