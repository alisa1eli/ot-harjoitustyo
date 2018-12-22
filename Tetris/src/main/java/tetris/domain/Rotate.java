package tetris.domain;

/**
 *
 * @author alisaelizarova
 */
public class Rotate {
    int[][] field;
    int[][] movingPart;
    int type;
    int[][] mP;
    public Rotate() {
        this.field = new int[11][26];
        this.type = 1;
        this.movingPart = new int[4][2];
        this.mP = new int[4][2];
    }
    
    public void rotate(int[][] field, int[][] movingPart) {
        this.movingPart = this.copyArray(movingPart);
        this.field = this.copyArray(field);
        this.type = field[movingPart[0][0]][movingPart[0][1]];
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
//        System.out.println("The field in the class: " + this.matrixToString(field));
    }
    public int[][] getMovingPart() {
        return this.movingPart;
    }
    public int[][] getField() {
        return this.field;
    }
    public void rotateType2 (){
        if (this.field[this.movingPart[0][0]][this.movingPart[0][1] - 1] == 0) {
//            this.type = 4;
            this.mP = this.copyArray(this.movingPart);
            this.mP[1][0] = movingPart[0][0];
            this.mP[1][1] = movingPart[0][1] - 1;
            this.setMovingPart(4);
//            System.out.println("Field : " + this.matrixToString(field));
        }
    }
    public void rotateType3 (){
        if (this.movingPart[1][1] == 25) {
                return;
        }
        if (this.field[this.movingPart[1][0]][this.movingPart[1][1] + 1] == 0 && this.field[this.movingPart[1][0]][this.movingPart[1][1] - 1] == 0) {
//            this.type = 5;
            this.mP = this.copyArray(this.movingPart);
            this.mP[0][0] = movingPart[1][0];
            this.mP[0][1] = movingPart[1][1] + 1;
            this.mP[2][0] = movingPart[1][0];
            this.mP[2][1] = movingPart[1][1] - 1;
            this.setMovingPart(5);
        }
    }
    public void rotateType4 (){
        if(this.movingPart[0][0] == 10) {
                return;
        }
        if (this.field[this.movingPart[0][0] + 1][this.movingPart[0][1]] == 0) {
//            this.type = 2;
            this.mP = this.copyArray(this.movingPart);
            this.mP[1][0] = movingPart[0][0] + 1;
            this.mP[1][1] = movingPart[0][1];
            this.setMovingPart(2);
        }
    }
    public void rotateType5(){
        if (this.movingPart[1][0] == 0 || this.movingPart[1][0] == 10) {
            return;
        }
        if (this.field[this.movingPart[1][0] - 1][this.movingPart[1][1]] == 0 && this.field[this.movingPart[1][0] + 1][this.movingPart[1][1]] == 0) {
//            this.type = 3;
            this.mP = this.copyArray(this.movingPart);
            this.mP[0][0] = this.movingPart[1][0] - 1;
            this.mP[0][1] = this.movingPart[1][1];
            this.mP[2][0] = this.movingPart[1][0] + 1;
            this.mP[2][1] = this.movingPart[1][1];
            this.setMovingPart(3);    
        }
    }
    public void rotateType6(){
        if (this.movingPart[0][1] == 10 ) {
            return;
        }
        if (this.field[this.movingPart[0][0]][this.movingPart[0][1] + 1] == 0) {
//            this.type = 8;
            this.mP = this.copyArray(this.movingPart);
            this.mP[3][0] = this.movingPart[0][0];
            this.mP[3][1] = this.movingPart[0][1] + 1;
            this.setMovingPart(8);    
        }
    }
    public void rotateType7(){
       if (this.movingPart[1][0] == 10) {
            return;
        }
        if (this.field[this.movingPart[1][0] + 1][this.movingPart[0][1]] == 0) {
//            this.type = 6;
            this.mP = this.copyArray(this.movingPart);
            this.mP[0][0] = this.movingPart[0][0] + 1;
            this.mP[0][1] = this.movingPart[0][1];
            this.mP[1][0] = this.movingPart[1][0] + 1;
            this.mP[1][1] = this.movingPart[1][1];
            this.setMovingPart(6);
        }
    }
    public void rotateType8(){
        if (this.movingPart[0][0] == 0 ) {
            return;
        }
        if (this.field[this.movingPart[0][0] - 1][this.movingPart[0][1]] == 0) {
//            this.type = 9;
            this.mP = this.copyArray(this.movingPart);
            this.mP[0][0] = this.movingPart[0][0] - 1;
            this.mP[0][1] = this.movingPart[0][1];
            this.mP[1][0] = this.movingPart[0][0];
            this.mP[1][1] = this.movingPart[0][1];
            this.setMovingPart(9);
            }
    }
    public void rotateType9(){
         if (this.field[this.movingPart[1][0]][this.movingPart[1][1] - 1] == 0) {
//            this.type = 7;
            this.mP = this.copyArray(this.movingPart);
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
        this.movingPart = this.copyArray(mP);
        for (int x = 0; x < 4; x++) {
            if (this.movingPart[x][0] != -1) {
                this.field[this.movingPart[x][0]][this.movingPart[x][1]] = t;
            }
        }
    }
    public String matrixToString(int[][] m) {
        String r = "\n";
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                r = r + " " + m[i][j];
            }
            r = r + "\n";
        }
        return r;
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
