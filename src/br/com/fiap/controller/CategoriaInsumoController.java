package br.com.fiap.controller;

import br.com.fiap.model.dao.CategoriaInsumoDAO;
import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dto.CategoriaInsumo;

import java.sql.Connection;
import java.util.List;

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

    public List<CategoriaInsumo> listarTodasCategorias() {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        List<CategoriaInsumo> categorias = categoriaInsumoDAO.listarTodos();

        ConnectionFactory.fecharConexao(connection);
        return categorias;
    }

    public CategoriaInsumo listarUmaCategoria(CategoriaInsumo categoriaInsumo) {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        CategoriaInsumo categoriaInsumoDB = categoriaInsumoDAO.listarUm(categoriaInsumo);

        ConnectionFactory.fecharConexao(connection);

        return categoriaInsumoDB;
    }

    public String deletarCategoria(CategoriaInsumo categoriaInsumo) {
        Connection connection = ConnectionFactory.abrirConexao();
        categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        String resultado = categoriaInsumoDAO.deletar(categoriaInsumo);

        ConnectionFactory.fecharConexao(connection);

        return resultado;
    }
}
