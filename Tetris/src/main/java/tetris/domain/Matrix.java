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
    
     /**
     * This method takes a matrix and returns its String form. 
     * Used for tests.
     * 
     * @param m - matrix (int[][])
     * 
     * @return matrix in String (String). 
     */
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
    
    /**
     * This method takes a matrix and sets its all numbers to the same value (a) 
     * Used for tests.
     * 
     * @param m - matrix (int[][])
     * @param a - value (int)
     * 
     * @return updated matrix (int[][]). 
     */
    public int[][] setAllTheSameValue(int[][] m, int a) {
        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[x].length; y++) {
                m[x][y] = a;
            }
        }
        return m;
    }
    
    /**
     * This method make a copy of a matrix/ array. 
     * New array will not changed if the old one is modified.
     * 
     * @param m - matrix (int[][])
     * 
     * @return copy of the matrix (int[][]). 
     */
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
