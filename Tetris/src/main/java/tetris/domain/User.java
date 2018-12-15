package tetris.domain;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.sort;

/**
 *
 * @author alisaelizarova
 */
public class User {
    String id;
    String name;
    ArrayList<OldGame> oldGames;
    
    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.oldGames = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public void addAnOldGame(OldGame a) {
        this.oldGames.add(a);
    }
    
    public void deleteAnOldGame(int gameId) {
        for (int a = 0; a < this.oldGames.size(); a++) {
            if (this.oldGames.get(a).id == gameId) {
                this.oldGames.remove(a);
            }
        }
    }
}
