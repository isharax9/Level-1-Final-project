package services;

import dao.CategoryDAO;
import models.Category;
import utils.Database;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

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

    public static void main(String[] args) {
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

    }
}
