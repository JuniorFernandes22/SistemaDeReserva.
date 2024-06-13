package Modelo;

import Interface.ReservavelDAO;

public class Suite extends Acomodacao implements ReservavelDAO {
    private boolean reservado;

    public Suite(String tipo, int quantidadeLeitos, double precoBase) {
        super(tipo, quantidadeLeitos, precoBase);
        this.reservado = false;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    @Override
    public void reservar() {
        this.reservado = true;
    }

    @Override
    public void cancelarReserva() {
        this.reservado = false;
    }

    @Override
    public double calcularPreco() {
        double preco = getPrecoBase();
        if (this.reservado) {
            preco += 20.0;
        }
        return preco;
    }
}
