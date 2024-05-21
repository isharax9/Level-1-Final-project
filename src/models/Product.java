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
        if (Validators.isValidProductName(productName)) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Invalid product name");
        }
    }

    public void setProductPrintingName(String productPrintingName) {
        if (Validators.isValidProductPrintingName(productPrintingName)) {
            this.productPrintingName = productPrintingName;
        } else {
            throw new IllegalArgumentException("Invalid product printing name");
        }
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
}
