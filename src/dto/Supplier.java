package dto;

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

    public boolean isValidated() {
        if (!Validators.isValidAddress(address)) {
            throw new IllegalArgumentException("Invalid address");
        }
        if (!Validators.isValidContact(contact)) {
            throw new IllegalArgumentException("Invalid contact");
        }
        if (!Validators.isValidUserName(lastName)) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (!Validators.isValidUserName(firstName)) {
            throw new IllegalArgumentException("Invalid first name");

        }
        return true;
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
