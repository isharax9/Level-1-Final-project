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
public class SubCategory {
    private int id;
    private String subCategory;
    private Category category;

    public void setSubCategory(String subCategory) {
        if (Validators.isValidSubCategory(subCategory)) {
            this.subCategory = subCategory;
        } else {
            throw new IllegalArgumentException("Invalid sub category");
        }
    }

    public static SubCategory fromResultSet(ResultSet result) throws SQLException {
        SubCategory subCategory = new SubCategory();
        subCategory.setId(result.getInt("sub_cat_id"));
        subCategory.setSubCategory(result.getString("sub_category"));
        subCategory.setCategory(Category.fromResultSet(result));
        return subCategory;
    }
}
