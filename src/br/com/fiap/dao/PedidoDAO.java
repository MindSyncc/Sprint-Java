package br.com.fiap.dao;

import br.com.fiap.dto.Pedido;

import java.sql.*;
import java.time.LocalDate;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    // CREATE
    public String createPedido(Pedido pedido) {
        String insert = "INSERT INTO PEDIDOS(idPedido, QtdItem, NomeItem, DataPedido, status, idFuncionario, idFornecedor) VALUES(?, ?, ?, ?, ?, ?, ?)";

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
    public void readPedido() {
        String read = "SELECT * FROM PEDIDOS";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(read)) {
            ResultSet resultado = preparedStatement.executeQuery();

            Pedido pedido;
            while (resultado.next()) {
                pedido = new Pedido(resultado.getInt("idPedido"),
                        resultado.getInt("QtdItem"),
                        resultado.getString("NomeItem"),
                        LocalDate.parse(resultado.getString("DataPedido")),
                        resultado.getString("status"),
                        resultado.getInt("idFuncionario"),
                        resultado.getInt("idFornecedor"));

                pedido.exibirInformacoesDoPedido();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // UPDATE
    public String updatePedido(Pedido pedido) {
        String update = "UPDATE PEDIDOS SET QtdItem=?, NomeItem=?, DataPedido=?, status=?, idFuncionario=?,idFornecedor=? WHERE idDoPedido =?";

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
    public String deletePedido(Pedido pedido) {
        String delete = "DELETE FROM PEDIDOS WHERE idDoPedido = ?";

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
