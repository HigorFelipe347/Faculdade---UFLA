import java.util.List;

public class Sala {
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

    public String getNome() {
        return nome;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void mostrarInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Capacidade: " + capacidade);
        System.out.println("Equipamentos: " + String.join(", ", equipamentos));
        System.out.println("Status: " + (ativa ? "Ativa" : "Inativa"));
    }
}