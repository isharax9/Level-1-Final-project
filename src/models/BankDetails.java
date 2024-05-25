package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {
    private int id;
    private String bankName;
    private String bankBranch;
    private String bankAccountNumber;
    private String bankAccountHolderName;

    
    
    public boolean isValidated(){
        if(!Validators.isValidUserName(bankAccountHolderName)){
            throw new IllegalArgumentException("Invalid bank account holder name");
        }
        if (!Validators.isValidBankAccountNumber(bankAccountNumber)){
             throw new IllegalArgumentException("Invalid bank account number");
        }
        if(!Validators.isValidBankBranch(bankBranch)){
             throw new IllegalArgumentException("Invalid bank branch");
        }
        if(!Validators.isValidBankName(bankName)){
            throw new IllegalArgumentException("Invalid bank name");
        }
        
        return true;
        
    }

    public static BankDetails fromResultSet(ResultSet result) throws SQLException {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(result.getInt("bank_details_id"));
        bankDetails.setBankName(result.getString("bank_name"));
        bankDetails.setBankBranch(result.getString("bank_branch"));
        bankDetails.setBankAccountNumber(result.getString("bank_account_number"));
        bankDetails.setBankAccountHolderName(result.getString("bank_account_holder_name"));
        return bankDetails;
    }
}
