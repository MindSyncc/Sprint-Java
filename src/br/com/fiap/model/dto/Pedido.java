package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private int idDoPedido;
    private int qtdItem;
    private String nomeItem;
    private LocalDate dataPedido;
    private String status;
    private int idFuncionario;
    private int idFornecedor;

    // construtores

    public Pedido() {
    }

    // construtor com todos os atributos
    public Pedido(int idDoPedido, int qtdItem, String nomeItem, LocalDate dataPedido, String status, int idFuncionario, int idFornecedor) {
        this.idDoPedido = idDoPedido;
        this.qtdItem = qtdItem;
        this.nomeItem = nomeItem;
        this.dataPedido = dataPedido;
        this.status = status;
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
    }

    // construtor sem IdDoPedido
    public Pedido(int qtdItem, String nomeItem, LocalDate dataPedido, String status, int idFuncionario, int idFornecedor) {
        this.qtdItem = qtdItem;
        this.nomeItem = nomeItem;
        this.dataPedido = dataPedido;
        this.status = status;
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
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

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
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
                """,
                idDoPedido,
                nomeItem,
                qtdItem,
                dtf.format(dataPedido),
                status
        );

        JOptionPane.showMessageDialog(null, info, "Detalhes do Pedido", JOptionPane.INFORMATION_MESSAGE);
    }

}
