/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agency;

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
 * @author chath
 */
public class ProcessOrder extends javax.swing.JInternalFrame {

    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement s = null;
        
    
    public ProcessOrder() {
               
        
        
        initComponents();
        
        
        
        LBLpolineno.setVisible(false);
               
        
        
        conn = javaconnect.ConnectDb();
        generateID();
        
        //load Table
        
        tableLoad();
    }
    
    
    
    

     public void clearItem(){
    
            //Clear one item record
        
        TXTitemCode.setText("");
        TXTdescription.setText("");
        TXTquantity.setText("");
        TXTpriceEach.setText("");
        TXTamount.setText("");
        
     }
     
     public void clearAll(){
    
            //Clear whole invoice
        
        clearItem();
        CMBpaymentMethod.setSelectedIndex(0);
        TXTpaidAmount.setText("");
        TXTcreditbalance.setText("");
        TXTstoreID.setText("");
        TXTinvoiceNo.setText("");
        TXTphone.setText("");
        JCALdate.setDate(JCALdate.getDate());
        TXTAbillTo.setText("");
        TXTAdeliverTo.setText("");
        TXTsubtotal.setText("");
        TXTdiscount.setText("");
        TXTtotal.setText("");
        generateID();
     }
     
     
    
    
     
     public void calcSubTotal(){
     
          /////////////Calculating Sub Total after each item record
         
          String sql = "SELECT SUM(amount) FROM invoice";
        
        try {
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            String subtot = rs.getString("SUM(amount)");
                        
            TXTsubtotal.setText(subtot);
            
        }
        
        catch (Exception e) {
            System.out.println(e);
        }
        
        finally{
        
            try{
            
                pst.close();
                rs.close();
            
            }
            
            catch(Exception e){
            
                System.out.println(e);
            
            }
        
        }
     
     }
     
     
     
     
     public void tableLoad()
    {
    
        try{
        
            String sql = "SELECT lineno, itemcode, description, quantity, priceeach, amount FROM invoice";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
         
            invoiceTable.setModel(DbUtils.resultSetToTableModel(rs));
            
                        
        }
        
        
        
        
        catch(Exception e){
        
            System.out.println(e);
        
        }
            
        finally{
        
            try{
            
                pst.close();
                rs.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
    }
     
     
     
     public void generateID(){
        
        
        
        
        try{
            s = conn.createStatement();
            rs = s.executeQuery("SELECT MAX(invoiceno) AS lastitem FROM invoiceSummary");
           
            String storeid;
            
            while(rs.next()){
                
                storeid = rs.getString("lastitem");
                String sid[] = storeid.split("IN");
                int no=Integer.parseInt(sid[1]);
                no = no + 1;
                TXTinvoiceNo.setText("IN"+no);
                
                
//            s = conn.createStatement();
//            rs = s.executeQuery("SELECT MAX(invoiceno) AS lastinvoice FROM transaction ");
//           
//            String lastinvoice;
//            
//            while(rs.next()){
//                
//                lastinvoice = rs.getString("lastinvoice");
//                String sid[] = lastinvoice.split("IN");
//                int no=Integer.parseInt(sid[1]);
//                no = no + 1;
//                TXTinvoiceNo.setText("IN"+no);

            }
        }
        
        catch (Exception e) {
            
            System.out.println(e);
        
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

        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TXTinvoiceNo = new javax.swing.JTextField();
        TXTphone = new javax.swing.JTextField();
        JCALdate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TXTAbillTo = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXTAdeliverTo = new javax.swing.JTextArea();
        BTNcopy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        TXTpriceEach = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TXTdescription = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        TXTitemCode = new javax.swing.JTextField();
        TXTamount = new javax.swing.JTextField();
        BTNadd = new javax.swing.JButton();
        BTNdelete = new javax.swing.JButton();
        BTNupdate = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        TXTquantity = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        invoiceTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TXTsubtotal = new javax.swing.JTextField();
        TXTdiscount = new javax.swing.JTextField();
        TXTtotal = new javax.swing.JTextField();
        BTNsaveNew = new javax.swing.JButton();
        BTNclear = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        CMBpaymentMethod = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        TXTpaidAmount = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        TXTcreditbalance = new javax.swing.JTextField();
        BTNpayment = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        TXTbalance = new javax.swing.JTextField();
        TXTstoreID = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        TXTstoreName = new javax.swing.JTextField();
        LBLpolineno = new javax.swing.JLabel();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Bill To :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Invoice No :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Phone No  :");

        TXTinvoiceNo.setEditable(false);
        TXTinvoiceNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTinvoiceNoActionPerformed(evt);
            }
        });

        TXTphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTphoneActionPerformed(evt);
            }
        });

        TXTAbillTo.setColumns(20);
        TXTAbillTo.setRows(5);
        jScrollPane1.setViewportView(TXTAbillTo);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Date           :");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Deliver To :");

        TXTAdeliverTo.setColumns(20);
        TXTAdeliverTo.setRows(5);
        jScrollPane2.setViewportView(TXTAdeliverTo);

        BTNcopy.setText("Copy >>");
        BTNcopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNcopyActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("Invoice");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Item Code               :");

        TXTpriceEach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTpriceEachActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Description               :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Quantity                   :");

        TXTdescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTdescriptionActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Price Each                 :");

        TXTitemCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTitemCodeActionPerformed(evt);
            }
        });

        TXTamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTamountActionPerformed(evt);
            }
        });

        BTNadd.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BTNadd.setText("Add");
        BTNadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNaddActionPerformed(evt);
            }
        });

        BTNdelete.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BTNdelete.setText("Delete");
        BTNdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNdeleteActionPerformed(evt);
            }
        });

        BTNupdate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BTNupdate.setText("Update");
        BTNupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNupdateActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Amount                    :");

        TXTquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTquantityActionPerformed(evt);
            }
        });
        TXTquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTquantityKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTquantityKeyTyped(evt);
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTdescription, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(TXTitemCode)
                            .addComponent(TXTquantity)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TXTamount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXTpriceEach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(BTNadd, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(BTNupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTNdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TXTitemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TXTquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(TXTdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(TXTpriceEach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(TXTamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNdelete)
                    .addComponent(BTNupdate)
                    .addComponent(BTNadd))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        invoiceTable.setModel(new javax.swing.table.DefaultTableModel(
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
        invoiceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoiceTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(invoiceTable);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Rs.");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Sub Total");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Discount");

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Rs.");

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator4.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Total");

        jLabel7.setBackground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Rs.");

        TXTsubtotal.setEditable(false);
        TXTsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTsubtotalActionPerformed(evt);
            }
        });

        TXTdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTdiscountActionPerformed(evt);
            }
        });
        TXTdiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTdiscountKeyTyped(evt);
            }
        });

        TXTtotal.setEditable(false);
        TXTtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTtotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(TXTtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(jSeparator4)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(TXTdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TXTtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        BTNsaveNew.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BTNsaveNew.setText("Save & New");
        BTNsaveNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNsaveNewActionPerformed(evt);
            }
        });

        BTNclear.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BTNclear.setText("Clear");
        BTNclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNclearActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Payment Method    :");

        CMBpaymentMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cash", "Cheque", "Credit" }));
        CMBpaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBpaymentMethodActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Amount                   :");

        TXTpaidAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTpaidAmountActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Credit Balance        :");

        TXTcreditbalance.setEditable(false);
        TXTcreditbalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcreditbalanceActionPerformed(evt);
            }
        });

        BTNpayment.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BTNpayment.setText("Enter");
        BTNpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNpaymentActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Balance                   :");

        TXTbalance.setEditable(false);
        TXTbalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTbalanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(BTNpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TXTbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMBpaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTpaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTcreditbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(CMBpaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(TXTpaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(TXTcreditbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(BTNpayment)
                .addContainerGap())
        );

        TXTstoreID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTstoreIDActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Store ID       :");

        jLabel8.setText("<html>\n<p>\nSHASHI ENTERPRISES,\n<br>NO - 12,\n<br>NUWARA ELIYA ROAD,\n<br>KEPPETIPOLA.\n<br>057-2280047\n</p>\n</html>");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Store Name :");

        TXTstoreName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTstoreNameActionPerformed(evt);
            }
        });
        TXTstoreName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTstoreNameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(LBLpolineno, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(BTNsaveNew, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(BTNclear, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel28)
                                                .addGap(57, 57, 57))
                                            .addComponent(TXTstoreID, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(JCALdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel29)
                                                .addGap(58, 58, 58))
                                            .addComponent(TXTstoreName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXTinvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXTphone, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNcopy, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(BTNcopy)
                                    .addGap(21, 21, 21)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXTstoreID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TXTstoreName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCALdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXTinvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBLpolineno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTNsaveNew)
                            .addComponent(BTNclear)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void BTNclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNclearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNclearActionPerformed

    private void BTNupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNupdateActionPerformed
        
        
        
        calcSubTotal();
        
        
        
    }//GEN-LAST:event_BTNupdateActionPerformed

    private void BTNdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNdeleteActionPerformed
        
        String lineno = LBLpolineno.getText();
        
        int y = JOptionPane.showConfirmDialog(null,"Do you really want to delete this record?");

        if(y==0)
        {
            String sql = "DELETE from invoice where lineno = '"+ lineno +"'";

            try
            {

                pst = conn.prepareStatement(sql);
                pst.execute();
                clearItem();
                calcSubTotal();

                //load Table
                tableLoad();

            }

            catch(Exception e)
            {
                    System.out.println(e);
            }
            
            finally{
        
            try{
            
                pst.close();
                                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        }
        
        
    }//GEN-LAST:event_BTNdeleteActionPerformed

    private void BTNaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNaddActionPerformed

        String poinvoiceno = TXTinvoiceNo.getText();
        String poitemcode = TXTitemCode.getText();
        String podecription = TXTdescription.getText();
        String poquantity = TXTquantity.getText();
        String popriceeach = TXTpriceEach.getText();
        String poamount = TXTamount.getText();

          //////////////To check whether quantity is less 
        
        String itemcode = TXTitemCode.getText();
      //  float quantity = Float.parseFloat(TXTquantity.getText());
       // float quantityleft = 0;
        
       // String sql = "SELECT quantityleft FROM items WHERE itemcode = '"+ itemcode +"' ";
        
       // try {
            
         //   pst = conn.prepareStatement(sql);
           // rs = pst.executeQuery();
            
           // quantityleft = Float.parseFloat(rs.getString("quantityleft"));
             
            // if(quantityleft < quantity){
             
             //    JOptionPane.showMessageDialog(rootPane, "Out of Stock", "Notification", JOptionPane.WARNING_MESSAGE);
            // }
        
       // } catch (Exception e) {
            
         //   System.out.println(e);
        
        //}
        
        //finally{
        
          //  try{
            
            //    pst.close();
              //  rs.close();
                
            
            //}
            
           // catch(Exception e){
        
             //   System.out.println(e);
        
        //}
        
        //}
        
        
        
       // if(quantityleft > quantity){

        String query = "INSERT INTO invoice (invoiceno, itemcode, description, priceeach, quantity, amount) values('"+ poinvoiceno +"' , '"+ poitemcode +"' , '"+ podecription +"' , '"+ popriceeach +"' , '"+ poquantity +"' , '"+ poamount +"')";
        

         try
        {
            
                pst = conn.prepareStatement(query);
                pst.execute();
                
                            
                
                 //load Table
                tableLoad();
                calcSubTotal();
                
                
                //clear the fields
                clearItem();
        }

        catch(Exception e) 
        {

            System.out.println(e);

        }
         
         finally{
        
            try{
            
                pst.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
        
        //}   
    }//GEN-LAST:event_BTNaddActionPerformed

    private void TXTamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTamountActionPerformed

    private void TXTitemCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTitemCodeActionPerformed
        
        
        String itemcode = TXTitemCode.getText();
               
        String sql = "SELECT i.description, i.priceeach FROM items i WHERE i.itemcode = '"+ itemcode +"' ";
        /*
        try{
        
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
         
            TXTdescription.setText(rs.getString(String.valueOf("description")));
            TXTpriceEach.setText(rs.getString(String.valueOf("priceeach")));
                                 
            TXTquantity.requestFocus();
                        
        }
        
        catch(Exception e){
        
            System.out.println(e);
          
        }
        
        finally{
        
            try{
            
                pst.close();
                rs.close();
                        
            }
            
            catch(Exception e){
        
                System.out.println("line 880");
        
        }
        
        }
        */
        
        try {
        
            
        pst=conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        if(rs.next()) { 
            
            String description = rs.getString("description");
            TXTdescription.setText(description);
            String priceeach = rs.getString("priceeach");
            TXTpriceEach.setText(priceeach);
        
        }
        
        } catch (Exception e ) {
            
            System.out.println(e);

        }
        
        finally{
        
            try{
            
                pst.close();
                rs.close();
                        
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
        TXTquantity.requestFocus();

        
        
    }//GEN-LAST:event_TXTitemCodeActionPerformed

    private void TXTdescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTdescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTdescriptionActionPerformed

    private void TXTpriceEachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTpriceEachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTpriceEachActionPerformed

    private void TXTquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTquantityActionPerformed
        
        
        
        
        String popriceeach = TXTpriceEach.getText();
        String poquantity = TXTquantity.getText();
        
        amount = Double.parseDouble(popriceeach) * Double.parseDouble(poquantity);
        
        TXTamount.setText(Double.toString(amount));
        
        
        
        
        
    }//GEN-LAST:event_TXTquantityActionPerformed

    private void TXTpaidAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTpaidAmountActionPerformed
        
        String storename = TXTstoreName.getText();
        String storeid = TXTstoreID.getText();
        String paymentmethod = CMBpaymentMethod.getSelectedItem().toString();
        double amountpaid = Double.parseDouble(TXTpaidAmount.getText());
        double total = Double.parseDouble(TXTtotal.getText());
        double balance = 0;
        double creditbalance = 0;
        double temp;
        
        
        if(paymentmethod == "Cash" || paymentmethod == "Cheque"){
        
            if(amountpaid > total){
            
                balance = amountpaid - total;
                             
            }
            
            else if(amountpaid < total){
            
                creditbalance = total - amountpaid;
                
            }
            
            TXTbalance.setText(Double.toString(balance));
            TXTcreditbalance.setText(Double.toString(creditbalance));
            
        }
        
        else if(paymentmethod == "Credit"){
        
            String sql = "SELECT creditAmount FROM store WHERE id = '"+ storeid +"'";
            String q = "INSERT INTO store (creditAmount) VALUES ('"+ creditbalance +"') WHERE id = '"+ storeid +"'";
            
            
            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(!rs.next()){
                
                    pst = conn.prepareStatement(q);
                    rs = pst.executeQuery();
                
                }
                
                else{
                
                    String credit = rs.getString("creditAmount");
                                        
                    int y = JOptionPane.showConfirmDialog(null,storename+" already has a credit amount of "+ credit + ". Do you want to proceed with this transaction?");

                    if(y==0)
                    {
                        temp = (Double.parseDouble(credit)) + creditbalance;
                        
                        String a = "INSERT INTO store (creditAmount) VALUES ('"+ temp +"') WHERE id = '"+ storeid +"'";
                        
                        pst = conn.prepareStatement(a);
                        pst.execute();
                
                    }

            
                
                }
                
            }
            
            catch (Exception e) {
                System.out.println(e);
            }
            
            finally{
        
            try{
            
                pst.close();
                rs.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
        }
        
    }//GEN-LAST:event_TXTpaidAmountActionPerformed

    private void TXTcreditbalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcreditbalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcreditbalanceActionPerformed

    private void TXTinvoiceNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTinvoiceNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTinvoiceNoActionPerformed

    private void TXTphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTphoneActionPerformed

    private void BTNcopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNcopyActionPerformed
        
        TXTAdeliverTo.setText(TXTAbillTo.getText());
        
    }//GEN-LAST:event_BTNcopyActionPerformed

    private void TXTsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTsubtotalActionPerformed
        
        
        
       
        
        
        
    }//GEN-LAST:event_TXTsubtotalActionPerformed

    private void TXTdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTdiscountActionPerformed
        
        
        double subtot = Double.parseDouble(TXTsubtotal.getText());
        int disc = Integer.parseInt(TXTdiscount.getText());
        double tot = subtot - (subtot * disc/100.0);
        
        TXTtotal.setText(Double.toString(tot));
        
        
        
    }//GEN-LAST:event_TXTdiscountActionPerformed

    private void TXTtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTtotalActionPerformed

    private void invoiceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceTableMouseClicked
        
        
        int row = invoiceTable.getSelectedRow();
        String posaleno = invoiceTable.getValueAt(row,0).toString();
        String poitemcode = invoiceTable.getValueAt(row,1).toString();
        String podescription = invoiceTable.getValueAt(row,2).toString();
        String poquantity = invoiceTable.getValueAt(row,3).toString();
        String popriceeach = invoiceTable.getValueAt(row,4).toString();
        String poamount = invoiceTable.getValueAt(row,5).toString();
        
               
        LBLpolineno.setText(posaleno);
        TXTitemCode.setText(poitemcode);
        TXTquantity.setText(poquantity);
        TXTdescription.setText(podescription);
        TXTpriceEach.setText(popriceeach);
        TXTamount.setText(poamount);
                     
        
        
    }//GEN-LAST:event_invoiceTableMouseClicked

    private void TXTquantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTquantityKeyPressed
        
        
        
    }//GEN-LAST:event_TXTquantityKeyPressed

    private void BTNsaveNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNsaveNewActionPerformed
        
        String itemcode = TXTitemCode.getText();
        String query = "INSERT INTO sales (invoiceno,itemcode,description,priceeach,quantity,amount) SELECT invoiceno,itemcode,description,priceeach,quantity,amount FROM invoice";
        
        
        
        try
        {
            
                pst = conn.prepareStatement(query);
                pst.execute();
                
                String sql = "INSERT INTO (invoiceno,storeid,date,deliverto,subtotal,discounts,total) VALUES ('"+ TXTinvoiceNo.getText() +"','"+ TXTstoreID.getText() +"','"+ JCALdate.getDate()+"','"+ TXTAdeliverTo.getText()+"',"+ TXTsubtotal.getText() +"',"+ TXTdiscount.getText() +"',"+ TXTtotal.getText() +"') ";
                
                 //load Table
                tableLoad();
                
                //Clear the Temporary Invoice Table
                String q = "DELETE FROM invoice WHERE itemcode = '"+ itemcode +"' ";
                pst = conn.prepareStatement(sql);
                pst.execute();
                
                //clear the fields
                clearItem();
        }

        catch(Exception e)
        {

            System.out.println(e);

        }
        
        finally{
        
            try{
            
                pst.close();
               
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
    }//GEN-LAST:event_BTNsaveNewActionPerformed

    private void TXTstoreIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTstoreIDActionPerformed
        
        TXTAdeliverTo.setText("");
        
        String storeid = TXTstoreID.getText();
        
        String sql = "SELECT name, contact, address FROM store WHERE id = '"+ storeid +"' ";
        /*
        try{
        
            
            
            
            pst = conn.prepareStatement(sql);
            pst.execute();
         
            TXTstoreName.setText(rs.getString(String.valueOf("name")));
            TXTphone.setText(rs.getString(String.valueOf("contact")));
            TXTAbillTo.setText(rs.getString(String.valueOf("address")));
            
          
            
            BTNcopy.requestFocus();
                        
        }
        
        catch(Exception e){
        
            System.out.println(e);
          
        }
        */
        
        try {
        
            
        pst=conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        if(rs.next()) { 
            
            String name = rs.getString("name");
            TXTstoreName.setText(name);
            String contact = rs.getString("contact");
            TXTphone.setText(contact);
            String address = rs.getString("address");
            TXTAbillTo.setText(address);
        
        }
        
        } catch (Exception e ) {
            
            System.out.println(e);

        }
        
        finally{
        
            try{
            
                pst.close();
                rs.close();
                        
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
        BTNcopy.requestFocus();
        
    }//GEN-LAST:event_TXTstoreIDActionPerformed

    private void TXTstoreNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTstoreNameActionPerformed
        /*
        String name = TXTstoreName.getText();
        
        String sql = "SELECT id FROM store WHERE name = '"+ name +"' ";
        
        try{
        
            
            
            
            pst = conn.prepareStatement(sql);
            pst.execute();
         
            TXTstoreID.setText(rs.getString(String.valueOf("id")));
            
            

            //TXTdescription.setText(rs.getString("description"));
            //TXTpriceEach.setText(rs.getString("priceeach"));
            
            
            TXTstoreID.requestFocus();
                        
        }
        
        catch(Exception e){
        
            System.out.println(e);
          
        }
        
        */
        
    }//GEN-LAST:event_TXTstoreNameActionPerformed

    private void CMBpaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBpaymentMethodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMBpaymentMethodActionPerformed

    private void TXTstoreNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTstoreNameKeyTyped
        
        
        
    }//GEN-LAST:event_TXTstoreNameKeyTyped

    private void BTNpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNpaymentActionPerformed
        
        String paymentmethod = CMBpaymentMethod.getSelectedItem().toString();
        String amountpaid = TXTpaidAmount.getText();
        
        if(paymentmethod == "Cash"){
        
            
        
        }
        
        
        
        
    }//GEN-LAST:event_BTNpaymentActionPerformed

    private void TXTbalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTbalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTbalanceActionPerformed

    private void TXTquantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTquantityKeyTyped
       /* /String itemcode = TXTitemCode.getText();
        float quantity = Float.parseFloat(TXTquantity.getText());
        float quantityleft;
        
        String sql = "SELECT quantityleft FROM items WHERE itemcode = '"+ itemcode +"' ";
        
        try {
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            quantityleft = Float.parseFloat(rs.getString("quantityleft"));
             
             if(quantityleft < quantity){
             
                 JOptionPane.showInputDialog("Out of Stock");
             
             }
        
        } catch (Exception e) {
            
            System.out.println(e);
        
        }*/
    }//GEN-LAST:event_TXTquantityKeyTyped

    private void TXTdiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTdiscountKeyTyped
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
    }//GEN-LAST:event_TXTdiscountKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNadd;
    private javax.swing.JButton BTNclear;
    private javax.swing.JButton BTNcopy;
    private javax.swing.JButton BTNdelete;
    private javax.swing.JButton BTNpayment;
    private javax.swing.JButton BTNsaveNew;
    private javax.swing.JButton BTNupdate;
    private javax.swing.JComboBox CMBpaymentMethod;
    private com.toedter.calendar.JDateChooser JCALdate;
    private javax.swing.JLabel LBLpolineno;
    private javax.swing.JTextArea TXTAbillTo;
    private javax.swing.JTextArea TXTAdeliverTo;
    private javax.swing.JTextField TXTamount;
    private javax.swing.JTextField TXTbalance;
    private javax.swing.JTextField TXTcreditbalance;
    private javax.swing.JTextField TXTdescription;
    private javax.swing.JTextField TXTdiscount;
    private javax.swing.JTextField TXTinvoiceNo;
    private javax.swing.JTextField TXTitemCode;
    private javax.swing.JTextField TXTpaidAmount;
    private javax.swing.JTextField TXTphone;
    private javax.swing.JTextField TXTpriceEach;
    private javax.swing.JTextField TXTquantity;
    private javax.swing.JTextField TXTstoreID;
    private javax.swing.JTextField TXTstoreName;
    private javax.swing.JTextField TXTsubtotal;
    private javax.swing.JTextField TXTtotal;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables


double amount;
private String GenID;
private int currentInvoiceNumber;
double subtotal;
}
