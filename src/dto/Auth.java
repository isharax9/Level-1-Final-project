/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Connection;
import lombok.AllArgsConstructor;
import utils.Database;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vidur
 */
public class Auth {

    private int userID;
    private final String username;
    private final String password;
    private Employee employee;
    private final UserType authType;

    public Auth(String username, String password, UserType authType) {
        this.username = username;
        this.password = password;
        this.authType = authType;
    }

    public String getUsername() {
        return this.username;
    }
//    private String getPassword(){
//        return this.password;
//    }

    public Employee getEmployee() {
        return this.employee;
    }

    public int getUserID() {
        return this.userID;
    }

    public UserType getAuthType() {
        return this.authType;
    }

    public boolean isAuthenticated() throws Exception {

        Database instance = Database.getInstance();
        Connection connection = instance.getConnection();
        String query = "SELECT * FROM  users INNER JOIN bank_details ON users.bank_details_bank_details_id = bank_details.bank_details_id WHERE users.username = ? AND users.user_password = ? AND users.user_type = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, authType.name());
        ResultSet results = statement.executeQuery();
        if (results.next()) {
            employee = Employee.fromResultSet(results);
            userID = employee.getUserId();
            return true;
        }

        return false;
    }

}
