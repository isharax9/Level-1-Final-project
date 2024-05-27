package dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItem {
    private Stock stock;
    private Invoices invoice;
    private double invoiceItemQty;
    private double invoiceItemDiscount;
    

    public void setStock(Stock stock) {
        if (stock != null) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("Invalid stock");
        }
    }

    public void setInvoice(Invoices invoice) {
        if (invoice != null) {
            this.invoice = invoice;
        } else {
            throw new IllegalArgumentException("Invalid invoice");
        }
    }

    public static InvoiceItem fromResultSet(ResultSet result) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setStock(Stock.fromResultSet(result));
        invoiceItem.setInvoice(Invoices.fromResultSet(result));
        invoiceItem.setInvoiceItemQty(result.getDouble("invoice_qty"));
        invoiceItem.setInvoiceItemDiscount(result.getDouble("invoice_discount"));
        return invoiceItem;
    }
}
