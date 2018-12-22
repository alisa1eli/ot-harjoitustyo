package test.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.OldGame;

/**
 *
 * @author alisaelizarova
 */
public class OldGameTest {
    OldGame game;
    OldGame gameWithDetails;
    
    @Before
    public void setUp() {
        this.game = new OldGame(10);
        this.gameWithDetails = new OldGame(1, 15, new Date().toString());
    }


    @Test
    public void oldGamesAreComparableByScoreWhenGivenGamesScoreIsBigger() {
        OldGame a = new OldGame(11);
        assertTrue(game.compareTo(a) == 1);
    }
    @Test
    public void oldGamesAreComparableByScoreWhenGivenGamesScoreIsSmaller() {
        OldGame a = new OldGame(9);
        assertTrue(game.compareTo(a) == -1);
    }
    @Test
    public void methodGetIdWorksProperly() {
        assertTrue(this.gameWithDetails.getId() == 1);
    }
//    @Test
//    public void methodToStringWorksProperly() {
//        assertEquals("1 5 | "+this.gameWithDetails.getDate(),this.gameWithDetails.toString());
//    }
}
