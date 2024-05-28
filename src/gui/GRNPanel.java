/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dto.GRN;
import dto.Product;
import dto.PurchaseOrder;
import dto.Stock;
import dto.Supplier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.GRNService;
import services.ProductService;
import services.PurchaseOrdersService;
import services.StockService;
import services.SupplierService;

/**
 *
 * @author vidur
 */
public class GRNPanel extends javax.swing.JPanel {

    SupplierService supplierService;
    List<Supplier> suppliersList;
    Supplier selectedSupplier;

    List<GRN> grnList;
    GRN selectedGRN;

    List<Product> productList;
    List<PurchaseOrder> purrchaseOrders;
    ProductService productService;

    PurchaseOrdersService poService;
    PurchaseOrder selectedPurchasOrder;
    GRNService grnService;
    List<GRN> grnsList;
    StockService stockService;

    public GRNPanel() {
        this.stockService = new StockService();
        this.supplierService = new SupplierService();
        this.suppliersList = new ArrayList<>();
        this.grnList = new ArrayList<>();
        this.productService = new ProductService();
        this.productList = new ArrayList<>();
        this.purrchaseOrders = new ArrayList<>();
        this.poService = new PurchaseOrdersService();
        this.grnService = new GRNService();
        initComponents();
        initData();
    }

    private void initData() {
        suppliersList.clear();
        purrchaseOrders.clear();
        grnList.clear();
        try {
            suppliersList.addAll(supplierService.getAll());
            purrchaseOrders.addAll(poService.getAll());
            grnList.addAll(grnService.getAll());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error Title", JOptionPane.WARNING_MESSAGE);
        }

        productList.clear();
        try {
            productList.addAll(productService.getAllProducts());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error Title", JOptionPane.WARNING_MESSAGE);
        }
        loadPurchageOrderTable(purrchaseOrders);
        loadGRNTable(grnList);
    }

    private void setGRNtoFields(GRN grn) {
        tf_Id.setText("" + grn.getId());
        tf_supplier.setText(grn.getPurchaseOrder().getSupplier().getFirstName() + " " + grn.getPurchaseOrder().getSupplier().getFirstName());
        dc_OrderDate.setDate(grn.getGrnDate());
        tf_productName.setText(grn.getPurchaseOrder().getProduct().getProductName());
    }

    private void clear() {
        selectedGRN = null;
        initData();
        tf_Id.setText("");
        tf_supplier.setText("");
        dc_OrderDate.setDate(null);
        tf_productName.setText("");
    }

    private void loadPurchageOrderTable(List<PurchaseOrder> purchaseOrder) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_purchaseOrder.getModel();
        tableModel.setRowCount(0);
        for (PurchaseOrder s : purchaseOrder) {
            tableModel.addRow(new Object[]{
                s.getId(),
                s.getOrderedDate(),
                s.getOrderQty(),
                s.getProduct().getUnit().getUnit(),
                s.getWholesaleUnitPrice(),
                s.getProduct().getProductName(),
                s.getOrderQty() * s.getWholesaleUnitPrice(),
                s.getPaidAmount(),
                String.valueOf(s.getOrderQty() * s.getWholesaleUnitPrice() - s.getPaidAmount()),
                s.getSupplier().getFirstName() + " " + s.getSupplier().getLastName(),});
        }
    }

    private void loadGRNTable(List<GRN> grns) {
        System.out.println("GRN LOADINF" + grns);
        DefaultTableModel tableModel = (DefaultTableModel) tbl_grn.getModel();
        tableModel.setRowCount(0);
        for (GRN s : grns) {
            double grnvalue = s.getPurchaseOrder().getOrderQty() * s.getPurchaseOrder().getWholesaleUnitPrice();
            tableModel.addRow(new Object[]{
                s.getId(),
                s.getPurchaseOrder().getOrderedDate(),
                s.getPurchaseOrder().getProduct().getProductName(),
                s.getPurchaseOrder().getOrderQty(),
                s.getPurchaseOrder().getWholesaleUnitPrice(),
                s.getPurchaseOrder().getSupplier().getFirstName() + s.getPurchaseOrder().getSupplier().getLastName(),
                grnvalue,
                s.getPurchaseOrder().getPaidAmount(),
                grnvalue - s.getPurchaseOrder().getPaidAmount(),});

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
        roundedPanel1 = new components.RoundedPanel();
        roundedPanel2 = new components.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        tf_Id = new javax.swing.JTextField();
        tf_supplier = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dc_OrderDate = new com.toedter.calendar.JDateChooser();
        tf_productName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_pay = new javax.swing.JButton();
        btn_viewStock = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        roundedPanel3 = new components.RoundedPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_grn = new javax.swing.JTable();
        roundedPanel4 = new components.RoundedPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_purchaseOrder = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Good Receive Note Management");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);

        jLabel4.setText("ID");

        jLabel5.setText("Supplier");

        jLabel6.setText("Purchase Order Date");

        jLabel7.setText("Product Name");

        btn_pay.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        btn_pay.setText("Pay");

        btn_viewStock.setText("View Stock");
        btn_viewStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewStockActionPerformed(evt);
            }
        });

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_Id)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_supplier, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(dc_OrderDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(tf_productName, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGroup(roundedPanel2Layout.createSequentialGroup()
                            .addComponent(btn_viewStock)
                            .addGap(18, 18, 18)
                            .addComponent(btn_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dc_OrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_productName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(31, 31, 31)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_viewStock)
                    .addComponent(btn_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_grn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GRN ID", "Purchase Order Date", "Product", "Qty", "Whole Sale", "Supplier", "Value", "Paid Amount", "Due Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_grn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_grnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_grn);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Purchase Order Table");

        tbl_purchaseOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Order Date", "Qty", "Unit", "Whole Sale Price", "Product Name", "Value", "Paid Amount", "Due Amount", "Supplier Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_purchaseOrder);

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundedPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(191, Short.MAX_VALUE))
            .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundedPanel4Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("GRN Table");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(roundedPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_grnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_grnMouseClicked
        // TODO add your handling code here:
        int row = tbl_grn.getSelectedRow();
        if (row >= 0) {
            selectedGRN = grnList.get(row);
            setGRNtoFields(selectedGRN);
            var pos = new ArrayList<PurchaseOrder>();
            pos.add(selectedGRN.getPurchaseOrder());
            loadPurchageOrderTable(pos);
        }

    }//GEN-LAST:event_tbl_grnMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_viewStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewStockActionPerformed
        // TODO add your handling code here:

        if (selectedGRN != null) {
            try {
                Stock stock = stockService.getByGRNID(selectedGRN.getId());
                if (stock != null) {
                    new ViewStockFrame(stock).setVisible(true);
                } else {
                    new GRNAddToStockFrame(selectedGRN).setVisible(true);
                }
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            System.out.println("Not Selected GRN");
        }
    }//GEN-LAST:event_btn_viewStockActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pay;
    private javax.swing.JButton btn_viewStock;
    private com.toedter.calendar.JDateChooser dc_OrderDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private components.RoundedPanel roundedPanel1;
    private components.RoundedPanel roundedPanel2;
    private components.RoundedPanel roundedPanel3;
    private components.RoundedPanel roundedPanel4;
    private javax.swing.JTable tbl_grn;
    private javax.swing.JTable tbl_purchaseOrder;
    private javax.swing.JTextField tf_Id;
    private javax.swing.JTextField tf_productName;
    private javax.swing.JTextField tf_supplier;
    // End of variables declaration//GEN-END:variables
}
