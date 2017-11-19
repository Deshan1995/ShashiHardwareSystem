/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Supplier;

import DBconnection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    PreparedStatement pst7 = null;
    PreparedStatement pst8 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;
    ResultSet rs5 = null;

    /**
     * Creates new form CreditPayables
     */
    public CreditPayables() {
        initComponents();
        conn1 = DBconnect.connectDb();
        LoadingCustomizedOrder_CreditPayables();
        LoadingSupplierOrder_CreditPayables();
    }
     public String getNumDate(java.util.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
            String numberDate = sdf.format(date);
            return numberDate;
        }catch(Exception e){
            return "E";
        }
        
        
        
    }
    
    public java.util.Date getStringDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date longDate =null;
        try {
            longDate = (Date) (sdf.parse(date));
            //System.out.println(sdf.format(jDateChooser1.getDate()) );        // TODO add your handling code here:
        } catch (ParseException ex) {
            //Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println();
        }
        
        return  longDate;

    }
    
    public void LoadingCustomizedOrder_CreditPayables(){
        try {
            String s = "SELECT OrderID,CustomerID,SupplierID,Total,TotalPaidAmount FROM CustomizedOrders WHERE PaymentStatus='Not Completed'";
            
            Statement stm3 = conn1.createStatement();
            rs1 = stm3.executeQuery(s);
            
            jTable1.setModel(DbUtils.resultSetToTableModel(rs1));
            
            
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
    
    public void LoadingSupplierOrder_CreditPayables(){
        try {
            
            String s = "SELECT OrderID,SupplierID,TotalAmount,TotalPaidAmount FROM SupplierOrder WHERE PaymentStatus='Not Completed'";
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
    
    public void LoadingCustomizedOrder_Installments(){
        try {
            String id = txt_LabelOID.getText();
            
            String s = "SELECT InstallmentNo, PayingAmount,PaymentDate,PaymentType FROM CustomizedOrders_CreditDetails WHERE OrderID='"+id+"'";
            Statement stm3 = conn1.createStatement();
            rs2 = stm3.executeQuery(s);
            
            jTable3.setModel(DbUtils.resultSetToTableModel(rs2));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public void LoadingSupplierOrder_Installments(){
        try {
            String id = LabelOID.getText();
            
            String s = "SELECT InstallmentNo, PayingAmount,PaymentDate,PaymentType FROM SuplierOrders_CreditDetails WHERE OrderID='"+id+"'";
            Statement stm3 = conn1.createStatement();
            rs2 = stm3.executeQuery(s);
            
            jTable4.setModel(DbUtils.resultSetToTableModel(rs2));
            
            
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
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_LabelOID = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PayingAmount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        InstallmentNo = new javax.swing.JTextField();
        PaymentType = new javax.swing.JComboBox<>();
        PaymentDate = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        LabelOID = new javax.swing.JLabel();
        InstallmentNo1 = new javax.swing.JTextField();
        PayingAmount1 = new javax.swing.JTextField();
        PaymentType1 = new javax.swing.JComboBox<>();
        PaymentDate1 = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1660, 1000));

        jTabbedPane4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "OrderID", "CustomerID", "SupplierID", "Total Credit Amount", "TotalPaid Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Search By Supplier ID:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                .addGap(426, 426, 426)
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

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("   Update Credit Levels");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Order ID");

        txt_LabelOID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Paying Amount");

        PayingAmount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Installment Number");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Payment Date");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Payment Type");

        InstallmentNo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        PaymentType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cheque", "Cash" }));

        PaymentDate.setDateFormatString("dd-MM-yyyy");

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Add Payment");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Installment No", "Paying Amount", "Payment Date", "Payment Type"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1615, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(56, 56, 56)
                                        .addComponent(InstallmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(212, 212, 212)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_LabelOID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PayingAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addGap(99, 99, 99)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(PaymentDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PaymentType, 0, 161, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_LabelOID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(InstallmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6))
                            .addComponent(PayingAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(PaymentDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(PaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(29, 29, 29))))
        );

        jTabbedPane4.addTab("Customized Order Details", jPanel1);

        jPanel2.setLayout(null);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "OrderID", "SupplierID", "Total Credit Amount", "Paid Amount"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(12, 102, 1607, 240);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Search by Supplier ID: ");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(128, 32, 183, 22);

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(jTextField2);
        jTextField2.setBounds(321, 29, 168, 28);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(496, 28, 87, 31);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(1040, 28, 93, 31);

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("  Update Credit Levels");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(0, 350, 1627, 70);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("OrderID");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(10, 450, 100, 22);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Installment No");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(10, 510, 140, 22);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Paying Amount");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(10, 570, 140, 22);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Payment Type");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(10, 640, 130, 22);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Payment Date");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(10, 710, 120, 22);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Add Payment");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);
        jButton7.setBounds(110, 770, 160, 31);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Installment No", "Paying Amount", "Payment Type", "Payment Date"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel2.add(jScrollPane4);
        jScrollPane4.setBounds(370, 430, 1250, 380);

        LabelOID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(LabelOID);
        LabelOID.setBounds(200, 450, 160, 20);

        InstallmentNo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(InstallmentNo1);
        InstallmentNo1.setBounds(200, 510, 160, 28);

        PayingAmount1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(PayingAmount1);
        PayingAmount1.setBounds(200, 570, 160, 28);

        PaymentType1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PaymentType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cheque", "Cash" }));
        jPanel2.add(PaymentType1);
        PaymentType1.setBounds(200, 640, 160, 30);

        PaymentDate1.setDateFormatString("dd-MM-yyyy");
        jPanel2.add(PaymentDate1);
        PaymentDate1.setBounds(200, 710, 160, 22);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1064, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7);
        jPanel7.setBounds(110, 10, 1070, 60);

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
        LoadingCustomizedOrder_CreditPayables();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LoadingSupplierOrder_CreditPayables();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //jButton1.setEnabled(false);
        int row=jTable1.getSelectedRow();
            
        String oid=jTable1.getValueAt(row,0).toString();
        System.out.print(oid);
        txt_LabelOID.setText(oid);
        
                
        
        try {
            
            //String ss1 = "SELECT OrderID, PaymentAmount, PaymentType, PaymentDate, InstallmentNo FROM CustomizedOrders_CreditDetails WHERE OrderID=oid";
            String ss1 = "SELECT * FROM CustomizedOrders_CreditDetails WHERE OrderID='"+oid+"'";
            pst3 = conn1.prepareStatement(ss1);
            rs3 = pst3.executeQuery();
            
            int row1 = -1;
            
            while(rs3.next()){
                row1++;
                System.out.println(row1);
            }
            
            if(row1==-1){
                InstallmentNo.setText(String.valueOf("1"));
            }
            else{
                int instNo = row1+2;
                InstallmentNo.setText(Integer.toString(instNo));
                }
            
            LoadingCustomizedOrder_Installments();
           
        } catch (SQLException ex) {
            Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            
            // TODO add your handling code here:
            int row=jTable1.getSelectedRow();
            
            String oid = txt_LabelOID.getText();
            String instNo = InstallmentNo.getText();
            Double PayAmount = Double.parseDouble(PayingAmount.getText());
            Date pDate = PaymentDate.getDate();
            
            String payDate = getNumDate(pDate);
            String pType = PaymentType.getSelectedItem().toString();
            Double TotalCredit = Double.parseDouble(jTable1.getValueAt(row,3).toString());
            Double PreTotalPaid = Double.parseDouble(jTable1.getValueAt(row,4).toString());
            Double TotalPaid = PayAmount + PreTotalPaid;
            
        if(oid.equals("") || instNo.equals("") || PayAmount==0 || payDate.equals("") || pType.equals("")){
            JOptionPane.showMessageDialog(null,"All the text fields should be filled!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
            String ss2 = "INSERT INTO CustomizedOrders_CreditDetails (OrderID, InstallmentNo, PayingAmount, PaymentDate, PaymentType) VALUES ('"+oid+"','"+instNo+"','"+PayAmount+"','"+payDate+"','"+pType+"')";
            pst7 = conn1.prepareStatement(ss2);
            pst7.execute();
            
            LoadingCustomizedOrder_Installments();
            
            JOptionPane.showMessageDialog(null,"Payment successfully added!","Added", JOptionPane.INFORMATION_MESSAGE);
            
            String ss3 = "UPDATE CustomizedOrders SET TotalPaidAmount = '"+TotalPaid+"' WHERE OrderID='"+oid+"'";
            pst7 = conn1.prepareStatement(ss3);
            pst7.execute();
            LoadingCustomizedOrder_CreditPayables();
            
            
            if(Double.compare(TotalCredit,TotalPaid)==0){
                
                String ss4 = "UPDATE CustomizedOrders SET PaymentStatus = \"Completed\" WHERE OrderID ='"+oid+"'";
                pst7 = conn1.prepareStatement(ss4);
                pst7.execute();
                
                String ss5 = "UPDATE CustomizedOrders SET EndDate = '"+payDate+"' WHERE OrderID ='"+oid+"'";
                pst7 = conn1.prepareStatement(ss5);
                pst7.execute();
                System.out.println("near supplier update query" + payDate);
                
                LoadingCustomizedOrder_CreditPayables();
            }
            
            txt_LabelOID.setText("");
            InstallmentNo.setText("");
            PayingAmount.setText("");
            PaymentType.setSelectedItem("");
            PaymentDate.setDate(null);
           
        } catch (SQLException ex) {
            Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error in adding the payment!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }finally{
                try {
                    pst7.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int row=jTable2.getSelectedRow();
            
        String oid=jTable2.getValueAt(row,0).toString();
        LabelOID.setText(oid);
        
                
        
        try {
            String ss1 = "SELECT * FROM SuplierOrders_CreditDetails WHERE OrderID='"+oid+"'";
            pst3 = conn1.prepareStatement(ss1);
            rs3 = pst3.executeQuery();
            
            int row1 = -1;
            
            while(rs3.next()){
                row1++;
                System.out.println(row1);
            }
            
            if(row1==-1){
                InstallmentNo1.setText(String.valueOf("1"));
            }
            else{
                int instNo = row1+2;
                InstallmentNo1.setText(Integer.toString(instNo));
                }
            
            LoadingSupplierOrder_Installments();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        int row=jTable2.getSelectedRow();
            
            String oid1 = LabelOID.getText();
            String instNo1 = InstallmentNo1.getText();
            Double PayAmount1 = Double.parseDouble(PayingAmount1.getText());
            Date pDate1 = PaymentDate1.getDate();
            String payDate1 = getNumDate(pDate1);
            String pType1 = PaymentType1.getSelectedItem().toString();
            Double TotalCredit1 = Double.parseDouble(jTable2.getValueAt(row,2).toString());
            Double PreTotalPaid1 = Double.parseDouble(jTable2.getValueAt(row,3).toString());
            Double TotalPaid1 = PayAmount1 + PreTotalPaid1;
            
            
        if(oid1.equals("") || instNo1.equals("") || PayAmount1==0 || payDate1.equals("") || pType1.equals("")){
            JOptionPane.showMessageDialog(null,"All the text fields should be filled!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
            String ss2 = "INSERT INTO SuplierOrders_CreditDetails (OrderID, InstallmentNo, PayingAmount, PaymentDate, PaymentType) VALUES ('"+oid1+"','"+instNo1+"','"+PayAmount1+"','"+payDate1+"','"+pType1+"')";
            pst7 = conn1.prepareStatement(ss2);
            pst7.execute();
            
            LoadingSupplierOrder_Installments();
            
            
            JOptionPane.showMessageDialog(null,"Payment successfully added!","Added", JOptionPane.INFORMATION_MESSAGE);
            
            String ss3 = "UPDATE SupplierOrder SET TotalPaidAmount = '"+TotalPaid1+"' WHERE OrderID='"+oid1+"'";
            pst7 = conn1.prepareStatement(ss3);
            pst7.execute();
            LoadingSupplierOrder_CreditPayables();
            
            
            if(Double.compare(TotalCredit1,TotalPaid1)==0){
            
                String ss4 = "UPDATE SupplierOrder SET PaymentStatus = \"Completed\" WHERE OrderID='"+oid1+"'";
                pst7 = conn1.prepareStatement(ss4);
                pst7.execute();
                
                String ss5 = "UPDATE SupplierOrder SET EndDate = '"+payDate1+"'  WHERE OrderID='"+oid1+"'";
                pst8 = conn1.prepareStatement(ss5);
                pst8.execute();
                
                LoadingSupplierOrder_CreditPayables();
            }
            
            LabelOID.setText("");
            InstallmentNo1.setText("");
            PayingAmount1.setText("");
            PaymentType1.setSelectedItem("");
            PaymentDate1.setDate(null);
           
        } catch (SQLException ex) {
            Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error in adding the payment!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }
            catch(Exception e){
                System.out.println(e);
            }
            finally{
                try {
                    pst7.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CreditPayables.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int row=jTable4.getSelectedRow();
            
            String oid1 = LabelOID.getText();
            String instNo1 = InstallmentNo1.getText();
            Double PayAmount1 = Double.parseDouble(PayingAmount1.getText());
            Date pDate1 = PaymentDate1.getDate();
            String payDate1 = DateFormat.getDateInstance().format(pDate1);
            String pType1 = PaymentType1.getSelectedItem().toString();
            //Double TotalCredit1 = Double.parseDouble(jTable2.getValueAt(row,2).toString());
            //Double PreTotalPaid1 = Double.parseDouble(jTable2.getValueAt(row,3).toString());
            //Double TotalPaid1 = PayAmount1 + PreTotalPaid1;
            
            LabelOID.setText(oid1);
            InstallmentNo1.setText(instNo1);
            PayingAmount1.setText(Double.toString(PayAmount1));
            PaymentDate1.setDate(pDate1);
            PaymentType1.setSelectedItem(pType1);
    }//GEN-LAST:event_jTable4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField InstallmentNo;
    private javax.swing.JTextField InstallmentNo1;
    private javax.swing.JLabel LabelOID;
    private javax.swing.JTextField PayingAmount;
    private javax.swing.JTextField PayingAmount1;
    private com.toedter.calendar.JDateChooser PaymentDate;
    private com.toedter.calendar.JDateChooser PaymentDate1;
    private javax.swing.JComboBox<String> PaymentType;
    private javax.swing.JComboBox<String> PaymentType1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel txt_LabelOID;
    // End of variables declaration//GEN-END:variables
}
