package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;

public class AnalistaCorporativo extends Funcionario{
    private String areaDeAtuacao;
    private String departamento;

    public AnalistaCorporativo() {

    }

    public AnalistaCorporativo(String nome, String senha, LocalDate dataDeNascimento, String cpf, float salario, LocalDate dataDeInicio, String turno,String areaDeAtuacao, String departamento) {
        super(nome, senha, dataDeNascimento, cpf, salario, turno);
        setFuncao("Analista Corporativo");
        this.areaDeAtuacao = areaDeAtuacao;
        this.departamento = departamento;
    }

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void exibirInformacoesDoFuncionario() {
        String dadosFuncionario = String.format("ID do Funcionário: %d%n" +
                "Nome do Funcionário: %s%n" +
                "Data de Nascimento: %s%n" +
                "CPF do Funcionário: %s%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s" +
                "Área de Atuação: %s" +
                "Departamento: %s", getIdFuncionario(), getNome(), getDataDeNascimento(), getCpf(), getTurno(), getFuncao(), areaDeAtuacao, departamento);
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Analista Corporativo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void visualizarUnidades() {
        String informcoesSimuladas = """
                ID da Unidade: 12
                Local da Unidade: Santo André, SP
                Nome da Unidade: Labvita Diagnósticos
                Nível do Estoque: BAIXO
                
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                
                ID da Unidade: 45
                Local da Unidade: Santa Maria, RS
                Nome da Unidade: Genex Diagnósticos
                Nível do Estoque: MODERADO
                
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                
                ID da Unidade: 03
                Local da Unidade: Cuiabá, MT
                Nome da Unidade: Biosynthetica
                Nível de Estoque: ADEQUADO
                """;
        JOptionPane.showMessageDialog(null, informcoesSimuladas, "Quadro de Unidades", JOptionPane.INFORMATION_MESSAGE);
    }
}
