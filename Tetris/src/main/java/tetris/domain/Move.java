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
        int[][] movingPart = this.copyArray(mP);
        if (onTheEdge(movingPart, 1)) {                   // check if any blocks is next to the edge of the field
            return movingPart;                                                                                 // and if so, no move can be made.
        }
        int type = field[movingPart[0][0]][movingPart[0][1]];
        int[][] toCheck = this.moveRightBlocksToCheck(movingPart, type);
        System.out.println("toCheck" + this.matrixToString(toCheck));
        if (this.allBlocksCanBeMovedTo(1, field, toCheck)) {
            for (int x = 3; x >= 0; x--) {
                if (movingPart[x][0] != -1) {
                    movingPart[x][0] = movingPart[x][0] + 1;
                }
            }
        }
        return movingPart;
    }
    public int[][] moveLeft(int[][] field, int[][] mP) {
        int[][] movingPart = this.copyArray(mP);
        if (onTheEdge(movingPart, 0)) {                   // check if any blocks is next to the edge of the field
            return movingPart;                                                                                 // and if so, no move can be made.
        }
        int type = field[movingPart[0][0]][movingPart[0][1]];
        int[][] toCheck = this.moveLeftBlocksToCheck(movingPart, type);
        if (this.allBlocksCanBeMovedTo(-1, field, toCheck)) {
            for (int x = 3; x >= 0; x--) {
                if (movingPart[x][0] != -1) {
                    movingPart[x][0] = movingPart[x][0] - 1;
                }
            }
        }
        return movingPart;
    }
    
    
    private int[][] moveRightBlocksToCheck(int[][] movingPart, int type) {
        if (type == 1 || type == 2 || type == 3) {
            int[][] t = {{movingPart[type - 1][0], movingPart[type - 1][1]},{-1, -1},{-1, -1}};
            return t;
        } else if (type == 4) {
            int[][] t = { {movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {-1,-1}};
            return t;
        } else if (type == 5) {
            int[][] t  = {{movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {movingPart[2][0], movingPart[2][1]}};
            return t;
        } else if (type == 6 || type == 7 || type == 8 || type == 9) {
            int t[][] = {{movingPart[3][0], movingPart[3][1]}, {movingPart[1][0],movingPart[1][1]}, {-1, -1}};
            return t;
        }
        return null;
    }
    
    private int[][] moveLeftBlocksToCheck(int[][] movingPart, int type) {
        if (type == 1 || type == 2 || type == 3) {
            int[][] t = {{movingPart[0][0], movingPart[0][1]},{-1, -1},{-1, -1}};
            return t;
        } else if (type == 4) {
            int[][] t = { {movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {-1,-1}};
            return t;
        } else if (type == 5) {
            int[][] t  = {{movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {movingPart[2][0], movingPart[2][1]}};
            return t;
        } else if (type == 6 || type == 7 || type == 8 || type == 9) {
            int t[][] = {{movingPart[3][0], movingPart[3][1]}, {movingPart[0][0],movingPart[0][1]}, {-1, -1}};
            return t;
        }
        return null;
    }
    
    private boolean allBlocksCanBeMovedTo(int a, int[][] field, int[][] toCheck ) {
        // 1 stands for right and -1 for left
        System.out.println("Matrix to check : "+this.matrixToString(toCheck));
        for (int x = 0; x < 3; x++) {
            if (toCheck[x][0] == -1) {
                break;
            } 
            if (field[toCheck[x][0] + a][toCheck[x][1]] != 0) {
                return false;
            }
        }
        return true;
//        } else if (a == 0) {
//            for (int x = 0; x < 3; x++) {
//                if (toCheck[x][0] == -1) {
//                    break;
//                } 
//                if (field[toCheck[x][0] - 1][toCheck[x][1]] != 0) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        return false;
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
    
    // for tests
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
    //for tests
    public String matrixToString(int a[][]) {
        String r = "";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                r = r + " " + a[i][j];
            }
            r = r + ",";
        }
        return r;
    }
}
