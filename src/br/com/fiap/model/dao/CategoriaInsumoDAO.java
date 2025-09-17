package br.com.fiap.model.dao;

import br.com.fiap.model.dto.CategoriaInsumo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaInsumoDAO {
    private Connection connection;

    public CategoriaInsumoDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(CategoriaInsumo categoriaInsumo) {
        String sqlQuery = "INSERT INTO CATEGORIA(ID_CATEGORIA, TIPOCATEGORIA) VALUES (?,?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, categoriaInsumo.getIdCategoria());
            preparedStatement.setString(2, categoriaInsumo.getTipoCategoria());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro de categoria de insumo inserido com sucesso!";
            }

            return "Não foi possível registrar a categoria";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(CategoriaInsumo categoriaInsumo) {
        String sqlQuery = "UPDATE CATEGORIA SET TIPOCATEGORIA=? WHERE ID_CATEGORIA=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, categoriaInsumo.getTipoCategoria());
            preparedStatement.setInt(2, categoriaInsumo.getIdCategoria());


            if (preparedStatement.executeUpdate() > 0) {
                return "Registro de categoria de insumo atualizado com sucesso!";
            }

            return "Não foi possível atualizar o registro";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public List<CategoriaInsumo> listarTodos() {
        List<CategoriaInsumo> categorias = new ArrayList<>();
        String sqlQuery = "SELECT * FROM CATEGORIA";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                categorias.add(new CategoriaInsumo(
                        result.getInt("ID_CATEGORIA"),
                        result.getString("TIPOCATEGORIA")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage());
        }

        return categorias;
    }

    public CategoriaInsumo listarUm(CategoriaInsumo categoriaInsumo) {
        String sqlQuery = "SELECT * FROM CATEGORIA WHERE ID_CATEGORIA=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, categoriaInsumo.getIdCategoria());
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                categoriaInsumo = new CategoriaInsumo(result.getInt("ID_CATEGORIA"),
                        result.getString("TIPOCATEGORIA"));

                categoriaInsumo.exibirInformacoesDoEstoque();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return categoriaInsumo;
    }

    public String deletar(CategoriaInsumo categoriaInsumo) {
        String sqlQuery = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, categoriaInsumo.getIdCategoria());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro de categoria removido com sucesso!";
            }

            return "Não foi possível remover o registro";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
}
