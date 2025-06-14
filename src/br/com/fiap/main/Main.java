package br.com.fiap.main;

import br.com.fiap.bean.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Listas
        List<Almoxarife> almoxarifes = new ArrayList<>();
        List<AnalistaLocal> analistasLocais = new ArrayList<>();
        List<AnalistaCorporativo> analistasCorporativos = new ArrayList<>();
        List<Movimentacao> movimentacoes = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        List<Insumo> insumos = new ArrayList<>();

        // mock de prateleira inteligente
        PrateleiraInteligente prateleira = new PrateleiraInteligente(3, "Em manutenção", "Lucas dos Santos");

        // Formatador
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            int escolha = Integer.parseInt(JOptionPane.showInputDialog("MENU PRINCIPAL \n\n Escolha uma opção \n\n1 - Cadastrar \n2 - Login \n3 - Sair"));

            switch (escolha) {
                case 1:
                    try {
                        int tipoCadastro = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de funcionário a ser cadastrado \n1 - Almoxarife \n2 - Analista Local \n3 - Analista Corporativo"));

                        String nomeCadastro = JOptionPane.showInputDialog("Digite o nome que será utilizado no cadastro. Recomendamos que utilize seu nome completo");
                        String senhaCadastro = JOptionPane.showInputDialog("Digite a senha de cadastro. A senha deve conter pelo menos 5 caracteres");

                        LocalDate dataDeNascimento = LocalDate.parse(JOptionPane.showInputDialog("Digite sua data de nascimento"), dtf);
                        String cpf = JOptionPane.showInputDialog("Digite seu CPF");
                        float salario = Float.parseFloat(JOptionPane.showInputDialog("Digite seu salário"));
                        String turno = JOptionPane.showInputDialog("Digite seu turno");


                        switch (tipoCadastro) {
                            case 1: // Cadastro de um almoxarife
                                almoxarifes.add(new Almoxarife(nomeCadastro, senhaCadastro, dataDeNascimento, cpf, salario, turno));
                                System.out.println(almoxarifes);
                                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                                break;

                            case 2: // Cadastro de um Analista Local
                                analistasLocais.add(new AnalistaLocal(nomeCadastro, senhaCadastro, dataDeNascimento, cpf, salario, turno));
                                System.out.println(analistasLocais);
                                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                                break;

                            case 3: // Cadastro de um Analista corporativo
                                String areaDeAtuacao = JOptionPane.showInputDialog("Digite sua área de atuação. Ex: Analista de Previsões");
                                String departamento = JOptionPane.showInputDialog("Digite seu departamento. Ex: Planejamento Estratégico");

                                analistasCorporativos.add(new AnalistaCorporativo(nomeCadastro, senhaCadastro, dataDeNascimento, cpf, salario, turno, areaDeAtuacao, departamento));
                                System.out.println(analistasCorporativos);
                                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Tipo inválido");
                                continue;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 2:
                    int tipoLogin = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de funcionário \n1 - Almoxarife \n2 - Analista Local \n3 - Analista Corporativo"));

                    String nomeLogin = JOptionPane.showInputDialog("Digite o nome");
                    String senhaLogin = JOptionPane.showInputDialog("Digite a senha");

                    boolean autenticado = false;

                    switch (tipoLogin) {
                        case 1:
                            for (Almoxarife almoxarife : almoxarifes) {
                                if (almoxarife.getNome().equals(nomeLogin) && almoxarife.getSenha().equals(senhaLogin)) {
                                    autenticado = true;
                                    while (true) {
                                        int opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO ALMOXARIFE \n\nEscolha uma opção \n1 - Exibir informações do usuário \n2 - Retirar insumo \n3 - Registrar entrada de insumo\n4 - Deslogar\n5 - Sair do Sistema\n"));

                                        switch (opcao) {
                                            case 1: // Exibir informações do usuário
                                                almoxarife.exibirInformacoesDoFuncionario();
                                                break;

                                            case 2: // Retirar insumo
                                                String insumo = JOptionPane.showInputDialog("Digite o nome do insumo que deseja retirar");
                                                boolean existeMotivo = JOptionPane.showConfirmDialog(null, "Deseja inserir o motivo de retirada?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
                                                if (existeMotivo) {
                                                    String motivo = JOptionPane.showInputDialog("Insira o motivo de retirada");
                                                    almoxarife.retirarInsumo(insumos, insumo, motivo);
                                                } else {
                                                    almoxarife.retirarInsumo(insumos, insumo);
                                                }
                                                break;

                                            case 3: // Registrar entrada de insumo
                                                almoxarife.registrarEntradaDeInsumo();
                                                break;

                                            case 4: // Deslogar
                                                break;

                                            case 5: // Sair do Sistema
                                                JOptionPane.showMessageDialog(null, "Até a próxima!");
                                                System.exit(0);
                                                break;


                                            default:
                                                JOptionPane.showMessageDialog(null, "Opção inválida");
                                        }
                                        if (opcao == 4) break;
                                    }
                                    break;
                                }
                            }
                            break;
                        case 2:
                            for (AnalistaLocal analistaLocal : analistasLocais) {
                                if (analistaLocal.getNome().equals(nomeLogin) && analistaLocal.getSenha().equals(senhaLogin)) {
                                    autenticado = true;
                                    while (true) {
                                        int opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO ANALISTA LOCAL \n\nEscolha uma opção \n1 - Exibir informações do funcionário \n2 - Verificar status da prateleira \n3 - Realizar pedido \n4 - Verificar movimentações \n5 - Deslogar\n6 - Sair do Sistema\n"));
                                        switch (opcao) {
                                            case 1: // Exibir informacoes do Funcionario
                                                analistaLocal.exibirInformacoesDoFuncionario();
                                                break;

                                            case 2: // Verificar status da Prateleira
                                                analistaLocal.verificarStatusDaPrateleira(prateleira);
                                                break;

                                            case 3: // Realizar pedido de Insumo()
                                                analistaLocal.realizarPedidoDeInsumo();
                                                break;

                                            case 4: // Verificar movimentacoes()
                                                analistaLocal.verificarMovimentacoes(movimentacoes);
                                                break;

                                            case 5: // Deslogar
                                                System.out.println("Deslogando...");
                                                break;

                                            case 6: // Sair do sistema
                                                JOptionPane.showMessageDialog(null, "Até a próxima!");
                                                System.exit(0);
                                            default:
                                                JOptionPane.showMessageDialog(null, "Opção inválida");
                                        }
                                        if (opcao == 5) break;
                                    }
                                    break;
                                }
                            }
                            break;
                        case 3:
                            AnalistaCorporativo analistaCorporativoBusca = null;
                            boolean encontrou = false;

                            for (AnalistaCorporativo analistaCorporativo : analistasCorporativos) {
                                if (analistaCorporativo.getNome().equals(nomeLogin) && analistaCorporativo.getSenha().equals(senhaLogin)) {
                                    encontrou = true;
                                    analistaCorporativoBusca = analistaCorporativo;
                                    break;
                                }
                            }

                            if (encontrou) {
                                autenticado = true;
                                boolean continuar = true;

                                while (continuar) {
                                    int opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO ANALISTA CORPORATIVO \n\n1 - Exibir informações do funcionário \n2 - Visualizar o estado das unidades \n3 - Deslogar\n4 - Sair do Sistema\n"));

                                    switch (opcao) {

                                        case 1: // Exibir informações do funcionário
                                            analistaCorporativoBusca.exibirInformacoesDoFuncionario();
                                            break;
                                        case 2: // Visualizar o estado das unidades
                                            analistaCorporativoBusca.visualizarUnidades();
                                            break;
                                        case 3: // Deslogar
                                            autenticado = false;
                                            break;
                                        case 4: // Sair do sistema
                                            JOptionPane.showMessageDialog(null, "Até a próxima!");
                                            System.exit(0);
                                        default: // Sair do Sistema
                                            JOptionPane.showMessageDialog(null, "Opção inválida");
                                            break;
                                    }

                                    if (!autenticado) {
                                        break;
                                    }

                                    continuar = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "CONTINUAR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Login falhou. Usuário não encontrado, verifique se digitou o nome e senha corretamente\n");
                            }
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção Inválida");
                            break;
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Até a próxima!");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        }
    }
}
