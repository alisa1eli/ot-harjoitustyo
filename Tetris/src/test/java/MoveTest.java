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
import tetris.domain.Move;

/**
 *
 * @author alisaelizarova
 */
public class MoveTest {
    Move move;
    int[][] field;
    
    @Before
    public void setUp() {
        this.move = new Move();
        this.field = this.move.setAllTheSameValue(new int[11][26], 0);
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
//    @Test
//    public void typeOneCanBeMovedRight() {
//        int mP = move.moveRight(field, mP);
//        assertEquals(" 6 4, -1 -1, -1 -1, -1 -1,", game.matrixToString(game.getMovingPart()));
//    }
    

//    @Test
//    public void 
}
