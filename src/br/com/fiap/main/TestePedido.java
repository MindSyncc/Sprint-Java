package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.PedidoDAO;
import br.com.fiap.dto.Pedido;

import java.sql.Connection;
import java.time.LocalDate;

public class TestePedido {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.abrirConexao();
        PedidoDAO pedidoDao = new PedidoDAO(connection);

        Pedido pedido = new Pedido(1, 10, "Luvas", LocalDate.now(), "Andamento", 8, 2);

        System.out.println(pedidoDao.createPedido(pedido));

        ConnectionFactory.fecharConexao(connection);
    }
}
