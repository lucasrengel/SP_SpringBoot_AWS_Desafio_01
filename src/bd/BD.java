package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
    public static Connection getConexao() {

        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            //modifique como for necessario
            String server = "localhost";
            String database = "desafio-01";
            String url = "jdbc:mysql://" + server + ":3306/" + database;

            String user = "root";
            String password = "12345";

            connection = DriverManager.getConnection(url, user, password);

            if (connection == null) {
                System.out.println("Status: NAO CONECTADO!");
            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver nao encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }

    }
}
