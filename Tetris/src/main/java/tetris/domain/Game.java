package tetris.domain;

import java.util.Arrays;

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
    
    
    public Game() {
        this.field = new int[11][26];
        this.heigth = 26;
        this.length = 11;
        this.field[6][4] = 1;
        this.timesUpdated = 0;
        this.position = new int[4][2];
        this.colums = new int[11];
        this.type = 1;
        
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

    public int getLength() {
        return length;
    }
    public void addOnePeace(int x, int y) {
        this.field[x][y] = 1;
    }
    
    public void update() {
        // copy array

        String beforeUpdate = Arrays.deepToString(field);
        
        for (int y = this.heigth - 1; y >= 0; y--) {
            for (int x = 0; x < this.length; x++) {
                if (this.field[x][y] == 1) {
                    if (this.roomUnder(x, y)) {
                        this.field[x][y] = 0;
                        this.field[x][y + 1] = 1;
                    }
                }
            }
        }
        this.aRowIsFull();
        if (Arrays.deepToString(field).equals(beforeUpdate)) {
            this.addNewFigure(1);
            this.type = 1;
        }
        
//        for (int i = 0; i < this.field.length; i++) {
//            for (int j = 0; j < field[i].length; j++) {
//                System.out.print(field[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        
    }
    
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
            }
            sum = 0;
        }
    }
    
    public int[][] visible() {
        int[][] v = new int[11][22];
        for (int y = 4; y < this.heigth; y++) {
            for (int x = 0; x < this.length; x++) {
                v[x][y - 4] = this.field[x][y];
            }
        }
        return v;
    }
    
    public void addNewFigure(int number) {
        if (number == 1) {
            this.field[5][4] = 1;
        }
        if (number == 2) {
            this.field[3][5] = 1;
            this.field[3][6] = 1;
        }
        if (number == 3) {
            this.field[3][5] = 1;
            this.field[3][6] = 1;
            this.field[3][7] = 1;
        }
    }
    
    
    public void setAllZeroes() {
        for (int y = 0; y < this.heigth; y++) {
            for (int x = 0; x < this.length; x++) {
                this.field[x][y] = 0;
            }
        }
    }
    public boolean onTheBottom() {
        int[][] p = this.getPosition();
        int a = 0;
        int b = 0;
        if (p[0][0] == -1) {
            return true;
        }
        for (int x = 0; x < 4; x++) {
            if (p[x][0] != -1) {
                a++;
                if (this.roomUnder(p[x][0], p[x][1])) {
                    b++;
                }
            }
        }
        if (a == b) {
            return false;
        }
        return true;
    }
    public boolean roomUnder(int x, int y) {
        if (y == 25) {
            return false;
        } else if (this.field[x][y + 1] == 0) {
            return true;
        }
        return false;
    }
    
    public void makeMove(int move) {
        int[][] position = this.getPosition();
        // Right
        if (move == 1) {
            for (int a = 0; a < 4; a++) {
                if (position[0][a] != -1) {
                    int x = position[0][a];
                    int y = position[1][a];
                    if (x < 10 && y < 25) {
                        this.field[x][y] = 0;
                        this.field[x + 1][y] = 1;
                    }
                } else {
                    return;
                }
            } 
        } else if (move == 2) {    // left
            for (int a = 0; a < 4; a++) {
                if (position[0][a] != -1) {
                    int x = position[0][a];
                    int y = position[1][a];
                    if (x - 1 < 0) {
                        return;
                    }
                    this.field[x][y] = 0;
                    this.field[x - 1][y] = 1;
                    
                } else {
                    return;
                }
            } 
        } else if (move == 3) {                 // down
            for (int a = 0; a < 4; a++) {
                if (position[0][a] != -1) {
                    int x = position[0][a];
                    int y = position[1][a];
                    int newY = y;
                    while (newY + 1 <= 25) {
                        if (this.field[x][newY + 1] == 0) {
                            newY = newY + 1;
                        } else {
                            break;
                        }   
                    }
                    this.field[x][newY] = 1;
                    this.field[x][y] = 0;
                }
            } 
        }
    }
    
    public int[][] getPosition() {
        int r[][] = {{-1, -1, -1, -1}, {-1, -1, -1, -1}};
        int rX = 0;
        boolean moving = false;
        this.printMatrix(r);
        for (int y = 0; y < this.heigth - 1; y++) {
            for (int x = 0; x < this.length; x++) {
                if (this.field[x][y] == 1) {
                    System.out.println(x + "," + y);
                    r[0][rX] = x;
                    r[1][rX] = y;
                    this.printMatrix(r);
                    rX++;
                }
                if (this.roomUnder(x, y)) {
                    moving = true;
                }
                if (rX == this.type) {
                    break;
                }
            }
        }
//        this.printMatrix(r);
        if (moving) {
            return r;
        }
        int[][] a = {{-1, -1, -1, -1}, {-1, -1, -1, -1}};
        return a;
        
    }

    public boolean gameover() {
        for (int x = 0; x < this.length; x++) {
            if (this.field[x][4] == 1 && !this.roomUnder(x, 21)) {
                this.printMatrix(field);
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
