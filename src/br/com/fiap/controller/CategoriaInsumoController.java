package br.com.fiap.controller;

import br.com.fiap.dao.CategoriaInsumoDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.EstoqueDAO;
import br.com.fiap.dto.CategoriaInsumo;
import br.com.fiap.dto.Estoque;

import java.sql.Connection;

public class CategoriaInsumoController {
    CategoriaInsumoDAO categoriaInsumoDAO;

    public String inserirCategoria(CategoriaInsumo categoriaInsumo) {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        String resultado = categoriaInsumoDAO.inserir(categoriaInsumo);

        ConnectionFactory.fecharConexao(connection);

        return resultado;
    }

    public String atualizarCategoria(CategoriaInsumo categoriaInsumo) {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        String resultado = categoriaInsumoDAO.atualizar(categoriaInsumo);

        ConnectionFactory.fecharConexao(connection);

        return resultado;
    }

    public void listarTodasCategorias() {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        categoriaInsumoDAO.listarTodos();

        ConnectionFactory.fecharConexao(connection);
    }

    public void listarUmaCategoria(CategoriaInsumo categoriaInsumo) {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        categoriaInsumoDAO.listarUm(categoriaInsumo);

        ConnectionFactory.fecharConexao(connection);
    }

    public String deletarCategoria(CategoriaInsumo categoriaInsumo) {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        String resultado = categoriaInsumoDAO.deletar(categoriaInsumo);

        ConnectionFactory.fecharConexao(connection);

        return resultado;
    }
}
