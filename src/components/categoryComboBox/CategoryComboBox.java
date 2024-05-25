/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.categoryComboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.Category;
import services.CategoryService;

/**
 *
 * @author vidur
 */
public class CategoryComboBox extends javax.swing.JPanel {

    /**
     * Creates new form CategoryComboBox
     */
    CategoryService service;
    List<Category> categories = new ArrayList<>();
    Category selectedCategory;
    CategoryComboBoxInterface categoryComboBoxInterface ;

    public CategoryComboBox() {
        initComponents();
        service = new CategoryService();
        loadCategory();
    }

    private void loadCategory() {
        try {
            categories.addAll(service.getAllCategories());
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            comboBoxModel.removeAllElements();
            for (Category category : categories) {
                comboBoxModel.addElement(category.getCategory());
            }
            jComboBox1.setModel(comboBoxModel);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

    }
    
    public void onSelect(Category cat){
        selectedCategory= cat;
        System.out.println("selec CAt"+cat.getCategory());
        jComboBox1.setSelectedItem(cat.getCategory());
    }
    
    public void setCategoryComboBoxInterface(CategoryComboBoxInterface boxInterface){
        this.categoryComboBoxInterface = boxInterface;
    }
    
    public Category getSelectedCategory(){
        return selectedCategory;
    }
    
    public void refresh(){
        loadCategory();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        String selectedCategoryName = (String) jComboBox1.getSelectedItem();
            for (Category category : categories) {
                if (category.getCategory().equals(selectedCategoryName)) {
                    selectedCategory = category;
                    if (categoryComboBoxInterface != null) {
                        categoryComboBoxInterface.onSelectCategory(category);
                    }
                    break;
                }
            }
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    // End of variables declaration//GEN-END:variables
}
