package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.PedidoDAO;
import br.com.fiap.model.dto.Pedido;

import java.sql.Connection;
import java.sql.SQLException;

public class PedidoController {

    public String inserirPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoDAO pedidoDAO = new PedidoDAO(con);

        String resultado = pedidoDAO.inserir(pedido);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String atualizarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoDAO pedidoDAO = new PedidoDAO(con);

        String resultado = pedidoDAO.atualizar(pedido);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String deletarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoDAO pedidoDAO = new PedidoDAO(con);

        String resultado = pedidoDAO.deletar(pedido);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public void listarUmPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoDAO pedidoDAO = new PedidoDAO(con);

        pedidoDAO.listarUm(pedido);

        ConnectionFactory.fecharConexao(con);
    }

    public void listarTodosPedidos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        PedidoDAO pedidoDAO = new PedidoDAO(con);

        pedidoDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
    }
}
