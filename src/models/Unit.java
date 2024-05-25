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
public class Unit {

    private int id;
    private String unit;

    public void setUnit(String unit) {
            this.unit = unit;
        
    }

    public static Unit fromResultSet(ResultSet result) throws SQLException {
        Unit unit = new Unit();
        unit.setId(result.getInt("unit_id"));
        unit.setUnit(result.getString("unit"));
        return unit;
    }

    public boolean isValidate() {
        if (!Validators.isValidUnit(unit)) {
            throw new IllegalArgumentException("Invalid unit");
        }
        return true;
    }
}
