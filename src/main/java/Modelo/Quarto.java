package Modelo;

import java.time.LocalDate;

public class Quarto {
    private final int idQuarto;
    private final String localizacao;
    private  boolean disponivel;

    public Quarto(int id, String localizacao) {
        this.idQuarto = id;
        this.localizacao = localizacao;
        this.disponivel = true; // Por padrão, o quarto está disponível
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public boolean estaDisponivel(LocalDate dataEstadia) {
        // Implementação de lógica para verificar disponibilidade na dataEstadia
        return disponivel; // Retorna true para simplicidade
    }

    public void setDisponibilidade(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void exibirInformacoes() {
        System.out.println("ID do Quarto: " + getIdQuarto());
        System.out.println("Localização do Quarto: " + getLocalizacao());
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
    }
}
