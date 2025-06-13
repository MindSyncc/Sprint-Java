package br.com.fiap.bean;

import java.time.LocalDate;

public class Pedido {
    private int idDoPedido;
    private int qtdItem;
    private String nomeItem;
    private LocalDate dataPedido;
    private String status;
    private AnalistaLocal analistaLocal;

    // construtores

    public Pedido() {
    }

    public Pedido(int idDoPedido, int qtdItem, String nomeItem, LocalDate dataPedido, String status, AnalistaLocal analistaLocal) {
        this.idDoPedido = idDoPedido;
        this.qtdItem = qtdItem;
        this.nomeItem = nomeItem;
        this.dataPedido = dataPedido;
        this.status = status;
        this.analistaLocal = analistaLocal;
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

    public AnalistaLocal getAnalistaLocal() {
        return analistaLocal;
    }

    public void setAnalistaLocal(AnalistaLocal analistaLocal) {
        this.analistaLocal = analistaLocal;
    }
}
