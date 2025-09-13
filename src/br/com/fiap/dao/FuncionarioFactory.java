package br.com.fiap.dao;

import br.com.fiap.dto.Almoxarife;
import br.com.fiap.dto.AnalistaCorporativo;
import br.com.fiap.dto.AnalistaLocal;
import br.com.fiap.dto.Funcionario;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FuncionarioFactory {
    private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FuncionarioFactory() {}

    public static Funcionario criarFuncionario(int tipoDeFuncionario) {
        String funcional = JOptionPane.showInputDialog("Digite seu número funcional (8 dígitos)");
        String nome = JOptionPane.showInputDialog("Digite o nome completo");
        String senha = JOptionPane.showInputDialog("Digite a senha (mínimo 5 caracteres)");
        LocalDate dataDeNascimento = LocalDate.parse(JOptionPane.showInputDialog("Digite sua data de nascimento (dd/MM/yyyy)"), DTF);
        String cpf = JOptionPane.showInputDialog("Digite seu CPF (11 dígitos)");
        float salario = Float.parseFloat(JOptionPane.showInputDialog("Digite seu salário"));
        LocalDate dataInicio = LocalDate.now(); // inicia hoje
        String turno = JOptionPane.showInputDialog("Digite seu turno (Manhã, Tarde ou Noite)");
        String cargo = JOptionPane.showInputDialog("Digite seu cargo");
        String permissao = JOptionPane.showInputDialog("Digite sua permissão (Analista Local, Analista Corporativo, Almoxarife, Colaborador Interno)");

        // Endereço
        String rua = JOptionPane.showInputDialog("Digite a rua");
        String numero = JOptionPane.showInputDialog("Digite o número");
        String bairro = JOptionPane.showInputDialog("Digite o bairro");
        String cidade = JOptionPane.showInputDialog("Digite a cidade");
        String estado = JOptionPane.showInputDialog("Digite o estado (2 caracteres)");
        String cep = JOptionPane.showInputDialog("Digite o CEP (8 ou 9 caracteres)");
        int idUnidade = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da unidade"));

        switch (tipoDeFuncionario) {
            case 1:
                return new Almoxarife(funcional, nome, senha, dataDeNascimento, cpf, salario,
                        dataInicio, turno, cargo, permissao,
                        rua, numero, bairro, cidade, estado, cep,
                        idUnidade);
            case 2:
                return new AnalistaLocal(funcional, nome, senha, dataDeNascimento, cpf, salario,
                        dataInicio, turno, cargo, permissao,
                        rua, numero, bairro, cidade, estado, cep,
                        idUnidade);
            case 3:
                String areaDeAtuacao = JOptionPane.showInputDialog("Digite sua área de atuação. Ex: Analista de Previsões");
                String departamento = JOptionPane.showInputDialog("Digite seu departamento. Ex: Planejamento Estratégico");

                return new AnalistaCorporativo(funcional, nome, senha, dataDeNascimento, cpf, salario,
                        dataInicio, turno, cargo, permissao,
                        rua, numero, bairro, cidade, estado, cep,
                        idUnidade, areaDeAtuacao, departamento);
            default:
                throw new RuntimeException("Opção inválida! Por favor digite um tipo de funcionário válido");
        }
    }
}
