package br.com.fiap.model.dto;

import br.com.fiap.controller.CategoriaInsumoController;
import br.com.fiap.controller.EstoqueInsumoController;
import br.com.fiap.controller.InsumoController;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class Almoxarife extends Funcionario {
    private int qtdOperacoesDia;
    private LocalDate dataUltimoReabastecimento;
    private int qtdInsumosReabastecidos;

    public Almoxarife() {

    }

    public Almoxarife(String funcional, String nome, String senha, LocalDate dataDeNascimento,
                      String cpf, float salario, LocalDate dataInicio, String turno,
                      String cargo, String permissao,
                      String rua, String numero, String bairro, String cidade, String estado, String cep,
                      int idUnidade) {
        super(funcional, nome, senha, dataDeNascimento, cpf, salario, dataInicio, turno,
                cargo, permissao, rua, numero, bairro, cidade, estado, cep, idUnidade);
        this.qtdOperacoesDia = 0;
        this.qtdInsumosReabastecidos = 0;
        this.dataUltimoReabastecimento = null;
    }

    // Construtor que recebe um funcionário
    public Almoxarife(Funcionario f) {
        super(f.getFuncional(), f.getNome(), f.getSenha(), f.getDataDeNascimento(),
                f.getCpf(), f.getSalario(), f.getDataDeInicio(), f.getTurno(),
                f.getCargo(), f.getPermissao(),
                f.getRua(), f.getNumero(), f.getBairro(), f.getCidade(), f.getEstado(), f.getCep(),
                f.getIdUnidade());
        this.setIdFuncionario(f.getIdFuncionario()); // mantém o ID original
        this.qtdOperacoesDia = 0;
        this.qtdInsumosReabastecidos = 0;
        this.dataUltimoReabastecimento = null;
    }

    public int getQtdOperacoesDia() {
        return qtdOperacoesDia;
    }

    public void setQtdOperacoesDia(int qtdOperacoesDia) {
        this.qtdOperacoesDia = qtdOperacoesDia;
    }

    public LocalDate getDataUltimoReabastecimento() {
        return dataUltimoReabastecimento;
    }

    public void setDataUltimoReabastecimento(LocalDate dataUltimoReabastecimento) {
        this.dataUltimoReabastecimento = dataUltimoReabastecimento;
    }

    public int getQtdInsumosReabastecidos() {
        return qtdInsumosReabastecidos;
    }

    public void setQtdInsumosReabastecidos(int qtdInsumosReabastecidos) {
        this.qtdInsumosReabastecidos = qtdInsumosReabastecidos;
    }

    public static String gerarQRCodeUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

    public void exibirInformacoesDoFuncionario() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dados = String.format(
                "ID: %d%nFuncional: %s%nNome: %s%nCPF: %s%nData Nascimento: %s%n" +
                        "Salário: %.2f%nData Início: %s%nData Término: %s%nTurno: %s%nCargo: %s%nPermissão: %s%n" +
                        "Endereço: %s, %s, %s, %s - %s%nCEP: %s%nID Unidade: %d%n" +
                        "Qtd Movimentos Hoje: %d%nQtd Insumos Reabastecidos: %d",
                getIdFuncionario(),
                getFuncional(),
                getNome(),
                getCpf(),
                getDataDeNascimento().format(dtf),
                getSalario(),
                getDataDeInicio().format(dtf),
                getDataTermino() != null ? getDataTermino().format(dtf) : "N/A",
                getTurno(),
                getCargo(),
                getPermissao(),
                getRua(),
                getNumero(),
                getBairro(),
                getCidade(),
                getEstado(),
                getCep(),
                getIdUnidade(),
                qtdOperacoesDia,
                qtdInsumosReabastecidos
        );
        JOptionPane.showMessageDialog(null, dados, "Informações do Almoxarife", JOptionPane.INFORMATION_MESSAGE);
    }

    public void retirarInsumo(String QRCodeInsumo) {

        // Cria os controladores necessários
        InsumoController insumoController = new InsumoController();
        EstoqueInsumoController estoqueInsumoController = new EstoqueInsumoController();

        try {
            // 1. Busca o insumo pelo QRCode
            Insumo insumo = insumoController.listarUmInsumo(QRCodeInsumo);
            if (insumo == null) {
                JOptionPane.showMessageDialog(null,
                        "Insumo não encontrado com este QRCode!",
                        "ERRO", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Buscar o registro do insumo no estoque
            int idEstoquePrincipal = 1; // Estoque principal de teste
            EstoqueInsumo estoqueInsumo = estoqueInsumoController.listarUmEstoqueInsumo(idEstoquePrincipal, insumo.getIdInsumo());

            if (estoqueInsumo == null) {
                JOptionPane.showMessageDialog(null,
                        "Este insumo ainda não está vinculado ao estoque!",
                        "ERRO", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (estoqueInsumo != null && estoqueInsumo.getQuantidade() > 0) {
                estoqueInsumo.setQuantidade(estoqueInsumo.getQuantidade() - 1);

                // atualiza o Estoque com o insumo já retirado e aumenta a qnt de operações por dia do Almoxarife
                String resultado = estoqueInsumoController.atualizarEstoqueInsumo(estoqueInsumo);
                setQtdOperacoesDia(getQtdOperacoesDia() + 1);

                JOptionPane.showMessageDialog(null, resultado, "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Não há quantidade disponível deste insumo no estoque.",
                        "ESTOQUE VAZIO", JOptionPane.WARNING_MESSAGE);
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro: " + e.getMessage(),
                    "ERRO", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public Insumo registrarEntradaDeInsumo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null,
                "Para registrar um novo insumo é necessário preencher algumas informações relevantes",
                "PREENCHIMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        Insumo novoInsumo = null;

        try {
            // ===== Entrada dos dados =====
            String nomeDoInsumo = JOptionPane.showInputDialog("Digite o nome do insumo recebido: ");
            String loteDoInsumo = JOptionPane.showInputDialog("Digite o lote do insumo recebido: ");
            String unidadeDeMedida = JOptionPane.showInputDialog("Digite a unidade de medida do insumo (ou 'Unitário'): ");
            LocalDate dataDeValidade = LocalDate.parse(
                    JOptionPane.showInputDialog("Digite a data de validade prescrita no insumo recebido (dd/MM/yyyy): "),
                    dtf);

            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade recebida"));

            // Registro de categoria
            boolean registrarCategoriaDoInsumo = JOptionPane.showConfirmDialog(null, "Gostaria de detalhar a categoria do insumo recebido?", "ADICIONAR CATEGORIA DO INSUMO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

            CategoriaInsumo categoriaInsumo = null;
            if (registrarCategoriaDoInsumo) {
                categoriaInsumo = registrarCategoria(); // aqui já retornaria a categoria criada
            }

            // gera um QRCode para o insumo recebido
            String qrCode = gerarQRCodeUUID();

            // Criação do objeto insumo
            novoInsumo = new Insumo(
                    loteDoInsumo,
                    dataDeValidade,
                    nomeDoInsumo,
                    unidadeDeMedida,
                    categoriaInsumo != null ? categoriaInsumo.getIdCategoria() : null,
                    qrCode
            );

            // Cria os controladores para acessar o banco
            InsumoController insumoController = new InsumoController();
            EstoqueInsumoController estoqueInsumoController = new EstoqueInsumoController();

            // Verifica a existência do insumo recebido
            int idInsumo = 0;
            Insumo insumoExiste = insumoController.listarUmInsumo(qrCode);

            if (insumoExiste == null) {
                // não existe, então cria
                insumoController.inserirInsumo(novoInsumo);
                idInsumo = insumoController.listarUmInsumo(novoInsumo.getQRCode()).getIdInsumo();

            } else { // existe, então recupera o ID para vínculo com EstoqueInsumo
                idInsumo = insumoExiste.getIdInsumo();
            }

            // Vincula o insumo ao estoque (Criação de uma classe EstoqueInsumo)
            int idEstoquePrincipal = 1; // supondo estoque padrão
            EstoqueInsumo estoqueInsumo = estoqueInsumoController.listarUmEstoqueInsumo(idEstoquePrincipal, idInsumo);

            if (estoqueInsumo == null) {
                // Se não existe vínculo, cria o registro de estoqueInsumo
                estoqueInsumo = new EstoqueInsumo(idEstoquePrincipal, idInsumo, quantidade);
                estoqueInsumoController.inserirEstoqueInsumo(estoqueInsumo);
            } else {
                // Se já existe, apenas atualiza o registro aumentando a quantidade
                estoqueInsumo.setQuantidade(quantidade);
                estoqueInsumoController.atualizarEstoqueInsumo(estoqueInsumo);
            }

            setDataUltimoReabastecimento(LocalDate.now());

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar insumo: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return novoInsumo;
    }

    public Insumo registrarEntradaDeInsumo(String motivo) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null,
                "Para registrar um novo insumo é necessário preencher algumas informações relevantes",
                "PREENCHIMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        Insumo novoInsumo = null;

        try {
            // ===== Entrada dos dados =====
            String nomeDoInsumo = JOptionPane.showInputDialog("Digite o nome do insumo recebido: ");
            String loteDoInsumo = JOptionPane.showInputDialog("Digite o lote do insumo recebido: ");
            String unidadeDeMedida = JOptionPane.showInputDialog("Digite a unidade de medida do insumo (ou 'Unitário'): ");
            LocalDate dataDeValidade = LocalDate.parse(
                    JOptionPane.showInputDialog("Digite a data de validade prescrita no insumo recebido (dd/MM/yyyy): "),
                    dtf);

            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade recebida"));

            // Registro de categoria
            boolean registrarCategoriaDoInsumo = JOptionPane.showConfirmDialog(null, "Gostaria de detalhar a categoria do insumo recebido?", "ADICIONAR CATEGORIA DO INSUMO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

            CategoriaInsumo categoriaInsumo = null;
            if (registrarCategoriaDoInsumo) {
                categoriaInsumo = registrarCategoria(); // aqui já retornaria a categoria criada
            }

            // gera um QRCode para o insumo recebido
            String qrCode = gerarQRCodeUUID();

            // Criação do objeto insumo
            novoInsumo = new Insumo(
                    loteDoInsumo,
                    dataDeValidade,
                    nomeDoInsumo,
                    unidadeDeMedida,
                    categoriaInsumo != null ? categoriaInsumo.getIdCategoria() : null,
                    qrCode
            );

            // Cria os controladores para acessar o banco
            InsumoController insumoController = new InsumoController();
            EstoqueInsumoController estoqueInsumoController = new EstoqueInsumoController();

            // Verifica a existência do insumo recebido
            int idInsumo = 0;
            Insumo insumoExiste = insumoController.listarUmInsumo(qrCode);

            if (insumoExiste == null) {
                // não existe, então cria
                insumoController.inserirInsumo(novoInsumo);
                idInsumo = insumoController.listarUmInsumo(novoInsumo.getQRCode()).getIdInsumo();

            } else { // existe, então recupera o ID para vínculo com EstoqueInsumo
                idInsumo = insumoExiste.getIdInsumo();
            }

            // Vincula o insumo ao estoque (Criação de uma classe EstoqueInsumo)
            int idEstoquePrincipal = 1; // supondo estoque padrão
            EstoqueInsumo estoqueInsumo = estoqueInsumoController.listarUmEstoqueInsumo(idEstoquePrincipal, idInsumo);

            if (estoqueInsumo == null) {
                // Se não existe vínculo, cria o registro de estoqueInsumo
                estoqueInsumo = new EstoqueInsumo(idEstoquePrincipal, idInsumo, quantidade);
                estoqueInsumoController.inserirEstoqueInsumo(estoqueInsumo);
            } else {
                // Se já existe, apenas atualiza o registro aumentando a quantidade
                estoqueInsumo.setQuantidade(quantidade);
                estoqueInsumoController.atualizarEstoqueInsumo(estoqueInsumo);
            }

            setDataUltimoReabastecimento(LocalDate.now());
            JOptionPane.showMessageDialog(null, "Insumo registrado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar insumo: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return novoInsumo;
    }

    public CategoriaInsumo registrarCategoria() {
        CategoriaInsumoController categoriaController = new CategoriaInsumoController();

        List<CategoriaInsumo> listaDeCategorias = categoriaController.listarTodasCategorias();

        // Monta as opções para o JOptionPane
        String categorias = "";
        int index = 0;
        for (CategoriaInsumo categoria : listaDeCategorias) {
            categorias += index + " - " + categoria.getTipoCategoria() + "\n";
            index++;
        }
        categorias += index + " - " + "Outra Categoria"; // última opção

        int escolha = 0;

        // Menu de escolha de categoria
        try {
            escolha = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione a categoria do insumo: \n\n" + categorias, "Inserção de categoria", JOptionPane.QUESTION_MESSAGE
            ));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Opção inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        CategoriaInsumo categoriaEscolhida = null;

        // Cria uma nova categoria no banco de dados
        if (escolha == listaDeCategorias.size()) {
            String nomeCategoria = JOptionPane.showInputDialog("Digite o nome da nova categoria:");
            categoriaEscolhida = new CategoriaInsumo(nomeCategoria);
            categoriaController.inserirCategoria(categoriaEscolhida); //
        }
        // Recebe uma categoria já existente
        else if (escolha >= 0 && escolha < listaDeCategorias.size()) {
            categoriaEscolhida = listaDeCategorias.get(escolha);
        }
        else {
            JOptionPane.showMessageDialog(null, "Opção inválida!");
        }

        return categoriaEscolhida;
    }
}
