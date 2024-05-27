/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import dto.Auth;
import dto.UserType;
import static gui.Login.auth;

/**
 *
 * @author vidur
 */
public class DashboardFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdminPanel
     */
    public DashboardFrame() {
        initComponents();
        lbl_frameTitle.setText(Login.auth.getAuthType().name()+" PANEL");
        lb_adminGreeting.setText("Hello, "+Login.auth.getEmployee().getFirstName()+" !");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel2 = new components.RoundedPanel();
        lbl_frameTitle = new javax.swing.JLabel();
        roundedPanel1 = new components.RoundedPanel();
        lb_adminGreeting = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        sellProductPanel1 = new gui.SellProductPanel();
        productsPanel1 = new gui.ProductsPanel();
        purchuseOrderPanel1 = new gui.PurchuseOrderPanel();
        stocksPanel1 = new gui.StocksPanel();
        suppliersPanel1 = new gui.SuppliersPanel();
        gRNPanel1 = new gui.GRNPanel();
        customerPanel1 = new gui.CustomerPanel();
        invoicePanel1 = new gui.InvoicePanel();
        userPanel1 = new gui.UserPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roundedPanel2.setBackground(new java.awt.Color(221, 231, 244));
        roundedPanel2.setLayout(new java.awt.FlowLayout());

        lbl_frameTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_frameTitle.setText("ADMIN PANEL");
        roundedPanel2.add(lbl_frameTitle);

        roundedPanel1.setBackground(new java.awt.Color(0, 122, 255));

        lb_adminGreeting.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_adminGreeting.setForeground(new java.awt.Color(255, 255, 255));
        lb_adminGreeting.setText("Hello Vidura !");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(lb_adminGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_adminGreeting)
                .addContainerGap())
        );

        roundedPanel2.add(roundedPanel1);

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.addTab("Sell Products", sellProductPanel1);
        jTabbedPane2.addTab("Products", productsPanel1);
        jTabbedPane2.addTab("POs", purchuseOrderPanel1);
        jTabbedPane2.addTab("Stock", stocksPanel1);
        jTabbedPane2.addTab("Suppliers", suppliersPanel1);
        jTabbedPane2.addTab("GRNs", gRNPanel1);
        jTabbedPane2.addTab("Customers", customerPanel1);
        jTabbedPane2.addTab("Invoices", invoicePanel1);
        jTabbedPane2.addTab("Users", userPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addGap(91, 91, 91))
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
         Login.auth = new Auth("admin", "123", UserType.ADMIN);
         try{
         auth.isAuthenticated();
         }catch(Exception ex){
             
         }
         com.formdev.flatlaf.themes.FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.CustomerPanel customerPanel1;
    private gui.GRNPanel gRNPanel1;
    private gui.InvoicePanel invoicePanel1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lb_adminGreeting;
    private javax.swing.JLabel lbl_frameTitle;
    private gui.ProductsPanel productsPanel1;
    private gui.PurchuseOrderPanel purchuseOrderPanel1;
    private components.RoundedPanel roundedPanel1;
    private components.RoundedPanel roundedPanel2;
    private gui.SellProductPanel sellProductPanel1;
    private gui.StocksPanel stocksPanel1;
    private gui.SuppliersPanel suppliersPanel1;
    private gui.UserPanel userPanel1;
    // End of variables declaration//GEN-END:variables
}
