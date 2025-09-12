package br.com.fiap.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {
    private static int sequencial = 1;


    private int idMovimentacao;
    private String motivo;
    private LocalDate data;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private String tipoMovimentacao;
    private int quantidade;
    private PrateleiraInteligente prateleira; // Será necessário retirar este atributo
    private String funcionario;

    // construtores

    public Movimentacao() {
    }

    public Movimentacao(String motivo, LocalDate data, String tipoMovimentacao, int quantidade, PrateleiraInteligente prateleira, String funcionario) {
        this.idMovimentacao = sequencial++;
        this.motivo = motivo;
        this.data = data;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.prateleira = prateleira;
        this.funcionario = funcionario;
    }

    // getters/setters

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PrateleiraInteligente getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(PrateleiraInteligente prateleira) {
        this.prateleira = prateleira;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    // metodos da classe

    /**
     *
     * Classe para exibir as informacoes da movimentacao em um string formatada
     *
     */
    public String exibirInformacoesDaMovimentacao() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String info = String.format("ID MOVIMENTAÇÃO: %d \nMOTIVO: %s \nDATA: %s \nTIPO: %s \nQNT: %d \nPRATELEIRA: %d \nRESPONSÁVEL: %s \n\n", idMovimentacao, motivo, dtf.format(data), tipoMovimentacao, quantidade, prateleira.getIdPrateleira(), funcionario
        );

        return info;
    }
}
