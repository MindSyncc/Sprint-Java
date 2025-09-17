package br.com.fiap.model.dto;

public class EstoqueInsumo {
    private int idEstoque;
    private int idInsumo;
    private int quantidade;

    public EstoqueInsumo() {}

    public EstoqueInsumo(int idEstoque, int idInsumo, int quantidade) {
        this.idEstoque = idEstoque;
        this.idInsumo = idInsumo;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
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
