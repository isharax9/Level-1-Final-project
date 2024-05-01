/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author vidur
 * all validations are in this class , 
 * EX : need to validate Email ? call isValidEmail(String Email)
 */
public class Validators {

    // Regular expression for a basic email validation pattern
    private static final String EMAIL_REGEX
            = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
   
    //conditions  : email not null, xxx@xx.xx type , maximum 100 chars 
    //returns : valid return TRUE,  invalid resutn False
    public static boolean isValidEmail(String email) {
        // Check if the email is null
        if (email == null) {
            return false;
        }
        
        // Check if the email exceeds 100 characters
        if (email.length() > 100) {
            return false;
        }

        // Validate the email format using regex
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    //conditions : name char length between 2 and 40
     //returns : valid return TRUE,  invalid resutn False 
    public static boolean isValidUserName(String name){
        return !(name.length() > 40 || name.length() < 2);
    }
    
    //conditions : password char length between 2 and 8
     //returns : valid return TRUE,  invalid resutn False 
    public static boolean isValidPassword(String name){
        return !(name.length() > 8 || name.length() < 2);
    }
    
    
    
    
    

}
