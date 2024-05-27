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
public class OtherExpences {
    private int expencesId;
    private double payAmount;
    private String discription;
    private Date date;
    private Employee employee;

    public void setPayAmount(double payAmount) {
        if (Validators.isValidAmount(payAmount)) {
            this.payAmount = payAmount;
        } else {
            throw new IllegalArgumentException("Invalid pay amount");
        }
    }

    public void setDiscription(String discription) {
        if (Validators.isValidDescription(discription)) {
            this.discription = discription;
        } else {
            throw new IllegalArgumentException("Invalid description");
        }
    }

    public void setDate(Date date) {
        if (Validators.isValidDatetime(date)) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public void setEmployee(Employee user) {
        if (user != null) {
            this.employee = user;
        } else {
            throw new IllegalArgumentException("Invalid user");
        }
    }

    public static OtherExpences fromResultSet(ResultSet result) throws SQLException {
        OtherExpences otherExpences = new OtherExpences();
        otherExpences.setExpencesId(result.getInt("expences_id"));
        otherExpences.setPayAmount(result.getDouble("pay_amount"));
        otherExpences.setDiscription(result.getString("discription"));
        otherExpences.setDate(result.getTimestamp("date"));
        otherExpences.setEmployee(Employee.fromResultSet(result));  // Assuming you join users in your SQL query
        return otherExpences;
    }
}
