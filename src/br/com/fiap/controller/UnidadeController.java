package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UnidadeDAO;
import br.com.fiap.dto.Unidade;

import java.sql.Connection;
import java.sql.SQLException;

public class UnidadeController {

    public String inserirUnidade(Unidade unidade) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        UnidadeDAO unidadeDAO = new UnidadeDAO(con);

        String resultado = unidadeDAO.inserir(unidade);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String atualizarUnidade(Unidade unidade) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        UnidadeDAO unidadeDAO = new UnidadeDAO(con);

        String resultado = unidadeDAO.atualizar(unidade);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String deletarUnidade(Unidade unidade) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        UnidadeDAO unidadeDAO = new UnidadeDAO(con);

        String resultado = unidadeDAO.deletar(unidade);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public void listarUmaUnidade(Unidade unidade) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        UnidadeDAO unidadeDAO = new UnidadeDAO(con);

        unidadeDAO.listarUm(unidade);

        ConnectionFactory.fecharConexao(con);
    }

    public void listarTodasUnidades() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        UnidadeDAO unidadeDAO = new UnidadeDAO(con);

        unidadeDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
    }
}
