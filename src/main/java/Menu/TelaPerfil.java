package Menu;

import Modelo.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaPerfil extends JPanel {

    private JLabel labelNome;
    private JLabel labelEmail;
    private JButton botaoLogout;
    private JTable tabelaReservas;
    private JButton botaoEditar;
    private JButton botaoCancelar;
    private JPanel painelPrincipal;

    private String nomeUsuario;
    private String emailUsuario;
    private List<Reserva> reservas;

    public TelaPerfil() {
        initComponents();
        reservas = new ArrayList<>();
    }

    private void initComponents() {
        painelPrincipal = new JPanel(new BorderLayout());
        setLayout(new BorderLayout());
        add(painelPrincipal, BorderLayout.CENTER);

        JPanel panelUsuario = new JPanel(new GridLayout(2, 1));
        labelNome = new JLabel("Nome do Usuário");
        labelEmail = new JLabel("Email do Usuário");
        panelUsuario.add(labelNome);
        panelUsuario.add(labelEmail);

        botaoLogout = new JButton("Logout");
        botaoLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel panelLogout = new JPanel();
        panelLogout.add(botaoLogout);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelUsuario, BorderLayout.CENTER);
        panelSuperior.add(panelLogout, BorderLayout.SOUTH);

        tabelaReservas = new JTable(new DefaultTableModel(new Object[]{"ID", "Data Check-in", "Data Check-out"}, 0));

        JScrollPane scrollPane = new JScrollPane(tabelaReservas);

        botaoEditar = new JButton("Editar Reserva");
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar reserva
            }
        });

        botaoCancelar = new JButton("Cancelar Reserva");
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.add(botaoEditar);
        panelBotoes.add(botaoCancelar);

        painelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(panelBotoes, BorderLayout.SOUTH);
    }

    public void setInformacoes(String nome, String email) {
        labelNome.setText("Nome: " + nome);
        labelEmail.setText("Email: " + email);
    }

    public JPanel getMainPanel() {
        return painelPrincipal;
    }

}

