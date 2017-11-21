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
import DBconnection.DBconnect;
import java.util.Date;

/**
 *
 * @author chath
 */
public class ProcessOrder extends javax.swing.JInternalFrame {

    
    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    PreparedStatement pst4 = null;
    ResultSet rs = null;
    Statement s = null;
        
    
    public ProcessOrder() {
               
        
        
        initComponents();
//        
        LBLcreditBal.setVisible(false);

        Date ds = new Date();
        
        JDCdate.setDate(ds);
        
        generateID();
        
        LBLpolineno.setVisible(false);
               
        
        
        conn = DBconnect.connectDb();
        generateID();
        calcSubTotal();
        //load Table
        
        tableLoad();
    }
    
    
    
    

     public void clearItem(){
    
            //Clear one item record
        
        TXTitemCode.setText("");
        TXTdescription.setText("");
        TXTquantity.setText("");
        TXTitemdiscount.setText("");
        TXTpriceEach.setText("");
        TXTamount.setText("");
        
     }
     
     public void clearAll(){
    
            //Clear whole invoice
        
        clearItem();
//        CMBpaymentMethod.setSelectedIndex(0);
//        TXTPaymentAmount.setText("");
//        TXTcreditbalance.setText("");
        TXTstoreID.setText("");
        TXTinvoiceNo.setText("");
        TXTphone.setText("");
        //JCALdate.setDate(JCALdate.getDate());
        TXTAbillTo.setText("");
        TXTAdeliverTo.setText("");
        TXTsubtotal.setText("");
        TXTdiscount.setText("");
        TXTtotal.setText("");
        generateID();
     }
     
     public void updateCreditBalance(){
     String invoice = TXTinvoiceNo.getText();
       String stid = TXTstoreID.getText();
   try{
       String sql = "SELECT creditAmount from store Where id= '"+ stid +"'";
      pst=conn.prepareStatement(sql);
      
       rs = pst.executeQuery();
//       
        String creditbal = rs.getString("creditAmount");
        
        LBLcreditBal.setText(creditbal);
       
       double cAmount = Double.parseDouble(LBLcreditBal.getText());
       
       double total = Double.parseDouble(TXTtotal.getText());
       
      
       
       cAmount=cAmount+total;
       
       String credAmount = Double.toString(cAmount);
       
     //  TXTcreditbalance.setText(credAmount);
       
       String sql1 = "Update store set creditAmount='"+ credAmount +"' Where id='"+ stid +"'"
               + " ";
       pst1=conn.prepareStatement(sql1);
       pst1.execute();
       
   }   
   catch(Exception e){
       System.out.println(e);
   }
   
    finally{
        
            try{
            
                pst1.close();
                                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
   
   
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
            e.printStackTrace();
        }
        
        finally{
        
            try{
            
                pst.close();
                rs.close();
            
            }
            
            catch(Exception e){
            
                e.printStackTrace();
            
            }
        
        }
     
     }
     double quantity;
     double previousDiscountAmount;
     public void calcDiscounts(){
     
         
         
        
        try {
            
            String itemcode = TXTitemCode.getText();
            quantity = Double.parseDouble(TXTquantity.getText());
            previousDiscountAmount = Double.parseDouble(TXTitemdiscount.getText());
         
         String sql = "SELECT priceeach, discounts FROM items WHERE itemcode = '"+ itemcode +"' ";
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            double rate = Double.parseDouble(rs.getString("discounts"));
            double amount = Double.parseDouble(rs.getString("priceeach"));
            
            double currentDiscountAmount = (amount * rate/100.0)*quantity;
                        
            //TXTdiscount.setText(Double.toString(previousDiscountAmount + currentDiscountAmount));
           
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
        
            e.printStackTrace();
        
        }
            
        finally{
        
            try{
            
                pst.close();
                rs.close();
                
            
            }
            
            catch(Exception e){
        
                e.printStackTrace();
                        
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
        
        finally{
        
            try{
                s.close();
//                rs.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
                
        }
        
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
        jLabel20 = new javax.swing.JLabel();
        TXTitemdiscount = new javax.swing.JTextField();
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
        TXTstoreID = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        TXTstoreName = new javax.swing.JTextField();
        LBLpolineno = new javax.swing.JLabel();
        JDCdate = new com.toedter.calendar.JDateChooser();
        LBLcreditBal = new javax.swing.JLabel();

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

        TXTpriceEach.setEditable(false);
        TXTpriceEach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTpriceEachActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Description               :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Quantity                   :");

        TXTdescription.setEditable(false);
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

        TXTamount.setEditable(false);
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

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Discount                   :");

        TXTitemdiscount.setEditable(false);
        TXTitemdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTitemdiscountActionPerformed(evt);
            }
        });
        TXTitemdiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTitemdiscountKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTitemdiscountKeyTyped(evt);
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
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTamount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTpriceEach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BTNadd, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(BTNupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(TXTitemCode, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(TXTitemdiscount))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(TXTquantity))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(TXTdescription)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TXTitemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(TXTdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(TXTpriceEach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TXTquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(TXTitemdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(TXTamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNdelete)
                    .addComponent(BTNupdate)
                    .addComponent(BTNadd))
                .addContainerGap(55, Short.MAX_VALUE))
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
                                    .addComponent(jSeparator3)
                                    .addComponent(TXTtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(jSeparator4)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(525, 525, 525)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(640, 640, 640)
                                .addComponent(LBLcreditBal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(BTNsaveNew, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BTNclear, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(LBLpolineno, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(427, 427, 427))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(57, 57, 57))
                                    .addComponent(TXTstoreID, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXTphone, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(JDCdate, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addGap(58, 58, 58))
                                    .addComponent(TXTstoreName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(TXTinvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(145, 145, 145)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNcopy, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTstoreID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JDCdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTstoreName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTinvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addComponent(jLabel13))
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
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(LBLcreditBal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNsaveNew)
                    .addComponent(BTNclear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(LBLpolineno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
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
        
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to update this record?");

        if(x == 0)
        {

            String invoiceno = TXTinvoiceNo.getText();
            String lineno = LBLpolineno.getText();
            String itemcode = TXTitemCode.getText();
            String qty = TXTquantity.getText();
            String description = TXTdescription.getText();
            String priceeach = TXTpriceEach.getText();
            String amount = TXTamount.getText();

            String sql = "UPDATE invoice SET invoiceno='"+ invoiceno +"' , itemcode='"+ itemcode +"' ,  quantity='"+ qty +"' , amount='"+ amount +"' ,  description='"+ description +"' ,  priceeach='"+ priceeach +"'    WHERE lineno = '"+ lineno +"'";

            try{

                
                
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                

                //load Table
                tableLoad();

            }

            catch(Exception e){
                    e.printStackTrace();
            }
            
            finally{
        
            try{
            
                pst.close();
                
            
            }
            
            catch(Exception e){
        
                e.printStackTrace();
        
        }
        
        }

        }
        
        calcSubTotal();
        
        
        
    }//GEN-LAST:event_BTNupdateActionPerformed

    private void BTNdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNdeleteActionPerformed
        
        String lineno = LBLpolineno.getText();
//        double amount = Double.parseDouble(TXTamount.getText());
//        double quan = Double.parseDouble(TXTquantity.getText());
//        double price = Double.parseDouble(TXTpriceEach.getText());;
//        double disAmount = Double.parseDouble(TXTdiscount.getText());
//        totDiscount = Double.parseDouble(TXTdiscount.getText());
//        
//        
        
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

//                disAmount = amount - (price * quan);
//                
//                totDiscount = totDiscount - disAmount;
//                
//                TXTdiscount.setText(Double.toString(totDiscount));
                
                //load Table
                tableLoad();

                
            }

            catch(Exception e)
            {
                    e.printStackTrace();
            }
            
            finally{
        
            try{
            
                pst.close();
                                
            
            }
            
            catch(Exception e){
        
                e.printStackTrace();
        
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
                calcDiscounts();
                
                
                //clear the fields
                clearItem();
        }

        catch(Exception e) 
        {
            
            e.printStackTrace();

        }
         
         finally{
        
            try{
            
                pst.close();
                
            
            }
            
            catch(Exception e){
        
                e.printStackTrace();
        
        }
        
        }
         clearItem();
        
        //}   
    }//GEN-LAST:event_BTNaddActionPerformed

    private void TXTamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTamountActionPerformed

    private void TXTitemCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTitemCodeActionPerformed
        
        
        String itemcode = TXTitemCode.getText();
               
        String sql = "SELECT i.description, i.priceeach, i.discounts FROM items i WHERE i.itemcode = '"+ itemcode +"' ";
                
        try {
        
            
        pst=conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        if(rs.next()) { 
            
            String description = rs.getString("description");
            TXTdescription.setText(description);
            String priceeach = rs.getString("priceeach");
            TXTpriceEach.setText(priceeach);
            String itemdiscounts = rs.getString("discounts");
            TXTitemdiscount.setText(itemdiscounts);
            
        
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
        
                e.printStackTrace();
        
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
        
        
        
        
        double popriceeach = Double.parseDouble(TXTpriceEach.getText());
        double poquantity = Double.parseDouble(TXTquantity.getText());
        double itemdiscount;
        
        if(!TXTitemdiscount.getText().isEmpty()){
        
            itemdiscount = Double.parseDouble(TXTitemdiscount.getText());
            
        
        }
        
        else{
        
            itemdiscount=0;
        
        }
        
        
        amount = (popriceeach - (popriceeach * itemdiscount/100)) * poquantity;
        
        TXTamount.setText(Double.toString(amount));
        
        
        
        
        
    }//GEN-LAST:event_TXTquantityActionPerformed

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
        
        double total = Double.parseDouble(TXTtotal.getText());
        //String itemcode = TXTitemCode.getText();
        String invoiceno = TXTinvoiceNo.getText();
        //System.out.println("kk");
        String deliverto = TXTAdeliverTo.getText();
        String discount = TXTdiscount.getText();
        String insubtotal = TXTsubtotal.getText();
        
        System.out.println(total);
        System.out.println(invoiceno);
        
//        
        String date = (JDCdate.getDate()).toString();
        double credBal = 0.0;
        double temp = Double.parseDouble(TXTtotal.getText());
        String stid = TXTstoreID.getText();
        
        System.out.println(date);
        System.out.println(stid);
        System.out.println(temp);
        
        updateCreditBalance();
        
        
        try
        {
            String query = "INSERT INTO sales (invoiceno,itemcode,description,priceeach,quantity,amount) SELECT invoiceno,itemcode,description,priceeach,quantity,amount FROM invoice";
                pst = conn.prepareStatement(query);
                pst.execute();
                
            //Clear the Temporary Invoice Table
                String q = "DELETE FROM invoice";
                 pst3 = conn.prepareStatement(q);
                 pst3.execute();
                
                 //load Table
                tableLoad();
                
                String sql = "INSERT INTO invoiceSummary(invoiceno,storeid,date,deliverto,subtotal,discounts,total) VALUES(?,?,?,?,?,?,?) ";
                
                pst1.setString(1,TXTinvoiceNo.getText());
                pst1.setString(2,TXTstoreID.getText());
                pst1.setString(3,(JDCdate.getDate()).toString());
                pst1.setString(4,TXTAdeliverTo.getText());
                pst1.setString(5,TXTsubtotal.getText());
                pst1.setString(6,TXTdiscount.getText());
                pst1.setString(7,TXTtotal.getText());
                pst1 = conn.prepareStatement(sql);
                pst1.execute();
              
                System.out.println("ll");
                String query1 = "SELECT s.creditAmount FROM store s, invoiceSummary i WHERE i.storeid = s.id ";
 
                pst2 = conn.prepareStatement(query1);
                rs = pst2.executeQuery();

                System.out.println("kk");
                
                while(rs.next()) {
 System.out.println("mm");
                    credBal = Double.parseDouble(rs.getString("creditAmount"));
//                  LBLcreditBal.setText(Double.toString(credBal));
//
                    System.out.println("******");
                    System.out.println(credBal);
                    
//                  credBal = Double.parseDouble(LBLcreditBal.getText());
                    credBal = credBal + temp;
                    System.out.println("yy");
                    
                    String query2 = "UPDATE store SET creditAmount='"+ credBal +"' WHERE id  = '"+ stid +"'";
                    //String query2 = "INSERT INTO store (creditAmount) VALUES ('"+ credBal +"') WHERE id = '"+ stid +"'";

                    pst4 = conn.prepareStatement(query2);
                    pst4.execute();
                
                
        }
    }

        catch(Exception e)
        {

            e.printStackTrace();

        }
        
        finally{
        
            try{
            
                pst.close();
                pst1.close();
                pst2.close();
                pst3.close();
                pst4.close();
                rs.close();
            }
            
            catch(Exception e){
        
                System.out.println(e);
        }
        
        }
        
        
        
        //clear the fields
        clearAll();
        
        
        
        
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

    private void TXTstoreNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTstoreNameKeyTyped
        
        
        
    }//GEN-LAST:event_TXTstoreNameKeyTyped

    private void TXTquantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTquantityKeyTyped
       /*String itemcode = TXTitemCode.getText();
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
        
        }
        
        finally{
        
            try{
            
                pst.close();
                rs.close();
            
            }
            
            catch(Exception e){
            
                System.out.println(e);
            
            }
        
        }*/
        
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
    }//GEN-LAST:event_TXTquantityKeyTyped

    private void TXTdiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTdiscountKeyTyped
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
    }//GEN-LAST:event_TXTdiscountKeyTyped

    private void TXTitemdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTitemdiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTitemdiscountActionPerformed

    private void TXTitemdiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTitemdiscountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTitemdiscountKeyPressed

    private void TXTitemdiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTitemdiscountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTitemdiscountKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNadd;
    private javax.swing.JButton BTNclear;
    private javax.swing.JButton BTNcopy;
    private javax.swing.JButton BTNdelete;
    private javax.swing.JButton BTNsaveNew;
    private javax.swing.JButton BTNupdate;
    private com.toedter.calendar.JDateChooser JDCdate;
    private javax.swing.JLabel LBLcreditBal;
    private javax.swing.JLabel LBLpolineno;
    private javax.swing.JTextArea TXTAbillTo;
    private javax.swing.JTextArea TXTAdeliverTo;
    private javax.swing.JTextField TXTamount;
    private javax.swing.JTextField TXTdescription;
    private javax.swing.JTextField TXTdiscount;
    private javax.swing.JTextField TXTinvoiceNo;
    private javax.swing.JTextField TXTitemCode;
    private javax.swing.JTextField TXTitemdiscount;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
double totDiscount;
}
