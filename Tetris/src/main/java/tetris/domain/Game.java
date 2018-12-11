package tetris.domain;

import com.google.common.primitives.Ints;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author alisaelizarova
 */
public class Game {
    int[][] field;
    int heigth;
    int length;
    int timesUpdated;
    int[][] position;
    int[] colums;
    int type;
    int[][] movingPart;
    
    
    public Game() {
        this.field = new int[11][26];
        this.heigth = 26;
        this.length = 11;
        this.timesUpdated = 0;
        this.position = new int[4][2];
        this.colums = new int[11];
        this.type = 1;
        this.movingPart = new int[4][2];
        this.addNewFigure(1);
        
    }
    public void setMovingPartPosition(int row, int x, int y, int xDelete, int yDelete) {

        this.movingPart[row][0] = x;
        this.movingPart[row][1] = y;
        this.field[xDelete][yDelete] = 0;
        this.field[x][y] = 1;
    }
    
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
    public int[][] getMovingPart() {
        return this.movingPart;
    }
    
    public void setField(int[][] a) {
        this.field = a;
    }

    public int[][] getField() {
        return field;
    }

    public int getHeigth() {
        return heigth;
    }
    
    public void reservePlace(int x, int y) {                                    // only for testing
        this.field[x][y] = 1;
    }

    public int getLength() {
        return length;
    }
    public void addOnePeace(int x, int y) {
        this.field[x][y] = 1;
    }
    
     /**
     * This method will update your game, which means
     * that the moving part (blocks) will be put down 
     * be one step. 
     * 
     * @return TRUE means that the update succeed and 
     * FALSE means that the game is over. 
     */
    public boolean update() {
        String beforeUpdate = Arrays.deepToString(this.movingPart);

        updateType1();
        updateType2();
        updateType6();
        
        this.aRowIsFull();
        if (Arrays.deepToString(this.movingPart).equals(beforeUpdate)) {
            Random random = new Random();
            this.type = random.nextInt(7 - 1 + 1) + 1;
            if (this.gameover()) {
                return false;
            }
            this.addNewFigure(this.type);
        }
        return true;
    }
    /**
     * This method contains logics, with which blocks of types 1, 4, 5 
     * updated ( = moved down).
     */
    private void updateType1() {
        if (this.type == 1 || this.type == 4 || this.type == 5) {
            for (int x = 0; x < 4; x++) {
                if (this.movingPart[x][0] != -1 && this.movingPart[x][1] != -1) {
                    if (this.roomUnder(movingPart[x][0], movingPart[x][1])) {
                        System.out.println("updated by type 1!");
//                        this.field[movingPart[x][0]][movingPart[x][1]] = 0;
//                        this.field[movingPart[x][0]][movingPart[x][1] + 1] = 1;
                        this.setMovingPartPosition(x, movingPart[x][0], movingPart[x][1] + 1, movingPart[x][0], movingPart[x][1]);
                    }
                } else {
                    return;
                }
            }
        }
    }
    /**
     * This method contains logics, with which blocks of types 2, 3 
     * will be updated ( = moved down).
     */
    private void updateType2() {
        int b = 0;
        int a = 0;
        if (this.type == 2 || this.type == 3) {
            for (int x = 0; x < 4; x++) {
                if (this.movingPart[x][0] != -1) {
                    b++;
                    if (this.roomUnder(this.movingPart[x][0], this.movingPart[x][1])) {
                        a++;
                    }
                } 
            }
            if (a == b) {
                for (int x = 0; x < 4; x++) {
                    if (this.movingPart[x][0] != -1) {
//                        this.field[movingPart[x][0]][movingPart[x][1]] = 0;
//                        this.field[movingPart[x][0]][movingPart[x][1] + 1] = 1;
                        this.setMovingPartPosition(x, movingPart[x][0], movingPart[x][1] + 1, movingPart[x][0], movingPart[x][1]);
                    } 
                }
            }
        }
    }
    /**
     * This method contains logics, with which blocks of types 6, 7 
     * will be updated ( = moved down).
     */
    private void updateType6() {
        int a = 0;
        int b = 0;
        if (this.type == 6 || this.type == 7) {
            for (int x = 0; x < 3; x++) {
                if (this.movingPart[x][0] != -1) {
                    b++;
                    if (this.roomUnder(this.movingPart[x][0], this.movingPart[x][1])) {
                        a++;
                    }
                }
            }
            if (a == b) {
                for (int x = 0; x < 3; x++) {
                    if (this.movingPart[x][0] != -1) {
//                        this.field[movingPart[x][0]][movingPart[x][1]] = 0;
//                        this.field[movingPart[x][0]][movingPart[x][1] + 1] = 1;
                        this.setMovingPartPosition(x, movingPart[x][0], movingPart[x][1] + 1, movingPart[x][0], movingPart[x][1]);
                    } 
                }
                this.setMovingPartPosition(3, movingPart[3][0], movingPart[3][1] + 1, movingPart[3][0], movingPart[3][1]);
            }
            
        }
        
    }
    
    /**
     * This method checks if there are any rows full of blocks. 
     * At the moment, it can find only one row at a time.
     * 
     * If one is found, method "moveAllRowsDownAboveRowY" is called.
     */
    public void aRowIsFull() {
        int sum = 0;
        for (int y = 0; y < this.heigth; y++) {
            for (int x = 0; x < this.length; x++) {
                if (this.field[x][y] == 1) {
                    sum++;
                }
            }
            if (sum == 11) {
                for (int x = 0; x < this.length; x++) {
                    this.field[x][y] = 0;
                }
                this.moveAllRowsDownAboveRowY(y);
            }

            sum = 0;
        }
        
    }
    
    /**
     * This method will delete one row and move the blocks above
     * the y th row down.
     * 
     * @param y a full row needed to be deleted 
     */
    public void moveAllRowsDownAboveRowY(int y) {
        System.out.println("Great method started!");
        while (y - 1 > 4) {
            int blocks = 0;
            for (int x = 0; x < 11 ; x++) {
                System.out.println(this.matrixToString(this.movingPart));
                if (this.field[x][y - 1] == 1 || this.matrixToString(this.movingPart).contains(x + " " + (y - 1))) {
                    blocks++;
                    this.field[x][y] = 1;
                    this.field[x][y - 1] = 0;
                }                 
            }
            if (blocks == 0) {
                return;
            }
            y--;
        }
    }
    
    /**
     * This method return the part of game field that is visible for a user. 
     * 
     * @return game field (int[11][22]) 
     */
    public int[][] visible() {
        int[][] v = new int[11][22];
        for (int y = 4; y < this.heigth; y++) {
            for (int x = 0; x < this.length; x++) {
                v[x][y - 4] = this.field[x][y];
            }
        }
        return v;
    }
    
    /**
     * This method adds a new figure according to the number in the parament. 
     * 
     * @param number figure type
     */
    public void addNewFigure(int number) {
        this.type = number;
        this.setAllTheSameValue(this.movingPart, -1);
        if (number == 1) {
            this.field[5][4] = 1;
            this.movingPart[0][0] = 5;
            this.movingPart[0][1] = 4;
        }
        if (number == 2) {
            this.field[5][4] = 1;
            this.field[6][4] = 1;
            this.movingPart[0][0] = 5;
            this.movingPart[0][1] = 4;
            this.movingPart[1][0] = 6;
            this.movingPart[1][1] = 4;
        }
        if (number == 3) {
            this.field[5][4] = 1;
            this.field[6][4] = 1;
            this.field[7][4] = 1;
            this.movingPart[0][0] = 5;
            this.movingPart[0][1] = 4;
            this.movingPart[1][0] = 6;
            this.movingPart[1][1] = 4;
            this.movingPart[2][0] = 7;
            this.movingPart[2][1] = 4;
        }
        if (number == 4) {
            this.field[5][4] = 1;
            this.field[5][3] = 1;
            this.movingPart[0][0] = 5;
            this.movingPart[0][1] = 4;
            this.movingPart[1][0] = 5;
            this.movingPart[1][1] = 3;
        } else if (number == 5) {
            this.field[5][4] = 1;
            this.field[5][3] = 1;
            this.field[5][2] = 1;
            this.movingPart[0][0] = 5;
            this.movingPart[0][1] = 4;
            this.movingPart[1][0] = 5;
            this.movingPart[1][1] = 3;
            this.movingPart[2][0] = 5;
            this.movingPart[2][1] = 2;
        } else if (number == 6) {
            this.field[5][4] = 1;
            this.field[6][4] = 1;
            this.field[5][3] = 1;
            this.movingPart[0][0] = 5;
            this.movingPart[0][1] = 4;
            this.movingPart[1][0] = 6;
            this.movingPart[1][1] = 4;
            this.movingPart[3][0] = 5;
            this.movingPart[3][1] = 3;
        } else if (number == 7) {
            this.field[4][4] = 1;
            this.field[5][4] = 1;
            this.field[5][3] = 1;
            this.movingPart[0][0] = 4;
            this.movingPart[0][1] = 4;
            this.movingPart[1][0] = 5;
            this.movingPart[1][1] = 4;
            this.movingPart[3][0] = 5;
            this.movingPart[3][1] = 3;
        } 
    }
    
    /**
     * This sets all numbers of the matrix m to the same value. 
     * 
     * @param m matrix that need to be changed
     * @param a number that will replace all values of the matrix m
     * @return new matrix 
     */
    public int[][] setAllTheSameValue(int[][] m, int a) {
        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[x].length; y++) {
                m[x][y] = a;
            }
        }
        return m;
    }
    
//    public boolean onTheBottom() {
//        int[][] p = this.getPosition();
//        int a = 0;
//        int b = 0;
//        if (p[0][0] == -1) {
//            return true;
//        }
//        for (int x = 0; x < 4; x++) {
//            if (p[x][0] != -1) {
//                a++;
//                if (this.roomUnder(p[x][0], p[x][1])) {
//                    b++;
//                }
//            }
//        }
//        if (a == b) {
//            return false;
//        }
//        return true;
//    }
    
    /**
     * This method checks if there is room under a block  
     * 
     * @param x coordinates of the block
     * @param y 
     * @return TRUE if there some room, FALSE otherwise.
     */
    public boolean roomUnder(int x, int y) {
        System.out.println("x: " + x + " y: " + y);
        if (y == 25) {
            return false;
        } else if (this.field[x][y + 1] == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * This method handles moving right, left and down.
     * 
     * @param number tells in which direction the block should be moved.
     * 1    right
     * 2    left
     * 3    down
     */
    public void makeMove(int move) {
        // Right
        if (move == 1) {
            makeMoveRight();
        } else if (move == 2) {    // left
            makeMoveLeft(); 
        } else if (move == 3) { 
            makeMoveDown();                                                     // down
        }

    }
    
    private boolean onTheEdge(int a) {              // check if any blocks is next to the edge of the field, if so return TRUE
        if (a == 1)  {                              // 1 stands for moving right 
            for (int x = 0; x < 4; x++) {
                if (this.movingPart[x][0] == 10) {
                    return true;
                }
            }
        } else if (a == 0) {                         // 0 stands for moving left 
            for (int x = 0; x < 4; x++) {
                if(this.movingPart[x][0] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    private void makeMoveRight() {
        if (this.onTheEdge(1)) {                                                // check if any blocks is next to the edge of the field
            return;                                                             // and if so, no move can be made.
        }
        int[][] toCheck = new int[3][2];
        this.setAllTheSameValue(toCheck, -1);
        if (this.type == 1 || this.type == 2 || this.type == 3) {
            toCheck[0][0] = this.movingPart[this.type - 1][0];
            toCheck[0][1] = this.movingPart[this.type - 1][1];
        } else if (this.type == 4) {
            toCheck[0][0] = this.movingPart[0][0];
            toCheck[0][1] = this.movingPart[0][1];
            toCheck[1][0] = this.movingPart[1][0];
            toCheck[1][1] = this.movingPart[1][1];
        } else if (this.type == 5) {
            toCheck[0][0] = this.movingPart[0][0];
            toCheck[0][1] = this.movingPart[0][1];
            toCheck[1][0] = this.movingPart[1][0];
            toCheck[1][1] = this.movingPart[1][1];
            toCheck[2][0] = this.movingPart[2][0];
            toCheck[2][1] = this.movingPart[2][1];
        } else if (this.type == 6 || this.type == 7) {
            toCheck[0][0] = this.movingPart[3][0];
            toCheck[0][1] = this.movingPart[3][1];
            toCheck[1][0] = this.movingPart[1][0];
            toCheck[1][1] = this.movingPart[1][1];
        }
        for (int x = 0; x < 3; x++) {
            if (toCheck[x][0] == -1) {
                break;
            } 
            if (this.field[toCheck[x][0] + 1][toCheck[x][1]] != 0) {
                return;
            }
        }
        for (int x = 3; x >= 0; x--) {
            if (this.movingPart[x][0] != -1) {
                this.setMovingPartPosition(x, this.movingPart[x][0] + 1, this.movingPart[x][1], this.movingPart[x][0], this.movingPart[x][1]);
            }
        }
    }    
    private void makeMoveLeft() {
        if (this.onTheEdge(0)) {
            return;
        }
        int[][] toCheck = new int[3][2];
        this.setAllTheSameValue(toCheck, -1);
        if (this.type == 1 || this.type == 2 || this.type == 3) {
            toCheck[0][0] = this.movingPart[0][0];
            toCheck[0][1] = this.movingPart[0][1];
        } else if (this.type == 4) {
            toCheck[0][0] = this.movingPart[0][0];
            toCheck[0][1] = this.movingPart[0][1];
            toCheck[1][0] = this.movingPart[1][0];
            toCheck[1][1] = this.movingPart[1][1];
        } else if (this.type == 5) {
            toCheck[0][0] = this.movingPart[0][0];
            toCheck[0][1] = this.movingPart[0][1];
            toCheck[1][0] = this.movingPart[1][0];
            toCheck[1][1] = this.movingPart[1][1];
            toCheck[2][0] = this.movingPart[2][0];
            toCheck[2][1] = this.movingPart[2][1];
        } else if (this.type == 6 || this.type == 7) {
            toCheck[0][0] = this.movingPart[3][0];
            toCheck[0][1] = this.movingPart[3][1];
            toCheck[1][0] = this.movingPart[0][0];
            toCheck[1][1] = this.movingPart[0][1];
        }
        for (int x = 0; x < 3; x++) {
            if (toCheck[x][0] == -1) {
                break;
            } 
            if (this.field[toCheck[x][0] - 1][toCheck[x][1]] != 0) {
                return;
            }
        }
        for (int x = 0; x < 4; x++) {
            if (this.movingPart[x][0] != -1) {
                this.setMovingPartPosition(x, this.movingPart[x][0] - 1, this.movingPart[x][1], this.movingPart[x][0], this.movingPart[x][1]);
            }
        }
        
    }
    private void makeMoveDown() {
        if (this.movingPart[0][1] == 25) {
            return;
        }
        int[][] toCheck = new int[3][2];
        this.setAllTheSameValue(toCheck, -1);
        if (this.type == 1 || this.type == 2 || this.type == 3 || this.type == 6 || this.type == 7) {
            toCheck[0][0] = this.movingPart[0][0];
            toCheck[0][1] = this.movingPart[0][1];
            toCheck[1][0] = this.movingPart[1][0];
            toCheck[1][1] = this.movingPart[1][1];
            toCheck[2][0] = this.movingPart[2][0];
            toCheck[2][1] = this.movingPart[2][1];
        } else if (this.type == 4 || this.type == 5) {
            toCheck[0][0] = this.movingPart[0][0];
            toCheck[0][1] = this.movingPart[0][1];
        }
        int d = 0;                                                              // d says how many blocks we chould take our figure down.
        while (true) {
            for (int x = 0; x < 3; x++) {
                if (toCheck[x][0] != -1) {
                    if (this.field[toCheck[x][0]][toCheck[x][1] + d + 1] == 1 ) {                  
                        for(int xN = 0; xN < 4; xN++) {
                            if (this.movingPart[xN][0] != -1) {
                                this.setMovingPartPosition(xN, this.movingPart[xN][0], this.movingPart[xN][1] + d, this.movingPart[xN][0], this.movingPart[xN][1]);
                            }
                        }
                        return;
                    }
                }
            }
            d++;
            if (toCheck[0][1] + d == 25) {
                for (int xN = 0; xN < 4; xN++) {
                        if (this.movingPart[xN][0] != -1) {
                            this.setMovingPartPosition(xN, this.movingPart[xN][0], this.movingPart[xN][1] + d, this.movingPart[xN][0], this.movingPart[xN][1]);
                        }
                    }
                return;
            } 
        }
    }
    
    /**
     * This method rotates the figure if it's possible. 
     */
    public void rotate() {
        // works only for types 1, 2, 3 4, 5
        if (this.type == 1) {
            return;
        } else if (this.type == 2) {
            if (this.field[this.movingPart[0][0]][this.movingPart[0][1] - 1] == 0) {
                this.type = 4;
                this.setMovingPartPosition(1, this.movingPart[0][0], this.movingPart[0][1] - 1, this.movingPart[1][0], this.movingPart[1][1]);
            }
        } else if (this.type == 3) {
            if (this.field[this.movingPart[1][0]][this.movingPart[1][1] + 1] == 0 && this.field[this.movingPart[1][0]][this.movingPart[1][1] - 1] == 0) {
                this.type = 5;
                this.setMovingPartPosition(0, this.movingPart[1][0], this.movingPart[1][1] + 1, this.movingPart[0][0], this.movingPart[0][1]);
                this.setMovingPartPosition(2, this.movingPart[1][0], this.movingPart[1][1] - 1, this.movingPart[2][0], this.movingPart[2][1]);
            }
        } else if (this.type == 4) {
            if (this.field[this.movingPart[0][0] + 1][this.movingPart[0][1]] == 0) {
                this.type = 2;
                this.setMovingPartPosition(1, this.movingPart[0][0] + 1, this.movingPart[0][1], this.movingPart[1][0], this.movingPart[1][1]);
            }
        } else if (this.type == 5) {
            if (this.field[this.movingPart[1][0] - 1][this.movingPart[1][1]] == 0 && this.field[this.movingPart[1][0] + 1][this.movingPart[1][1]] == 0) {
                this.type = 3;
                this.setMovingPartPosition(0, this.movingPart[1][0] - 1, this.movingPart[1][1], this.movingPart[0][0], this.movingPart[0][1]);
                this.setMovingPartPosition(2, this.movingPart[1][0] + 1, this.movingPart[1][1], this.movingPart[2][0], this.movingPart[2][1]);
            }
        }
    }
     
//    public int[][] getPosition() {
//        int r[][] = {{-1, -1, -1, -1}, {-1, -1, -1, -1}};
//        int rX = 0;
//        boolean moving = false;
//        this.printMatrix(r);
//        for (int y = 0; y < this.heigth - 1; y++) {
//            for (int x = 0; x < this.length; x++) {
//                if (this.field[x][y] == 1) {
//                    System.out.println(x + "," + y);
//                    r[0][rX] = x;
//                    r[1][rX] = y;
//                    this.printMatrix(r);
//                    rX++;
//                }
//                if (this.roomUnder(x, y)) {
//                    moving = true;
//                }
//                if (rX == this.type) {
//                    break;
//                }
//            }
//        }
////        this.printMatrix(r);
//        if (moving) {
//            return r;
//        }
//        int[][] a = {{-1, -1, -1, -1}, {-1, -1, -1, -1}};
//        return a;
//        
//    }

    /**
     * This method informs if the game is over or not. 
     * It decides it based on if there is a room for the following figure.
     * 
     * @return TRUE if the game is over, FALSE otherwise.
     */
    public boolean gameover() {
        if (this.type == 1 || this.type == 4 || this.type == 5) {
            if (this.field[5][4] == 1) {
                return true;
            }
        } else if (this.type == 6 || this.type == 2) {
            if (this.field[5][4] == 1 || this.field[6][4] == 1) {
                return true;
            }
        } else if (this.type == 7) {
            if (this.field[4][4] == 1 || this.field[5][4] == 1) {
                return true;
            }
        } else if (this.type == 3) {
            if (this.field[6][4] == 1 || this.field[5][4] == 1 || this.field[7][4] == 1) {
                return true;
            }
        } 
        return false;
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
    public int[][] copyArray(int[][] src) {
        int[][] dst = new int[11][26];
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                dst[i][j] = src[i][j];
            }
        }
        return dst;
    }
}
