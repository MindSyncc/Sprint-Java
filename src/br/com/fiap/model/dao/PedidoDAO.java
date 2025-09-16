package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Pedido;

import java.sql.*;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    // CREATE
    public String inserir(Pedido pedido) {
        String insert = "INSERT INTO PEDIDO(ID_PEDIDO, QUANTIDADE, NOME_ITEM, DATA_PEDIDO, STATUS, ID_FUNCIONARIO, ID_FORNECEDOR) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insert)) {
            preparedStatement.setInt(1, pedido.getIdDoPedido());
            preparedStatement.setInt(2, pedido.getQtdItem());
            preparedStatement.setString(3, pedido.getNomeItem());
            preparedStatement.setDate(4, Date.valueOf(pedido.getDataPedido()));
            preparedStatement.setString(5, pedido.getStatus());
            preparedStatement.setInt(6, pedido.getIdFuncionario());
            preparedStatement.setInt(7, pedido.getIdFornecedor());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro inserido com sucesso!";
            }

            return "Erro ao inserir";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // READ
    public void listarTodos() {
        String read = "SELECT * FROM PEDIDO";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(read)) {
            ResultSet resultado = preparedStatement.executeQuery();

            Pedido pedido;
            while (resultado.next()) {
                pedido = new Pedido(resultado.getInt("ID_PEDIDO"),
                        resultado.getInt("QUANTIDADE"),
                        resultado.getString("NOME_ITEM"),
                        resultado.getDate("DATA_PEDIDO").toLocalDate(),
                        resultado.getString("STATUS"),
                        resultado.getInt("ID_FUNCIONARIO"),
                        resultado.getInt("ID_FORNECEDOR"));

                pedido.exibirInformacoesDoPedido();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void listarUm(Pedido pedido) {
        String sqlQuery = "SELECT * FROM PEDIDO WHERE ID_PEDIDO=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, pedido.getIdDoPedido());
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                pedido = new Pedido(resultado.getInt("ID_PEDIDO"),
                        resultado.getInt("QUANTIDADE"),
                        resultado.getString("NOME_ITEM"),
                        resultado.getDate("DATA_PEDIDO").toLocalDate(),
                        resultado.getString("STATUS"),
                        resultado.getInt("ID_FUNCIONARIO"),
                        resultado.getInt("ID_FORNECEDOR"));

                pedido.exibirInformacoesDoPedido();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // UPDATE
    public String atualizar(Pedido pedido) {
        String update = "UPDATE PEDIDO SET QUANTIDADE=?, NOME_ITEM=?, DATA_PEDIDO=?, STATUS=?, ID_FUNCIONARIO=?,ID_FORNECEDOR=? WHERE ID_PEDIDO=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(update)) {

            preparedStatement.setInt(1, pedido.getQtdItem());
            preparedStatement.setString(2, pedido.getNomeItem());
            preparedStatement.setDate(3, Date.valueOf(pedido.getDataPedido()));
            preparedStatement.setString(4, pedido.getStatus());
            preparedStatement.setInt(5, pedido.getIdFuncionario());
            preparedStatement.setInt(6, pedido.getIdFornecedor());
            preparedStatement.setInt(7, pedido.getIdDoPedido());

            if (preparedStatement.executeUpdate() > 0) {
                return "Atualização realizada com sucesso!";
            }

            return "Erro ao atualizar o pedido";

        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // DELETE
    public String deletar(Pedido pedido) {
        String delete = "DELETE FROM PEDIDO WHERE ID_PEDIDO = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(delete)) {
            preparedStatement.setInt(1, pedido.getIdDoPedido());

            if (preparedStatement.executeUpdate() > 0) {
                return "Remoção realizada com sucesso!";
            }

            return "Erro ao remover registro";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
