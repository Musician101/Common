package io.musician101.musicianlibrary.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySQLHandler {

    private final String database;
    private final String hostname;
    private final String password;
    private final String port;
    private final String user;
    private Connection connection;

    public MySQLHandler(String database, String hostname, String password, String port, String user) {
        this.connection = null;
        this.database = database;
        this.hostname = hostname;
        this.password = password;
        this.port = port;
        this.user = user;
    }

    private boolean checkConnection() {
        return connection != null;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void executeBatch(List<String> queries) throws ClassNotFoundException, SQLException {
        Connection c;
        if (checkConnection()) {
            c = getConnection();
        }
        else {
            c = openConnection();
        }

        Statement s = c.createStatement();
        for (String query : queries) {
            s.addBatch(query);
        }
        s.executeBatch();
        s.close();
        closeConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Drive");
        connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, user, password);
        return connection;
    }

    public ResultSet querySQL(String query) throws ClassNotFoundException, SQLException {
        Connection c;
        if (checkConnection()) {
            c = getConnection();
        }
        else {
            c = openConnection();
        }

        Statement s = c.createStatement();
        ResultSet rset = s.executeQuery(query);
        s.close();
        closeConnection();
        return rset;
    }

    public void updateSQL(String update) throws ClassNotFoundException, SQLException {
        Connection c;
        if (checkConnection()) {
            c = getConnection();
        }
        else {
            c = openConnection();
        }

        Statement s = c.createStatement();
        s.executeUpdate(update);
        s.close();
        closeConnection();
    }
}
