package br.com.ufla.sgsr.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/sgsr";
    private static final String USUARIO = "root";
    private static final String SENHA = "root"; 

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados: ", e);
        }
    }
}
