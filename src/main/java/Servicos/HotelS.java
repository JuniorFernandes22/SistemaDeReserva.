package Servicos;

import java.util.List;
import Modelo.Hotel;
import ModeloDAO.HotelDAO;

public class HotelS {
    private static HotelDAO hotelDAO = new HotelDAO();

    public void criarHotel(String nome, String localizacao, String precopornoite) {
        Hotel hotel = new Hotel(nome, localizacao, precopornoite);
        hotelDAO.criar(hotel);
    }

    public void lerHoteis() {
        List<Hotel> lista = hotelDAO.lerHoteis();
        for (Hotel hotel : lista) {
            System.out.println(hotel.getNome() + " - " + hotel.getLocalizacao() + " - " + hotel.getpreco_por_noite());
        }
    }

    public void atualizarHotel(int id, String nome, String localizacao, String precopornoite) {
        Hotel hotel = new Hotel(nome, localizacao, precopornoite);
        hotelDAO.atualizar(hotel, id);
    }

    public void removerHotel(int id) {
        hotelDAO.remover(id);
    }
}
