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
    private String firstName;
    private String lastName;
    private String address;
    private BankDetails bankaccountDetails;    
    

    public boolean isValidated() {
        
       
        
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
