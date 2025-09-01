package br.com.fiap.dao;

import br.com.fiap.dto.Insumo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsumoDAO {
    private Connection con;

    public InsumoDAO (Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Insumo insumo) {
        String sql = "insert into INSUMO(IDInsumo, Lote, DataValidade, Nome, UnidadeMedida, Codigo_De_Barras values(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, insumo.getIdInsumo());
            ps.setString(2, insumo.getLote());
            ps.setDate(3, java.sql.Date.valueOf(insumo.getDataValidade()));
            ps.setString(4, insumo.getNome());
            ps.setString(5, insumo.getUnidadeMedida());
            ps.setInt(6, 2000); // REVISAR (MOCK)

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(Insumo insumo) {
        String sql = "UPDATE INSUMO SET Lote=?, DataValidade=?, Nome=?, UnidadeMedida=?, Codigo_De_Barras=? " +
                "WHERE IDInsumo=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, insumo.getLote());
            ps.setDate(2, java.sql.Date.valueOf(insumo.getDataValidade()));
            ps.setString(3, insumo.getNome());
            ps.setString(4, insumo.getUnidadeMedida());
            ps.setInt(6, 2000); // REVISAR (MOCK)
            ps.setInt(6, insumo.getIdInsumo());

            if (ps.executeUpdate() > 0) {
                return "Atualizado com sucesso";
            } else {
                return "Erro ao atualizar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String deletar(int idInsumo) {
        String sql = "DELETE FROM INSUMO WHERE IDInsumo=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idInsumo);

            if (ps.executeUpdate() > 0) {
                return "Deletado com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
}
