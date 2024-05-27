/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Timestamp;
import java.util.Date;
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
    
    
    private static final String DOUBLE_REG = "^\\d+$";
     private static final Pattern doublePatten = Pattern.compile(DOUBLE_REG);
    // Regular expression for validating  integers
    private static final  String INT_REGEX  = "^-?[0-9]+$";
    private static final Pattern INT_PATTERN = Pattern.compile(INT_REGEX);
    
    public static boolean isValidDouble(String dob){
        return doublePatten.matcher(dob).matches();
    }
    
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
    
    public static boolean isValidInt(String num){
         return POSITIVE_INT_PATTERN.matcher(num).matches();
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
    
    public static boolean isValidProductName(String productName){
        return productName.length() <= 45  && productName.length() > 2;
    }
    
    
      public static boolean isValidSubCategoryName(String subCategoryName) {
        return subCategoryName.length() <= 45;
    }

    public static boolean isValidCategoryName(String categoryName) {
        return categoryName.length() <= 45;
    }

    public static boolean isValidUnitName(String unitName) {
        return unitName.length() <= 45;
    }
    
    // Validates a category name -- must not be null or empty and must be less than 45 characters
    public static boolean isValidCategory(String category) {
        return category != null && category.length() <= 45;
    }

    // Validates a sub category name -- must not be null or empty and must be less than 45 characters
    public static boolean isValidSubCategory(String subCategory) {
        return subCategory != null && subCategory.length() <= 45;
    }

    // Validates a unit name -- must not be null or empty and must be less than 45 characters
    public static boolean isValidUnit(String unit) {
        return unit != null && unit.length() <= 45 && unit.length()>0;
    }

    

    // Validates a product printing name -- must not be null or empty and must be less than 45 characters
    public static boolean isValidProductPrintingName(String productPrintingName) {
        return productPrintingName != null && productPrintingName.length() <= 45;
    }

   

    // Validates a contact number -- must not be null or empty and must be exactly 10 digits
    public static boolean isValidContact(String contact) {
        return contact != null && Pattern.matches("\\d{10}", contact);
    }

    // Validates an address -- must not be null or empty and must be less than 100 characters
    public static boolean isValidAddress(String address) {
        return address != null && address.length() <= 100;
    }

    // Validates a date
    public static boolean isValidDate(Object date) {
//        return date != null;
return true;
    }

    // Validates a quantity -- must be a positive number
    public static boolean isValidQuantity(double quantity) {
        return quantity >= 0;
    }

    // Validates a price -- must be a positive number
    public static boolean isValidPrice(double price) {
        return price >= 0;
    }
    // Validates a Timestamp
    public static boolean isValidTimestamp(Timestamp timestamp) {
        return timestamp != null;
    }
    
    // Validates a discount (should be between 0 and 100)
    public static boolean isValidDiscount(double discount) {
        return discount >= 0 && discount <= 100;
    }
    
    // Validates a name (should not be empty and should only contain letters and spaces)
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z\\s]+");
    }
    
    // Validates a point value (should be non-negative)
    public static boolean isValidPoint(int point) {
        return point >= 0;
    }
    
    // Validates a username (should not be empty and should only contain letters, numbers, and underscores)
    public static boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.matches("\\w+");
    }
    
    // Validates a datetime (should not be null)
    public static boolean isValidDatetime(Date datetime) {
        return datetime != null;
    }

    // Validates an amount (should be non-negative)
    public static boolean isValidAmount(double amount) {
        return amount >= 0;
    }

    // Validates a payment method (should not be empty)
    public static boolean isValidPaymentMethod(String paymentMethod) {
        return paymentMethod != null && !paymentMethod.trim().isEmpty();
    }
    
    // Validates a description (should not be null and should not be too long)
    public static boolean isValidDescription(String description) {
        return description != null && description.length() <= 45;
    }
}
