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
public class PurchaseOrder {

    private int id;
    private Date orderedDate;
    private double orderQty;
    private Product product;
    private double wholesaleUnitPrice;
    private double paidAmount;
    private Supplier supplier;

   
    public boolean isValidated() {
        if (!Validators.isValidPrice(paidAmount)) {
            throw new IllegalArgumentException("Inavalid Paid Amount");
        }
        if (!Validators.isValidPrice(wholesaleUnitPrice)) {
            throw new IllegalArgumentException("Invalid Wholesale Price");
        }
        if (!Validators.isValidQuantity(orderQty)){
            throw new IllegalArgumentException(" invalid Quentity ");
        }
        if(!Validators.isValidDate(orderedDate)){
            throw new IllegalArgumentException("Invalid ordered date");
        }

        return true;
    }

    public static PurchaseOrder fromResultSet(ResultSet result) throws SQLException {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(result.getInt("po_id"));
        purchaseOrder.setOrderedDate(result.getDate("ordered_date"));
        purchaseOrder.setOrderQty(result.getDouble("order_qty"));
        purchaseOrder.setProduct(Product.fromResultSet(result));
        purchaseOrder.setWholesaleUnitPrice(result.getDouble("wholesale_unit_price"));
        purchaseOrder.setPaidAmount(result.getDouble("paid_amount"));
        purchaseOrder.setSupplier(Supplier.fromResultSet(result));
        return purchaseOrder;
    }
}
