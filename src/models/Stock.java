/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vidur
 */
@AllArgsConstructor
@NoArgsConstructor //generating a No args constructure , LOMBOC ANNOTATION LIB
@Data // setters and getters
public class Stock {
    private int id;
    private int productID;
    private Product product;
    private int grnID;
    private double wholeSalePrice;
    private double retailPrice;
    private double defaultDiscount;
    private double availebleQty;
    private LocalDateTime expDate;
    private LocalDateTime mnfDate;
    private String barcode;
    
    
    
}
