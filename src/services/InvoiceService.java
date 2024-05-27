/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.Invoice;
import java.sql.SQLException;

/**
 *
 * @author vidur
 */
public class InvoiceService {
    
    public Invoice create(Invoice invoice)throws   SQLException, IllegalArgumentException{
        invoice.setId(232);
        return invoice;
    }
    
}
