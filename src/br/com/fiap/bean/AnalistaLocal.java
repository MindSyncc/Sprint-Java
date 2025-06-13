package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;

public class AnalistaLocal extends Funcionario {

    public AnalistaLocal() {

    }

    public AnalistaLocal(int idFuncionario, String nome, LocalDate dataDeNascimento, int cpf, float salario, LocalDate dataDeInicio, String turno, String funcao) {
        super(idFuncionario, nome, dataDeNascimento, cpf, salario, dataDeInicio, turno, funcao);
    }

    public void exibirInformacoesDoFuncionario() {
        String dadosFuncionario = String.format("ID do Funcionário: %d%n" +
                "Nome do Funcionário: %s%n" +
                "Data de Nascimento: %s%n" +
                "CPF do Funcionário: %d%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s" +
                "Salário vigente: %.2f", getIdFuncionario(), getNome(), getDataDeNascimento(), getCpf(), getTurno(), getFuncao(), getSalario());
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Analista Local", JOptionPane.INFORMATION_MESSAGE);
    }

    public void verificarStatusDaPrateleira(PrateleiraInteligente prateleira) {
        JOptionPane.showMessageDialog(null, "");
    }

    public Pedido realizarPedidoDeInsumo() {
        return new Pedido();
    }

    public void verificarMovimentacoes() {
        JOptionPane.showMessageDialog(null, "");
    }
}
