/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.CustomerDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.Customer;
import dto.Supplier;
import utils.Validators;

/**
 *
 * @author vidur
 */
public class CustomerService {

    final CustomerDAO customerDAO;
    final BankDetailsService bankDetailsService;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
        this.bankDetailsService = new BankDetailsService();
    }

    public List<Customer> getAll() throws SQLException, IllegalArgumentException {
        return customerDAO.getAll();
    }

    public void create(Customer customer) throws SQLException, IllegalArgumentException {
        if (customer.isValidated()) {
            boolean isNotExistSupplier = true;
            try {
                customerDAO.getByContact(customer.getCustomerContact());
                isNotExistSupplier = false;

            } catch (IllegalArgumentException ex) {

            }
        }
    }

    public Customer getByContact(String contact) throws SQLException, IllegalArgumentException {
        if (!Validators.isValidContact(contact)) {
            throw new IllegalArgumentException("Invalid contact");
        }
        return customerDAO.getByContact(contact);
    }

    public List<Customer> searchByContact(String contact) throws SQLException, IllegalArgumentException {
        if (!Validators.isValidContact(contact)) {
            throw new IllegalArgumentException("Invalid contact");
        }        
        return customerDAO.searchByContact(contact);
    }

    public Customer getByID(int id) throws SQLException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return customerDAO.getByID(id);
    }

    public void update(Customer customer) throws SQLException, IllegalArgumentException {
        if (customer.getCustomerId()<= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }        
        if(customer.isValidated()){
            customerDAO.update(customer);
        }     

    }
    
    public  List<Customer> search(String id,String name,String address,String mobile, String points)throws SQLException, IllegalArgumentException{
        if(id.isBlank()){
            id = null;
        }
        if(name.isBlank()){
            name = null;
        }
        if(address.isBlank()){
            address = null;  
        }
        if(mobile.isBlank()){
            mobile = null;
        }
        if(points.isBlank()){
            points=null;
        }
        
        if(id == null && name == null && address == null && mobile == null && points == null){
            return getAll();
        }else{
            return customerDAO.search(id,mobile,address,points);
        }
    }

//    public static void main(String[] args) {
//        SupplierService service = new SupplierService();
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
//try{
//    var sup = service.getByContact("0759569855");
//    System.out.println(sup);
//}catch(Exception ex){
//    ex.printStackTrace();
//}
//try{
//    var sup = service.searchByName("x");
//    System.out.println(sup);
//}catch(Exception ex){
//    ex.printStackTrace();
//}
//try{
//    var sup = service.searchByContact("075");
//    System.out.println(sup);
//}catch(Exception ex){
//    ex.printStackTrace();
//}
//try{
//    var sup = service.getByID(0);
//    System.out.println(sup);
//}catch(Exception ex){
//    ex.printStackTrace();
//}
//try{
//    var sup = service.getAll();
//    System.out.println(sup);
//}catch(Exception ex){
//    ex.printStackTrace();
//}
//    }
}
