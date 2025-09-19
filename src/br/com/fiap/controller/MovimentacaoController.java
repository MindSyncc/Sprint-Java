package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.MovimentacaoDAO;
import br.com.fiap.model.dto.Movimentacao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MovimentacaoController {

    public String inserir(Movimentacao movimentacao) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.abrirConexao();
        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(con);

        String saida = movimentacaoDAO.inserir(movimentacao);

        ConnectionFactory.fecharConexao(con);
        return saida;
    }

    public String atualizar(Movimentacao movimentacao) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.abrirConexao();
        MovimentacaoDAO dao = new MovimentacaoDAO(con);

        String saida = dao.atualizar(movimentacao);

        ConnectionFactory.fecharConexao(con);
        return saida;
    }

    public List<Movimentacao> listarTodos() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.abrirConexao();
        MovimentacaoDAO dao = new MovimentacaoDAO(con);

        List<Movimentacao> lista = dao.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return lista;
    }

    public Movimentacao listarUm(int idMovimentacao) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.abrirConexao();
        MovimentacaoDAO dao = new MovimentacaoDAO(con);

        Movimentacao movimentacao = dao.listarUm(idMovimentacao);

        ConnectionFactory.fecharConexao(con);
        return movimentacao;
    }

    public String deletar(int idMovimentacao) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.abrirConexao();
        MovimentacaoDAO dao = new MovimentacaoDAO(con);

        String saida = dao.deletar(idMovimentacao);

        ConnectionFactory.fecharConexao(con);
        return saida;
    }
}
