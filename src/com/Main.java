/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import gui.Login;
import gui.PurchuseOrderPanel;

/**
 *
 * @author vidur
 */
public class Main {

    public static void main(String args[]) {
        
        com.formdev.flatlaf.themes.FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
