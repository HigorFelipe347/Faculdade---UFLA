import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaManager {
    private List<Reserva> reservas;

    public ReservaManager() {
        this.reservas = new ArrayList<>();
    }

    public boolean adicionarReserva(Usuario usuario, Sala sala, LocalDate data, LocalTime inicio, LocalTime fim) {
        if (!sala.isAtiva()) {
            System.out.println("Sala inativa. Reserva não permitida.");
            return false;
        }

        int qtdReservasFuturas = 0;
        for (Reserva r : reservas) {
            if (r.getUsuario().getMatricula().equals(usuario.getMatricula()) && r.getData().isAfter(LocalDate.now())) {
                qtdReservasFuturas++;
            }
        }

        if (qtdReservasFuturas >= 2) {
            System.out.println("Limite de 2 reservas futuras atingido.");
            return false;
        }

        for (Reserva r : reservas) {
            if (r.getSala().getNome().equalsIgnoreCase(sala.getNome()) &&
                r.getData().equals(data) &&
                !(fim.isBefore(r.getInicio()) || inicio.isAfter(r.getFim()))) {
                System.out.println("Conflito de horário com outra reserva.");
                return false;
            }
        }

        reservas.add(new Reserva(usuario, sala, data, inicio, fim));
        System.out.println("[Reserva realizada com sucesso!]");
        return true;
    }

    public boolean cancelarReserva(String matricula, int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id && r.getUsuario().getMatricula().equals(matricula) && r.getData().isAfter(LocalDate.now())) {
                reservas.remove(r);
                System.out.println("[Reserva cancelada com sucesso!]");
                return true;
            }
        }
        System.out.println("Reserva não encontrada ou não pode ser cancelada.");
        return false;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}