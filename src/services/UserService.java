/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.UserDAO;
import dto.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.User;

/**
 *
 * @author vidur
 */
public class UserService {

    final UserDAO userDAO;
    final BankDetailsService bankDetailsService;

    public UserService() {
        this.userDAO = new UserDAO();
        this.bankDetailsService = new BankDetailsService();
    }

    public List<Employee> getAll() throws SQLException, IllegalArgumentException {

        return userDAO.getAll();

    }

    public void create(Employee emplyee,String username,String password) throws SQLException, IllegalArgumentException {
        if (emplyee.isValidated()) {
            boolean doesntExistUser = true;
            try {
                userDAO.getByUserName(username);
                doesntExistUser = false;

            } catch (IllegalArgumentException ex) {

            }
            try {
                userDAO.getByUserName(username);
                doesntExistUser = false;

            } catch (IllegalArgumentException ex) {

            }

            if (doesntExistUser) {
                emplyee.setBankaccountDetails(bankDetailsService.create(emplyee.getBankaccountDetails()));
                userDAO.create(emplyee, username, password);

            } else {
                throw new IllegalArgumentException("User Name Alredy exist ");
            }
        }
    }

    public List<Employee> searchByName(String name) throws SQLException, IllegalArgumentException {
        if (name.length() == 0) {
            return new ArrayList<>();
        }
        return userDAO.searchByName(name);

    }

    public Employee getByID(int id) throws SQLException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return userDAO.getByID(id);
    }

    public void update(Employee emplyee,String username,String password) throws SQLException, IllegalArgumentException {
        if (emplyee.getUserId()<= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        
        if(emplyee.isValidated()){
            userDAO.update(emplyee, username, password);
        }
        if(emplyee.getBankaccountDetails().isValidated()){
            bankDetailsService.update(emplyee.getBankaccountDetails());
        }
        

    }
    
    public List<Employee> search(String id, String firstName, String lastName, String userName, String userEmail, String bankAccountNumber) throws SQLException, IllegalArgumentException {
    if (id.isBlank()) {
        id = null;
    }
    if (firstName.isBlank()) {
        firstName = null;
    }
    if (lastName.isBlank()) {
        lastName = null;
    }
    if (userName.isBlank()) {
        userName = null;
    }
    if (userEmail.isBlank()) {
        userEmail = null;
    }
    if (bankAccountNumber.isBlank()) {
        bankAccountNumber = null;
    }

    if (id == null && firstName == null && lastName == null && userName == null && userEmail == null && bankAccountNumber == null) {
        return getAll();
    } else {
        return userDAO.search(id, firstName, lastName, userName, userEmail, bankAccountNumber);
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
