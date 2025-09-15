package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UnidadeDAO;
import br.com.fiap.dto.Unidade;

import java.sql.Connection;

public class TesteUnidade {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.abrirConexao();
        UnidadeDAO unidadeDAO = new UnidadeDAO(connection);

        Unidade unidade = new Unidade(2, "Lab Teste", "Rua A", "21", "Bairro A", "Cidade A", "AS", "02000001", 1);

//        System.out.println(unidadeDAO.inserir(unidade));
//        unidadeDAO.listarTodos();
//
//        unidade.setNomeUnidade("Lab Testes");
//        System.out.println(unidadeDAO.atualizar(unidade));
//
//        unidadeDAO.listarUm(unidade);
//
        unidadeDAO.listarTodos();
        System.out.println(unidadeDAO.deletar(unidade));

        ConnectionFactory.fecharConexao(connection);
    }
}
