    package br.com.fiap.view;

    import br.com.fiap.controller.FuncionarioController;
    import br.com.fiap.controller.InsumoController;
    import br.com.fiap.controller.MovimentacaoController;
    import br.com.fiap.controller.PedidoController;
    import br.com.fiap.model.dao.FuncionarioFactory;
    import br.com.fiap.model.dto.*;

    import javax.swing.*;
    import java.sql.SQLException;
    import java.util.*;

    public class Main {
        public static void main(String[] args) {
            FuncionarioController funcionarioController = new FuncionarioController();

            // Criação do controlador de movimentação, pedido e insumo para as operações dos funcionários.
            MovimentacaoController movimentacaoController = new MovimentacaoController();
            PedidoController pedidoController = new PedidoController();
            InsumoController insumoController = new InsumoController();

            // Listas locais para exibição de logs
            List<Movimentacao> movimentacoes = new ArrayList<>();
            List<Pedido> pedidos = new ArrayList<>();
            List<Insumo> insumos = new ArrayList<>();

            try {
                // Atribuição dos valores do banco às listas
                movimentacoes = movimentacaoController.listarTodos();
                pedidos = pedidoController.listarTodosPedidos();
                insumos = insumoController.listarTodosInsumos();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERRO de SQL: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }


            while (true) {
                int escolha = Integer.parseInt(JOptionPane.showInputDialog(null, "Bem-vindo ao Sistema de Gerenciamento! \nPor favor, selecione uma opção \n\n(1) Cadastrar \n(2) Login \n(3) Sair do sistema", "Menu inicial", JOptionPane.INFORMATION_MESSAGE));

                switch (escolha) {
                    case 1: // Cadastro
                        try {
                            // Preparar uma instância do FuncionárioFactory e para cada ocasião você terá perguntas específicas para cada classe que herda de funcionário
                            // Mudança na classe Main

                            int tipoCadastro = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha o tipo de funcionário a ser cadastrado \n\n(1) Almoxarife \n(2) Analista Local \n(3) Analista Corporativo", "Seleção do Cargo para Cadastro", JOptionPane.INFORMATION_MESSAGE));

                            // Objeto funcionario que recebe um funcionário já criado de FuncionarioFactory
                            Funcionario funcionario = FuncionarioFactory.criarFuncionario(tipoCadastro);

                            boolean sucesso = funcionarioController.inserirFuncionario(funcionario);

                            if (sucesso) {
                                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    case 2: // Login
                        try {
                            boolean autenticado = false;

                            int tipoLogin = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha o tipo de funcionário \n\n(1) Almoxarife \n(2) Analista Local \n(3) Analista Corporativo", "Seleção do Cargo para Login", JOptionPane.INFORMATION_MESSAGE));

                            String nomeLogin = JOptionPane.showInputDialog("Digite o nome");
                            String senhaLogin = JOptionPane.showInputDialog("Digite a senha");

                            List<Funcionario> funcionarios = funcionarioController.listarTodosFuncionarios();

                            // Procura o funcionário do banco de dados
                            Funcionario funcionarioEncontrado = null;
                            for (Funcionario funcionario : funcionarios) {
                                if (funcionario.getNome().equals(nomeLogin) && funcionario.getSenha().equals(senhaLogin)) {
                                    funcionarioEncontrado = funcionario;
                                    break;
                                }
                            }

                            // Se encontrar o funcionário, autentica ele
                            if (funcionarioEncontrado != null) {
                                autenticado = true;

                                switch (tipoLogin) {

                                    case 1: // Login do almoxarife
                                        Almoxarife almoxarifeLogado = new Almoxarife(funcionarioEncontrado);

                                        while (autenticado) {

                                            int opcao = Integer.parseInt(JOptionPane.showInputDialog("====== MENU DO ALMOXARIFE ====== \n\nEscolha uma opção \n(1) Exibir informações do usuário \n(2) Retirar insumo \n(3) Registrar entrada de insumo\n(4) Deslogar\n(5) Sair do Sistema"));

                                            switch (opcao) {
                                                case 1: // Exibir informações do usuário
                                                    almoxarifeLogado.exibirInformacoesDoFuncionario();
                                                    break;

                                                case 2: // Retirar insumo
                                                    String QRCodeInsumo = JOptionPane.showInputDialog("Digite o código de barras do insumo que deseja retirar");
                                                    almoxarifeLogado.retirarInsumo(QRCodeInsumo);

                                                    insumos.removeIf(insumo -> insumo.getQRCode().equals(QRCodeInsumo));
                                                    Movimentacao movimentacaoSaida = new Movimentacao("Transferência", "Saída", 1, Integer.toString(almoxarifeLogado.getIdFuncionario()));

                                                    // Insere a movimentação no banco
                                                    movimentacaoController.inserir(movimentacaoSaida);
                                                    movimentacoes.add(movimentacaoSaida);

                                                    System.out.println("Lista de insumos" + "\n" + insumos);
                                                    System.out.println("Lista de movimentações" + "\n" + movimentacoes);
                                                    break;

                                                case 3: // Registrar entrada de insumo
                                                    Insumo insumo = almoxarifeLogado.registrarEntradaDeInsumo();
                                                    insumo.exibirInformacoesDoInsumo();

                                                    // Adiciona o insumo na lista
                                                    insumos.add(insumo);

                                                    Movimentacao movimentacaoEntrada = new Movimentacao("Reabastecimento", "Entrada", 1, Integer.toString(almoxarifeLogado.getIdFuncionario()));

                                                    // Insere a movimentação no banco
                                                    movimentacaoController.inserir(movimentacaoEntrada);
                                                    movimentacoes.add(movimentacaoEntrada);

                                                    System.out.println("Lista de insumos" + "\n" + insumos);
                                                    System.out.println("Lista de movimentações" + "\n" + movimentacoes);
                                                    break;

                                                case 4: // Deslogar
                                                    autenticado = false;
                                                    break;

                                                case 5: // Sair do sistema
                                                    JOptionPane.showMessageDialog(null, "Até a próxima!");
                                                    System.exit(0);

                                                default:
                                                    JOptionPane.showMessageDialog(null, "Opção inválida");
                                            }
                                        }
                                        break;

                                    case 2: // Login do analista local
                                        AnalistaLocal analistaLocalLogado = new AnalistaLocal(funcionarioEncontrado);

                                            while (autenticado) {
                                                int opcao = Integer.parseInt(JOptionPane.showInputDialog("====== MENU DO ANALISTA LOCAL ====== \n\nEscolha uma opção \n(1) Exibir informações do funcionário \n(2) Realizar pedido \n(3) Verificar movimentações \n(4) Verificar estoque\n(5) Deslogar\n(6) Sair do Sistema"));

                                                switch (opcao) {
                                                    case 1: // Exibir informações do funcionário
                                                        analistaLocalLogado.exibirInformacoesDoFuncionario();
                                                        break;

                                                    case 2: // Realizar pedido
                                                        Pedido pedido = analistaLocalLogado.realizarPedidoDeInsumo();
                                                        pedido.exibirInformacoesDoPedido();

                                                        // Chama o fornecedor para atender o pedido
                                                        Fornecedor fornecedor = new Fornecedor();
                                                        fornecedor.atenderPedido(pedido);

                                                        // Adiciona o pedido na lista
                                                        pedidos.add(pedido);
                                                        Movimentacao movimentacaoPedido = new Movimentacao("Reabastecimento", "Entrada", pedido.getQtdItem(), Integer.toString(pedido.getIdFuncionario()));

                                                        // Insere a movimentação no banco
                                                        movimentacaoController.inserir(movimentacaoPedido);
                                                        movimentacoes.add(movimentacaoPedido);

                                                        System.out.println("Lista de pedidos" + "\n" + pedidos);
                                                        System.out.println("Lista de movimentações" + "\n" + movimentacoes);
                                                        break;

                                                    case 3: // Verificar movimentações
                                                        analistaLocalLogado.verificarMovimentacoes(movimentacoes);
                                                        break;

                                                    case 4: // Verificar estoque
                                                        analistaLocalLogado.verificarEstoque();
                                                        break;

                                                    case 5: // Deslogar
                                                        autenticado = false;
                                                        break;

                                                    case 6: // Sair do sistema
                                                        JOptionPane.showMessageDialog(null, "Até a próxima!");
                                                        System.exit(0);

                                                    default:
                                                        JOptionPane.showMessageDialog(null, "Opção inválida");
                                                }
                                            }

                                        break;

                                    case 3: // Login do analista corporativo
                                        AnalistaCorporativo analistaCorporativoLogado = new AnalistaCorporativo(funcionarioEncontrado);

                                            while (autenticado) {
                                                int opcao = Integer.parseInt(JOptionPane.showInputDialog("====== MENU DO ANALISTA CORPORATIVO ====== \n\n(1) Exibir informações do funcionário \n(2) Visualizar o estado das unidades \n(3) Deslogar\n(4) Sair do Sistema\n"));

                                                switch (opcao) {

                                                    case 1: // Exibir informações do funcionário
                                                        analistaCorporativoLogado.exibirInformacoesDoFuncionario();
                                                        break;
                                                    case 2: // Visualizar o estado das unidades
                                                        analistaCorporativoLogado.visualizarUnidades();
                                                        break;
                                                    case 3: // Deslogar
                                                        autenticado = false;
                                                        break;
                                                    case 4: // Sair do sistema
                                                        JOptionPane.showMessageDialog(null, "Até a próxima!");
                                                        System.exit(0);
                                                    default:
                                                        JOptionPane.showMessageDialog(null, "Opção inválida");
                                                        break;
                                                }
                                            }
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                                        break;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Login falhou. Usuário não encontrado, verifique se digitou o nome e senha corretamente");
                            }
                        } catch (ClassNotFoundException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }

                        break;
                    case 3: // Sair do sistema
                        JOptionPane.showMessageDialog(null, "Até a próxima!");
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }
            }
        }
    }
