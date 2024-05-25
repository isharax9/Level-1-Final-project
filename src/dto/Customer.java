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

    public void setCustomerName(String customerName) {
        if (Validators.isValidName(customerName)) {
            this.customerName = customerName;
        } else {
            throw new IllegalArgumentException("Invalid customer name");
        }
    }

    public void setCustomerAddress(String customerAddress) {
        if (Validators.isValidAddress(customerAddress)) {
            this.customerAddress = customerAddress;
        } else {
            throw new IllegalArgumentException("Invalid customer address");
        }
    }

    public void setCustomerContact(String customerContact) {
        if (Validators.isValidContact(customerContact)) {
            this.customerContact = customerContact;
        } else {
            throw new IllegalArgumentException("Invalid customer contact");
        }
    }

    public void setPoint(Integer point) {
        if (point == null || Validators.isValidPoint(point)) {
            this.point = point;
        } else {
            throw new IllegalArgumentException("Invalid point value");
        }
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
