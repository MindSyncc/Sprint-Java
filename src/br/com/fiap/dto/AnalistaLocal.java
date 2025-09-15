package br.com.fiap.dto;

import br.com.fiap.controller.PedidoController;
import br.com.fiap.dao.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AnalistaLocal extends Funcionario {


    public AnalistaLocal() {

    }

    public AnalistaLocal(String funcional, String nome, String senha, LocalDate dataDeNascimento,
                      String cpf, float salario, LocalDate dataInicio, String turno,
                      String cargo, String permissao,
                      String rua, String numero, String bairro, String cidade, String estado, String cep,
                      int idUnidade) {
        super(funcional, nome, senha, dataDeNascimento, cpf, salario, dataInicio, turno,
                cargo, permissao, rua, numero, bairro, cidade, estado, cep, idUnidade);
    }

    // Construtor que recebe um funcionário
    public AnalistaLocal(Funcionario funcionario) {
        super(funcionario.getFuncional(), funcionario.getNome(), funcionario.getSenha(), funcionario.getDataDeNascimento(),
                funcionario.getCpf(), funcionario.getSalario(), funcionario.getDataDeInicio(), funcionario.getTurno(),
                funcionario.getCargo(), funcionario.getPermissao(),
                funcionario.getRua(), funcionario.getNumero(), funcionario.getBairro(), funcionario.getCidade(), funcionario.getEstado(), funcionario.getCep(),
                funcionario.getIdUnidade());
        this.setIdFuncionario(funcionario.getIdFuncionario()); // mantém o ID original
    }

    public void exibirInformacoesDoFuncionario() {
        String dadosFuncionario = String.format("ID do Funcionário: %d%n" +
                "Nome do Funcionário: %s%n" +
                "Data de Nascimento: %s%n" +
                "CPF do Funcionário: %s%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s%n" +
                "Salário vigente: %.2f", getIdFuncionario(), getNome(), getDataDeNascimento(), getCpf(), getTurno(), getCargo(), getSalario());
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Analista Local", JOptionPane.INFORMATION_MESSAGE);
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
        LocalDate dataDoPedido = LocalDate.now();
        int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do fornecedor:"));

        // Cria o objeto pedido
        Pedido pedido = new Pedido(idPedido,quantidadeItem, nomeItem, dataDoPedido, statusPedido, getIdFuncionario(), idFornecedor);

        // Armazena o pedido no banco de dados
        try {
            PedidoController pedidoController = new PedidoController();
            String resultadoInserir = pedidoController.inserirPedido(pedido);
            System.out.println(resultadoInserir);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }


        return pedido;
    }

    public void verificarMovimentacoes(List<Movimentacao> listaDeMovimentacoes) {
        String string = "LISTA DE MOVIMENTAÇÕES\n";

        for (Movimentacao movimentacao : listaDeMovimentacoes) {
            string += movimentacao.exibirInformacoesDaMovimentacao() + "\n";
        }

        JOptionPane.showMessageDialog(null, string, "Movimentacoes", JOptionPane.INFORMATION_MESSAGE);
    }
}
