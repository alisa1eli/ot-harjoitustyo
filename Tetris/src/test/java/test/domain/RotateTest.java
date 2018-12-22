package test.domain;

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
import tetris.domain.Matrix;
import tetris.domain.Rotate;

/**
 *
 * @author alisaelizarova
 */
public class RotateTest {
    Rotate rotate;
    Block block;
    int[][] field;
    Matrix matrix;
    
    @Before
    public void setUp() {
        this.rotate = new Rotate();
        this.block = new Block();
        matrix = new Matrix();
        this.field = this.matrix.setAllTheSameValue(new int[11][26], 0);
    }
    
    @Test 
    public void typeTwoCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(2);
        this.setBlocksOnTheField(movingPart, 2);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", this.matrix.matrixToString(this.rotate.getMovingPart()));
    }
    @Test 
    public void typeThreeCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(3);
        this.setBlocksOnTheField(movingPart, 3);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 6 5, 6 4, 6 3, -1 -1,", this.matrix.matrixToString(this.rotate.getMovingPart()));
    }
    @Test 
    public void typeFourCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(4);
        this.setBlocksOnTheField(movingPart, 4);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 5 4, 6 4, -1 -1, -1 -1,", this.matrix.matrixToString(this.rotate.getMovingPart()));
    }
    @Test 
    public void typeFiveCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(5);
        this.setBlocksOnTheField(movingPart, 5);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 4 3, 5 3, 6 3, -1 -1,3", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[6][3]);
    }
    @Test 
    public void typeSixCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(6);
        this.setBlocksOnTheField(movingPart, 6);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 5 4, 6 4, -1 -1, 5 5,8", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[5][4]);
    }
    @Test 
    public void typeSevenCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(7);
        this.setBlocksOnTheField(movingPart, 7);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 5 4, 6 4, -1 -1, 5 3,6", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[5][4]);
    }
    @Test
    public void typeEightCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(8);
        this.setBlocksOnTheField(movingPart, 8);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 4 3, 5 3, -1 -1, 5 4,9", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[5][4]);
    }
    @Test
    public void typeNineCanBeRotated() {
        int[][] movingPart = this.block.getBlocks(9);
        this.setBlocksOnTheField(movingPart, 9);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 4 3, 5 3, -1 -1, 5 2,7", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[4][3]);
    }
    @Test
    public void typeFourWillBeNotRotatedIfItIsNextToTheEdge() {
        int[][] movingPart = {{10, 4},{10, 3},{-1, -1},{-1, -1}};
        this.setBlocksOnTheField(movingPart, 4);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 10 4, 10 3, -1 -1, -1 -1,4", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[10][3]);
    }
    @Test
    public void typeEightWillBeNotRotatedIfItIsNextToTheEdge() {
        int[][] movingPart = {{0, 3},{1, 3},{-1, -1},{0, 4}};
        this.setBlocksOnTheField(movingPart, 8);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 0 3, 1 3, -1 -1, 0 4,8", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[0][3]);
    }
    @Test
    public void typeThreeWillBeNotRotatedIfItIsOnTheBottom() {
        int[][] movingPart = {{5, 25},{6, 25},{7, 25},{-1, -1}};
        this.setBlocksOnTheField(movingPart, 3);
        this.rotate.rotate(field, movingPart);
        assertEquals(" 5 25, 6 25, 7 25, -1 -1,3", this.matrix.matrixToString(this.rotate.getMovingPart())+this.rotate.getField()[5][25]);
    }
    private void setBlocksOnTheField(int[][] movingPart, int type) {
        for (int x = 0; x < 4; x++) {
            if (movingPart[x][0] == -1 ){
                break;
            }
            this.field[movingPart[x][0]][movingPart[x][1]] = type;
        }
    }
}
