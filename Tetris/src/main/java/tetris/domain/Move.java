package tetris.domain;

import java.util.Arrays;

/**
 * The main methods of this class are Update, moveRight, moveLeft and moveDown.
 * The idea of those methods are:
 * They take the game field and position of the moving blocks. 
 * They move it one block down, right, left or all the way down.
 * After that, they return the new position. 
 * (Class Game processes it by updating the position, deleting old blocks and  adding blocks to new position.)
 * 
 * @author alisaelizarova
 */
public class Move {
    
    Matrix matrix;
    
    /**
     * No parameters required. 
     * Class Matrix is in use now.
     */
    public Move() {
        this.matrix = new Matrix();
    }
    
    /**
     * This method updates, if it's possible, the position of the moving blocks (referred as movingPart).
     * Updating means that the blocks will be moved to the row below.
     * The position will not changed, if it is not possible to move it.
     * 
     * @param field of the game(int[][])
     * @param mP - position of the moving blocks (int[][])
     * 
     * @return updated position of the moving blocks (int[][])
     */
    public int[][] update(int[][] field, int[][] mP) {
        int[][] movingPart = this.matrix.copyArray(mP);
        if (onTheEdge(movingPart, 2)) {                                         // checks if any of blocks are already on the bottom           
            return movingPart;                                                  // if so no update needed                                                                                
        }
        int type = field[movingPart[0][0]][movingPart[0][1]];

        int[][] toCheck = this.updateBlocksToCheck(movingPart, type);
        if (this.allBlocksCanBeUpdate(field, toCheck)) {
            for (int x = 3; x >= 0; x--) {
                if (movingPart[x][0] != -1) {
                    movingPart[x][1] = movingPart[x][1] + 1;
                }
            }
        }
        return movingPart;
    }
    
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
        int[][] movingPart = this.matrix.copyArray(mP);
        if (onTheEdge(movingPart, 1)) {                   // check if any blocks is next to the edge of the field
            return movingPart;                                                                                 // and if so, no move can be made.
        }
        int type = field[movingPart[0][0]][movingPart[0][1]];
        int[][] toCheck = this.moveRightBlocksToCheck(movingPart, type);
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
        int[][] movingPart = this.matrix.copyArray(mP);
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
        int[][] mPCopy = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}};
        int[][] movingPart = this.matrix.copyArray(mP);
        int[][] fakeField = this.matrix.copyArray(field);
        int type = fakeField[movingPart[0][0]][movingPart[0][1]];
        while (!Arrays.deepToString(movingPart).equals(Arrays.deepToString(mPCopy))) {
            mPCopy = this.matrix.copyArray(movingPart);
            movingPart = this.update(fakeField, movingPart);
            for (int x = 0; x < 4; x++) {
                if (movingPart[x][0] != -1) {
                    fakeField[movingPart[x][0]][movingPart[x][1]] = type;
                }
            }
        }
        return movingPart;
    }
    
    // Next three methods will pick up those blocks next to which we should 
    // check for room to move.
    private int[][] updateBlocksToCheck(int[][] movingPart, int type) {
        if (type == 8) {
            int[][] t = {{movingPart[3][0], movingPart[3][1]}, {movingPart[1][0], movingPart[1][1]}, {-1, -1}};
            return t;
        } else if (type == 9) {
            int[][] t = {{movingPart[3][0], movingPart[3][1]}, {movingPart[0][0], movingPart[0][1]}, {-1, -1}};
            return t;
        } else if (type == 4 || type == 5) {
            int[][] t = {{movingPart[0][0], movingPart[0][1]}, {-1, -1}, {-1, -1}};
            return t;
        }
        int[][] t = {{movingPart[0][0], movingPart[0][1]}, {movingPart[1][0], movingPart[1][1]}, {movingPart[2][0], movingPart[2][1]}};
        return t;
    }
    
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
   
    // This method checks if there is free room next to all blocks.
    // Return true, if we can move the whole figure to left(a = -1) or to right(a = 1)
    private boolean allBlocksCanBeMovedTo(int a, int[][] field, int[][] toCheck) {
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
    // This method checks if there is free room next to all blocks to be updated.
    private boolean allBlocksCanBeUpdate(int[][] field, int[][] toCheck) {
        for (int x = 0; x < 3; x++) {
            if (toCheck[x][0] == -1) {
                break;
            } 
            if (field[toCheck[x][0]][toCheck[x][1] + 1] != 0) {
                return false;
            }
        }
        return true;
    }
    
    
    // this method checks if any of the blocks are on the edge or on the bottom.
    // (a = 1) stands for the right edge, 
    // (a = 0) for the left one and
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
}
