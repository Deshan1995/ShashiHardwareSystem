/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Supplier;

import DBconnection.javaconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Timansi_Lakshika
 */
public class CreditPayables extends javax.swing.JInternalFrame {
    Connection conn1 = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    PreparedStatement pst4 = null;
    PreparedStatement pst5 = null;
    PreparedStatement pst6 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;

    /**
     * Creates new form CreditPayables
     */
    public CreditPayables() {
        initComponents();
        conn1=javaconnect.ConnectDB();
        LoadingCustomizedOrder_CreditDetails();
        LoadingSupplierOrder_CreditDetails();
    }
    
    public void LoadingCustomizedOrder_CreditDetails(){
        try {
            //String s = "SELECT OrderID FROM CustomizedOrders WHERE (PaymentStatus = 'Completed') ";
            String s = "SELECT OrderID,CustomerID,SupplierID,Total FROM CustomizedOrders WHERE PaymentStatus='Not Completed'";
            //pst1 = conn1.prepareStatement(s);
            //rs1 = pst1.executeQuery();
            Statement stm3 = conn1.createStatement();
            rs1 = stm3.executeQuery(s);
            
            jTable1.setModel(DbUtils.resultSetToTableModel(rs1));
            
            //int OID,SID;
            //String P_Status = null;
            //String CID = null;
            //double total = 0;
            //double remain = 0;
            
            /*while(rs1.next()){
                int OID = Integer.parseInt(rs1.getString(1));
                String CID = rs1.getString(2);
                int SID = Integer.parseInt(rs1.getString(3));
                String P_Status = rs1.getString(5);
                double total = rs1.getDouble(8);
                //remain = total;
                
                //System.out.println(OID+" "+CID+" "+SID+" "+P_Status+" "+total);
                
                if(P_Status.equals("Completed")){
                    
                    System.out.println(OID+" "+CID+" "+SID+" "+P_Status+" "+total);
                    
                //String ss = "INSERT INTO CustomizedOrders_CreditDetails (OrderID,CustomerID,SupplierID,TotalCreditAmount,RemainingCreditAmount) VALUES ('"+OID+"','"+CID+"','"+SID+"','"+total+"','"+remain+"')";
                String ss = "INSERT INTO CustomizedOrders_CreditDetails (OrderID,CustomerID,SupplierID,TotalCreditAmount) VALUES ('"+OID+"','"+CID+"','"+SID+"','"+total+"')";
//                pst2 = conn1.prepareStatement(ss);
//                pst2.execute();
                    Statement stm1 = conn1.createStatement();
                    stm1.execute(ss);
                }
            }
            
            String s2 = "SELECT * FROM CustomizedOrders_CreditDetails";
            //pst3 = conn1.prepareStatement(s2);
            //rs2 = pst3.executeQuery();
            
            Statement stm2 = conn1.createStatement();
            rs2 = stm2.executeQuery(s2);
            
            jTable1.setModel(DbUtils.resultSetToTableModel(rs2));*/
            
        } catch (SQLException ex) {
            Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        } finally{
            try {
                //pst2.close();
                //pst3.close();
                //rs2.close();
                rs1.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void LoadingSupplierOrder_CreditDetails(){
        try {
            
            String s = "SELECT OrderID,SupplierID,TotalAmount FROM SupplierOrder WHERE PaymentStatus='Not Completed'";
            Statement stm3 = conn1.createStatement();
            rs2 = stm3.executeQuery(s);
            
            jTable2.setModel(DbUtils.resultSetToTableModel(rs2));
            
            
            /*//String s = "SELECT OrderID FROM CustomizedOrders WHERE (PaymentStatus = 'Completed') ";
            String s4 = "SELECT * FROM SupplierOrders";
            pst4 = conn1.prepareStatement(s4);
            rs3 = pst4.executeQuery();
            
            //jTable1.setModel(DbUtils.resultSetToTableModel(rs1));
            
            int OID,SID;
            String P_Status = null;
            double total = 0;
            //double remain = 0;
            
            while(rs3.next()){
                OID = Integer.parseInt(rs3.getString(1));
                SID = Integer.parseInt(rs3.getString(2));
                P_Status = rs3.getString(4);
                total = rs3.getDouble(7);
                //remain = total;
                
                System.out.println(OID+" "+SID+" "+P_Status+" "+total);
                
                if(P_Status.equals("Not Completed")){
                    
                //String ss = "INSERT INTO SupplierOrders_CreditDetails (OrderID,SupplierID,TotalCreditAmount,RemainingCreditAmount) VALUES ('"+OID+"','"+SID+"','"+total+"','"+remain+"')";
                String ss = "INSERT INTO SupplierOrders_CreditDetails (OrderID,SupplierID,TotalCreditAmount) VALUES ('"+OID+"','"+SID+"','"+total+"')";
                pst5 = conn1.prepareStatement(ss);
                pst5.execute();
                }
            }
            
            String s3 = "SELECT * FROM SupplierOrders_CreditDetails";
            pst6 = conn1.prepareStatement(s3);
            rs4 = pst6.executeQuery();
            
            jTable2.setModel(DbUtils.resultSetToTableModel(rs4));*/
            
        } catch (SQLException ex) {
            Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
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

        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1660, 1000));

        jTabbedPane4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "OrderID", "CustomerID", "SupplierID", "Total Credit Amount", "Paid Amount", "Remaining Amount "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Search By Supplier ID:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(120, 120, 120)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(708, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Customized Order Details", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "OrderID", "SupplierID", "Total Credit Amount", "Paid Amount", "Remaining Credit Amount"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Search by Supplier ID: ");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(209, 209, 209)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(717, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(474, 474, 474))
        );

        jTabbedPane4.addTab("Supplier Order Details", jPanel2);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Credit Payables");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int ID = Integer.parseInt(jTextField1.getText());
            
            String str = "SELECT OrderID,CustomerID,SupplierID,Total FROM CustomizedOrders WHERE (SupplierID = '"+ID+"' AND PaymentStatus = 'Not Completed')";
            pst3 = conn1.prepareStatement(str); 
            rs3 = pst3.executeQuery();
            
            jTable1.setModel(DbUtils.resultSetToTableModel(rs3));
                    
            } catch (SQLException ex) {
                Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,ex);
        } finally{
            try {
                pst3.close();
                rs3.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            int ID = Integer.parseInt(jTextField2.getText());
            
            String str = "SELECT OrderID,SupplierID,TotalAmount FROM SupplierOrder WHERE (SupplierID = '"+ID+"' AND PaymentStatus = 'Not Completed')";
            pst3 = conn1.prepareStatement(str); 
            rs3 = pst3.executeQuery();
            
            jTable2.setModel(DbUtils.resultSetToTableModel(rs3));
                    
            } catch (SQLException ex) {
                Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,ex);
        } finally{
            try {
                pst3.close();
                rs3.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LoadingCustomizedOrder_CreditDetails();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LoadingSupplierOrder_CreditDetails();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
