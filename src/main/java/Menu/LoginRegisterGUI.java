package Menu;

import Conexao.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginRegisterGUI extends JFrame {

    private Connection connection;
    private JButton loginButton;
    private JTextField loginUserField;
    private JPasswordField loginPassField;



    public LoginRegisterGUI() {
        setTitle("Login e Registro");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        connection = ConnectionFactory.getConnection();

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        JTextField loginUserField = new JTextField(15);
        loginPanel.add(loginUserField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        JPasswordField loginPassField = new JPasswordField(15);
        loginPanel.add(loginPassField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton, gbc);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridBagLayout());
        registerPanel.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        JTextField registerNameField = new JTextField(15);
        registerPanel.add(registerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(new JLabel("E-mail:"), gbc);

        gbc.gridx = 1;
        JTextField registerEmailField = new JTextField(15);
        registerPanel.add(registerEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        JTextField registerTelefoneField = new JTextField(15);
        registerPanel.add(registerTelefoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        JPasswordField registerPassField = new JPasswordField(15);
        registerPanel.add(registerPassField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        registerPanel.add(new JLabel("Tipo:"), gbc);

        gbc.gridx = 1;
        String[] tipos = {"Cliente", "Funcionario"};
        JComboBox<String> tipoComboBox = new JComboBox<>(tipos);
        registerPanel.add(tipoComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerButton = new JButton("Registrar");
        registerPanel.add(registerButton, gbc);

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Registro", registerPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        add(mainPanel);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginUserField.getText();
                String password = new String(loginPassField.getPassword());
                String tipo = validateLogin(username, password);
                if (tipo != null) {
                    if (tipo.equals("Cliente")) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido como Cliente!");
                        openPerfil(username);
                        dispose();
                    } else if (tipo.equals("Funcionario")) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido como Funcionário!");
                        SwingUtilities.invokeLater(() -> {
                            FuncionarioInterface funcionarioInterface = new FuncionarioInterface();
                            funcionarioInterface.setVisible(true);
                            dispose();
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                }
            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = registerNameField.getText();
                String email = registerEmailField.getText();
                String telefone = registerTelefoneField.getText();
                String password = new String(registerPassField.getPassword());
                String userType = (String) tipoComboBox.getSelectedItem();
                if (registerUser(name, email, telefone, password, userType)) {
                    JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar usuário!");
                }
            }
        });

    }

    String validateLogin(String nome, String password) {
        try {
            String sql = "SELECT tipo FROM Utilizador WHERE nome = ? AND senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("tipo");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void openPerfil(String nome, String tipo) {
        if (tipo != null && tipo.equals("Funcionario")) {
            SwingUtilities.invokeLater(() -> new FuncionarioInterface().setVisible(true));
            dispose();
        } else {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Perfil do Usuário");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TelaPerfil telaPerfil = new TelaPerfil();
                frame.getContentPane().add(telaPerfil);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        }
    }


    private void openPerfil(String nome) {
        boolean isCliente = verificarTipoUsuario(nome, "Cliente");

        if (isCliente) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Reserva de Hotel");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    TelaReservaHotel telaReservaHotel = new TelaReservaHotel();
                    frame.getContentPane().add(telaReservaHotel);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Perfil do Usuário");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    TelaPerfil telaPerfil = new TelaPerfil();

                    String nomeUsuario = "";
                    String emailUsuario = "";
                    try {
                        String sql = "SELECT nome, email FROM utilizador WHERE nome = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, nome);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            nomeUsuario = resultSet.getString("nome");
                            emailUsuario = resultSet.getString("email");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    telaPerfil.setInformacoes(nomeUsuario, emailUsuario);
                    frame.getContentPane().add(telaPerfil);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
        }
    }

    private boolean verificarTipoUsuario(String nome, String tipo) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM utilizador WHERE nome = ? AND tipo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, tipo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    private boolean registerUser(String nome, String email, String telefone, String password, String tipo) {
        try {
            String sql = "INSERT INTO Utilizador (nome, email, telefone, senha, tipo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, telefone);
            statement.setString(4, password);
            statement.setString(5, tipo);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginRegisterGUI().setVisible(true);
            }
        });
    }
    public void addLoginButtonActionListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    public String getLoginUsername() {
        return loginUserField.getText();
    }
    public String getLoginPassword() {
        return new String(loginPassField.getPassword());
    }
    public JButton getLoginButton() {
        return loginButton;
    }
}

