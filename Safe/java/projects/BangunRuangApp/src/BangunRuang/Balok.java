/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BangunRuang;

/**
 *
 * @author malsoryz
 */
public class Balok {
    private double luas, volume;
    
    public double getLuas() {
        return luas;
    }
    
    public void setLuas(double panjang, double lebar, double tinggi) {
        this.luas = 2 * (panjang * lebar + panjang * tinggi + lebar * tinggi);
    }
    
    public double getVolume() {
        return volume;
    }
    
    public void setVolume(double panjang, double lebar, double tinggi) {
        this.volume = panjang * lebar * tinggi;
    }
}
