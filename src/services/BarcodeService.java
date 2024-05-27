/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.Random;

/**
 *
 * @author vidur
 */
public class BarcodeService {
    public static int makeBarcodeForStock(){
        // Create a Random object
        Random random = new Random();
        
        // Generate a random integer between 111111111 and 999999999
        int min = 111111111;
        int max = 999999999;
        int barcode = random.nextInt((max - min) + 1) + min;
        
        return barcode;
    }
    public static void printBarcode(int barcode){
        
    }
    
}
