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

/**
 *
 * @author alisaelizarova
 */
public class GameTest {
    Game game;
    
    @Before
    public void setUp() {
        this.game = new Game();
    }

    @Test
    public void methodSetAllZeroesWorksProperly() {
        int[][] a = new int[2][2];
        a[0][0] = 1;
        a[0][1] = 2;
        a[1][0] = 3;
        a[1][1] = 4;
        a = game.setAllTheSameValue(a, 0);
        assertEquals(" 0 0, 0 0,", game.matrixToString(a) );
    }
    @Test
    public void methodMatrixToStringWorksProperly() {
        int[][] a = new int[2][2];
        a[0][0] = 1;
        a[0][1] = 2;
        a[1][0] = 3;
        a[1][1] = 4;
        assertEquals(" 1 2, 3 4,", game.matrixToString(a) );
    }
    @Test 
    public void whenGameStartsTypeOfTheFirstFigureIsOne() {
        assertEquals("1", game.getType()+"");
    }
    @Test 
    public void whenGameStartsPositionOfTheMovingPartIsCorrect() {
        assertEquals(" 5 4, -1 -1, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeOneCanBeMovedRight() {
        game.makeMove(1);
        assertEquals(" 6 4, -1 -1, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeOneCanBeMovedLeft() {
        game.makeMove(2);
        assertEquals(" 4 4, -1 -1, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeOneCanBePutDown() {
        game.makeMove(3);
        assertEquals(" 5 25, -1 -1, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeFourIsDefinedCorrectly() {
        game.addNewFigure(4);
        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeSixCanBeMovedRight() {
        game.addNewFigure(6);
        game.makeMove(1);
        assertEquals(" 6 4, 7 4, -1 -1, 6 3,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeFourCanBeMovedRight() {
        game.addNewFigure(4);
        game.makeMove(1);
        assertEquals(" 6 4, 6 3, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeFiveWillNotBeMovedRightIfSomeBlocksNextToIt() {
        game.addNewFigure(5);
        game.reservePlace(6, 3);
        game.makeMove(1);
        assertEquals(" 5 4, 5 3, 5 2, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeSevenCanBeMovedLeft() {
        game.addNewFigure(7);
        game.makeMove(2);
        assertEquals(" 3 4, 4 4, -1 -1, 4 3,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeFiveCanBePutDown() {
        game.addNewFigure(5);
        game.makeMove(3);
        assertEquals(" 5 25, 5 24, 5 23, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeFiveCanBeMovedLeft() {
        game.addNewFigure(5);
        game.makeMove(2);
        assertEquals(" 4 4, 4 3, 4 2, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void typeFourWillNotBeMovedLeftIfSomeBlocksNextToIt() {
        game.addNewFigure(4);
        game.reservePlace(4, 3);
        game.makeMove(2);
        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test 
    public void typeTwoWillNotMoveDownIfThereIsABlockUnderIt() {
        game.addNewFigure(2);
        game.reservePlace(5, 7);
        game.makeMove(3);
        assertEquals(" 5 6, 6 6, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test 
    public void rotationWorksForTypeTwo() {
        game.addNewFigure(2);
        game.rotate();
        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test 
    public void rotationWorksForTypeOne() {
        game.addNewFigure(1);
        game.rotate();
        assertEquals(" 5 4, -1 -1, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test 
    public void rotationWorksForTypeThree() {
        game.addNewFigure(3);
        game.rotate();
        assertEquals(" 6 5, 6 4, 6 3, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
    @Test
    public void rotationDoesntWorkForTypeFourIfThereIsBlockNextToIt() {
        game.addNewFigure(4);
        game.reservePlace(6, 4);
        game.rotate();
        assertEquals(" 5 4, 5 3, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
    }
}
