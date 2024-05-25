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

    public void setOrderedDate(Date orderedDate) {
        if (Validators.isValidDate(orderedDate)) {
            this.orderedDate = orderedDate;
        } else {
            throw new IllegalArgumentException("Invalid ordered date");
        }
    }

    public void setOrderQty(double orderQty) {
        if (Validators.isValidQuantity(orderQty)) {
            this.orderQty = orderQty;
        } else {
            throw new IllegalArgumentException("Invalid order quantity");
        }
    }

    public void setWholesaleUnitPrice(double wholesaleUnitPrice) {
        if (Validators.isValidPrice(wholesaleUnitPrice)) {
            this.wholesaleUnitPrice = wholesaleUnitPrice;
        } else {
            throw new IllegalArgumentException("Invalid wholesale unit price");
        }
    }

    public void setPaidAmount(double paidAmount) {
        if (Validators.isValidPrice(paidAmount)) {
            this.paidAmount = paidAmount;
        } else {
            throw new IllegalArgumentException("Invalid paid amount");
        }
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
