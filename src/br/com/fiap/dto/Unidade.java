package br.com.fiap.dto;

import javax.swing.*;
import java.time.format.DateTimeFormatter;

public class Unidade {
    private int idUnidade;
    private String nomeUnidade;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private int idEstoque;

    public Unidade() {

    }

    public Unidade(int idUnidade, String nomeUnidade, String rua, String numero, String bairro, String cidade, String estado, String cep, int idEstoque) {
        this.idUnidade = idUnidade;
        this.nomeUnidade = nomeUnidade;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.idEstoque = idEstoque;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public void exibirInformacoesDaUnidade() {
        String info = String.format(
                """
                ==== Informações do Pedido ====
                
                ID da Unidade: %d
                Nome da Unidade: %s
                Estado: %s
                Bairro: %s
                Rua: %s
                """,
                idUnidade,
                nomeUnidade,
                estado,
                bairro,
                rua
        );

        JOptionPane.showMessageDialog(null, info, "Detalhes da Unidade", JOptionPane.INFORMATION_MESSAGE);
    }
}
