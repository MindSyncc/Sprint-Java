package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.PedidoInsumoDAO;
import br.com.fiap.model.dto.PedidoInsumo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PedidoInsumoController {

    public String inserirPedidoInsumo(PedidoInsumo pedidoInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoInsumoDAO pedidoInsumoDAO = new PedidoInsumoDAO(con);

        String resultado = pedidoInsumoDAO.inserir(pedidoInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String atualizarPedidoInsumo(PedidoInsumo pedidoInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoInsumoDAO pedidoInsumoDAO = new PedidoInsumoDAO(con);

        String resultado = pedidoInsumoDAO.atualizar(pedidoInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String deletarPedidoInsumo(int idPedido, int idInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoInsumoDAO pedidoInsumoDAO = new PedidoInsumoDAO(con);

        String resultado = pedidoInsumoDAO.deletar(idPedido, idInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public PedidoInsumo listarUmPedidoInsumo(int idPedido, int idInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoInsumoDAO pedidoInsumoDAO = new PedidoInsumoDAO(con);

        PedidoInsumo resultado = pedidoInsumoDAO.listarUm(idPedido, idInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public List<PedidoInsumo> listarTodosPedidoInsumos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoInsumoDAO pedidoInsumoDAO = new PedidoInsumoDAO(con);

        List<PedidoInsumo> lista = pedidoInsumoDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return lista;
    }

    public List<PedidoInsumo> listarPorPedido(int idPedido) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoInsumoDAO pedidoInsumoDAO = new PedidoInsumoDAO(con);

        List<PedidoInsumo> lista = pedidoInsumoDAO.listarPorPedido(idPedido);

        ConnectionFactory.fecharConexao(con);
        return lista;
    }
}
