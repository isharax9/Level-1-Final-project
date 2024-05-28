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
public class Employee {
    private int userId;
    private String userEmail;
    private UserType userType;
    private String userName;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private BankDetails bankaccountDetails;    
    

    public boolean isValidated() {
        if (!Validators.isValidAddress(address)) {
            throw new IllegalArgumentException("Invalid address");
        }
        if (!Validators.isValidName(userName)) {
            throw new IllegalArgumentException("Invalid User Name");
        }
        if (!Validators.isValidUserName(lastName)) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (!Validators.isValidUserName(firstName)) {
            throw new IllegalArgumentException("Invalid first name");

        }
        if (!Validators.isValidEmail(userEmail)) {
            throw new IllegalArgumentException("Invalid email name");

        }
        if (!Validators.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password");

        }
        if (!Validators.isValidName(userType.toString())) {
            throw new IllegalArgumentException("Invalid user type");

        }
        return true;
    }

    
    
    

//    public void setUserType(String userType) {
//        if (Validators.isValidUserType(userType)) {
//            this.userType = userType;
//        } else {
//            throw new IllegalArgumentException("Invalid user type");
//        }
//    }

    public static Employee fromResultSet(ResultSet result) throws SQLException {
        Employee user = new Employee();
        user.setUserId(result.getInt("user_id"));
        user.setUserEmail(result.getString("user_email"));
        user.setUserType(UserType.valueOf(result.getString("user_type")));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setAddress(result.getString("address"));
        user.setBankaccountDetails(BankDetails.fromResultSet(result));
        return user;
    }
}
