package br.com.fiap.main;

import br.com.fiap.controller.PedidoController;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.PedidoDAO;
import br.com.fiap.dto.Pedido;

import javax.swing.*;
import java.sql.Connection;
import java.time.LocalDate;

public class TestePedido {
    public static void main(String[] args) {

        Pedido pedido = new Pedido(1, 10, "Luvas", LocalDate.now(), "Andamento", 8, 1);
        pedido.setNomeItem("Seringas");
        System.out.println(pedidoDao.atualizar(pedido));

        Pedido pedido1 = new Pedido(2, 5, "Tubos de Ensaio", LocalDate.now(), "Andamento", 8, 1);
        System.out.println(pedidoDao.inserir(pedido1));

        pedidoDao.listarUm(pedido1);
        pedidoDao.listarTodos();

        System.out.println(pedidoDao.deletar(pedido1));

        ConnectionFactory.fecharConexao(connection);
    }
}
