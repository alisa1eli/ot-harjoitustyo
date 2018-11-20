package tetris.domain;

import java.util.Date;

/**
 *
 * @author alisaelizarova
 */
public class OldGame implements Comparable<OldGame>{
    String id;
    int score;
    Date date;
    
    public OldGame(User user, int score) {
        this.id = user.getLogin();
        this.score = score;
        this.date = new Date();
    }
    @Override
    public String toString() {
        return("Date : "+this.date.toString() + ", Score : "+this.score);
    }

    public int compareTo(OldGame a) {
        if (a.score > this.score) {
            return 1;
        }
        return -1;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }
    
    
}
