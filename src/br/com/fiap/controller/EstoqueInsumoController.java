package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.EstoqueInsumoDAO;
import br.com.fiap.model.dto.EstoqueInsumo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EstoqueInsumoController {

    public String inserirEstoqueInsumo(EstoqueInsumo estoqueInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        EstoqueInsumoDAO estoqueInsumoDAO = new EstoqueInsumoDAO(con);

        String resultado = estoqueInsumoDAO.inserir(estoqueInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String atualizarEstoqueInsumo(EstoqueInsumo estoqueInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        EstoqueInsumoDAO estoqueInsumoDAO = new EstoqueInsumoDAO(con);

        String resultado = estoqueInsumoDAO.atualizar(estoqueInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String deletarEstoqueInsumo(int idEstoque, int idInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        EstoqueInsumoDAO estoqueInsumoDAO = new EstoqueInsumoDAO(con);

        String resultado = estoqueInsumoDAO.deletar(idEstoque, idInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public EstoqueInsumo listarUmEstoqueInsumo(int idEstoque, int idInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        EstoqueInsumoDAO estoqueInsumoDAO = new EstoqueInsumoDAO(con);

        EstoqueInsumo resultado = estoqueInsumoDAO.listarUm(idEstoque, idInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public List<EstoqueInsumo> listarTodosEstoqueInsumo() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        EstoqueInsumoDAO estoqueInsumoDAO = new EstoqueInsumoDAO(con);

        List<EstoqueInsumo> lista = estoqueInsumoDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return lista;
    }
}
