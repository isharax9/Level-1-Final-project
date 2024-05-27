/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BankDetails;
import dto.GRN;
import dto.PurchaseOrder;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Database;

/**
 *
 * @author vidur
 */
public class GrnDAO {
    
    String baseQuery = """
                       SELECT * FROM grn
                       INNER JOIN purchase_orders ON purchase_orders.po_id = grn.purchase_orders_po_id
                       INNER JOIN products ON  products.product_id = purchase_orders.products_product_id
                       INNER JOIN sub_categories ON products.sub_categories_id = sub_categories.sub_cat_id
                       INNER JOIN categories ON sub_categories.categories_id = categories.cat_id
                       INNER JOIN units ON units.unit_id = products.units_unit_id
                       INNER JOIN suppliers ON suppliers.supplier_id = purchase_orders.suppliers_supplier_id
                       INNER JOIN bank_details ON suppliers.bank_details_bank_details_id = bank_details.bank_details_id
                       
                      """;

    public GRN create(GRN grn) throws SQLException {
        String query = "INSERT INTO `grn` (`grn_date`, `purchase_orders_po_id`) VALUES (?, ?);";
        Timestamp timestamp = grn.getGrnDate();
        Date sqlDate = new Date(timestamp.getTime());
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        System.out.println(sqlDate+" : "+grn.getPurchaseOrder().getId());
        statement.setDate(1, sqlDate);
        statement.setInt(2, grn.getPurchaseOrder().getId());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("grn details failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                grn.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("grn details failed, no ID obtained.");
            }
        }

        return grn;
    }

    public GRN getByPOID(int id)throws SQLException  {
        String query = baseQuery+ " WHERE purchase_orders.po_id = ? ";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,id);
        var result = statement.executeQuery();
        if (result.next()) {
            return GRN.fromResultSet(result);
        }
        
        return null;
    }

   
}
