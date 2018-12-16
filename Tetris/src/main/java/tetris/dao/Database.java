package tetris.dao;

/**
 *
 * @author alisaelizarova
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {

        List<String> commands = sqliteCommands();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String command : commands) {
                System.out.println("Running command >> " + command);
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