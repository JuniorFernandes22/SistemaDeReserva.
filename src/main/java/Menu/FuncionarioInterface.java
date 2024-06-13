package Menu;

import Modelo.Acomodacao;
import Modelo.GerenciarAcomodacoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class FuncionarioInterface extends JFrame {
    private GerenciarAcomodacoes gerenciador;
    private JTextArea acomodacoesTextArea;

    public FuncionarioInterface() {
        gerenciador = new GerenciarAcomodacoes();

        setTitle("Interface do Funcionário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(176, 196, 222));

        JLabel welcomeLabel = new JLabel("Bem-vindo, Funcionário!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        JButton addButton = new JButton("Adicionar Acomodação");
        addButton.addActionListener(e -> addAccommodation());
        buttonPanel.add(addButton);

        JButton editButton = new JButton("Editar Acomodação");
        editButton.addActionListener(e -> editAccommodation());
        buttonPanel.add(editButton);

        JButton removeButton = new JButton("Remover Acomodação");
        removeButton.addActionListener(e -> removeAccommodation());
        buttonPanel.add(removeButton);

        JButton listButton = new JButton("Listar Acomodações");
        listButton.addActionListener(e -> refreshAccommodationsList());
        buttonPanel.add(listButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        acomodacoesTextArea = new JTextArea();
        acomodacoesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(acomodacoesTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        refreshAccommodationsList();
    }

    private void addAccommodation() {
        JTextField tipoField = new JTextField(10);
        JTextField leitosField = new JTextField(5);
        JTextField precoField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Tipo:"));
        panel.add(tipoField);
        panel.add(new JLabel("Quantidade de Leitos:"));
        panel.add(leitosField);
        panel.add(new JLabel("Preço Base:"));
        panel.add(precoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Acomodação", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String tipo = tipoField.getText();
            int quantidadeLeitos = Integer.parseInt(leitosField.getText());
            double precoBase = Double.parseDouble(precoField.getText());

            Acomodacao acomodacao = new Acomodacao(tipo, quantidadeLeitos, precoBase);
            gerenciador.adicionarAcomodacao(acomodacao);

            refreshAccommodationsList(); // Atualiza a lista após adicionar a acomodação
        }
    }

    private void editAccommodation() {
        String indexStr = JOptionPane.showInputDialog(this, "Informe o índice da Acomodação:");
        if (indexStr != null && !indexStr.isEmpty()) {
            int index = Integer.parseInt(indexStr);
            List<Acomodacao> acomodacoes = gerenciador.listarAcomodacoes();
            if (index >= 0 && index < acomodacoes.size()) {
                Acomodacao acomodacao = acomodacoes.get(index);
                JTextField tipoField = new JTextField(acomodacao.getTipo(), 10);
                JTextField leitosField = new JTextField(String.valueOf(acomodacao.getQuantidadeLeitos()), 5);
                JTextField precoField = new JTextField(String.valueOf(acomodacao.getPrecoBase()), 10);

                JPanel panel = new JPanel(new GridLayout(3, 2));
                panel.add(new JLabel("Tipo:"));
                panel.add(tipoField);
                panel.add(new JLabel("Quantidade de Leitos:"));
                panel.add(leitosField);
                panel.add(new JLabel("Preço Base:"));
                panel.add(precoField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Editar Acomodação", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    acomodacao.setTipo(tipoField.getText());
                    acomodacao.setQuantidadeLeitos(Integer.parseInt(leitosField.getText()));
                    acomodacao.setPrecoBase(Double.parseDouble(precoField.getText()));
                    gerenciador.editarAcomodacao(index, acomodacao);

                    refreshAccommodationsList(); // Atualiza a lista após editar a acomodação
                }
            } else {
                JOptionPane.showMessageDialog
                        (this, "Acomodação não encontrada.");
            }
        }
    }

    private void removeAccommodation() {
        String indexStr = JOptionPane.showInputDialog(this, "Informe o índice da Acomodação:");
        if (indexStr != null && !indexStr.isEmpty()) {
            int index = Integer.parseInt(indexStr);
            List<Acomodacao> acomodacoes = gerenciador.listarAcomodacoes();
            if (index >= 0 && index < acomodacoes.size()) {
                gerenciador.removerAcomodacao(index);

                refreshAccommodationsList(); // Atualiza a lista após remover a acomodação
            } else {
                JOptionPane.showMessageDialog(this, "Acomodação não encontrada.");
            }
        }
    }

    private void refreshAccommodationsList() {
        List<Acomodacao> acomodacoes = gerenciador.listarAcomodacoes();
        StringBuilder message = new StringBuilder("Acomodações:\n");
        for (int i = 0; i < acomodacoes.size(); i++) {
            Acomodacao acomodacao = acomodacoes.get(i);
            String disponibilidade = acomodacao.isDisponivel(LocalDate.now()) ? "Disponível" : "Não disponível";
            message.append("ID: ").append(i).append(", ")
                    .append("Tipo: ").append(acomodacao.getTipo()).append(", ")
                    .append("Leitos: ").append(acomodacao.getQuantidadeLeitos()).append(", ")
                    .append("Preço: ").append(acomodacao.getPrecoBase()).append(", ")
                    .append("Disponibilidade: ").append(disponibilidade).append("\n");
        }
        acomodacoesTextArea.setText(message.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FuncionarioInterface().setVisible(true));
    }
}
