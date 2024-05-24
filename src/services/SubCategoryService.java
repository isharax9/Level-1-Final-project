package services;

import dao.CategoryDAO;
import dao.SubCategoryDAO;
import models.SubCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Category;

public class SubCategoryService {

    private final SubCategoryDAO subCategoryDAO;
    private final CategoryDAO categoryDAO;

    public SubCategoryService() {
        subCategoryDAO = new SubCategoryDAO();
        categoryDAO = new CategoryDAO();
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
                    System.out.println(ex.getMessage());
                    // Log the exception if needed
                }

//                check main category is alredy have
                try{
                    Category mainCat =categoryDAO.getByID(subCategory.getCategory().getId());
                    if(mainCat == null){
                        throw new IllegalArgumentException("Main category not found");
                    }

                } catch (Exception ex){
                    throw ex;
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
        SubCategory subCat = subCategoryDAO.getByID(subCategory.getId());
        if(subCat == null){
            throw new IllegalArgumentException("Sub Category not found for update");
        }
        
        Category mainCat = categoryDAO.getByID(subCategory.getCategory().getId());
        if(mainCat == null){
            throw new IllegalArgumentException("Main Category not found for update");
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
     public List<SubCategory> searchSubCategoriesByMainCatID(int mainCatID) throws SQLException, IllegalArgumentException {
        if (mainCatID <= 0 ) {
            return new ArrayList<>();
        }
        return subCategoryDAO.searchSubCategoriesByMainCatID(mainCatID);
    }

//    public static void main(String[] args) {
//        SubCategoryService subCategoryService = new SubCategoryService();

        // Example for adding a new SubCategory
//        SubCategory subCategoryToAdd = new SubCategory();
//        subCategoryToAdd.setSubCategory("New SubCategory5");
//        subCategoryToAdd.setCategory(new Category(1, "Sample Category")); // Assuming you have a Category with id 1
//        try {
//            subCategoryService.createSubCategory(subCategoryToAdd);
//            System.out.println("SubCategory added successfully!");
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }


// Example for getting all SubCategories
//        try {
//            List<SubCategory> allSubCategories = subCategoryService.getAllSubCategories();
//            System.out.println("All SubCategories: " + allSubCategories);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

 // Example for updating a SubCategory
//        SubCategory subCategoryToUpdate = new SubCategory();
//        subCategoryToUpdate.setId(6); // Assuming this is the id of the SubCategory you want to update
//        subCategoryToUpdate.setSubCategory("Updated SubCategory");
//        subCategoryToUpdate.setCategory(new Category(2, "Sample Category")); // Assuming you have a Category with id 1
//        try {
//            subCategoryService.updateSubCategory(subCategoryToUpdate);
//            System.out.println("SubCategory updated successfully!");
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }

// Example for getting a SubCategory by its id
//        try {
//            SubCategory foundSubCategory = subCategoryService.getByID(8); // Assuming this is the id of the SubCategory you want to find
//            System.out.println("Found SubCategory: " + foundSubCategory);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
        
        // Example for searching SubCategories by name
//        String subCategoryName = "updated";
//        try {
//            List<SubCategory> searchResults = subCategoryService.searchSubCategoriesByName(subCategoryName);
//            System.out.println("Search results for '" + subCategoryName + "': " + searchResults);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }

// Example for getting a SubCategory by its mainCategory id
//        try {
//            List<SubCategory> foundSubCategories = subCategoryService.searchSubCategoriesByMainCatID(1); // Assuming this is the id of the SubCategory you want to find
//            System.out.println("Found SubCategory: " + foundSubCategories);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }
}
