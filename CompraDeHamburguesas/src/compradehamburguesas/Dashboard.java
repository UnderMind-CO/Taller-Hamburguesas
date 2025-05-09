package compradehamburguesas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Dashboard extends JFrame {
    private JTable table;

    public Dashboard(JFrame parent) {
        setTitle("Dashboard de Clientes");
        setSize(600, 450);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(40, 40, 40));

        JLabel title = new JLabel("Clientes Registrados");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        String[] columnNames = {"Cédula", "Nombres", "Apellidos", "Teléfono"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setBackground(new Color(60, 60, 60));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(40, 40, 40));
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            dispose();
            parent.setVisible(true);
        });
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);

        cargarDatos(model);

        setVisible(true);
    }

    private void cargarDatos(DefaultTableModel model) {
        conDB db = new conDB();
        String[][] registros = db.consultaMatriz("SELECT * FROM clientes");
        if (registros != null) {
            for (String[] fila : registros) {
                model.addRow(fila);
            }
        }
    }
}
