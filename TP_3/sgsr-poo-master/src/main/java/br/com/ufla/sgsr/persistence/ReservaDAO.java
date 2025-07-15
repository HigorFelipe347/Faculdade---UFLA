/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ufla.sgsr.persistence;

import br.com.ufla.sgsr.database.ConexaoMySQL;
import br.com.ufla.sgsr.model.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author raiva
 */
public class ReservaDAO {
    public void salvar(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reservas (id_usuario, id_sala, data_reserva, hora_inicio, hora_fim, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2, reserva.getIdSala());
            stmt.setString(3, reserva.getDataReserva());   // formato yyyy-MM-dd
            stmt.setString(4, reserva.getHoraInicio());    // formato HH:mm
            stmt.setString(5, reserva.getHoraFim());       // formato HH:mm
            stmt.setString(6, reserva.getStatus());        // Confirmada ou Cancelada
            stmt.executeUpdate();
        }
    }

    // VERIFICAR CONFLITO DE HORÁRIO NA MESMA SALA E DATA
    public boolean verificarConflito(int idSala, String data, String horaInicio, String horaFim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservas " +
                     "WHERE id_sala = ? AND data_reserva = ? AND status = 'Confirmada' " +
                     "AND ( (hora_inicio < ? AND hora_fim > ?) OR (hora_inicio < ? AND hora_fim > ?) OR " +
                     "(hora_inicio >= ? AND hora_fim <= ?) )";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idSala);
            stmt.setString(2, data);

            // Verifica sobreposição entre os horários
            stmt.setString(3, horaFim);    // hora_inicio < horaFim
            stmt.setString(4, horaInicio); // hora_fim > horaInicio
            stmt.setString(5, horaFim);
            stmt.setString(6, horaInicio);
            stmt.setString(7, horaInicio);
            stmt.setString(8, horaFim);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            return false;
        }
    }
}
