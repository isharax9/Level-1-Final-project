/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.Invoice;
import dto.InvoiceItem;
import dto.UserType;
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

    public static void PrintInvoice(Invoice invoice) {
        System.out.println("INVOICE ");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("src/reports/invoice.jrxml");
            List<Map<String, Object>> dataSource = new ArrayList<>();
            for(InvoiceItem invoiceItem : invoice.getItems()){
                 Map<String, Object> item = new HashMap<>();
                    item.put("product_name",invoiceItem.getStock().getGrn().getPurchaseOrder().getProduct().getProductName());
                    item.put("qty", "X" + invoiceItem.getInvoiceItemQty());
                    item.put("unit_price", "රු. " + String.format("%.2f", invoiceItem.getStock().getUnitPrice()));
                    item.put("discount", "රු. " + String.format("%.2f", invoiceItem.getInvoiceItemDiscount()));
                    double subtotal = invoiceItem.getInvoiceItemQty()* (invoiceItem.getStock().getUnitPrice()- invoiceItem.getInvoiceItemDiscount());
                    item.put("cost", "රු. " + String.format("%.2f", subtotal));
                    dataSource.add(item);
            }
            
            Map<String, Object> parameters = new HashMap<>();
                parameters.put("invoice_no", "invoiceno");
                parameters.put("invoice_date","jij");
                parameters.put("total_cost_without_discount", "මුළු වටිනාකම (වට්ටම් රහිත) : රු.");
                parameters.put("total_discount", "මුළු වට්ටම   : රු.");
                parameters.put("invoice_total", "ගෙවිය යුතු මුළු මුදල : රු.");
                parameters.put("total_discount_greeting", "ඔබට ලැබූ මුලු ලාභය  : රු.");
                
                int cash = 400;
                if (cash > 0) {
                    parameters.put("cash", "දුන් මුදල : රු." );
                    parameters.put("balance", "ඉතිරි දුන් මුදල : රු.");
                    parameters.put("line1", "___________");
                } else {
                    parameters.put("cash", "");
                    parameters.put("balance", "");
                    parameters.put("line1", "");
                }
                parameters.put("CashierName","");
                
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(dataSource));

                JasperPrintManager.printReport(jasperPrint, false);

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            
        }
    }

}
