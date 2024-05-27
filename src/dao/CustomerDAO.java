/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author vidur
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.Customer;
import utils.Database;

public class CustomerDAO {

    private final String baseQuery = "SELECT * FROM customers";

    public Customer create(Customer customer) throws SQLException {
        String query = "INSERT INTO `customers` (`customer_name`,`customer_address`, `customer_contact`,`point`) VALUES (?,?,?,?,?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, customer.getCustomerName().toLowerCase());
        statement.setString(2, customer.getCustomerAddress().toLowerCase());
        statement.setString(3, customer.getCustomerContact());
        statement.setString(4, String.valueOf(customer.getPoint()));
        try {
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setCustomerId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating Customer failed, no ID obtained.");
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            throw new SQLException("Customer Adding failed");
        }
        return customer;
    }

    public Customer getByContact(String contact) throws SQLException {
        String query = baseQuery + " WHERE customer_contact = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, contact);
        var result = statement.executeQuery();
        if (result.next()) {
            return Customer.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("Customer not found in this Contact number");
        }

    }

    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        String query = baseQuery;
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        var result = statement.executeQuery();
        while (result.next()) {
            customerList.add(Customer.fromResultSet(result));
        }
        return customerList;
    }

    public void update(Customer customer) throws SQLException {
        String query = "UPDATE `customers` SET `customer_name`=? , `customer_address`=?, `customer_contact`=?,`point`=? WHERE  `customer_id`=?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, customer.getCustomerName().toLowerCase());
        statement.setString(2, customer.getCustomerAddress().toLowerCase());
        statement.setString(3, customer.getCustomerContact());
        statement.setString(4, String.valueOf(customer.getPoint()));
        statement.setInt(5, customer.getCustomerId());
        statement.executeUpdate();
        System.out.println("UPDATE Customer");
    }

    public List<Customer> search(String id, String mobile, String Address) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        List<Object> parameters = new ArrayList<>();
        boolean isFirstCondition = true;

        if (id != null && !id.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" customer_id = ?");
            parameters.add(Integer.valueOf(id));
            isFirstCondition = false;
        }

        if (mobile != null && !mobile.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" supplier_contact LIKE ?");
            parameters.add(mobile + "%");
            isFirstCondition = false;
        }

        if (Address != null && !Address.trim().isEmpty()) {
            queryBuilder.append(isFirstCondition ? " WHERE" : " AND");
            queryBuilder.append(" customer_address LIKE ?");
            parameters.add(Address + "%");
        }

        String query = queryBuilder.toString();
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }
        var result = statement.executeQuery();
        while (result.next()) {
            customerList.add(Customer.fromResultSet(result));
        }
        return customerList;
    }
}
