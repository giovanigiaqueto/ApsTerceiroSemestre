package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Ver se faz uma telinha para o usuário configurar qual BD usar
public class CriarConexao {
    final static String dbuser = "postgres";
    final static String dbpassw = "kato123";
    final static String dburl = "jdbc:postgresql://localhost:5432/APST";
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(dburl, dbuser, dbpassw);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
