/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Database;

/**
 *
 * @author vidur
 */
public class StockDAO {

    String baseQuery = """
                       SELECT * FROM stock
                       INNER JOIN grn ON grn.grn_id = stock.GRN_grn_id
                       INNER JOIN purchase_orders ON purchase_orders.po_id = grn.purchase_orders_po_id
                       INNER JOIN products ON  products.product_id = purchase_orders.products_product_id
                       INNER JOIN sub_categories ON products.sub_categories_id = sub_categories.sub_cat_id
                       INNER JOIN categories ON sub_categories.categories_id = categories.cat_id
                       INNER JOIN units ON units.unit_id = products.units_unit_id
                       INNER JOIN suppliers ON suppliers.supplier_id = purchase_orders.suppliers_supplier_id
                       INNER JOIN bank_details ON suppliers.bank_details_bank_details_id = bank_details.bank_details_id
                       """;

    public List<Stock> getAll() throws SQLException {
        List<Stock> stocks = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(baseQuery);
        var result = statement.executeQuery();
        while (result.next()) {
            stocks.add(Stock.fromResultSet(result));
        }

        return stocks;
    }

    public Stock getByGRNID(int id) throws SQLException {
        String query = baseQuery + " WHERE grn.grn_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        var reuslt = statement.executeQuery();
        if (reuslt.next()) {
            return Stock.fromResultSet(reuslt);
        }
        return null;
    }

    public Stock create(Stock stock) throws SQLException {
        String query = "INSERT INTO `stock` (`stock_barcode`,`mnf_date`, `exp_date`, `unit_price`, `available_qty`, `discount`, `GRN_grn_id`) VALUES (?,?,?,?, ?, ?,?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, stock.getStockBarcode());
        statement.setDate(2, stock.getMnfDate());
        statement.setDate(3, stock.getExpDate());
        statement.setDouble(4, stock.getUnitPrice());
        statement.setDouble(5, stock.getAvailableQty());
        statement.setDouble(6, stock.getDiscount());
        statement.setInt(7, stock.getGrn().getId());

        try {
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating stock failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    stock.setStockBarcode(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating stock failed, no ID obtained.");
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            throw new SQLException("INVALID BANK DETAILS ID");
        }
        return stock;
    }

    public Stock getByStockBarcode(int barcode) throws SQLException {
        String query = baseQuery + " WHERE stock.stock_barcode = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, barcode);
        var reuslt = statement.executeQuery();
        if (reuslt.next()) {
            return Stock.fromResultSet(reuslt);
        }
        return null;
    }

    public List<Stock> getByProductName(String productName) throws SQLException {
        String query = baseQuery + " WHERE products.product_name LIKE ?";
        List<Stock> stocks = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, productName + "%");
        var result = statement.executeQuery();
        while (result.next()) {
            stocks.add(Stock.fromResultSet(result));
        }

        return stocks;

    }

}
