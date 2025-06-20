import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RelatorioManager {
    public static void exibirRelatorioSalasMaisReservadas(List<Reserva> reservas) {
        Map<String, Integer> contagem = new HashMap<>();
        for (Reserva r : reservas) {
            String nome = r.getSala().getNome();
            contagem.put(nome, contagem.getOrDefault(nome, 0) + 1);
        }
        System.out.println("--- Salas mais reservadas ---");
        for (Map.Entry<String, Integer> entrada : contagem.entrySet()) {
            System.out.println("Sala " + entrada.getKey() + ": " + entrada.getValue() + " reservas");
        }
    }

    public static void exibirReservasPorUsuario(List<Usuario> usuarios, List<Reserva> reservas) {
        System.out.println("--- Reservas futuras por usuário ---");
        for (Usuario u : usuarios) {
            System.out.println("Usuário: " + u.getNome());
            for (Reserva r : reservas) {
                if (r.getUsuario().getMatricula().equals(u.getMatricula()) && r.getData().isAfter(LocalDate.now())) {
                    r.mostrarInformacoes();
                }
            }
        }
    }
}