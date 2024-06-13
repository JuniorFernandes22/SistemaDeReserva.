package Modelo;

public class Cliente extends Utilizador {

    public Cliente(  String nome, String email, String telefone, String senha) {
        super( nome, email, telefone, senha);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getTelefone() {
        return super.getTelefone();
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
    }
}
