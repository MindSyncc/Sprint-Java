package br.com.fiap.bean;

import java.time.LocalDate;
import javax.swing.*;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private LocalDate dataDeNascimento;
    private int cpf;
    private float salario;
    private LocalDate dataDeInicio;
    private String turno;
    private String funcao;

    public Funcionario() {

    }

    public Funcionario(int idFuncionario, String nome, LocalDate dataDeNascimento, int cpf, float salario, LocalDate dataDeInicio, String turno, String funcao) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.salario = salario;
        this.dataDeInicio = dataDeInicio;
        this.turno = turno;
        this.funcao = funcao;
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

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public int getCpf() {
        return cpf;
    }

    // Aplicar a regra de negócio de 11 digitos
    public void setCpf(int cpf) {
        this.cpf = cpf;
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
                "CPF do Funcionário: %d%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s", idFuncionario, nome, dataDeNascimento, cpf, turno, funcao);
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Funcionário", JOptionPane.INFORMATION_MESSAGE);
    }
}
