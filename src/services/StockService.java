/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.StockDAO;
import dto.Stock;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vidur
 */
public class StockService {

    StockDAO stockDTO;

    public StockService() {
        stockDTO = new StockDAO();
    }

    public Stock create(Stock stock) throws SQLException, IllegalArgumentException {
        if (stock.isValidate()) {
            Stock existStock = getByGRNID(stock.getGrn().getId());
            Stock existStockBarcode;
            try {
                existStockBarcode =  getByStockBarcode(stock.getStockBarcode());

            }catch(Exception ex){
                throw new IllegalArgumentException(ex.getMessage());
            }
            if(existStockBarcode != null){
                throw new IllegalArgumentException("That Stock Barcode is already In DB");
            }
            if (existStock == null) {
                return stockDTO.create(stock);

            } else {
                throw new IllegalArgumentException("That Stock is Alredy In DB,(GRN)");
            }
        }
        return null;
    }

    public List<Stock> getAll() throws SQLException, IllegalArgumentException {
        return stockDTO.getAll();
    }

    public Stock getByGRNID(int id) throws SQLException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid GRN ID");
        } else {
            return stockDTO.getByGRNID(id);
        }

    }

    public Stock getByStockBarcode(int barcode) throws SQLException, IllegalAccessException {
        if (barcode <= 0) {
            throw new IllegalArgumentException("Invalid Barcode");
        } else {
            return stockDTO.getByStockBarcode(barcode);
        }
    }

    public List<Stock> getByProductName(String productName) throws SQLException, IllegalAccessException {
        if (productName.length() == 0) {
            return new ArrayList<>();
        } else {
            return stockDTO.getByProductName(productName);
        }
    }

}
