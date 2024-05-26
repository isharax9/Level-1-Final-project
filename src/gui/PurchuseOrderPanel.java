/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dto.PurchaseOrder;
import dto.Supplier;
import java.sql.Date;
import dto.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.ProductService;
import services.SupplierService;
import utils.Validators;
import java.sql.ResultSet;
import java.time.LocalDate;
import services.PurchaseOrdersService;

/**
 *
 * @author vidur
 */
public class PurchuseOrderPanel extends javax.swing.JPanel {

    /**
     * Creates new form SellProductPanel
     */
    SupplierService supplierService;
    List<Supplier> suppliers;
    Supplier selectedSupplier;

    List<Product> productList;
    ProductService productService;
    
    PurchaseOrdersService poService;
    public PurchuseOrderPanel() {
        this.supplierService = new SupplierService();
        this.suppliers = new ArrayList<>();
        this.productService = new ProductService();
        this.productList = new ArrayList<>();
        poService = new PurchaseOrdersService();
        initComponents();
        initData();
    }

    private void initData() {
        suppliers.clear();
        try {
            suppliers.addAll(supplierService.getAll());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error Title", JOptionPane.WARNING_MESSAGE);
        }

        productList.clear();
        try {
            productList.addAll(productService.getAllProducts());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error Title", JOptionPane.WARNING_MESSAGE);
        }
        loadSelectProduct(productList);
        loadSelectSupplier(suppliers);
//        loadPurchageOrderTable(supplier);
    }

    private void loadSelectProduct(List<Product> proList) {
        DefaultComboBoxModel cm = new DefaultComboBoxModel();
        cm.removeAllElements();
        for (Product product : productList) {
            cm.addElement(product.getProductName());
        }
        cb_product.setModel(cm);
    }

    private void loadSelectSupplier(List<Supplier> proList) {
        DefaultComboBoxModel cm = new DefaultComboBoxModel();
        cm.removeAllElements();
        for (Supplier s : suppliers) {
            cm.addElement(s.getFirstName() + " " + s.getLastName());
        }
        cb_supplier.setModel(cm);
    }

//    private void loadPurchageOrderTable(List<PurchaseOrder> purchaseOrder) {
//        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
//        tableModel.setRowCount(0);
//        for (PurchaseOrder s : purchaseOrder) {
//            tableModel.addRow(new Object[]{
//                s.getId(),
//                s.getOrderedDate(),
//                s.getOrderQty(),
//                s.getWholesaleUnitPrice().getBankName(),
//                s.getBankDetails().getBankBranch(),
//                s.getBankDetails().getBankAccountNumber(),});
//        }
//    }
    private void clear() {
        tf_Id.setText("");
        ff_orderDate.setText("");
        tf_paidAmount.setText("");
        tf_prodcutName.setText("");
        tf_qty.setText("");
        tf_supplierSearchByContact.setText("");
        tf_whoelSalePrice.setText("");
    }

    private PurchaseOrder getPurchaseOrderFromFields() throws IllegalArgumentException {
        
        if (!Validators.isValidDouble(tf_qty.getText())) {
            throw new IllegalArgumentException("Quantity is Invalid");
        }
        if (!Validators.isValidDouble(tf_whoelSalePrice.getText())) {
            throw new IllegalArgumentException("Whole sale Price is Invalid");
        }

        if (cb_product.getSelectedItem() == null) {
            throw new IllegalArgumentException("Product is Invalid");
        }
        if (!Validators.isValidDouble(tf_paidAmount.getText())) {
            throw new IllegalArgumentException("Paid Amount is Invalid");
        }
        if (cb_supplier.getSelectedItem() == null) {
            throw new IllegalArgumentException("Supplier is Invalid");
        }

        dto.PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(0);
        purchaseOrder.setOrderQty(Integer.parseInt(tf_qty.getText()));
        purchaseOrder.setOrderedDate(Date.valueOf(LocalDate.now()));
        purchaseOrder.setPaidAmount(Integer.parseInt(tf_paidAmount.getText()));
        purchaseOrder.setProduct(getSelectedProductFromComboBox());
        purchaseOrder.setSupplier(getSelectedSupplierFromComboBox());

        return purchaseOrder;
    }

    private Product getSelectedProductFromComboBox() {
        String pro = "Nothing";
        String selectedProduct = String.valueOf(cb_product.getSelectedItem());
        for (Product product : productList) {
            if (product.getProductName().equals(selectedProduct)) {
                pro = product.getProductName();
                return product;
            }
        }
        System.out.println(pro);
        return null;
    }

    private Supplier getSelectedSupplierFromComboBox() {
        String selectedSupplier = String.valueOf(cb_supplier.getSelectedItem());
        for (Supplier s : suppliers) {
            String name = s.getFirstName() + " " + s.getLastName();
            if (name.equals(selectedSupplier)) {
                return s;
            }
        }
        return null;
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
        roundedPanel3 = new components.RoundedPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        roundedPanel2 = new components.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_qty = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_prodcutName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_paidAmount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tf_whoelSalePrice = new javax.swing.JTextField();
        cb_product = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btn_clear = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_makeGRN = new javax.swing.JButton();
        btn_viewSUpplier = new javax.swing.JButton();
        tf_supplierSearchByContact = new javax.swing.JTextField();
        cb_supplier = new javax.swing.JComboBox<>();
        btn_add = new javax.swing.JButton();
        tf_Id = new javax.swing.JFormattedTextField();
        ff_orderDate = new javax.swing.JFormattedTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase Order Management");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Order Date", "Qty", "Unite", "Whole Sale Product", "Product Name", "Value", "Paid Amount", "Suppler Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("ID");

        jLabel3.setText("Order Date");

        jLabel4.setText("Qty");

        jLabel5.setText("Product Name");

        jLabel6.setText("Supplier Search By Contact");

        jLabel7.setText("Paid Amount");

        jLabel8.setText("Whole Sale Price");

        jLabel9.setText("Select Product");

        cb_product.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_product.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_productItemStateChanged(evt);
            }
        });

        jLabel11.setText("Select Supplier");

        btn_clear.setText("Clear");

        btn_update.setText("Update");

        btn_makeGRN.setText("Make GRN");

        btn_viewSUpplier.setText("View Supplier");

        cb_supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        tf_Id.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        ff_orderDate.setEditable(false);
        ff_orderDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ff_orderDate.setEnabled(false);

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addComponent(btn_clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_update))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_add))
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_qty)
                            .addComponent(jLabel5)
                            .addComponent(tf_prodcutName)
                            .addComponent(jLabel9)
                            .addComponent(cb_product, 0, 196, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(tf_whoelSalePrice)
                            .addComponent(tf_paidAmount)
                            .addComponent(jLabel6)
                            .addComponent(tf_supplierSearchByContact)
                            .addComponent(jLabel11)
                            .addComponent(cb_supplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addComponent(btn_makeGRN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_viewSUpplier))
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(tf_Id)
                            .addComponent(ff_orderDate))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ff_orderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_prodcutName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_whoelSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_paidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_supplierSearchByContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear)
                    .addComponent(btn_update))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_makeGRN)
                    .addComponent(btn_viewSUpplier))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        try {

            PurchaseOrder s = getPurchaseOrderFromFields();
            if (s.isValidated()) {
                System.out.println(s.getWholesaleUnitPrice());
                poService.create(s);
            JOptionPane.showMessageDialog(this, "Success", "Sucessfully Created", JOptionPane.INFORMATION_MESSAGE);
            clear();
            initData();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "User Input Error", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Error Title", JOptionPane.WARNING_MESSAGE);
        
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void cb_productItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_productItemStateChanged
        // TODO add your handling code here:
        getSelectedProductFromComboBox();
    }//GEN-LAST:event_cb_productItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_makeGRN;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_viewSUpplier;
    private javax.swing.JComboBox<String> cb_product;
    private javax.swing.JComboBox<String> cb_supplier;
    private javax.swing.JFormattedTextField ff_orderDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private components.RoundedPanel roundedPanel1;
    private components.RoundedPanel roundedPanel2;
    private components.RoundedPanel roundedPanel3;
    private javax.swing.JFormattedTextField tf_Id;
    private javax.swing.JTextField tf_paidAmount;
    private javax.swing.JTextField tf_prodcutName;
    private javax.swing.JTextField tf_qty;
    private javax.swing.JTextField tf_supplierSearchByContact;
    private javax.swing.JTextField tf_whoelSalePrice;
    // End of variables declaration//GEN-END:variables
}
