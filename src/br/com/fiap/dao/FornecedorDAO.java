package br.com.fiap.dao;

import br.com.fiap.dto.Fornecedor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    private Connection con;

    public FornecedorDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO FORNECEDORES (nome_fornecedor, telefone, email, cnpj, rua, numero, bairro, cidade, estado, cep) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, fornecedor.getNomeFornecedor());
            ps.setString(2, fornecedor.getTelefone());
            ps.setString(3, fornecedor.getEmail());
            ps.setString(4, fornecedor.getCnpj());
            ps.setString(5, fornecedor.getRua());
            ps.setString(6, fornecedor.getNumero());
            ps.setString(7, fornecedor.getBairro());
            ps.setString(8, fornecedor.getCidade());
            ps.setString(9, fornecedor.getEstado());
            ps.setString(10, fornecedor.getCep());

            if (ps.executeUpdate() > 0) {
                return "Fornecedor inserido com sucesso!";
            } else {
                return "Erro ao inserir fornecedor.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String atualizar(Fornecedor fornecedor) {
        String sql = "UPDATE FORNECEDORES SET nome_fornecedor=?, telefone=?, email=?, cnpj=?, rua=?, numero=?, bairro=?, cidade=?, estado=?, cep=? " +
                "WHERE id_fornecedor=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, fornecedor.getNomeFornecedor());
            ps.setString(2, fornecedor.getTelefone());
            ps.setString(3, fornecedor.getEmail());
            ps.setString(4, fornecedor.getCnpj());
            ps.setString(5, fornecedor.getRua());
            ps.setString(6, fornecedor.getNumero());
            ps.setString(7, fornecedor.getBairro());
            ps.setString(8, fornecedor.getCidade());
            ps.setString(9, fornecedor.getEstado());
            ps.setString(10, fornecedor.getCep());
            ps.setInt(11, fornecedor.getIdFornecedor());

            if (ps.executeUpdate() > 0) {
                return "Fornecedor atualizado com sucesso!";
            } else {
                return "Erro ao atualizar fornecedor.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String deletar(int idFornecedor) {
        String sql = "DELETE FROM FORNECEDORES WHERE id_fornecedor=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idFornecedor);

            if (ps.executeUpdate() > 0) {
                return "Fornecedor deletado com sucesso!";
            } else {
                return "Erro ao deletar fornecedor.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String listarUm(Fornecedor fornecedor) {
        String sql = "SELECT * FROM FORNECEDORES WHERE id_fornecedor=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, fornecedor.getIdFornecedor());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return String.format(
                        "ID: %d%nNome: %s%nTelefone: %s%nEmail: %s%nCNPJ: %s%n" +
                                "Endereço: %s, %s, %s, %s - %s%nCEP: %s",
                        rs.getInt("id_fornecedor"),
                        rs.getString("nome_fornecedor"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep")
                );
            } else {
                return "Fornecedor não encontrado!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public List<Fornecedor> listarTodos() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM FORNECEDORES";

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
                fornecedor.setNomeFornecedor(rs.getString("nome_fornecedor"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setRua(rs.getString("rua"));
                fornecedor.setNumero(rs.getString("numero"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setEstado(rs.getString("estado"));
                fornecedor.setCep(rs.getString("cep"));

                fornecedores.add(fornecedor);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return fornecedores;
    }
}
