/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BangunRuang;

/**
 *
 * @author malsoryz
 */
public class Lingkaran {
    private double luas, keliling;
    
    public double getLuas() {
        return luas;
    }
    
    public void setLuas(double jari) {
        this.luas = Math.round((Math.PI * Math.pow(jari, 2)) * 100.0) / 100.0;
    }
    
    public double getKeliling() {
        return keliling;
    }
    
    public void setKeliling(double jari) {
        this.keliling = Math.round((2 * Math.PI * jari) * 100.0) / 100.0;
    }
}
