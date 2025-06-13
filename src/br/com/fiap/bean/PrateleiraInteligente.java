package br.com.fiap.bean;

import javax.swing.*;

public class PrateleiraInteligente {
    private int idPrateleira;
    private String status;
    private AnalistaLocal responsavelPelaVerificacao;

    // construtores

    public PrateleiraInteligente() {
    }

    public PrateleiraInteligente(int idPrateleira, String status, AnalistaLocal responsavelPelaVerificacao) {
        this.idPrateleira = idPrateleira;
        this.status = status;
        this.responsavelPelaVerificacao = responsavelPelaVerificacao;
    }

    // getters/setters

    public int getIdPrateleira() {
        return idPrateleira;
    }

    public void setIdPrateleira(int idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AnalistaLocal getResponsavelPelaVerificacao() {
        return responsavelPelaVerificacao;
    }

    public void setResponsavelPelaVerificacao(AnalistaLocal responsavelPelaVerificacao) {
        this.responsavelPelaVerificacao = responsavelPelaVerificacao;
    }

    public void exibirInformacoesDaPrateleira() {
        String info = String.format(
                """
                🗄️ Informações da Prateleira Inteligente 🗄️
                🆔 ID da Prateleira: %d
                📶 Status: %s
                👨‍💼 Responsável pela Verificação: %s
                """,
                idPrateleira,
                status,
                responsavelPelaVerificacao
        );

        JOptionPane.showMessageDialog(null, info, "Detalhes da Prateleira", JOptionPane.INFORMATION_MESSAGE);
    }

}
