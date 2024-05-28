/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.Invoice;
import dto.InvoiceItem;
import dto.UserType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author vidur
 */
public class PrintService {
    
    public static String getCurrentTimeFormatted() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/dd hh:mm a");
        return now.format(formatter);
    }

    public static void PrintInvoice(Invoice invoice) {
        System.out.println("INVOICE ");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("src/reports/invoice.jrxml");
            List<Map<String, Object>> dataSource = new ArrayList<>();
            double totalForFooter = 0;
            double totalDiscount = 0;
            for(InvoiceItem invoiceItem : invoice.getItems()){
                 Map<String, Object> item = new HashMap<>();
                    item.put("product_name",invoiceItem.getStock().getGrn().getPurchaseOrder().getProduct().getProductName());
                    item.put("qty", "X" + invoiceItem.getInvoiceItemQty());
                    item.put("unit_price", "රු. " + String.format("%.2f", invoiceItem.getStock().getUnitPrice()));
                    item.put("discount", "රු. " + String.format("%.2f", invoiceItem.getInvoiceItemDiscount()));
                    double subtotal = invoiceItem.getInvoiceItemQty()* (invoiceItem.getStock().getUnitPrice()- invoiceItem.getInvoiceItemDiscount());
                    item.put("cost", "රු. " + String.format("%.2f", subtotal));
                    dataSource.add(item);
                    totalForFooter += invoiceItem.getInvoiceItemQty()* invoiceItem.getStock().getUnitPrice();
                    totalDiscount += invoiceItem.getInvoiceItemDiscount()*invoiceItem.getInvoiceItemQty();
            }
            
            Map<String, Object> parameters = new HashMap<>();
                parameters.put("invoice_no", invoice.getId()+"");
                parameters.put("invoice_date",getCurrentTimeFormatted());
                parameters.put("total_cost_without_discount", "Total: LKR."+ String.format("%.2f", totalForFooter));
                parameters.put("total_discount", "Total Discount : LKR."+String.format("%.2f", totalDiscount));
                parameters.put("invoice_total", "Grand Total : LKR."+String.format("%.2f",totalForFooter-totalDiscount ));
                parameters.put("total_discount_greeting", "");
                
                double cash = invoice.getPaidAmount();
                if (cash > 0) {
                    parameters.put("cash", "Cash : LKR."+String.format("%.2f",invoice.getPaidAmount()) );
                    parameters.put("balance", "Balance : LKR."+String.format("%.2f",invoice.getPaidAmount()-(totalForFooter-totalDiscount)));
                    parameters.put("line1", "___________");
                } else {
                    parameters.put("cash", "CARDPAYMENT");
                    parameters.put("balance", "");
                    parameters.put("line1", "___________");
                }
                parameters.put("CashierName",invoice.getEmployee().getFirstName()+" "+invoice.getEmployee().getLastName());
                
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(dataSource));

                JasperPrintManager.printReport(jasperPrint, false);

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            
        }
        
        
    }

}
