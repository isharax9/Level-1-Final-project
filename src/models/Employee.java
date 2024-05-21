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
public class Employee {
    private int userId;
    private String username;
    private String userEmail;
    private String userPassword;
    private UserType userType;

    public void setUsername(String username) {
        if (Validators.isValidUsername(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public void setUserEmail(String userEmail) {
        if (Validators.isValidEmail(userEmail)) {
            this.userEmail = userEmail;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void setUserPassword(String userPassword) {
        if (Validators.isValidPassword(userPassword)) {
            this.userPassword = userPassword;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
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
        user.setUsername(result.getString("username"));
        user.setUserEmail(result.getString("user_email"));
        user.setUserPassword(result.getString("user_password"));
        user.setUserType(UserType.valueOf(result.getString("user_type")));
        return user;
    }
}
