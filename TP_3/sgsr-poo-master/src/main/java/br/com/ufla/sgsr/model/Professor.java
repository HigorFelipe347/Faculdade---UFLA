package br.com.ufla.sgsr.model;

public class Professor extends Usuario {
    public Professor(String nomeCompleto, String matricula) {
        super(nomeCompleto, matricula, TipoUsuario.PROFESSOR);
    }
}