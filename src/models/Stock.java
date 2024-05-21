package models;

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

    public void setMnfDate(Date mnfDate) {
        if (Validators.isValidDate(mnfDate)) {
            this.mnfDate = mnfDate;
        } else {
            throw new IllegalArgumentException("Invalid manufacturing date");
        }
    }

    public void setExpDate(Date expDate) {
        if (Validators.isValidDate(expDate)) {
            this.expDate = expDate;
        } else {
            throw new IllegalArgumentException("Invalid expiry date");
        }
    }

    public void setUnitPrice(double unitPrice) {
        if (Validators.isValidPrice(unitPrice)) {
            this.unitPrice = unitPrice;
        } else {
            throw new IllegalArgumentException("Invalid unit price");
        }
    }

    public void setAvailableQty(double availableQty) {
        if (Validators.isValidQuantity(availableQty)) {
            this.availableQty = availableQty;
        } else {
            throw new IllegalArgumentException("Invalid available quantity");
        }
    }

    public void setDiscount(double discount) {
        if (Validators.isValidDiscount(discount)) {
            this.discount = discount;
        } else {
            throw new IllegalArgumentException("Invalid discount");
        }
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
