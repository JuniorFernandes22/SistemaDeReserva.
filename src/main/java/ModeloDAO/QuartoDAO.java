package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.ConnectionFactory;
import Modelo.Quarto;

public class QuartoDAO {
    public void criar(Quarto quarto) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO QUARTOS (id_quarto, localizacao, disponivel) VALUES (?, ?, ?)";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, quarto.getIdQuarto());
            smt.setString(2, quarto.getLocalizacao());
            smt.setBoolean(3, quarto.estaDisponivel(null)); // Aqui, dataEstadia não é necessária

            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Quarto criado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar quarto: " + e.getMessage());
        }
    }

    public List<Quarto> lerQuartos() {
        Connection con = ConnectionFactory.getConnection();
        List<Quarto> lista = new ArrayList<>();
        String sql = "SELECT * FROM QUARTOS";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            ResultSet resultado = smt.executeQuery();
            while (resultado.next()) {
                Quarto quarto = new Quarto(
                        resultado.getInt("id_quarto"),
                        resultado.getString("localizacao")
                );
                quarto.setDisponibilidade(resultado.getBoolean("disponivel"));
                lista.add(quarto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler quartos: " + e.getMessage());
        }

        return lista;
    }

    public void atualizar(Quarto quarto) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE QUARTOS SET localizacao = ?, disponivel = ? WHERE id_quarto = ?";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, quarto.getLocalizacao());
            smt.setBoolean(2, quarto.estaDisponivel(null)); // Aqui, dataEstadia não é necessária
            smt.setInt(3, quarto.getIdQuarto());

            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Quarto atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar quarto: " + e.getMessage());
        }
    }

    public void remover(int idQuarto) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM QUARTOS WHERE id_quarto = ?";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, idQuarto);

            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Quarto removido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover quarto: " + e.getMessage());
        }
    }
}
