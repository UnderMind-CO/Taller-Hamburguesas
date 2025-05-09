package compradehamburguesas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import javax.swing.SwingUtilities;

public class CompraDeHamburguesas extends JFrame {
    JButton btnCliente = new JButton("Cliente");
    JButton btnTipoHamburguesa = new JButton("Tipo de Hamburguesa");
    JButton btnVentas = new JButton("Dashboard");
    JButton btnPedidos = new JButton("Ver Pedidos");
    JButton btnAcerca = new JButton("Acerca");

    public CompraDeHamburguesas() {
        super("Menú Principal");
        setSize(420, 460); // Altura ajustada para que quepan todos los botones
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
        titulo.setBackground(new Color(65,64,64));
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
            new Dashboard(this);  // Dashboard de clientes
        });
        add(btnVentas);

        btnPedidos.setBounds(100, 270, 200, 40);
        btnPedidos.addActionListener(e -> {
            setVisible(false);
            new PedidosDashboard(this);  // Dashboard de pedidos
        });
        add(btnPedidos);

        btnAcerca.setBounds(100, 330, 200, 40);
        btnAcerca.addActionListener(e -> {
            setVisible(false);
            new AcercaDialog(this);
        });
        add(btnAcerca);
    }

    public static void main(String[] args) {
        FlatXcodeDarkIJTheme.setup();
        SwingUtilities.invokeLater(CompraDeHamburguesas::new);
    }
}
