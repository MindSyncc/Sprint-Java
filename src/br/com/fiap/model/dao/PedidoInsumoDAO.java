package br.com.fiap.model.dao;

import br.com.fiap.model.dto.PedidoInsumo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoInsumoDAO {
    private Connection connection;

    public PedidoInsumoDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(PedidoInsumo pedidoInsumo) {
        String sql = "INSERT INTO pedido_insumo (id_pedido, id_insumo, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, pedidoInsumo.getIdPedido());
            preparedStatement.setInt(2, pedidoInsumo.getIdInsumo());
            preparedStatement.setInt(3, pedidoInsumo.getQuantidade());

            if (preparedStatement.executeUpdate() > 0) {
                return "PedidoInsumo inserido com sucesso!";
            } else {
                return "Erro ao inserir PedidoInsumo.";
            }

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(PedidoInsumo pedidoInsumo) {
        String sql = "UPDATE pedido_insumo SET quantidade=? WHERE id_pedido=? AND id_insumo=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, pedidoInsumo.getQuantidade());
            preparedStatement.setInt(2, pedidoInsumo.getIdPedido());
            preparedStatement.setInt(3, pedidoInsumo.getIdInsumo());

            if (preparedStatement.executeUpdate() > 0) {
                return "PedidoInsumo atualizado com sucesso!";
            } else {
                return "Erro ao atualizar PedidoInsumo.";
            }

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String deletar(int idPedido, int idInsumo) {
        String sql = "DELETE FROM pedido_insumo WHERE id_pedido=? AND id_insumo=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, idInsumo);

            if (preparedStatement.executeUpdate() > 0) {
                return "PedidoInsumo deletado com sucesso!";
            } else {
                return "Erro ao deletar PedidoInsumo.";
            }

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public PedidoInsumo listarUm(int idPedido, int idInsumo) {
        PedidoInsumo pedidoInsumo = null;
        String sql = "SELECT * FROM pedido_insumo WHERE id_pedido=? AND id_insumo=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, idInsumo);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                pedidoInsumo = new PedidoInsumo(
                        result.getInt("id_pedido"),
                        result.getInt("id_insumo"),
                        result.getInt("quantidade")
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return pedidoInsumo;
    }

    public List<PedidoInsumo> listarTodos() {
        List<PedidoInsumo> lista = new ArrayList<>();
        String sql = "SELECT * from pedido_insumo";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
             ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()) {
                PedidoInsumo pedidoInsumo = new PedidoInsumo(
                        result.getInt("id_pedido"),
                        result.getInt("id_insumo"),
                        result.getInt("quantidade")
                );
                lista.add(pedidoInsumo);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return lista;
    }
}
