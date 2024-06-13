package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.ConnectionFactory;
import Modelo.Utilizador;

public class UtilizadorDAO {
    public void criar(Utilizador utilizador) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO UTILIZADOR (nome, email, telefone, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, utilizador.getNome());
            smt.setString(2, utilizador.getEmail());
            smt.setString(3, utilizador.getTelefone());
            smt.setString(4, utilizador.getSenha());
            smt.execute();
            JOptionPane.showMessageDialog(null, "Utilizador criado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar utilizador: " + e.getMessage());
        }
    }

    public List<Utilizador> lerUtilizadores() {
        Connection con = ConnectionFactory.getConnection();
        List<Utilizador> lista = new ArrayList<>();
        String sql = "SELECT * FROM UTILIZADOR";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            ResultSet resultado = smt.executeQuery();
            while (resultado.next()) {
                Utilizador utilizador = new Utilizador(resultado.getString("nome"), resultado.getString("email"), resultado.getString("telefone"), "");
                lista.add(utilizador);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler utilizadores: " + e.getMessage());
        }

        return lista;
    }
}
