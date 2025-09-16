package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;

public class AnalistaCorporativo extends Funcionario {
    private String areaDeAtuacao;
    private String departamento;

    public AnalistaCorporativo() {

    }

    public AnalistaCorporativo(String funcional, String nome, String senha, LocalDate dataDeNascimento,
                               String cpf, float salario, LocalDate dataInicio, String turno,
                               String cargo, String permissao,
                               String rua, String numero, String bairro, String cidade, String estado, String cep,
                               int idUnidade, String areaDeAtuacao, String departamento) {
        super(funcional, nome, senha, dataDeNascimento, cpf, salario, dataInicio, turno,
                cargo, permissao, rua, numero, bairro, cidade, estado, cep, idUnidade);
        this.areaDeAtuacao = areaDeAtuacao;
        this.departamento = departamento;
    }

    // Construtor que recebe um funcionário
    public AnalistaCorporativo(Funcionario funcionario) {
        super(funcionario.getFuncional(), funcionario.getNome(), funcionario.getSenha(), funcionario.getDataDeNascimento(),
                funcionario.getCpf(), funcionario.getSalario(), funcionario.getDataDeInicio(), funcionario.getTurno(),
                funcionario.getCargo(), funcionario.getPermissao(),
                funcionario.getRua(), funcionario.getNumero(), funcionario.getBairro(), funcionario.getCidade(), funcionario.getEstado(), funcionario.getCep(),
                funcionario.getIdUnidade());
        this.setIdFuncionario(funcionario.getIdFuncionario());
        this.areaDeAtuacao = "Bombeiros";
        this.departamento = "50A";
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
        String dadosFuncionario = String.format(
                "ID: %d%nFuncional: %s%nNome: %s%nCPF: %s%nData Nascimento: %s%n" +
                        "Salário: %.2f%nData Início: %s%nData Término: %s%nTurno: %s%nCargo: %s%nPermissão: %s%n" +
                        "Endereço: %s, %s, %s, %s - %s%nCEP: %s%nID Unidade: %d%n" +
                        "Área de Atuação: %s%nDepartamento: %s",
                getIdFuncionario(), getFuncional(), getNome(), getCpf(), getDataDeNascimento(),
                getSalario(), getDataDeInicio(),
                getDataTermino() != null ? getDataTermino() : "N/A",
                getTurno(), getCargo(), getPermissao(),
                getRua(), getNumero(), getBairro(), getCidade(), getEstado(), getCep(), getIdUnidade(),
                areaDeAtuacao, departamento
        );
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
