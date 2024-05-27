package dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GRN {

    private int id;
    private Timestamp grnDate;
    private PurchaseOrder purchaseOrder;

    public boolean isValidated() {
        if (!Validators.isValidTimestamp(grnDate)) {
            throw new IllegalArgumentException("Invalid GRN date");
        }
        if (purchaseOrder == null){
            throw new IllegalArgumentException("Purchase Order is cannot be null ");
        }
        if(!purchaseOrder.isValidated()){
            throw new IllegalArgumentException("Purchase Order is invalid ");
        }
        
        return true;
  }
    

    public static GRN fromResultSet(ResultSet result) throws SQLException {
        GRN grn = new GRN();
        grn.setId(result.getInt("grn_id"));
        grn.setGrnDate(result.getTimestamp("grn_date"));
        grn.setPurchaseOrder(PurchaseOrder.fromResultSet(result));
        return grn;
    }
}
