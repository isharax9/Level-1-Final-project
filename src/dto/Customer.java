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
public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerContact;
    private Integer point;

    

    

    public boolean isValidated() {
        return true;
    }

    public static Customer fromResultSet(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(result.getInt("customer_id"));
        customer.setCustomerName(result.getString("customer_name"));
        customer.setCustomerAddress(result.getString("customer_address"));
        customer.setCustomerContact(result.getString("customer_contact"));
        customer.setPoint(result.getInt("point"));
        return customer;
    }
}
