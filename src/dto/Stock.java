package dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private int stockBarcode;
    private Date mnfDate;
    private Date expDate;
    private double unitPrice;
    private double availableQty;
    private double discount;
    private GRN grn;

   

    
    public boolean isValidate(){
         if (!Validators.isValidDiscount(discount)) {
             throw new IllegalArgumentException("Invalid discount");
         }
         if (!Validators.isValidPrice(unitPrice)) {
              throw new IllegalArgumentException("Invalid unit price");
         }
         if (!Validators.isValidDate(expDate)) {
              throw new IllegalArgumentException("Invalid expiry date");
         }
         if (!Validators.isValidDate(mnfDate)) {
             throw new IllegalArgumentException("Invalid manufacturing date");
         }
         return true;
         
       
    }

    public static Stock fromResultSet(ResultSet result) throws SQLException {
        Stock stock = new Stock();
        stock.setStockBarcode(result.getInt("stock_barcode"));
        stock.setMnfDate(result.getDate("mnf_date"));
        stock.setExpDate(result.getDate("exp_date"));
        stock.setUnitPrice(result.getDouble("unit_price"));
        stock.setAvailableQty(result.getDouble("available_qty"));
        stock.setDiscount(result.getDouble("discount"));
        stock.setGrn(GRN.fromResultSet(result));
        return stock;
    }
}
