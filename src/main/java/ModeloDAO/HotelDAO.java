package ModeloDAO;

import Conexao.ConnectionFactory;
import Modelo.Hotel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    public void criar(Hotel hotel) {
        String sql = "INSERT INTO hotel (nome, localizacao, preco_por_noite ) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, hotel.getNome());
            smt.setString(2, hotel.getLocalizacao());
            smt.setString(3, hotel.getpreco_por_noite());
            smt.execute();
            smt.close();
            JOptionPane.showMessageDialog(null, "Hotel criado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar hotel: " + e.getMessage());
        }
    }

    public List<Hotel> lerHoteis() {
        List<Hotel> lista = new ArrayList<>();
        String sql = "SELECT * FROM HOTEIS";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql);
             ResultSet resultado = smt.executeQuery()) {
            while (resultado.next()) {
                Hotel hotel = new Hotel(resultado.getString("nome_hotel"), resultado.getString("localizacao"), resultado.getString("Preco_por_noite") );
                lista.add(hotel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler hotéis: " + e.getMessage());
        }

        return lista;
    }

    public void atualizar(Hotel hotel, int id) {
        String sql = "UPDATE HOTEIS SET nome_hotel = ?, localizacao = ?, Preco_por_noite= ?WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, hotel.getNome());
            smt.setString(2, hotel.getLocalizacao());
            smt.setString(2, hotel.getpreco_por_noite());
            smt.setInt(3, id);
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hotel atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar hotel: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM HOTEIS WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, id);
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hotel removido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover hotel: " + e.getMessage());
        }
    }
}
