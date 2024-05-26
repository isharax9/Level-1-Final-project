/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.PurchaseOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Database;

/**
 *
 * @author vidur
 */
public class PurchaseOrdersDAO {
    
    public PurchaseOrder create(PurchaseOrder po) throws SQLException {
        String query = "INSERT INTO `purchase_orders` (`ordered_date`, `order_qty`, `products_product_id`, `wholesale_unit_price`, `paid_amount`, `suppliers_supplier_id`) VALUES (?,?,?,?,?,?)";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setDate(1, po.getOrderedDate());
        statement.setDouble(2, po.getOrderQty());
        statement.setInt(3, po.getProduct().getId());
        statement.setDouble(4, po.getWholesaleUnitPrice());
        statement.setDouble(5, po.getPaidAmount());
        statement.setDouble(6, po.getSupplier().getId());

        int affectedRows = statement.executeUpdate();
        try {

            if (affectedRows == 0) {
                throw new SQLException("Creating Supplier failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    po.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating Supplier failed, no ID obtained.");
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            throw new SQLException("INVALID BANK DETAILS ID");
        }

        return po;
    }

    public void update(PurchaseOrder po) throws SQLException {
        String query = "UPDATE `purchase_orders` SET `order_qty`=?, `products_product_id`=?, `wholesale_unit_price`=?, `paid_amount`=?, `suppliers_supplier_id`=? WHERE  `po_id`=?;";

        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setDouble(1, po.getOrderQty());
        statement.setInt(2, po.getProduct().getId());
        statement.setDouble(3, po.getWholesaleUnitPrice());
        statement.setDouble(4, po.getPaidAmount());
        statement.setDouble(5, po.getSupplier().getId());
        statement.setInt(6, po.getId());
        statement.executeUpdate();

    }

    
}
