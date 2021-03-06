package br.edu.senac.kkcommerce.dao;

import br.edu.senac.kkcommerce.dao.util.ConnectionUtils;
import br.edu.senac.kkcommerce.model.Cliente;
import br.edu.senac.kkcommerce.model.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author While True
 */
public class ClienteDao implements IDaoBase<Cliente> {

    Connection conexao = null;

    @Override
    public int inserir(Cliente obj) throws SQLException, Exception {
        int idGerado = 0;
        String query = "INSERT INTO Cliente "
                + "(NOME, CPF, EMAIL, DATA_NASC, SEXO, TELEFONE, CELULAR, SENHA)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        try {
            conexao = ConnectionUtils.getConnection();
            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, obj.getNome());
            statement.setString(2, obj.getCpf());
            statement.setString(3, obj.getEmail());
            statement.setDate(4, Util.toSQLDate(obj.getDataNascimento()));
            statement.setString(5, obj.getSexo());
            statement.setString(6, obj.getTelefone());
            statement.setString(7, obj.getCelular());
            statement.setString(8, obj.getSenha());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }

        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }

            if (conexao != null || !conexao.isClosed()) {
                conexao.close();
            }
        }
        return idGerado;
    }

    @Override
    public void atualizar(Cliente obj) throws SQLException, Exception {
        String query = "UPDATE Cliente "
                + "SET NOME = ?, EMAIL = ?, SEXO = ?, TELEFONE = ?, CELULAR = ? "
                + "WHERE ID = ?";

        PreparedStatement statement = null;

        try {
            conexao = ConnectionUtils.getConnection();
            statement = conexao.prepareStatement(query);
            statement.setString(1, obj.getNome());
            statement.setString(2, obj.getEmail());
            statement.setString(3, obj.getSexo());
            statement.setString(4, obj.getTelefone());
            statement.setString(5, obj.getCelular());
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }

            if (conexao != null || !conexao.isClosed()) {
                conexao.close();
            }
        }
    }

    @Override
    public void excluir(int id) throws SQLException, Exception {
        String query = "UPDATE Cliente SET ATIVO = false WHERE ID = ?";

        PreparedStatement statement = null;

        try {

            conexao = ConnectionUtils.getConnection();
            statement = conexao.prepareStatement(query);
            statement.setInt(1, id);

        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }

            if (conexao != null || !conexao.isClosed()) {
                conexao.close();
            }
        }
    }

    @Override
    public List<Cliente> listar() throws SQLException, Exception {
        return null;
    }

    @Override
    public Cliente getById(int id) throws SQLException, Exception {
        return null;
    }

}
