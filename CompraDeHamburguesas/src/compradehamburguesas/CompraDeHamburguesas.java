package compradehamburguesas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CompraDeHamburguesas extends JFrame {
    JButton btnCliente = new JButton("Cliente");
    JButton btnTipoHamburguesa = new JButton("Tipo de Hamburguesa");
    JButton btnVentas = new JButton("Ventas");

    public CompraDeHamburguesas() {
        super("Menú Principal");
        setSize(400, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        crearGUI();
        setVisible(true);
    }

    private void crearGUI() {
        JLabel titulo = new JLabel("Menú Principal");
        titulo.setBounds(0, 20, 400, 40);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        titulo.setOpaque(true);
        titulo.setBackground(Color.WHITE);
        add(titulo);

        btnCliente.setBounds(100, 90, 200, 40);
        btnCliente.addActionListener(e -> {
            setVisible(false);
            new ClienteMenu(this);
        });
        add(btnCliente);

        btnTipoHamburguesa.setBounds(100, 150, 200, 40);
        btnTipoHamburguesa.addActionListener(e -> {
            setVisible(false);
            new TipoHamburguesaMenu(this);
        });
        add(btnTipoHamburguesa);

        btnVentas.setBounds(100, 210, 200, 40);
        btnVentas.addActionListener(e -> {
            setVisible(false);
            new RegistroVenta(this);
        });
        add(btnVentas);
    }

    public static void main(String[] args) {
        new CompraDeHamburguesas();
    }
}

