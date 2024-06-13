package Menu;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Sistema de Reserva de Hotel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                CardLayout cardLayout = new CardLayout();
                JPanel cardPanel = new JPanel(cardLayout);

                cardPanel.add(new TelaReservaHotel().getMainPanel(), "reserva");
                cardPanel.add(new TelaPerfil().getMainPanel(), "perfil");

                frame.add(cardPanel);

                JPanel buttonPanel = new JPanel(new FlowLayout());
                JButton reservaButton = new JButton("Reserva");
                JButton perfilButton = new JButton("Perfil");
                buttonPanel.add(reservaButton);
                buttonPanel.add(perfilButton);
                frame.add(buttonPanel, BorderLayout.NORTH);

                reservaButton.addActionListener(e -> cardLayout.show(cardPanel, "reserva"));
                perfilButton.addActionListener(e -> cardLayout.show(cardPanel, "perfil"));

                frame.setVisible(true);
            }
        });
    }
}
