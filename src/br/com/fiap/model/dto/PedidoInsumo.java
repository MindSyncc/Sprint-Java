package br.com.fiap.model.dto;

public class PedidoInsumo {
    private int idPedido;
    private int idInsumo;
    private int quantidade;

    // Construtor padrão
    public PedidoInsumo() {
    }

    // Construtor completo
    public PedidoInsumo(int idPedido, int idInsumo, int quantidade) {
        this.idPedido = idPedido;
        this.idInsumo = idInsumo;
        this.quantidade = quantidade;
    }

    // getters / setters

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
