package tetris.domain;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author alisaelizarova
 */
public class OldGame implements Comparable<OldGame> {
    int id;
    int score;
    String date;
    
    public OldGame(int score) {
        this.date = this.dateToString(Calendar.getInstance());
        this.id = -1;
        this.score = score;
    }
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
    
    public String dateToString(Calendar object) {
        String date = object.get(Calendar.HOUR_OF_DAY)+":"+object.get(Calendar.MINUTE)+
                " " + object.get(Calendar.DAY_OF_MONTH) +"."+object.get(Calendar.MONTH)+
                "." + object.get(Calendar.YEAR);
        return date;
    }
    
    public String toString() {
        String[] d = this.date.split(".");
        return  this.score + " | " + this.date;
    }
}
