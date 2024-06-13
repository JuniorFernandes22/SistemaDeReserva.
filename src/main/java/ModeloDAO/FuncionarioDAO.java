package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.ConnectionFactory;
import Modelo.Funcionario;


public class FuncionarioDAO {
    public static void atualizar() {
    }

    public void criar(Funcionario Funcionario) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO Funcionario (nome, email, telefone,senha,cargo ) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, Funcionario.getNome());
            smt.setString(2, Funcionario.getEmail());
            smt.setString(3, Funcionario.getTelefone());
            smt.setString(4, Funcionario.getSenha());
            smt.setString(5, Funcionario.getCargo());
            smt.execute();
            JOptionPane.showMessageDialog(null, "Funcionario criado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar Funcionario: " + e.getMessage());
        }
    }

    public List<Funcionario> lerFuncionarios() {
        Connection con = (Connection) ConnectionFactory.getConnection();// Altere aqui para usar a nova classe de conexão
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM FUNCIONARIOS";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            ResultSet resultado = smt.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario( resultado.getString("nome"), resultado.getString("email"), resultado.getString("telefone"), resultado.getString("senha"), resultado.getString("cargo"));
                lista.add(funcionario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler funcionários: " + e.getMessage());
        }

        return lista;
    }

    public static void atualizar(Funcionario funcionario, int id) {
        Connection con = (Connection) ConnectionFactory.getConnection(); // Altere aqui para usar a nova classe de conexão
        String sql = "UPDATE FUNCIONARIOS SET nome = ?, email = ?, telefone = ?, senha = ?, cargo = ? WHERE id = ?";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, funcionario.getNome());
            smt.setString(2, funcionario.getEmail());
            smt.setString(3, funcionario.getTelefone());
            smt.setString(4, funcionario.getSenha());
            smt.setString(5, funcionario.getCargo());
            smt.setInt(6, id);
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    public void remover(int id) {
        Connection con = (Connection) ConnectionFactory.getConnection(); // Altere aqui para usar a nova classe de conexão
        String sql = "DELETE FROM FUNCIONARIOS WHERE id = ?";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, id);
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover funcionário: " + e.getMessage());
        }
    }
}
