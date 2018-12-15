package tetris.main;


import com.google.common.primitives.Chars;
import com.google.common.primitives.Ints;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;
import javafx.application.Application;
import tetris.dao.Database;
import tetris.dao.GameDao;
import tetris.dao.UserDao;
import tetris.domain.OldGame;
import tetris.domain.User;
import tetris.ui.TetrisUi;

/**
 *
 * @author alisaelizarova
 */
public class main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Application.launch(TetrisUi.class);
        
//        String text = "Game over! Your score is 10 .";
//        System.out.println(Integer.parseInt(text.split(" ")[5]));
    }  
}
