package br.com.ufla.sgsr.model;

public abstract class Usuario {
    private int id;
    private String nomeCompleto;
    private String matricula;
    private TipoUsuario tipo;

    public Usuario(String nomeCompleto, String matricula, TipoUsuario tipo) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.tipo = tipo;
    }

    // Getters
    public int getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getMatricula() { return matricula; }
    public TipoUsuario getTipo() { return tipo; }

    public void setId(int id) { this.id = id; }
    public void setNomeCompleto(String nome) { this.nomeCompleto = nome; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}