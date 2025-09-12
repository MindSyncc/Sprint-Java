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

    public FuncionarioFactory() {

    }

    public static Funcionario criarFuncionario(int tipoDeFuncionario) {
        String nomeCadastro = JOptionPane.showInputDialog("Digite o nome que será utilizado no cadastro. Recomendamos que utilize seu nome completo");
        String senhaCadastro = JOptionPane.showInputDialog("Digite a senha de cadastro. A senha deve conter pelo menos 5 caracteres");

        LocalDate dataDeNascimento = LocalDate.parse(JOptionPane.showInputDialog("Digite sua data de nascimento (dd/mm/aaaa)"), DTF);
        String cpf = JOptionPane.showInputDialog("Digite seu CPF");
        float salario = Float.parseFloat(JOptionPane.showInputDialog("Digite seu salário"));
        String turno = JOptionPane.showInputDialog("Digite seu turno (manhã, tarde ou noite)");

        switch (tipoDeFuncionario) {
            case 1:
                return new Almoxarife(nomeCadastro, senhaCadastro, dataDeNascimento, cpf, salario, turno);
            case 2:
                return new AnalistaLocal(nomeCadastro, senhaCadastro, dataDeNascimento, cpf, salario, turno);
            case 3:
                String areaDeAtuacao = JOptionPane.showInputDialog("Digite sua área de atuação. Ex: Analista de Previsões");
                String departamento = JOptionPane.showInputDialog("Digite seu departamento. Ex: Planejamento Estratégico");

                return new AnalistaCorporativo(nomeCadastro, senhaCadastro, dataDeNascimento, cpf, salario, turno, areaDeAtuacao, departamento);
            default:
                throw new RuntimeException("Opção inválida! Por favor digite um tipo de funcionário válido");
        }
    }
}
