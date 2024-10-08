package dao;

import dto.SubCategory;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryDAO {

    public void createSubCategory(SubCategory subCategory) throws SQLException {
        String query = "INSERT INTO `sub_categories` (`sub_category`, `categories_id`)  VALUES (?, ?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, subCategory.getSubCategory().toLowerCase());
        statement.setInt(2, subCategory.getCategory().getId());
        statement.executeUpdate();
    }

    public List<SubCategory> readSubCategories() throws SQLException {
        List<SubCategory> subCategories = new ArrayList<>();
        String query = "SELECT *  FROM sub_categories INNER JOIN categories ON categories.cat_id = sub_categories.categories_id";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            subCategories.add(SubCategory.fromResultSet(results));
        }
        return subCategories;
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException {
        String query = "UPDATE sub_categories SET sub_category = ?, categories_id = ? WHERE sub_cat_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, subCategory.getSubCategory().toLowerCase());
        statement.setInt(2, subCategory.getCategory().getId());
        statement.setInt(3, subCategory.getId());
        statement.executeUpdate();
    }

    public SubCategory getSubCategoryByName(String name) throws SQLException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("SubCategory name cannot be empty");
        }

        String query = "SELECT * FROM sub_categories INNER JOIN categories ON categories.cat_id = sub_categories.categories_id WHERE sub_categories.sub_category  = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name.toLowerCase());
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return SubCategory.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("SubCategory not found");
        }
    }

    public SubCategory getByID(int id) throws SQLException, IllegalArgumentException {
        if (id == 0) {
            throw new IllegalArgumentException("Subcategory id cannot be 0");
        }

        String query = "SELECT * FROM sub_categories INNER JOIN categories ON categories.cat_id = sub_categories.categories_id  WHERE sub_cat_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return SubCategory.fromResultSet(result);
        } else {
            throw new IllegalArgumentException("SubCategory not found");
        }
    }

    public List<SubCategory> searchSubCategoriesByName(String name) throws SQLException, IllegalArgumentException {
        List<SubCategory> subCategories = new ArrayList<>();
        String query = "SELECT * FROM sub_categories INNER JOIN categories ON categories.cat_id = sub_categories.categories_id WHERE sub_category LIKE ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name.toLowerCase() + "%");
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            subCategories.add(SubCategory.fromResultSet(results));
        }
        return subCategories;
    }
    
    public List<SubCategory> searchSubCategoriesByMainCatID(int mainCatID) throws SQLException, IllegalArgumentException {
        List<SubCategory> subCategories = new ArrayList<>();
        String query = "SELECT * FROM sub_categories INNER JOIN categories ON categories.cat_id = sub_categories.categories_id WHERE sub_categories.categories_id =  ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, mainCatID);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            subCategories.add(SubCategory.fromResultSet(results));
        }
        return subCategories;
    }
    
//    
}
