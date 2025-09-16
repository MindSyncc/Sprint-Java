package br.com.fiap.view;

import br.com.fiap.model.dao.CategoriaInsumoDAO;
import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dto.CategoriaInsumo;

import java.sql.Connection;

public class CategoriaInsumoTeste {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.abrirConexao();
        CategoriaInsumoDAO categoriaInsumoDAO = new CategoriaInsumoDAO(connection);

        CategoriaInsumo categoriaInsumo = new CategoriaInsumo(2, "Reagentes");

        System.out.println(categoriaInsumoDAO.inserir(categoriaInsumo));

        categoriaInsumoDAO.listarUm(categoriaInsumo);

        categoriaInsumo.setTipoCategoria("Plástico");
        System.out.println(categoriaInsumoDAO.atualizar(categoriaInsumo));
        categoriaInsumoDAO.listarTodos();

        System.out.println(categoriaInsumoDAO.deletar(categoriaInsumo));
        categoriaInsumoDAO.listarTodos();

        ConnectionFactory.fecharConexao(connection);
    }
}

