/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.SupplierDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.BankDetails;
import models.Supplier;
import utils.Validators;

/**
 *
 * @author vidur
 */
public class SupplierService {

    final SupplierDAO supplierDAO;
    final BankDetailsService bankDetailsService;

    public SupplierService() {
        this.supplierDAO = new SupplierDAO();
        this.bankDetailsService = new BankDetailsService();
    }

    public void create(Supplier supplier) throws SQLException, IllegalArgumentException {
        if (supplier.isValidated()) {
            boolean isNotExistSupplier = true;
            try {
                supplierDAO.getByContact(supplier.getContact());
                isNotExistSupplier = false;

            } catch (IllegalArgumentException ex) {

            }

            if (isNotExistSupplier) {
                supplier.setBankDetails(bankDetailsService.create(supplier.getBankDetails()));
                supplierDAO.create(supplier);

            }else{
                throw new IllegalArgumentException("Supplier contact number is Alredy exist ");
            }
        }
    }
    
    public Supplier getByContact(String contact)throws SQLException, IllegalArgumentException{
        if (!Validators.isValidContact(contact)) {
            throw new IllegalArgumentException("Invalid contact");
        }
        
        return supplierDAO.getByContact(contact);
    }
    
    public List<Supplier> searchByName(String name)throws SQLException, IllegalArgumentException{
        if(name.length() == 0){
            return new ArrayList<>();
        }
        return supplierDAO.searchByName(name);
        
    }

    public static void main(String[] args) {
        SupplierService service = new SupplierService();

//        try {
//            var supplier = new Supplier();
//            supplier.setFirstName("vidura");
//            supplier.setLastName("Vijerathne");
//            supplier.setContact("0759569899");
//            supplier.setAddress("Moroththa,Madahapola");
//            supplier.setBankDetails(new BankDetails(1, "bn", "bb", "000000", "vidura"));
//            service.create(supplier);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }


    }

}
