import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import model.Cliente;
import model.Produto;
import model.Venda;

public class Main {
    public static void main(String[] args) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            VendaDAO vendaDAO = new VendaDAO();

            Cliente cliente = new Cliente();
            cliente.setNome("Lucas");
            cliente.setEmail("lucas@email.com");
            clienteDAO.salvar(cliente);

            Produto produto = new Produto();
            produto.setNome("Notebook");
            produto.setPreco(3500.00);
            produtoDAO.salvar(produto);

            Venda venda = new Venda();
            venda.setClienteId(1);
            venda.setProdutoId(1);
            venda.setQuantidade(2);
            vendaDAO.salvar(venda);

            System.out.println("Clientes cadastrados:");
            clienteDAO.listar().forEach(c -> System.out.println(c.getNome() + " - " + c.getEmail()));

            System.out.println("Produtos cadastrados:");
            produtoDAO.listar().forEach(p -> System.out.println(p.getNome() + " - R$" + p.getPreco()));

            System.out.println("Vendas cadastradas:");
            vendaDAO.listar().forEach(v -> System.out.println("Venda ID: " + v.getId() + " | Cliente: " + v.getClienteId() + " | Produto: " + v.getProdutoId() + " | Quantidade: " + v.getQuantidade()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
