package Modelo;

import Modelo.Acomodacao;

import java.util.ArrayList;
import java.util.List;

public class GerenciarAcomodacoes {
    private List<Acomodacao> acomodacoes;

    public GerenciarAcomodacoes() {
        acomodacoes = new ArrayList<>();
    }

    public void adicionarAcomodacao(Acomodacao acomodacao) {
        acomodacoes.add(acomodacao);
    }

    public void editarAcomodacao(int index, Acomodacao novosDados) {
        if (index >= 0 && index < acomodacoes.size()) {
            acomodacoes.set(index, novosDados);
        }
    }

    public void removerAcomodacao(int index) {
        if (index >= 0 && index < acomodacoes.size()) {
            acomodacoes.remove(index);
        }
    }

    public List<Acomodacao> listarAcomodacoes() {
        return acomodacoes;
    }
}
