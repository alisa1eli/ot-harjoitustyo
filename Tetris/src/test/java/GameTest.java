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
import tetris.domain.Game;
import tetris.domain.Matrix;

/**
 *
 * @author alisaelizarova
 */
public class GameTest {
    Game game;
    Matrix matrix;
    
    @Before
    public void setUp() {
        this.game = new Game();
    }

    @Test 
    public void whenGameStartsTypeOfTheFirstFigureIsOne() {
        assertEquals("1", game.getType()+"");
    }
//    @Test 
//    public void whenGameStartsPositionOfTheMovingPartIsCorrect() {
//        assertEquals(" 5 4, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(game.getMovingPart()));
//    }
//
//    @Test
//    public void typeFourIsDefinedCorrectly() {
//        game.addNewFigure(4);
//        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", this.matrix.matrixToString(game.getMovingPart()));
//    }
//    @Test
//    public void typeFiveWillNotBeMovedRightIfSomeBlocksNextToIt() {
//        game.addNewFigure(5);
//        game.reservePlace(6, 3);
//        game.makeMove(1);
//        assertEquals(" 5 4, 5 3, 5 2, -1 -1,", game.matrixToString(game.getMovingPart()));
//    }



//    @Test
//    public void typeFourWillNotBeMovedLeftIfSomeBlocksNextToIt() {
//        game.addNewFigure(4);
//        game.reservePlace(4, 3);
//        game.makeMove(2);
//        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
//    }
//    @Test 
//    public void typeTwoWillNotMoveDownIfThereIsABlockUnderIt() {
//        game.addNewFigure(2);
//        game.reservePlace(5, 7);
//        game.makeMove(3);
//        assertEquals(" 5 6, 6 6, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
//    }
//    @Test 
//    public void rotationWorksForTypeTwo() {
//        game.addNewFigure(2);
//        game.rotate();
//        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", this.matrix.matrixToString(game.getMovingPart()));
//    }
//    @Test 
//    public void rotationWorksForTypeOne() {
//        game.addNewFigure(1);
//        game.rotate();
//        assertEquals(" 5 4, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(game.getMovingPart()));
//    }
//    @Test 
//    public void rotationWorksForTypeThree() {
//        game.addNewFigure(3);
//        game.rotate();
//        assertEquals(" 6 5, 6 4, 6 3, -1 -1,", this.matrix.matrixToString(game.getMovingPart()));
//    }
//    @Test
//    public void rotationDoesntWorkForTypeFourIfThereIsBlockNextToIt() {
//        game.addNewFigure(4);
//        game.reservePlace(6, 4);
//        game.rotate();
//        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
//    }
}
