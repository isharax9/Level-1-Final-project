package dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Validators;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoices {
    private int invoiceId;
    private Date invoiceDatetime;
    private double paidAmount;
    private PaymentTypes paymentMethod;
    private Employee user;
    private Customer customer;

    public void setInvoiceDatetime(Date invoiceDatetime) {
        if (Validators.isValidDatetime(invoiceDatetime)) {
            this.invoiceDatetime = invoiceDatetime;
        } else {
            throw new IllegalArgumentException("Invalid invoice datetime");
        }
    }

    public void setPaidAmount(double paidAmount) {
        if (Validators.isValidAmount(paidAmount)) {
            this.paidAmount = paidAmount;
        } else {
            throw new IllegalArgumentException("Invalid paid amount");
        }
    }

    public void setPaymentMethod(PaymentTypes paymentMethod) {
            this.paymentMethod = paymentMethod;
        
    }

    public static Invoices fromResultSet(ResultSet result) throws SQLException {
        Invoices invoice = new Invoices();
        invoice.setInvoiceId(result.getInt("invoice_id"));
        invoice.setInvoiceDatetime(result.getTimestamp("invoice_datetime"));
        invoice.setPaidAmount(result.getDouble("paid_amount"));
        invoice.setPaymentMethod(PaymentTypes.valueOf(result.getString("payment_method")));
        invoice.setUser(Employee.fromResultSet(result));  // Assuming you join users in your SQL query
        invoice.setCustomer(Customer.fromResultSet(result));  // Assuming you join customers in your SQL query
        return invoice;
    }
}
