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
public class Supplier {
    private int id;
    private String name;
    private String mobile;
    private String email;
    private String address;
    private String details;
    private String bankDetailsID;
    
     public void setName(String name){
        if(Validators.isValidUserName(name)){
            this.name = name;
        }else{
            throw new IllegalArgumentException("customer  Name length must between 2 and 40");
        }
    }
    
     
     public void setMobile(String mobile){
        if(Validators.isValidMobile(mobile)){
            this.mobile = mobile;
        }else{
            throw new IllegalArgumentException("mobile length must be 10 ");
        }
    }
     
      public void setEmail(String email){
        if(Validators.isValidEmail(email)){
            this.email = email;
        }else{
            throw new IllegalArgumentException("Invalid email format: " + email); 
        }
    }
    
}
