
import java.util.ArrayList;
import java.util.Collections;
import tetris.domain.OldGame;
import tetris.domain.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alisaelizarova
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User user = new User("User","Password");
        OldGame a = new OldGame(user,10);
        OldGame b = new OldGame(user,12);
        OldGame c = new OldGame(user,16);
        OldGame d = new OldGame(user,8);
        OldGame e = new OldGame(user,12);
        ArrayList<OldGame> games = new ArrayList<>();
        games.add(a);
        games.add(b);
        games.add(c);
        games.add(d);
        games.add(e);
        Collections.sort(games);
        System.out.println(games);
        System.out.print(games.size());
    }
    
}
