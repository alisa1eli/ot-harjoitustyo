package tetris.dao;

/**
 * This class sets the connection with the database.
 * 
 * @author alisaelizarova
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final String databaseAddress;
    /**
     * This method sets address to the database.
     * @param databaseAddress ( String )
     * @throws ClassNotFoundException if address is not correct
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    /**
     * This method return connection.
     * @return connection to db (Connection)
     * @throws  SQLException if db not found
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    /**
     * This method creates two tables (Game and User), if needed.
     */
    public void init() {

        List<String> commands = sqliteCommands();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String command : commands) {
                st.executeUpdate(command);
            }

        } catch (Throwable t) {
            // database already exists
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteCommands() {
        ArrayList<String> lista = new ArrayList<>();

        // to create a database
        lista.add("CREATE TABLE IF NOT EXISTS User ( "
                + "id varchar(255) PRIMARY KEY, "
                + "name varchar(255) ); ");
        lista.add("CREATE TABLE IF NOT EXISTS Game(id integer PRIMARY KEY, userId varchar(255), "
                + "date varchar(30), score integer, "
                + "FOREIGN KEY(userId) REFERENCES User(id));");
        
        return lista;
    }
}