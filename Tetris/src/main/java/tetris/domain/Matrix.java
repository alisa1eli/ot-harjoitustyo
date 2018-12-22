/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

/**
 * This class contains methods for processing matrixes. 
 * 
 * @author alisaelizarova
 */
public class Matrix {
    
    public String matrixToString(int[][] m) {
        String r = "";
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                r = r + " " + m[i][j];
            }
            r = r + ",";
        }
        return r;
    }
    public int[][] setAllTheSameValue(int[][] m, int a) {
        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[x].length; y++) {
                m[x][y] = a;
            }
        }
        return m;
    }
    public int[][] copyArray(int[][] m) {
        int[][] r = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                r[i][j] = m[i][j];
            }
        }
        return r;
    }
}
