/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.Block;
import tetris.domain.Game;
import tetris.domain.Move;

/**
 *
 * @author alisaelizarova
 */
public class MoveTest {
    Move move;
    Block block;
    int[][] field;
    
    @Before
    public void setUp() {
        this.move = new Move();
        this.field = this.move.setAllTheSameValue(new int[11][26], 0);
        block = new Block();
    }
    @Test 
    public void methodSetAllTheSameValueWorksProperly() {
        int notZeros = 0;
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 26; y++) {
                if(this.field[x][y] != 0){
                    notZeros++;
                }
            }
        }
        assertEquals("0", notZeros+"");
    }
    @Test
    public void typeOneCanBeMovedRight() {
        int[][] movingPart = this.block.getBlocks(1);
        this.setBlocksOnTheField(movingPart, 1);
        int[][] mP = this.move.moveRight(field, movingPart);
        assertEquals(" 6 4, -1 -1, -1 -1, -1 -1,", matrixToString(mP));
    }
    @Test
    public void typeSixCanBeMovedRight() {
        int[][] movingPart = this.block.getBlocks(6);
        this.setBlocksOnTheField(movingPart, 6);
        int[][] mP = this.move.moveRight(field, movingPart);
        assertEquals(" 6 4, 7 4, -1 -1, 6 3,", matrixToString(mP));
    }
    @Test
    public void typeFourCanBeMovedRight() {
        int[][] movingPart = this.block.getBlocks(4);
        this.setBlocksOnTheField(movingPart, 4);
        int[][] mP = this.move.moveRight(field, movingPart);
        assertEquals(" 6 4, 6 3, -1 -1, -1 -1,", matrixToString(mP));
    }
    @Test
    public void typeOneCanBeMovedLeft() {
        int[][] movingPart = this.block.getBlocks(1);
        this.setBlocksOnTheField(movingPart, 1);
        int[][] mP = this.move.moveLeft(field, movingPart);
        assertEquals(" 4 4, -1 -1, -1 -1, -1 -1,", matrixToString(mP));
    }
    @Test
    public void typeSevenCanBeMovedLeft() {
        int[][] movingPart = this.block.getBlocks(7);
        this.setBlocksOnTheField(movingPart, 7);
        int[][] mP = this.move.moveLeft(field, movingPart);
        assertEquals(" 3 4, 4 4, -1 -1, 4 3,", matrixToString(mP));
    }
    @Test
    public void typeFiveCanBeMovedLeft() {
        int[][] movingPart = this.block.getBlocks(5);
        this.setBlocksOnTheField(movingPart, 5);
        int[][] mP = this.move.moveLeft(field, movingPart);
        assertEquals(" 4 4, 4 3, 4 2, -1 -1,", matrixToString(mP));
    }
    private String matrixToString(int a[][]) {
        String r = "";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                r = r + " " + a[i][j];
            }
            r = r + ",";
        }
        return r;
    }
    private void setBlocksOnTheField(int[][] movingPart, int type) {
        for (int x = 0; x < 4; x++) {
            if (movingPart[x][0] == -1 ){
                break;
            }
            this.field[movingPart[x][0]][movingPart[x][1]] = type;
        }
    }
    

//    @Test
//    public void 
}
