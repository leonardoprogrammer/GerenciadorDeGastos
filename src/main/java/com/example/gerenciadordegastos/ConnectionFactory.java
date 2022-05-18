package com.example.gerenciadordegastos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String DB_USERNAME = "ggadmin";
    private static final String DB_PASSWORD = "nimdagg";

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3050/C:/GerenciadorDeGastos/data/db/dbgg";

    public static Connection createConnectionToMySql() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        return connection;
    }
}
