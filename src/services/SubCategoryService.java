/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.SubCategoryDAO;
import models.SubCategory;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vidur
 */
public class SubCategoryService {
    private SubCategoryDAO subCategoryDAO;

    public SubCategoryService() {
        this.subCategoryDAO = new SubCategoryDAO();
    }
    public void createSubCategory(SubCategory subCategory) throws SQLException, IllegalArgumentException {
        if (subCategory.getCategory() == null || subCategory.getCategory().getId() == 0) {
            throw new IllegalArgumentException("Invalid category id");
        }
        if (subCategory.getSubCategory() == null || subCategory.getSubCategory().isEmpty()) {
            throw new IllegalArgumentException("SubCategory name cannot be empty");
        }
        subCategoryDAO.createSubCategory(subCategory);
    }
    public List<SubCategory> getAllSubCategories() throws SQLException {
        return subCategoryDAO.readSubCategories();
    }
    public void updateSubCategory(SubCategory subCategory) throws SQLException, IllegalArgumentException {
        if (subCategory.getId() == 0) {
            throw new IllegalArgumentException("Invalid subcategory id");
        }
        if (subCategory.getCategory() == null || subCategory.getCategory().getId() == 0) {
            throw new IllegalArgumentException("Invalid category id");
        }
        if (subCategory.getSubCategory() == null || subCategory.getSubCategory().isEmpty()) {
            throw new IllegalArgumentException("SubCategory name cannot be empty");
        }
        subCategoryDAO.updateSubCategory(subCategory);
    }

   
}