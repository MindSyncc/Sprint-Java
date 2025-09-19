package br.com.fiap.model.dto;

import br.com.fiap.controller.FornecedorController;
import br.com.fiap.controller.InsumoController;
import br.com.fiap.controller.PedidoController;
import br.com.fiap.controller.PedidoInsumoController;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
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

        Pedido pedido = null;

        JOptionPane.showMessageDialog(null, "Para ordenar um novo pedido é necessário que preencha algumas informações relevantes",
                "PREENCHIMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        // Informações do Pedido
        try {
            InsumoController insumoController = new InsumoController(); // Inicia o controlador de Insumo
            List<Insumo> listaDeInsumos = insumoController.listarTodosInsumos();

            // Monta as opções para o JOptionPane que exibe a lista de insumos
            String insumos = "";
            int index = 0;
            for (Insumo insumo : listaDeInsumos) {
                insumos += index + " - " + insumo.getNome() + "\n";
                index++;
            }

            FornecedorController fornecedorController = new FornecedorController();
            List<Fornecedor> listaDeFornecedores = fornecedorController.listarTodosFornecedores();

            // Monta as opções para o JOptionPane que exibe a lista de fornecedores
            String fornecedores = "";
            int indexF = 0;
            for (Fornecedor fornecedor : listaDeFornecedores) {
                fornecedores += indexF + " - " + fornecedor.getNomeFornecedor() + "\n";
                indexF++;
            }

            // perguntas para o usuário
            String nomeItem = JOptionPane.showInputDialog("Digite o nome do item que será pedido: \n\n" + insumos);
            int quantidadeItem = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade que será pedida: "));
            String statusPedido = "Pendente";
            LocalDate dataDoPedido = LocalDate.now();
            int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do fornecedor: \n\n Fornecedores disponíveis \n" + fornecedores));

            // Cria o objeto pedido
            pedido = new Pedido(quantidadeItem, nomeItem, dataDoPedido, statusPedido, getIdFuncionario(), idFornecedor);

            // Armazena o pedido no banco de dados
            PedidoController pedidoController = new PedidoController();
            String resultadoInserir = pedidoController.inserirPedido(pedido);
            System.out.println(resultadoInserir);

            // Recupera o ID do pedido após inserção no banco para criação do vínculo entre Insumo e Pedido
            int idPedido = pedido.getIdDoPedido();
            
            // Recupera o ID do insumo no banco pelo nome do insumo para criação do vínculo entre Insumo e Pedido
            int idInsumo = insumoController.listarUmInsumoPorNome(nomeItem).getIdInsumo();

            // Criação do controlador e objeto de PedidoInsumo para vínculo com Insumo
            PedidoInsumoController pedidoInsumoController = new PedidoInsumoController();
            PedidoInsumo pedidoInsumo = new PedidoInsumo(idPedido, idInsumo, quantidadeItem);

            // Cria o registro de PedidoInsumo no banco (vínculo entre Pedido e Insumo)
            pedidoInsumoController.inserirPedidoInsumo(pedidoInsumo);

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
