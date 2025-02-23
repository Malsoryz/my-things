/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BangunRuang;

/**
 *
 * @author malsoryz
 */
public class PersegiPanjang {
    private double luas, keliling;
    
    public double getLuas() {
        return luas;
    }
    
    public void setLuas(double panjang, double lebar) {
        this.luas = panjang * lebar;
    }
    
    public double getKeliling() {
        return keliling;
    }
    
    public void setKeliling(double panjang, double lebar) {
        this.keliling = 2 * (panjang + lebar);
    }
}
