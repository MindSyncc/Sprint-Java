package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Almoxarife extends Funcionario {
    private int qtdOperacoesDia;
    private LocalDate dataUltimoReabastecimento;
    private int qtdInsumosReabastecidos;

    public Almoxarife() {

    }

    public Almoxarife(int idFuncionario, String nome, String senha, LocalDate dataDeNascimento, String cpf, float salario, String turno, String funcao, int qtdOperacoesDia, LocalDate dataUltimoReabastecimento, int qtdInsumosReabastecidos) {
        super(idFuncionario, nome, senha, dataDeNascimento, cpf, salario, turno, funcao);
        this.qtdOperacoesDia = qtdOperacoesDia;
        this.dataUltimoReabastecimento = dataUltimoReabastecimento;
        this.qtdInsumosReabastecidos = qtdInsumosReabastecidos;
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

    public void exibirInformacoesDoFuncionario() {
        String dadosFuncionario = String.format("ID do Funcionário: %d%n" +
                "Nome do Funcionário: %s%n" +
                "Data de Nascimento: %s%n" +
                "CPF do Funcionário: %s%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s" +
                "Quantidade de movimentações realizadas nesse dia: %d" +
                "Quantidade de insumos já reabastecidos: %d", getIdFuncionario(), getNome(), getDataDeNascimento(), getCpf(), getTurno(), getFuncao(), qtdOperacoesDia, qtdInsumosReabastecidos);
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Analista Corporativo", JOptionPane.INFORMATION_MESSAGE);
    }

    public QRCode gerarQRCode() {
        JOptionPane.showMessageDialog(null, "Gerando um QRCode...");

        // Informações do QRCode Mocadas
        int IdQRCode = 22;
        LocalDate dataDeCriacao = LocalDate.now();
        boolean statusQRCode = true;

        return new QRCode(IdQRCode, dataDeCriacao, null, statusQRCode);
    }

    public List<Insumo> retirarInsumo(List<Insumo> listaDeInsumos, String nomeDoInsumo) {
        Insumo insumoBuscado = null;
        boolean encontrou = false;

        for (Insumo insumo: listaDeInsumos) {
            if (insumo.getNome().equalsIgnoreCase(nomeDoInsumo)) {
                encontrou = true;
                insumoBuscado = insumo;
                break;
            }
        }

        listaDeInsumos.remove(insumoBuscado);
        if (encontrou) {
            JOptionPane.showMessageDialog(null, "Produto Encontrado!");
            JOptionPane.showMessageDialog(null, insumoBuscado.exibirInformacoesDoInsumo(), "INFORMAÇÕES DO INSUMO", JOptionPane.INFORMATION_MESSAGE);
            return listaDeInsumos;
        }

        JOptionPane.showMessageDialog(null, "Produto não encontrado, por favor verifique se o nome do insumo foi digitado corretamente", "ERRO",  JOptionPane.WARNING_MESSAGE);
        return listaDeInsumos;
    }

    public List<Insumo> retirarInsumo(List<Insumo> listaDeInsumos, String nomeDoInsumo, String motivoDeRetirada) {
        Insumo insumoBuscado = null;
        boolean encontrou = false;

        for (Insumo insumo: listaDeInsumos) {
            if (insumo.getNome().equalsIgnoreCase(nomeDoInsumo)) {
                encontrou = true;
                insumoBuscado = insumo;
                break;
            }
        }

        if (encontrou) {
            listaDeInsumos.remove(insumoBuscado);
            JOptionPane.showMessageDialog(null, "Produto Encontrado!");
            String mensagem = "Motivo de retirada: " + motivoDeRetirada + "\n" + insumoBuscado.exibirInformacoesDoInsumo();
            JOptionPane.showMessageDialog(null, mensagem, "INFORMAÇÕES DO INSUMO", JOptionPane.INFORMATION_MESSAGE);
            return listaDeInsumos;
        }

        JOptionPane.showMessageDialog(null, "Produto não encontrado, por favor verifique se o nome do insumo foi digitado corretamente", "ERRO",  JOptionPane.WARNING_MESSAGE);
        return listaDeInsumos;
    }

    public Insumo registrarEntradaDeInsumo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, "Para registar um novo insumo e necessário preencher algumas informações relevantes", "PREENCHIEMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        // Dados para a criação do objeto Insumo
        int idInsumo = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Insumo recebido: "));
        String nomeDoInsumo = JOptionPane.showInputDialog("Digite o nome do insumo recebido: ");
        String loteDoInsumo = JOptionPane.showInputDialog("Digite o lote do insumo recebido: ");
        String unidadeDeMedida = JOptionPane.showInputDialog("Digite a unidade de medida do insumo (Caso o insumo não possuia uma unidade de medida digite Unitário): ");
        LocalDate dataDeValidade = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de validade prescrita no insumo recebido: "), dtf);

        boolean registrarCategoriaDoInsumo = JOptionPane.showConfirmDialog(null, "Gostaria de detalhar a categoria do insumo recebido?","ADICIONAR CATEGORIA DO INSUMO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        CategoriaInsumo categoriaInsumo = null;
        if (registrarCategoriaDoInsumo) {
            int idCategoria = 20;
            String tipoCategoria = JOptionPane.showInputDialog("Digite a categoria do insumo recebido: ");
            String responsavelPeloRegistro = getNome();

            categoriaInsumo = new CategoriaInsumo(idCategoria, tipoCategoria, responsavelPeloRegistro);
        }

        QRCode qrCode = null;
        boolean etiquetarQRCode = JOptionPane.showConfirmDialog(null, "Gostaria de gerar um QRCode para etiquetar o insumo recebido?","GERAR QRCODE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        if (etiquetarQRCode) {
            qrCode = gerarQRCode();
        }

        JOptionPane.showMessageDialog(null, "Insumo registrado com sucesso!", "EXITO", JOptionPane.INFORMATION_MESSAGE);

        return new Insumo(idInsumo, loteDoInsumo, dataDeValidade, nomeDoInsumo, unidadeDeMedida, categoriaInsumo, qrCode);
    }

    public Insumo registrarEntradaDeInsumo(String motivo) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, "Para registar um novo insumo e necessário preencher algumas informações relevantes", "PREENCHIEMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        // Dados para a criação do objeto Insumo
        int idInsumo = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Insumo recebido: "));
        String nomeDoInsumo = JOptionPane.showInputDialog("Digite o nome do insumo recebido: ");
        String loteDoInsumo = JOptionPane.showInputDialog("Digite o lote do insumo recebido: ");
        String unidadeDeMedida = JOptionPane.showInputDialog("Digite a unidade de medida do insumo (Caso o insumo não possuia uma unidade de medida digite Unitário): ");
        LocalDate dataDeValidade = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de validade prescrita no insumo recebido: "), dtf);

        boolean registrarCategoriaDoInsumo = JOptionPane.showConfirmDialog(null, "Gostaria de detalhar a categoria do insumo recebido?","ADICIONAR CATEGORIA DO INSUMO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        CategoriaInsumo categoriaInsumo = null;
        if (registrarCategoriaDoInsumo) {
            int idCategoria = 20;
            String tipoCategoria = JOptionPane.showInputDialog("Digite a categoria do insumo recebido: ");
            String responsavelPeloRegistro = getNome();

            categoriaInsumo = new CategoriaInsumo(idCategoria, tipoCategoria, responsavelPeloRegistro);
        }

        QRCode qrCode = null;
        boolean etiquetarQRCode = JOptionPane.showConfirmDialog(null, "Gostaria de gerar um QRCode para etiquetar o insumo recebido?","GERAR QRCODE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        if (etiquetarQRCode) {
            qrCode = gerarQRCode();
        }

        String mensagem = "Insumo registrado com sucesso! \n" + "Motivo do registo" + motivo;
        JOptionPane.showMessageDialog(null, mensagem, "EXITO", JOptionPane.INFORMATION_MESSAGE);

        return new Insumo(idInsumo, loteDoInsumo, dataDeValidade, nomeDoInsumo, unidadeDeMedida, categoriaInsumo, qrCode);
    }
}
