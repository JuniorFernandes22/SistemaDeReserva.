package Sistema;



import java.util.HashMap;
import java.util.Map;
import Modelo.Utilizador;
import Modelo.Cliente;
import Modelo.Funcionario;

public class SistemaAutenticacao {
    private Map<String, Utilizador> utilizadores;

    public SistemaAutenticacao() {
        this.utilizadores = new HashMap<>();
    }

    // Método para registrar um utilizador
    public void registrarUtilizador(Utilizador utilizador) {
        utilizadores.put(utilizador.getEmail(), utilizador);
    }

    // Método para autenticar um utilizador
    public Utilizador autenticar(String email, String senha) {
        Utilizador utilizador = utilizadores.get(email);
        if (utilizador != null && utilizador.getSenha().equals(senha)) {
            return utilizador;
        }
        return null;
    }

    public static void main(String[] args) {
        SistemaAutenticacao sistema = new SistemaAutenticacao();

        Cliente cliente = new Cliente( "EliaSilva", "eliatita@example.com", "9878687", "senha123");
        Funcionario funcionario = new Funcionario( "NelsonOliveira", "netxon@example.com", "9876543", "senha456", "");

        sistema.registrarUtilizador(cliente);
        sistema.registrarUtilizador(funcionario);

        sistema.registrarUtilizador(cliente);
        sistema.registrarUtilizador(funcionario);

        // Autenticando utilizadores
        Utilizador user1 = sistema.autenticar("Elia@example.com", "senha123");
        if (user1 != null) {
            System.out.println("Autenticação bem-sucedida!");
            user1.getNome();
        } else {
            System.out.println("Falha na autenticação.");
        }

        Utilizador user2 = sistema.autenticar("Elia@example.com", "senha456");
        if (user2 != null) {
            System.out.println("Autenticação bem-sucedida!");
            user2.getNome();
        } else {
            System.out.println("Falha na autenticação.");
        }
    }
}

