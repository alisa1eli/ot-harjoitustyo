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

/**
 *
 * @author alisaelizarova
 */
public class BlocksTest {
    Block blocks;
    Matrix matrix;

    @Before
    public void setUp() {
        this.blocks = new Block();
        this.matrix = new Matrix();
    }
    @Test 
    public void itIsPosiibleToGetType1() {
        int[][] m = this.blocks.getBlocks(1);
        assertEquals(" 5 4, -1 -1, -1 -1, -1 -1,", this.matrix.matrixToString(m));
    }
}
