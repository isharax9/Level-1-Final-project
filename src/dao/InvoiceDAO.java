/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Invoice;
import dto.InvoiceItem;
import dto.PaymentTypes;
import gui.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Database;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 *
 * @author User
 */
public class InvoiceDAO {
    
    private InvoiceItem addInvoiceItem(int invoiceID , InvoiceItem item) throws SQLException{
        
        
        String invoiceQuery = "INSERT INTO `invoice_item` (`stock_stock_barcode`, `invoices_invoice_id`, `invoice_qty`, `invoice_discount`) VALUES (?, ?, ?, ?);";

        var con = Database.getInstance().getConnection();
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(invoiceQuery);

       
        statement.setInt(1, item.getStock().getStockBarcode());
        statement.setDouble(2,invoiceID);
        statement.setDouble(3,item.getInvoiceItemQty());
        statement.setDouble(4, item.getInvoiceItemDiscount());
        int affectedRows = statement.executeUpdate();
        
        return item;
    }

    public Invoice create(Invoice invoice) throws SQLException {
        String invoiceQuery = "INSERT INTO `invoices` (`invoice_datetime`, `paid_amount`, `payment_method`, `users_user_id`, `customers_customer_id`) VALUES (?, ?,?, ?,?);";

        var con = Database.getInstance().getConnection();
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(invoiceQuery, PreparedStatement.RETURN_GENERATED_KEYS);

        // Convert LocalDateTime to Timestamp
        LocalDateTime invoiceDateTime = invoice.getInvoiceDate();
        Timestamp timestamp = Timestamp.valueOf(invoiceDateTime);
        statement.setTimestamp(1, timestamp);
        statement.setDouble(2,invoice.getPaidAmount());
        statement.setString(3, invoice.getPaidAmount() == 0 ? PaymentTypes.card.toString() : PaymentTypes.cash.toString());
        statement.setInt(4, Login.auth.getUserID());
        statement.setInt(5,1);
        
        try {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("invoice creating failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    invoice.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating invoice failed, no ID obtained.");
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            throw new SQLException("Invalid invoice  ID");
        }
        
        for (InvoiceItem i : invoice.getItems()){
            addInvoiceItem(invoice.getId(),i);
        }

        return invoice;
    }

}
