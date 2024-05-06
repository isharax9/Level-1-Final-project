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
public class BankDetails {
    
    private int id;
    private String bankName;
    private String bankBranch;
    private String bankAccountNumber;
    
    
    public void setBankName(String name){
        if(Validators.isValidBankName(name)){
            this.bankName = name;
        }else{
            throw new IllegalArgumentException(" invalid bank name ");
        }
    }
    public void setBankBranch(String name){
        if(Validators.isValidBankBranch(name)){
            this.bankBranch = name;
        }else{
            throw new IllegalArgumentException(" invalid bank branch name ");
        }
    }
    
     public void setBankAcccountNumber(String name){
        if(Validators.isValidBankAccountNumber(name)){
            this.bankAccountNumber = name;
        }else{
            throw new IllegalArgumentException(" invalid bank account number  ");
        }
    }
    
}
