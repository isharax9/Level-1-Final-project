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
    Database database ; //singolton Instance of 

    public CategoryDAO() {
        this.database = Database.getInstance();
    }
    
    public void createCategory(Category category) throws SQLException {
        String query = "INSERT INTO categories (category) VALUES (?)";
        Connection conn = database.getConnection();
         PreparedStatement statement = conn.prepareStatement(query);
         statement.setString(1,category.getCategory());
         ResultSet results = statement.executeQuery();
    }
    
    public List<Category> readCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet results = statement.executeQuery();
        while(results.next()){
            categories.add(Category.fromResultSet(results));
        }
        return categories;        
    }
    
    
     public void updateCategory(Category category) throws SQLException {
        String query = "UPDATE categories SET category = ? WHERE cat_id = ?";
         Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, category.getCategory());
        statement.setInt(2,category.getId());
        ResultSet results = statement.executeQuery();
     }
}
