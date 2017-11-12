/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer_and_Order;

import DBconnection.DBconnect;
import Delivery.models.OrderDelivery;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Float.parseFloat;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Bhanuka Yapa
 */
public class Order_sub extends javax.swing.JInternalFrame {

    /**
     * Creates new form Order_sub
     */
    
    Connection conn=null;
    String delID=null;
    Float delCharge=0f;
    
    String cusID=null;
    Float pastLoan=0f;
    
    String cashAmount =null;
    String chequeAmount = null;
    String chequeNum = null;
    
    String loanAmount = null;
    String itemNo=null;
   
     double rate1 =0;
    double rate2 =0;
    double rate3 =0;
    boolean busy =false;
    boolean road= false;
    boolean fr = false;
    
    private static final String API_KEY = "AIzaSyBeRbLtOqrLbPkyyObLwzLyedOur9bFgm8";
    
    public Order_sub() {
        initComponents();
        
        conn = DBconnect.connectDb();    //establish db connection
        System.out.println("Connected to database");
        
        loadAvailableItemList();
        
        txtA_itemDes.setEditable(false);
        
        btn_assignCus.setEnabled(false);
        btn_removeCus.setEnabled(false);
        
        txt_total.setEditable(false);
        txt_totDis.setEditable(false);
        txt_netValue.setEditable(false);
        txt_other.setEditable(false);

        txt_pre.setEditable(false);
        txt_totToBePaid.setEditable(false);
        txt_nowPaying.setEditable(false);
        txt_remaining.setEditable(false);
       
        //lbl_cusResult.setVisible(false);
        
        txt_nowPaying.setEditable(false);
        loadItemsBoughtTable1();
        
        
        btn_giveLoan.setEnabled(false);
        btn_confirmCheque.setEnabled(false);
        chkbx_addPastLoan.setEnabled(false);
        
        
        jTextArea1.setVisible(false);
        delivIdTxt.setEditable(false);
        distanceTxt.setEditable(false);
        loadDrivers();
        loadVehicles();
        loadOrderDeliverTable();
        validateDeliveID();
        
     
    }
    
    public String getItemCategory(String ItemID){
        
        String category=null;        
        String key=null;
        
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;
        
         try{
            if(ItemID.length()>=3)
                key = ItemID.substring(0, 3);
            else
                key = ItemID;
            
            System.out.println(key);
            
            ps_category = conn.prepareStatement("select category from ItemCodes where keyword like '%"+key+"%'");         
            rs_category = ps_category.executeQuery();
            rs_category.next();
            category = rs_category.getString("category");
         }catch (Exception e){
            
            System.out.print(e + "Error finding item category");
            //ex.printStackTrace();
         }finally {
            
            try{            
                if (ps_category != null) 
                { 
                   ps_category.close();
                }
                if (rs_category != null) 
                { 
                   rs_category.close();
                } 
                          
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
         }
         
        return category; 
    }
    
    
    public void loadItemsBoughtTable1(){
        PreparedStatement ps =null;
        ResultSet rs = null;
    
        try{
            ps=conn.prepareStatement("SELECT itemNo,itemID,description,qty,unitPrice,unitDiscount,netItemPrice FROM ItemsBought_temp");
            rs=ps.executeQuery();
            tbl_itemBought.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            try{            
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }
    
    }
    
    public void finalize() throws SQLException {
        conn.close();
    }
    public void lock(){
        
        btn_assignCus.setEnabled(false);
        btn_removeCus.setEnabled(false);
        btn_addCus.setEnabled(false);
        btn_addEntry.setEnabled(false);
        btn_removeEntry.setEnabled(false);
        
    }
    public Boolean validateItemEntry(){
        
        if((txt_itemID.getText().isEmpty()) ||(txt_qty.getText().isEmpty())||(txt_unitPrice.getText().isEmpty())||(txt_unitDis.getText().isEmpty())){
            JOptionPane.showMessageDialog(null,"Please Enter all the Item details","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(txtA_itemDes.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Enter a valid Item ID","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        String qty=txt_qty.getText();
        String unit=txt_unitPrice.getText();
        String unitDis=txt_unitDis.getText();
        
        
        for(int i=0; i<qty.length(); i++){
            
            if(!Character.isDigit(qty.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter only numbers for the quantity.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        for(int i=0; i<unit.length(); i++){
            
            if(!Character.isDigit(unit.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter only numbers for the unit price.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        for(int i=0; i<unitDis.length(); i++){
            
            if(!Character.isDigit(unitDis.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter only numbers for the unit discount.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        
        return true;
    }
    
    public void clearForm(){
        
    }
    
    public void updateTot(){
        
        PreparedStatement ps =null;
        ResultSet rs=null;
        String subTotal="0";
        String discount="0";
        String netValue="0";       
    
        try{
            ps=conn.prepareStatement("select sum(qty*unitPrice) as subTotal,\n" +
                                "sum(qty*unitDiscount) as discount ,\n" +
                                "sum(qty*unitPrice-qty*unitDiscount) as netValue\n" +
                                "from ItemsBought_temp");
            rs=ps.executeQuery();
            rs.next();
            subTotal=rs.getString(1);
            discount=rs.getString(2);
            netValue=rs.getString(3);        
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
        
            try{            
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
        /////////////////////////////////////////////////////////////////
        //in case user removes all the items, have to avoid null exception
        if(subTotal==null)
            subTotal="0";
        if(discount==null)
            discount="0";
        if(netValue==null)
            netValue="0";
 
        //set values for the left side payment details
        Float totalF = Float.parseFloat(netValue)+this.delCharge;
        String total = totalF.toString();        
        
        txt_total.setText(subTotal.toString());
        txt_totDis.setText(discount.toString());
        txt_netValue.setText(netValue.toString());
        txt_other.setText(this.delCharge.toString());
        lbl_total.setText(total);
        
        //Set values for the right side payment details
        Float totToPay;
        if(chkbx_addPastLoan.isSelected())
            totToPay = totalF+this.pastLoan;
        else 
            totToPay=totalF;
        
        txt_pre.setText(this.pastLoan.toString());
        txt_totToBePaid.setText(totToPay.toString());

        
    }
    
    public void updatePayDetails(){
        
        //setting now paying value
        Float nowPaying=0f;
        Float cashF=0f;
        Float chequeF=0f;
        
        if(this.cashAmount!=null){
            cashF=Float.parseFloat(this.cashAmount);
        }
        if(this.chequeAmount!=null){
            chequeF=Float.parseFloat(this.chequeAmount);
        }
        
        nowPaying=cashF+chequeF;        
        txt_nowPaying.setText(nowPaying.toString());
        
        //setting remaining value
        Float remaining = Float.parseFloat(txt_totToBePaid.getText())-nowPaying;
        txt_remaining.setText(remaining.toString());
        
        //setting amounts for "pay by" boxes
        updatePayByBoxes();
            
    }
    public void updatePayByBoxes(){
        Float remaining = Float.parseFloat(txt_remaining.getText());
        
        if((this.cashAmount==null)&&(this.chequeAmount==null)){
            txt_amountCash1.setText(remaining.toString());
            txt_amountCheque.setText(remaining.toString());
        }else if(this.chequeAmount==null){
            txt_amountCheque.setText(remaining.toString());
        }else if(this.cashAmount==null){
            txt_amountCash1.setText(remaining.toString());
        }
    }
    void loadAvailableItemList(){
        
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = txt_itemID.getText();
        String key=null;
        
        String itemName=null;
        String q=null;
        
        String category=getItemCategory(itemID);
        System.out.println(category);
        
        try{
            
            if(category.equals("Paint&Thinner")){
                q = "SELECT ItemID,Catergory,BrandName,ColourCode FROM Paint_Thinner WHERE ItemID LIKE '%"+itemID+"%'";                
            }else if(category.equals("Construction")){
                q = "SELECT ItemID,ItemName,BrandName,size,length FROM Construction WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Roofing&Fitting")){
                q = "SELECT ItemID, Catergary,Type,BrandName,size,Colour, Qty FROM Roofing_Fitting WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Waterpipe&Fittings")){
                q = "SELECT ItemID , ItemName,BrandName,Size,Qty FROM WaterPipe_Fitting WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Chemical&Farming")){
                q = "SELECT ItemID,ItemName,BrandName,Duration,Qty FROM Chemical_Farming WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Other")){
                q = "SELECT ItemID,ItemName,BrandName,Colour,Size,Qty FROM OtherItem WHERE ItemID LIKE '%"+itemID+"%'";
            }
            //ps_itemList.setString(1, itemID);
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();
            
            int count =0;
            while(rs_itemList.next()){
                count++;
            }
            System.out.println("count "+count);
            rs_itemList = ps_itemList.executeQuery();
            
            if(count==1){
                if(category.equals("Paint&Thinner")){
                    itemName= rs_itemList.getString("Catergory")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("ColourCode");
                }else if(category.equals("Construction")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("size")+" "+ rs_itemList.getString("length");
                }else if(category.equals("Roofing&Fitting")){
                    itemName= rs_itemList.getString("Catergary")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("Type")+" "+ rs_itemList.getString("size")+" "+ rs_itemList.getString("Colour");
                }else if(category.equals("Waterpipe&Fittings")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("Size");
                }else if(category.equals("Chemical&Farming")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName");
                }else if(category.equals("Other")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("Colour")+" "+ rs_itemList.getString("Size");
                }
                
                txtA_itemDes.setText(itemName);
            }else
                txtA_itemDes.setText("");
            
            rs_itemList = ps_itemList.executeQuery();
            tbl_availableList.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            //ex.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }  
        
        

    }
    /*
    
    public void findTot(){
        
        //set values for the left side payment details
        Float unitPrice = parseFloat(txt_unitPrice.getText());
        Float unitDis = parseFloat(txt_unitDis.getText());
        Float qty = parseFloat(txt_qty.getText());
        Float entryTot = qty*unitPrice;
        Float entryTotDis = qty*unitDis;
        
        Float totItem=Float.parseFloat(txt_total.getText())+ entryTot;
        Float totDis = Float.parseFloat(txt_totDis.getText())+ entryTotDis;
        Float netValue = totItem-totDis;
        Float other = Float.parseFloat(txt_other.getText());
        Float total = netValue+other;
        
        txt_total.setText(totItem.toString());
        txt_totDis.setText(totDis.toString());
        txt_netValue.setText(netValue.toString());
        lbl_total.setText(total.toString());
        
        //Set values fr right side
        String preLoan="0";
        
        if(this.cusID!=null){
            PreparedStatement ps=null;
            ResultSet rs=null;
            
            try {
                ps = conn.prepareStatement("select totalOutstanding from customer where cusID ='"+this.cusID+"'");                
                preLoan = rs.getString("totalOutstanding");
                ps.executeQuery();
                txt_pre.setText(preLoan);
            
            } catch (Exception ex) {
                System.out.println(ex);
            }
            
        }
        
        
        
        //set values for the right side of payment details
        Float pre = parseFloat(txt_pre.getText());
        Float totToPay = total+pre;
        
        txt_totToBePaid.setText(totToPay.toString());
        txt_remaining.setText(totToPay.toString());
        
        
        //setting pay by Cash suggestion value
        txt_amountCash1.setText(totToPay.toString());
        
        
        
    }  */

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        orderDeliveryInt = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        vehiTxt = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        driverIdTxt = new javax.swing.JComboBox();
        delivIdTxt = new javax.swing.JTextField();
        addressTxt = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        busyhBox = new javax.swing.JCheckBox();
        rdConBox = new javax.swing.JCheckBox();
        fragileBox = new javax.swing.JCheckBox();
        calbutton = new javax.swing.JButton();
        confirmbtn = new javax.swing.JButton();
        distanceTxt = new javax.swing.JTextField();
        totalTxt = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        statuscombo = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        orderDelTable = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_itemID = new javax.swing.JTextField();
        txt_qty = new javax.swing.JTextField();
        txt_unitPrice = new javax.swing.JTextField();
        txt_unitDis = new javax.swing.JTextField();
        btn_addEntry = new javax.swing.JButton();
        btn_removeEntry = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_availableList = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtA_itemDes = new javax.swing.JTextArea();
        lbl_orderID = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_cusID = new javax.swing.JTextField();
        txt_cusName = new javax.swing.JTextField();
        btn_assignCus = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txt_delID = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        lbl_cusStatus = new javax.swing.JLabel();
        lbl_cusResult = new javax.swing.JLabel();
        btn_removeCus = new javax.swing.JButton();
        btn_addCus = new javax.swing.JButton();
        btn_addCus1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_itemBought = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_totDis = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_netValue = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_other = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_remaining = new javax.swing.JTextField();
        txt_totToBePaid = new javax.swing.JTextField();
        txt_pre = new javax.swing.JTextField();
        btn_giveLoan = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_amountCash1 = new javax.swing.JTextField();
        txt_cash1 = new javax.swing.JTextField();
        txt_balanceCash1 = new javax.swing.JTextField();
        btn_confirmCash1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        txt_amountCheque = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_numCheque = new javax.swing.JTextField();
        btn_confirmCheque = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lbl_confirmCheque = new javax.swing.JLabel();
        lbl_confirmCash = new javax.swing.JLabel();
        chkbx_addPastLoan = new javax.swing.JCheckBox();
        txt_nowPaying = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

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
        jScrollPane1.setViewportView(jTable1);

        orderDeliveryInt.setSize(new java.awt.Dimension(1200, 800));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Schedule", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Deliver ID");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Address");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Date");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Vehicle");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Driver");

        addressTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTxtActionPerformed(evt);
            }
        });
        addressTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addressTxtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel9)
                    .addComponent(jLabel33)
                    .addComponent(jLabel4)
                    .addComponent(jLabel34))
                .addGap(41, 41, 41)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vehiTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(driverIdTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressTxt)
                    .addComponent(delivIdTxt))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(delivIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel16)
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(vehiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driverIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Calculation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22))); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Distance");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Other charges");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Total");

        busyhBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        busyhBox.setText("Busy hours");
        busyhBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busyhBoxActionPerformed(evt);
            }
        });

        rdConBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdConBox.setText("Bad road condition");
        rdConBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdConBoxActionPerformed(evt);
            }
        });

        fragileBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fragileBox.setText("Fragile goods");
        fragileBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fragileBoxActionPerformed(evt);
            }
        });

        calbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        calbutton.setText("Calculate");
        calbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calbuttonActionPerformed(evt);
            }
        });

        confirmbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        confirmbtn.setText("Confirm");
        confirmbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmbtnActionPerformed(evt);
            }
        });

        totalTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTxtActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Vehicle Status");

        statuscombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empty", "Half Full", "Loaded" }));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(109, 109, 109)
                        .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(busyhBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdConBox))
                                    .addComponent(distanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statuscombo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(calbutton))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(fragileBox))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(confirmbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel36))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(distanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(busyhBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdConBox)
                                .addComponent(fragileBox)))))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(statuscombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmbtn)
                    .addComponent(calbutton))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        orderDelTable.setModel(new javax.swing.table.DefaultTableModel(
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
        orderDelTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderDelTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(orderDelTable);

        jPanel15.setBackground(new java.awt.Color(153, 153, 153));

        jLabel39.setBackground(new java.awt.Color(153, 153, 153));
        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("   Customer Delivery");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(615, 615, 615)
                    .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(425, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(187, 187, 187)
                    .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(188, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout orderDeliveryIntLayout = new javax.swing.GroupLayout(orderDeliveryInt.getContentPane());
        orderDeliveryInt.getContentPane().setLayout(orderDeliveryIntLayout);
        orderDeliveryIntLayout.setHorizontalGroup(
            orderDeliveryIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1388, Short.MAX_VALUE)
            .addGroup(orderDeliveryIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderDeliveryIntLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        orderDeliveryIntLayout.setVerticalGroup(
            orderDeliveryIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
            .addGroup(orderDeliveryIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderDeliveryIntLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        setPreferredSize(new java.awt.Dimension(1700, 1000));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Invoice Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Item ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Item Description");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Unit Discount");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Unit Price");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Quantity");

        txt_itemID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_itemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_itemIDActionPerformed(evt);
            }
        });
        txt_itemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_itemIDKeyReleased(evt);
            }
        });

        txt_qty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_qtyActionPerformed(evt);
            }
        });

        txt_unitPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_unitPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_unitPriceActionPerformed(evt);
            }
        });

        txt_unitDis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_addEntry.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_addEntry.setText("Add Entry");
        btn_addEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addEntryActionPerformed(evt);
            }
        });

        btn_removeEntry.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_removeEntry.setText("Remove Entry");
        btn_removeEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeEntryActionPerformed(evt);
            }
        });

        tbl_availableList.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_availableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_availableListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_availableList);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel17.setText("Available Items");

        txtA_itemDes.setColumns(20);
        txtA_itemDes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtA_itemDes.setRows(3);
        jScrollPane4.setViewportView(txtA_itemDes);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_orderID, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_itemID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_unitDis, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_unitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(btn_addEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btn_removeEntry)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(414, 414, 414))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_qty, txt_unitDis, txt_unitPrice});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbl_orderID, txt_itemID});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_orderID, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addComponent(jButton2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_itemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_unitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_unitDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_removeEntry)
                                    .addComponent(btn_addEntry))))))
                .addGap(39, 39, 39))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbl_orderID, txt_itemID, txt_qty, txt_unitDis, txt_unitPrice});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Customer Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Cutomer ID");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Customer Name");

        txt_cusID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cusID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cusIDKeyReleased(evt);
            }
        });

        txt_cusName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_assignCus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_assignCus.setText("<html><center> Assign  <br/>Customer</center> </html>");
        btn_assignCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_assignCusActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0)), "Delivery details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(153, 153, 0))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Delivery ID");

        txt_delID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox1.setText("Delivery");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setText("Delivery Charge");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_delID, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jCheckBox1))
                    .addComponent(jLabel12))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_delID)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(45, 45, 45)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        lbl_cusStatus.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbl_cusStatus.setForeground(new java.awt.Color(255, 0, 51));
        lbl_cusStatus.setText("Customer Details Not Added");

        lbl_cusResult.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbl_cusResult.setForeground(new java.awt.Color(0, 102, 51));

        btn_removeCus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_removeCus.setText("<html><center> Remove <br/>Customer</center> </html>");
        btn_removeCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeCusActionPerformed(evt);
            }
        });

        btn_addCus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_addCus.setText("<html><center> Add New <br/>Customer</center> </html>");
        btn_addCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addCusActionPerformed(evt);
            }
        });

        btn_addCus1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_addCus1.setText("<html><center> Search and<br/>Assign</center> </html>");
        btn_addCus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addCus1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_cusStatus)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_assignCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_removeCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_addCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_addCus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_cusResult, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_assignCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_addCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_removeCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_addCus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cusResult, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lbl_cusStatus)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_itemBought.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item No", "Item Name", "Unit", "Unit Price", "Unit discount", "Total price"
            }
        ));
        tbl_itemBought.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_itemBoughtMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_itemBought);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Payment Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel12.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Sub Total");
        jPanel12.add(jLabel20);
        jLabel20.setBounds(40, 30, 95, 25);

        txt_total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_total.setText("0.00");
        jPanel12.add(txt_total);
        txt_total.setBounds(180, 40, 120, 23);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Less Discount");
        jPanel12.add(jLabel25);
        jLabel25.setBounds(40, 80, 94, 17);

        txt_totDis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_totDis.setText("0.00");
        jPanel12.add(txt_totDis);
        txt_totDis.setBounds(180, 80, 120, 23);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Net Value");
        jPanel12.add(jLabel24);
        jLabel24.setBounds(40, 120, 67, 17);

        txt_netValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_netValue.setText("0.00");
        jPanel12.add(txt_netValue);
        txt_netValue.setBounds(180, 120, 120, 23);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Other Charges");
        jPanel12.add(jLabel29);
        jLabel29.setBounds(40, 190, 101, 17);

        txt_other.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_other.setText("0.00");
        jPanel12.add(txt_other);
        txt_other.setBounds(180, 190, 120, 23);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Total");
        jPanel12.add(jLabel13);
        jLabel13.setBounds(40, 232, 60, 30);

        lbl_total.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_total.setText("0.00");
        jPanel12.add(lbl_total);
        lbl_total.setBounds(180, 240, 150, 22);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Total to be Paid");
        jPanel12.add(jLabel8);
        jLabel8.setBounds(390, 80, 120, 16);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Now Paying");
        jPanel12.add(jLabel19);
        jLabel19.setBounds(390, 130, 110, 16);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Remaining to be paid");
        jPanel12.add(jLabel28);
        jLabel28.setBounds(390, 200, 160, 16);

        txt_remaining.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_remaining.setText("0.00");
        jPanel12.add(txt_remaining);
        txt_remaining.setBounds(570, 200, 120, 23);

        txt_totToBePaid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_totToBePaid.setText("0.00");
        jPanel12.add(txt_totToBePaid);
        txt_totToBePaid.setBounds(570, 80, 120, 23);

        txt_pre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_pre.setText("0.00");
        jPanel12.add(txt_pre);
        txt_pre.setBounds(570, 40, 120, 23);

        btn_giveLoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_giveLoan.setText("Give Loan");
        btn_giveLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_giveLoanActionPerformed(evt);
            }
        });
        jPanel12.add(btn_giveLoan);
        btn_giveLoan.setBounds(470, 240, 166, 25);

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Pay by Cash"));
        jPanel13.setLayout(null);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Amount");
        jPanel13.add(jLabel31);
        jLabel31.setBounds(30, 40, 95, 17);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Cash");
        jPanel13.add(jLabel30);
        jLabel30.setBounds(30, 80, 95, 17);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Balance");
        jPanel13.add(jLabel32);
        jLabel32.setBounds(30, 120, 95, 17);

        txt_amountCash1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel13.add(txt_amountCash1);
        txt_amountCash1.setBounds(130, 40, 100, 23);

        txt_cash1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel13.add(txt_cash1);
        txt_cash1.setBounds(130, 80, 100, 23);

        txt_balanceCash1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel13.add(txt_balanceCash1);
        txt_balanceCash1.setBounds(130, 120, 100, 23);

        btn_confirmCash1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_confirmCash1.setText("Confirm");
        btn_confirmCash1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmCashActionPerformed(evt);
            }
        });
        jPanel13.add(btn_confirmCash1);
        btn_confirmCash1.setBounds(80, 160, 100, 25);

        jPanel12.add(jPanel13);
        jPanel13.setBounds(760, 30, 280, 200);

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Pay by Cheque"));
        jPanel14.setLayout(null);

        txt_amountCheque.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel14.add(txt_amountCheque);
        txt_amountCheque.setBounds(140, 90, 100, 23);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Cheque No");
        jPanel14.add(jLabel26);
        jLabel26.setBounds(20, 40, 95, 17);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Amount");
        jPanel14.add(jLabel23);
        jLabel23.setBounds(20, 90, 95, 17);

        txt_numCheque.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel14.add(txt_numCheque);
        txt_numCheque.setBounds(140, 40, 100, 23);

        btn_confirmCheque.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_confirmCheque.setText("Confirm");
        btn_confirmCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmChequeActionPerformed(evt);
            }
        });
        jPanel14.add(btn_confirmCheque);
        btn_confirmCheque.setBounds(90, 150, 100, 25);

        jPanel12.add(jPanel14);
        jPanel14.setBounds(1100, 30, 280, 200);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("<html><center>Print<br/>Invoice</center></html>");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton6);
        jButton6.setBounds(1426, 51, 190, 150);

        lbl_confirmCheque.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbl_confirmCheque.setForeground(new java.awt.Color(0, 102, 0));
        jPanel12.add(lbl_confirmCheque);
        lbl_confirmCheque.setBounds(1170, 250, 143, 27);

        lbl_confirmCash.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbl_confirmCash.setForeground(new java.awt.Color(0, 102, 0));
        jPanel12.add(lbl_confirmCash);
        lbl_confirmCash.setBounds(830, 250, 141, 27);

        chkbx_addPastLoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkbx_addPastLoan.setText("Add Past Loans");
        chkbx_addPastLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbx_addPastLoanActionPerformed(evt);
            }
        });
        jPanel12.add(chkbx_addPastLoan);
        chkbx_addPastLoan.setBounds(390, 40, 140, 25);

        txt_nowPaying.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nowPaying.setText("0.00");
        jPanel12.add(txt_nowPaying);
        txt_nowPaying.setBounds(570, 130, 120, 23);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("<html><center>New<br/>Form</center></html>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Make Order", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1684, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Settle Debts", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1684, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Return Items", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1684, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Transaction History", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_itemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_itemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_itemIDActionPerformed

    private void txt_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qtyActionPerformed

    private void txt_unitPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_unitPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_unitPriceActionPerformed

    private void txt_itemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_itemIDKeyReleased
        
        loadAvailableItemList();
    }//GEN-LAST:event_txt_itemIDKeyReleased

    private void btn_addEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addEntryActionPerformed
        
        if(!validateItemEntry()){
            return;
        }
        

        
        String orderID = lbl_orderID.getText();
        String itemId = txt_itemID.getText();
        String itemDis = txtA_itemDes.getText();
        Float qty = parseFloat(txt_qty.getText());
        Float unitPrice = parseFloat(txt_unitPrice.getText());
        Float unitDis = parseFloat(txt_unitDis.getText());
        Float netItemPrice = qty*(unitPrice-unitDis);
        
        PreparedStatement ps=null;
        ResultSet rs = null;
        
        try{
            ps= conn.prepareStatement("INSERT INTO ItemsBought_temp VALUES (NULL,?,?,?,?,?,?,?)");
            ps.setString(1, itemId); 
            ps.setString(2, itemDis);
            ps.setString(3, qty.toString());
            ps.setString(4, unitPrice.toString());
            ps.setString(5, unitDis.toString());
            ps.setString(6, netItemPrice.toString());
            ps.setString(7, orderID);
            ps.execute();
            
            
        }catch(Exception e){
            System.out.println(e);
        }finally {
            
            
            //findTot();
            updateTot();
            updatePayDetails();
            loadItemsBoughtTable1();
            
            try{            
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }  
        
        //clearing item details
        txt_itemID.setText("");
        txtA_itemDes.setText("");
        txt_qty.setText("");
        txt_unitPrice.setText("");
        txt_unitDis.setText("");
        
       
        
    }//GEN-LAST:event_btn_addEntryActionPerformed

    private void btn_addCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addCusActionPerformed
        
        Order.callAddCusForm();
    }//GEN-LAST:event_btn_addCusActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        int x= JOptionPane.showConfirmDialog(null, "Are you sure you want to Print?");
        
        if(x==0){  
        
        
        PreparedStatement ps_insertOrder=null;
        PreparedStatement ps_ordeID=null;
        ResultSet rs_orderID=null;
        ResultSet rs_items_temp=null;
        PreparedStatement ps_insertItems_ori = null;
        PreparedStatement ps_items_temp=null;
        PreparedStatement ps_clr=null;
        PreparedStatement ps_payment = null;

        String orderID=null;
        String cusID = txt_cusID.getText();
        String grossTot=txt_total.getText();
        String totDis=txt_totDis.getText();
        String netValue=txt_netValue.getText();
        String total=lbl_total.getText();

        try{
            //inserting data to order table
            ps_insertOrder = conn.prepareStatement("INSERT INTO \"Order\" VALUES (NULL,?,?,?,?,?,datetime('now','localtime'),?,?)");
            ps_insertOrder.setString(1,cusID);
            ps_insertOrder.setString(2,this.delID);
            ps_insertOrder.setString(3,grossTot);
            ps_insertOrder.setString(4,totDis);
            ps_insertOrder.setString(5,netValue);
            ps_insertOrder.setString(6,total);
            ps_insertOrder.setString(7,netValue);
            ps_insertOrder.execute();

            //retrieving order id
            ps_ordeID = conn.prepareStatement("SELECT last_insert_rowid()");
            rs_orderID = ps_ordeID.executeQuery();
            orderID = rs_orderID.getString(1);

            //copying data from otemsbought temp to ori
            ps_items_temp = conn.prepareStatement("SELECT itemID,description,qty,unitPrice,unitDiscount,netItemPrice FROM ItemsBought_temp");
            rs_items_temp = ps_items_temp.executeQuery();

            int count=1;
            String itemNo,itemID,description,qty,unitPrice,unitDis,net;

            while(rs_items_temp.next()){

                itemNo = Integer.toString(count);
                itemID = rs_items_temp.getString("itemID");
                description = rs_items_temp.getString("description");
                qty = rs_items_temp.getString("qty");
                unitPrice = rs_items_temp.getString("unitPrice");
                unitDis = rs_items_temp.getString("unitDiscount");
                net = rs_items_temp.getString("netItemPrice");

                ps_insertItems_ori = conn.prepareStatement("INSERT INTO ItemsBought VALUES (NULL,?,?,?,?,?,?,?,?)");
                ps_insertItems_ori.setString(1, itemNo);
                ps_insertItems_ori.setString(2, itemID);
                ps_insertItems_ori.setString(3, description);
                ps_insertItems_ori.setString(4, qty);
                ps_insertItems_ori.setString(5, unitPrice);
                ps_insertItems_ori.setString(6, unitDis);
                ps_insertItems_ori.setString(7, net);
                ps_insertItems_ori.setString(8, orderID);

                ps_insertItems_ori.execute();

                count++;
            }

            //deleting items temp table
            ps_clr = conn.prepareStatement("DELETE FROM ItemsBought_temp"); 
            ps_clr.execute();
            ps_clr = conn.prepareStatement("DELETE FROM sqlite_sequence WHERE name = 'ItemsBought_temp';");
            ps_clr.execute();
            
            
            //inserting order payment details
            ps_payment = conn.prepareStatement("INSERT INTO OrderPayment VALUES (NULL,?,?,?,?,?,?, datetime('now','localtime') )");
            if(this.cashAmount != null){               
                ps_payment.setString(1,orderID);
                ps_payment.setString(2,this.cusID);
                ps_payment.setString(3,"Cash");
                ps_payment.setString(4,this.cashAmount);
                ps_payment.setString(5,null);
                ps_payment.setString(6,null);   
                ps_payment.execute();
            }
            
            if(this.chequeAmount != null){
                ps_payment.setString(1,orderID);
                ps_payment.setString(2,this.cusID);
                ps_payment.setString(3,"Cheque");
                ps_payment.setString(4,this.chequeAmount);
                ps_payment.setString(5,this.chequeNum);
                ps_payment.setString(6,"Initial");   
                ps_payment.execute();
            }
            
            if(this.loanAmount != null){
                ps_payment.setString(1,orderID);
                ps_payment.setString(2,this.cusID);
                ps_payment.setString(3,"Credit");
                ps_payment.setString(4,this.loanAmount);
                ps_payment.setString(5,null);
                ps_payment.setString(6,null);   
                ps_payment.execute();
            }
            
            

        }catch(Exception e){
            System.out.println(e);
        }finally {
            try{
                if (ps_insertOrder != null) {ps_insertOrder.close();}
                if (ps_ordeID != null) {ps_ordeID.close();}
                if (rs_orderID != null) {rs_orderID.close();}
                if (rs_items_temp != null) {rs_items_temp.close();}
                if (ps_insertItems_ori != null) {ps_insertItems_ori.close();}
                if (ps_items_temp != null) {ps_items_temp.close();}
                if (ps_clr != null) {ps_clr.close();}

                conn.setAutoCommit(true);

            }catch(Exception e){
                System.out.println(e);
            }
        }

        //invoke a function to clr the form
       Order.reload();
       
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_confirmChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmChequeActionPerformed

        lbl_confirmCheque.setText("Paid by Cheque!");
        btn_confirmCheque.setEnabled(false);
        
        
        //Float remaining = Float.parseFloat(txt_remaining.getText()) - Float.parseFloat(txt_amountCheque.getText()) ;
        //txt_remaining.setText(remaining.toString());
        
        //Float nowPaying = Float.parseFloat(txt_nowPaying.getText())+ Float.parseFloat(txt_amountCash1.getText());
        //txt_nowPaying.setText(nowPaying.toString());
        
        this.chequeAmount = txt_amountCheque.getText();
        this.chequeNum = txt_numCheque.getText();
        
        updatePayDetails();
        
        txt_numCheque.setEditable(false);
        txt_amountCheque.setEditable(false);
        
    }//GEN-LAST:event_btn_confirmChequeActionPerformed

    private void btn_confirmCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmCashActionPerformed

        //setting values for the paying panel
        //Float remaining = Float.parseFloat(txt_remaining.getText()) - Float.parseFloat(txt_amountCash1.getText()) ;
        Float balanceCash = Float.parseFloat(txt_cash1.getText()) - Float.parseFloat(txt_amountCash1.getText());
        //txt_remaining.setText(remaining.toString());
        txt_balanceCash1.setText(balanceCash.toString());
        lbl_confirmCash.setText("Paid by Cash!");
        
        
        
        ///set value for Now Paying
        /*Float nowPaying = Float.parseFloat(txt_nowPaying.getText())+ Float.parseFloat(txt_amountCash1.getText());
        txt_nowPaying.setText(nowPaying.toString());*/
        
        this.cashAmount=txt_amountCash1.getText();
        updatePayDetails();
        
        //locking txtfields
        
        txt_amountCash1.setEditable(false);
        txt_cash1.setEditable(false);
        txt_balanceCash1.setEditable(false);
        
        
        btn_confirmCash1.setEnabled(false);
        
    }//GEN-LAST:event_btn_confirmCashActionPerformed

    private void btn_assignCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_assignCusActionPerformed
        lbl_cusStatus.setText("Customer Details Added");
        lbl_cusResult.setText("");
        this.cusID=txt_cusID.getText();
         
        chkbx_addPastLoan.setEnabled(true);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pastLoan_S="0";
        
        try{
           ps=conn.prepareStatement("select totalOutstanding from customer where cusID like '"+this.cusID +"'");
           rs=ps.executeQuery();
           rs.next();
           pastLoan_S = rs.getString("totalOutstanding");
           txt_pre.setText(pastLoan_S);
           this.pastLoan=Float.parseFloat(pastLoan_S);
            
        }catch(Exception e){
            System.out.println(e);
            
        }finally {
            
            try{            
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
        txt_cusID.setEditable(false);
        txt_cusName.setEditable(false);
        
        
        ////////////////////////
        //btn_confirmCash1.setEnabled(true);
        btn_confirmCheque.setEnabled(true);
        btn_giveLoan.setEnabled(true);
        
        
        //////////////////
        btn_removeCus.setEnabled(true);
        btn_addCus.setEnabled(false);
        btn_assignCus.setEnabled(false);

        
       
    }//GEN-LAST:event_btn_assignCusActionPerformed

    private void btn_removeEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeEntryActionPerformed

        if(this.itemNo==null){
            JOptionPane.showMessageDialog(null,"Please select an entry first","Invalid selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        
        /////////////////////////////
         String itemNo = this.itemNo;
         PreparedStatement ps = null;
         ResultSet rs =null;
            
            try{
             
                ps =conn.prepareStatement("DELETE FROM ItemsBought_temp where itemNo=?");
                ps.setString(1,itemNo);                
                ps.execute();
                
                ps=conn.prepareStatement("SELECT itemNo,itemID,description,qty,unitPrice,unitDiscount,netItemPrice FROM ItemsBought_temp");
                rs=ps.executeQuery();
            
            
        }catch(Exception e){
            System.out.println(e);
        }finally {
            
            updateTot();
            updatePayDetails();
            loadItemsBoughtTable1();
            
            
            try{            
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }  
        
        //clearing item details
        txt_itemID.setText("");
        txtA_itemDes.setText("");
        txt_qty.setText("");
        txt_unitPrice.setText("");
        txt_unitDis.setText("");
       ////////////////////////////////
       this.itemNo =null;
       
    }//GEN-LAST:event_btn_removeEntryActionPerformed

    private void txt_cusIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cusIDKeyReleased
        
        String key = txt_cusID.getText();
        PreparedStatement ps=null;
        ResultSet rs =null;
        int count =0;
        
        try{
           
                
                ps = conn.prepareStatement("SELECT * FROM Customer WHERE cusID ='"+key+"'");
                rs = ps.executeQuery();
                
                /*
                while(rs.next()){
                    count++;
                    System.out.println(count);
                }
                */
                //rs = ps.executeQuery();
                if(rs.next()){
                    
                        txt_cusName.setText(rs.getString("name"));
                        lbl_cusResult.setText("Results Found!");
                        btn_assignCus.setEnabled(true);
                 
                }else if(key.length()>9){
                
                        txt_cusName.setText("");
                        lbl_cusResult.setText("No Results Found!");
                       // btn_removeCus.doClick();
                        btn_assignCus.setEnabled(false);
                }else{
                        txt_cusName.setText("");
                        lbl_cusResult.setText("");
                       // btn_removeCus.doClick();
                        btn_assignCus.setEnabled(false);
                    
                }
                
        }catch(Exception e){
            
            System.out.println(e);
            System.out.println("Landed to catch block");
        }finally{
            try{            
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }
                
        
    }//GEN-LAST:event_txt_cusIDKeyReleased

    private void btn_removeCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeCusActionPerformed
        txt_cusName.setText("");
        txt_cusID.setText("");
        txt_cusName.setEditable(true);
        txt_cusID.setEditable(true);
        
        lbl_cusStatus.setText("Customer Details Not Added");
        
        if(chkbx_addPastLoan.isSelected()){
            chkbx_addPastLoan.doClick();
        }
        
        txt_pre.setText("");
        this.cusID=null;
        this.pastLoan=0f;
        
        
        chkbx_addPastLoan.setEnabled(false);        
        
        
        //////////////////////////////////////
        btn_assignCus.setEnabled(false);
        btn_addCus.setEnabled(true);
        btn_removeCus.setEnabled(false);
        
    }//GEN-LAST:event_btn_removeCusActionPerformed

    private void chkbx_addPastLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbx_addPastLoanActionPerformed
        
        if(chkbx_addPastLoan.isSelected()){               
        
            Float totToPay = Float.parseFloat(txt_pre.getText()) + Float.parseFloat(txt_totToBePaid.getText());
            Float remainToPay = Float.parseFloat(txt_pre.getText()) + Float.parseFloat(txt_remaining.getText());

            txt_totToBePaid.setText(totToPay.toString());
            txt_remaining.setText(remainToPay.toString());
            updatePayByBoxes();
        }else{
            Float totToPay = Float.parseFloat(txt_totToBePaid.getText()) - Float.parseFloat(txt_pre.getText());
            Float remainToPay = Float.parseFloat(txt_remaining.getText()) -  Float.parseFloat(txt_pre.getText());

            txt_totToBePaid.setText(totToPay.toString());
            txt_remaining.setText(remainToPay.toString());
            updatePayByBoxes();
        }
    }//GEN-LAST:event_chkbx_addPastLoanActionPerformed

    private void btn_giveLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_giveLoanActionPerformed
       
        this.loanAmount= txt_remaining.getText();
        
        txt_remaining.setText(txt_remaining.getText() + " - LOAN"); 
        txt_remaining.setForeground(Color.red);
        
        
        btn_confirmCash1.setEnabled(false);
        btn_confirmCheque.setEnabled(false);
        
        
        btn_giveLoan.setEnabled(false);
        
        txt_amountCash1.setEditable(false);
        txt_cash1.setEditable(false);
        txt_balanceCash1.setEditable(false);

        txt_numCheque.setEditable(false);
        txt_amountCheque.setEditable(false);
        
        if(this.chequeAmount==null){
            txt_amountCheque.setText("");
        }
        if(this.cashAmount==null){
            txt_amountCash1.setText("");
        }
        
        
    }//GEN-LAST:event_btn_giveLoanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        int x= JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the form?");
        
        if(x==0){  
            PreparedStatement ps_clr = null;
            
            try{
                ps_clr = conn.prepareStatement("DELETE FROM ItemsBought_temp"); 
                ps_clr.execute();
                ps_clr = conn.prepareStatement("DELETE FROM sqlite_sequence WHERE name = 'ItemsBought_temp';");
                ps_clr.execute();
                
            }catch(Exception e){
                System.out.println(e);
            }finally{
                //deleting items temp table
                Order.reload();                
                try{
                
                    if (ps_clr != null) {ps_clr.close();}
                    conn.setAutoCommit(true);

                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
            
        
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_itemBoughtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_itemBoughtMouseClicked
        int r = tbl_itemBought.getSelectedRow();

        String itemNum = tbl_itemBought.getValueAt(r, 0).toString();
        this.itemNo=itemNum;
        
        txt_itemID.setText(tbl_itemBought.getValueAt(r, 1).toString());
        txtA_itemDes.setText(tbl_itemBought.getValueAt(r, 2).toString());
        txt_qty.setText(tbl_itemBought.getValueAt(r, 3).toString());
        txt_unitPrice.setText(tbl_itemBought.getValueAt(r, 4).toString());
        txt_unitDis.setText(tbl_itemBought.getValueAt(r, 5).toString());
        

        
        txt_itemID.setEditable(false);
        txtA_itemDes.setEditable(false);
        txt_qty.setEditable(false);
        txt_unitPrice.setEditable(false);
        txt_unitDis.setEditable(false);
        
        
    }//GEN-LAST:event_tbl_itemBoughtMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        //clearing item details
        txt_itemID.setText("");
        txtA_itemDes.setText("");
        txt_qty.setText("");
        txt_unitPrice.setText("");
        txt_unitDis.setText("");
        
        txt_itemID.setEditable(true);
        txtA_itemDes.setEditable(true);
        txt_qty.setEditable(true);
        txt_unitPrice.setEditable(true);
        txt_unitDis.setEditable(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void addressTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTxtActionPerformed
        try {
            // TODO add your handling code here:
            String address = addressTxt.getText();

            jTextArea1.setText(getMap(address));
            distanceTxt.setText(""+getDistance());
        } catch (IOException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addressTxtActionPerformed

    private void addressTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressTxtKeyPressed
        PreparedStatement stm=null;
        ResultSet rs = null;
        
        try {
            // TODO add your handling code here:
            String address = addressTxt.getText();
            String SQL = "SELECT * FROM OrderDelivery WHERE address like'%"+address+"'";
            stm = conn.prepareStatement(SQL);
            rs = stm.executeQuery(SQL);
            orderDelTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }finally {
            
            try{            
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_addressTxtKeyPressed

    private void busyhBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busyhBoxActionPerformed
        // TODO add your handling code here:
        if(busyhBox.isSelected()){
            rate1 =+ 0.05;
        }
        else if(!busyhBox.isSelected()){
            rate1 = 0;
        }
    }//GEN-LAST:event_busyhBoxActionPerformed

    private void rdConBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdConBoxActionPerformed
        // TODO add your handling code here:
        if(rdConBox.isSelected()){
            rate2 =+ 0.05;
        }
        else if(!rdConBox.isSelected()){
            rate2 = 0;
        }
    }//GEN-LAST:event_rdConBoxActionPerformed

    private void fragileBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fragileBoxActionPerformed
        // TODO add your handling code here:
        if(fragileBox.isSelected()){
            rate3 =+ 0.05;
        }
        else if(!fragileBox.isSelected()){
            rate3 = 0;
        }
    }//GEN-LAST:event_fragileBoxActionPerformed

    private void calbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calbuttonActionPerformed
        //Calculate delivery charges
        //        OrderDelivery orderDelivery = new OrderDelivery();
        showCheckBoxes();
        double price = calculateTotal(Double.parseDouble(distanceTxt.getText()));
        totalTxt.setText(""+price);
        totalTxt.setEditable(false);
    }//GEN-LAST:event_calbuttonActionPerformed

    private void confirmbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmbtnActionPerformed
        try {
            showCheckBoxes();

            int res= addDelivery();
            if(res>0){
                JOptionPane.showMessageDialog(this, "Added Successfully");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        loadOrderDeliverTable();
        
        this.delID = delivIdTxt.getText();
        txt_delID.setText(this.delID);
        clear();
    }//GEN-LAST:event_confirmbtnActionPerformed

    private void totalTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTxtActionPerformed
        // TODO add your handling code here:
        totalTxt.setEditable(false);
    }//GEN-LAST:event_totalTxtActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        orderDeliveryInt.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void orderDelTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderDelTableMouseClicked
        // TODO add your handling code here:
        int row = orderDelTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)orderDelTable.getModel();

        String vehicle = orderDelTable.getValueAt(row, 4).toString();
        Object date = orderDelTable.getValueAt(row, 3);
        String driv = orderDelTable.getValueAt(row, 5).toString();

        vehiTxt.setSelectedItem(vehicle);
       // ((JTextField)delivDate.getDateEditor().getUiComponent()).setText(model.getValueAt(row,3).toString()); --by bhanuka
        driverIdTxt.setSelectedItem(driv);
    }//GEN-LAST:event_orderDelTableMouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
            orderDeliveryInt.setVisible(true);
        }
        else{
            orderDeliveryInt.setVisible(false);
            this.delCharge=0f;
            this.delID=null;
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btn_addCus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addCus1ActionPerformed
        Order.callAddCusSearchForm();
    }//GEN-LAST:event_btn_addCus1ActionPerformed

    private void tbl_availableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_availableListMouseClicked
        int r = tbl_availableList.getSelectedRow();

        String itemID = tbl_availableList.getValueAt(r, 0).toString();
        String description = tbl_availableList.getValueAt(r, 1).toString();
        
        String category=getItemCategory(itemID);
        System.out.println(category+" catergry found in tableclick event");
        String itemName =null;        
        
        if(category.equals("Paint&Thinner")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r,3).toString();
        }else if(category.equals("Construction")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString()+" "+ tbl_availableList.getValueAt(r, 4).toString();
        }else if(category.equals("Roofing&Fitting")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 4).toString()+" "+ tbl_availableList.getValueAt(r, 5).toString();
        }else if(category.equals("Waterpipe&Fittings")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString();
        }else if(category.equals("Chemical&Farming")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString();
        }else if(category.equals("Other")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString()+" "+ tbl_availableList.getValueAt(r, 4).toString();
        }

        txt_itemID.setText(itemID);
        txtA_itemDes.setText(itemName);
            
        
    }//GEN-LAST:event_tbl_availableListMouseClicked


    public void loadOrderDeliverTable() {
    
        validateDeliveID();
        try {
            Statement stm=conn.createStatement();
            String SQL="SELECT * FROM OrderDelivery";
            ResultSet rs=stm.executeQuery(SQL);
             
            orderDelTable.setModel(DbUtils.resultSetToTableModel(rs));
             
        } catch (SQLException ex) {
           // Logger.getLogger(ordertempjFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
         }
    }   
    
    private double calculateTotal(double dist){
        //calculate delivery chages
        OrderDelivery orderDelivery = new OrderDelivery();
        double tot = 0;
        double temp =0;
        
        if (dist < 1){
            return 0;            
        }
        else{
            tot = dist*orderDelivery.getUnitprice();
            tot = tot * (1+rate1 + rate2 + rate3);
        }        
        return tot;
    }
    
    private void showCheckBoxes(){        
        //identify the checked and unchecked check boxes
        busyhBox.addItemListener(new ItemListener() {
        @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==1){
                    busy = true;
                }
                else{
                    busy = false;
                }                
            }
        });
        rdConBox.addItemListener(new ItemListener(){
        @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==1){
                    road = true;
                }
                else{
                    road = false;
                }
            }
        });
        fragileBox.addItemListener(new ItemListener(){
        @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==1){
                    fr = true;
                }
                else{
                    fr = false;
                }
            }
        });
    }
    
    private int addDelivery() throws SQLException{
        
        String did = delivIdTxt.getText();
        String oid = null;
        String addrs = addressTxt.getText();
        //String ddate = (((JTextField)delivDate.getDateEditor().getUiComponent()).getText());       --by bhanuka
        String ddate =null;  // --by bhanuka
        String vehino = vehiTxt.getSelectedItem().toString();
        String driver = driverIdTxt.getSelectedItem().toString();
        String dis = distanceTxt.getText();
        String status = statuscombo.getSelectedItem().toString();
        Float tot = Float.parseFloat(totalTxt.getText());
        
        
        String SQL="INSERT INTO OrderDelivery VALUES ('"+did+"','"+oid+"','"+addrs+"', '"+ddate+"','"+vehino+"','"+driver+"','"+dis+"','"+busy+"','"+road+"','"+fr+"','"+status+"','"+tot+"')";
        Statement stm=conn.createStatement(); 
        int res = stm.executeUpdate(SQL);
        
        this.delCharge=tot;
        
        return res;
    }
    
    public void clear(){
        delivIdTxt.setText(null);
        addressTxt.setText(null);
        //delivDate.setDate(null);  --by bhanuka
        vehiTxt.setSelectedItem(null);
        driverIdTxt.setSelectedItem(null);
        distanceTxt.setText(null);
        busyhBox.setEnabled(false);
        rdConBox.setEnabled(false);
        fragileBox.setEnabled(false);
        totalTxt.setText(null);
        statuscombo.setSelectedItem(null);   
    }

    public void loadVehicles(){
    
        try {
            String SQL = "Select vehiNo from Vehicle";
            Statement stm = conn.createStatement();
            ResultSet rs=stm.executeQuery(SQL);
            while(rs.next()){
                //add(rs.getString("id"));
                vehiTxt.addItem(rs.getString("vehiNo"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public void loadDrivers(){
    
        try {
            String SQL= "SELECT Name from Driver";
            Statement stm = conn.createStatement();
            ResultSet rs=stm.executeQuery(SQL);
            while(rs.next()){
                driverIdTxt.addItem(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    
    }
    
    public String getMap(String destination){
        try {
            //String origin= "Shashi+Enterprises,no+12,Nuwara+Eliya+Road,Kappetipola";
            String origin = "SLIIT,Kandy+rd,Malabe";
            destination = destination.replace(' ', '+');
            HttpURLConnection connection = null;
            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+origin+"&destinations="+destination+"&key="+API_KEY);
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            return response.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public double getDistance() throws FileNotFoundException, IOException{
        
        double dis;
        int count =-1;
        char[] arr = new char[100];
        for (String line : jTextArea1.getText().split("\\n")){
            count++;
            //System.out.println(line);
            if(count == 8){
                int len = line.length();
                line.getChars(28, len-6, arr, 0);
                dis = Double.parseDouble(String.valueOf(arr));       
                
                return dis;
            }
            
        }      
            
        return -1;
    }
    
    private void validateDeliveID(){
    
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(delivId) AS itemid FROM OrderDelivery");
            String itemid;
            while(rs.next()){
                itemid=rs.getString("itemid");
                String ino[]=itemid.split("OD");
                int no=Integer.parseInt(ino[1]);
                no=no+1;
                delivIdTxt.setText("OD"+no);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTxt;
    private javax.swing.JButton btn_addCus;
    private javax.swing.JButton btn_addCus1;
    private javax.swing.JButton btn_addEntry;
    protected javax.swing.JButton btn_assignCus;
    private javax.swing.JButton btn_confirmCash1;
    private javax.swing.JButton btn_confirmCheque;
    private javax.swing.JButton btn_giveLoan;
    private javax.swing.JButton btn_removeCus;
    private javax.swing.JButton btn_removeEntry;
    private javax.swing.JCheckBox busyhBox;
    private javax.swing.JButton calbutton;
    private javax.swing.JCheckBox chkbx_addPastLoan;
    private javax.swing.JButton confirmbtn;
    private javax.swing.JTextField delivIdTxt;
    private javax.swing.JTextField distanceTxt;
    private javax.swing.JComboBox driverIdTxt;
    private javax.swing.JCheckBox fragileBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    protected javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_confirmCash;
    private javax.swing.JLabel lbl_confirmCheque;
    private javax.swing.JLabel lbl_cusResult;
    private javax.swing.JLabel lbl_cusStatus;
    private javax.swing.JLabel lbl_orderID;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JTable orderDelTable;
    private javax.swing.JDialog orderDeliveryInt;
    private javax.swing.JCheckBox rdConBox;
    private javax.swing.JComboBox statuscombo;
    private javax.swing.JTable tbl_availableList;
    private javax.swing.JTable tbl_itemBought;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JTextArea txtA_itemDes;
    private javax.swing.JTextField txt_amountCash1;
    private javax.swing.JTextField txt_amountCheque;
    private javax.swing.JTextField txt_balanceCash1;
    private javax.swing.JTextField txt_cash1;
    protected javax.swing.JTextField txt_cusID;
    private javax.swing.JTextField txt_cusName;
    private javax.swing.JTextField txt_delID;
    private javax.swing.JTextField txt_itemID;
    private javax.swing.JTextField txt_netValue;
    private javax.swing.JTextField txt_nowPaying;
    private javax.swing.JTextField txt_numCheque;
    private javax.swing.JTextField txt_other;
    private javax.swing.JTextField txt_pre;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_remaining;
    private javax.swing.JTextField txt_totDis;
    private javax.swing.JTextField txt_totToBePaid;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_unitDis;
    private javax.swing.JTextField txt_unitPrice;
    private javax.swing.JComboBox vehiTxt;
    // End of variables declaration//GEN-END:variables
}
