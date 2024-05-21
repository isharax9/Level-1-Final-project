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
public class Supplier {
    private int id;
    private String firstName;
    private String lastName;
    private String contact;
    private String address;
    private BankDetails bankDetails;

    public void setFirstName(String firstName) {
        if (Validators.isValidUserName(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Invalid first name");
        }
    }

    public void setLastName(String lastName) {
        if (Validators.isValidUserName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Invalid last name");
        }
    }

    public void setContact(String contact) {
        if (Validators.isValidContact(contact)) {
            this.contact = contact;
        } else {
            throw new IllegalArgumentException("Invalid contact");
        }
    }

    public void setAddress(String address) {
        if (Validators.isValidAddress(address)) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Invalid address");
        }
    }

    public static Supplier fromResultSet(ResultSet result) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(result.getInt("supplier_id"));
        supplier.setFirstName(result.getString("supplier_first_name"));
        supplier.setLastName(result.getString("supplier_last_name"));
        supplier.setContact(result.getString("supplier_contact"));
        supplier.setAddress(result.getString("supplier_address"));
        supplier.setBankDetails(BankDetails.fromResultSet(result));
        return supplier;
    }
}
