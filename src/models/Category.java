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
public class Category {
    private int id;
    private String category;

    public void setCategory(String category) {
        if (Validators.isValidCategory(category)) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Invalid category");
        }
    }

    public static Category fromResultSet(ResultSet result) throws SQLException {
        Category category = new Category();
        category.setId(result.getInt("cat_id"));
        category.setCategory(result.getString("category"));
        return category;
    }
}
