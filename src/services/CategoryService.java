package services;

import dao.CategoryDAO;
import dto.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public void createCategory(Category category) throws SQLException, IllegalArgumentException {
        try {
            if (category.isValidate()) {
                boolean isCategoryAlreadyExist = false;
                try {

                    Category exitCat = categoryDAO.getCategoryByName(category.getCategory());

                    if (exitCat != null) {
                        isCategoryAlreadyExist = true;
                    }
                } catch (Exception ex) {
//                    ex.printStackTrace();
                    // Log the exception if needed
                }
                if (!isCategoryAlreadyExist) {
                    categoryDAO.createCategory(category);
                } else {
                    throw new IllegalArgumentException("Category Name already exists!");
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
    
    public Category getByID(int id)throws SQLException, IllegalArgumentException {
        if (id == 0 ) {
            throw new IllegalArgumentException("Category id cannog be 0");
        }
        return  categoryDAO.getByID(id);
    }
    
    public List<Category> searchCategoriesByName(String catName)throws SQLException, IllegalArgumentException{
        if(catName == null || catName.isEmpty()){
            return new ArrayList<>();
        }
        return categoryDAO.searchCategoriesByName(catName);
    }

//    public static void main(String[] args) {
//       EXAMPLE FOR ADD CATEGORY
//        Category cat = new Category(0, "CatAAADe");
//        CategoryService categoryService = new CategoryService();
//        try {
//            categoryService.createCategory(cat);
//
//        } catch (IllegalArgumentException ex) {
//            JOptionPane.showMessageDialog(null, ex, "User Input Error", JOptionPane.ERROR_MESSAGE);
//        } catch (SQLException ex) {
////            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, ex, "DB Error", JOptionPane.ERROR_MESSAGE);
//        }

//EXAMPLE FOR GET ALL CATEGORIES
//        try {
//            CategoryService categoryService = new CategoryService();
//            List<Category> categorys = categoryService.getAllCategories();
//            System.out.println(categorys);
//        } catch (SQLException ex) {
////            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, ex, "DB Error", JOptionPane.ERROR_MESSAGE);
//        }

//EXAMPLE FOR UPDATE CATEGORY
//        try {
//            Category cat = new Category(1, "updated");
//            CategoryService categoryService = new CategoryService();
//            categoryService.updateCategory(cat);
//        } catch (IllegalArgumentException ex) {
//            JOptionPane.showMessageDialog(null, ex, "User Input Error", JOptionPane.ERROR_MESSAGE);
//        } catch (SQLException ex) {
////            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, ex, "DB Error", JOptionPane.ERROR_MESSAGE);
//        }


//EXAMPLE FOR GET BY ID CATEGORY
//        try {
//            
//            CategoryService categoryService = new CategoryService();
//            Category cat = categoryService.getByID(12);
//            System.out.println(cat);
//        } catch (IllegalArgumentException ex) {
//            JOptionPane.showMessageDialog(null, ex, "User Input Error", JOptionPane.ERROR_MESSAGE);
//        } catch (SQLException ex) {
////            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, ex, "DB Error", JOptionPane.ERROR_MESSAGE);
//        }

//EXAMPLE FOR Search CATEGORIES
//        try {
//            CategoryService categoryService = new CategoryService();
//            List<Category> categorys = categoryService.searchCategoriesByName("C");
//            System.out.println(categorys);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, ex, "DB Error", JOptionPane.ERROR_MESSAGE);
//        }

//    }
}
