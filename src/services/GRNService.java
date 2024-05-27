/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.GrnDAO;
import dto.GRN;
import dto.PurchaseOrder;
import java.sql.Timestamp;
import java.sql.SQLException;

/**
 *
 * @author vidur
 */
public class GRNService {

    final GrnDAO grnDao;

    public GRNService() {
        this.grnDao = new GrnDAO();
    }

    public GRN getByPOID(int id) throws IllegalArgumentException, SQLException {
        if (id > 0) {
            return grnDao.getByPOID(id);
        } else {
            throw new IllegalArgumentException("Invalid purchas Order ID");
        }
    }

    public GRN create(GRN grn) throws IllegalArgumentException, SQLException {
        if (grn.isValidated()) {
            if (getByPOID(grn.getPurchaseOrder().getId()) == null) {
                return grnDao.create(grn);
            } else {
                throw new IllegalArgumentException("that Purchase order has GRN already");
            }

        }
        return null;
    }

   

}
