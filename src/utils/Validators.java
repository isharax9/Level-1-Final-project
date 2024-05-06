/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author vidur all validations are in this class , EX : need to validate Email
 * ? call isValidEmail(String Email)
 */
public class Validators {

    // Regular expression for a basic email validation pattern
    private static final String EMAIL_REGEX
            = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"; 
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    // Regular expression for validating positive integers
    private static final  String POSITIVE_INT_REGEX  = "^[0-9]+$";
    private static final Pattern POSITIVE_INT_PATTERN = Pattern.compile(POSITIVE_INT_REGEX);
    
    // Regular expression for validating  integers
    private static final  String INT_REGEX  = "^-?[0-9]+$";
    private static final Pattern INT_PATTERN = Pattern.compile(INT_REGEX);
    
    
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
    public static boolean isValidUserName(String name) {
        return !(name.length() > 40 || name.length() < 2);
    }

    //conditions : password char length between 2 and 8
    //returns : valid return TRUE,  invalid resutn False 
    public static boolean isValidPassword(String name) {
        return !(name.length() > 8 || name.length() < 2);
    }

    //conditions : password char length be 10
    //returns : valid return TRUE,  invalid resutn False
    public static boolean isValidMobile(String mobile) {
        return mobile.length() == 10;
    }

    //conditions : bank name char length between 1 and 44
    //returns : valid return TRUE,  invalid resutn False
    public static boolean isValidBankName(String text) {
        return text.length() < 45 && text.length() > 0;
        
   
    }
    
    //conditions : bank branch name char length between 1 and 44
    //returns : valid return TRUE,  invalid resutn False
    public static boolean isValidBankBranch(String text) {
         return text.length() < 45 && text.length() > 0;
    }

    //conditions : bank number char length between 1 and 44 AND banct account number must to be int
    //returns : valid return TRUE,  invalid resutn False
    public static boolean isValidBankAccountNumber(String text) {
        if(text.length() >= 45){
            return false;
        }
        if(text.length() == 0){
            return false;
        }
        return POSITIVE_INT_PATTERN.matcher(text).matches();
    }

}
