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
import dto.Unit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import utils.Database;

public class PurchaseOrdersService {

    final PurchaseOrdersDAO poDAO;

    public PurchaseOrdersService() {
        this.poDAO = new PurchaseOrdersDAO();
    }
    
    public PurchaseOrder create(PurchaseOrder po) throws SQLException,IllegalArgumentException{
        if(po.isValidated()){
            return poDAO.create(po);
        }
        return null;
    }
    public void update(PurchaseOrder po) throws SQLException,IllegalArgumentException{
        if(po.getId() <= 0){
            throw new IllegalArgumentException("Invalid Purchase order name ");
        }
        if(po.isValidated()){
            
             poDAO.update(po);
        }
    }
    public List<PurchaseOrder> getAll()throws SQLException,IllegalArgumentException{
        return poDAO.getAll();
    }
   

}
