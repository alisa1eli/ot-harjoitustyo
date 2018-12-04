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
        this.game.setAllZeroes();
    }

    @Test
    public void methodSetAllZeroesWorksProperly() {
        this.game.addNewFigure(1);
        
    }
}
