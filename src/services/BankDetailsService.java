/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.BankDetailsDAO;
import java.sql.SQLException;
import dto.BankDetails;

/**
 *
 * @author vidur
 */
public class BankDetailsService {
    BankDetailsDAO bankDetailsDAO;
    public BankDetailsService() {
        bankDetailsDAO = new BankDetailsDAO();
    }
    
    public BankDetails create(BankDetails bankDetails)throws SQLException, IllegalArgumentException {
        if(bankDetails.isValidated()){
           return bankDetailsDAO.createAndGet(bankDetails);
        }
        return null;
    }
    
    public BankDetails getByID(int id)throws SQLException, IllegalArgumentException {
        if(id <= 0){
            throw new IllegalArgumentException("Invalid ID");
        }
        
        return bankDetailsDAO.getByID(id);
    }
    
    
    
}
