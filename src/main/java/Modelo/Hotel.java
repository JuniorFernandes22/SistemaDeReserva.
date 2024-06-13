package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int IdHotel;
    private String nomeHotel;
    private String localizacaoHotel;

    private String preco_por_noiteHotel;
    private List<Quarto> acomodacoesHotel;

    public Hotel(String nome, String localizacao, String preco_por_noite) {
        this.nomeHotel = nome;
        this.localizacaoHotel = localizacao;
        this.preco_por_noiteHotel = preco_por_noite;
        this.acomodacoesHotel = new ArrayList<>();
    }

    public int getId() {
        return IdHotel;
    }

    public String getNome() {
        return nomeHotel;
    }

    public String getLocalizacao() {
        return localizacaoHotel;
    }

    public String getpreco_por_noite() { return preco_por_noiteHotel; }

    public List<Quarto> getAcomodacoes() {
        return acomodacoesHotel;
    }

    public void adicionarAcomodacao(Quarto acomodacao) {
        this.acomodacoesHotel.add(acomodacao);
    }

    public List<Quarto> getAcomodacoesDisponiveis(LocalDate dataEstadia) {
        List<Quarto> disponiveis = new ArrayList<>();
        for (Quarto acomodacao : acomodacoesHotel) {
            if (acomodacao.estaDisponivel(dataEstadia)) {
                disponiveis.add(acomodacao);
            }
        }
        return disponiveis;
    }

    public void exibirInformacoes() {
        System.out.println("Nome do Hotel: " + getNome());
        System.out.println("Localização do Hotel: " + getLocalizacao());
        System.out.println("Preço Por Noite " + getpreco_por_noite());
    }


}
