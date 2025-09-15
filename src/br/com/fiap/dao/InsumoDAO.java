package br.com.fiap.dao;

import br.com.fiap.dto.Insumo;

import javax.swing.*;
import java.sql.*;

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
        String sql = "insert into INSUMO(nome, lote, datavalidade, unidademedida, codigo_de_barras, id_categoria) values(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, insumo.getNome());
            ps.setString(2, insumo.getLote());
            if (insumo.getDataValidade() != null) {
                ps.setDate(3, Date.valueOf(insumo.getDataValidade()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setString(4, insumo.getUnidadeMedida());
            ps.setString(5, insumo.getQRCode());
            ps.setInt(6, insumo.getIdCategoriaInsumo());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Insumo inserido com sucesso");

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o insumo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return "Insumo inserido com sucesso";
    }

    public String atualizar(Insumo insumo) {
        String sql = "UPDATE INSUMO SET nome=?, lote=?, datavalidade=?, unidademedida=?, codigo_de_barras=?, id_categoria=?" +
                "WHERE ID_INSUMO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, insumo.getNome());
            ps.setString(2, insumo.getLote());
            ps.setDate(3, java.sql.Date.valueOf(insumo.getDataValidade()));
            ps.setString(4, insumo.getUnidadeMedida());
            ps.setString(5, insumo.getQRCode());
            ps.setInt(6, insumo.getIdCategoriaInsumo());

            ps.setInt(7, insumo.getIdInsumo()); // WHERE

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
        String sql = "DELETE FROM INSUMO WHERE id_insumo=?";
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
        String sql = "SELECT * FROM INSUMO where id_insumo=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, insumo.getIdInsumo());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dados = String.format(
                        "ID: %d%nNome: %s%nLote: %s%nData Validade: %s%n" +
                                "Unidade de Medida: %s%nCódigo de Barras: %s%nID Categoria: %d",
                        rs.getInt("id_insumo"),
                        rs.getString("nome"),
                        rs.getString("lote") != null ? rs.getString("LOTE") : "N/A",
                        rs.getDate("datavalidade") != null ? rs.getDate("DATAVALIDADE").toString() : "N/A",
                        rs.getString("unidademedida"),
                        rs.getString("codigo_de_barras"),
                        rs.getInt("id_categoria")
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
                insumo.setUnidadeMedida(rs.getString("unidademedida"));
                insumo.setIdCategoriaInsumo(rs.getInt("id_categoria"));

                insumos.add(insumo);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return insumos;
    }

}
