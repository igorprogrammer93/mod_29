package dao;
import Database.Database;
import model.Venda;
import java.util.*;

public class VendaDAO {
    public void salvar(Venda venda) throws Exception {
        var conn = Database.getConnection();
        var sql = "INSERT INTO venda (cliente_id, produto_id, quantidade) VALUES (?, ?, ?)";
        var stmt = conn.prepareStatement(sql);
        stmt.setInt(1, venda.getClienteId());
        stmt.setInt(2, venda.getProdutoId());
        stmt.setInt(3, venda.getQuantidade());
        stmt.executeUpdate();
        conn.close();
    }

    public List<Venda> listar() throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM venda");
        List<Venda> vendas = new ArrayList<>();
        while (rs.next()) {
            Venda v = new Venda();
            v.setId(rs.getInt("id"));
            v.setClienteId(rs.getInt("cliente_id"));
            v.setProdutoId(rs.getInt("produto_id"));
            v.setQuantidade(rs.getInt("quantidade"));
            vendas.add(v);
        }
        conn.close();
        return vendas;
    }

    public Venda buscar(int id) throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.prepareStatement("SELECT * FROM venda WHERE id = ?");
        stmt.setInt(1, id);
        var rs = stmt.executeQuery();
        Venda v = null;
        if (rs.next()) {
            v = new Venda();
            v.setId(rs.getInt("id"));
            v.setClienteId(rs.getInt("cliente_id"));
            v.setProdutoId(rs.getInt("produto_id"));
            v.setQuantidade(rs.getInt("quantidade"));
        }
        conn.close();
        return v;
    }

    public void atualizar(Venda venda) throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.prepareStatement("UPDATE venda SET cliente_id = ?, produto_id = ?, quantidade = ? WHERE id = ?");
        stmt.setInt(1, venda.getClienteId());
        stmt.setInt(2, venda.getProdutoId());
        stmt.setInt(3, venda.getQuantidade());
        stmt.setInt(4, venda.getId());
        stmt.executeUpdate();
        conn.close();
    }
}
