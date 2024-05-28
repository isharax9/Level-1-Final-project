/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.InvoiceDAO;
import dto.Invoice;
import java.sql.SQLException;

/**
 *
 * @author vidur
 */
public class InvoiceService {
    InvoiceDAO invoiceDao;

    public InvoiceService() {
        this.invoiceDao  = new InvoiceDAO();
    }
    
    public Invoice create(Invoice invoice)throws   SQLException, IllegalArgumentException{
        if(invoice.getItems().isEmpty()){
            throw new IllegalArgumentException("Please  add invoice item to make invoice");
        }
        
        System.out.println(invoice);
        invoice.setId(0);
        invoiceDao.create(invoice);
        return invoice;
    }
    
}
