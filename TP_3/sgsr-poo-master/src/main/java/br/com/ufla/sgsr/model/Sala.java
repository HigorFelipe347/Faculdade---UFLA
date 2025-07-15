package br.com.ufla.sgsr.model;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Sala {
    private int id;
    private String nome;
    private int capacidade;
    private List<String> equipamentos;
    private boolean ativa;

    public Sala(String nome, int capacidade, List<String> equipamentos, boolean ativa) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.equipamentos = equipamentos;
        this.ativa = ativa;
    }
    
    public Sala() { }

    public void setEquipamentosDeString(String string) {
    if (string == null || string.trim().isEmpty()) {
        this.equipamentos = Collections.emptyList();
    } else {
        this.equipamentos = Arrays.stream(string.split(","))
                                  .map(String::trim)
                                  .collect(Collectors.toList());
    }
}

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    public List<String> getEquipamentos() { return equipamentos; }
    public void setEquipamentos(List<String> equipamentos) { this.equipamentos = equipamentos; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    public String getEquipamentosComoString() {
        if (equipamentos == null || equipamentos.isEmpty()) {
            return "";
        }
        return String.join(", ", equipamentos);
    }

}