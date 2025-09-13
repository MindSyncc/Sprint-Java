package br.com.fiap.dao;

import br.com.fiap.dto.Funcionario;
import br.com.fiap.dto.Insumo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsumoDAO {
    private Connection con;

    public InsumoDAO (Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Insumo insumo) {
        String sql = "insert into INSUMO(IDInsumo, Lote, DataValidade, Nome, UnidadeMedida, Codigo_De_Barras) values(?, ?, ?, ?, ?, ?)";
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

    public String listarUm(Insumo insumo) {
        String sql = "SELECT * FROM INSUMO WHERE ID_INSUMO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, insumo.getIdInsumo());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dados = String.format(
                        "ID: %d%nNome: %s%nLote: %s%nData Validade: %s%n" +
                                "Unidade de Medida: %s%nCódigo de Barras: %s%nID Categoria: %d",
                        rs.getInt("ID_INSUMO"),
                        rs.getString("NOME"),
                        rs.getString("LOTE") != null ? rs.getString("LOTE") : "N/A",
                        rs.getDate("DATAVALIDADE") != null ? rs.getDate("DATAVALIDADE").toString() : "N/A",
                        rs.getString("UNIDADEMEDIDA"),
                        rs.getString("CODIGO_DE_BARRAS"),
                        rs.getInt("ID_CATEGORIA")
                );
                return dados;
            } else {
                return "Registro de insumo não encontrado!";
            }

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }


    public List<Insumo> listarTodos() {
        List<Insumo> insumos = new ArrayList<>();
        String sql = "SELECT * FROM INSUMO";

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Insumo insumo = new Insumo();

                insumo.setIdInsumo(rs.getInt("id_insumo"));
                insumo.setNome(rs.getString("nome"));
                insumo.setLote(rs.getString("lote"));

                java.sql.Date d = rs.getDate("dataValidade");
                if (d != null) {
                    insumo.setDataValidade(d.toLocalDate());
                }

                insumo.setQRCode(rs.getString("codigo_de_barras"));
                insumo.setUnidadeMedida(rs.getString("unidadeMedida"));
                insumo.setIdCategoriaInsumo(rs.getInt("id_categoria"));

                insumos.add(insumo);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return insumos;
    }

}
