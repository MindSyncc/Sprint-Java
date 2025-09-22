package br.com.fiap.model.dto;

import br.com.fiap.controller.*;

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

        JOptionPane.showMessageDialog(null,
                "Para ordenar um novo pedido é necessário que preencha algumas informações relevantes",
                "PREENCHIMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        try {
            // Controlador de insumos para verificar os insumos do sistema
            InsumoController insumoController = new InsumoController();
            List<Insumo> listaDeInsumos = insumoController.listarTodosInsumos();

            // Monta a lista de insumos
            String insumos = "";
            int index = 1;
            for (Insumo insumo : listaDeInsumos) {
                insumos += index + " - " + insumo.getNome() + "\n";
                index++;
            }

            // Pergunta o nome do item
            int escolhaInsumo = Integer.parseInt(JOptionPane.showInputDialog("Insira o DÍGITO do item que será pedido: \n\n" + insumos));

            if (escolhaInsumo < 1 || escolhaInsumo > listaDeInsumos.size()) {
                JOptionPane.showMessageDialog(null,
                        "Opção inválida! O insumo selecionado não existe.",
                        "INSUMO INEXISTENTE", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // recupera o insumo escolhido
            Insumo insumoSelecionado = listaDeInsumos.get(escolhaInsumo - 1); // diminui um pois a lista de insumos é zero-indexada nossas opções começaram pelo 1

            // se o item existir, procede com a criação do pedido
            int quantidadeItem = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade que será pedida: "));
            String statusPedido = "Pendente";
            LocalDate dataDoPedido = LocalDate.now();

            FornecedorController fornecedorController = new FornecedorController();
            List<Fornecedor> listaDeFornecedores = fornecedorController.listarTodosFornecedores();

            String fornecedores = "";
            int indexF = 1;
            for (Fornecedor fornecedor : listaDeFornecedores) {
                fornecedores += indexF + " - " + fornecedor.getNomeFornecedor() + "\n";
                indexF++;
            }

            int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Insira o DÍGITO referente ao fornecedor que deseja selecionar: \n\n Fornecedores disponíveis \n" + fornecedores));

            // Cria o objeto pedido
            pedido = new Pedido(quantidadeItem, insumoSelecionado.getNome(), dataDoPedido, statusPedido, getIdFuncionario(), idFornecedor);

            // Insere no banco
            PedidoController pedidoController = new PedidoController();
            String resultadoInserir = pedidoController.inserirPedido(pedido);
            System.out.println(resultadoInserir);

            // Recupera IDs
            int idPedido = pedido.getIdDoPedido();
            int idInsumo = insumoSelecionado.getIdInsumo();

            // Cria vínculo PedidoInsumo
            PedidoInsumoController pedidoInsumoController = new PedidoInsumoController();
            PedidoInsumo pedidoInsumo = new PedidoInsumo(idPedido, idInsumo, quantidadeItem);
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

    public void verificarEstoque() {
        try {
            // Instanciando controladores necessários para a exibição do estoque.
            EstoqueInsumoController estoqueInsumoController = new EstoqueInsumoController();
            InsumoController insumoController = new InsumoController();
            List<EstoqueInsumo> listaEstoque = estoqueInsumoController.listarTodosEstoqueInsumo();


            String aux = "";

            aux += "==== Informações do Estoque ==== \n\n";

            for (EstoqueInsumo estoque : listaEstoque) {
                // Consulta insumo para recuperar o nome e exibi-lo.
                Insumo insumo = insumoController.listarUmInsumo("id", estoque.getIdInsumo());

                // Adiciona informações básicas na string
                aux += String.format("🆔 ID Insumo: %d | 📊 Quantidade: %d | 📋 Nome: %s \n",
                        estoque.getIdInsumo(),
                        estoque.getQuantidade(), insumo.getNome());

                aux += "---------------------------------------------------------------------------------- \n";
            }

            JOptionPane.showMessageDialog(null, aux, "ESTOQUE", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.out.println("Erro ao verificar estoque: " + e.getMessage());
        }
    }
}
