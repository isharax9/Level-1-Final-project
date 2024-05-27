/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dto.GRN;
import dto.PurchaseOrder;
import dto.Supplier;
import java.sql.Date;
import dto.Product;
import java.sql.Timestamp;
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
import services.GRNService;
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
    List<PurchaseOrder> purrchaseOrders;
    ProductService productService;

    PurchaseOrdersService poService;
    PurchaseOrder selectedPurchasOrder;
    GRNService grnService;
    List<PurchaseOrder> searchedPOs;

    public PurchuseOrderPanel() {
        this.supplierService = new SupplierService();
        this.suppliers = new ArrayList<>();
        this.productService = new ProductService();
        this.productList = new ArrayList<>();
        this.purrchaseOrders = new ArrayList<>();
        this.searchedPOs = new ArrayList<>();
        poService = new PurchaseOrdersService();
        grnService = new GRNService();
        initComponents();
        initData();
    }

    private void initData() {
        suppliers.clear();
        purrchaseOrders.clear();
        searchedPOs.clear();
        try {
            suppliers.addAll(supplierService.getAll());
            purrchaseOrders.addAll(poService.getAll());
            searchedPOs.addAll(purrchaseOrders);
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
        loadPurchageOrderTable(purrchaseOrders);
    }

    private void loadSelectProduct(List<Product> proList) {
        DefaultComboBoxModel cm = new DefaultComboBoxModel();
        cm.removeAllElements();
        for (Product product : proList) {
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

    private void loadPurchageOrderTable(List<PurchaseOrder> purchaseOrder) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
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

    private void clear() {
        tf_Id.setText("0");
        ff_orderDate.setText("");
        tf_paidAmount.setText("0");
        tf_prodcutName.setText("");
        tf_qty.setText("0");
        tf_supplierSearchByContact.setText("");
        tf_whoelSalePrice.setText("0");
        selectedPurchasOrder = null;
        lbl_dueAmount.setText("");
        initData();
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
        purchaseOrder.setWholesaleUnitPrice(Double.parseDouble(tf_whoelSalePrice.getText()));

        return purchaseOrder;
    }

    private void setPurchaseOrderFromFields(PurchaseOrder po) throws IllegalArgumentException {
        tf_Id.setText(String.valueOf(po.getId()));
        ff_orderDate.setText(po.getOrderedDate().toString());
        tf_qty.setText(String.valueOf(po.getOrderQty()));
        tf_prodcutName.setText(po.getProduct().getProductName());
        tf_whoelSalePrice.setText(String.valueOf(po.getWholesaleUnitPrice()));
        tf_paidAmount.setText(String.valueOf(po.getPaidAmount()));
        tf_supplierSearchByContact.setText(po.getSupplier().getContact());
        lbl_dueAmount.setText("Due Amount :" + (po.getOrderQty() * po.getWholesaleUnitPrice() - po.getPaidAmount()));
        setSelectedProductToComboBox(po.getProduct());
        setSelectedSupplierToComboBox(po.getSupplier());

    }

    private Product getSelectedProductFromComboBox() {
        String pro = "Nothing";
        String selectedProduct = String.valueOf(cb_product.getSelectedItem());
        for (Product product : productList) {
            if (product.getProductName().equals(selectedProduct)) {
//                pro = product.getProductName();
                System.out.println(product);

                return product;
            }
        }
        System.out.println(pro);
        return null;
    }

    private void setSelectedProductToComboBox(Product product) {
        cb_product.setSelectedItem(product.getProductName());
    }

    private void setSelectedSupplierToComboBox(Supplier s) {
        cb_supplier.setSelectedItem(s.getFirstName() + " " + s.getLastName());
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

    private void calculateDueAmount() {
        double paidAmount = 0;
        if (!Validators.isValidDouble(tf_whoelSalePrice.getText())) {
            lbl_dueAmount.setText("");
            return;
        }
        if (Validators.isValidDouble(tf_paidAmount.getText())) {
            paidAmount = Double.parseDouble(tf_paidAmount.getText());
        }
        if (!Validators.isValidDouble(tf_qty.getText())) {
            lbl_dueAmount.setText("");
            return;
        }

        double wholesalePrice = Double.parseDouble(tf_whoelSalePrice.getText());
        double qty = Double.parseDouble(tf_qty.getText());

        double duePayment = (wholesalePrice * qty) - paidAmount;
        lbl_dueAmount.setText("Due Amount :" + duePayment);

    }

    private void poSearchByProduct(Product product) {
        List<PurchaseOrder> pos = new ArrayList<>();
        for (var ps : purrchaseOrders) {
            if (ps.getProduct().getId() == product.getId()) {
                pos.add(ps);
            }
        }
        if (!pos.isEmpty()) {
            loadPurchageOrderTable(pos);
        }
    }
    
    private void poSearchBySupplier(Supplier supplier){
        List<PurchaseOrder> sup = new ArrayList<>();
        for (var ps : purrchaseOrders) {
            if (ps.getSupplier().getId() == supplier.getId()) {
                sup.add(ps);
            }
        }
        if (!sup.isEmpty()) {
            loadPurchageOrderTable(sup);
        }
    }
    
    private void loadOnlyNotCompletedPos(){
        List<PurchaseOrder> sup = new ArrayList<>();
        for (var ps : purrchaseOrders) {
            double duoAmount =  (ps.getOrderQty()*ps.getWholesaleUnitPrice())-ps.getPaidAmount();
            if(duoAmount > 0){
                sup.add(ps);
            }
        }
         loadPurchageOrderTable(sup);
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
        lbl_dueAmount = new javax.swing.JLabel();
        jc_notcompletePO = new javax.swing.JCheckBox();

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
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Order Date", "Qty", "Unit", "WholeSale Price", "Product Name", "Value", "Paid Amount", "due Amount", "Suppler Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
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

        tf_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_qtyKeyReleased(evt);
            }
        });

        jLabel4.setText("Qty");

        jLabel5.setText("Product Name");

        tf_prodcutName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_prodcutNameKeyReleased(evt);
            }
        });

        jLabel6.setText("Supplier Search By Contact");

        tf_paidAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_paidAmountKeyReleased(evt);
            }
        });

        jLabel7.setText("Paid Amount");

        jLabel8.setText("Whole Sale Price");

        jLabel9.setText("Select Product");

        tf_whoelSalePrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_whoelSalePriceKeyReleased(evt);
            }
        });

        cb_product.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_product.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_productItemStateChanged(evt);
            }
        });
        cb_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_productActionPerformed(evt);
            }
        });

        jLabel11.setText("Select Supplier");

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_makeGRN.setText("Make GRN");
        btn_makeGRN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_makeGRNActionPerformed(evt);
            }
        });

        btn_viewSUpplier.setText("View Supplier");

        tf_supplierSearchByContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_supplierSearchByContactKeyReleased(evt);
            }
        });

        cb_supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_supplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_supplierItemStateChanged(evt);
            }
        });

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

        lbl_dueAmount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_dueAmount.setForeground(new java.awt.Color(255, 0, 0));

        jc_notcompletePO.setText("Not Completed POs only");
        jc_notcompletePO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_notcompletePOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_qty)
                    .addComponent(tf_prodcutName)
                    .addComponent(cb_product, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_whoelSalePrice)
                    .addComponent(tf_paidAmount)
                    .addComponent(tf_supplierSearchByContact)
                    .addComponent(cb_supplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_Id)
                    .addComponent(ff_orderDate)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_makeGRN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addComponent(btn_viewSUpplier)
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addComponent(btn_update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_dueAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jc_notcompletePO)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addComponent(cb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_dueAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jc_notcompletePO)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear)
                    .addComponent(btn_update))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        if(selectedPurchasOrder != null){
            return;
        }
        poSearchByProduct(getSelectedProductFromComboBox());
    }//GEN-LAST:event_cb_productItemStateChanged

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tf_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_qtyKeyReleased
        // TODO add your handling code here:
        calculateDueAmount();
    }//GEN-LAST:event_tf_qtyKeyReleased

    private void tf_whoelSalePriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_whoelSalePriceKeyReleased
        // TODO add your handling code here:
        calculateDueAmount();
    }//GEN-LAST:event_tf_whoelSalePriceKeyReleased

    private void tf_paidAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_paidAmountKeyReleased
        // TODO add your handling code here:
        calculateDueAmount();
    }//GEN-LAST:event_tf_paidAmountKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String ID = String.valueOf(jTable1.getValueAt(selectedRow, 0));
            if (Validators.isValidInt(ID)) {
                for (PurchaseOrder po : purrchaseOrders) {
                    if (po.getId() == Integer.parseInt(ID)) {
                        selectedPurchasOrder = po;
                        setPurchaseOrderFromFields(selectedPurchasOrder);
                        break;
                    }
                }
            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_makeGRNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_makeGRNActionPerformed
        // TODO add your handling code here:
        if (selectedPurchasOrder == null) {
            JOptionPane.showMessageDialog(this, "Please Select PO from Table", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            GRN grn = new GRN();
            grn.setId(0);
            grn.setPurchaseOrder(selectedPurchasOrder);
            grn.setGrnDate(new Timestamp(System.currentTimeMillis()));

            GRN cretedGRN = grnService.create(grn);
            JOptionPane.showMessageDialog(this, "Successfully GRN Created", "Success", JOptionPane.INFORMATION_MESSAGE);
            new GRNAddToStockFrame(cretedGRN).setVisible(true);

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "user Input Error", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btn_makeGRNActionPerformed

    private void tf_prodcutNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_prodcutNameKeyReleased
        // TODO add your handling code here:

        try {
            if (tf_prodcutName.getText().isEmpty()) {
                loadSelectProduct(productList);
                return;
            }
            var p = productService.searchProductsByName(tf_prodcutName.getText());

            loadSelectProduct(p);

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "user Input Error", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tf_prodcutNameKeyReleased

    private void tf_supplierSearchByContactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_supplierSearchByContactKeyReleased
        // TODO add your handling code here:
        try {
            if (tf_supplierSearchByContact.getText().isEmpty()) {
                loadSelectSupplier(suppliers);
                return;
            }
            var p = supplierService.searchByContact(tf_supplierSearchByContact.getText());
            loadSelectSupplier(p);

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "user Input Error", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tf_supplierSearchByContactKeyReleased

    private void cb_supplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_supplierItemStateChanged
        // TODO add your handling code here:
        if(selectedPurchasOrder != null){
            return;
        }
        poSearchBySupplier(getSelectedSupplierFromComboBox());
        
    }//GEN-LAST:event_cb_supplierItemStateChanged

    private void jc_notcompletePOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_notcompletePOActionPerformed
        // TODO add your handling code here:
        if(jc_notcompletePO.isSelected()){
            loadOnlyNotCompletedPos();
        }else{
            loadPurchageOrderTable(purrchaseOrders);
        }
    }//GEN-LAST:event_jc_notcompletePOActionPerformed

    private void cb_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_productActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_productActionPerformed


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
    private javax.swing.JCheckBox jc_notcompletePO;
    private javax.swing.JLabel lbl_dueAmount;
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
