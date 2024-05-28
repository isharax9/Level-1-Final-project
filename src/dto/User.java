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
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String userType;    
    private BankDetails bankDetails;

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
        if (!Validators.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email name");

        }
        if (!Validators.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password");

        }
        if (!Validators.isValidName(userType)) {
            throw new IllegalArgumentException("Invalid user type");

        }
        return true;
    }

    public static User fromResultSet(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getInt("user_id"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setAddress(result.getString("address"));
        user.setUserName(result.getString("username"));
        user.setEmail(result.getString("user_email"));
        user.setPassword(result.getString("user_password"));
        user.setUserType(result.getString("user_type"));
        user.setBankDetails(BankDetails.fromResultSet(result));
        return user;
    }
}
