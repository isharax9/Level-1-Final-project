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

    public void setBankName(String bankName) {
        if (Validators.isValidBankName(bankName)) {
            this.bankName = bankName;
        } else {
            throw new IllegalArgumentException("Invalid bank name");
        }
    }

    public void setBankBranch(String bankBranch) {
        if (Validators.isValidBankBranch(bankBranch)) {
            this.bankBranch = bankBranch;
        } else {
            throw new IllegalArgumentException("Invalid bank branch");
        }
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        if (Validators.isValidBankAccountNumber(bankAccountNumber)) {
            this.bankAccountNumber = bankAccountNumber;
        } else {
            throw new IllegalArgumentException("Invalid bank account number");
        }
    }

    public void setBankAccountHolderName(String bankAccountHolderName) {
        if (Validators.isValidUserName(bankAccountHolderName)) {
            this.bankAccountHolderName = bankAccountHolderName;
        } else {
            throw new IllegalArgumentException("Invalid bank account holder name");
        }
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
