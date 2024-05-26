/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author vidur
 */
import dao.PurchaseOrdersDAO;
import dto.Product;
import dto.PurchaseOrder;
import dto.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import utils.Database;

public class PurchaseOrdersService {

    final PurchaseOrdersDAO poService;

    public PurchaseOrdersService() {
        this.poService = new PurchaseOrdersDAO();
    }
    
    public PurchaseOrder create(PurchaseOrder po) throws SQLException,IllegalArgumentException{
        
    }
    public static void main(String[] args) throws Exception {
        var p = new PurchaseOrder();
        var pdct = new Product();
        pdct.setId(10);

        var sp = new Supplier();
        sp.setId(11);
        p.setId(5);
        p.setOrderQty(100);
        p.setPaidAmount(1000);
        p.setProduct(pdct);
        p.setSupplier(sp);
        p.setWholesaleUnitPrice(1500);
        p.setOrderedDate(Date.valueOf(LocalDate.now()));
        new PurchaseOrdersService().update(p);

    }

}
