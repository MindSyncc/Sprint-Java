package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Unidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnidadeDAO {
    private Connection connection;

    public UnidadeDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(Unidade unidade) {
        String sqlQuery = "INSERT INTO UNIDADE(ID_UNIDADE, NOME_UNIDADE, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, ID_ESTOQUE) VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, unidade.getIdUnidade());
            preparedStatement.setString(2, unidade.getNomeUnidade());
            preparedStatement.setString(3, unidade.getRua());
            preparedStatement.setString(4, unidade.getNumero());
            preparedStatement.setString(5, unidade.getBairro());
            preparedStatement.setString(6, unidade.getCidade());
            preparedStatement.setString(7, unidade.getEstado());
            preparedStatement.setString(8, unidade.getCep());
            preparedStatement.setInt(9, unidade.getIdEstoque());

            if (preparedStatement.executeUpdate() > 0) {
                return "Unidade registrada com sucesso!";
            }

            return "Não foi possível registrar a unidade";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(Unidade unidade) {
        String sqlQuery = "UPDATE UNIDADE SET NOME_UNIDADE=?, RUA=?, NUMERO=?, BAIRRO=?, CIDADE=?, ESTADO=?, CEP=?, ID_ESTOQUE=? WHERE ID_UNIDADE=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, unidade.getNomeUnidade());
            preparedStatement.setString(2, unidade.getRua());
            preparedStatement.setString(3, unidade.getNumero());
            preparedStatement.setString(4, unidade.getBairro());
            preparedStatement.setString(5, unidade.getCidade());
            preparedStatement.setString(6, unidade.getEstado());
            preparedStatement.setString(7, unidade.getCep());
            preparedStatement.setInt(8, unidade.getIdEstoque());
            preparedStatement.setInt(9, unidade.getIdUnidade());

            if (preparedStatement.executeUpdate() > 0) {
                return "Unidade atualizada com sucesso!";
            }

            return "Não foi possível registrar a unidade";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public void listarTodos() {
        String sqlQuery = "SELECT * FROM UNIDADE";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            ResultSet result = preparedStatement.executeQuery();

            Unidade unidade;
            while (result.next()) {
                unidade = new Unidade(result.getInt("ID_UNIDADE"),
                        result.getString("NOME_UNIDADE"),
                        result.getString("RUA"),
                        result.getString("NUMERO"),
                        result.getString("BAIRRO"),
                        result.getString("CIDADE"),
                        result.getString("ESTADO"),
                        result.getString("CEP"),
                        result.getInt("ID_ESTOQUE"));

                unidade.exibirInformacoesDaUnidade();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void listarUm(Unidade unidade) {
        String sqlQuery = "SELECT * FROM UNIDADE WHERE ID_UNIDADE=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, unidade.getIdUnidade());
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                unidade = new Unidade(result.getInt("ID_UNIDADE"),
                        result.getString("NOME_UNIDADE"),
                        result.getString("RUA"),
                        result.getString("NUMERO"),
                        result.getString("BAIRRO"),
                        result.getString("CIDADE"),
                        result.getString("ESTADO"),
                        result.getString("CEP"),
                        result.getInt("ID_ESTOQUE"));

                unidade.exibirInformacoesDaUnidade();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String deletar(Unidade unidade) {
        String sqlQuery = "DELETE FROM UNIDADE WHERE ID_UNIDADE=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, unidade.getIdUnidade());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro removido com sucesso!";
            }

            return "Erro ao remover registro";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
}
