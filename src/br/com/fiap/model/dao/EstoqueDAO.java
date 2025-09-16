package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO {
    private Connection connection;

    public EstoqueDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(Estoque estoque) {
        String sqlQuery = "INSERT INTO ESTOQUE(ID_ESTOQUE, QTDATUAL, QTDMINIMA, QTDMAXIMA, STATUS) VALUES (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, estoque.getIdEstoque());
            preparedStatement.setInt(2, estoque.getQtdAtual());
            preparedStatement.setInt(3, estoque.getQtdMinima());
            preparedStatement.setInt(4, estoque.getQtdMaxima());
            preparedStatement.setString(5, estoque.getStatus());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro de estoque inserido com sucesso!";
            }

            return "Não foi possível registrar o estoque";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(Estoque estoque) {
        String sqlQuery = "UPDATE ESTOQUE SET QTDATUAL=?, QTDMINIMA=?, QTDMAXIMA=?, STATUS=? WHERE ID_ESTOQUE=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, estoque.getQtdAtual());
            preparedStatement.setInt(2, estoque.getQtdMinima());
            preparedStatement.setInt(3, estoque.getQtdMaxima());
            preparedStatement.setString(4, estoque.getStatus());
            preparedStatement.setInt(5, estoque.getIdEstoque());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro de estoque atualizado com sucesso!";
            }

            return "Não foi possível atualizar o registro";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public void listarTodos() {
        String sqlQuery = "SELECT * FROM ESTOQUE";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            ResultSet result = preparedStatement.executeQuery();

            Estoque estoque;
            while (result.next()) {
                estoque = new Estoque(result.getInt("ID_ESTOQUE"),
                        result.getInt("QTDATUAL"),
                        result.getInt("QTDMINIMA"),
                        result.getInt("QTDMAXIMA"),
                        result.getString("STATUS"));

                estoque.exibirInformacoesDoEstoque();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void listarUm(Estoque estoque) {
        String sqlQuery = "SELECT * FROM ESTOQUE WHERE ID_ESTOQUE=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, estoque.getIdEstoque());
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                estoque = new Estoque(result.getInt("ID_ESTOQUE"),
                        result.getInt("QTDATUAL"),
                        result.getInt("QTDMINIMA"),
                        result.getInt("QTDMAXIMA"),
                        result.getString("STATUS"));

                estoque.exibirInformacoesDoEstoque();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String deletar(Estoque estoque) {
        String sqlQuery = "DELETE FROM ESTOQUE WHERE ID_ESTOQUE=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, estoque.getIdEstoque());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro de estoque removido com sucesso!";
            }

            return "Não foi possível remover o registro";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
}
