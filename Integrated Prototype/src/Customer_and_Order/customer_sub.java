/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer_and_Order;

import DBconnection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Bhanuka Yapa
 */
public class customer_sub extends javax.swing.JInternalFrame {

    /**
     * Creates new form customer_sub
     */
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public customer_sub() {
        initComponents();
        
        //conn = dbConnection.open();     //establish db connection
        conn = DBconnect.connectDb();
        System.out.println("Connected to database");
        
        rdBtn_ID.setSelected(true);
        btn_backToOrder.setVisible(false);
        
        txt_cusID2.setEditable(false);
        txt_regDate2.setEditable(false);
        txt_noOfVisits2.setEditable(false);
        txt_totPaid2.setEditable(false);
        txt_totDebt2.setEditable(false);
        
        loadCustomerTable1();
        loadCustomerTable2();
    }
    
    public void finalize() throws SQLException {
        conn.close();
    }
    
    public Boolean validateTextfields(){
        
        String id =txt_cusID.getText();
        String name = txt_cusName.getText();
        String numOfV = txt_numOfVisits.getText();
        String add = txtA_cusAdd.getText();
        
        //validating for empty fields
        if((id.isEmpty())||(name.isEmpty())||(numOfV.isEmpty())||(add.isEmpty())){
            JOptionPane.showMessageDialog(null,"Please fill all the fields before you click on Register","Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        //validating cusID
        
        for(int i=0; i<id.length(); i++){
            
            if(!Character.isDigit(id.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter a valid Customer ID. Only the phone numbers are allowed with maximum of 10 numbers.","Invalid ID", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        if(id.length()!=10){
                JOptionPane.showMessageDialog(null,"Please Enter a valid Customer ID. Only the phone numbers are allowed with 10 numbers.","Invalid ID", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        
        //validating for cusName
        
        for(int i=0; i<name.length(); i++){ 
            
            if((!Character.isAlphabetic(name.charAt(i))) && (name.charAt(i)!=' ')){
                JOptionPane.showMessageDialog(null,"Please Enter a valid Name. Only the letters are allowed.","Invalid Name", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        //validating for num of visits
        for(int i=0; i<numOfV.length(); i++){ 
            
            if(!Character.isDigit(numOfV.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter a number for number of visits.","Invalid Number of Vists", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        
        return true;
        
        
        
    }
    
    public Boolean validateUpdatefields(){
        
        String id = txt_cusID2.getText();
        String name = txt_cusName2.getText();
        String add = txtA_cusAdd2.getText();
        String regDate = txt_regDate2.getText();
        String numV = txt_noOfVisits2.getText();
        String totPaid = txt_totPaid2.getText();
        String totDebt = txt_totDebt2.getText();
        
        //validating for empty fields
        if((name.isEmpty())||(add.isEmpty())){
            JOptionPane.showMessageDialog(null,"Please fill all the fields before you click on Update","Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        
        
        //validating for cusName
        
        for(int i=0; i<name.length(); i++){ 
            
            if((!Character.isAlphabetic(name.charAt(i))) && (name.charAt(i)!=' ')){
                JOptionPane.showMessageDialog(null,"Please Enter a valid Name. Only the letters are allowed.","Invalid Name", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        return true;
        
        
        
    }
    
    public void loadCustomerTable1(){
    
    try{
        
        ps = conn.prepareStatement("select cusID,name,address,noOfVisits from Customer"); 
        rs = ps.executeQuery();
        
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));

    }catch(Exception ex){
        
    
            System.out.println(ex);
    }
    
    }
    
        public void loadCustomerTable2(){
    
    try{
        
        ps = conn.prepareStatement("select * from Customer"); 
        rs = ps.executeQuery();
        
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
    }catch(Exception ex){
        
    
            System.out.println(ex);
    }}

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_Register = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_cusAdd = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txt_cusName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_cusID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_numOfVisits = new javax.swing.JTextField();
        btn_backToOrder = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_cusAdd2 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txt_cusName2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_cusID2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_regDate2 = new javax.swing.JTextField();
        txt_noOfVisits2 = new javax.swing.JTextField();
        txt_totPaid2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txt_totDebt2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txt_key = new javax.swing.JTextField();
        rdBtn_ID = new javax.swing.JRadioButton();
        rdBtn_Name = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1700, 1000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 255)), "Registration Details", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 0, 255))); // NOI18N

        btn_Register.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Register.setText("Register");
        btn_Register.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Customer Address");

        txtA_cusAdd.setColumns(20);
        txtA_cusAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtA_cusAdd.setRows(5);
        jScrollPane1.setViewportView(txtA_cusAdd);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Customer Name");

        txt_cusName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Customer ID");

        txt_cusID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cusID.setToolTipText("Telephone Number");
        txt_cusID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cusIDActionPerformed(evt);
            }
        });
        txt_cusID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cusIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cusIDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cusIDKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Number of Visits");

        txt_numOfVisits.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_numOfVisits.setText("1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_numOfVisits, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cusID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cusName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(86, 86, 86))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_numOfVisits, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        btn_backToOrder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_backToOrder.setText("Back to Order");
        btn_backToOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backToOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_backToOrder)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(btn_backToOrder)
                .addGap(43, 43, 43))
        );

        jTabbedPane1.addTab("New Customer", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 255)), "Customer Details", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 0, 255))); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Update");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Customer Address");

        txtA_cusAdd2.setColumns(20);
        txtA_cusAdd2.setRows(5);
        jScrollPane2.setViewportView(txtA_cusAdd2);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Customer Name");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Customer ID");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Registered Date");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("No. of Visits");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Total payments");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Remove");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Get Detailed Report");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Total outstandings  ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(423, 423, 423)
                        .addComponent(jButton4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(175, 175, 175)
                                    .addComponent(txt_cusID2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_cusName2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(197, 197, 197)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_regDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_noOfVisits2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_totPaid2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_totDebt2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_cusID2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_regDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cusName2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_noOfVisits2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel9))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_totPaid2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_totDebt2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 255)), "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 0, 255))); // NOI18N

        txt_key.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_key.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_keyKeyReleased(evt);
            }
        });

        rdBtn_ID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdBtn_ID.setText("By ID");
        rdBtn_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtn_IDActionPerformed(evt);
            }
        });

        rdBtn_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdBtn_Name.setText("By Name");
        rdBtn_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtn_NameActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rdBtn_ID)
                        .addGap(96, 96, 96)
                        .addComponent(rdBtn_Name)
                        .addGap(120, 120, 120)
                        .addComponent(txt_key, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdBtn_ID)
                    .addComponent(rdBtn_Name)
                    .addComponent(txt_key, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Existing Customer", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1690, 970));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        
        if(!validateTextfields()){
            return;
        }
        
        int x= JOptionPane.showConfirmDialog(null, "Are you sure you want to Register this customer?");
        
        if(x==0){  
            String cusID=txt_cusID.getText();
            String cusName=txt_cusName.getText();
            String cusAdd=txtA_cusAdd.getText();
            String numOfVisits=txt_numOfVisits.getText();

            try{
                ps = conn.prepareStatement("insert into Customer (cusID,name,address,noOfVisits) values(?,?,?,?)");
                ps.setString(1,cusID);
                ps.setString(2,cusName);
                ps.setString(3,cusAdd);
                ps.setString(4,numOfVisits);
                ps.execute();
                loadCustomerTable1();
                loadCustomerTable2();
                JOptionPane.showMessageDialog(null,"New customer is successfully registered!","Transacation Successful", JOptionPane.INFORMATION_MESSAGE);

            }catch(Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Sorry! Something went wrong. Please contact the developing team.","Transacation Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
            
            txt_cusID.setText("");
            txt_cusName.setText("");
            txtA_cusAdd.setText("");
            txt_numOfVisits.setText("1");
           
        }
           
                
       
        
    }//GEN-LAST:event_btn_RegisterActionPerformed

    private void txt_cusIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cusIDActionPerformed
        
    }//GEN-LAST:event_txt_cusIDActionPerformed

    private void txt_cusIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cusIDKeyTyped
         
        
    }//GEN-LAST:event_txt_cusIDKeyTyped

    private void txt_cusIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cusIDKeyPressed
       
    }//GEN-LAST:event_txt_cusIDKeyPressed

    private void txt_cusIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cusIDKeyReleased
       /*   //System.out.println("test");
            String x = txt_cusID.getText();
          //System.out.println(x);
          //System.out.println(x.length());     
            Character ch = x.charAt(x.length()-1);  //getting last character
            
            if(!Character.isDigit(ch)){             //if last char is not a digit
                JOptionPane.showMessageDialog(null,"Please Enter a valid Customer ID. Only the phone numbers are allowed.","Invalid ID", JOptionPane.INFORMATION_MESSAGE);
                
                if(x.length()==1){                  //if only one character is typed
                    txt_cusID.setText("");
                    
                }else{                              //if more than one characters are typed
                    txt_cusID.setText(x.substring(0, x.length()-1));
                }
            
            }else if(Character.isDigit(ch)&&x.length()>10){        //if last character is a digit & length >10
                JOptionPane.showMessageDialog(null,"Please Enter a valid Customer ID. Only  are allowed.","Invalid ID", JOptionPane.INFORMATION_MESSAGE);
                txt_cusID.setText(x.substring(0, x.length()-1));
            }   */
    }//GEN-LAST:event_txt_cusIDKeyReleased

    private void rdBtn_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtn_IDActionPerformed
        rdBtn_ID.setSelected(true);
        rdBtn_Name.setSelected(false);
    }//GEN-LAST:event_rdBtn_IDActionPerformed

    private void rdBtn_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtn_NameActionPerformed
        rdBtn_Name.setSelected(true);
        rdBtn_ID.setSelected(false);
    }//GEN-LAST:event_rdBtn_NameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if(!validateUpdatefields()){
            return;
        }
        
        int x= JOptionPane.showConfirmDialog(null, "Are you sure you want to Update this customer?");
        
        if(x==0){  
            String id = txt_cusID2.getText();
            String name = txt_cusName2.getText();
            String add = txtA_cusAdd2.getText();
          /*String regDate = txt_regDate2.getText();
            String numV = txt_noOfVisits2.getText();
            String totPaid = txt_totPaid2.getText();
            String totDebt = txt_totDebt2.getText();*/

            try{
              //PreparedStatement ps = conn.prepareStatement("UPDATE Customer SET name=? , address=? ,regDate=? ,noOfVisits=? ,totalPaid=? ,totalOutstanding=? where id=?  ");
                PreparedStatement ps = conn.prepareStatement("UPDATE Customer SET name=? ,address=? where cusID=?  ");
                ps.setString(1,name);
                ps.setString(2,add);
                ps.setString(3,id);
                /*ps.setString(4,numV);
                ps.setString(5,totPaid);
                ps.setString(6,totDebt);
                ps.setString(7,id);*/
                ps.execute();
                loadCustomerTable1();
                loadCustomerTable2();
                JOptionPane.showMessageDialog(null,"The customer is successfully updated!","Transacation Successful", JOptionPane.INFORMATION_MESSAGE);

            }catch(Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Sorry! Something went wrong. Please contact the developing team.","Transacation Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
           
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      
        
        int x= JOptionPane.showConfirmDialog(null, "Are you sure you want to Remove this customer?");
        
        if(x==0){  
            String id = txt_cusID2.getText();
            
            try{
             
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Customer where cusID=?");
                ps.setString(1,id);
                
                ps.execute();
                loadCustomerTable1();
                loadCustomerTable2();
                JOptionPane.showMessageDialog(null,"The customer is successfully removed!","Transacation Successful", JOptionPane.INFORMATION_MESSAGE);

            }catch(Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Sorry! Something went wrong. Please contact the developing team.","Transacation Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
           
        }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_keyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_keyKeyReleased
        
        String key = txt_key.getText();
       
        
        try{
            if(rdBtn_ID.isSelected()){
                
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Customer WHERE cusID LIKE '%"+key+"%'");
                ResultSet rs = ps.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
                //System.out.println("First if st");
                
                
            }else if(rdBtn_Name.isSelected()){
                //System.out.println("Else if st");
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Customer WHERE name LIKE '%"+key+"%'");
                ResultSet rs = ps.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
                
            }                
            
        }catch(Exception e){
            
            System.out.println(e);
            System.out.println("Landed to catch block");
        }
                
    }//GEN-LAST:event_txt_keyKeyReleased

    private void btn_backToOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backToOrderActionPerformed
        Order.callOrderFormAgain();
    }//GEN-LAST:event_btn_backToOrderActionPerformed

 
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {                                     
        int r = jTable2.getSelectedRow();

        String cusID = jTable2.getValueAt(r, 0).toString();

        try{

            PreparedStatement ps = conn.prepareStatement("select * from Customer where cusID=?");

            ps.setString(1,cusID );

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
             
                txt_cusID2.setText(rs.getString("cusID"));
                txt_cusName2.setText(rs.getString("name"));
                txtA_cusAdd2.setText(rs.getString("address"));

                txt_regDate2.setText(rs.getString("regDate"));
                txt_noOfVisits2.setText(rs.getString("noOfVisits"));
                txt_totPaid2.setText(rs.getString("totalPaid"));
                txt_totDebt2.setText(rs.getString("totalOutstanding"));

            }

        }catch (Exception ex){

            ex.printStackTrace();
        }

    }                                    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Register;
    javax.swing.JButton btn_backToOrder;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton rdBtn_ID;
    private javax.swing.JRadioButton rdBtn_Name;
    private javax.swing.JTextArea txtA_cusAdd;
    private javax.swing.JTextArea txtA_cusAdd2;
    private javax.swing.JTextField txt_cusID;
    private javax.swing.JTextField txt_cusID2;
    private javax.swing.JTextField txt_cusName;
    private javax.swing.JTextField txt_cusName2;
    private javax.swing.JTextField txt_key;
    private javax.swing.JTextField txt_noOfVisits2;
    private javax.swing.JTextField txt_numOfVisits;
    private javax.swing.JTextField txt_regDate2;
    private javax.swing.JTextField txt_totDebt2;
    private javax.swing.JTextField txt_totPaid2;
    // End of variables declaration//GEN-END:variables
}
