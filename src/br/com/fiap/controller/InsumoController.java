package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.InsumoDAO;
import br.com.fiap.model.dto.Insumo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InsumoController {

    public String inserirInsumo(Insumo insumo) throws ClassNotFoundException, SQLException {

        Connection con = ConnectionFactory.abrirConexao();

        InsumoDAO insumoDAO = new InsumoDAO(con);
        String resultado = insumoDAO.inserir(insumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String atualizarInsumo(Insumo insumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        InsumoDAO insumoDAO = new InsumoDAO(con);

        String resultado = insumoDAO.atualizar(insumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String deletarInsumo(String QRCodeInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        InsumoDAO insumoDAO = new InsumoDAO(con);

        String resultado = insumoDAO.deletar(QRCodeInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public Insumo listarUmInsumoPorNome(String nomeDoInsumo) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        InsumoDAO insumoDAO = new InsumoDAO(con);

        Insumo resultado = insumoDAO.listarUmPorNome(nomeDoInsumo);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public Insumo listarUmInsumo(String QRCode) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        InsumoDAO insumoDAO = new InsumoDAO(con);

        Insumo resultado = insumoDAO.listarUm(QRCode);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public List<Insumo> listarTodosInsumos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        InsumoDAO insumoDAO = new InsumoDAO(con);

        List<Insumo> insumos = insumoDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return insumos;
    }
}
