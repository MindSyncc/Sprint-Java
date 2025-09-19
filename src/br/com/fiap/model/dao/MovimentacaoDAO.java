package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Movimentacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {
    private Connection connection;

    public MovimentacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(Movimentacao movimentacao) {
        String sqlQuery  = "INSERT INTO MOVIMENTACOES(MOTIVO, DATA_HORA_ENTRADA, DATA_HORA_SAIDA, TIPO_MOVIMENTACAO, QUANTIDADE, ID_FUNCIONARIO)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, movimentacao.getMotivo());

            if (movimentacao.getTipoMovimentacao().equalsIgnoreCase("ENTRADA")) {
                preparedStatement.setDate(2, Date.valueOf(movimentacao.getDataHoraEntrada().toLocalDate()));
                preparedStatement.setNull(3, java.sql.Types.DATE);
            } else { // SAÍDA
                preparedStatement.setNull(2, java.sql.Types.DATE);
                preparedStatement.setDate(3, Date.valueOf(movimentacao.getDataHoraSaida().toLocalDate()));
            }

            preparedStatement.setString(4, movimentacao.getTipoMovimentacao());
            preparedStatement.setInt(5, movimentacao.getQuantidade());
            preparedStatement.setInt(6, Integer.parseInt(movimentacao.getFuncionario()));

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro inserido com sucesso!";
            }

            return "Erro ao inserir. Tente novamente mais tarde";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(Movimentacao movimentacao) {
        String sqlQuery = "UPDATE MOVIMENTACOES SET MOTIVO=?, TIPO_MOVIMENTACAO=?, DATA_HORA_ENTRADA=?, DATA_HORA_SAIDA=?, QUANTIDADE=?, ID_FUNCIONARIO=?" +
                " WHERE ID_MOVIMENTACOES=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, movimentacao.getMotivo());
            preparedStatement.setString(2, movimentacao.getTipoMovimentacao());

            if (movimentacao.getTipoMovimentacao().equalsIgnoreCase("ENTRADA")) {
                preparedStatement.setDate(3, Date.valueOf(movimentacao.getDataHoraEntrada().toLocalDate()));
                preparedStatement.setNull(4, java.sql.Types.DATE);
            } else {
                preparedStatement.setNull(3, java.sql.Types.DATE);
                preparedStatement.setDate(4, Date.valueOf(movimentacao.getDataHoraSaida().toLocalDate()));
            }

            preparedStatement.setInt(5, movimentacao.getQuantidade());
            preparedStatement.setString(6, movimentacao.getFuncionario());
            preparedStatement.setInt(7, movimentacao.getIdMovimentacao());

            if (preparedStatement.executeUpdate() > 0) {
                return "Movimentação atualizada com sucesso!";
            }

            return "Erro ao atualizar uma movimentação";

        } catch (SQLException e) {
            return "Erro de SQL:" + e.getMessage();
        }
    }

    // 🔹 Agora retorna lista
    public List<Movimentacao> listarTodos() {
        String sqlQuery = "SELECT * FROM MOVIMENTACOES";
        List<Movimentacao> movimentacoes = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Movimentacao movimentacao = new Movimentacao(
                        result.getString("MOTIVO"),
                        result.getString("TIPO_MOVIMENTACAO"),
                        result.getInt("QUANTIDADE"),
                        result.getString("ID_FUNCIONARIO")
                );
                movimentacao.setIdMovimentacao(result.getInt("ID_MOVIMENTACOES"));

                movimentacoes.add(movimentacao);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar movimentações: " + e.getMessage());
        }

        return movimentacoes;
    }

    // 🔹 Agora retorna um objeto ou null
    public Movimentacao listarUm(int idMovimentacao) {
        String sqlQuery = "SELECT * FROM MOVIMENTACOES WHERE ID_MOVIMENTACOES=?";
        Movimentacao movimentacao = null;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, idMovimentacao);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                movimentacao = new Movimentacao(
                        result.getString("MOTIVO"),
                        result.getString("TIPO_MOVIMENTACAO"),
                        result.getInt("QUANTIDADE"),
                        result.getString("ID_FUNCIONARIO")
                );
                movimentacao.setIdMovimentacao(result.getInt("ID_MOVIMENTACOES"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar movimentação: " + e.getMessage());
        }

        return movimentacao;
    }

    public String deletar(int idMovimentacao) {
        String sqlQuery = "DELETE FROM MOVIMENTACOES WHERE ID_MOVIMENTACOES=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, idMovimentacao);

            if (preparedStatement.executeUpdate() > 0) {
                return "Movimentação removida com sucesso!";
            }

            return "Nenhuma movimentação encontrada com esse ID.";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
}
