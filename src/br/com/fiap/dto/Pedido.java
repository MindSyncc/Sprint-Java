package br.com.fiap.dto;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private int idDoPedido;
    private int qtdItem;
    private String nomeItem;
    private LocalDate dataPedido;
    private String status;
    private String analistaResponsavelPeloPedido;

    // construtores

    public Pedido() {
    }

    public Pedido(int idDoPedido, int qtdItem, String nomeItem, LocalDate dataPedido, String status, String analistaResponsavelPeloPedido) {
        this.idDoPedido = idDoPedido;
        this.qtdItem = qtdItem;
        this.nomeItem = nomeItem;
        this.dataPedido = dataPedido;
        this.status = status;
        this.analistaResponsavelPeloPedido = analistaResponsavelPeloPedido;
    }

    // getters/setters

    public int getIdDoPedido() {
        return idDoPedido;
    }

    public void setIdDoPedido(int idDoPedido) {
        this.idDoPedido = idDoPedido;
    }

    public int getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(int qtdItem) {
        this.qtdItem = qtdItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnalistaResponsavelPeloPedido() {
        return analistaResponsavelPeloPedido;
    }

    public void setAnalistaResponsavelPeloPedido(String analistaResponsavelPeloPedido) {
        this.analistaResponsavelPeloPedido = analistaResponsavelPeloPedido;
    }

    public void exibirInformacoesDoPedido() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String info = String.format(
                """
                ==== Informações do Pedido ====
                
                🆔 ID do Pedido: %d
                📋 Nome do Item: %s
                🔢 Quantidade: %d
                📅 Data do Pedido: %s
                📌 Status: %s
                👨‍💼 Analista Responsável: %s
                """,
                idDoPedido,
                nomeItem,
                qtdItem,
                dtf.format(dataPedido),
                status,
                analistaResponsavelPeloPedido
        );

        JOptionPane.showMessageDialog(null, info, "Detalhes do Pedido", JOptionPane.INFORMATION_MESSAGE);
    }

}
