package Menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Conexao.ConnectionFactory;
import java.util.Date;

public class TelaReservaHotel extends JPanel implements ActionListener {

    private JPanel painelPrincipal;
    private JDateChooser dateChooserCheckIn;
    private JDateChooser dateChooserCheckOut;

    public TelaReservaHotel() {
        setLayout(new BorderLayout());

        painelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("images/background.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelPrincipal.setLayout(new BorderLayout());
        add(painelPrincipal, BorderLayout.CENTER);

        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setBackground(new Color(70, 130, 180)); // Azul
        JLabel titulo = new JLabel("Sistema de Reserva de Hotel", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painelCabecalho.add(titulo, BorderLayout.CENTER);
        painelCabecalho.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);

        JPanel painelCalendario = new JPanel(new GridLayout(1, 4, 10, 10));
        painelCalendario.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelCalendario.setOpaque(false);

        dateChooserCheckIn = new JDateChooser();
        dateChooserCheckOut = new JDateChooser();

        painelCalendario.add(new JLabel("Check-in:"));
        painelCalendario.add(dateChooserCheckIn);
        painelCalendario.add(new JLabel("Check-out:"));
        painelCalendario.add(dateChooserCheckOut);

        painelPrincipal.add(painelCalendario, BorderLayout.NORTH);

        JPanel painelQuartos = new JPanel();
        painelQuartos.setLayout(new BoxLayout(painelQuartos, BoxLayout.Y_AXIS));
        painelQuartos.setOpaque(false);

        painelQuartos.add(criarPainelQuarto("Hotel Praia Azul", "Localização: Avenida dos Hotéis, Praia\nClassificação: 9.2/10\nPreço: CVE 6500/noite", "images/quarto_praia_azul.jpg"));
        painelQuartos.add(criarPainelQuarto("Hotel Oásis Atlântico", "Localização: Avenida Cidade de Lisboa, Mindelo\nClassificação: 8.5/10\nPreço: CVE 7500/noite", "images/quarto_oasis_atlantico.jpg"));
        painelQuartos.add(criarPainelQuarto("Hotel Porto Grande", "Localização: Avenida Marginal, Mindelo\nClassificação: 9.0/10\nPreço: CVE 9500/noite", "images/quarto_porto_grande.jpg")
        );

        JScrollPane scrollPane = new JScrollPane(painelQuartos);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setBackground(new Color(70, 130, 180)); // Azul
        JLabel rodape = new JLabel("Informações sobre a empresa", SwingConstants.CENTER);
        rodape.setForeground(Color.WHITE);
        rodape.setFont(new Font("Arial", Font.PLAIN, 14));
        painelRodape.add(rodape, BorderLayout.CENTER);
        painelRodape.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);
    }

    private JPanel criarPainelQuarto(String titulo, String informacoes, String imagem) {
        JPanel painelQuarto = new JPanel(new BorderLayout());
        painelQuarto.setBorder(new LineBorder(Color.BLACK, 1, true));
        painelQuarto.setBackground(Color.WHITE);
        painelQuarto.setMaximumSize(new Dimension(700, 200));

        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon imageIcon = new ImageIcon(imagem);
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(image));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.WEST;
        painelConteudo.add(labelImagem, gbc);

        JLabel labelTitulo = new JLabel(titulo, SwingConstants.LEFT);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        painelConteudo.add(labelTitulo, gbc);

        JLabel labelInformacoes = new JLabel("<html>" + informacoes.replace("\n", "<br>") + "</html>");
        labelInformacoes.setFont(new Font("Arial", Font.PLAIN, 14));
        labelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelConteudo.add(labelInformacoes, gbc);

        painelQuarto.add(painelConteudo, BorderLayout.CENTER);

        JButton botaoReservar = new JButton("Reservar");
        botaoReservar.setBackground(new Color(255, 165, 0));
        botaoReservar.setFont(new
                Font("Arial", Font.BOLD, 14));
        botaoReservar.setFocusPainted(false);
        botaoReservar.addActionListener(this);

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(botaoReservar);

        painelQuarto.add(painelBotao, BorderLayout.SOUTH);

        return painelQuarto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date dataCheckIn = dateChooserCheckIn.getDate();
        Date dataCheckOut = dateChooserCheckOut.getDate();

        if (dataCheckIn == null || dataCheckOut == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione as datas de check-in e check-out.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO reserva ( IdHotel, dataCheckIn, dataCheckOut, valorTotal) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, 1); // Aqui você deve definir o IdHotel corretamente
                stmt.setDate(2, new java.sql.Date(dataCheckIn.getTime()));
                stmt.setDate(3, new java.sql.Date(dataCheckOut.getTime()));
                stmt.setDouble(4, 1000.0); // ValorTotal

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Reserva efetuada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao efetuar a reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    public JPanel getMainPanel() {
        return painelPrincipal;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Reserva de Hotel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new TelaReservaHotel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


}
