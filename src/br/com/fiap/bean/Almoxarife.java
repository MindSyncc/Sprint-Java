package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;

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

    // Vê se da para usar super.exiInformacoesDoFuncionario
    public void exibirInformacoesDoFuncionario() {
        String dadosFuncionario = String.format("ID do Funcionário: %d%n" +
                "Nome do Funcionário: %s%n" +
                "Data de Nascimento: %s%n" +
                "CPF do Funcionário: %d%n" +
                "Turno Alocado: %s%n" +
                "Função do Funcionário: %s" +
                "Quantidade de movimentações realizadas nesse dia: %d" +
                "Quantidade de insumos já reabastecidos: %d", getIdFuncionario(), getNome(), getDataDeNascimento(), getCpf(), getTurno(), getFuncao(), qtdOperacoesDia, qtdInsumosReabastecidos);
        JOptionPane.showMessageDialog(null, dadosFuncionario, "Dados do Analista Corporativo", JOptionPane.INFORMATION_MESSAGE);
    }

    public QRCode gerarQRCode() {
        return new QRCode();
    }

    public void retirarinsumo(Insumo insumo) {
        JOptionPane.showMessageDialog(null, "");
    }

    public void retirarinsumo(Insumo insumo, String motivo) {
        JOptionPane.showMessageDialog(null, "");
    }

    public Insumo registrarEntradaDeInsumo() {
        return new Insumo();
    }

    public Insumo registrarEntradaDeInsumo(String motivo) {
        return new Insumo();
    }
}
