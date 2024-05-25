package services;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.SubCategoryDAO;
import dao.UnitDAO;
import dto.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;
    private final SubCategoryDAO subcategoryDAO;
    private final UnitDAO unitDAO;

    public ProductService() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        subcategoryDAO = new SubCategoryDAO();
        unitDAO = new UnitDAO();
    }

    public void createProduct(Product product) throws SQLException, IllegalArgumentException {
        try {
            if (product.isValidated()) {
                boolean isProductAlreadyExist = false;
                try {
                    Product existingProduct = productDAO.getProductByName(product.getProductName());
                    if (existingProduct != null) {
                        isProductAlreadyExist = true;
                    }
                } catch (Exception ex) {
//                   ex.printStackTrace();
                    // Log the exception if needed
                }

                categoryDAO.getByID(product.getSubCategory().getCategory().getId());
                subcategoryDAO.getByID(product.getSubCategory().getId());
                unitDAO.getUnitByID(product.getUnit().getId());

                if (!isProductAlreadyExist) {
                    productDAO.createProduct(product);
                } else {
                    throw new IllegalArgumentException("Product Name already exists!");
                }
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.readProducts();
    }

    public void updateProduct(Product product) throws SQLException, IllegalArgumentException {
        if (product.getId() == 0) {
            throw new IllegalArgumentException("Invalid product id");
        }
        if (product.isValidated()) {
            productDAO.updateProduct(product);
        }
    }

    public Product getProductByID(int id) throws SQLException, IllegalArgumentException {
        if (id == 0) {
            throw new IllegalArgumentException("Product id cannot be 0");
        }
        return productDAO.getProductByID(id);
    }

    public Product getProductByName(String name) throws SQLException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        return productDAO.getProductByName(name);
    }

    public List<Product> searchProductsByName(String name) throws SQLException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            return List.of();
        }
        return productDAO.searchProductsByName(name);
    }

    public List<Product> searchProductsBySubCategory(int subCategoryId) throws SQLException, IllegalArgumentException {
        if (subCategoryId == 0) {
            throw new IllegalArgumentException("Sub category id cannot be 0");
        }
        return productDAO.searchProductsBySubCategory(subCategoryId);
    }

    public List<Product> searchProductsByCategory(int categoryId) throws SQLException, IllegalArgumentException {
        if (categoryId == 0) {
            throw new IllegalArgumentException("Category id cannot be 0");
        }
        return productDAO.searchProductsByCategory(categoryId);
    }

    public List<Product> searchProductsByUnit(int unitId) throws SQLException, IllegalArgumentException {
        if (unitId == 0) {
            throw new IllegalArgumentException("Unit id cannot be 0");
        }
        return productDAO.searchProductsByUnit(unitId);
    }

//    public static void main(String[] args) {
//        ProductService productService = new ProductService();

        // Example for adding a new Product
//        Product productToAdd = new Product();
//        productToAdd.setProductName("New Product 122");
//        productToAdd.setProductPrintingName("New Product Printing Name");
//        productToAdd.setSubCategory(new SubCategory(7, "Sample SubCategory", new Category(1, "Sample Category")));
//        productToAdd.setUnit(new Unit(1, "Sample Unit"));
//        productToAdd.setStockRefillingQty(20.0);
//        try {
//            productService.createProduct(productToAdd);
//            System.out.println("Product added successfully!");
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
        // Example for getting a Product by its id
//        try {
//            Product foundProduct = productService.getProductByID(6); // Assuming this is the id of the Product you want to find
//            System.out.println("Found Product: " + foundProduct);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
        // Example for searching Products by name
//        String productName = "new";
//        try {
//            List<Product> searchResults = productService.searchProductsByName(productName);
//            System.out.println("Search results for '" + productName + "': " + searchResults);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
// Example for searching Products by SubCategory
//        int subCategoryId = 7; // Assuming this is the id of the SubCategory you want to search by
//        try {
//            List<Product> searchResults = productService.searchProductsBySubCategory(subCategoryId);
//            System.out.println("Search results for SubCategory ID '" + subCategoryId + "': " + searchResults);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
// Example for searching Products by Category
//        int categoryId = 1; // Assuming this is the id of the Category you want to search by
//        try {
//            List<Product> searchResults = productService.searchProductsByCategory(categoryId);
//            System.out.println("Search results for Category ID '" + categoryId + "': " + searchResults);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }

// Example for searching Products by Unit
//        int unitId = 55; // Assuming this is the id of the Unit you want to search by
//        try {
//            List<Product> searchResults = productService.searchProductsByUnit(unitId);
//            System.out.println("Search results for Unit ID '" + unitId + "': " + searchResults);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//
//    }
}
