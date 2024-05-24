/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.CategoryDAO;
import models.Category;
import models.Category;
import utils.Database;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author vidur
 */
public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public void createCategory(Category category) throws SQLException, IllegalArgumentException {
        try {
            if (category.isValidate()) {
                
//                check already Exist Cat name
                boolean isCategoryAlreadyExist = false;
                try{
                    var exitCat = categoryDAO.getCategoryByName(category.getCategory());
                    if(exitCat !=null){
                        isCategoryAlreadyExist = true;
                    }
                    
                }catch(Exception ex){
                    
                }
                if(!isCategoryAlreadyExist){
                    categoryDAO.createCategory(category);
                }else{
                    throw new IllegalArgumentException("Category Name is already Exist!");
                }
                
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }

    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryDAO.readCategories();
    }

    public void updateCategory(Category category) throws SQLException, IllegalArgumentException {
        if (category.getId() == 0) {
            throw new IllegalArgumentException("Invalid category id");
        }
        if (category.isValidate()) {
            categoryDAO.updateCategory(category);
        }

    }

    public static void main(String[] args) {

        Category cat = new Category(0, "CatAAAD");
        CategoryService categoryService = new CategoryService();
        try {
            categoryService.createCategory(cat);
            System.out.println("DONE");
        }catch(IllegalArgumentException ex){
            
            JOptionPane.showMessageDialog(null,ex,"UserInputError",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException ex){
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,ex,"DB Error",JOptionPane.ERROR_MESSAGE);
        }

    }

}
