/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

/**
 *
 * @author vidur
 */
@NoArgsConstructor //generating a No args constructure , LOMBOC ANNOTATION LIB
@Data // setters and getters

public class User {
    int id;
    String email;
    String name;
    String password;
    UserType userType;
    int isActive;
    
    
    
    
    public void setEmail(String email){
        if(Validators.isValidEmail(email)){
            this.email = email;
        }else{
            throw new IllegalArgumentException("Invalid email format: " + email); 
        }
    }
    
    public void setName(String name){
        if(Validators.isValidUserName(name)){
            this.name = name;
        }else{
            throw new IllegalArgumentException("User Name length must between 2 and 40");
        }
    }
    
    public void setPassword(String password){
        if(Validators.isValidPassword(password)){
            this.password = password;
        }else{
              throw new IllegalArgumentException("Password length must between 2 and 8");
        }
    }
    
    
    
    
}
