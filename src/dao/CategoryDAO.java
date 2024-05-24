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
        statement.setString(1, category.getCategory().toLowerCase());
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
        statement.setString(1, category.getCategory().toLowerCase());
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
        statement.setString(1, name.toLowerCase());
        ResultSet result = statement.executeQuery();
        if (result.next()) {

            return Category.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("Category not found");
        }

    }
    
    public Category getByID(int id)throws SQLException, IllegalArgumentException{
         if (id == 0 ) {
            throw new IllegalArgumentException("Category id cannog be 0");
        }

        String query = "SELECT * FROM categories WHERE cat_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {

            return Category.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("Category not found");
        }
    }
    
    public List<Category> searchCategoriesByName(String name)throws SQLException, IllegalArgumentException{
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories WHERE categories.category LIKE ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name.toLowerCase()+"%");
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            categories.add(Category.fromResultSet(results));
        }

        return categories;
    }
}
