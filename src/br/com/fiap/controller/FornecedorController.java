package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.FornecedorDAO;
import br.com.fiap.dto.Fornecedor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FornecedorController {

    public String inserirFornecedor(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FornecedorDAO fornecedorDAO = new FornecedorDAO(con);

        String resultado = fornecedorDAO.inserir(fornecedor);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String atualizarFornecedor(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FornecedorDAO fornecedorDAO = new FornecedorDAO(con);

        String resultado = fornecedorDAO.atualizar(fornecedor);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String deletarFornecedor(int idFornecedor) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FornecedorDAO fornecedorDAO = new FornecedorDAO(con);

        String resultado = fornecedorDAO.deletar(idFornecedor);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String listarUmFornecedor(int idFornecedor) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FornecedorDAO fornecedorDAO = new FornecedorDAO(con);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(idFornecedor);

        String resultado = fornecedorDAO.listarUm(fornecedor);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public List<Fornecedor> listarTodosFornecedores() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FornecedorDAO fornecedorDAO = new FornecedorDAO(con);

        List<Fornecedor> fornecedores = fornecedorDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return fornecedores;
    }
}
