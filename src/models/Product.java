/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

/**
 *
 * @author vidur
 */
@AllArgsConstructor
@NoArgsConstructor //generating a No args constructure , LOMBOC ANNOTATION LIB
@Data // setters and getters
public class Product {
    private int id;
    private int unitID;
    private Unit unit;
    private String productName;
    private String productPrintingName;
    
    
    public void setProductName(String name){
        if(Validators.isValidProductName(name)){
            this.productName = name;
        }else{
            throw new IllegalArgumentException(" invalid Product name "); 
        }
    }
    
    public void setProductPrintingName(String name){
        if(Validators.isValidProductName(name)){
            this.productName = name;
        }else{
            throw new IllegalArgumentException(" invalid Product Printing  name "); 
        }
    }
}
