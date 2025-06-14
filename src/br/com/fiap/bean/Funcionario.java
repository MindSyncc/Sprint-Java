package br.com.fiap.bean;

import java.time.LocalDate;
import javax.swing.*;

public class Funcionario {
    private static int sequencial = 1;


    private int idFuncionario;
    private String nome;
    private String senha;
    private LocalDate dataDeNascimento;
    private String cpf;
    private float salario;
    private LocalDate dataDeInicio;
    private String turno;
    private String funcao;

    // construtores

    public Funcionario() {

    }

    public Funcionario (String nome, String senha, LocalDate dataDeNascimento, String cpf, float salario, String turno) {
        this.idFuncionario = sequencial++;
        this.nome = nome;
        setSenha(senha);
        setDataDeNascimento(dataDeNascimento);
        setCpf(cpf);
        this.salario = salario;
        this.dataDeInicio = LocalDate.now();
        this.turno = turno;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            if (senha.length() >= 5) {
                this.senha = senha;
            } else {
                throw new Exception("A senha precisa ter pelo menos 5 digitos. Tente novamente mais tarde");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        LocalDate dataInicio = LocalDate.parse("1899-12-31");
        LocalDate dataAtual = LocalDate.now();

        try {
            if (dataDeNascimento.isAfter(dataInicio) && (dataDeNascimento.isBefore(dataAtual) || dataDeNascimento.equals(dataAtual))) {
                this.dataDeNascimento = dataDeNascimento;
            } else {
                throw new Exception("ERRO ao registrar data de nascimento. Faixa permitida: Min=1900, Max=Data Atual");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        try {
            if (cpf.length() == 11) {
                this.cpf = cpf;
            } else {
                throw new Exception("CPF Inválido, O CPF precisa ter 11 digitos. Tente novamente mais tarde");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(LocalDate dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void exibirInformacoesDoFuncionario() {
        String dadosFuncionario = String.format("ID do Funcionário: %d%n" +
                "Nome do Funcionário: %s%n" +
                "Data de Nascimento: %s%n" +
                "CPF do Funcionário: %s%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s", idFuncionario, nome, dataDeNascimento, cpf, turno, funcao);
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Funcionário", JOptionPane.INFORMATION_MESSAGE);
    }
}
