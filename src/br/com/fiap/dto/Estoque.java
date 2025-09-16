package br.com.fiap.dto;

import javax.swing.*;

public class Estoque {
    private int idEstoque;
    private int qtdAtual;
    private int qtdMinima;
    private int qtdMaxima;
    private String status;

    public Estoque() {

    }

    public Estoque(int idEstoque, int qtdAtual, int qtdMinima, int qtdMaxima, String status) {
        this.idEstoque = idEstoque;
        this.qtdAtual = qtdAtual;
        this.qtdMinima = qtdMinima;
        this.qtdMaxima = qtdMaxima;
        this.status = status;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public int getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(int qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public int getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(int qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void exibirInformacoesDoEstoque() {
        String info = String.format(
                """
                ==== Informações do Estoque ====
                
                ID da Unidade: %d
                Quant. Atual: %d
                Quant. Mínima: %d
                Quant. Máxima: %d
                Status: %s
                """,
                idEstoque,
                qtdAtual,
                qtdMinima,
                qtdMaxima,
                status
        );

        JOptionPane.showMessageDialog(null, info, "Detalhes da Unidade", JOptionPane.INFORMATION_MESSAGE);
    }
}
