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
        String[] colorsType1 = {"#C57E00","#C57E00", "#C57E00", "#B87909", "#B87909", "#B87909", 
            "#AC7513", "#AC7513", "#AC7513", "#A0711D", "#A0711D", "#A0711D",
            "#946D27", "#946D27", "#946D27", "#886830", "#886830", "#886830", 
            "#7C643A", "#7C643A", "#7C643A", "#706044", "#706044", "#706044",
            "#645C4E", "#645C4E", "#645C4E"};
        String[] colorsType24 = {"#2F8BBA", "#2F8BBA", "#2F8BBA", "#3385AF", "#3385AF", "#3385AF",
            "#387FA4", "#387FA4", "#387FA4", "#3C7A99", "#3C7A99", "#3C7A99",
            "#41748E", "#41748E", "#41748E", "#456E83", "#456E83", "#456E83", 
            "#4A6978", "#4A6978", "#4A6978", "#4E636D", "#4E636D", "#4E636D", 
            "#535D62", "#535D62", "#535D62"};
        String[] colorsType35 = {"#6E4242", "#6E4242", "#6E4242", "#6B4444", "#6B4444", "#6B4444",
            "#694646", "#694646", "#694646", "#664949", "#664949", "#664949",
            "#644B4B", "#644B4B", "#644B4B", "#614E4E", "#614E4E", "#614E4E",
            "#5F5050", "#5F5050", "#5F5050", "#5C5353", "#5C5353", "#5C5353", 
            "#5A5555", "#5A5555", "#5A5555"};
        if (this.points + 1 > 26) {
            String[] colors = {"#ffffff", "#585858", "#585858", "#585858", "#585858", "#585858", 
                "#585858", "#585858", "#585858", "#585858"};
            return colors;
        }
        String[] colors = new String[10];
        colors[0] = "#ffffff";
        colors[1] = colorsType1[this.points];
        colors[2] = colorsType24[this.points];
        colors[4] = colorsType24[this.points];
        colors[3] = colorsType35[this.points];
        colors[5] = colorsType35[this.points];
        for(int a = 6; a < 10; a++) {
                colors[a] = "#585858";
        }
        return colors;
    }
}
