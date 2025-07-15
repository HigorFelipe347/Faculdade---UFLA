package br.com.ufla.sgsr.persistence;

import br.com.ufla.sgsr.database.ConexaoMySQL;
import br.com.ufla.sgsr.model.Aluno;
import br.com.ufla.sgsr.model.Professor;
import br.com.ufla.sgsr.model.Tecnico;
import br.com.ufla.sgsr.model.TipoUsuario;
import br.com.ufla.sgsr.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioDAO {

    
    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nomeCompleto, matricula, Tipo) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNomeCompleto());
            pstmt.setString(2, usuario.getMatricula());
            pstmt.setString(3, usuario.getTipo().getDescricao());

            pstmt.executeUpdate();
        }
    }

    
    public Optional<Usuario> buscarPorMatricula(String matricula) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE matricula = ?";
        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, matricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nomeCompleto = rs.getString("nomeCompleto");
                    String tipoStr = rs.getString("Tipo");
                    
                    Usuario usuario = null;
                    // Converte a String do banco para o nosso Enum
                    TipoUsuario tipo = TipoUsuario.valueOf(tipoStr.toUpperCase().replace(" ", "_"));

                    switch(tipo) {
                        case ALUNO:
                            usuario = new Aluno(nomeCompleto, matricula);
                            break;
                        case PROFESSOR:
                            usuario = new Professor(nomeCompleto, matricula);
                            break;
                        case TECNICO:
                            usuario = new Tecnico(nomeCompleto, matricula);
                            break;
                    }
                    
                    if(usuario != null){
                        usuario.setId(id); // Define o ID do usuário encontrado
                    }
                    
                    return Optional.ofNullable(usuario);
                }
            }
        }
        return Optional.empty();
    }
}