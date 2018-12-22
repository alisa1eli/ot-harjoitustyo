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
public class Block {
    public Block() {
    }
    
    public int[][] getBlocks(int type) {
        switch (type) {
            case 1:            return this.getBlockType1();
            case 2:            return this.getBlockType2();
            case 3:            return this.getBlockType3();
            case 4:            return this.getBlockType4();
            case 5:            return this.getBlockType5();
            case 6:            return this.getBlockType6();
            case 7:            return this.getBlockType7();
            case 8:            return this.getBlockType8();
            case 9:            return this.getBlockType9();
            default:           break;
        }  
        return this.getBlockType1();
    }
    
    public int[][] getBlockType1() {
        int[][] a = this.getBase();
        a[0][0] = 5;
        a[0][1] = 4;
        return a;
    }
    public int[][] getBlockType2() {
        int[][] a = this.getBase();
        a[0][0] = 5;
        a[0][1] = 4;
        a[1][0] = 6;
        a[1][1] = 4;
        return a;
    }
    public int[][] getBlockType3() {
        int[][] a = this.getBase();
        a[0][0] = 5;
        a[0][1] = 4;
        a[1][0] = 6;
        a[1][1] = 4;
        a[2][0] = 7;
        a[2][1] = 4;
        return a;
    }
    public int[][] getBlockType4() {
        int[][] a = this.getBase();
        a[0][0] = 5;
        a[0][1] = 4;
        a[1][0] = 5;
        a[1][1] = 3;
        return a;
    } 
    public int[][] getBlockType5() {
        int[][] a = this.getBase();
        a[0][0] = 5;
        a[0][1] = 4;
        a[1][0] = 5;
        a[1][1] = 3;
        a[2][0] = 5;
        a[2][1] = 2;
        return a;
    } 
    public int[][] getBlockType6() {
        int[][] a = this.getBase();
        a[0][0] = 5;
        a[0][1] = 4;
        a[1][0] = 6;
        a[1][1] = 4;
        a[3][0] = 5;
        a[3][1] = 3;
        return a;
    } 
    public int[][] getBlockType7() {
        int[][] a = this.getBase();
        a[0][0] = 4;
        a[0][1] = 4;
        a[1][0] = 5;
        a[1][1] = 4;
        a[3][0] = 5;
        a[3][1] = 3;
        return a;
    }
    public int[][] getBlockType8() {
        int[][] a = this.getBase();
        a[0][0] = 5;
        a[0][1] = 3;
        a[1][0] = 6;
        a[1][1] = 3;
        a[3][0] = 5;
        a[3][1] = 4;
        return a;
    }
    public int[][] getBlockType9() {
        int[][] a = this.getBase();
        a[0][0] = 4;
        a[0][1] = 3;
        a[1][0] = 5;
        a[1][1] = 3;
        a[3][0] = 5;
        a[3][1] = 4;
        return a;
    }
    
    public int[][] getBase() {
        int[][] r = {{-1, -1},{ -1 ,-1},{ -1, -1},{ -1, -1}};
        return r;
    }
  

}
