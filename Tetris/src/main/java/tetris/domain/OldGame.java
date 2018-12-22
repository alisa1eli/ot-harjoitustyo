package tetris.domain;

import java.util.Calendar;
import java.util.Date;

/**
 * This class represents a old, complete game.
 * 
 * @author alisaelizarova
 */
public class OldGame implements Comparable<OldGame> {
    int id;
    int score;
    String date;
    
    /**
     * This method is used to saved just complete game. 
     * The game isn't saved to db yet.
     * 
     * @param score - score of the game (int)
     */
    public OldGame(int score) {
        this.date = this.dateToString(Calendar.getInstance());
        this.id = -1;
        this.score = score;
    }
    /**
     * This method is for a game that is saved in db.
     * 
     * @param id - id of the game (int)
     * @param score - score of the game (int)
     * @param date - date the game played (String)
     */
    public OldGame(int id, int score, String date) {
        this.date = date;
        this.id = id;
        this.score = score;
    }    

    @Override
    public int compareTo(OldGame a) {
        if (a.score > this.score) {
            return 1;
        }
        return -1;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * This method returns date (Calendar) in String form.
     * 
     * @param object - date (Calendar)
     * @return date - date (String)
     */
    public String dateToString(Calendar object) {
        String date = object.get(Calendar.HOUR_OF_DAY) + ":" + object.get(Calendar.MINUTE) +
                " " + object.get(Calendar.DAY_OF_MONTH) + "." + object.get(Calendar.MONTH) +
                "." + object.get(Calendar.YEAR);
        return date;
    }
    
    @Override
    public String toString() {
        return  this.score + " | " + this.date;
    }
}
