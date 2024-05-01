/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import gui.Login;
import models.User;

/**
 *
 * @author vidur
 */
public class Main {
    public static void main(String args[]) {
        User user = new User();
        user.setEmail("hello");
        com.formdev.flatlaf.themes.FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}


//dispose freme, exist code 0 