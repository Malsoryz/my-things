/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BangunRuang;

/**
 *
 * @author malsoryz
 */
public class Tabung {
    private double luas, volume;
    
    public double getLuas() {
        return luas;
    }
    
    public void setLuas(double jari, double tinggi) {
        this.luas = Math.round((2 * Math.PI * jari * (jari * tinggi)) * 100.0) / 100.0;
    }
    
    public double getVolume() {
        return volume;
    }
    
    public void setVolume(double jari, double tinggi) {
        this.volume = Math.round((Math.PI * Math.pow(jari, 2) * tinggi) * 100.0) / 100.0;
    }
}
