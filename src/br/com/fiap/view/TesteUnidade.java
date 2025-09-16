package br.com.fiap.view;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.UnidadeDAO;
import br.com.fiap.model.dto.Unidade;

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
