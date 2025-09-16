package br.com.fiap.model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {

    private int idMovimentacao;
    private String motivo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private String tipoMovimentacao;
    private int quantidade;
    private String funcionario;

    // construtores

    public Movimentacao() {
    }

    public Movimentacao(String motivo, String tipoMovimentacao, int quantidade, String funcionario) {
        this.motivo = motivo;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.funcionario = funcionario;

        if (tipoMovimentacao.equalsIgnoreCase("Entrada")) {
            setDataHoraEntrada(LocalDateTime.now());
        } else {
            setDataHoraSaida(LocalDateTime.now());
        }
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
        String info = String.format("ID MOVIMENTAÇÃO: %d \nMOTIVO: %s \nTIPO: %s \nQNT: %d \nRESPONSÁVEL: %s \n\n", idMovimentacao, motivo, tipoMovimentacao, quantidade, funcionario
        );

        return info;
    }
}
