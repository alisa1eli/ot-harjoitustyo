package tetris.domain;

import java.util.Date;

/**
 *
 * @author alisaelizarova
 */
public class OldGame implements Comparable<OldGame>{
    int id;
    int score;
    String date;
    
    public OldGame(int score) {
        this.date = this.dateToString(new Date());
        this.id = -1;
        this.score = score;
    }
    public OldGame(int id, int score, String date ) {
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
    
    public String dateToString(Date object) {
        String date = object.getYear() + "."+object.getMonth()+
                "."+object.getDay()+"."+object.getHours()+"."
                + object.getMinutes() +"."+object.getSeconds();
        return date;
    }
    
    public String toString() {
        return this.id +" | "+this.score+" | "+ this.date;
    }
}
