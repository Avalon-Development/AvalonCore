package net.avalondevs.avaloncore.MySQL;

import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Generic class that all database implementations must follow
 */
public abstract class Database {

    @Setter
    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    /**
     * Method that dynamically prepares a {@link java.sql.PreparedStatement}
     *
     * @param query query
     * @param args  arguments to pass in
     * @return the processed statement
     * @throws SQLException when there is more / fewer arguments than specified in the query
     */
    public PreparedStatement prepareStatement(String query, Object... args) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query);

        int index = 1;

        for (Object object : args) {
            statement.setObject(index++, object);
        }

        return statement;

    }

    public PreparedStatement prepareStatement(PreparedStatement statement, Object... args) throws SQLException {

        int index = 1;

        for (Object object : args) {
            statement.setObject(index++, object);
        }

        return statement;

    }


    public void executeStatement(PreparedStatement statement) throws SQLException {

        statement.execute();

    }

    public ResultSet executeQuery(PreparedStatement statement) throws SQLException {

        return statement.executeQuery();

    }

    public void executeStatement(String query, Object... args) throws SQLException {

        prepareStatement(query, args).execute();

    }

    public ResultSet executeQuery(String query, Object... args) throws SQLException {

        return prepareStatement(query, args).executeQuery();

    }

    public abstract void connect() throws SQLException;

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
