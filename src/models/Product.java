package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String productName;
    private String productPrintingName;
    private SubCategory subCategory;
    private Unit unit;
    private double stockRefillingQty;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrintingName(String productPrintingName) {
        this.productPrintingName = productPrintingName;
    }

    public static Product fromResultSet(ResultSet result) throws SQLException {
        Product product = new Product();
        product.setId(result.getInt("product_id"));
        product.setProductName(result.getString("product_name"));
        product.setProductPrintingName(result.getString("product_printing_name"));
        product.setSubCategory(SubCategory.fromResultSet(result));
        product.setUnit(Unit.fromResultSet(result));
        product.setStockRefillingQty(result.getDouble("stock_refilling_qty"));
        return product;
    }

    public boolean isValidated() {
        if (!Validators.isValidProductName(productName)) {
            throw new IllegalArgumentException("Invalid product name");
        }
        if (!Validators.isValidProductPrintingName(productPrintingName)) {
            throw new IllegalArgumentException("Invalid product printing name");
        }
        if (subCategory.getId() <= 0) {
            throw new IllegalArgumentException("Invalid Sub category ID");
        }
        if (!subCategory.isValidate()) {
            throw new IllegalArgumentException("Invalid Sub category Name");
        }
        if (unit.getId() <= 0) {
            throw new IllegalArgumentException("Invalid unit ID");
        }
        if (!unit.isValidate()) {
            throw new IllegalArgumentException("Invalid unit ");

        }

        return true;
    }
}
