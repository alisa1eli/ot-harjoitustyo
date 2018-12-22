package tetris.main;


import java.sql.SQLException;
import javafx.application.Application;
import tetris.ui.TetrisUi;

/**
 * This is a main class of the Tetris.
 * @author alisaelizarova
 */
public class main {
    /**
     * This method calls TetrisUI.
     * @throws ClassNotFoundException if class is not found
     * @throws SQLException if db is not found
     * @param args 
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Application.launch(TetrisUi.class);
        
    }  
}
