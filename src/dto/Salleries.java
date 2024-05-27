package dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salleries {
    private int idSalleries;
    private double amount;
    private Date paidDate;
    private String description;
    private Employee employee;

    public void setAmount(double amount) {
        if (Validators.isValidAmount(amount)) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    public void setPaidDate(Date paidDate) {
        if (Validators.isValidDatetime(paidDate)) {
            this.paidDate = paidDate;
        } else {
            throw new IllegalArgumentException("Invalid paid date");
        }
    }

    public void setDescription(String description) {
        if (Validators.isValidDescription(description)) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Invalid description");
        }
    }

    public void setEmployee(Employee user) {
        if (user != null) {
            this.employee = user;
        } else {
            throw new IllegalArgumentException("Invalid user");
        }
    }

    public static Salleries fromResultSet(ResultSet result) throws SQLException {
        Salleries salleries = new Salleries();
        salleries.setIdSalleries(result.getInt("idsalleries"));
        salleries.setAmount(result.getDouble("amount"));
        salleries.setPaidDate(result.getTimestamp("paid_date"));
        salleries.setDescription(result.getString("description"));
        salleries.setEmployee(Employee.fromResultSet(result));  // Assuming you join users in your SQL query
        return salleries;
    }
}
