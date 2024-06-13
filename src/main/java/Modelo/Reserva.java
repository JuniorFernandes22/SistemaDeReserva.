package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private int idReserva;
    private int idCliente;
    private  int idHotel;
    private List<Acomodacao> acomodacoes;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private double valorTotal;

    public Reserva(int idCliente, int idHotel, int id, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        this.idCliente = idCliente;
        this.idHotel = idHotel;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.acomodacoes = new ArrayList<>();
        this.valorTotal = calcularValorTotal();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }

    public LocalDate getDataCheckOut() {
        return dataCheckOut;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    private double calcularValorTotal() {
        double total = 0;
        long dias = dataCheckOut.toEpochDay() - dataCheckIn.toEpochDay();
        for (Acomodacao acomodacao : acomodacoes) {
            total += acomodacao.getPrecoBase() * dias;
        }
        return total;
    }

    public void listarAcomodacoes() {
        if (acomodacoes.isEmpty()) {
            System.out.println("Não há acomodações na reserva.");
        } else {
            System.out.println("------ ACOMODAÇÕES DA RESERVA ------");
            for (Acomodacao acomodacao : acomodacoes) {
                System.out.println("Tipo: " + acomodacao.getTipo() + ", Leitos: " + acomodacao.getQuantidadeLeitos() + ", Preço: " + acomodacao.getPrecoBase());
            }
            System.out.println("------------------------------------");
        }
    }
}



