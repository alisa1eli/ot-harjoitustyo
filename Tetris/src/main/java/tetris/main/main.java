package tetris.main;


import java.sql.SQLException;
import javafx.application.Application;
import tetris.ui.TetrisUi;

/**
 *
 * @author alisaelizarova
 */
public class main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Application.launch(TetrisUi.class);
    }  
}
