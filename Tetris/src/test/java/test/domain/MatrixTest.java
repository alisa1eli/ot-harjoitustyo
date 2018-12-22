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
import tetris.domain.Matrix;

/**
 *
 * @author alisaelizarova
 */
public class MatrixTest {
    Matrix m;
    int[][] a;

    @Before
    public void setUp() {
        this.m = new Matrix();
        this.a = new int[2][2];
        a[0][0] = 1;
        a[0][1] = 2;
        a[1][0] = 3;
        a[1][1] = 4;
    }
    
    @Test
    public void methodMatrixToStringWorksProperly() {
        assertEquals(" 1 2, 3 4,", m.matrixToString(a) );
    }
    @Test
    public void methodSetAllZeroesWorksProperly() {
        a = m.setAllTheSameValue(a, 0);
        assertEquals(" 0 0, 0 0,", m.matrixToString(a) );
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
