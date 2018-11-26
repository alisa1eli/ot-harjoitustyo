
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
//        User user = new User("User","Password");
//        OldGame a = new OldGame(user,10);
//        OldGame b = new OldGame(user,12);
//        OldGame c = new OldGame(user,16);
//        OldGame d = new OldGame(user,8);
//        OldGame e = new OldGame(user,12);
//        ArrayList<OldGame> games = new ArrayList<>();
//        games.add(a);
//        games.add(b);
//        games.add(c);
//        games.add(d);
//        games.add(e);
//        Collections.sort(games);
//        System.out.println(games);
//        System.out.print(games.size());
//        Database database = new Database("jdbc:sqlite:notes.db");
//        database.init();
//        
//        GameDao dao = new GameDao(database);
//        dao.save(new OldGame(10), "userId");
//        dao.save(new OldGame(11), "userId");
//        dao.save(new OldGame(14), "userId1");
//        System.out.println(dao.findAllGamesOfOneUser("userId"));
        
//        String a = "2010.11.30.12.55";
//        System.out.println(a);
//        String[] d = a.split(Pattern.quote("."));
//        int min = Integer.parseInt(d[4]);
//        int hours = Integer.parseInt(d[3]);
//        int day = Integer.parseInt(d[2]);
//        int month = Integer.parseInt(d[1]);
//        int year = Integer.parseInt(d[0]);
//        Database database = new Database("jdbc:sqlite:notes.db");
//        database.init();
//        UserDao dao = new UserDao(database);
//        dao.save(new User("Login2", "BlaName2") );
        
        
        Application.launch(TetrisUi.class);
    }
    
}
