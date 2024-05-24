/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.cj.xdevapi.Statement;
import models.Category;
import utils.Database;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vidur
 */
public class CategoryDAO {

   
    public void createCategory(Category category) throws SQLException {
         Database database = Database.getInstance();
        String query = "INSERT INTO categories (category) VALUES (?)";
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, category.getCategory());
        statement.executeUpdate();
    }

    public List<Category> readCategories() throws SQLException {
         Database database = Database.getInstance();
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            categories.add(Category.fromResultSet(results));
        }
        return categories;
    }

    public void updateCategory(Category category) throws SQLException {
         Database database = Database.getInstance();
        String query = "UPDATE categories SET category = ? WHERE cat_id = ?";
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, category.getCategory());
        statement.setInt(2, category.getId());
        ResultSet results = statement.executeQuery();
    }

    public Category getCategoryByName(String name) throws SQLException, IllegalArgumentException {
        Database database = Database.getInstance();
       
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        String query = "SELECT * FROM categories WHERE category = ?";

        try (Connection conn = database.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, name);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Category.fromResultSet(result);
                } else {
                    throw new IllegalArgumentException("Category not found");
                }
            }
        }
    }
}
