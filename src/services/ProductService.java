package services;

import dao.ProductDAO;
import models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void createProduct(Product product) throws SQLException, IllegalArgumentException {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getProductPrintingName() == null || product.getProductPrintingName().isEmpty()) {
            throw new IllegalArgumentException("Product printing name cannot be empty");
        }
        if (product.getSubCategory() == null || product.getSubCategory().getId() == 0) {
            throw new IllegalArgumentException("Invalid subcategory id");
        }
        if (product.getUnit() == null || product.getUnit().getId() == 0) {
            throw new IllegalArgumentException("Invalid unit id");
        }
        productDAO.createProduct(product);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.readProducts();
    }

    public void updateProduct(Product product) throws SQLException, IllegalArgumentException {
        if (product.getId() == 0) {
            throw new IllegalArgumentException("Invalid product id");
        }
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getProductPrintingName() == null || product.getProductPrintingName().isEmpty()) {
            throw new IllegalArgumentException("Product printing name cannot be empty");
        }
        if (product.getSubCategory() == null || product.getSubCategory().getId() == 0) {
            throw new IllegalArgumentException("Invalid subcategory id");
        }
        if (product.getUnit() == null || product.getUnit().getId() == 0) {
            throw new IllegalArgumentException("Invalid unit id");
        }
        productDAO.updateProduct(product);
    }
}
