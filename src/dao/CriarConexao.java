package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Ver se faz uma telinha para o usuário configurar qual BD usar
public class CriarConexao {
    
    static final String dbtype = "postgresql";
    static final String dbaddr = "localhost";
    static final String dbport = "5432";
    static final String dbuser = "postgres";
    static final String dbpass = "123456";
    static final String dbname = "APST";
    
    final static String dburl =
        "jdbc:" + dbtype + "://" + dbaddr + ':' + dbport + '/' + dbname;
    
    public static Connection getConexao() throws SQLException {
        try {
            return DriverManager.getConnection(dburl, dbuser, dbpass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
