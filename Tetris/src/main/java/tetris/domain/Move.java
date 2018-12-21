package tetris.domain;

/**
 * The main methods of this class are moveRight, moveLeft and moveDown.
 * The idea of those methods are:
 * They take the game field and position of the moving blocks. 
 * They move it right, left or all the way down.
 * After that, they return the new position. 
 * (Class Game processes it by updating the position, deleting old blocks and  adding blocks to new position.)
 * 
 * @author alisaelizarova
 */
public class Move {
    
    /**
     * This method moves, if it's possible, the moving blocks (referred as movingPart) right.
     * The position will not changed, if it is not possible to move it.
     * 
     * @param field of the game(int[][])
     * @param mP - position of the moving blocks (int[][])
     * 
     * @return updated position of the moving blocks (int[][])
     */
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
    
    /**
     * This method moves, if it's possible, the moving blocks (referred as movingPart) left.
     * The position will not changed, if it is not possible to move it.
     * 
     * @param field of the game(int[][])
     * @param mP - position of the moving blocks (int[][])
     * 
     * @return updated position of the moving blocks (int[][]). 
     */
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
    
    /**
     * This method moves, if it's possible, the moving blocks (referred as movingPart) to the bottom of the game field.
     * The position will not changed, if it is not possible to move it.
     * 
     * @param field of the game(int[][])
     * @param mP = position of the moving blocks (int[][])
     * 
     * @return updated position of the moving blocks (int[][]). 
     */
    public int[][] moveDown(int[][] field, int[][] mP) {
        int[][] movingPart = this.copyArray(mP);
        if (this.onTheEdge(movingPart, 2)) {
            return movingPart;
        }
        int type = field[movingPart[0][0]][movingPart[0][1]];
        int[][] toCheck = this.moveDownBlocksToCheck(movingPart, type);
        System.out.println("Blocks to check : " + this.matrixToString(toCheck));
        int d = this.howManyBlocksWeCanTakeBlocksDown(field, toCheck);          // d says how many blocks we chould take our figure down.
        System.out.println("We should take it " + d + " blocks down.");
        for (int x = 0; x < 4; x++) {
            if (movingPart[x][0] != -1) {
                movingPart[x][1] = movingPart[x][1] + d;  
            }
        }
        return movingPart;
    }
    
    // Next three methods will pick up those blocks next to which we should 
    // check for room to move.
    
    private int[][] moveRightBlocksToCheck(int[][] movingPart, int type) {
        if (type == 1 || type == 2 || type == 3) {
            int[][] t = {{movingPart[type - 1][0], movingPart[type - 1][1]}, {-1, -1}, {-1, -1}};
            return t;
        } else if (type == 4) {
            int[][] t = { {movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {-1, -1}};
            return t;
        } else if (type == 5) {
            int[][] t  = {{movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {movingPart[2][0], movingPart[2][1]}};
            return t;
        } else if (type == 6 || type == 7 || type == 8 || type == 9) {
            int t[][] = {{movingPart[3][0], movingPart[3][1]}, {movingPart[1][0], movingPart[1][1]}, {-1, -1}};
            return t;
        }
        return null;
    }
    
    private int[][] moveLeftBlocksToCheck(int[][] movingPart, int type) {
        if (type == 1 || type == 2 || type == 3) {
            int[][] t = {{movingPart[0][0], movingPart[0][1]}, {-1, -1}, {-1, -1}};
            return t;
        } else if (type == 4) {
            int[][] t = { {movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {-1, -1}};
            return t;
        } else if (type == 5) {
            int[][] t  = {{movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {movingPart[2][0], movingPart[2][1]}};
            return t;
        } else if (type == 6 || type == 7 || type == 8 || type == 9) {
            int t[][] = {{movingPart[3][0], movingPart[3][1]}, {movingPart[0][0], movingPart[0][1]}, {-1, -1}};
            return t;
        }
        return null;
    }
    
    private int[][] moveDownBlocksToCheck(int[][] movingPart, int type) {
        if (type == 1 || type == 2 || type == 3 || type == 6 || type == 7) {
            int[][] t = { {movingPart[0][0], movingPart[0][1]}, { movingPart[1][0], movingPart[1][1]}, {movingPart[2][0], movingPart[2][1]} };
            return t;
        } else if (type == 4 || type == 5) {
            int[][] t = { {movingPart[0][0], movingPart[0][1]}, {-1, -1}, {-1, -1}};
            return t;
        } else if (type == 8) {
            int[][] t = {{movingPart[3][0], movingPart[3][1]}, {movingPart[1][0], movingPart[1][1]}, {-1, -1}};
            return t;
        } else if (type == 9) {
            int[][] t = { {movingPart[3][0], movingPart[3][1]}, {movingPart[0][0], movingPart[0][1]}, {-1, -1}};
            return t;
        }
        return null;
    }
    
    // This method checks if there is free room next to all blocks.
    // Return true, if we can move the whole figure to left(a = -1) or to right(a = 1)
    private boolean allBlocksCanBeMovedTo(int a, int[][] field, int[][] toCheck) {
        System.out.println("Matrix to check : " + this.matrixToString(toCheck));
        for (int x = 0; x < 3; x++) {
            if (toCheck[x][0] == -1) {
                break;
            } 
            if (field[toCheck[x][0] + a][toCheck[x][1]] != 0) {
                return false;
            }
        }
        return true;
    } 
    
    private int howManyBlocksWeCanTakeBlocksDown(int[][] field, int[][] toCheck) {
        int free = 3; 
        int d = 0;                                                             // d says how many blocks we chould take our figure down.
        while (free == 3) { 
            d++;
            free = 0;
            for (int x = 0; x < 3; x++) {
                if (toCheck[x][0] != -1) {
                    if (toCheck[x][1] + d > 25) {
                        d--;
                        break;
                    }
                    if (field[toCheck[x][0]][toCheck[x][1] + d] == 0) {
                        free++;
                    } else {
                        d--;
                        free--;
                    }
                } else {
                    free++;
                }
            }
        }
        return d;
    }
    
    // this method checks if any of the blocks are on the edge or on the bottom.
    // (a = 1) stands for the right edge, (a = 0) for the left one and
    // (a = 2) for the bottom.
    private boolean onTheEdge(int[][] movingPart, int a) {             
        for (int x = 0; x < 4; x++) {
            if (movingPart[x][0] == 10 && a == 1) {
                return true;
            }
            if (movingPart[x][0] == 0 && a == 0) {
                return true;
            } 
            if (movingPart[x][1] == 25 && a == 2) {
                return true;
            }
        }
        return false;
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
}
