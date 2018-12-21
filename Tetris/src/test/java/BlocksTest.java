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

/**
 *
 * @author alisaelizarova
 */
public class BlocksTest {
    Block blocks;

    @Before
    public void setUp() {
        this.blocks = new Block();
    }
    @Test 
    public void methodSetAllTheSameValueWorksProperly() {
        int[][] m = this.blocks.getBase();
        assertEquals(" -1 -1 -1 -1, -1 -1 -1 -1,", this.blocks.matrixToString(m));
    }
    @Test 
    public void canGetType1() {
        int[][] m = this.blocks.getBlocks(1);
        assertEquals(" 5 4 -1 -1, -1 -1 -1 -1,", this.blocks.matrixToString(m));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
