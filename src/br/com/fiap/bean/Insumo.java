package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;


public class Insumo {
    private int idInsumo;
    private String lote;
    private LocalDate dataValidade;
    private String nome;
    private String unidadeMedida;
    private CategoriaInsumo categoriaInsumo;
    private QRCode QRCode;

    // construtores

    public Insumo() {
    }

    public Insumo(int idInsumo, String lote, LocalDate dataValidade, String nome, String unidadeMedida, CategoriaInsumo categoriaInsumo, br.com.fiap.bean.QRCode QRCode) {
        this.idInsumo = idInsumo;
        this.lote = lote;
        this.dataValidade = dataValidade;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.categoriaInsumo = categoriaInsumo;
        this.QRCode = QRCode;
    }

    // getters/setters

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public CategoriaInsumo getCategoriaInsumo() {
        return categoriaInsumo;
    }

    public void setCategoriaInsumo(CategoriaInsumo categoriaInsumo) {
        this.categoriaInsumo = categoriaInsumo;
    }

    public QRCode getQRCode() {
        return QRCode;
    }

    public void setQRCode(QRCode QRCode) {
        this.QRCode = QRCode;
    }

    // metodos da classe

    /**
     * Classe para exibir as informacoes do insumo em um string formatada
     */
    public String exibirInformacoesDoInsumo() {
        String info = String.format("ID do Insumo: %d%n" +
                "Nome: %s%n" +
                "Validade: %s%n" +
                "Lote: %s%n" +
                "Unidade de Medida: %s%n", idInsumo, nome, dataValidade, lote, unidadeMedida);

        return info;
    }
}
