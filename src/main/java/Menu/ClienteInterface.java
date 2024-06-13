package Menu;

import javax.swing.*;
import java.awt.*;

public class ClienteInterface extends JFrame {
    public ClienteInterface() {
        setTitle("Interface do Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bem-vindo, Cliente!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(mainPanel);


        JButton searchButton = new JButton("Pesquisar Acomodações");
        searchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Pesquisa de acomodações não implementada.");
        });
        mainPanel.add(searchButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteInterface().setVisible(true));
    }
}
