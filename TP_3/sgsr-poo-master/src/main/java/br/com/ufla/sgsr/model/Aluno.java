package br.com.ufla.sgsr.model;

public class Aluno extends Usuario {
    public Aluno(String nomeCompleto, String matricula) {
        super(nomeCompleto, matricula, TipoUsuario.ALUNO);
    }
}