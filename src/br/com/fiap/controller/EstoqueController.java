package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.EstoqueDAO;
import br.com.fiap.dto.Estoque;

import java.sql.Connection;

public class EstoqueController {
    EstoqueDAO estoqueDAO;

    public String inserirEstoque(Estoque estoque) {
        Connection connection = ConnectionFactory.abrirConexao();
        estoqueDAO = new EstoqueDAO(connection);

        String resultado = estoqueDAO.inserir(estoque);

        ConnectionFactory.fecharConexao(connection);

        return resultado;
    }

    public String atualizarEstoque(Estoque estoque) {
        Connection connection = ConnectionFactory.abrirConexao();
        estoqueDAO = new EstoqueDAO(connection);

        String resultado = estoqueDAO.atualizar(estoque);

        ConnectionFactory.fecharConexao(connection);

        return resultado;
    }

    public void listarTodosEstoques() {
        Connection connection = ConnectionFactory.abrirConexao();
        estoqueDAO = new EstoqueDAO(connection);

        estoqueDAO.listarTodos();

        ConnectionFactory.fecharConexao(connection);
    }

    public void listarUmEstoque(Estoque estoque) {
        Connection connection = ConnectionFactory.abrirConexao();
        estoqueDAO = new EstoqueDAO(connection);

        estoqueDAO.listarUm(estoque);

        ConnectionFactory.fecharConexao(connection);
    }

    public String deletarEstoque(Estoque estoque) {
        Connection connection = ConnectionFactory.abrirConexao();
        estoqueDAO = new EstoqueDAO(connection);

        String resultado = estoqueDAO.deletar(estoque);

        ConnectionFactory.fecharConexao(connection);

        return resultado;
    }
}
