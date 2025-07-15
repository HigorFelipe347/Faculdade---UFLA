package br.com.ufla.sgsr.model;

public class Tecnico extends Usuario {
    public Tecnico(String nomeCompleto, String matricula) {
        super(nomeCompleto, matricula, TipoUsuario.TECNICO);
    }
}
