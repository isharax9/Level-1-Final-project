/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import lombok.AllArgsConstructor;

/**
 *
 * @author vidur
 */
public class Auth {
    private int userID;
    private String username;
    private String password;
    private Employee employee;
    private UserType authType;
    
    public Auth(String username,String password, UserType authType){
        this.username = username;
        this.password = password;
        this.authType = authType;
    }
    
    private String getUsername(){
        return this.username;
    }
//    private String getPassword(){
//        return this.password;
//    }
    private Employee getEmployee(){
        return this.employee;
    }
    private int getUserID(){
        return this.userID;
    }
    private UserType getAuthType(){
        return this.authType;
    }
    
    private boolean isAuthenticated(){
        return false;
    }
    
    
}
