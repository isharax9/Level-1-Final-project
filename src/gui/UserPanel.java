/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dto.BankDetails;
import dto.Employee;
import dto.UserType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.UserService;
import utils.Validators;

/**
 *
 * @author vidur
 */
public class UserPanel extends javax.swing.JPanel {

    /**
     * Creates new form SellProductPanel
     */
    UserService userService;
    List<Employee> employeeList;
    Employee selectedEmployee;

    public UserPanel() {
        this.userService = new UserService();
        this.employeeList = new ArrayList<>();
        initComponents();
        initData();
    }

    private void initData() {
        employeeList.clear();
        try {
            employeeList.addAll(userService.getAll());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error Title", JOptionPane.WARNING_MESSAGE);
        }
        loadCustomerTable(employeeList);
    }

    private void loadCustomerTable(List<Employee> users) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl_use_manegement.getModel();
        tableModel.setRowCount(0);
        for (Employee s : users) {
            tableModel.addRow(new Object[]{
                s.getUserId(),
                s.getFirstName(),
                s.getLastName(),
                s.getFirstName() + s.getLastName(),
                s.getUserEmail(),
                s.getUserType(),
                s.getBankaccountDetails().getBankName(),
                s.getBankaccountDetails().getBankAccountNumber(),
                s.getBankaccountDetails().getBankBranch()}
            );
        }
    }

    private void setUserToField(Employee employee) {
        System.out.println(employee);
        tf_fn_use.setText(employee.getFirstName());
        tf_ln_use.setText(employee.getLastName());
        tf_address_use.setText(employee.getAddress());
        tf_userName.setText(employee.getFirstName() + employee.getLastName());
        tf_password_use.setText("*************");
        tf_email_use.setText(employee.getUserEmail());
        jComboBox1.setSelectedItem(employee.getUserType().toString());
        dto.BankDetails b = employee.getBankaccountDetails();
        tf_bn_use.setText(b.getBankName());
        tf_accoun_number_use.setText(b.getBankAccountNumber());
        tf_branch_use.setText(b.getBankBranch());
        tf_account_name_use.setText(b.getBankAccountHolderName());
    }

    private Employee getUserFromFields() throws IllegalArgumentException {
        if (!Validators.isValidName(tf_fn_use.getText())) {
            throw new IllegalArgumentException("First Name is Invalid");
        }
        if (!Validators.isValidName(tf_ln_use.getText())) {
            throw new IllegalArgumentException("Last Name is Invalid");
        }
        if (!Validators.isValidName(tf_userName.getText())) {
            throw new IllegalArgumentException("User Name is Invalid");
        }
        if (!Validators.isValidEmail(tf_email_use.getText())) {
            throw new IllegalArgumentException("Email is Invalid");
        }
        if (!Validators.isValidPassword(tf_password_use.getText())) {
            throw new IllegalArgumentException("Password is Invalid");
        }

        if (!Validators.isValidAddress(tf_address_use.getText())) {
            throw new IllegalArgumentException("Address is Invalid");
        }
        if (!Validators.isValidBankName(tf_bn_use.getText())) {
            throw new IllegalArgumentException("Bank Name is Invalid");
        }
        if (!Validators.isValidBankBranch(tf_branch_use.getText())) {
            throw new IllegalArgumentException("Branch Name is Invalid");
        }
        if (!Validators.isValidBankAccountNumber(tf_accoun_number_use.getText())) {
            throw new IllegalArgumentException("Bank Account Number is Invalid");
        }
        if (!Validators.isValidUserName(tf_account_name_use.getText())) {
            throw new IllegalArgumentException("Branch Name is Invalid");
        }

        dto.BankDetails bankDetails = new dto.BankDetails();
        bankDetails.setId(0);
        bankDetails.setBankName(tf_bn_use.getText());
        bankDetails.setBankAccountHolderName(tf_account_name_use.getText());
        bankDetails.setBankBranch(tf_branch_use.getText());
        bankDetails.setBankAccountNumber(tf_accoun_number_use.getText());

        Employee s = new Employee();
        s.setAddress(tf_address_use.getText());
        s.setFirstName(tf_fn_use.getText());
        s.setLastName(tf_ln_use.getText());

        s.setUserType(UserType.valueOf(jComboBox1.getSelectedItem().toString()));
        s.setUserEmail(tf_email_use.getText());
        s.setBankaccountDetails(bankDetails);
        return s;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_fn_use = new javax.swing.JTextField();
        tf_ln_use = new javax.swing.JTextField();
        tf_email_use = new javax.swing.JTextField();
        tf_password_use = new javax.swing.JTextField();
        tf_address_use = new javax.swing.JTextField();
        tf_bn_use = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tf_branch_use = new javax.swing.JTextField();
        tf_accoun_number_use = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_account_name_use = new javax.swing.JTextField();
        btn_add_use = new javax.swing.JButton();
        btn_update_use = new javax.swing.JButton();
        btn_deactivate_use = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        tf_userName = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_use_manegement = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User  Management ");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);

        jLabel2.setText("Fistr Name");

        jLabel3.setText(" Last Name");

        jLabel4.setText("Email");

        jLabel5.setText("Password");

        jLabel6.setText("Use Type");

        jLabel7.setText("Address");

        tf_ln_use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ln_useActionPerformed(evt);
            }
        });

        jLabel8.setText("Bank Name");

        jLabel9.setText("Branch");

        jLabel10.setText("Bank Number");

        tf_accoun_number_use.setText(" ");
        tf_accoun_number_use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_accoun_number_useActionPerformed(evt);
            }
        });

        jLabel11.setText("Holder' Account Name");

        tf_account_name_use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_account_name_useActionPerformed(evt);
            }
        });

        btn_add_use.setText("Add ");
        btn_add_use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_useActionPerformed(evt);
            }
        });

        btn_update_use.setText("Update");
        btn_update_use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_useActionPerformed(evt);
            }
        });

        btn_deactivate_use.setText("Deactivate");

        jLabel12.setText("User Name");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASHIER", "ADMIN" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_add_use)
                        .addGap(18, 18, 18)
                        .addComponent(btn_update_use)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btn_deactivate_use))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_account_name_use, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(19, 19, 19)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tf_accoun_number_use, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_branch_use, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(tf_bn_use, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tf_fn_use)
                                .addComponent(tf_ln_use)
                                .addComponent(tf_email_use)
                                .addComponent(tf_address_use, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                .addComponent(tf_password_use, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tf_userName, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_fn_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tf_ln_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(tf_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_email_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_password_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_address_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_bn_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_branch_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_accoun_number_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_account_name_use, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add_use)
                    .addComponent(btn_update_use)
                    .addComponent(btn_deactivate_use))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_use_manegement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Use Id", "First Name", "Last Name", "User Name", "Email", "User Type", "Bank Name", "Bank Account Number", "Branch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_use_manegement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_use_manegementMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_use_manegement);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btn_add_useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_useActionPerformed
        // TODO add your handling code here:
        Employee emp = new Employee();
        emp.setFirstName(tf_fn_use.getText());
        emp.setLastName(tf_ln_use.getText());
        emp.setUserEmail(tf_email_use.getText());
        emp.setUserType(UserType.valueOf(jComboBox1.getSelectedItem().toString()));
        emp.setAddress(tf_address_use.getText());

        BankDetails bd = new BankDetails();
        bd.setBankName(tf_bn_use.getText());
        bd.setBankBranch(tf_branch_use.getText());
        bd.setBankAccountNumber(tf_accoun_number_use.getText());
        bd.setBankAccountHolderName(tf_account_name_use.getText());

        emp.setBankaccountDetails(bd);
        try {
            userService.create(emp, tf_userName.getText(), tf_password_use.getText());
            initData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_add_useActionPerformed

    private void tf_accoun_number_useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_accoun_number_useActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_accoun_number_useActionPerformed

    private void tf_account_name_useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_account_name_useActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_account_name_useActionPerformed

    private void btn_update_useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_useActionPerformed
        // TODO add your handling code here:
        Employee emp = new Employee();
        emp.setFirstName(tf_fn_use.getText());
        emp.setLastName(tf_ln_use.getText());
        emp.setUserEmail(tf_email_use.getText());
        emp.setUserType(UserType.valueOf(jComboBox1.getSelectedItem().toString()));
        emp.setAddress(tf_address_use.getText());

        BankDetails bd = new BankDetails();
        bd.setBankName(tf_bn_use.getText());
        bd.setBankBranch(tf_branch_use.getText());
        bd.setBankAccountNumber(tf_accoun_number_use.getText());
        bd.setBankAccountHolderName(tf_account_name_use.getText());

        emp.setBankaccountDetails(bd);
        emp.setUserId(selectedEmployee.getUserId());
        try {
            userService.update(emp, tf_userName.getText(), tf_password_use.getText());
            initData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_update_useActionPerformed

    private void tbl_use_manegementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_use_manegementMouseClicked
        // TODO add your handling code here:
        int SelectedRow = tbl_use_manegement.getSelectedRow();
        if (SelectedRow >= 0) {
            String ID = String.valueOf(tbl_use_manegement.getValueAt(SelectedRow, 0));
            System.out.println(ID);
            for (Employee s : employeeList) {
                if (s.getUserId() == Integer.parseInt(ID)) {
                    selectedEmployee = s;
                    setUserToField(selectedEmployee);
                    break;
                }
            }
        }
    }//GEN-LAST:event_tbl_use_manegementMouseClicked

    private void tf_ln_useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ln_useActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_ln_useActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_use;
    private javax.swing.JButton btn_deactivate_use;
    private javax.swing.JButton btn_update_use;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private components.RoundedPanel roundedPanel1;
    private javax.swing.JTable tbl_use_manegement;
    private javax.swing.JTextField tf_accoun_number_use;
    private javax.swing.JTextField tf_account_name_use;
    private javax.swing.JTextField tf_address_use;
    private javax.swing.JTextField tf_bn_use;
    private javax.swing.JTextField tf_branch_use;
    private javax.swing.JTextField tf_email_use;
    private javax.swing.JTextField tf_fn_use;
    private javax.swing.JTextField tf_ln_use;
    private javax.swing.JTextField tf_password_use;
    private javax.swing.JTextField tf_userName;
    // End of variables declaration//GEN-END:variables
}
