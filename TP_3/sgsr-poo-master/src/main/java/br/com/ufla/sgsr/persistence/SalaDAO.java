package br.com.ufla.sgsr.persistence;

import br.com.ufla.sgsr.database.ConexaoMySQL;
import br.com.ufla.sgsr.model.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaDAO {
    public void salvar(Sala sala) throws SQLException {
        String sql = "INSERT INTO Sala (nome, capacidade, equipamentos, ativa) VALUES (?, ?, ?, ?)";
        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, sala.getNome());
            pstmt.setInt(2, sala.getCapacidade());
            pstmt.setString(3, sala.getEquipamentosComoString());
            pstmt.setBoolean(4, sala.isAtiva());

            pstmt.executeUpdate();
        }
    }

    public Sala buscarPorNome(String nomeSala) throws SQLException {
    String sql = "SELECT * FROM sala WHERE nome = ?";

    try (Connection conexao = ConexaoMySQL.getConexao();
         PreparedStatement pstmt = conexao.prepareStatement(sql)) {

        pstmt.setString(1, nomeSala);

        try (ResultSet rs = pstmt.executeQuery()) {   // <-- precisa do import
            if (rs.next()) {
                Sala sala = new Sala();               // <-- precisa existir construtor vazio
                sala.setId(rs.getInt("id"));
                sala.setNome(rs.getString("nome"));
                sala.setCapacidade(rs.getInt("capacidade"));
                sala.setEquipamentosDeString(rs.getString("equipamentos"));
                sala.setAtiva(rs.getBoolean("ativa"));
                return sala;
            }
        }
    }
    return null;
    }
}