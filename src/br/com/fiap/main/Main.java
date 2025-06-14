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

        // Formatador
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            int escolha = Integer.parseInt(JOptionPane.showInputDialog("MENU PRINCIPAL \n\n Escolha uma opção \n\n1 - Cadastrar \n2 - Login \n3 - Sair"));

            switch (escolha) {
                case 1:
                    int tipoCadastro = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de funcionário a ser cadastrado \n1 - Almoxarife \n2 - Analista Local \n3 - Analista Corporativo"));

                    String nomeCadastro = JOptionPane.showInputDialog("Digite o nome que será utilizado no cadastro. Recomendamos que utilize seu nome completo");
                    String senhaCadastro = JOptionPane.showInputDialog("Digite a senha de cadastro. A senha deve conter pelo menos 5 caracteres");

                    LocalDate dataDeNascimento = LocalDate.parse(JOptionPane.showInputDialog("Digite sua data de nascimento"), dtf);
                    String cpf = JOptionPane.showInputDialog("Digite seu CPF");
                    float salario = Float.parseFloat(JOptionPane.showInputDialog("Digite seu salário"));
                    String turno = JOptionPane.showInputDialog("Digite seu turno");


                    switch (tipoCadastro) {
                        case 1: // Cadastro de um almoxarife
                            break;

                        case 2: // Cadastro de um Analista Local
                            break;

                        case 3: // Cadastro de um Analista corporativo
                            String areaDeAtuacao = JOptionPane.showInputDialog("Digite sua área de atuação. Ex: Analista de Previsões");
                            String departamento = JOptionPane.showInputDialog("Digite seu departamento. Ex: Planejamento Estratégico");

                            analistasCorporativos.add(new AnalistaCorporativo(nomeCadastro, senhaCadastro, dataDeNascimento, cpf, salario, turno, areaDeAtuacao, departamento));
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Tipo inválido");
                            continue;
                    }

                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\n");
                    break;

                case 2:
                    int tipoLogin = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de funcionário \n1 - Almoxarife \n2 - Analista Local \n3 - Analista Corporativo"));

                    String nomeLogin = JOptionPane.showInputDialog("Digite o nome");
                    String senhaLogin = JOptionPane.showInputDialog("Digite a senha");

                    boolean autenticado = false;

                    if (tipoLogin == 1) {
                        for (Almoxarife almoxarife : almoxarifes) {
                            if (almoxarife.getNome().equals(nomeLogin) && almoxarife.getSenha().equals(senhaLogin)) {
                                autenticado = true;
                                while (true) {
                                    int opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO ALMOXARIFE \n\nEscolha uma opção \n1 - Exibir informações do usuário \n2 - Gerar QRCode \n3 - Retirar insumo \n4 - Registrar entrada de insumo\n5 - Deslogar\n6 - Sair do Sistema\n"));

                                    switch (opcao) {
                                        case 1: // Exibir informações do usuário
                                            break;

                                        case 2: // Gerar QRCode
                                            break;

                                        case 3: // Retirar insumo
                                            break;

                                        case 4: // Registrar entrada de insumo
                                            break;

                                        case 5: // Deslogar
                                            break;

                                        case 6: // Sair do Sistema
                                            JOptionPane.showMessageDialog(null, "Até a próxima!");
                                            System.exit(0);
                                            break;

                                        default:
                                            JOptionPane.showMessageDialog(null, "Opção inválida");
                                    }
                                    if (opcao == 5) break;
                                }
                                break;
                            }
                        }

                    } else if (tipoLogin == 2) {
                        for (AnalistaLocal analistaLocal : analistasLocais) {
                            if (analistaLocal.getNome().equals(nomeLogin) && analistaLocal.getSenha().equals(senhaLogin)) {
                                autenticado = true;
                                while (true) {
                                    int opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO ANALISTA LOCAL \n\nEscolha uma opção \n1 - Exibir informações do funcionário \n2 - Verificar status da prateleira \n3 - Realizar pedido \n4 - Verificar movimentações \n5 - Deslogar\n6 - Sair do Sistema\n"));
                                    switch (opcao) {
                                        case 1: // Exibir informacoes do Funcionario
                                            break;

                                        case 2: // Verificar status da Prateleira
                                            break;

                                        case 3: // Realizar pedido de Insumo()
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
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opção inválida");
                                    }
                                    if (opcao == 5) break;
                                }
                                break;
                            }
                        }

                    } else if (tipoLogin == 3) {
                        for (AnalistaCorporativo analistaCorporativo : analistasCorporativos) {
                            if (analistaCorporativo.getNome().equals(nomeLogin) && analistaCorporativo.getSenha().equals(senhaLogin)) {
                                autenticado = true;
                                while (true) {
                                    int opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU DO ANALISTA CORPORATIVO \n\n1 - Exibir informações do funcionário \n2 - Visualizar o estado das unidades \n3 - Deslogar\n4 - Sair do Sistema\n"));
                                    switch (opcao) {

                                        case 1: // Exibir informações do funcionário
                                            break;

                                        case 2: // Visualizar o estado das unidades
                                            break;

                                        case 3: // Deslogar
                                            break;

                                        case 4: // Sair do sistema
                                            JOptionPane.showMessageDialog(null, "Até a próxima!");
                                            System.exit(0);
                                            break;

                                        default: // Sair do Sistema
                                            JOptionPane.showMessageDialog(null, "Opção inválida");
                                    }

                                    if (opcao == 3) {
                                        break;
                                    }
                                }
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Tipo inválido.");
                    }

                    if (!autenticado) {
                        JOptionPane.showMessageDialog(null, "Login falhou. Verifique nome e senha.\n");
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
