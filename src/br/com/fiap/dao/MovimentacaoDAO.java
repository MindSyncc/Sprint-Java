package br.com.fiap.dao;

import br.com.fiap.dto.Movimentacao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovimentacaoDAO {
    private Connection connection;

    public MovimentacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(Movimentacao movimentacao) {
        String sqlQuery  = "INSERT INTO MOVIMENTACOES(ID_MOVIMENTACOES, MOTIVO, TIPO_MOVIMENTACAO, DATA_HORA_ENTRADA, DATA_HORA_SAIDA, QUANTIDADE, ID_FUNCIONARIO) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, movimentacao.getIdMovimentacao());
            preparedStatement.setString(2, movimentacao.getMotivo());
            preparedStatement.setString(3, movimentacao.getTipoMovimentacao());

            if (movimentacao.getTipoMovimentacao().equalsIgnoreCase("ENTRADA")) {
                preparedStatement.setDate(4, Date.valueOf(movimentacao.getDataHoraEntrada().toLocalDate()));
                preparedStatement.setNull(5, java.sql.Types.DATE);
            } else {
                preparedStatement.setNull(4, java.sql.Types.DATE);
                preparedStatement.setDate(5, Date.valueOf(movimentacao.getDataHoraSaida().toLocalDate()));
            }

            preparedStatement.setInt(6, movimentacao.getQuantidade());
            preparedStatement.setInt(7, Integer.parseInt(movimentacao.getFuncionario()));

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

    public void listarTodos() {
        String sqlQuery = "SELECT * FROM MOVIMENTACOES";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {
            ResultSet result = preparedStatement.executeQuery(sqlQuery);

            Movimentacao movimentacao;
            while (result.next()) {
                movimentacao = new Movimentacao (
                        result.getString("MOTIVO"),
                        result.getString("TIPO_MOVIMENTACAO"),
                        result.getInt("QUANTIDADE"),
                        result.getString("ID_FUNCIONARIO")
                );
                movimentacao.setIdMovimentacao(result.getInt("ID_MOVIMENTACOES"));

                String mensagem = movimentacao.exibirInformacoesDaMovimentacao();
                System.out.println(mensagem);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void listarUm(Movimentacao movimentacao) {
        String sqlQuery = "SELECT * FROM MOVIMENTACOES WHERE ID_MOVIMENTACOES=?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, movimentacao.getIdMovimentacao());

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                movimentacao = new Movimentacao (
                        result.getString("MOTIVO"),
                        result.getString("TIPO_MOVIMENTACAO"),
                        result.getInt("QUANTIDADE"),
                        result.getString("ID_FUNCIONARIO")
                );
                movimentacao.setIdMovimentacao(result.getInt("ID_MOVIMENTACOES"));

                movimentacao.exibirInformacoesDaMovimentacao();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String deletar(Movimentacao movimentacao) {
        String sqlQuery = "DELETE FROM MOVIMENTACOES WHERE ID_MOVIMENTACOES=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, movimentacao.getIdMovimentacao());

            if (preparedStatement.executeUpdate() > 0) {
                return "Movimentação removida com sucesso!";
            }

            return "Nenhuma movimentação encontrada com esse ID.";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
}
