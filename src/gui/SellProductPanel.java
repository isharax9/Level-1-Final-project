/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dto.Invoice;
import dto.InvoiceItem;
import dto.Product;
import dto.Stock;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.InvoiceService;
import services.PrintService;
import services.StockService;
import utils.Validators;

/**
 *
 * @author vidur
 */
public class SellProductPanel extends javax.swing.JPanel {

    /**
     * Creates new form SellProductPanel
     */
    List<Stock> stocks = new ArrayList<>();
    List<InvoiceItem> invoiceItems = new ArrayList<>();
    StockService stockService = new StockService();
    Stock selectedStock;
    InvoiceItem selectedInvoiceItem;
    InvoiceService invoiceService = new InvoiceService();
    PrintService printService = new PrintService();

    public SellProductPanel() {
        initComponents();
        initData();
    }

    void initData() {
        stocks.clear();
        try {
            stocks.addAll(stockService.getAllNotExpriedAndInStock());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadTable(stocks);
    }

    void loadTable(List<Stock> stks) {
        System.out.println(stks.size());
        DefaultTableModel tableModel = (DefaultTableModel) StockTable.getModel();
        tableModel.setRowCount(0); // Clear existing rows

        for (Stock stock : stks) {
            tableModel.addRow(new Object[]{
                stock.getStockBarcode(),
                stock.getGrn().getPurchaseOrder().getProduct().getProductName(),
                stock.getUnitPrice(),
                stock.getAvailableQty(),
                stock.getMnfDate(),
                stock.getExpDate()
            });
        }
    }

    void loadInvoceTable(List<InvoiceItem> items) {
        DefaultTableModel tableModel = (DefaultTableModel) InvoiceTable.getModel();
        tableModel.setRowCount(0); // Clear existing rows

        for (InvoiceItem i : items) {
            tableModel.addRow(new Object[]{
                i.getStock().getStockBarcode(),
                i.getStock().getGrn().getPurchaseOrder().getProduct().getProductName(),
                i.getInvoiceItemQty(),
                i.getStock().getUnitPrice(),
                i.getInvoiceItemDiscount(),
                (i.getStock().getUnitPrice() - i.getInvoiceItemDiscount()) * i.getInvoiceItemQty()

            });
        }
    }

    void addToInvoice(Stock s) {
        try {
            var item = getInvoiceItemFromFieds();
//            make a logic to same stock barcode invoice item need add to that qty+itemqty to invoice item, else add as new invoice item

            boolean itemExists = false;
            for (InvoiceItem invoiceItem : invoiceItems) {
                if (invoiceItem.getStock().getStockBarcode() == item.getStock().getStockBarcode()) {
                    // If the stock barcode matches, update the quantity
                    invoiceItem.setInvoiceItemQty(invoiceItem.getInvoiceItemQty() + item.getInvoiceItemQty());
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) {
                // If the item does not exist, add it as a new item
                invoiceItems.add(item);
            }
//            invoiceItems.add(item);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        loadInvoceTable(invoiceItems);
    }

    private double getCash() {
        return 10;
    }

    private Invoice getInvoice() {
        double total = 0;
        double discount = 0;

        double cash = getCash();
        double balance = 0;

        for (var i : invoiceItems) {
            total += i.getStock().getUnitPrice() * i.getInvoiceItemQty();
            discount += i.getInvoiceItemDiscount() * i.getInvoiceItemQty();

        }
        double grandTotal = total - discount;
        if (cash > 0) {
            balance = grandTotal - cash;
        }
        Invoice i = new Invoice();
        i.setId(0);
        i.setBalance(balance);
        i.setEmployee(Login.auth.getEmployee());
        i.setGrandTotal(grandTotal);
        i.setInvoiceDate(LocalDateTime.now());
        i.setPaidAmount(cash);
        i.setTotal(total);
        i.setItems(invoiceItems);

        return i;

    }

    private void loadTF() {
        var i = getInvoice();
        ItemCountTextField.setText(i.getItems().size() + "");
        TotalTextField.setText(i.getTotal() + "");
        InvoiceDiscountTextField.setText(i.getTotalDiscount() + "");
        GrandTotalTextField.setText(i.getGrandTotal() + "");
        CashTextField.setText(i.getPaidAmount() + "");
        BalenceTextField.setText(i.getPaidAmount() - i.getGrandTotal() + "");

    }

    private void loadStockToField(Stock stock) {
        BarcodeTextField.setText(stock.getStockBarcode() + "");
        ProductNameTextField.setText(stock.getGrn().getPurchaseOrder().getProduct().getProductName());
        QtyTextField.setText("1");
        lbl_unit.setText(stock.getGrn().getPurchaseOrder().getProduct().getUnit().getUnit());
        DiscountTextField.setText(stock.getDiscount() + "");
    }

    private InvoiceItem getInvoiceItemFromFieds() {
        if (!Validators.isValidDouble(QtyTextField.getText())) {
            throw new IllegalArgumentException("Invalid Qty");
        }
        if (!Validators.isValidDouble(DiscountTextField.getText())) {
            throw new IllegalArgumentException("Invalid Discount");
        }
        double discount = Double.parseDouble(DiscountTextField.getText());
        double qty = Double.parseDouble(QtyTextField.getText());
        if (discount > selectedStock.getDiscount()) {
            throw new IllegalArgumentException("Maximum Discount Exceeded");
        }

        InvoiceItem item = new InvoiceItem();
        item.setStock(selectedStock);
        item.setInvoiceItemQty(qty);
        item.setInvoiceItemDiscount(discount);

        return item;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BarcodeTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ProductNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        QtyTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DiscountTextField = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        ItemCountTextField = new javax.swing.JTextField();
        TotalTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        InvoiceDiscountTextField = new javax.swing.JTextField();
        GrandTotalTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CashTextField = new javax.swing.JTextField();
        BalenceTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jToggleButton3 = new javax.swing.JToggleButton();
        lbl_unit = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StockTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        InvoiceTable = new javax.swing.JTable();
        ClearInvoiceButton = new javax.swing.JToggleButton();
        DeleteInvoiceItemButton = new javax.swing.JToggleButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELL Products");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Barcode");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Product Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Qty");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Discount");

        jToggleButton1.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton1.setText("Add To Invoice");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setText("Clear");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Item Count :");

        ItemCountTextField.setEnabled(false);

        TotalTextField.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Total :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Discount :");

        InvoiceDiscountTextField.setEnabled(false);
        InvoiceDiscountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvoiceDiscountTextFieldActionPerformed(evt);
            }
        });

        GrandTotalTextField.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Grand Total :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Cash :");

        CashTextField.setEnabled(false);

        BalenceTextField.setEnabled(false);
        BalenceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalenceTextFieldActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Balence :");

        jToggleButton3.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        jToggleButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton3.setText("Print Invoice");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        lbl_unit.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BarcodeTextField)
                            .addComponent(ProductNameTextField)
                            .addComponent(DiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(QtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_unit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(ItemCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(InvoiceDiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GrandTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CashTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BalenceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(BarcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ProductNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_unit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(QtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ItemCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(InvoiceDiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(GrandTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(CashTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(BalenceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jToggleButton3)
                .addGap(23, 23, 23))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Stock Table");

        StockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode", "Product Name", "Selling Price", "Availble Qty", "MFG", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StockTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StockTableMouseClicked(evt);
            }
        });
        StockTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                StockTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(StockTable);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Invoice");

        InvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Barcode", "Product Name ", "Qty", "Unit Price", "Discount", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        InvoiceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InvoiceTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(InvoiceTable);

        ClearInvoiceButton.setBackground(new java.awt.Color(255, 51, 51));
        ClearInvoiceButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ClearInvoiceButton.setForeground(new java.awt.Color(255, 255, 255));
        ClearInvoiceButton.setText("Clear Invoice");
        ClearInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearInvoiceButtonActionPerformed(evt);
            }
        });

        DeleteInvoiceItemButton.setBackground(new java.awt.Color(204, 0, 0));
        DeleteInvoiceItemButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteInvoiceItemButton.setForeground(new java.awt.Color(255, 255, 255));
        DeleteInvoiceItemButton.setText("Del Selected Invoice Item");
        DeleteInvoiceItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteInvoiceItemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(454, 498, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ClearInvoiceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeleteInvoiceItemButton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeleteInvoiceItemButton)
                    .addComponent(ClearInvoiceButton))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        addToInvoice(selectedStock);
        loadTF();
    }//GEN-LAST:event_jToggleButton1ActionPerformed
    private void clearTopFields() {
        BarcodeTextField.setText("");
        ProductNameTextField.setText("");
        QtyTextField.setText("");
        DiscountTextField.setText("");
    }
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        clearTopFields();
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void BalenceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalenceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BalenceTextFieldActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:

        try {
            Invoice i = getInvoice();
            invoiceService.create(i);
            PrintService.PrintInvoice(i);
            JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            invoiceItems.clear();
            loadInvoceTable(invoiceItems);
            loadTF();
            clearTopFields();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "DB Erorr", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "User Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void StockTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StockTableKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_StockTableKeyReleased

    private void StockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StockTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = StockTable.getSelectedRow();
        if (selectedRow >= 0) {
            int barcode = (int) StockTable.getModel().getValueAt(selectedRow, 0);
            for (Stock s : stocks) {
                if (s.getStockBarcode() == barcode) {
                    selectedStock = s;
                    loadStockToField(selectedStock);
                    return;
                }
            }
        }
    }//GEN-LAST:event_StockTableMouseClicked

    private void InvoiceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InvoiceTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = InvoiceTable.getSelectedRow();
        if (selectedRow >= 0) {
            int barcode = (int) InvoiceTable.getModel().getValueAt(selectedRow, 0);
            for (var i : invoiceItems) {
                if (i.getStock().getStockBarcode() == barcode) {
                    selectedInvoiceItem = i;
                }
            }
        }
    }//GEN-LAST:event_InvoiceTableMouseClicked

    private void DeleteInvoiceItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteInvoiceItemButtonActionPerformed
        // TODO add your handling code here:
        if (selectedInvoiceItem != null) {
            invoiceItems.remove(selectedInvoiceItem);
            selectedInvoiceItem = null; // Reset the selected item
            loadInvoceTable(invoiceItems); // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "No item selected", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        loadTF();
    }//GEN-LAST:event_DeleteInvoiceItemButtonActionPerformed

    private void InvoiceDiscountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvoiceDiscountTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InvoiceDiscountTextFieldActionPerformed

    private void ClearInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearInvoiceButtonActionPerformed
        // TODO add your handling code here:
        invoiceItems.clear();
        loadInvoceTable(invoiceItems);
        loadTF();
    }//GEN-LAST:event_ClearInvoiceButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BalenceTextField;
    private javax.swing.JTextField BarcodeTextField;
    private javax.swing.JTextField CashTextField;
    private javax.swing.JToggleButton ClearInvoiceButton;
    private javax.swing.JToggleButton DeleteInvoiceItemButton;
    private javax.swing.JTextField DiscountTextField;
    private javax.swing.JTextField GrandTotalTextField;
    private javax.swing.JTextField InvoiceDiscountTextField;
    private javax.swing.JTable InvoiceTable;
    private javax.swing.JTextField ItemCountTextField;
    private javax.swing.JTextField ProductNameTextField;
    private javax.swing.JTextField QtyTextField;
    private javax.swing.JTable StockTable;
    private javax.swing.JTextField TotalTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JLabel lbl_unit;
    private components.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
