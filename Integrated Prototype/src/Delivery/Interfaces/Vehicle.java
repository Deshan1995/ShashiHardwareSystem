package Delivery.Interfaces;

//import Delivery.Model.DBConnection;
import DBconnection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manushi-PC
 */
public class Vehicle extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pstupdate = null;
    ResultSet rs = null;
    
    public void loadvehicleTable() {
        try {
            
        Statement stm=conn.createStatement();
        String SQL="SELECT * FROM Vehicle";
        ResultSet rs=stm.executeQuery(SQL);

            vehicleTbl.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * Creates new form Vehicle
     */
    public Vehicle() {
        initComponents();
        conn = DBconnect.connectDb();
        loadvehicleTable();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vehicleTbl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        veninum = new javax.swing.JTextField();
        typetxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        costTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        vehicleTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        vehicleTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vehicleTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vehicleTbl);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Vehicle No");

        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        addBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        editBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Type");

        veninum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                veninumActionPerformed(evt);
            }
        });
        veninum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                veninumKeyTyped(evt);
            }
        });

        typetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                typetxtKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cost for Agencies");

        costTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                costTxtKeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Demo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(addBtn)
                        .addGap(34, 34, 34)
                        .addComponent(editBtn)
                        .addGap(32, 32, 32)
                        .addComponent(deleteBtn)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(veninum)
                            .addComponent(typetxt, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(costTxt))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(veninum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(typetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(costTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn)
                            .addComponent(editBtn)
                            .addComponent(deleteBtn)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("   Vehicle Management");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 981, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 251, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:  
        
        String vehicleNo = veninum.getText();
        String type = typetxt.getText();
        double cost = Double.parseDouble(costTxt.getText());
        if((veninum.getText().equals(""))||(type.equals(""))||(costTxt.getText().equals(""))){
            JOptionPane.showMessageDialog(null, "No empty fields allowed", "Empty field",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {

                String query = "INSERT INTO Vehicle values( '" + vehicleNo + "', '" + type + "', 'Available', '"+cost+"') ";
                pst = conn.prepareStatement(query);
                pst.execute();

                JOptionPane.showMessageDialog(rootPane, "Sucessfully Inserted", "Login", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

            finally{
                if (pst != null){
                    try {
                        pst.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DeliveryOut.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DeliveryOut.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            loadvehicleTable();  
            clear();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void vehicleTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehicleTblMouseClicked
        // TODO add your handling code here:
        int row = vehicleTbl.getSelectedRow();
        
        String num = vehicleTbl.getValueAt(row, 0).toString();
        String type = vehicleTbl.getValueAt(row, 1).toString();
        String cost = vehicleTbl.getValueAt(row, 3).toString();
        
        veninum.setText(num);
        typetxt.setText(type);
        costTxt.setText(cost);
    }//GEN-LAST:event_vehicleTblMouseClicked

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // edit details
        if(veninum.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vehicle not selected", "Empty field",JOptionPane.ERROR_MESSAGE);
        }
        else{
                        
            int value = JOptionPane.showConfirmDialog(null, "Do you want to save changes?");
            Statement stm = null;
        
            if(value == 0){

                try {
                    String num= veninum.getText();
                    String type = typetxt.getText();
                    double cost = Double.parseDouble(costTxt.getText());

                    String SQL = "Update Vehicle set vehiType ='"+type+"', agencyCost ='"+cost+"' where vehiNo = '"+num+"'";
                    stm= conn.createStatement();
                    stm.executeUpdate(SQL);
                } catch (SQLException ex) {
                    Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    if (stm != null){
                        try {
                            stm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DeliveryOut.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        
            loadvehicleTable();  
            clear();
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // Delete button
        if(veninum.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vehicle not selected", "Empty field",JOptionPane.ERROR_MESSAGE);
        }
        else{
        int value = JOptionPane.showConfirmDialog(null, "Do you want to delete this entry?");
            Statement stm = null;
            if(value == 0){

                try {
                    String num= veninum.getText();
                    String type = typetxt.getText();

                    String SQL = "DELETE FROM Vehicle where vehiNo = '"+num+"'";
                    stm= conn.createStatement();
                    stm.executeUpdate(SQL);
                } catch (SQLException ex) {
                    Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    if (stm != null){
                        try {
                            stm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DeliveryOut.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            loadvehicleTable();  
            clear();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void veninumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_veninumKeyTyped
        // TODO add your handling code here:
       
    }//GEN-LAST:event_veninumKeyTyped

    private void typetxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typetxtKeyTyped
        // Do not allow numeric values
        char c=evt.getKeyChar();

        if(!Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Invalid Input");
        }
    }//GEN-LAST:event_typetxtKeyTyped

    private void veninumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_veninumActionPerformed
        // only allow valid vehicle numbers
        validateVehicleNo();
    }//GEN-LAST:event_veninumActionPerformed

    private void costTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costTxtKeyTyped
        // Do not allow characters
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Invalid Input");
        }
    }//GEN-LAST:event_costTxtKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String vehicleNo = "AE7588";
        String type = "Lorry";
        double cost = 8000;
        
        try {

            String query = "INSERT INTO Vehicle values( '" + vehicleNo + "', '" + type + "', 'Available', '"+cost+"') ";
            pst = conn.prepareStatement(query);
            pst.execute();

            JOptionPane.showMessageDialog(rootPane, "Sucessfully Inserted", "Login", JOptionPane.INFORMATION_MESSAGE);
              
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
        finally{
            if (pst != null){
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeliveryOut.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeliveryOut.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        loadvehicleTable();  
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void clear(){
    
        veninum.setText(null);
        typetxt.setText(null);
        costTxt.setText(null);
    
    }
    
    private void validateVehicleNo(){
    
        String num = veninum.getText();
        char [] arr = num.toCharArray();      
        int len = arr.length;
        for (int i =0; i <len; i++) {
            char temp = arr[i];
            boolean a;
            a = Character.isDigit(temp);
            if((i<2)&&(a==true)){
                JOptionPane.showMessageDialog(null,"Invalid Vehicle Number");
            }
            else if((i>2)&&(a==false)){
                JOptionPane.showMessageDialog(null,"Invalid Vehicle Number");
            }
        }        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField costTxt;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField typetxt;
    private javax.swing.JTable vehicleTbl;
    private javax.swing.JTextField veninum;
    // End of variables declaration//GEN-END:variables
}

















