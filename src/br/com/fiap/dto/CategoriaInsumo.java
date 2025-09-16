package br.com.fiap.dto;

import javax.swing.*;

public class CategoriaInsumo {
    private int idCategoria;
    private String tipoCategoria;

    // construtores

    public CategoriaInsumo() {
    }

    public CategoriaInsumo(int idCategoria, String tipoCategoria) {
        this.idCategoria = idCategoria;
        this.tipoCategoria = tipoCategoria;
    }

    // getters/setters

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public void exibirInformacoesDoEstoque() {
        String info = String.format(
                """
                ==== Informações da Categoria ====
                
                ID da Categoria: %d
                Tipo de Categoria: %s
                """,
                idCategoria,
                tipoCategoria
        );

        JOptionPane.showMessageDialog(null, info, "Detalhes da Unidade", JOptionPane.INFORMATION_MESSAGE);
    }
}
