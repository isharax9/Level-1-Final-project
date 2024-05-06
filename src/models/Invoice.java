/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vidur
 */
@AllArgsConstructor
@NoArgsConstructor //generating a No args constructure , LOMBOC ANNOTATION LIB
@Data // setters and getters
public class Invoice {
    private int id;
    private int employeeID;
    private Employee employee;
    private Customer customer;
    private double total;
    private double totalDiscount;
    private double grandTotal;
    private double paidAmount;
    private double balance;
    private LocalDateTime invoiceDate;
    private List<InvoiceItem> items;
    
}
