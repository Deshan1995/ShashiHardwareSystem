/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agency;

import DBconnection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author chath
 */
public class ProcessPayment extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement s = null;
    Date ds;
    
    /**
     * Creates new form ProcessPayment
     */
    public ProcessPayment() {
        initComponents();
                
        ds = new Date();
        
        JDCdate.setDate(ds);
        
        conn = DBconnect.connectDb();
        generateID();
        
        
        //load Table
        
        tableLoad();
        tableLoad1();
    }

    public void clear(){
    
            TXTstoreid.setText("");
            TXTstorename.setText("");
            TXTpaymentid.setText("");
            TXTcreditamount.setText("");
            TXTcashpayment.setText("");
            TXTcashpaidamount.setText("");
            TXTcashbalance.setText("");
            JDCdate.setDate(ds);
            
            
            generateID();
    
    }
    
    
    
    public void tableLoad1()
    {
    
        try{
        
            String sql = "SELECT Cheque_No,Account_No,Date,Bank_Name,Amount,Status FROM Cheque WHERE Cheque_FROM = 'Agency'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
         
            chequepaymenttable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
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
        
                System.out.println(e);
        
        }
        
        }
        
    }
    
    public void tableLoad()
    {
    
        try{
        
            String sql = "SELECT a.paymentid, s.name, a.amount, a.date FROM agencyPayments a, store s WHERE a.storeid = s.id";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
         
            cashpaymenttable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
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
        
                System.out.println(e);
        
        }
        
        }
        
    }
    
    
    
    public void generateID(){
        
        
        try{
            s = conn.createStatement();
            rs = s.executeQuery("SELECT MAX(paymentid) AS lastitem FROM agencyPayments");
           
            String paymentid;
            
            while(rs.next()){
                
                paymentid = rs.getString("lastitem");
                String pid[] = paymentid.split("PID");
                int no=Integer.parseInt(pid[1]);
                no = no + 1;
                TXTpaymentid.setText("PID"+no);
                
            }
        }
        
        catch (Exception e) {
            
            System.out.println(e);
        
        }
        
        finally{
        
            try{
            
                s.close();
                rs.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
       
    }
        
public void updateCreditBalance(){
    
   try{
//       String sql = "SELECT creditAmount from store";
//       pst=conn.prepareStatement(sql);
//      
//       rs = pst.executeQuery();
//       
//       String creditbal = rs.getString("creditAmount");
       
       double cAmount = Double.parseDouble(TXTcreditamount.getText());
       
       double cPay = Double.parseDouble(TXTcashpayment.getText());
       
       String stid = TXTstoreid.getText();
       
       cAmount=cAmount-cPay;
       
       String credAmount = Double.toString(cAmount);
       
       TXTcreditamount.setText(credAmount);
       
       String sql = "Update store set creditAmount='"+ credAmount +"' Where id='"+ stid +"' ";
       pst=conn.prepareStatement(sql);
       pst.execute();
       
   }   
   catch(Exception e){
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
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        TXTcashpayment = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        TXTcashpaidamount = new javax.swing.JTextField();
        TXTcashbalance = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cashpaymenttable = new javax.swing.JTable();
        BTNcashaddpayment = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        TXTchequechequeno = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        TXTchequebankno = new javax.swing.JTextField();
        TXTchequebranchno = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        TXTaccountno = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        TXTchequebankname = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        chequepaymenttable = new javax.swing.JTable();
        BTNchequeaddpayment = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        TXTchequepayment = new javax.swing.JTextField();
        TXTstorename = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        TXTstoreid = new javax.swing.JTextField();
        JDCdate = new com.toedter.calendar.JDateChooser();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        TXTpaymentid = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        TXTcreditamount = new javax.swing.JTextField();

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("   Payments");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("   Cash Payments");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1780, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
        );

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setText("Payment         :");

        TXTcashpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcashpaymentActionPerformed(evt);
            }
        });
        TXTcashpayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTcashpaymentKeyTyped(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Paid Amount  :");

        TXTcashpaidamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcashpaidamountActionPerformed(evt);
            }
        });
        TXTcashpaidamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTcashpaidamountKeyTyped(evt);
            }
        });

        TXTcashbalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcashbalanceActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setText("Balance           :");

        cashpaymenttable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(cashpaymenttable);

        BTNcashaddpayment.setText("Add Payment");
        BTNcashaddpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNcashaddpaymentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TXTcashpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TXTcashpaidamount, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TXTcashbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BTNcashaddpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTcashpayment)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTcashpaidamount)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTcashbalance)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addComponent(BTNcashaddpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 348, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cash Payments", jPanel1);

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Cheque Payments");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
        );

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Cheque No            :");

        TXTchequechequeno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTchequechequenoActionPerformed(evt);
            }
        });
        TXTchequechequeno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTchequechequenoKeyTyped(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setText("Bank No                :");

        TXTchequebankno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTchequebanknoActionPerformed(evt);
            }
        });
        TXTchequebankno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTchequebanknoKeyTyped(evt);
            }
        });

        TXTchequebranchno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTchequebranchnoActionPerformed(evt);
            }
        });
        TXTchequebranchno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTchequebranchnoKeyTyped(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setText("Branch No             :");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setText("Account No           :");

        TXTaccountno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTaccountnoActionPerformed(evt);
            }
        });
        TXTaccountno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTaccountnoKeyTyped(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setText("Bank Name           :");

        TXTchequebankname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTchequebanknameActionPerformed(evt);
            }
        });
        TXTchequebankname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTchequebanknameKeyTyped(evt);
            }
        });

        chequepaymenttable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(chequepaymenttable);

        BTNchequeaddpayment.setText("Add Payment");
        BTNchequeaddpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNchequeaddpaymentActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Payment                :");

        TXTchequepayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTchequepaymentActionPerformed(evt);
            }
        });
        TXTchequepayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTchequepaymentKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(BTNchequeaddpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addComponent(TXTaccountno, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addComponent(TXTchequebranchno, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addComponent(TXTchequechequeno, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(58, 58, 58))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(71, 71, 71)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TXTchequebankno)
                                        .addComponent(TXTchequebankname, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(TXTchequepayment, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTchequepayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTchequechequeno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTchequebankno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79)
                            .addComponent(TXTchequebankname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTchequebranchno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTaccountno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNchequeaddpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(178, 178, 178))
        );

        jTabbedPane1.addTab("Cheque Payments", jPanel2);

        TXTstorename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTstorenameActionPerformed(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel82.setText("Store ID               :");

        TXTstoreid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTstoreidActionPerformed(evt);
            }
        });
        TXTstoreid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTstoreidKeyTyped(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setText("Date                     :");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("Payment ID         :");

        TXTpaymentid.setEditable(false);
        TXTpaymentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTpaymentidActionPerformed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Current Credit Amount    :");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setText("Store Name         :");

        TXTcreditamount.setEditable(false);
        TXTcreditamount.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TXTcreditamount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TXTcreditamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcreditamountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TXTpaymentid, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(TXTstoreid))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTstorename, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDCdate, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TXTcreditamount, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TXTpaymentid)
                                    .addComponent(jLabel83))
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JDCdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TXTstorename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(TXTstoreid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TXTcreditamount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTchequechequenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTchequechequenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTchequechequenoActionPerformed

    private void TXTcashpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcashpaymentActionPerformed
        TXTcashpaidamount.requestFocus();
    }//GEN-LAST:event_TXTcashpaymentActionPerformed

    private void TXTcashpaidamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcashpaidamountActionPerformed
        
//        String storename = TXTstoreName.getText();
//        String storeid = TXTstoreID.getText();
//        String paymentmethod = CMBpaymentMethod.getSelectedItem().toString();
        double amountpaid = Double.parseDouble(TXTcashpaidamount.getText());
        double payment = Double.parseDouble(TXTcashpayment.getText());
        double balance = 0;
        double creditbalance = Double.parseDouble(TXTcreditamount.getText());
        double temp;
        
        
        
            if(amountpaid > payment){
            
                balance = amountpaid - payment;
                
                
                             
            }
            
            else {
            
                balance = 0;
                
            }
            
            TXTcashbalance.setText(Double.toString(balance));
            TXTcreditamount.setText(Double.toString(creditbalance));
            
            

    }//GEN-LAST:event_TXTcashpaidamountActionPerformed

    private void TXTcashbalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcashbalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcashbalanceActionPerformed

    private void TXTchequebanknoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTchequebanknoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTchequebanknoActionPerformed

    private void TXTchequebranchnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTchequebranchnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTchequebranchnoActionPerformed

    private void TXTaccountnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTaccountnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTaccountnoActionPerformed

    private void TXTchequebanknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTchequebanknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTchequebanknameActionPerformed

    private void TXTpaymentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTpaymentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTpaymentidActionPerformed

    private void TXTcreditamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcreditamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcreditamountActionPerformed

    private void TXTstorenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTstorenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTstorenameActionPerformed

    private void TXTstoreidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTstoreidActionPerformed
        
        
        
        
        String stid = TXTstoreid.getText();
        
        String sql = "SELECT name, creditAmount FROM store WHERE id = '"+ stid +"' ";
         
        try {
        
            
        pst=conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        if(rs.next()) { 
            
            String name = rs.getString("name");
            TXTstorename.setText(name);
            String credAmount = rs.getString("creditAmount");
            TXTcreditamount.setText(credAmount);
            
            
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
        
        
        
        
    }//GEN-LAST:event_TXTstoreidActionPerformed

    private void BTNchequeaddpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNchequeaddpaymentActionPerformed
        if(TXTchequechequeno.getText().equals("") || TXTchequebranchno.getText().equals("") || TXTchequebankno.getText().equals("") || TXTchequebankname.getText().equals("") || TXTaccountno.getText().equals("")) 
        {

            JOptionPane.showMessageDialog(null,"All fields are required to be filled");

        }
       
        
            
        else{
        
            String paymentid = TXTpaymentid.getText();
            String stid = TXTstoreid.getText();
            String chequno = TXTchequechequeno.getText();
            String bankno = TXTchequebankno.getText();
            String bankname = TXTchequebankname.getText();
            String branchno = TXTchequebranchno.getText();
            String accountno = TXTaccountno.getText();
            Date date = JDCdate.getDate();
            String amount = TXTchequepayment.getText();
            String status = "Pending";
            String chequefrom = "Agency";
            String chequeto = "N/A";
        
        
        String q = "INSERT INTO Cheque (Cheque_No,Bank_No,Branch_No,Account_No,Bank_Name,Amount,Date,Cheque_From,Cheque_To,Status) values('"+ chequno +"' , '"+ bankno +"' , '"+ branchno +"' , '"+ accountno +"' , '"+ bankname +"' , '"+ amount +"' , '"+ date +"' , '"+ chequefrom +"' , '"+ chequeto +"' , '"+ status +"')";

         try
        {
            
                pst = conn.prepareStatement(q);
                pst.execute();
                
                //load Table
                tableLoad();
                
                
                
                
               

       
                double cAmount = Double.parseDouble(TXTcreditamount.getText());
       
                double cPay = Double.parseDouble(TXTchequepayment.getText());
       
                
       
                cAmount=cAmount-cPay;
       
                String credAmount = Double.toString(cAmount);
       
                TXTcreditamount.setText(credAmount);
       
                String sql = "Update store set creditAmount='"+ credAmount +"' Where id='"+ stid +"' ";
                pst=conn.prepareStatement(sql);
                pst.execute();
                
                
                
                
                
                
                clear();
       
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
    }//GEN-LAST:event_BTNchequeaddpaymentActionPerformed

    private void BTNcashaddpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNcashaddpaymentActionPerformed
        if(TXTpaymentid.getText().equals("") || TXTstoreid.getText().equals("") || TXTcashpayment.getText().equals("") || TXTcashpaidamount.getText().equals("") || TXTcashbalance.getText().equals("")) 
        {

            JOptionPane.showMessageDialog(null,"All fields are required to be filled");

        }
       
        
            
        else{
        
            String paymentid = TXTpaymentid.getText();
            String stid = TXTstoreid.getText();
            String payment = TXTcashpayment.getText();
            String paidamount = TXTcashpaidamount.getText();
            String balance = TXTcashbalance.getText();
            Date date = JDCdate.getDate();
        
        
            
        
        
        String q = "INSERT INTO agencyPayments (paymentid,storeid,amount,paidamount,balance,date) values('"+ paymentid +"' , '"+ stid +"' , '"+ payment +"' , '"+ paidamount +"' , '"+ balance +"' , '"+ date +"')";

         try
        {
            
                pst = conn.prepareStatement(q);
                pst.execute();
                
                //load Table
                tableLoad();
                
                
                updateCreditBalance();
                
                clear();
                
                
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
                                          

   
            
        
    }//GEN-LAST:event_BTNcashaddpaymentActionPerformed

    private void TXTchequepaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTchequepaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTchequepaymentActionPerformed

    private void TXTstoreidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTstoreidKeyTyped
       
        
    }//GEN-LAST:event_TXTstoreidKeyTyped

    private void TXTcashpaymentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTcashpaymentKeyTyped
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Digits");
        }
    }//GEN-LAST:event_TXTcashpaymentKeyTyped

    private void TXTcashpaidamountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTcashpaidamountKeyTyped
char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Digits");
        }
    }//GEN-LAST:event_TXTcashpaidamountKeyTyped

    private void TXTchequepaymentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTchequepaymentKeyTyped
char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Digits");
        }    }//GEN-LAST:event_TXTchequepaymentKeyTyped

    private void TXTchequechequenoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTchequechequenoKeyTyped
        char c=evt.getKeyChar();

        String check = TXTchequechequeno.getText()+c;

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
        
        else if(check.length() > 6){
        
            evt.consume();
            JOptionPane.showMessageDialog(null, "Only 6 numbers are allowed");
        
        }
    }//GEN-LAST:event_TXTchequechequenoKeyTyped

    private void TXTchequebanknoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTchequebanknoKeyTyped
        char c=evt.getKeyChar();

        String check = TXTchequebankno.getText()+c;

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
        
        else if(check.length() > 4){
        
            evt.consume();
            JOptionPane.showMessageDialog(null, "Only 4 numbers are allowed");
        
        }
    }//GEN-LAST:event_TXTchequebanknoKeyTyped

    private void TXTchequebranchnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTchequebranchnoKeyTyped
       char c=evt.getKeyChar();

        String check = TXTchequebranchno.getText()+c;

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
        
        else if(check.length() > 3){
        
            evt.consume();
            JOptionPane.showMessageDialog(null, "Only 3 numbers are allowed");
        
        }
    }//GEN-LAST:event_TXTchequebranchnoKeyTyped

    private void TXTaccountnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTaccountnoKeyTyped
        char c=evt.getKeyChar();

        String check = TXTaccountno.getText()+c;

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
        
        else if(check.length() > 10){
        
            evt.consume();
            JOptionPane.showMessageDialog(null, "Only 10 numbers are allowed");
        
        }
    }//GEN-LAST:event_TXTaccountnoKeyTyped

    private void TXTchequebanknameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTchequebanknameKeyTyped
        char c=evt.getKeyChar();

        if(Character.isDigit(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Letters");
        }
    }//GEN-LAST:event_TXTchequebanknameKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNcashaddpayment;
    private javax.swing.JButton BTNchequeaddpayment;
    private com.toedter.calendar.JDateChooser JDCdate;
    private javax.swing.JTextField TXTaccountno;
    private javax.swing.JTextField TXTcashbalance;
    private javax.swing.JTextField TXTcashpaidamount;
    private javax.swing.JTextField TXTcashpayment;
    private javax.swing.JTextField TXTchequebankname;
    private javax.swing.JTextField TXTchequebankno;
    private javax.swing.JTextField TXTchequebranchno;
    private javax.swing.JTextField TXTchequechequeno;
    private javax.swing.JTextField TXTchequepayment;
    private javax.swing.JTextField TXTcreditamount;
    private javax.swing.JTextField TXTpaymentid;
    private javax.swing.JTextField TXTstoreid;
    private javax.swing.JTextField TXTstorename;
    private javax.swing.JTable cashpaymenttable;
    private javax.swing.JTable chequepaymenttable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
