/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dto.Stock;
import dto.Supplier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.PrintService;
import services.StockService;
import services.SupplierService;

/**
 *
 * @author vidur
 */
public class StocksPanel extends javax.swing.JPanel {

    StockService stockService;
    List<Stock> stockList;
    Stock selectedStock;

    public StocksPanel() {
        this.stockService = new StockService();
        this.stockList = new ArrayList<>();
        initComponents();
        initData();
    }  
    
    private void initData() {
        stockList.clear();
        try {
            stockList.addAll(stockService.getAll());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error Title", JOptionPane.WARNING_MESSAGE);
        }
        loadSuppliertable(stockList);
    }  
    
    private void setSupplierToField(Stock stock) {
        String supplierName = stock.getGrn().getPurchaseOrder().getSupplier().getFirstName()+" "+stock.getGrn().getPurchaseOrder().getSupplier().getFirstName();
        tf_barcode_Stocks.setText(String.valueOf(stock.getStockBarcode()));
        tf_product_name_Stocks.setText(stock.getGrn().getPurchaseOrder().getProduct().getProductName());
        tf_suppler_Stocks.setText(supplierName);
        dc_mnf_date_Stocks.setDate(stock.getMnfDate());
        dc_exp_date_Stocks.setDate(stock.getExpDate());
    }
    
    private void search() {
        
    }

    private void loadSuppliertable(List<Stock> stockList) {
        System.out.println("Cool");
        DefaultTableModel tableModel = (DefaultTableModel) tbt_stock.getModel();
        tableModel.setRowCount(0);
        for (Stock s : stockList) {
            tableModel.addRow(new Object[]{
                s.getStockBarcode(),
                s.getGrn().getPurchaseOrder().getProduct().getProductName(),
                s.getMnfDate(),
                s.getExpDate(),
                s.getAvailableQty(),
                s.getDiscount(),
                s.getUnitPrice(),
                s.getGrn().getPurchaseOrder().getWholesaleUnitPrice()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel3 = new components.RoundedPanel();
        jPanel1 = new javax.swing.JPanel();
        tf_barcode_Stocks = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_product_name_Stocks = new javax.swing.JTextField();
        tf_suppler_Stocks = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dc_mnf_date_Stocks = new com.toedter.calendar.JDateChooser();
        dc_exp_date_Stocks = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        btn_search_Stocks = new javax.swing.JButton();
        btn_deactivate_Stocks = new javax.swing.JButton();
        btn_view_grn_Stocks = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbt_stock = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Stocks Management");

        roundedPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel3.setRoundBottomLeft(20);
        roundedPanel3.setRoundBottomRight(20);
        roundedPanel3.setRoundTopLeft(20);
        roundedPanel3.setRoundTopRight(20);

        tf_barcode_Stocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_barcode_StocksActionPerformed(evt);
            }
        });

        jLabel2.setText("Barcode ");

        jLabel3.setText("Product Name");

        tf_product_name_Stocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_product_name_StocksActionPerformed(evt);
            }
        });

        jLabel4.setText("Suppler");

        jLabel5.setText("Mnf Date");

        jLabel6.setText("Exp Date");

        jCheckBox1.setText("expired");

        btn_search_Stocks.setText("Search");

        btn_deactivate_Stocks.setText("Deactivate");

        btn_view_grn_Stocks.setText("View Grn");

        jButton1.setText("Print Exp Stocks Reports");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_search_Stocks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_deactivate_Stocks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_view_grn_Stocks))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_barcode_Stocks)
                                    .addComponent(tf_product_name_Stocks)
                                    .addComponent(tf_suppler_Stocks)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dc_mnf_date_Stocks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dc_exp_date_Stocks, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_barcode_Stocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_product_name_Stocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_suppler_Stocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dc_mnf_date_Stocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(dc_exp_date_Stocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_search_Stocks)
                    .addComponent(btn_deactivate_Stocks)
                    .addComponent(btn_view_grn_Stocks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbt_stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Barcode", "Product Name", "Mnf Date", "Exp Date", "Available Qty", "Discount", "Retail Price", "Wholesale Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbt_stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbt_stockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbt_stock);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tf_barcode_StocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_barcode_StocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_barcode_StocksActionPerformed

    private void tf_product_name_StocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_product_name_StocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_product_name_StocksActionPerformed

    private void tbt_stockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbt_stockMouseClicked
        // TODO add your handling code here:
        int SelectedRow = tbt_stock.getSelectedRow();
        if (SelectedRow >= 0) {
            String ID = String.valueOf(tbt_stock.getValueAt(SelectedRow, 0));
            System.out.println(ID);
            for (Stock s : stockList) {
                if (s.getStockBarcode()== Integer.valueOf(ID)) {
                    selectedStock = s;
                    setSupplierToField(selectedStock);
                    break;
                }
            }
        }
    }//GEN-LAST:event_tbt_stockMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new PrintService().PrintExpriedStocks(stockList);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_deactivate_Stocks;
    private javax.swing.JButton btn_search_Stocks;
    private javax.swing.JButton btn_view_grn_Stocks;
    private com.toedter.calendar.JDateChooser dc_exp_date_Stocks;
    private com.toedter.calendar.JDateChooser dc_mnf_date_Stocks;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private components.RoundedPanel roundedPanel3;
    private javax.swing.JTable tbt_stock;
    private javax.swing.JTextField tf_barcode_Stocks;
    private javax.swing.JTextField tf_product_name_Stocks;
    private javax.swing.JTextField tf_suppler_Stocks;
    // End of variables declaration//GEN-END:variables
}
