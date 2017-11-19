/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset;

/**
 *
 * @author User
 */
import DBconnection.DBconnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Assets extends javax.swing.JInternalFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement ps = null;
PreparedStatement ps1 = null;
Statement s = null;

    int month,day,year,hour,minute,second;
    int no;
    /**
     * Creates new form Assets
     */
    public Assets() {
        initComponents();
        conn=DBconnect.connectDb();
        auto();
        CurrentDate();
        showtime();
        tableUpdate();
    }
    
    public void CurrentDate(){
        Calendar cal = new GregorianCalendar();
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        day = cal.get(Calendar.DAY_OF_MONTH);
        //lbl_date.setText(year+"/"+(month+1)+"/"+day); 
    }
    
    public void showtime(){
        new Timer(0,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar cal = new GregorianCalendar();
                hour = cal.get(Calendar.HOUR);
                minute = cal.get(Calendar.MINUTE);
                second = cal.get(Calendar.SECOND);
               // lbl_time.setText(hour+":"+minute+":"+second); 
            }
        }).start();
                
    }
    
    public void tableUpdate(){
        try{
            String sql="Select * from Asset ";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            Asset.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    
    public void PaytableUpdate(){
        try{
            String sql="Select * from Pay ";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            pay.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    
    public void clear(){
               
               cmb_category.setSelectedIndex(0);
               txt_Description.setText("");
               txt_cost.setText("");
               txt_Rate.setText("");
               txt_life.setText("");
               ((JTextField)purchase_date.getDateEditor().getUiComponent()).setText("");
               
               txt_assetId.setEditable(true);
               cmb_category.setEnabled(true);
               
               tableUpdate();
               auto();
    }
    
    public void auto(){
      
    ArrayList<String> list = new ArrayList<String>();
        try {
            String sql = "SELECT Asset_Id as assetid FROM Asset";
            
            ps=conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                list.add(rs.getString("assetid"));
            }
            
            
            String a = list.get(list.size()-1);
           
            String ino[]=a.split("A");
            
                
            no=Integer.parseInt(ino[1]);
            no=no+1;
            txt_assetId.setText("A"+no);
    
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    
    }
    int no1;
public void auto1(){
    
        ArrayList<String> list = new ArrayList<String>();
        try {
            String a = txt_assetId.getText();
            String ino[]=a.split("A");
            no1=Integer.parseInt(ino[1]);
            
            String sql = "SELECT Asset_Id as assetid FROM Asset";
            
            ps=conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                list.add(rs.getString("assetid"));
            }
    
            for(int i=0;i<=list.size()-1;i++){
	              
                String b = list.get(i);
                String ino1[]=b.split("A");
            
                no=Integer.parseInt(ino1[1]);

		if(no1+1<=no){

                    int j=no1+(i-1);
                    String id="A"+j;
                  
                    String sql1="Update Asset set Asset_Id='"+id+"' where Asset_Id='"+b+"'";
                    ps=conn.prepareStatement(sql1);
                    ps.execute();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    
     }
public void pay(){
    
    String temp1 = (String)cmb_category.getSelectedItem(); 
    String temp2 = (String)cmb_pay.getSelectedItem(); 
        
        try{
            String sql="Insert into Pay(Asset_Id,Categroy,Description,Date,Amount,Pay_Type) values(?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_assetId.getText());
            ps.setString(2,temp1);
            ps.setString(3,txt_Description.getText());
            ps.setString(4,((JTextField)purchase_date.getDateEditor().getUiComponent()).getText());
            ps.setString(5,txt_cost.getText());
            ps.setString(6,temp2);

            if(validation1()){
                ps.execute();
            }
            
  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        
        tableUpdate();
          
        
        
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        payment = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        pay = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_pay = new javax.swing.JTextField();
        btn_paySearch = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_desc = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_cost = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_assetId = new javax.swing.JTextField();
        cmb_category = new javax.swing.JComboBox();
        purchase_date = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txt_Description = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Asset = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btn_view = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cmb_pay = new javax.swing.JComboBox<>();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_Rate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_life = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        payment.setLocation(new java.awt.Point(365, 0));
        payment.setSize(new java.awt.Dimension(1000, 500));

        pay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Asset_Id", "Asset_Category", "Asset_Description", "Amount", "Purchase_Date", "Payment_Type"
            }
        ));
        jScrollPane2.setViewportView(pay);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Asset_Id");

        txt_pay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_pay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btn_paySearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_paySearch.setText("Search");
        btn_paySearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_paySearchActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Asset_Description");

        txt_desc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_desc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout paymentLayout = new javax.swing.GroupLayout(payment.getContentPane());
        payment.getContentPane().setLayout(paymentLayout);
        paymentLayout.setHorizontalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(paymentLayout.createSequentialGroup()
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentLayout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(btn_paySearch))
                    .addGroup(paymentLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(92, 92, 92)
                        .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paymentLayout.setVerticalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btn_paySearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        setPreferredSize(new java.awt.Dimension(1000, 705));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asset Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Purchase Date");

        txt_cost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cost.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_cost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_costKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_costKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Purchase Cost");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Asset Category");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Asset Id");

        txt_assetId.setEditable(false);
        txt_assetId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_assetId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_assetId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_assetIdKeyPressed(evt);
            }
        });

        cmb_category.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmb_category.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Select>", "Computer ", "Furniture", "Machinary", "Vehicle" }));
        cmb_category.setBorder(null);
        cmb_category.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmb_categoryKeyPressed(evt);
            }
        });

        purchase_date.setDateFormatString("yyyy-MM-dd");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Asset Description");

        txt_Description.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Description.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_DescriptionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_category, 0, 150, Short.MAX_VALUE)
                                    .addComponent(txt_assetId))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(purchase_date, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_assetId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmb_category, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purchase_date, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        Asset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Asset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Asset Id", "Asset Category", "Asset description", "Purchase date", "Asset value", "Depreciation rate", "Depreciable life"
            }
        ));
        Asset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AssetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Asset);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        btn_view.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_view.setText("View");
        btn_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Payment Type");

        cmb_pay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmb_pay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "Cash", "Cheque", "Loan" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(btn_view, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btn_view)
                .addGap(20, 20, 20))
        );

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_refresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Depreciation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Depreciation Rate");

        txt_Rate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Rate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RateKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RateKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Depreciable Life");

        txt_life.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_life.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_life.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_lifeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_life, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_life, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_search)
                            .addComponent(btn_refresh)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(btn_delete)
                    .addComponent(btn_insert)
                    .addComponent(btn_clear))
                .addGap(30, 30, 30))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(989, 50));

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("   Asset");
        jLabel5.setPreferredSize(new java.awt.Dimension(34, 36));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(152, 152, 152))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        String temp1 = (String)cmb_category.getSelectedItem(); 
        
        try{
            String sql="Insert into Asset(Asset_Id,Asset_Category,Asset_Description,Purchased_Date,Purchased_cost,Depreciation_Rate,Depreciable_Life) values(?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_assetId.getText());
            ps.setString(2,temp1);
            ps.setString(3,txt_Description.getText());
            ps.setString(4,((JTextField)purchase_date.getDateEditor().getUiComponent()).getText());
            ps.setString(5,txt_cost.getText());
            ps.setString(6,txt_Rate.getText());
            ps.setString(7,txt_life.getText());
          
           
            
            if(validation1()){
                ps.execute();
            }
  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        pay();
        tableUpdate();
          
        
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        
    int d=JOptionPane.showConfirmDialog(null,"Do You Really Want To Update","Update",JOptionPane.YES_NO_OPTION);
    if(d==0)
    { 
        try{
            String val1 = txt_Description.getText();
            String val2 = ((JTextField)purchase_date.getDateEditor().getUiComponent()).getText();
            String val3 = txt_cost.getText();
            String val4 = txt_Rate.getText();
            String val5 = txt_life.getText();
            String val6 = cmb_pay.getSelectedItem().toString();
          
            String sql = "Update Asset set Asset_Description='"+val1+"',Purchased_Date='"+val2+"',Purchased_Cost='"+val3+"',Depreciation_Rate='"+val4+"',Depreciable_Life='"+val5+"' where Asset_Id=?";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_assetId.getText());

                ps.execute();
            
            String sql1 = "Update Pay set Pay_Type='"+val6+"' where Asset_Id=?";
            
            ps1=conn.prepareStatement(sql1);
            ps1.setString(1,txt_assetId.getText());

                ps1.execute();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"a");
        }
          
        finally{
            try{
                ps.close();
                ps1.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        
        tableUpdate();
    }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       
    int d=JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete","Delete",JOptionPane.YES_NO_OPTION);
    if(d==0)
    {   
        try{
            String sql = "delete from Asset where Asset_Id=?";
        
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_assetId.getText());
           
            ps.execute();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        auto1();
        tableUpdate();
        
        clear();
    }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void AssetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssetMouseClicked
        
        DefaultTableModel model =  (DefaultTableModel) Asset.getModel();
        int selectedRowIndex = Asset.getSelectedRow();
        
        txt_assetId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        cmb_category.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
        txt_Description.setText(model.getValueAt(selectedRowIndex, 2).toString());
        ((JTextField)purchase_date.getDateEditor().getUiComponent()).setText(model.getValueAt(selectedRowIndex, 3).toString());
        txt_cost.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txt_Rate.setText(model.getValueAt(selectedRowIndex, 5).toString());
        txt_life.setText(model.getValueAt(selectedRowIndex, 6).toString());
        
        txt_assetId.setEditable(false);
        cmb_category.setEnabled(false);
        
    }//GEN-LAST:event_AssetMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        
        try{
       
            String sql="Select Asset_Id,Asset_Category,Asset_Description,Purchased_Date,Purchased_cost,Depreciation_Rate,Depreciable_Life from Asset where Asset_Id Like '%"+txt_assetId.getText()+"%' AND Asset_Description Like '%"+txt_Description.getText()+"%'";
       
            ps=conn.prepareStatement(sql);
            
            rs=ps.executeQuery();
            Asset.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clear();
               
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        tableUpdate();
        clear();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void txt_costKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_costKeyTyped

    private void txt_RateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RateKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_RateKeyTyped

    private void txt_lifeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lifeKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_lifeKeyTyped

    private void txt_assetIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_assetIdKeyPressed
        if(evt.getKeyCode()==10){      
                cmb_category.grabFocus();   
        }
    }//GEN-LAST:event_txt_assetIdKeyPressed

    private void cmb_categoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmb_categoryKeyPressed
        if(evt.getKeyCode()==10){
                txt_Description.grabFocus(); 
        }
    }//GEN-LAST:event_cmb_categoryKeyPressed

    private void txt_DescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DescriptionKeyPressed
        if(evt.getKeyCode()==10){
            if(txt_Description.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a description", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                txt_cost.grabFocus();    
            }
        }
    }//GEN-LAST:event_txt_DescriptionKeyPressed

    private void txt_costKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costKeyPressed
       if(evt.getKeyCode()==10){
            if(txt_cost.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a cost Value", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                txt_Rate.grabFocus();    
            }
        }
    }//GEN-LAST:event_txt_costKeyPressed

    private void txt_RateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RateKeyPressed
        if(evt.getKeyCode()==10){
            if(txt_Rate.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a rate Value", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                txt_life.grabFocus();    
            }
        }
    }//GEN-LAST:event_txt_RateKeyPressed

    private void btn_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewActionPerformed
        payment.setVisible(true);
        PaytableUpdate();
//            try{
//            String sql="Select Asset_Id,Categroy,Description,Amount,Date,Pay_Type From Pay where Asset_Id Like '%"+txt_assetId.getText()+"%'";
//
//                ps=conn.prepareStatement(sql);
//
//                rs=ps.executeQuery();
//                pay.setModel(DbUtils.resultSetToTableModel(rs));
//
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(null,e);
//            }
        
    }//GEN-LAST:event_btn_viewActionPerformed

    private void btn_paySearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_paySearchActionPerformed
        try{
       
       String sql="Select * from Pay where Asset_Id Like '%"+txt_pay.getText()+"%' AND Description Like '%"+txt_desc.getText()+"%'";
       
            ps=conn.prepareStatement(sql);
          
            rs=ps.executeQuery();
            pay.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btn_paySearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Asset;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_paySearch;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_view;
    private javax.swing.JComboBox cmb_category;
    private javax.swing.JComboBox<String> cmb_pay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable pay;
    private javax.swing.JDialog payment;
    private com.toedter.calendar.JDateChooser purchase_date;
    private javax.swing.JTextField txt_Description;
    private javax.swing.JTextField txt_Rate;
    private javax.swing.JTextField txt_assetId;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JTextField txt_desc;
    private javax.swing.JTextField txt_life;
    private javax.swing.JTextField txt_pay;
    // End of variables declaration//GEN-END:variables

    private boolean validation1() {
    boolean bool=true ;   
    try {
            
            if(txt_assetId.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter an asset id", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(cmb_category.getSelectedItem()=="<Select>")
            {
                JOptionPane.showConfirmDialog(rootPane, "Select a asset category", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_Description.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a asset description", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(((JTextField)purchase_date.getDateEditor().getUiComponent()).getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a purchased date", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_cost.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter the purchased cost ", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_Rate.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a depreciation rate", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_life.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a depreciable life", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(cmb_pay.getSelectedItem()=="<Select>")
            {
                JOptionPane.showConfirmDialog(rootPane, "Select a payment type", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
             }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return bool;
}
    
}
