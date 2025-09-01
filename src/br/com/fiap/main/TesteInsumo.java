package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.InsumoDAO;
import br.com.fiap.dto.Insumo;

import java.sql.Connection;
import java.time.LocalDate;

public class TesteInsumo {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();
        Insumo insumo = new Insumo();
        InsumoDAO insumoDAO = new InsumoDAO(con);
        insumo.setIdInsumo(5000);
        insumo.setLote("25A");
        insumo.setDataValidade(LocalDate.parse("2025-05-05")); // formato americano
        insumo.setNome("Seringa XL");
        insumo.setUnidadeMedida("Metros");

        System.out.println(insumoDAO.inserir(insumo));
        ConnectionFactory.fecharConexao(con);
    }






}
