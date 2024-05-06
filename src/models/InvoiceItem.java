/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

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
public class InvoiceItem {
    private int invoiceItemID;
    private int stockID;
    private Stock stock;
    private double qty;
    private double dicount;
    private double value;
    private double unitPrice;
    
}
