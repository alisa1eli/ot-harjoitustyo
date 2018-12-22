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
    Level level;
    int points;
    Move move;
    Block block;
    Rotate rotate;

    
    public Game() {
        this.field = new int[11][26];
        this.heigth = 26;
        this.length = 11;
        this.timesUpdated = 0;
        this.position = new int[4][2];
        this.colums = new int[11];
        this.type = 1;
        this.movingPart = new int[4][2];
        this.block = new Block();
        this.addNewFigure(1);
        this.points = 0;
        this.level = new Level();
        this.move = new Move();
        this.rotate = new Rotate();

    }
    public void setMovingPartPosition(int t, int row, int x, int y, int xDelete, int yDelete) {

        this.movingPart[row][0] = x;
        this.movingPart[row][1] = y;
        this.field[xDelete][yDelete] = 0;
        this.field[x][y] = t;
    }
    
    public int getPoints() {
        return this.level.getPoints();
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
     * that the moving part (blocks) will be moved down 
     * by one step. 
     * 
     * @return TRUE means that the update succeed and 
     * FALSE means that the game is over. 
     */
    public boolean update() {
        String beforeUpdate = Arrays.deepToString(this.movingPart);  
//        System.out.println("Before Update: ");// this way will be possible compared if the moving part has moved or it is already on the bottom and a new blocks are needed
//        this.printMatrix(field);
        this.setMovingPart(this.move.update(this.field, this.movingPart));
//        System.out.println("After:");
//        this.printMatrix(field);
//        System.out.println("-----------------------------------------");
        if (Arrays.deepToString(this.movingPart).equals(beforeUpdate)) {
            this.aRowIsFull();                                                  // the program checks is there are any full rows
            Random random = new Random();                                       // to choose the next block
            this.type = random.nextInt(9 - 1 + 1) + 1;                          // at the moment there is only 9 different blocks
            if (this.gameover()) {
                return false;
            }
            this.addNewFigure(this.type);
        }
        return true;
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
                if (this.field[x][y] != 0) {
                    sum++;
                }
            }
            if (sum == 11) {
                this.level.nextLevel();
                this.removeRowYAndMoveAllRowsDown(y);
                y--;
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
    public void removeRowYAndMoveAllRowsDown(int y) {
        for (int x = 0; x < this.length; x++) {
            this.field[x][y] = 0;
        }
        while (y - 1 > 4) {
            int blocks = 0;
            for (int x = 0; x < 11 ; x++) {
                if (this.field[x][y - 1] != 0) {
                    blocks++;
                    this.field[x][y] = this.field[x][y - 1];
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
     * @return game field (Integer[11][22]) 
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
        this.movingPart = this.getCurentTypeOfFigure();
        for (int x = 0; x < 4; x++) {
            if (this.movingPart[x][0] != -1) {
                this.field[this.movingPart[x][0]][this.movingPart[x][1]] = this.type;
            }
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
    
    
    /**
     * This method checks if there is room under a block  
     * 
     * @param x coordinates of the block
     * @param y 
     * @return TRUE if there some room, FALSE otherwise.
     */
    
//    public boolean roomUnder(int x, int y) {
//        if (y == 25) {
//            return false;
//        } else if (this.field[x][y + 1] == 0) {
//            return true;
//        }
//        return false;
//    }
    
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
            this.setMovingPart(this.move.moveRight(field, movingPart));
        } else if (move == 2) {    // left
            this.setMovingPart(this.move.moveLeft(field, movingPart)); 
        } else if (move == 3) {   // to the bottom
            this.setMovingPart(this.move.moveDown(field, movingPart));                                              // down
        }

    }
        
    private void setMovingPart(int[][] mP) {
        for (int x = 0; x < 4; x++) {
            if (this.movingPart[x][0] != -1) {
                this.field[this.movingPart[x][0]][this.movingPart[x][1]] = 0;
            }
        }
        this.movingPart = mP;
        for (int x = 0; x < 4; x++) {
            if (this.movingPart[x][0] != -1) {
                this.field[this.movingPart[x][0]][this.movingPart[x][1]] = this.type;
            }
        }
    }
    
    /**
     * This method rotates the figure if it's possible. 
     * The logics of this action is located in class Rotate.
     * 
     *
     */
    public void rotate() {
        this.rotate.rotate(field, movingPart);
        this.setField(this.rotate.getField());
        this.movingPart = this.rotate.getMovingPart();
        this.type = this.field[this.movingPart[0][0]][this.movingPart[0][1]];
    }
     
    /**
     * This method informs if the game is over or not. 
     * It decides it based on if there is a room for the following figure.
     * 
     * @return TRUE if the game is over, FALSE otherwise.
     */
    public boolean gameover() {
        int[][] a = this.getCurentTypeOfFigure();
        for (int x = 0; x < 4; x++) {
            if (a[x][0] != -1) {
                if (this.field[a[x][0]][a[x][1]] != 0 ) {
                    return true;
                }
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
    
    private int[][] getCurentTypeOfFigure() {
        return this.block.getBlocks(this.type);
    }
    
    public String getColor(int x, int y) {
        return this.level.getColors()[this.field[x][y + 4]];
    }
    
    public long getSpeed() {
        long speed = this.level.getSpeed();
        return speed;
    }

    public void setLevel(String text) {    
        String t[] = text.split(" ");
        if (!t[0].equals("arcade")) {
            if (t[0].equals("slow")) {
                this.level.setSpeed(1);
            } else if (t[0].equals("medium")) {
                
                this.level.setSpeed(2);
            } else {
                this.level.setSpeed(3);
            }
        } else {
            this.level.setArcadeTrue();
        }
        if ((t[2].equals("fading"))) {
            this.level.setFading(true);
        } else {
            this.level.setFading(false);
        }
    }
}