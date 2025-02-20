/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BangunRuang;

/**
 *
 * @author malsoryz
 */
public class Persegi {
    private double luasPersegi, kelilingPersegi;
    
    public double getLuasPersegi() {
        return luasPersegi;
    }
    
    public void setLuasPersegi(double sisi) {
        this.luasPersegi = sisi * sisi;
    }
    
    public double getKelilingPersegi() {
        return kelilingPersegi;
    }
    
    public void setKelilingPersegi(double sisi) {
        this.kelilingPersegi = 4 * sisi;
    }
}
