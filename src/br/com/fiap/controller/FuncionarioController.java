package br.com.fiap.controller;

import br.com.fiap.dao.FuncionarioDAO;
import br.com.fiap.dto.Funcionario;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.FuncionarioFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    public boolean inserirFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {

        Connection con = ConnectionFactory.abrirConexao();

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(con);
        boolean resultado = funcionarioDAO.inserir(funcionario);

        ConnectionFactory.fecharConexao(con);

        return resultado;
    }

    public String atualizarFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(con);

        String resultado = funcionarioDAO.atualizar(funcionario);

        ConnectionFactory.fecharConexao(con);

        return resultado;
    }

    public String deletarFuncionario(int idFuncionario) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(con);

        String resultado = funcionarioDAO.deletar(idFuncionario);

        ConnectionFactory.fecharConexao(con);

        return resultado;
    }

    public String listarUmFuncionario(int idFuncionario) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(con);

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(idFuncionario);

        String resultado = funcionarioDAO.listarUm(funcionario);
        ConnectionFactory.fecharConexao(con);

        return resultado;
    }

    public List<Funcionario> listarTodosFuncionarios() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(con);

        List<Funcionario> funcionarios = funcionarioDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return funcionarios;
    }

}
