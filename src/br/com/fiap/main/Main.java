    package br.com.fiap.main;

    import br.com.fiap.controller.FuncionarioController;
    import br.com.fiap.dao.FuncionarioFactory;
    import br.com.fiap.dto.*;

    import javax.swing.*;
    import java.sql.SQLException;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.*;

    public class Main {
        public static void main(String[] args) {
            FuncionarioController funcionarioController = new FuncionarioController();

            // Listas
            List<Movimentacao> movimentacoes = new ArrayList<>();
            List<Pedido> pedidos = new ArrayList<>();
            List<Insumo> insumos = new ArrayList<>();

            // Formatador
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (true) {
                int escolha = Integer.parseInt(JOptionPane.showInputDialog(null, "Bem-vindo ao Sistema de Gerenciamento! \nPor favor, selecione uma opção \n\n(1) Cadastrar \n(2) Login \n(3) Sair do sistema", "Menu inicial", JOptionPane.INFORMATION_MESSAGE));

                switch (escolha) {
                    case 1: // Cadastro
                        try {
                            // Preparar uma instância do FuncionárioFactory e para cada ocasião você terá perguntas específicas para cada classe que herda de funcionário
                            // Mudança na classe Main

                            int tipoCadastro = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha o tipo de funcionário a ser cadastrado \n\n(1) Almoxarife \n(2) Analista Local \n(3) Analista Corporativo", "Seleção do Cargo para Cadastro", JOptionPane.INFORMATION_MESSAGE));
                            Funcionario funcionario = FuncionarioFactory.criarFuncionario(tipoCadastro);

                            boolean sucesso = funcionarioController.inserirFuncionario(funcionario);

                            // Achar uma forma de registar na lista de funcionários o funcionário específico

                            if (sucesso) {
                                switch (tipoCadastro) {
                                    case 1: // Cadastro de um almoxarife
                                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                                        break;

                                    case 2: // Cadastro de um Analista Local
                                        System.out.println("Lista de analistas locais" + "\n");
                                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                                        break;

                                    case 3: // Cadastro de um Analista corporativo
                                        String areaDeAtuacao = JOptionPane.showInputDialog("Digite sua área de atuação. Ex: Analista de Previsões");
                                        String departamento = JOptionPane.showInputDialog("Digite seu departamento. Ex: Planejamento Estratégico");

                                        System.out.println("Lista de analistas corporativos" + "\n");
                                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Tipo inválido");
                                        continue;
                                }
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    case 2: // Login
                        try {
                            int tipoLogin = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha o tipo de funcionário \n\n(1) Almoxarife \n(2) Analista Local \n(3) Analista Corporativo", "Seleção do Cargo para Login", JOptionPane.INFORMATION_MESSAGE));

                            String nomeLogin = JOptionPane.showInputDialog("Digite o nome");
                            String senhaLogin = JOptionPane.showInputDialog("Digite a senha");

                            boolean autenticado = false;

                            List<Funcionario> funcionarios = funcionarioController.listarTodosFuncionarios();

                            // procura no banco
                            Funcionario funcionarioEncontrado = null;
                            for (Funcionario funcionario : funcionarios) {
                                if (funcionario.getNome().equals(nomeLogin) && funcionario.getSenha().equals(senhaLogin)) {
                                    funcionarioEncontrado = funcionario;
                                    break;
                                }
                            }

                            if (funcionarioEncontrado != null) {
                                autenticado = true;

                                switch (tipoLogin) {
                                    case 1: // Login do almoxarife
                                        Almoxarife almoxarifeLogado = new Almoxarife(funcionarioEncontrado);

                                        while (autenticado) {
                                            int opcao = Integer.parseInt(JOptionPane.showInputDialog("====== MENU DO ALMOXARIFE ====== \n\nEscolha uma opção \n(1) Exibir informações do usuário \n(2) Retirar insumo \n(3) Registrar entrada de insumo\n(4) Deslogar\n(5) Sair do Sistema"));

                                            switch (opcao) {
                                                case 1: // Exibir informações do usuário

                                                    break;

                                                case 2: // Retirar insumo
                                                    String nomeInsumo = JOptionPane.showInputDialog("Digite o nome do insumo que deseja retirar");
                                                    boolean existeMotivoRetirada = JOptionPane.showConfirmDialog(null, "Deseja inserir o motivo de retirada?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
                                                    if (existeMotivoRetirada) {
                                                        String motivoDeRetirada = JOptionPane.showInputDialog("Insira o motivo de retirada");
                                                        insumos = almoxarifeLogado.retirarInsumo(insumos, nomeInsumo, motivoDeRetirada);
                                                    } else {
                                                        insumos = almoxarifeLogado.retirarInsumo(insumos, nomeInsumo);
                                                    }
                                                    System.out.println("Lista de insumos" + "\n" + insumos);

                                                    movimentacoes.add(new Movimentacao("CONSUMO", "SAIDA", 1, Integer.toString(almoxarifeLogado.getIdFuncionario())));
                                                    break;

                                                case 3: // Registrar entrada de insumo
                                                    boolean existeMotivoRegistro = JOptionPane.showConfirmDialog(null, "Deseja inserir o motivo do registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
                                                    Insumo insumo;
                                                    if (existeMotivoRegistro) {
                                                        String motivoDeRegistro = JOptionPane.showInputDialog("Insira o motivo de registro");
                                                        insumo = almoxarifeLogado.registrarEntradaDeInsumo(motivoDeRegistro);
                                                        insumos.add(insumo);
                                                    } else {
                                                        insumo = almoxarifeLogado.registrarEntradaDeInsumo();
                                                        insumos.add(insumo);
                                                    }
                                                    System.out.println("Lista de insumos" + "\n" + insumos);

                                                    movimentacoes.add(new Movimentacao("RETIRADA", "ENTRADA", 1, Integer.toString(almoxarifeLogado.getIdFuncionario())));
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
                                                int opcao = Integer.parseInt(JOptionPane.showInputDialog("====== MENU DO ANALISTA LOCAL ====== \n\nEscolha uma opção \n(1) Exibir informações do funcionário \n(2) Realizar pedido \n(3) Verificar movimentações \n(4) Deslogar\n(5) Sair do Sistema"));

                                                switch (opcao) {
                                                    case 1: // Exibir informações do funcionário
                                                        analistaLocalLogado.exibirInformacoesDoFuncionario();
                                                        break;

                                                    case 2: // Realizar pedido
                                                        Pedido pedido = analistaLocalLogado.realizarPedidoDeInsumo();
                                                        pedido.exibirInformacoesDoPedido();
                                                        pedidos.add(pedido);

                                                        movimentacoes.add(new Movimentacao("REABASTECIMENTO", "ENTRADA", pedido.getQtdItem(), Integer.toString(pedido.getIdFuncionario())));

                                                        System.out.println("Lista de pedidos" + "\n" + pedidos);
                                                        break;

                                                    case 3: // Verificar movimentações
                                                        analistaLocalLogado.verificarMovimentacoes(movimentacoes);
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

                                    case 3: // Login do analista corporativo
                                        AnalistaCorporativo analistaCorporativoLogado = new AnalistaCorporativo(funcionarioEncontrado);
                                        boolean encontrouAnalistaCorporativo = false;


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
