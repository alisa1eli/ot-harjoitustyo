package tetris.domain;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.sort;

/**
 * This class represent an user.
 * @author alisaelizarova
 */
public class User {
    String id;
    String name;
    ArrayList<OldGame> oldGames;
    
    /**
     * This method creates a new user.
     * To do it, id and name is always needed.
     * 
     * @param id (String)
     * @param name (String)
     */
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
    
    /**
     * This method returns user's games sorted by scores.
     * 
     * @return list of games (ArrayList<OldGame>)
     */
    public ArrayList<OldGame> getOldGamesSortedByScore() {
        ArrayList<OldGame> oldGamesSortedByScore = this.oldGames;
        Collections.sort(oldGamesSortedByScore);
        return oldGamesSortedByScore;
    }

    public void setOldGames(ArrayList<OldGame> oldGames) {
        this.oldGames = oldGames;
    }
    
    /**
     * This method adds a new game to user's games.
     * 
     * @param a - game to be added
     */
    public void addAnOldGame(OldGame a) {
        this.oldGames.add(a);
    }
    /**
     * This method deletes an user game which id is gotten as a parameter.
     * 
     * @param gameId - game to be deleted
     */
    public void deleteAnOldGame(int gameId) {
        for (int a = 0; a < this.oldGames.size(); a++) {
            if (this.oldGames.get(a).id == gameId) {
                this.oldGames.remove(a);
            }
        }
    }
}
