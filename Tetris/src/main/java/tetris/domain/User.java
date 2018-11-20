package tetris.domain;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.sort;

/**
 *
 * @author alisaelizarova
 */
public class User {
    String login;
    String password;
    ArrayList<OldGame> oldGames;
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.oldGames = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<OldGame> getOldGames() {
        return oldGames;
    }
    
    public ArrayList<OldGame> getOldGamesSortedByScore() {
        ArrayList<OldGame> oldGamesSortedByScore = this.oldGames;
        Collections.sort(oldGamesSortedByScore);
        return oldGamesSortedByScore;
    }

    public void setOldGames(ArrayList<OldGame> oldGames) {
        this.oldGames = oldGames;
    }
    
    public void addAnOldGame(int score) {
        OldGame a = new OldGame(this, score);
        this.oldGames.add(a);
    }
    
    public void deleteAnOldGame(String gameId) {
        for (int a = 0; a<this.oldGames.size(); a++) {
            if(this.oldGames.get(a).id == gameId) {
                this.oldGames.remove(a);
            }
        }
    }
}
