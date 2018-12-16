/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

/**
 *
 * @author alisaelizarova
 */
public class Level {
    private int points;
    public Level() {
        this.points = 0;
    }
    
    public void nextLevel() {
        this.points++;
    }
    public int getPoints() {
        return this.points;
    }
    public String[] getColors() {
        String[] colors = {"#ffffff", "#c57e00", "#2f8bba", "#6e4242", "#2f8bba", "#6e4242", 
            "#585858", "#585858", "#585858", "#585858"};
        return colors;
    }
}
