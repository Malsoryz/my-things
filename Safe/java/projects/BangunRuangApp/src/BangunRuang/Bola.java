/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BangunRuang;

/**
 *
 * @author malsoryz
 */
public class Bola {
    private double luas, volume;
    
    public double getLuas() {
        return luas;
    }
    
    public void setLuas(double jari) {
        this.luas = Math.round((4 * Math.PI * Math.pow(jari, 2)) * 100.0) / 100.0;
    }
    
    public double getVolume() {
        return volume;
    }
    
    public void setVolume(double jari) {
        this.volume = Math.round(((4.0 / 3.0) * Math.PI * Math.pow(jari, 3)) * 100.0) / 100.0;
    }
}
