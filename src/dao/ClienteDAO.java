package dao;

import model.Cliente;
import Database.Database;
import java.sql.*;
import java.util.*;

public class ClienteDAO {
    public void salvar(Cliente cliente) throws Exception {
        var conn = Database.getConnection();
        var sql = "INSERT INTO cliente (nome, email, telefone) VALUES (?, ?, ?)";
        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getTelefone());
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
            c.setTelefone(rs.getString("telefone"));
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
            c.setTelefone(rs.getString("telefone"));
        }
        conn.close();
        return c;
    }

    public void atualizar(Cliente cliente) throws Exception {
        var conn = Database.getConnection();
        var stmt = conn.prepareStatement("UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE id = ?");
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getTelefone());
        stmt.setInt(4, cliente.getId());
        stmt.executeUpdate();
        conn.close();
    }
}
