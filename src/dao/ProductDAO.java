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
    private final String mainSelectQuery = "SELECT * FROM products  INNER JOIN sub_categories ON sub_categories.sub_cat_id = products.sub_categories_id INNER JOIN categories ON categories.cat_id = sub_categories.categories_id INNER JOIN units ON units.unit_id = products.units_unit_id ";

    public void createProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (product_name, product_printing_name, sub_categories_id, units_unit_id, stock_refilling_qty) VALUES (?, ?, ?, ?, ?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, product.getProductName().toLowerCase());
        statement.setString(2, product.getProductPrintingName().toLowerCase());
        statement.setInt(3, product.getSubCategory().getId());
        statement.setInt(4, product.getUnit().getId());
        statement.setDouble(5, product.getStockRefillingQty());
        statement.executeUpdate();
    }

    public List<Product> readProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = mainSelectQuery;
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            products.add(Product.fromResultSet(results));
        }
        return products;
    }

    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET product_name = ?, product_printing_name = ?, sub_categories_id = ?, units_unit_id = ?, stock_refilling_qty = ? WHERE product_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, product.getProductName().toLowerCase());
        statement.setString(2, product.getProductPrintingName().toLowerCase());
        statement.setInt(3, product.getSubCategory().getId());
        statement.setInt(4, product.getUnit().getId());
        statement.setDouble(5, product.getStockRefillingQty());
        statement.setInt(6, product.getId());
        statement.executeUpdate();
    }

    public Product getProductByID(int id) throws SQLException {
        String query = mainSelectQuery+" WHERE product_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        try (ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                return Product.fromResultSet(result);
            } else {
                throw new IllegalArgumentException("Product not found");
            }
        }
    }
    
    public Product getProductByName(String name) throws SQLException {
        String query = mainSelectQuery+" WHERE product_name = ?";
            Connection conn = Database.getInstance().getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name.toLowerCase());
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Product.fromResultSet(result);
                } else {
                    throw new IllegalArgumentException("Product not found");
                }
        }
    }
    
    public List<Product> searchProductsByName(String name) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = mainSelectQuery+" WHERE product_name LIKE ?";
            Connection conn = Database.getInstance().getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name.toLowerCase() + "%");
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    products.add(Product.fromResultSet(results));
                }
        }
        return products;
    }
    
    public List<Product> searchProductsBySubCategory(int subCategoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = mainSelectQuery+" WHERE sub_categories_id = ?";
            Connection conn = Database.getInstance().getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, subCategoryId);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    products.add(Product.fromResultSet(results));
                }
        }
        return products;
    }
    
    
    public List<Product> searchProductsByCategory(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = mainSelectQuery+" WHERE categories_id = ?";
            Connection conn = Database.getInstance().getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, categoryId);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    products.add(Product.fromResultSet(results));
                }
            }
        return products;
    }
    
    public List<Product> searchProductsByUnit(int unitId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = mainSelectQuery+" WHERE units_unit_id = ?";
            Connection conn = Database.getInstance().getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, unitId);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    products.add(Product.fromResultSet(results));
                }
        }
        return products;
    }

    
    
    
    
}
