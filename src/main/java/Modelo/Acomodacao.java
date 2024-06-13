package Modelo;

import java.time.LocalDate;

public class Acomodacao {
    private int id;
    private String tipo;
    private int quantidadeLeitos;
    private double precoBase;
    private boolean reservado;

    public Acomodacao(String tipo, int quantidadeLeitos, double precoBase) {
        this.tipo = tipo;
        this.quantidadeLeitos = quantidadeLeitos;
        this.precoBase = precoBase;
        this.reservado = false; // Inicialmente não reservado
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeLeitos() {
        return quantidadeLeitos;
    }

    public void setQuantidadeLeitos(int quantidadeLeitos) {
        this.quantidadeLeitos = quantidadeLeitos;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public boolean isDisponivel(LocalDate dataEstadia) {
        // Implementação fictícia para disponibilidade
        return !reservado; // Acomodação disponível se não estiver reservada
    }
}
