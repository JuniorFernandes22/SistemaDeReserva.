package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FixedMenuExample extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public FixedMenuExample() {
        setTitle("Fixed Menu Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topMenuPanel = new JPanel(new GridLayout(1, 2));
        topMenuPanel.setBackground(new Color(100, 100, 100));

        JButton profileButton = new JButton("Perfil");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Perfil");
            }
        });

        profileButton.setBackground(new Color(200, 200, 200));
        profileButton.setForeground(Color.BLACK);
        profileButton.setFont(new Font("Arial", Font.BOLD, 14));

        topMenuPanel.add(profileButton);

        JPanel menuBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuBar.setBackground(new Color(50, 50, 50));

        JButton page1Button = new JButton("Reservar");
        JButton page2Button = new JButton("Page 2");

        page1Button.setBackground(new Color(70, 70, 70));
        page1Button.setForeground(Color.WHITE);
        page1Button.setFont(new Font("Arial", Font.BOLD, 12));

        page2Button.setBackground(new Color(70, 70, 70));
        page2Button.setForeground(Color.WHITE); // Cor do texto
        page2Button.setFont(new Font("Arial", Font.BOLD, 12));

        page1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Page 1");
            }
        });

        page2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Page 2");
            }
        });

        menuBar.add(page1Button);
        menuBar.add(page2Button);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(topMenuPanel, BorderLayout.EAST);
        topPanel.add(menuBar, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        TelaPerfil telaPerfil = new TelaPerfil();
        telaPerfil.setBackground(new Color(150, 150, 150));
        topMenuPanel.add(telaPerfil);


        JPanel telaReservaHotelPanel = new TelaReservaHotel().getMainPanel();

        JPanel page2Panel = new JPanel();
        page2Panel.setBackground(new Color(200, 200, 200));
        page2Panel.add(new JLabel("Conteúdo da página 2", SwingConstants.CENTER));

        mainPanel.add(telaPerfil, "Perfil");
        mainPanel.add(telaReservaHotelPanel, "Page 1");
        mainPanel.add(page2Panel, "Page 2");

        cardLayout.show(mainPanel, "Perfil");

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FixedMenuExample();
            }
        });
    }
    private String userType;

    public FixedMenuExample(String userType) {
        this.userType = userType;

    }

}
