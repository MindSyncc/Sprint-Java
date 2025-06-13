package br.com.fiap.bean;

import java.time.LocalDate;

public class QRCode {
    private int idQRCode;
    private LocalDate dataCriacao;
    private LocalDate dataUltimaLeitura;
    private boolean statusQRCode;

    // construtores

    public QRCode() {
    }

    public QRCode(int idQRCode, LocalDate dataCriacao, LocalDate dataUltimaLeitura, boolean statusQRCode) {
        this.idQRCode = idQRCode;
        this.dataCriacao = dataCriacao;
        this.dataUltimaLeitura = dataUltimaLeitura;
        this.statusQRCode = statusQRCode;
    }

    // getters/setters

    public int getIdQRCode() {
        return idQRCode;
    }

    public void setIdQRCode(int idQRCode) {
        this.idQRCode = idQRCode;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataUltimaLeitura() {
        return dataUltimaLeitura;
    }

    public void setDataUltimaLeitura(LocalDate dataUltimaLeitura) {
        this.dataUltimaLeitura = dataUltimaLeitura;
    }

    public boolean isStatusQRCode() {
        return statusQRCode;
    }

    public void setStatusQRCode(boolean statusQRCode) {
        this.statusQRCode = statusQRCode;
    }
}
