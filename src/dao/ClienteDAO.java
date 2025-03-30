package dao;
import Database.Database;

import model.Cliente;
import java.util.*;

public class ClienteDAO {
    public void salvar(Cliente cliente) throws Exception {
        var conn = Database.getConnection();
        var sql = "INSERT INTO cliente (nome, email) VALUES (?, ?)";
        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.executeUpdate();
        conn.close();
    }

    public List<Cliente> listar() throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM cliente");
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente c = new Cliente();
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            clientes.add(c);
        }
        conn.close();
        return clientes;
    }

    public Cliente buscar(int id) throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?");
        stmt.setInt(1, id);
        var rs = stmt.executeQuery();
        Cliente c = null;
        if (rs.next()) {
            c = new Cliente();
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
        }
        conn.close();
        return c;
    }

    public void atualizar(Cliente cliente) throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.prepareStatement("UPDATE cliente SET nome = ?, email = ? WHERE id = ?");
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setInt(3, cliente.getId());
        stmt.executeUpdate();
        conn.close();
    }
}