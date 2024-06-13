package Servicos;

import java.util.List;

import Modelo.Cliente;
import ModeloDAO.ClienteDAO;

public class ClienteS {
    static ClienteDAO clienteDAO = new ClienteDAO();

    public ClienteS() {
    }

    public static void criarCliente(String nome, String email, String telefone){
        Cliente novoCliente = new Cliente( nome, email, telefone, null);
        clienteDAO.criar(novoCliente);
    }

    public static void lerClientes(){
        List<Cliente> lista = clienteDAO.lerClientes();
        for (Cliente cliente : lista) {
            clienteDAO.lerClientes();
        }
    }

    public void atualizarCliente(int id, String nome, String email, String telefone) throws InterruptedException {
        // Note que o método wait é chamado na instância do ClienteS, o que é incomum.
        // Provavelmente você queria chamar o método wait na thread atual, não no ClienteS.
        String senha = null;
        ClienteS cliente = new ClienteS();
        cliente.wait(id);
        clienteDAO.atualizar(cliente);
    }

    public void removerCliente(int id){
        clienteDAO.remover(id);
    }

    public String getNome() {
        return null;
    }

    public String getEmail() {
        return null;
    }

    public String getTelefone() {
        return null;
    }
}
