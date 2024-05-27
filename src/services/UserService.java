/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.UserDAO;
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

    public List<User> getAll() throws SQLException, IllegalArgumentException {

        return userDAO.getAll();

    }

    public void create(User user) throws SQLException, IllegalArgumentException {
        if (user.isValidated()) {
            boolean doesntExistUser = true;
            try {
                userDAO.getByUserName(user.getUserName());
                doesntExistUser = false;

            } catch (IllegalArgumentException ex) {

            }
            try {
                userDAO.getByUserName(user.getUserName());
                doesntExistUser = false;

            } catch (IllegalArgumentException ex) {

            }

            if (doesntExistUser) {
                user.setBankDetails(bankDetailsService.create(user.getBankDetails()));
                userDAO.create(user);

            } else {
                throw new IllegalArgumentException("User Name Alredy exist ");
            }
        }
    }

    public List<User> searchByName(String name) throws SQLException, IllegalArgumentException {
        if (name.length() == 0) {
            return new ArrayList<>();
        }
        return userDAO.searchByName(name);

    }

    public User getByID(int id) throws SQLException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return userDAO.getByID(id);
    }

    public void update(User user) throws SQLException, IllegalArgumentException {
        if (user.getId() <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        
        if(user.isValidated()){
            userDAO.update(user);
        }
        if(user.getBankDetails().isValidated()){
            bankDetailsService.update(user.getBankDetails());
        }
        

    }
    
    public List<User> search(String id, String firstName, String lastName, String userName, String userEmail, String bankAccountNumber) throws SQLException, IllegalArgumentException {
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
