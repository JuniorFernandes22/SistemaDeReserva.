package Servicos;

import Modelo.Reserva;
import ModeloDAO.ReservaDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservaS {
    static ReservaDAO reservaDAO = new ReservaDAO();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void criarReserva(int idCliente, int idHotel, String dataCheckIn, String dataCheckOut, double valorTotal) {
        LocalDate checkInDate = LocalDate.parse(dataCheckIn, formatter);
        LocalDate checkOutDate = LocalDate.parse(dataCheckOut, formatter);
        Reserva reserva = new Reserva(idCliente, idHotel, idHotel, checkInDate, checkOutDate);
        reserva.setValorTotal(valorTotal);
        reservaDAO.criar(reserva);
    }

    public void lerReservasPorCliente(int idCliente) {
        List<Reserva> lista = reservaDAO.lerReservasPorCliente(idCliente);
        for (Reserva reserva : lista) {
            System.out.println("Cliente: " + reserva.getIdCliente() +
                    " - Hotel: " + reserva.getIdHotel() +
                    " - Check-in: " + reserva.getDataCheckIn() +
                    " - Check-out: " + reserva.getDataCheckOut() +
                    " - Valor Total: " + reserva.getValorTotal());
        }
    }

    public void removerReserva(int id) {
        reservaDAO.remover(id);
    }
}
