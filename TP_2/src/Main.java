import java.util.*;
import java.time.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Sala> salas = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();
    static ReservaManager reservaManager = new ReservaManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Sistema de Gestão de Salas de Reunião =====");
            System.out.println("1. Cadastrar Sala");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Reservar Sala");
            System.out.println("4. Cancelar Reserva");
            System.out.println("5. Exibir Relatórios");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarSala();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    reservarSala();
                    break;
                case 4:
                    cancelarReserva();
                    break;
                case 5:
                    exibirRelatorios();
                    break;
                case 6:
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    static void cadastrarSala() {
        System.out.print("Nome da sala: ");
        String nome = scanner.nextLine();

        System.out.print("Capacidade: ");
        int capacidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Equipamentos (separados por vírgula): ");
        List<String> equipamentos = Arrays.asList(scanner.nextLine().split(","));

        System.out.println("Status da sala:");
        System.out.println("1 - Ativa");
        System.out.println("2 - Inativa");
        String status = scanner.nextLine();
        boolean ativa = status.equals("1");

        Sala sala = new Sala(nome, capacidade, equipamentos, ativa);
        salas.add(sala);
        System.out.println("[Sala cadastrada com sucesso!]");
    }

    static void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.println("Tipo de usuário:");
        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        System.out.println("3 - Técnico Administrativo");
        String tipo = scanner.nextLine();

        Usuario usuario;
        if (tipo.equals("1")) {
            usuario = new Aluno(nome, matricula);
        } else if (tipo.equals("2")) {
            usuario = new Professor(nome, matricula);
        } else {
            usuario = new Tecnico(nome, matricula);
        }

        usuarios.add(usuario);
        System.out.println("[Usuário cadastrado com sucesso!]");
    }

    static void reservarSala() {
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine();

        Usuario usuario = buscarUsuario(matricula);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Nome da sala: ");
        String nomeSala = scanner.nextLine();

        Sala sala = buscarSala(nomeSala);
        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }

        System.out.print("Data (dd/mm/aaaa): ");
        String[] dataStr = scanner.nextLine().split("/");
        LocalDate data = LocalDate.of(
            Integer.parseInt(dataStr[2]),
            Integer.parseInt(dataStr[1]),
            Integer.parseInt(dataStr[0])
        );

        System.out.print("Horário de início (HH:mm): ");
        LocalTime inicio = LocalTime.parse(scanner.nextLine());

        System.out.print("Horário de fim (HH:mm): ");
        LocalTime fim = LocalTime.parse(scanner.nextLine());

        reservaManager.adicionarReserva(usuario, sala, data, inicio, fim);
    }

    static void cancelarReserva() {
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine();

        System.out.print("ID da reserva: ");
        int id = Integer.parseInt(scanner.nextLine());

        reservaManager.cancelarReserva(matricula, id);
    }

    static void exibirRelatorios() {
        List<Reserva> reservas = reservaManager.getReservas();
        RelatorioManager.exibirRelatorioSalasMaisReservadas(reservas);
        RelatorioManager.exibirReservasPorUsuario(usuarios, reservas);
    }

    static Usuario buscarUsuario(String matricula) {
        for (Usuario u : usuarios) {
            if (u.getMatricula().equals(matricula)) {
                return u;
            }
        }
        return null;
    }

    static Sala buscarSala(String nome) {
        for (Sala s : salas) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                return s;
            }
        }
        return null;
    }
}
