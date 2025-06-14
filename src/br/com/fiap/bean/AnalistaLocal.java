package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AnalistaLocal extends Funcionario {

    public AnalistaLocal() {

    }

    public AnalistaLocal(String nome, String senha, LocalDate dataDeNascimento, String cpf, float salario, String turno) {
        super(nome, senha, dataDeNascimento, cpf, salario, turno);
        setFuncao("AnalistaCorporativo");
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, "Para ordenar um novo pedido é necessário que preencha algumas informações relevantes",
                "PREENCHIMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        // Informações do Pedido
        int idPedido = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do pedido: "));
        int quantidadeItem = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade que será pedida: "));
        String nomeItem = JOptionPane.showInputDialog("Digite o nome do item que será pedido: ");
        String statusPedido = "Pendente";
        LocalDate dataDoPedido = LocalDate.parse(LocalDate.now().format(dtf));

        return new Pedido(idPedido,quantidadeItem, nomeItem, dataDoPedido, statusPedido, getNome());
    }

    public void verificarMovimentacoes(List<Movimentacao> listaDeMovimentacoes) {
        String string = "";

        for (Movimentacao movimentacao : listaDeMovimentacoes) {
            string += movimentacao.exibirInformacoesDaMovimentacao() + "\n";
        }

        JOptionPane.showMessageDialog(null, string, "Movimentacoes", JOptionPane.INFORMATION_MESSAGE);
    }
}
