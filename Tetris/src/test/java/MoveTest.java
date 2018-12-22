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
import tetris.domain.Matrix;
import tetris.domain.Move;

/**
 *
 * @author alisaelizarova
 */
public class MoveTest {
    Move move;
    Block block;
    int[][] field;
    Matrix matrix;
    
    @Before
    public void setUp() {
        this.move = new Move();
        this.matrix = new Matrix();
        this.field = this.matrix.setAllTheSameValue(new int[11][26], 0);
        block = new Block();
    }
//    @Test 
//    public void methodSetAllTheSameValueWorksProperly() {
//        int notZeros = 0;
//        for (int x = 0; x < 11; x++) {
//            for (int y = 0; y < 26; y++) {
//                if(this.field[x][y] != 0){
//                    notZeros++;
//                }
//            }
//        }
//        assertEquals("0", notZeros+"");
//    }
    @Test
    public void typeOneCanBeMovedRight() {
        int[][] movingPart = this.block.getBlocks(1);
        this.setBlocksOnTheField(movingPart, 1);
        int[][] mP = this.move.moveRight(field, movingPart);
        assertEquals(" 6 4, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeSixCanBeMovedRight() {
        int[][] movingPart = this.block.getBlocks(6);
        this.setBlocksOnTheField(movingPart, 6);
        int[][] mP = this.move.moveRight(field, movingPart);
        assertEquals(" 6 4, 7 4, -1 -1, 6 3,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeFourCanBeMovedRight() {
        int[][] movingPart = this.block.getBlocks(4);
        this.setBlocksOnTheField(movingPart, 4);
        int[][] mP = this.move.moveRight(field, movingPart);
        assertEquals(" 6 4, 6 3, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeOneCanBeMovedLeft() {
        int[][] movingPart = this.block.getBlocks(1);
        this.setBlocksOnTheField(movingPart, 1);
        int[][] mP = this.move.moveLeft(field, movingPart);
        assertEquals(" 4 4, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeSevenCanBeMovedLeft() {
        int[][] movingPart = this.block.getBlocks(7);
        this.setBlocksOnTheField(movingPart, 7);
        int[][] mP = this.move.moveLeft(field, movingPart);
        assertEquals(" 3 4, 4 4, -1 -1, 4 3,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeFiveCanBeMovedLeft() {
        int[][] movingPart = this.block.getBlocks(5);
        this.setBlocksOnTheField(movingPart, 5);
        int[][] mP = this.move.moveLeft(field, movingPart);
        assertEquals(" 4 4, 4 3, 4 2, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeOneCanBeMovedToTheButtom() {
        int[][] movingPart = this.block.getBlocks(1);
        this.setBlocksOnTheField(movingPart, 1);
        int[][] mP = this.move.moveDown(field, movingPart);
        assertEquals(" 5 25, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeOneCanBeMovedToTheButtomIfThereAreAlreadySomeBlocks() {
        int[][] movingPart = this.block.getBlocks(1);
        this.setBlocksOnTheField(movingPart, 1);
        this.field[5][25] = 1;
        int[][] mP = this.move.moveDown(field, movingPart);
        assertEquals(" 5 24, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeFiveCanBeMovedToTheButtom() {
        int[][] movingPart = this.block.getBlocks(5);
        this.setBlocksOnTheField(movingPart, 5);
        int[][] mP = this.move.moveDown(field, movingPart);
        assertEquals(" 5 25, 5 24, 5 23, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeNineCanBeMovedToTheButtom() {
        int[][] movingPart = this.block.getBlocks(9);
        this.setBlocksOnTheField(movingPart, 9);
        int[][] mP = this.move.moveDown(field, movingPart);
        assertEquals(" 4 24, 5 24, -1 -1, 5 25,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeOneCanBeUpdated() {
        int[][] movingPart = this.block.getBlocks(1);
        this.setBlocksOnTheField(movingPart, 1);
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 5 5, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test 
    public void typeTwoCanBeUpdated() {
        int[][] movingPart = this.block.getBlocks(2);
        this.setBlocksOnTheField(movingPart, 2);
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 5 5, 6 5, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test 
    public void typeThreeWillNotBeUpdatedIfThereIsABlockUnderIt() {
        int[][] movingPart = this.block.getBlocks(3);
        this.setBlocksOnTheField(movingPart, 3);
        this.field[7][5] = 3;
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 5 4, 6 4, 7 4, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeFourCanBeUpdated() {
        int[][] movingPart = this.block.getBlocks(4);
        this.setBlocksOnTheField(movingPart, 4);
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 5 5, 5 4, -1 -1, -1 -1,", this.matrix.matrixToString(mP));
    }

    @Test
    public void typeFiveWillNotBeUpdatedIfThereIsABlockUnderIt() {
        int[][] movingPart = this.block.getBlocks(5);
        this.setBlocksOnTheField(movingPart, 5);
        this.field[5][5] = 1;
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 5 4, 5 3, 5 2, -1 -1,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeSixCanBeUpdated() {
        int[][] movingPart = this.block.getBlocks(6);
        this.setBlocksOnTheField(movingPart, 6);
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 5 5, 6 5, -1 -1, 5 4,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeSevenCanBeUpdated() {
        int[][] movingPart = this.block.getBlocks(7);
        this.setBlocksOnTheField(movingPart, 7);
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 4 5, 5 5, -1 -1, 5 4,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeEightCanBeUpdated() {
        int[][] movingPart = this.block.getBlocks(8);
        this.setBlocksOnTheField(movingPart, 8);
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 5 4, 6 4, -1 -1, 5 5,", this.matrix.matrixToString(mP));
    }
    @Test
    public void typeNineWillNotBeUpdatedIfThereIsABlockUnderIt() {
        int[][] movingPart = this.block.getBlocks(9);
        this.setBlocksOnTheField(movingPart, 9);
        this.field[4][4] = 1;
        int[][] mP = this.move.update(field, movingPart);
        assertEquals(" 4 3, 5 3, -1 -1, 5 4,", this.matrix.matrixToString(mP));
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
