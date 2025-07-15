package br.com.ufla.sgsr.model;

public enum TipoUsuario {
    ALUNO("Aluno"),
    PROFESSOR("Professor"),
    TECNICO("Técnico");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}