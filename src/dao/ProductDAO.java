package dao;

import models.Product;
import utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final Database database;

    public ProductDAO() {
        this.database = Database.getInstance();
    }

    public void createProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (product_name, product_printing_name, sub_cat_id, unit_id, stock_refilling_qty) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = database.getConnection(); 
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getProductPrintingName());
            statement.setInt(3, product.getSubCategory().getId());
            statement.setInt(4, product.getUnit().getId());
            statement.setDouble(5, product.getStockRefillingQty());
            statement.executeUpdate();
        }
    }

    public List<Product> readProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.product_printing_name, p.stock_refilling_qty, "
                + "sc.sub_cat_id, sc.sub_category, c.cat_id, c.category, "
                + "u.unit_id, u.unit_name "
                + "FROM products p "
                + "JOIN subcategories sc ON p.sub_cat_id = sc.sub_cat_id "
                + "JOIN categories c ON sc.cat_id = c.cat_id "
                + "JOIN units u ON p.unit_id = u.unit_id";
        try (Connection conn = database.getConnection(); 
             PreparedStatement statement = conn.prepareStatement(query); 
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                products.add(Product.fromResultSet(results));
            }
        }
        return products;
    }

    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET product_name = ?, product_printing_name = ?, sub_cat_id = ?, unit_id = ?, stock_refilling_qty = ? WHERE product_id = ?";
        try (Connection conn = database.getConnection(); 
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getProductPrintingName());
            statement.setInt(3, product.getSubCategory().getId());
            statement.setInt(4, product.getUnit().getId());
            statement.setDouble(5, product.getStockRefillingQty());
            statement.setInt(6, product.getId());
            statement.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = database.getConnection(); 
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
