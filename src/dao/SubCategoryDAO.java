/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.SubCategory;
import utils.Database;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author vidur
 */
public class SubCategoryDAO {
    Database database ; //singolton Instance of 

    public SubCategoryDAO() {
        this.database = Database.getInstance();
        
    }
    
    public void createSubCategory(SubCategory subCategory) throws SQLException {
        String query = "INSERT INTO subcategories (sub_category, cat_id) VALUES (?, ?)";
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, subCategory.getSubCategory());
        statement.setInt(2, subCategory.getCategory().getId());
        statement.executeUpdate();
    }
    public List<SubCategory> readSubCategories() throws SQLException {
        List<SubCategory> subCategories = new ArrayList<>();
        String query = "SELECT sc.sub_cat_id, sc.sub_category, c.cat_id, c.category FROM subcategories sc JOIN categories c ON sc.cat_id = c.cat_id";
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            subCategories.add(SubCategory.fromResultSet(results));
        }
        return subCategories;
    }
    public void updateSubCategory(SubCategory subCategory) throws SQLException {
        String query = "UPDATE subcategories SET sub_category = ?, cat_id = ? WHERE sub_cat_id = ?";
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, subCategory.getSubCategory());
        statement.setInt(2, subCategory.getCategory().getId());
        statement.setInt(3, subCategory.getId());
        statement.executeUpdate();
    }
    
    
    
    
    
}
