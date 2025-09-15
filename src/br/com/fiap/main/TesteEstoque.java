package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.EstoqueDAO;
import br.com.fiap.dto.Estoque;

import java.sql.Connection;

public class TesteEstoque {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.abrirConexao();
        EstoqueDAO estoqueDAO = new EstoqueDAO(connection);

        Estoque estoque = new Estoque(2, 500, 30, 1000, "Disponível");

        System.out.println(estoqueDAO.inserir(estoque));

        estoqueDAO.listarUm(estoque);

        estoque.setQtdMaxima(1200);
        System.out.println(estoqueDAO.atualizar(estoque));
        estoqueDAO.listarTodos();

        System.out.println(estoqueDAO.deletar(estoque));
        estoqueDAO.listarTodos();

        ConnectionFactory.fecharConexao(connection);
    }
}
