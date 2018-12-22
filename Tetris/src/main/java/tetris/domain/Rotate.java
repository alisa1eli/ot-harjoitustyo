package tetris.domain;

/**
 * This class handles rotation of blocks.
 * Class game use only method "rotate".
 * 
 * @author alisaelizarova
 */
public class Rotate {
    Matrix matrix;
    int[][] field;
    int[][] movingPart;
    int type;
    int[][] mP;
    
    /**
     * This method set values for the class parameters.
     */
    public Rotate() {
        this.matrix = new Matrix();
        this.field = new int[11][26];
        this.type = 1;
        this.movingPart = new int[4][2];
        this.mP = new int[4][2];
    }
    
    /**
     * This method gets game field and movingPart as parameters.
     * It calculates the type of the movingPart according to which it call 
     * methods that rotate blocks of that certain type.
     * 
     * The method doesn't return anything.
     * But the updated field and movingPart is in memory and should be extracted 
     * lately by methods "getMovingPart" and "getField".
     * 
     * This method is used by class Game.
     * 
     * @param field - game field (int[][])
     * @param movingPart - a position of moving part (blocks)
     */
    public void rotate(int[][] field, int[][] movingPart) {
        this.setParameters(field, movingPart);
        if (type == 2) {
            this.rotateType2();
        } else if (type == 3) {
            this.rotateType3();
        } else if (type == 4) {
            this.rotateType4();
        } else if (type == 5) {
            this.rotateType5();
        } else if (type == 6) {
            this.rotateType6();
        } else if (type == 7) {
            this.rotateType7();
        } else if (type == 8) {
            this.rotateType8();
        } else if (type == 9) {
            this.rotateType9();
        }
    }
    
    private void setParameters(int[][] field, int[][] movingPart) {
        this.movingPart = this.matrix.copyArray(movingPart);
        this.field = this.matrix.copyArray(field);
        this.type = field[movingPart[0][0]][movingPart[0][1]];
    }
    
    public int[][] getMovingPart() {
        return this.movingPart;
    }
    public int[][] getField() {
        return this.field;
    }
    private void rotateType2() {
        if (this.field[this.movingPart[0][0]][this.movingPart[0][1] - 1] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[1][0] = movingPart[0][0];
            this.mP[1][1] = movingPart[0][1] - 1;
            this.setMovingPart(4);
        }
    }
    private void rotateType3() {
        if (this.movingPart[1][1] == 25) {
            return;
        }
        if (this.field[this.movingPart[1][0]][this.movingPart[1][1] + 1] == 0 && this.field[this.movingPart[1][0]][this.movingPart[1][1] - 1] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[0][0] = movingPart[1][0];
            this.mP[0][1] = movingPart[1][1] + 1;
            this.mP[2][0] = movingPart[1][0];
            this.mP[2][1] = movingPart[1][1] - 1;
            this.setMovingPart(5);
        }
    }
    private void rotateType4() {
        if (this.movingPart[0][0] == 10) {
            return;
        }
        if (this.field[this.movingPart[0][0] + 1][this.movingPart[0][1]] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[1][0] = movingPart[0][0] + 1;
            this.mP[1][1] = movingPart[0][1];
            this.setMovingPart(2);
        }
    }
    private void rotateType5() {
        if (this.movingPart[1][0] == 0 || this.movingPart[1][0] == 10) {
            return;
        }
        if (this.field[this.movingPart[1][0] - 1][this.movingPart[1][1]] == 0 && this.field[this.movingPart[1][0] + 1][this.movingPart[1][1]] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[0][0] = this.movingPart[1][0] - 1;
            this.mP[0][1] = this.movingPart[1][1];
            this.mP[2][0] = this.movingPart[1][0] + 1;
            this.mP[2][1] = this.movingPart[1][1];
            this.setMovingPart(3);    
        }
    }
    private void rotateType6() {
        if (this.movingPart[0][1] == 10) {
            return;
        }
        if (this.field[this.movingPart[0][0]][this.movingPart[0][1] + 1] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[3][0] = this.movingPart[0][0];
            this.mP[3][1] = this.movingPart[0][1] + 1;
            this.setMovingPart(8);    
        }
    }
    private void rotateType7() {
        if (this.movingPart[1][0] == 10) {
            return;
        }
        if (this.field[this.movingPart[1][0] + 1][this.movingPart[0][1]] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[0][0] = this.movingPart[0][0] + 1;
            this.mP[0][1] = this.movingPart[0][1];
            this.mP[1][0] = this.movingPart[1][0] + 1;
            this.mP[1][1] = this.movingPart[1][1];
            this.setMovingPart(6);
        }
    }
    private void rotateType8() {
        if (this.movingPart[0][0] == 0) {
            return;
        }
        if (this.field[this.movingPart[0][0] - 1][this.movingPart[0][1]] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[0][0] = this.movingPart[0][0] - 1;
            this.mP[0][1] = this.movingPart[0][1];
            this.mP[1][0] = this.movingPart[0][0];
            this.mP[1][1] = this.movingPart[0][1];
            this.setMovingPart(9);
        }
    }
    private void rotateType9() {
        if (this.field[this.movingPart[1][0]][this.movingPart[1][1] - 1] == 0) {
            this.mP = this.matrix.copyArray(this.movingPart);
            this.mP[3][0] = this.movingPart[1][0];
            this.mP[3][1] = this.movingPart[1][1] - 1;
            this.setMovingPart(7);
        }
    }

    private void setMovingPart(int t) {
        for (int x = 0; x < 4; x++) {
            if (this.movingPart[x][0] != -1) {
                this.field[this.movingPart[x][0]][this.movingPart[x][1]] = 0;
            }
        }
        this.movingPart = this.matrix.copyArray(mP);
        for (int x = 0; x < 4; x++) {
            if (this.movingPart[x][0] != -1) {
                this.field[this.movingPart[x][0]][this.movingPart[x][1]] = t;
            }
        }
    }
}
