/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

/**
 *
 * @author alisaelizarova
 */
public class Move {
    public int[][] moveRight(int[][] field, int[][] mP) {
        this.printMatrix(field);
        int[][] movingPart = this.copyArray(mP);
        System.out.println("The moving part:");
        this.printMatrix(movingPart);
        if (onTheEdge(movingPart, 1)) {  
            System.out.println("The blocks are on the edge");                   // check if any blocks is next to the edge of the field
            return movingPart;                                                                                 // and if so, no move can be made.
        }
        int[][] toCheck = new int[3][2];
        this.setAllTheSameValue(toCheck, -1);
        int type = field[movingPart[0][0]][movingPart[0][1]];
        toCheck = this.moveRightBlocksToCheck(toCheck, movingPart, type);
//        if (type == 1 || type == 2 || type == 3) {
//            toCheck[0][0] = movingPart[type - 1][0];
//            toCheck[0][1] = movingPart[type - 1][1];
//        } else if (type == 4) {
//            toCheck[0][0] = movingPart[0][0];
//            toCheck[0][1] = movingPart[0][1];
//            toCheck[1][0] = movingPart[1][0];
//            toCheck[1][1] = movingPart[1][1];
//        } else if (type == 5) {
//            toCheck[0][0] = movingPart[0][0];
//            toCheck[0][1] = movingPart[0][1];
//            toCheck[1][0] = movingPart[1][0];
//            toCheck[1][1] = movingPart[1][1];
//            toCheck[2][0] = movingPart[2][0];
//            toCheck[2][1] = movingPart[2][1];
//        } else if (type == 6 || type == 7 || type == 8 || type == 9) {
//            toCheck[0][0] = movingPart[3][0];
//            toCheck[0][1] = movingPart[3][1];
//            toCheck[1][0] = movingPart[1][0];
//            toCheck[1][1] = movingPart[1][1];
//        }
        System.out.println("The matrix to check:");
        this.printMatrix(toCheck);
        for (int x = 0; x < 3; x++) {
            if (toCheck[x][0] == -1) {
                break;
            } 
            if (field[toCheck[x][0] + 1][toCheck[x][1]] != 0) {
                System.out.println("The blocks cannot be moved right!");
                return movingPart;
            }
        }
        for (int x = 3; x >= 0; x--) {
            if (movingPart[x][0] != -1) {
                movingPart[x][0] = movingPart[x][0] + 1;
            }
        }
        System.out.println("The blocks are moved");
        return movingPart;
    }
    
    private int[][] moveRightBlocksToCheck(int[][] toCheck, int[][] movingPart, int type) {
        if (type == 1 || type == 2 || type == 3) {
            toCheck[0][0] = movingPart[type - 1][0];
            toCheck[0][1] = movingPart[type - 1][1];
        } else if (type == 4) {
            toCheck[0][0] = movingPart[0][0];
            toCheck[0][1] = movingPart[0][1];
            toCheck[1][0] = movingPart[1][0];
            toCheck[1][1] = movingPart[1][1];
        } else if (type == 5) {
            toCheck[0][0] = movingPart[0][0];
            toCheck[0][1] = movingPart[0][1];
            toCheck[1][0] = movingPart[1][0];
            toCheck[1][1] = movingPart[1][1];
            toCheck[2][0] = movingPart[2][0];
            toCheck[2][1] = movingPart[2][1];
        } else if (type == 6 || type == 7 || type == 8 || type == 9) {
            toCheck[0][0] = movingPart[3][0];
            toCheck[0][1] = movingPart[3][1];
            toCheck[1][0] = movingPart[1][0];
            toCheck[1][1] = movingPart[1][1];
        }
        return toCheck;
    }
    
    private boolean onTheEdge(int[][] movingPart, int a) {              // check if any blocks is next to the edge of the field, if so return TRUE
        if (a == 1)  {                              // 1 stands for moving right 
            for (int x = 0; x < 4; x++) {
                if (movingPart[x][0] == 10) {
                    return true;
                }
            }
        } else if (a == 0) {                         // 0 stands for moving left 
            for (int x = 0; x < 4; x++) {
                if(movingPart[x][0] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int[][] setAllTheSameValue(int[][] m, int a) {
        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[x].length; y++) {
                m[x][y] = a;
            }
        }
        return m;
    }
    public int[][] copyArray(int[][] src) {
        int[][] dst = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                dst[i][j] = src[i][j];
            }
        }
        return dst;
    }
    public void printMatrix(int a[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
