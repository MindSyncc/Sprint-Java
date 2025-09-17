package br.com.fiap.model.dao;

import br.com.fiap.model.dto.EstoqueInsumo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueInsumoDAO {
    private Connection connection;

    public EstoqueInsumoDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(EstoqueInsumo estoqueInsumo) {
        String sql = "INSERT INTO estoque_insumo (id_estoque, id_insumo, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, estoqueInsumo.getIdEstoque());
            preparedStatement.setInt(2, estoqueInsumo.getIdInsumo());
            preparedStatement.setInt(3, estoqueInsumo.getQuantidade());

            if (preparedStatement.executeUpdate() > 0) {
                return "EstoqueInsumo inserido com sucesso!";
            } else {
                return "Erro ao inserir EstoqueInsumo.";
            }

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(EstoqueInsumo estoqueInsumo) {
        String sql = "UPDATE estoque_insumo SET quantidade=? WHERE id_estoque=? AND id_insumo=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

            preparedStatement.setInt(1, estoqueInsumo.getQuantidade());
            preparedStatement.setInt(2, estoqueInsumo.getIdEstoque());
            preparedStatement.setInt(3, estoqueInsumo.getIdInsumo());

            if (preparedStatement.executeUpdate() > 0) {
                return "EstoqueInsumo atualizado com sucesso!";
            } else {
                return "Erro ao atualizar EstoqueInsumo.";
            }

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String deletar(int idEstoque, int idInsumo) {
        String sql = "DELETE FROM estoque_insumo WHERE id_estoque=? AND id_insumo=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idEstoque);
            ps.setInt(2, idInsumo);

            if (ps.executeUpdate() > 0) {
                return "EstoqueInsumo deletado com sucesso!";
            } else {
                return "Erro ao deletar EstoqueInsumo.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public EstoqueInsumo listarUm(int idEstoque, int idInsumo) {
        EstoqueInsumo estoqueInsumo = null;

        String sql = "SELECT * FROM estoque_insumo WHERE id_estoque=? AND id_insumo=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idEstoque);
            ps.setInt(2, idInsumo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                estoqueInsumo =  new EstoqueInsumo(
                        rs.getInt("id_estoque"),
                        rs.getInt("id_insumo"),
                        rs.getInt("quantidade")
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return estoqueInsumo;
    }


    public List<EstoqueInsumo> listarTodos() {
        List<EstoqueInsumo> lista = new ArrayList<>();
        String sql = "SELECT * FROM estoque_insumo";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EstoqueInsumo estoqueInsumo = new EstoqueInsumo(
                        rs.getInt("id_estoque"),
                        rs.getInt("id_insumo"),
                        rs.getInt("quantidade")
                );
                lista.add(estoqueInsumo);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return lista;
    }
}
