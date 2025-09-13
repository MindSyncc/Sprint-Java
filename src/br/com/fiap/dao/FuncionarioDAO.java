package br.com.fiap.dao;

import br.com.fiap.dto.Funcionario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Connection con;

    public FuncionarioDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    // Inserir funcionário
    public boolean inserir(Funcionario funcionario) {
        boolean sucesso = false;

        String sql = "INSERT INTO funcionarios (funcional, nome, cpf, senhaHash, dataNascimento, " +
                "salario, dataInicio, dataTermino, turno, cargo, permissao, rua, numero, bairro, cidade, estado, cep, id_unidade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, funcionario.getFuncional());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getSenha()); // aqui você pode aplicar hash se quiser
            ps.setDate(5, java.sql.Date.valueOf(funcionario.getDataDeNascimento()));
            ps.setFloat(6, funcionario.getSalario());
            ps.setDate(7, java.sql.Date.valueOf(funcionario.getDataDeInicio()));

            if (funcionario.getDataTermino() != null) {
                ps.setDate(8, java.sql.Date.valueOf(funcionario.getDataTermino()));
            } else {
                ps.setNull(8, java.sql.Types.DATE);
            }

            ps.setString(9, funcionario.getTurno());
            ps.setString(10, funcionario.getCargo());
            ps.setString(11, funcionario.getPermissao());
            ps.setString(12, funcionario.getRua());
            ps.setString(13, funcionario.getNumero());
            ps.setString(14, funcionario.getBairro());
            ps.setString(15, funcionario.getCidade());
            ps.setString(16, funcionario.getEstado());
            ps.setString(17, funcionario.getCep());
            ps.setInt(18, funcionario.getIdUnidade());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null,"Funcionário inserido com sucesso!");
                sucesso = true;

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir funcionário");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); ;
        }
        return sucesso;
    }

    // Atualizar funcionário
    public String atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET funcional=?, nome=?, cpf=?, senhaHash=?, dataNascimento=?, " +
                "salario=?, dataInicio=?, dataTermino=?, turno=?, cargo=?, permissao=?, rua=?, numero=?, bairro=?, cidade=?, estado=?, cep=?, id_unidade=? " +
                "WHERE id_funcionario=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, funcionario.getFuncional());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getSenha());
            ps.setDate(5, java.sql.Date.valueOf(funcionario.getDataDeNascimento()));
            ps.setFloat(6, funcionario.getSalario());
            ps.setDate(7, java.sql.Date.valueOf(funcionario.getDataDeInicio()));

            if (funcionario.getDataTermino() != null) {
                ps.setDate(8, java.sql.Date.valueOf(funcionario.getDataTermino()));
            } else {
                ps.setNull(8, java.sql.Types.DATE);
            }

            ps.setString(9, funcionario.getTurno());
            ps.setString(10, funcionario.getCargo());
            ps.setString(11, funcionario.getPermissao());
            ps.setString(12, funcionario.getRua());
            ps.setString(13, funcionario.getNumero());
            ps.setString(14, funcionario.getBairro());
            ps.setString(15, funcionario.getCidade());
            ps.setString(16, funcionario.getEstado());
            ps.setString(17, funcionario.getCep());
            ps.setInt(18, funcionario.getIdUnidade());
            ps.setInt(19, funcionario.getIdFuncionario());

            if (ps.executeUpdate() > 0) {
                return "Funcionário atualizado com sucesso!";
            } else {
                return "Erro ao atualizar funcionário.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // Deletar funcionário
    public String deletar(int idFuncionario) {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);
            if (ps.executeUpdate() > 0) {
                return "Funcionário deletado com sucesso!";
            } else {
                return "Erro ao deletar funcionário.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // Listar funcionário
    public String listarUm(Funcionario funcionario) {
        String sql = "SELECT * FROM FUNCIONARIOS WHERE ID_FUNCIONARIO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, funcionario.getIdFuncionario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dados = String.format(
                        "ID: %d%nFuncional: %s%nNome: %s%nCPF: %s%nData Nascimento: %s%n" +
                                "Salário: %.2f%nData Início: %s%nData Término: %s%nTurno: %s%nCargo: %s%nPermissão: %s%n" +
                                "Endereço: %s, %s, %s, %s - %s%nCEP: %s%nID Unidade: %d",
                        rs.getInt("ID_FUNCIONARIO"),
                        rs.getString("FUNCIONAL"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getDate("DATANASCIMENTO"),
                        rs.getFloat("SALARIO"),
                        rs.getDate("DATAINICIO"),
                        rs.getDate("DATATERMINO") != null ? rs.getDate("DATATERMINO") : "N/A",
                        rs.getString("TURNO"),
                        rs.getString("CARGO"),
                        rs.getString("PERMISSAO"),
                        rs.getString("RUA"),
                        rs.getString("NUMERO"),
                        rs.getString("BAIRRO"),
                        rs.getString("CIDADE"),
                        rs.getString("ESTADO"),
                        rs.getString("CEP"),
                        rs.getInt("ID_UNIDADE")
                );
                return dados;
            } else {
                return "Registro de funcionário não encontrado!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setFuncional(rs.getString("funcional"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSenha(rs.getString("senhaHash"));

                java.sql.Date d; // variável para controlar as datas vindas do banco
                d = rs.getDate("dataNascimento");
                if (d != null) funcionario.setDataDeNascimento(d.toLocalDate());

                funcionario.setSalario(rs.getFloat("salario"));

                d = rs.getDate("dataInicio");
                if (d != null) funcionario.setDataDeInicio(d.toLocalDate());

                d = rs.getDate("dataTermino");
                if (d != null) funcionario.setDataTermino(d.toLocalDate());

                funcionario.setTurno(rs.getString("turno"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setPermissao(rs.getString("permissao"));
                funcionario.setRua(rs.getString("rua"));
                funcionario.setNumero(rs.getString("numero"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setIdUnidade(rs.getInt("id_unidade"));

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }

        return funcionarios;
    }



}
