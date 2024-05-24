package services;

import dao.SubCategoryDAO;
import models.SubCategory;
import utils.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryService {

    private final SubCategoryDAO subCategoryDAO;

    public SubCategoryService() {
        subCategoryDAO = new SubCategoryDAO();
    }

    public void createSubCategory(SubCategory subCategory) throws SQLException, IllegalArgumentException {
        try {
            if (subCategory.isValidate()) {
                boolean isSubCategoryAlreadyExist = false;
                try {
                    SubCategory exitSubCat = subCategoryDAO.getSubCategoryByName(subCategory.getSubCategory());
                    if (exitSubCat != null) {
                        isSubCategoryAlreadyExist = true;
                    }
                } catch (Exception ex) {
                    // Log the exception if needed
                }
                if (!isSubCategoryAlreadyExist) {
                    subCategoryDAO.createSubCategory(subCategory);
                } else {
                    throw new IllegalArgumentException("SubCategory Name already exists!");
                }
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    public List<SubCategory> getAllSubCategories() throws SQLException {
        return subCategoryDAO.readSubCategories();
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException, IllegalArgumentException {
        if (subCategory.getId() == 0) {
            throw new IllegalArgumentException("Invalid subcategory id");
        }
        if (subCategory.isValidate()) {
            subCategoryDAO.updateSubCategory(subCategory);
        }
    }

    public SubCategory getByID(int id) throws SQLException, IllegalArgumentException {
        if (id == 0) {
            throw new IllegalArgumentException("Subcategory id cannot be 0");
        }
        return subCategoryDAO.getByID(id);
    }

    public List<SubCategory> searchSubCategoriesByName(String subCatName) throws SQLException, IllegalArgumentException {
        if (subCatName == null || subCatName.isEmpty()) {
            return new ArrayList<>();
        }
        return subCategoryDAO.searchSubCategoriesByName(subCatName);
    }
}
