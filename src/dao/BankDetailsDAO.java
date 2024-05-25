/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.BankDetails;
import utils.Database;

/**
 *
 * @author vidur
 */
public class BankDetailsDAO {

    public BankDetails createAndGet(BankDetails bankDetails) throws SQLException {
        String query = "INSERT INTO `bank_details` (`bank_name`, `bank_branch`, `bank_account_number`, `bank_account_holder_name`) VALUES (?,?,?, ?);";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, bankDetails.getBankName());
        statement.setString(2, bankDetails.getBankBranch());
        statement.setString(3, bankDetails.getBankAccountNumber());
        statement.setString(4, bankDetails.getBankAccountHolderName());

        int affectedRows = statement.executeUpdate();
        
        if (affectedRows == 0) {
            throw new SQLException("Creating bank details failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                bankDetails.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating bank details failed, no ID obtained.");
            }
        }
        
        return bankDetails;
    }
    public BankDetails getByID(int id)throws SQLException{
        String query = "SELECT * FROM bank_details WHERE bank_details.bank_details_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        var result = statement.executeQuery();
        if(result.next()){
            return BankDetails.fromResultSet(result);
        }else{
            throw new IllegalArgumentException("BANK DETAILS NOT FOUNT IN THIS ID");
        }
        
    }


}
