package ModeloDAO;

import Conexao.ConnectionFactory;
import Modelo.Cliente;
import Modelo.Hotel;
import Modelo.Reserva;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void criar(Reserva reserva) {
        String sql = "INSERT INTO Reservas (idCliente, idHotel, dataCheckIn, dataCheckOut, valorTotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, reserva.getIdCliente());
            smt.setInt(2, reserva.getIdHotel());
            smt.setDate(3, java.sql.Date.valueOf(reserva.getDataCheckIn()));
            smt.setDate(4, java.sql.Date.valueOf(reserva.getDataCheckOut()));
            smt.setDouble(5, reserva.getValorTotal());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reservado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao reservar: " + e.getMessage());
        }
    }

    public List<Reserva> lerReservas() throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM Reservas ORDER BY dataCheckIn";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql);
             ResultSet resultado = smt.executeQuery()) {

            while (resultado.next()) {
                int idReserva = resultado.getInt("id");
                int clienteId = resultado.getInt("idCliente");
                int hotelId = resultado.getInt("idHotel");
                LocalDate dataCheckIn = resultado.getDate("dataCheckIn").toLocalDate();
                LocalDate dataCheckOut = resultado.getDate("dataCheckOut").toLocalDate();
                double valorTotal = resultado.getDouble("valorTotal");

                Cliente cliente = obterClientePorId(clienteId);
                Hotel hotel = obterHotelPorId(hotelId);

                Reserva reserva = new Reserva(idReserva, cliente.getId(), hotel.getId(), dataCheckIn, dataCheckOut);

            }
        }

        return lista;
    }

    private Cliente obterClientePorId(int clienteId) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE id = ?";
        Cliente cliente = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, clienteId);
            try (ResultSet resultado = smt.executeQuery()) {
                if (resultado.next()) {
                    String nome = resultado.getString("nome");
                    String email = resultado.getString("email");
                    String telefone = resultado.getString("telefone");
                    String senha = resultado.getString("senha");
                    cliente = new Cliente( nome, email, telefone, senha);
                }
            }
        }

        return cliente;
    }

    private Hotel obterHotelPorId(int hotelId) throws SQLException {
        String sql = "SELECT * FROM Hoteis WHERE id = ?";
        Hotel hotel = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, hotelId);
            try (ResultSet resultado = smt.executeQuery()) {
                if (resultado.next()) {
                    String nome = resultado.getString("nome_hotel");
                    String localizacao = resultado.getString("localizacao");
                    String Preco_por_noite = resultado.getString("Preco_por_noite");
                    hotel = new Hotel( nome, localizacao, Preco_por_noite);
                }
            }
        }

        return hotel;
    }

    public void atualizar(Reserva reserva, int id) {
        String sql = "UPDATE Reservas SET idCliente = ?, idHotel = ?, dataCheckIn = ?, dataCheckOut = ?, valorTotal = ? WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, reserva.getIdCliente());
            smt.setInt(2, reserva.getIdHotel());
            smt.setDate(3, java.sql.Date.valueOf(reserva.getDataCheckIn()));
            smt.setDate(4, java.sql.Date.valueOf(reserva.getDataCheckOut()));
            smt.setDouble(5, reserva.getValorTotal());
            smt.setInt(6, id);
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reserva atualizada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar reserva: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Reservas WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setInt(1, id);
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reserva removida com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover reserva: " + e.getMessage());
        }
    }

    public List<Reserva> lerReservasPorCliente(int idCliente) {

        return null;
    }



}
