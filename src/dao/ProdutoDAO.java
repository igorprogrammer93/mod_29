package dao;
import Database.Database;
import model.Produto;
import java.util.*;

public class ProdutoDAO {
    public void salvar(Produto produto) throws Exception {
        var conn = Database.getConnection();
        var sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";
        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.executeUpdate();
        conn.close();
    }

    public List<Produto> listar() throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM produto");
        List<Produto> produtos = new ArrayList<>();
        while (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));
            produtos.add(p);
        }
        conn.close();
        return produtos;
    }

    public Produto buscar(int id) throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.prepareStatement("SELECT * FROM produto WHERE id = ?");
        stmt.setInt(1, id);
        var rs = stmt.executeQuery();
        Produto p = null;
        if (rs.next()) {
            p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));
        }
        conn.close();
        return p;
    }

    public void atualizar(Produto produto) throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.prepareStatement("UPDATE produto SET nome = ?, preco = ? WHERE id = ?");
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setInt(3, produto.getId());
        stmt.executeUpdate();
        conn.close();
    }
}