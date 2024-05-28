/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author vidur
 */
import dto.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Database;

public class UserDAO {

    private final String baseQuery = "SELECT * FROM users  INNER JOIN bank_details ON  users.bank_details_bank_details_id =  bank_details.bank_details_id ";

    public Employee create(Employee user,String username,String password) throws SQLException {
        String query = "INSERT INTO `users` ("
                + "`username`, "
                + "`user_email`,"
                + " `user_password`,"
                + " `user_type`, "
                + "`first_name`,"
                + "`last_name`,"
                + "`address`,"
                + "`bank_details_bank_details_id`"
                + ") VALUES (?,?,?,?, ?,?,?,?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, username);
        statement.setString(2, user.getUserEmail());
        statement.setString(3, password);
        statement.setString(4, user.getUserType().toString());
        statement.setString(5, user.getFirstName().toLowerCase());
        statement.setString(6, user.getLastName().toLowerCase());
        statement.setString(7, user.getAddress().toLowerCase());
        statement.setInt(8, user.getBankaccountDetails().getId());
        try {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating User failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating User failed, no ID obtained.");
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            throw new SQLException("INVALID BANK DETAILS ID");
        }
        return user;
    }

    public Employee getByUserName(String userName) throws SQLException {
        String query = baseQuery + " WHERE username = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, userName);
        var result = statement.executeQuery();
        if (result.next()) {
            return Employee.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("User not found in this Name");
        }

    }

    public Employee getByEmail(String email) throws SQLException {
        String query = baseQuery + " WHERE user_email = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        var result = statement.executeQuery();
        if (result.next()) {
            return Employee.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("User not found in this Email");
        }

    }

    public List<Employee> searchByName(String name) throws SQLException {
        List<Employee> userList = new ArrayList<>();
        String query = baseQuery + " WHERE first_name LIKE ? OR last_name LIKE ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, name.toLowerCase() + "%");
        statement.setString(2, name.toLowerCase() + "%");
        var result = statement.executeQuery();
        while (result.next()) {
            userList.add(Employee.fromResultSet(result));
        }
        return userList;
    }

    public List<Employee> searchByUserName(String UserName) throws SQLException {
        List<Employee> userList = new ArrayList<>();
        String query = baseQuery + " WHERE username = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, UserName.toLowerCase() + "%");
        var result = statement.executeQuery();
        while (result.next()) {
            userList.add(Employee.fromResultSet(result));
        }
        return userList;
    }

    public Employee getByID(int id) throws SQLException {
        String query = baseQuery + " WHERE user _id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        var result = statement.executeQuery();
        if (result.next()) {
            return Employee.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("User not found in this id");
        }

    }

    public List<Employee> getAll() throws SQLException {
        List<Employee> userList = new ArrayList<>();
        String query = baseQuery;
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        var result = statement.executeQuery();
        while (result.next()) {
            userList.add(Employee.fromResultSet(result));
        }

        return userList;
    }

    public void update(Employee employee,String username,String password) throws SQLException {
        String query = "UPDATE `users` SET "
                + "`first_name`=? ,"
                + " `last_name`=?, "
                + "`username`=?, "
                + "`user_password`=?,"
                + "`user_type`=?,"
               
                + "`address`=? "
                + "WHERE  `user_id`=?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, employee.getFirstName().toLowerCase());
        statement.setString(2, employee.getLastName().toLowerCase());
        statement.setString(3, username);
        statement.setString(4, password);
        statement.setString(5, employee.getUserType().toString());
        statement.setString(6, employee.getAddress());
        statement.setInt(7, employee.getUserId());
        statement.executeUpdate();
        System.out.println("UPDATE USER "+employee.getBankaccountDetails());

    }

    public List<Employee> search(String id, String firstName, String lastName, String userName,String userEmail, String bankAccountNumber) throws SQLException {
        List<Employee> userList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        List<Object> parameters = new ArrayList<>();
        boolean isFirstCondition = true;

        if (id != null && !id.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" user_id = ?");
            parameters.add(Integer.valueOf(id));
            isFirstCondition = false;
        }

        if (firstName != null && !firstName.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" first_name LIKE ?");
            parameters.add(firstName.toLowerCase() + "%");
            isFirstCondition = false;
        }

        if (lastName != null && !lastName.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" last_name LIKE ?");
            parameters.add(lastName.toLowerCase() + "%");
            isFirstCondition = false;
        }

        if (userName != null && !userName.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" username LIKE ?");
            parameters.add(userName + "%");
            isFirstCondition = false;
        }

        if (userEmail != null && !userEmail.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" user_email LIKE ?");
            parameters.add(userEmail + "%");
            isFirstCondition = false;
        }

        if (bankAccountNumber != null && !bankAccountNumber.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" bank_details.bank_account_number LIKE ?");
            parameters.add(bankAccountNumber + "%");
        }

        String query = queryBuilder.toString();
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }
        var result = statement.executeQuery();
        while (result.next()) {
            userList.add(Employee.fromResultSet(result));
        }
        return userList;
    }
}
