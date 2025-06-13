package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movimentacao {
    private int idMovimentacao;
    private String motivo;
    private LocalDate data;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private String tipoMovimentacao;
    private int quantidade;
    private PrateleiraInteligente prateleira;
    private Funcionario funcionario;

    // construtores

    public Movimentacao() {
    }

    public Movimentacao(int idMovimentacao, String motivo, LocalDate data, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida, String tipoMovimentacao, int quantidade, PrateleiraInteligente prateleira, Funcionario funcionario) {
        this.idMovimentacao = idMovimentacao;
        this.motivo = motivo;
        this.data = data;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = dataHoraSaida;
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    // metodos da classe

    /**
     *
     * Classe para exibir as informacoes do insumo em um string formatada
     *
     */
    public void exibirInformacoesDaMovimentacao() {
        String info = String.format(
                """
                🔄 Informações da Movimentação 🔄
                📦 ID Movimentação: %d
                📝 Motivo: %s
                📅 Data: %s
                ⏰ Entrada: %s
                ⏳ Saída: %s
                📂 Tipo: %s
                🔢 Quantidade: %d
                📍 Prateleira: %s
                👤 Feito pelo funcionário: %s
                """,
                idMovimentacao,
                motivo,
                data,
                dataHoraEntrada,
                dataHoraSaida,
                tipoMovimentacao,
                quantidade,
                prateleira,
                funcionario
        );

        JOptionPane.showMessageDialog(null, info, "Detalhes da Movimentação", JOptionPane.INFORMATION_MESSAGE);
    }
}
