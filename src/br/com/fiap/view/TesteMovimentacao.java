package br.com.fiap.view;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.MovimentacaoDAO;
import br.com.fiap.model.dto.Movimentacao;

import java.sql.Connection;

public class TesteMovimentacao {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.abrirConexao();
        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(connection);

        Movimentacao movimentacao = new Movimentacao("Reabastecimento", "Entrada", 20, "8");
        movimentacao.setIdMovimentacao(1);
        // System.out.println(movimentacaoDAO.inserir(movimentacao));

//        movimentacao.setMotivo("Vencimento");
//
//        System.out.println(movimentacaoDAO.atualizar(movimentacao));

        Movimentacao movimentacao1 = new Movimentacao("Transferência", "Saída", 12, "8");
        movimentacao1.setIdMovimentacao(2);
//        System.out.println(movimentacaoDAO.inserir(movimentacao1));

        System.out.println(movimentacaoDAO.deletar(movimentacao1));

        movimentacaoDAO.listarTodos();

        ConnectionFactory.fecharConexao(connection);
    }
}
