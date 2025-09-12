package br.com.fiap.dao;

import br.com.fiap.dto.Movimentacao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovimentacaoDAO {
    private Connection connection;

    public MovimentacaoDAO() {

    }

    public Connection getConnection() {
        return connection;
    }

    public String inserir(Movimentacao movimentacao) {
        String sqlQuery  = "INSERT INTO MOVIMENTACOES(idMovimentacao, motivo, data, dataHoraEntrada, dataHoraSaida, tipoMovimentacao, quantidade, funcionario) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, Integer.toString(movimentacao.getIdMovimentacao()));
            preparedStatement.setString(2, movimentacao.getMotivo());
            preparedStatement.setDate(3, Date.valueOf(movimentacao.getData()));
            preparedStatement.setString(4, movimentacao.getDataHoraEntrada().toString());
            preparedStatement.setString(5, movimentacao.getDataHoraSaida().toString());
            preparedStatement.setString(6, movimentacao.getMotivo());
            preparedStatement.setInt(7, movimentacao.getQuantidade());
            preparedStatement.setString(8, movimentacao.getFuncionario());

            if (preparedStatement.executeUpdate() > 0) {
                return "Registro inserido com sucesso!";
            }

            return "Erro ao inserir. Tente novamente mais tarde";

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public void read() {
        String sqlQuery = "SELECT * FROM MOVIMENTACOES";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {
            ResultSet result = preparedStatement.executeQuery(sqlQuery);

            // Rever
            Movimentacao movimentacao;
//            while (result.next()) {
//                movimentacao = new Movimentacao (
//                        result.getString("motivo"),
//                        LocalDate.parse(result.getString("data")),
//                        result.getString("tipoMovimentacao"),
//                        result.getInt("quantidade"),
//                        result.getString("dataHoraEntrada"),
//                        result.getString("funcionario")
//                );
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
