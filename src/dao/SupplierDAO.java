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
import models.BankDetails;
import models.Supplier;
import utils.Database;

public class SupplierDAO {

    private final String baseQuery = "SELECT * FROM suppliers  INNER JOIN bank_details ON  suppliers.bank_details_bank_details_id =  bank_details.bank_details_id ";

    public Supplier create(Supplier supplier) throws SQLException {
        String query = "INSERT INTO `suppliers` (`supplier_first_name`, `supplier_last_name`, `supplier_contact`, `supplier_address`, `bank_details_bank_details_id`) VALUES (?,?,?,?, ?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, supplier.getFirstName().toLowerCase());
        statement.setString(2, supplier.getLastName().toLowerCase());
        statement.setString(3, supplier.getContact());
        statement.setString(4, supplier.getAddress());
        statement.setInt(5, supplier.getBankDetails().getId());
        try {
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Supplier failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    supplier.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating Supplier failed, no ID obtained.");
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            throw new SQLException("INVALID BANK DETAILS ID");
        }

        return supplier;
    }

    public Supplier getByContact(String contact) throws SQLException {
        String query = baseQuery + " WHERE supplier_contact = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, contact);
        var result = statement.executeQuery();
        if (result.next()) {
            return Supplier.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("Supplier not found in this Contact number");
        }

    }

    public List<Supplier> searchByName(String name) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String query = baseQuery + " WHERE supplier_first_name LIKE ? OR supplier_last_name LIKE ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, name.toLowerCase() + "%");
        statement.setString(2, name.toLowerCase() + "%");
        var result = statement.executeQuery();
        while (result.next()) {
            suppliers.add(Supplier.fromResultSet(result));
        }

        return suppliers;
    }
    
    public Supplier getByID(int id) throws SQLException {
         String query = baseQuery + " WHERE supplier_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        var result = statement.executeQuery();
        if (result.next()) {
            return Supplier.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("Supplier not found in this id");
        }

    }
    
    public List<Supplier> searchByContact(String contact) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String query = baseQuery + " WHERE  supplier_contact LIKE ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, contact+ "%");
        var result = statement.executeQuery();
        while (result.next()) {
            suppliers.add(Supplier.fromResultSet(result));
        }

        return suppliers;
    }

}
