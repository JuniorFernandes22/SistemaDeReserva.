package Modelo;

public class Funcionario extends Utilizador {
    private String cargo;
    public Funcionario( String nome, String email, String telefone, String senha, String cargo) {
        super(nome, email, telefone, senha);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Cargo: " + getCargo());
    }
}
