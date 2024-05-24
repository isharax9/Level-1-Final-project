package dao;

import models.Category;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public void createCategory(Category category) throws SQLException {
        String query = "INSERT INTO categories (category) VALUES (?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, category.getCategory());
        statement.executeUpdate();

    }

    public List<Category> readCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            categories.add(Category.fromResultSet(results));
        }

        return categories;
    }

    public void updateCategory(Category category) throws SQLException {
        String query = "UPDATE categories SET category = ? WHERE cat_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, category.getCategory());
        statement.setInt(2, category.getId());
        statement.executeUpdate();

    }

    public Category getCategoryByName(String name) throws SQLException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        String query = "SELECT * FROM categories WHERE category = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();
        if (result.next()) {

            return Category.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("Category not found");
        }

    }
}
