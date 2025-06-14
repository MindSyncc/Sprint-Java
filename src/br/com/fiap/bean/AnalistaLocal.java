package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class AnalistaLocal extends Funcionario {

    public AnalistaLocal() {

    }

    public AnalistaLocal(int idFuncionario, String nome, String senha, LocalDate dataDeNascimento, String cpf, float salario, String turno, String funcao) {
        super(idFuncionario, nome, senha, dataDeNascimento, cpf, salario, turno, funcao);
    }

    public void exibirInformacoesDoFuncionario() {
        String dadosFuncionario = String.format("ID do Funcionário: %d%n" +
                "Nome do Funcionário: %s%n" +
                "Data de Nascimento: %s%n" +
                "CPF do Funcionário: %s%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s" +
                "Salário vigente: %.2f", getIdFuncionario(), getNome(), getDataDeNascimento(), getCpf(), getTurno(), getFuncao(), getSalario());
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Analista Local", JOptionPane.INFORMATION_MESSAGE);
    }

    public void verificarStatusDaPrateleira(PrateleiraInteligente prateleira) {
        if (prateleira.getStatus().equals("Ativo")) {
            JOptionPane.showMessageDialog(null, "A prateleira inteligente está ativada!");
        } else {
            JOptionPane.showMessageDialog(null, "A prateleira inteligente está desativada!");
        }
    }

    public Pedido realizarPedidoDeInsumo() {
        Pedido pedido;
        String auxiliar;

        JOptionPane.showMessageDialog(null, "Para ordenar um novo pedido é necessário que preencha algumas informações relevantes",
                "PREENCHIMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);




        return new Pedido();
    }

    public void verificarMovimentacoes(List<Movimentacao> listaDeMovimentacoes) {
        String string = "";

        for (Movimentacao movimentacao : listaDeMovimentacoes) {
            string += movimentacao.exibirInformacoesDaMovimentacao() + "\n";
        }

        JOptionPane.showMessageDialog(null, string, "Movimentacoes", JOptionPane.INFORMATION_MESSAGE);
    }
}
