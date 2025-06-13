package br.com.fiap.bean;

public class CategoriaInsumo {
    private int idCategoria;
    private String tipoCategoria;
    Almoxarife responsavelPeloRegistro;

    // construtores

    public CategoriaInsumo() {
    }

    public CategoriaInsumo(int idCategoria, String tipoCategoria, Almoxarife responsavelPeloRegistro) {
        this.idCategoria = idCategoria;
        this.tipoCategoria = tipoCategoria;
        this.responsavelPeloRegistro = responsavelPeloRegistro;
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

    public Almoxarife getResponsavelPeloRegistro() {
        return responsavelPeloRegistro;
    }

    public void setResponsavelPeloRegistro(Almoxarife responsavelPeloRegistro) {
        this.responsavelPeloRegistro = responsavelPeloRegistro;
    }
}
