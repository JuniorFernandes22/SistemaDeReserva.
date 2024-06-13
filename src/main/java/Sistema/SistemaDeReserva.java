package Sistema;

import Modelo.Hotel;
import Modelo.Quarto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeReserva {
    private List<Hotel> hoteis;

    public SistemaDeReserva(List<Hotel> hoteis) {
        this.hoteis = hoteis;
    }

    public List<Quarto> pesquisarHoteis(String localizacao, LocalDate dataEstadia) {
        List<Quarto> quartosDisponiveis = new ArrayList<>();
        for (Hotel hotel : hoteis) {
            List<Quarto> quartosDoHotel = hotel.getAcomodacoesDisponiveis(dataEstadia);
            for (Quarto quarto : quartosDoHotel) {
                if (quarto.getLocalizacao().equals(localizacao) && quarto.estaDisponivel(dataEstadia)) {
                    quartosDisponiveis.add(quarto);
                }
            }
        }
        return quartosDisponiveis;
    }
}
