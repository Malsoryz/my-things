/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BangunRuang;

/**
 *
 * @author malsoryz
 */
public class Kubus {
    private double luas, volume;
    
    public double getLuas() {
        return luas;
    }
    
    public void setLuas(double sisi) {
        this.luas = 6 * (sisi * sisi);
    }
    
    public double getVolume() {
        return volume;
    }
    
    public void setVolume(double sisi) {
        this.volume = sisi * sisi * sisi;
    }
}
