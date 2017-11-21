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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

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
        
        txt_delID.setEnabled(false);
        Date date = new Date();
        delivDate.setSelectableDateRange(date, null);
        
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
        totalTxt.setEditable(false);
        loadDrivers();
        loadVehicles();
        loadOrderDeliverTable();
        validateOrderDeliveID();
        setDelivID();
        loadAvailableDrivers();
        loadAvailableVehicles();
        loadSupplierDelivTable();
        loadODeliveryTable();
        loadAgencyDelivTable();
        
        txt_chequeNo_sd.setEnabled(false);
        //txt_cusID_rt.setBackground(Color.);
        //txt_cusID_rt.setOpaque(true);
        
        loadCusTransactionsTable("");
        loadInvoiceTable("");
        loadHistoryTable();
     
    }

    void updateItemQty(String qty,String operator,String itemID){
                
        PreparedStatement ps =null;
        String q="update "+ getItemCategory(itemID)+" set qty=qty"+operator +qty+ " where itemID='"+itemID+"'";

        
        try{
            ps=conn.prepareStatement(q);
            ps.execute();
            
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            loadAvailableItemList();
            try{            
                if (ps != null) {
                    ps.close();
                }                              
                conn.setAutoCommit(true);                
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public String getNumDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
            String numberDate = sdf.format(date);
            return numberDate;
        }catch(Exception e){
            System.out.println(e + " E");
            return "E";
           // e.printStackTrace();
        }
        
        //return "E";
        
    }
    
    void loadHistoryTable(){
        PreparedStatement ps =null;
        ResultSet rs = null;
    
        try{
            ps=conn.prepareStatement("SELECT o.orderID,o.cusID,c.name,o.dateAndTime,netvalue FROM \"Order\" o LEFT JOIN customer c ON o.cusID = c.cusID");
            rs=ps.executeQuery();
            tbl_history.setModel(DbUtils.resultSetToTableModel(rs));

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
    
    Boolean validateAmount(String x){
        if(x.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill the amount","Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
                
        for(int i=0; i<x.length(); i++){
            
            if(!Character.isDigit(x.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter a valid amount. It should contain only numbers.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
                
        return true;
    }
    Boolean validateInvoice(String x){
        if(x.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill the Invoice number","Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
                
        for(int i=0; i<x.length(); i++){
            
            if(!Character.isDigit(x.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter a valid invoice number. It should contain only numbers.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
                
        return true;
    }
    
    String getCusName(String cusID_){
        PreparedStatement ps=null;
        ResultSet rs =null;
        String name=null;
        
        try{
            ps=conn.prepareStatement("select name from customer where cusID='"+cusID_+"'");
            rs=ps.executeQuery();
            name=rs.getString("name");  
            return name;
            
        }catch(Exception e){
            System.out.println(e);
            return null;
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
            
            //return name;
        }
    }
    
    Boolean validateChequeNo(String x){
        if(x.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill the cheque number","Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
                
        for(int i=0; i<x.length(); i++){
            
            if(!Character.isDigit(x.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter a valid cheque Number. Only the numbers are allowed with 6 numbers.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        if(x.length()!=6){
                JOptionPane.showMessageDialog(null,"Please Enter a valid cheque Number. Only the numbers are allowed with 6 numbers.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        
        return true;
        
    }
    
    void loadInvoiceTable(String orderID){
        
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        try{
            ps=conn.prepareStatement("select ItemNo as Item_No,ItemID as Item_ID,Description,qty,unitPrice,unitDiscount,netItemPrice from ItemsBought where orderID='"+orderID+"'" );
            rs=ps.executeQuery();
            tbl_invoice.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{            
                if (ps != null) 
                { 
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
    
    void calculateDebt(String cusID){
        PreparedStatement ps = null;
        ResultSet rs=null;
        try{
//            ps = conn.prepareStatement("select((select sum(amount) from OrderPayment where type='Credit' and cusID=?) + " +
//                                              "(select sum(amount) from OrderPayment where type='Cheque' and status ='Unsuccessful' and cusID=?) - " +
//                                              "(select sum(amount) from OrderPayment where orderID='Settle Debt' and  (status != 'Unsuccessful' or status is null) and cusID=?)) as loan");
//            ps.setString(1, cusID);
//            ps.setString(2, cusID);
//            ps.setString(3, cusID);
            ps = conn.prepareStatement("select totalOutstanding from customer where cusID=?");
            ps.setString(1, cusID);
            
            rs=ps.executeQuery();
            String calculatedLoan = rs.getString("totalOutstanding");
            if(calculatedLoan==null)
                calculatedLoan="0";
            System.out.println("Loan is " + calculatedLoan);
            txt_totDebt.setText(calculatedLoan);
            
            
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
    }
    void loadCusTransactionsTable(String cusID){
        
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        try{
            ps=conn.prepareStatement("select orderID as Order_ID,Amount,Type,status,dateAndTime from OrderPayment where cusID='"+cusID+"'" );
            rs=ps.executeQuery();
            tbl_cusTransactions.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{            
                if (ps != null) 
                { 
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
            
            if((!Character.isDigit(qty.charAt(i)))&&!(qty.charAt(i)=='.')){
                JOptionPane.showMessageDialog(null,"Please Enter only numbers for the quantity.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        for(int i=0; i<unit.length(); i++){
            
            if((!Character.isDigit(unit.charAt(i)))&&!(unit.charAt(i)=='.')){
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
            
            if(category.equals("Paint_Thinner")){
                q = "SELECT ItemID,Catergory,BrandName,ColourCode,Qty,SellingPrice FROM Paint_Thinner WHERE ItemID LIKE '%"+itemID+"%'";                
            }else if(category.equals("Construction")){
                q = "SELECT ItemID,ItemName,BrandName,size,length,Qty,SellingPrice FROM Construction WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Roofing_Fitting")){
                q = "SELECT ItemID, Catergary,Type,BrandName,size,Colour, Qty,SellingPrice FROM Roofing_Fitting WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("WaterPipe_Fitting")){
                q = "SELECT ItemID , ItemName,BrandName,Size,Qty,SellingPrice FROM WaterPipe_Fitting WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Chemical_Farming")){
                q = "SELECT ItemID,ItemName,BrandName,Duration,Qty,SellingPrice FROM Chemical_Farming WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("OtherItem")){
                q = "SELECT ItemID,ItemName,BrandName,Colour,Size,Qty,SellingPrice FROM OtherItem WHERE ItemID LIKE '%"+itemID+"%'";
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
            String unitPrice = null;
            if(count==1){
                if(category.equals("Paint&Thinner")){
                    itemName= rs_itemList.getString("Catergory")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("ColourCode");
                    unitPrice=rs_itemList.getString("SellingPrice");
                }else if(category.equals("Construction")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("size")+" "+ rs_itemList.getString("length");
                    unitPrice=rs_itemList.getString("SellingPrice");
                }else if(category.equals("Roofing_Fitting")){
                    itemName= rs_itemList.getString("Catergary")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("Type")+" "+ rs_itemList.getString("size")+" "+ rs_itemList.getString("Colour");
                    unitPrice=rs_itemList.getString("SellingPrice");
                }else if(category.equals("WaterPipe_Fitting")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("Size");
                    unitPrice=rs_itemList.getString("SellingPrice");
                }else if(category.equals("Chemical_Farming")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName");
                    unitPrice=rs_itemList.getString("SellingPrice");
                }else if(category.equals("OtherItem")){
                    itemName= rs_itemList.getString("ItemName")+" "+ rs_itemList.getString("BrandName")+" "+ rs_itemList.getString("Colour")+" "+ rs_itemList.getString("Size");
                    unitPrice=rs_itemList.getString("SellingPrice");
                }
                
                txtA_itemDes.setText(itemName);
                txt_unitPrice.setText(unitPrice);
            }else
                txtA_itemDes.setText("");
                txt_unitPrice.setText("");
            
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
        jPanel27 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        delivIdTxt = new javax.swing.JTextField();
        delivDate = new com.toedter.calendar.JDateChooser();
        addressTxt = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        rdConBox = new javax.swing.JCheckBox();
        fragileBox = new javax.swing.JCheckBox();
        calbutton = new javax.swing.JButton();
        confirmbtn = new javax.swing.JButton();
        distanceTxt = new javax.swing.JTextField();
        totalTxt = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        orderDelTable = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jDialog1 = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_ItemList = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
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
        btn_searchAndAssign = new javax.swing.JButton();
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
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        delivIdtxt = new javax.swing.JTextField();
        idtxt = new javax.swing.JTextField();
        addrstxt = new javax.swing.JTextField();
        vehiCombo = new javax.swing.JComboBox();
        driverCombo = new javax.swing.JComboBox();
        cancelBtn = new javax.swing.JButton();
        confirmBtn = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        typeCombo = new javax.swing.JComboBox();
        postpnBtn = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        allVehiCombo = new javax.swing.JComboBox();
        avaVehiCombo = new javax.swing.JComboBox();
        allDriverCombo = new javax.swing.JComboBox();
        avaDriverCombo = new javax.swing.JComboBox();
        driverUpdateBtn = new javax.swing.JButton();
        vehiUpdateBtn = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        supTable = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        delivTable = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        agencyTable = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        txt_cusID_sd = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txt_chequeNo_sd = new javax.swing.JTextField();
        txt_amount_sd = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        cmbx_payType = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_cusTransactions = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        txt_totDebt = new javax.swing.JTextField();
        lbl_currentTrans = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_invoice = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtA_itemDes_rt = new javax.swing.JTextField();
        txt_btQty_rt = new javax.swing.JTextField();
        txt_netUnitPrice_rt = new javax.swing.JTextField();
        txt_returnDes_rt = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btn_calculate = new javax.swing.JButton();
        cmbx_type = new javax.swing.JComboBox<>();
        btn_return = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txt_returnQty_rt = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txt_returnPrice_rt = new javax.swing.JTextField();
        btn_clr_rt = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_invoiceID_rt = new javax.swing.JTextField();
        txt_issuedDate_rt = new javax.swing.JTextField();
        txt_cusName_rt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_cusID_rt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txt_OrderID_history = new javax.swing.JTextField();
        txt_cusID_history = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_history = new javax.swing.JTable();

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

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Schedule", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Deliver ID");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Address");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Date");

        delivDate.setDateFormatString("dd-MM-yyyy");

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

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4))
                .addGap(41, 41, 41)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(delivDate, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(delivIdTxt)
                    .addComponent(addressTxt))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(delivIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(delivDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Calculation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22))); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Distance");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Other charges");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Total");

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
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(distanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(rdConBox)
                                .addGap(33, 33, 33)
                                .addComponent(fragileBox)))))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(calbutton)
                .addGap(55, 55, 55)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(confirmbtn))
                .addGap(31, 31, 31))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(distanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdConBox)
                        .addComponent(fragileBox))
                    .addComponent(jLabel36))
                .addGap(42, 42, 42)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmbtn)
                    .addComponent(calbutton))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(85, Short.MAX_VALUE))
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
                        .addGap(20, 20, 20)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(401, Short.MAX_VALUE))
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
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGap(0, 1391, Short.MAX_VALUE)
            .addGroup(orderDeliveryIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderDeliveryIntLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        orderDeliveryIntLayout.setVerticalGroup(
            orderDeliveryIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
            .addGroup(orderDeliveryIntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderDeliveryIntLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jDialog1.setSize(new java.awt.Dimension(995, 335));

        tbl_ItemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item No", "Item Code", "Item Name", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tbl_ItemList);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Order ID:");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        txt_unitPrice.setEditable(false);
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

        txt_delID.setEditable(false);
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

        btn_searchAndAssign.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_searchAndAssign.setText("<html><center> Search and<br/>Assign</center> </html>");
        btn_searchAndAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchAndAssignActionPerformed(evt);
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_cusName, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txt_cusID))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_assignCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_removeCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_addCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_searchAndAssign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(btn_searchAndAssign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        jLabel28.setText("Balance to be paid");
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

        txt_balanceCash1.setEditable(false);
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
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Make Order", jPanel5);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Schedule", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Delivery ID");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("ID");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Address");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Vehicle");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("Driver");

        cancelBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        confirmBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        confirmBtn.setText("Confirm");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Type");

        typeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Order", "Supplier", "Agency" }));

        postpnBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        postpnBtn.setText("Postpone");
        postpnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postpnBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(postpnBtn)
                        .addGap(18, 18, 18)
                        .addComponent(confirmBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBtn))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(delivIdtxt)
                            .addComponent(idtxt)
                            .addComponent(addrstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59)
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(vehiCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(driverCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typeCombo, 0, 158, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(driverCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                            .addComponent(vehiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(52, 52, 52)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addrstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)
                            .addComponent(typeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(delivIdtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(idtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel60))
                    .addComponent(jLabel59)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel57)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel58)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(confirmBtn)
                    .addComponent(postpnBtn))
                .addContainerGap())
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Availability", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("Vehicle");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("Availability");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Driver");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Availability");

        avaVehiCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Unavailable" }));

        avaDriverCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Unavailable" }));

        driverUpdateBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        driverUpdateBtn.setText("Update");
        driverUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driverUpdateBtnActionPerformed(evt);
            }
        });

        vehiUpdateBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vehiUpdateBtn.setText("Update");
        vehiUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiUpdateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(allVehiCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avaVehiCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allDriverCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avaDriverCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(driverUpdateBtn)
                    .addComponent(vehiUpdateBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(allVehiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(avaVehiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehiUpdateBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(allDriverCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(avaDriverCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driverUpdateBtn))
                .addGap(28, 28, 28))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
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
        itemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemTableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(itemTable);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        supTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        supTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supTableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(supTable);

        delivTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(delivTable);

        agencyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        agencyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agencyTableMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(agencyTable);

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(orderTable);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 1276, Short.MAX_VALUE)
                    .addComponent(jScrollPane12)
                    .addComponent(jScrollPane11)
                    .addComponent(jScrollPane14))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(355, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Deliveries", jPanel22);

        jPanel6.setLayout(null);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Customer Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel16.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Customer ID");
        jPanel16.add(jLabel1);
        jLabel1.setBounds(60, 30, 100, 16);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton4);
        jButton4.setBounds(600, 30, 100, 40);

        txt_cusID_sd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cusID_sd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cusID_sdKeyReleased(evt);
            }
        });
        jPanel16.add(txt_cusID_sd);
        txt_cusID_sd.setBounds(240, 30, 190, 30);

        jPanel6.add(jPanel16);
        jPanel16.setBounds(20, 30, 1600, 90);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Now Paying", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel19.setLayout(null);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Payment Method");
        jPanel19.add(jLabel45);
        jLabel45.setBounds(80, 60, 140, 16);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Cheque Number");
        jPanel19.add(jLabel46);
        jLabel46.setBounds(80, 130, 120, 16);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Amount");
        jPanel19.add(jLabel47);
        jLabel47.setBounds(670, 70, 100, 16);
        jPanel19.add(txt_chequeNo_sd);
        txt_chequeNo_sd.setBounds(290, 120, 190, 30);
        jPanel19.add(txt_amount_sd);
        txt_amount_sd.setBounds(810, 60, 190, 30);

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText("Pay Debt");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton8);
        jButton8.setBounds(1350, 40, 150, 100);

        cmbx_payType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Cheque" }));
        cmbx_payType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_payTypeActionPerformed(evt);
            }
        });
        jPanel19.add(cmbx_payType);
        cmbx_payType.setBounds(290, 60, 190, 22);

        jPanel6.add(jPanel19);
        jPanel19.setBounds(20, 540, 1600, 190);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Customer's Transactions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel20.setLayout(null);

        tbl_cusTransactions.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tbl_cusTransactions);

        jPanel20.add(jScrollPane7);
        jScrollPane7.setBounds(60, 90, 1470, 210);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Total Debts");
        jPanel20.add(jLabel48);
        jLabel48.setBounds(60, 340, 100, 16);

        txt_totDebt.setEditable(false);
        jPanel20.add(txt_totDebt);
        txt_totDebt.setBounds(260, 330, 190, 30);

        lbl_currentTrans.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_currentTrans.setText("No customer is selected");
        jPanel20.add(lbl_currentTrans);
        lbl_currentTrans.setBounds(60, 40, 440, 20);

        jPanel6.add(jPanel20);
        jPanel20.setBounds(20, 150, 1600, 370);

        jTabbedPane1.addTab("Settle Debts", jPanel6);

        jPanel7.setLayout(null);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Item Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel17.setLayout(null);

        tbl_invoice.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_invoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_invoiceMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_invoice);

        jPanel17.add(jScrollPane6);
        jScrollPane6.setBounds(40, 60, 1440, 230);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Item Description");
        jPanel17.add(jLabel27);
        jLabel27.setBounds(107, 346, 115, 17);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Bought Quantity");
        jPanel17.add(jLabel40);
        jLabel40.setBounds(600, 346, 124, 17);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Return Description");
        jPanel17.add(jLabel41);
        jLabel41.setBounds(107, 410, 139, 17);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Net Unit Price");
        jPanel17.add(jLabel42);
        jLabel42.setBounds(600, 412, 112, 17);

        txtA_itemDes_rt.setEditable(false);
        jPanel17.add(txtA_itemDes_rt);
        txtA_itemDes_rt.setBounds(266, 344, 185, 22);

        txt_btQty_rt.setEditable(false);
        jPanel17.add(txt_btQty_rt);
        txt_btQty_rt.setBounds(742, 344, 185, 22);

        txt_netUnitPrice_rt.setEditable(false);
        jPanel17.add(txt_netUnitPrice_rt);
        txt_netUnitPrice_rt.setBounds(742, 410, 185, 22);
        jPanel17.add(txt_returnDes_rt);
        txt_returnDes_rt.setBounds(266, 410, 185, 22);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Return Type");
        jPanel17.add(jLabel43);
        jLabel43.setBounds(107, 487, 99, 17);

        btn_calculate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_calculate.setText("Calculate Net Return Price");
        btn_calculate.setActionCommand("<html><center>Calculate<br/>Net Return Price</center></html>");
        btn_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculateActionPerformed(evt);
            }
        });
        jPanel17.add(btn_calculate);
        btn_calculate.setBounds(923, 485, 221, 33);

        cmbx_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Faulty Goods", "Non-Faluty Goods" }));
        jPanel17.add(cmbx_type);
        cmbx_type.setBounds(266, 485, 185, 22);

        btn_return.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_return.setText("Return");
        btn_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_returnActionPerformed(evt);
            }
        });
        jPanel17.add(btn_return);
        btn_return.setBounds(1199, 485, 221, 33);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Return Quantity");
        jPanel17.add(jLabel44);
        jLabel44.setBounds(1090, 352, 112, 17);
        jPanel17.add(txt_returnQty_rt);
        txt_returnQty_rt.setBounds(1235, 350, 185, 22);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("Pay Customer");
        jPanel17.add(jLabel49);
        jLabel49.setBounds(1090, 418, 112, 17);

        txt_returnPrice_rt.setEditable(false);
        jPanel17.add(txt_returnPrice_rt);
        txt_returnPrice_rt.setBounds(1235, 416, 185, 22);

        btn_clr_rt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_clr_rt.setText("Clear");
        btn_clr_rt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clr_rtActionPerformed(evt);
            }
        });
        jPanel17.add(btn_clr_rt);
        btn_clr_rt.setBounds(1451, 344, 67, 33);

        jPanel7.add(jPanel17);
        jPanel17.setBounds(50, 250, 1570, 540);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 255)), "Invoice Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Invoice ID");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Customer ID");

        txt_issuedDate_rt.setEditable(false);

        txt_cusName_rt.setEditable(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Issued Date & Time");

        txt_cusID_rt.setEditable(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Customer Name");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cusID_rt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_invoiceID_rt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_issuedDate_rt)
                    .addComponent(txt_cusName_rt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_invoiceID_rt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_issuedDate_rt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txt_cusName_rt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(txt_cusID_rt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel7.add(jPanel18);
        jPanel18.setBounds(50, 40, 1570, 190);

        jTabbedPane1.addTab("Return Items", jPanel7);

        jPanel8.setLayout(null);

        jPanel21.setBackground(new java.awt.Color(204, 204, 204));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Search by OrderID:");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("Serach by CustomerID:");

        txt_OrderID_history.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_OrderID_history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_OrderID_historyActionPerformed(evt);
            }
        });
        txt_OrderID_history.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_OrderID_historyKeyReleased(evt);
            }
        });

        txt_cusID_history.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cusID_history.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cusID_historyKeyReleased(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton10.setText("Refresh");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel53)
                .addGap(49, 49, 49)
                .addComponent(txt_OrderID_history, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227)
                .addComponent(jLabel54)
                .addGap(21, 21, 21)
                .addComponent(txt_cusID_history, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(109, 109, 109))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_OrderID_history, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_cusID_history, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel53)
                                .addComponent(jLabel54)))))
                .addGap(49, 49, 49))
        );

        jPanel8.add(jPanel21);
        jPanel21.setBounds(50, 20, 1590, 100);

        tbl_history.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "OrderID", "CustomerID", "Customer Name", "Placed Date", "Total Amount"
            }
        ));
        tbl_history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_historyMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_history);

        jPanel8.add(jScrollPane8);
        jScrollPane8.setBounds(50, 140, 1590, 590);

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
            updateItemQty(qty.toString(),"-",itemId);
            
            
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
        
        Order.callCusAddCusForm();
    }//GEN-LAST:event_btn_addCusActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

         int x= JOptionPane.showConfirmDialog(null, "Are you sure you want to Print?");
        
        if(x==0){
            
             
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        try{
            ps=conn.prepareStatement("select * from ItemsBought_temp");
            rs=ps.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null,"Please Add items before click on print","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
            
                return;
            }   
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
            
            
        PreparedStatement ps_insertOrder=null;
        PreparedStatement ps_ordeID=null;
        ResultSet rs_orderID=null;
        ResultSet rs_items_temp=null;
        PreparedStatement ps_insertItems_ori = null;
        PreparedStatement ps_items_temp=null;
        PreparedStatement ps_clr=null;
        PreparedStatement ps_payment = null;
        PreparedStatement cus_update = null;

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
            
            updateODelivTable(orderID);
            
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
            //setting loan value
            
            
            if(Float.parseFloat(txt_nowPaying.getText()) <= Float.parseFloat(lbl_total.getText())){ //when customer is paying less or equal than he bought - getting loan
                ps_payment.setString(1,orderID);
                ps_payment.setString(2,this.cusID);
                ps_payment.setString(3,"Credit");
                ps_payment.setString(4,this.loanAmount);
                ps_payment.setString(5,null);
                ps_payment.setString(6,null);   
                ps_payment.execute();
                
                //increment cus loan by loanAmount and cusVists++
                cus_update=conn.prepareStatement("UPDATE Customer SET totalOutstanding=totalOutstanding+?,noOfVisits=noOfVisits+1 where cusID=?  ");
                if(this.loanAmount==null)
                    this.loanAmount="0";
                cus_update.setString(1,this.loanAmount);
                cus_update.setString(2,this.cusID);
                cus_update.execute();
                
            }else{                      //when customer is paying more - paying debt and cusVisits++
                String payLoan =null;
                Float payLoanF=0f;
                payLoanF = pastLoan - Float.parseFloat(this.loanAmount);
                payLoan = payLoanF.toString();
                
                ps_payment.setString(1,"Settle Debt");
                ps_payment.setString(2,this.cusID);
                ps_payment.setString(3,"Order ID - "+orderID);
                ps_payment.setString(4,payLoan);
                ps_payment.setString(5,null);
                ps_payment.setString(6,null);   
                ps_payment.execute();
                
                //update cus loan by loanAmount
                cus_update=conn.prepareStatement("UPDATE Customer SET totalOutstanding=?,noOfVisits=noOfVisits+1 where cusID=?");
                if(this.loanAmount==null)
                    this.loanAmount="0";
                cus_update.setString(1,this.loanAmount);
                cus_update.setString(2,this.cusID);
                cus_update.execute();
            }
            
             String report = ".\\Invoice.jrxml";
                        JasperDesign jd = JRXmlLoader.load(report);
                        String sql1 = "SELECT itemNo, itemID,description,qty,unitPrice,unitDiscount,netItemPrice from ItemsBought where orderID='" + orderID+ "'";
                        JRDesignQuery n = new JRDesignQuery();
                        n.setText(sql1);
                        jd.setQuery(n);
                        JasperReport jr = JasperCompileManager.compileReport(jd);
                        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                        JasperViewer.viewReport(jp, false);

            
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

        if(!validateChequeNo(txt_numCheque.getText())){
            txt_cash1.setText("");   
            return;
        }
        
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

        if(Float.parseFloat(txt_amountCash1.getText())> Float.parseFloat(txt_cash1.getText())){
            JOptionPane.showMessageDialog(null, "The amount to be paid in 'Cash' should be greater than or equal to 'Amount' ");
            txt_cash1.setText("");
            return;
        }
        
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
        txt_delID.setEnabled(true);
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
        btn_searchAndAssign.setEnabled(false);

        
       
    }//GEN-LAST:event_btn_assignCusActionPerformed

    private void btn_removeEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeEntryActionPerformed

        if(this.itemNo==null){
            JOptionPane.showMessageDialog(null,"Please select an entry first","Invalid selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int r = tbl_itemBought.getSelectedRow();
        String qty = tbl_itemBought.getValueAt(r, 3).toString();
        String itemID2 = tbl_itemBought.getValueAt(r, 1).toString();
        
        /////////////////////////////
         String itemNo = this.itemNo;
         PreparedStatement ps = null;
        ResultSet rs =null;
            
            try{
             
                ps =conn.prepareStatement("DELETE FROM ItemsBought_temp where itemNo=?");
                ps.setString(1,itemNo);                
                ps.execute();
                
                //updating inventory
                updateItemQty(qty,"+",itemID2);
                
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
        btn_searchAndAssign.setEnabled(true);
        
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
        
        //CHECKING WHETHER PAYING MORE
        if((Float.parseFloat(txt_nowPaying.getText()) <= Float.parseFloat(lbl_total.getText())) &&(chkbx_addPastLoan.isSelected())){
            JOptionPane.showMessageDialog(null,"Customer is are not paying more. Please remove the tick of an Past Loans first","Warning", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        
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
            PreparedStatement ps=null;
            ResultSet rs =null;
            
            String qty = null;
            String itemID2 =null;
            
            try{
                
                //updating the inventory
                ps=conn.prepareStatement("select * from ItemsBought_temp");
                rs=ps.executeQuery();
                while(rs.next()){
                    qty=rs.getString("qty");
                    itemID2=rs.getString("itemID");
                    updateItemQty(qty,"+",itemID2);
                    
                }
//                String qty = tbl_itemBought.getValueAt(r, 3).toString();
//                String itemID2 = tbl_itemBought.getValueAt(r, 1).toString();

                ///////////////////////////
            
                
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
        showCheckBoxes();
        double price = calculateTotal(Double.parseDouble(distanceTxt.getText()));
        totalTxt.setText(""+price);
        totalTxt.setEditable(false);
        
    }//GEN-LAST:event_calbuttonActionPerformed

    private void confirmbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmbtnActionPerformed
        
        if(totalTxt.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Empty fields are not allowed", "Empty Fields", JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.delID = delivIdTxt.getText();

                showCheckBoxes();

                int res= addDelivery();
                if(res>0){
                    JOptionPane.showMessageDialog(this, "Added Successfully");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Added Unsuccessfully");
                }

            loadOrderDeliverTable();
        
        
//        this.delID = delivIdTxt.getText();
        txt_delID.setText(this.delID);
        clear();
        }
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

//        vehiTxt.setSelectedItem(vehicle);
//       // ((JTextField)delivDate.getDateEditor().getUiComponent()).setText(model.getValueAt(row,3).toString()); --by bhanuka
//        driverIdTxt.setSelectedItem(driv);
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

    private void btn_searchAndAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchAndAssignActionPerformed
        Order.callCusSearchForm();
    }//GEN-LAST:event_btn_searchAndAssignActionPerformed

    private void tbl_availableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_availableListMouseClicked
        int r = tbl_availableList.getSelectedRow();

        String itemID = tbl_availableList.getValueAt(r, 0).toString();
        String description = tbl_availableList.getValueAt(r, 1).toString();
        
        String category=getItemCategory(itemID);
        System.out.println(category+" catergry found in tableclick event");
        String itemName =null;    
        String unitPrice=null;
        
        if(category.equals("Paint_Thinner")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r,3).toString();
            unitPrice=tbl_availableList.getValueAt(r, 5).toString();
        }else if(category.equals("Construction")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString()+" "+ tbl_availableList.getValueAt(r, 4).toString();
            unitPrice=tbl_availableList.getValueAt(r, 6).toString();
        }else if(category.equals("Roofing_Fitting")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 4).toString()+" "+ tbl_availableList.getValueAt(r, 5).toString();
            unitPrice=tbl_availableList.getValueAt(r, 7).toString();
        }else if(category.equals("Waterpipe_Fitting")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString();
            unitPrice=tbl_availableList.getValueAt(r, 5).toString();
        }else if(category.equals("Chemical_Farming")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString();
            unitPrice=tbl_availableList.getValueAt(r, 5).toString();
        }else if(category.equals("OtherItem")){
            itemName= tbl_availableList.getValueAt(r, 1).toString()+" "+ tbl_availableList.getValueAt(r, 2).toString()+" "+ tbl_availableList.getValueAt(r, 3).toString()+" "+ tbl_availableList.getValueAt(r, 4).toString();
            unitPrice=tbl_availableList.getValueAt(r, 6).toString();
        }

        txt_itemID.setText(itemID);
        txtA_itemDes.setText(itemName);
        txt_unitPrice.setText(unitPrice);
            
        
    }//GEN-LAST:event_tbl_availableListMouseClicked

    private void txt_cusID_sdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cusID_sdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cusID_sdKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String id =txt_cusID_sd.getText();
        
        //validating for empty cusID
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please type the customer ID before you click on Search","No input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        //validating cusID
        
        for(int i=0; i<id.length(); i++){
            
            if(!Character.isDigit(id.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter a valid Customer ID. Only the numbers are allowed with 10 numbers.","Invalid ID", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        if(id.length()!=10){
                JOptionPane.showMessageDialog(null,"Please Enter a valid Customer ID. Only the numbers are allowed with 10 numbers.","Invalid ID", JOptionPane.INFORMATION_MESSAGE);
                return;
        }

        //String cusID_sd = txt_cusID_sd.getText();
        loadCusTransactionsTable(id);
        
        //calculating pending loans of the customer
        calculateDebt(id);
            
        String cusName =getCusName(id);
        if(cusName==null){
            lbl_currentTrans.setText("Name not found!");
            JOptionPane.showMessageDialog(null,"Sorry! Name not found in the Database","Wrong input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            lbl_currentTrans.setText("Transaction information of Mr./Mrs. "+cusName);
        }
        
            
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        if(!validateAmount(txt_amount_sd.getText())){
            return;
        }
        if(cmbx_payType.getSelectedItem().toString().equals("Cheque")){
            if(!validateChequeNo(txt_chequeNo_sd.getText())){
                return;
            }
        }
        
        
        String cusID_sd = txt_cusID_sd.getText();       
        
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        
        try{
            ps = conn.prepareStatement("INSERT INTO OrderPayment VALUES (NULL,?,?,?,?,?,?, datetime('now','localtime') )");       
            ps.setString(1,"Settle Debt");
            ps.setString(2,cusID_sd);
            ps.setString(3,cmbx_payType.getSelectedItem().toString());
            ps.setString(4,txt_amount_sd.getText());
            if(txt_chequeNo_sd.isEnabled()){
                ps.setString(5,txt_chequeNo_sd.getText());
                ps.setString(6,"Initial"); 
            }else{
                ps.setString(5,null);
                ps.setString(6,null); 
            }            
            ps.execute();
            
            ps2 = conn.prepareStatement("UPDATE Customer SET totalOutstanding=totalOutstanding-? where cusID=?");       
            ps2.setString(1,txt_amount_sd.getText());
            ps2.setString(2,cusID_sd);
            ps2.execute();
            
        }catch(Exception e){
            System.out.println(e);
        }finally {  
            loadCusTransactionsTable(cusID_sd);                                                    
            calculateDebt(cusID_sd);                        //calculating pending loans of the customer
            try{            
                if (ps != null) {
                    ps.close();
                }
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
            
            txt_chequeNo_sd.setText("");
            txt_amount_sd.setText("");
            
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cmbx_payTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_payTypeActionPerformed
        if(cmbx_payType.getSelectedItem().toString().equals("Cheque")){
            txt_chequeNo_sd.setText("");
            txt_chequeNo_sd.setEnabled(true);
        }else{
            txt_chequeNo_sd.setText("");
            txt_chequeNo_sd.setEnabled(false);
        }
    }//GEN-LAST:event_cmbx_payTypeActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!validateInvoice(txt_invoiceID_rt.getText())){
            return;
        }

        loadInvoiceTable(txt_invoiceID_rt.getText());
        
        PreparedStatement ps=null;
        ResultSet rs = null;
        
        try{
            ps=conn.prepareStatement("select o.cusID,c.Name,o.dateAndTime from customer c ,\"order\" o where c.cusID=o.cusID and o.orderID='"+txt_invoiceID_rt.getText()+"'");
            rs=ps.executeQuery();
            
            txt_cusID_rt.setText(rs.getString("cusID"));
            txt_cusName_rt.setText(rs.getString("Name"));
            txt_issuedDate_rt.setText(rs.getString("dateAndTime"));        
            
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
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbl_invoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_invoiceMouseClicked
        int r = tbl_invoice.getSelectedRow();
        
        txtA_itemDes_rt.setText(tbl_invoice.getValueAt(r, 2).toString());
        txt_btQty_rt.setText(tbl_invoice.getValueAt(r, 3).toString());        
        
        Float unit = Float.parseFloat(tbl_invoice.getValueAt(r, 4).toString());
        Float unitDis = Float.parseFloat(tbl_invoice.getValueAt(r, 5).toString());
        Float netUnit = unit- unitDis;
        txt_netUnitPrice_rt.setText(netUnit.toString());
        
        txt_returnDes_rt.setText("");
        txt_returnQty_rt.setText("");
        txt_returnPrice_rt.setText("");
     
    }//GEN-LAST:event_tbl_invoiceMouseClicked

    private void btn_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculateActionPerformed
        int r=-1;
        r = tbl_invoice.getSelectedRow();
        
        if(r==-1){
            //select a item first
            return;
        }
        String returnQ=txt_returnQty_rt.getText();
        
        if(returnQ.isEmpty()){
            //type return quntity first
           
            JOptionPane.showMessageDialog(null,"Please Enter the 'Return Quantity' first.","Null Input", JOptionPane.INFORMATION_MESSAGE);
            return ;
            
        }
        //validating for number constraint
        for(int i=0; i<returnQ.length(); i++){
            
            if(!Character.isDigit(returnQ.charAt(i))){
                JOptionPane.showMessageDialog(null,"Please Enter a valid return quantity. It should contain only numbers.","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                return ;
            }
        }

        Float netUnit = Float.parseFloat(txt_netUnitPrice_rt.getText());
        Float returnPrice = netUnit* Float.parseFloat(txt_returnQty_rt.getText());
        txt_returnPrice_rt.setText(returnPrice.toString());
    }//GEN-LAST:event_btn_calculateActionPerformed

    private void btn_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_returnActionPerformed
        
        if(txt_returnPrice_rt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please calculate the return price first","Invalid Input", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }   
        
        int r = tbl_invoice.getSelectedRow();        
        String itemNo_ = tbl_invoice.getValueAt(r, 0).toString();
        String itemID = tbl_invoice.getValueAt(r, 1).toString();
        String des = tbl_invoice.getValueAt(r, 2).toString();
        String qty = txt_returnQty_rt.getText();
        String unitPrice = tbl_invoice.getValueAt(r, 4).toString();
        String unitDis = tbl_invoice.getValueAt(r, 5).toString();
        String netItemPrice = txt_returnPrice_rt.getText();
        String OrderId = txt_invoiceID_rt.getText();
        Float netUnitPrice = Float.parseFloat(unitPrice)-Float.parseFloat(unitDis);
         
        PreparedStatement ps =null;
        PreparedStatement ps2 =null;
        
        try{
            ps=conn.prepareStatement("insert into ItemsBought (ItemNo,ItemID,Description,qty,unitPrice,unitDiscount,netItemPrice,orderID) values(?,?,?,?,?,?,?,?)" );
            ps.setString(1, itemNo_ + " **Returned**");
            ps.setString(2, itemID);
            ps.setString(3, des);
            ps.setString(4, qty);
            ps.setString(5, unitPrice);
            ps.setString(6, unitDis);
            ps.setString(7, netItemPrice);
            ps.setString(8, OrderId);
            ps.execute();
            loadInvoiceTable(txt_invoiceID_rt.getText());
            
            if(cmbx_type.getSelectedItem().toString().equals("Faulty Goods")){
                ps2=conn.prepareStatement("insert into returnItem_Faulty values(null,?,?,?,?,?,?,?,?)");
            }else{
                ps2=conn.prepareStatement("insert into \"returnItem_Non-Faulty\" values(null,?,?,?,?,?,?,?,?)");
            }
            ps2.setString(1, OrderId);
            ps2.setString(2, itemID);
            ps2.setString(3, des);
            ps2.setString(4, qty);
            ps2.setString(5, netUnitPrice.toString());
            ps2.setString(6, netItemPrice);
            ps2.setString(7, txt_returnDes_rt.getText());
            ps2.setString(8, txt_cusID_rt.getText());
            ps2.execute();
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{            
                if (ps != null) 
                { 
                   ps.close();
                }                
                if (ps2 != null) 
                { 
                   ps2.close();
                }
                
                conn.setAutoCommit(true);
            }catch(Exception e){
                System.out.println(e);
            }
            
            btn_clr_rt.doClick();
        }
    }//GEN-LAST:event_btn_returnActionPerformed

    private void btn_clr_rtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clr_rtActionPerformed
        txt_returnDes_rt.setText("");
        txt_returnQty_rt.setText("");
        txt_returnPrice_rt.setText("");
        txtA_itemDes_rt.setText("");
        txt_btQty_rt.setText("");
        txt_netUnitPrice_rt.setText("");
    }//GEN-LAST:event_btn_clr_rtActionPerformed

    private void txt_OrderID_historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_OrderID_historyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OrderID_historyActionPerformed

    private void tbl_historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_historyMouseClicked
        // TODO add your handling code here:
        
        int r = tbl_history.getSelectedRow();
        String OrderID = tbl_history.getValueAt(r, 0).toString();
        
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        try{
            ps=conn.prepareStatement("select itemNo,itemID,description,qty,netItemprice from ItemsBought where orderid='"+OrderID+"'" );
            rs=ps.executeQuery();
            tbl_ItemList.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{            
                if (ps != null) 
                { 
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
        jDialog1.setVisible(true);
        
    }//GEN-LAST:event_tbl_historyMouseClicked

    private void txt_OrderID_historyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OrderID_historyKeyReleased
        
        txt_cusID_history.setText("");
        
        String orderID = txt_OrderID_history.getText();
        PreparedStatement ps =null;
        ResultSet rs = null;
    
        try{
            ps=conn.prepareStatement("SELECT o.orderID,o.cusID,c.name,o.dateAndTime,netvalue FROM \"Order\" o LEFT JOIN customer c ON o.cusID = c.cusID where o.orderID like '%"+orderID+"%'");
            rs=ps.executeQuery();
            tbl_history.setModel(DbUtils.resultSetToTableModel(rs));

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
        
        if(orderID.isEmpty())
            loadHistoryTable();
    
    }//GEN-LAST:event_txt_OrderID_historyKeyReleased

    private void txt_cusID_historyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cusID_historyKeyReleased
        txt_OrderID_history.setText("");
        
        String cusID = txt_cusID_history.getText();
        PreparedStatement ps =null;
        ResultSet rs = null;
    
        try{
            ps=conn.prepareStatement("SELECT o.orderID,o.cusID,c.name,o.dateAndTime,netvalue FROM \"Order\" o LEFT JOIN customer c ON o.cusID = c.cusID where o.cusID like '%"+cusID+"%'");
            rs=ps.executeQuery();
            tbl_history.setModel(DbUtils.resultSetToTableModel(rs));

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
        
        if(cusID.isEmpty())
            loadHistoryTable();
    }//GEN-LAST:event_txt_cusID_historyKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        txt_OrderID_history.setText("");
        txt_cusID_history.setText("");
        loadHistoryTable();
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        clearScheDeliv();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
            
        updateDrivStatus();
        updateVehiStatus();

        int res= addScheduledDelivery();
        if(res>0){
            JOptionPane.showMessageDialog(this, "Insert Successful");
        }
        else{
            JOptionPane.showMessageDialog(this, "Insert Unsuccessful");
        }

        loadSchduledDelivery();
        clearScheDeliv();

    }//GEN-LAST:event_confirmBtnActionPerformed

    private void postpnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postpnBtnActionPerformed
        
        Statement stm1=null;
        String did = idtxt.getText();
        try {
            String SQL1 = "INSERT INTO PostponedDelivery Values (null,'"+did+"')";
            stm1 = conn.createStatement();
            stm1.executeUpdate(SQL1);
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm1 != null){
                try {
                    stm1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        char cmp = did.charAt(0);
        if(cmp == 'O'){
            int row = orderTable.getSelectedRow();
            orderTable.clearSelection();
        }
        else if(cmp== 'S'){
            int row = supTable.getSelectedRow();
            supTable.clearSelection();
        }
        else if(cmp== 'A'){
            int row = agencyTable.getSelectedRow();
            agencyTable.clearSelection();
        }
    }//GEN-LAST:event_postpnBtnActionPerformed

    private void driverUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driverUpdateBtnActionPerformed

        try {
            String dName = allDriverCombo.getSelectedItem().toString();
            String status = avaDriverCombo.getSelectedItem().toString();

            String SQL="UPDATE Driver SET availability = '"+status+"' WHERE Name = '"+dName+"'";
            Statement stm=conn.createStatement();
            stm.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_driverUpdateBtnActionPerformed

    private void vehiUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiUpdateBtnActionPerformed
        Statement stm=null; 
        try {
            String vehiNum = allVehiCombo.getSelectedItem().toString();
            String status = avaVehiCombo.getSelectedItem().toString();

            String SQL="UPDATE Vehicle SET availability = '"+status+"' WHERE vehiNo = '"+vehiNum+"'";
            stm=conn.createStatement();
            stm.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_vehiUpdateBtnActionPerformed

    private void itemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTableMouseClicked

    }//GEN-LAST:event_itemTableMouseClicked

    private void supTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supTableMouseClicked
        Statement stm=null;
        ResultSet rs=null;
        try {
            clearItemTable();
            
            orderTable.clearSelection();
            agencyTable.clearSelection();
            int row = supTable.getSelectedRow();
            
            String sdId = supTable.getValueAt(row,0).toString();
            String adrs = supTable.getValueAt(row,4).toString();
            
            idtxt.setText(sdId);
            addrstxt.setText(adrs);
            
            String oId = supTable.getValueAt(row, 1).toString();
            
            String SQL = "SELECT ItemName FROM SupplierOrderItems WHERE OrderID = '"+oId+"' ";
            stm = conn.createStatement();
            rs = stm.executeQuery(SQL);
            
            while(rs.next()){
                itemTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_supTableMouseClicked

    private void agencyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agencyTableMouseClicked
        Statement stm=null;
        ResultSet rs=null;
        try {
            clearItemTable();
            
            supTable.clearSelection();
            orderTable.clearSelection();

            int row = agencyTable.getSelectedRow();

            String odId = agencyTable.getValueAt(row,0).toString();
            //String adrs = agencyTable.getValueAt(row,2).toString();

            idtxt.setText(odId);
            //addrstxt.setText(adrs);

            String invoiceID = agencyTable.getValueAt(row, 1).toString();
            String adrs = getStoreAddress(invoiceID);
            addrstxt.setText(adrs);

            String SQL = "SELECT itemcode, description, quantity FROM sales WHERE invoiceno = '"+invoiceID+"'";
            stm = conn.createStatement();
            rs = stm.executeQuery(SQL);

            while(rs.next()){
                itemTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_agencyTableMouseClicked

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked
        Statement stm=null;
        ResultSet rs=null;
        try {
            clearItemTable();
            
            supTable.clearSelection();
            agencyTable.clearSelection();

            int row = orderTable.getSelectedRow();

            String odId = orderTable.getValueAt(row,0).toString();
            String adrs = orderTable.getValueAt(row,2).toString();

            idtxt.setText(odId);
            addrstxt.setText(adrs);

            String orderId = orderTable.getValueAt(row, 1).toString();

            String SQL = "SELECT itemID, description, qty FROM ItemsBought WHERE orderID = '"+orderId+"'";
            stm = conn.createStatement();
            rs = stm.executeQuery(SQL);

            while(rs.next()){
                itemTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_orderTableMouseClicked

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


    public void loadOrderDeliverTable() {
        
        Statement stm=null;
        ResultSet rs=null;
        validateDeliveID();
        try {
            stm=conn.createStatement();
            String SQL="SELECT * FROM OrderDelivery";
            rs=stm.executeQuery(SQL);
             
            orderTable.setModel(DbUtils.resultSetToTableModel(rs));
             
        } catch (SQLException ex) {
           // Logger.getLogger(ordertempjFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
//        busyhBox.addItemListener(new ItemListener() {
//        @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange()==1){
//                    busy = true;
//                }
//                else{
//                    busy = false;
//                }                
//            }
//        });
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
    
    private int addDelivery() {
        Statement stm=null;
//        ResultSet rs=null;
        int res = 0;
        try {
            String did = delivIdTxt.getText();
            String oid = null;
            String addrs = addressTxt.getText();
            String ddate = (((JTextField)delivDate.getDateEditor().getUiComponent()).getText());   
            String dis = distanceTxt.getText();
            Float tot = Float.parseFloat(totalTxt.getText());


            String SQL="INSERT INTO OrderDelivery VALUES ('"+did+"','"+oid+"','"+addrs+"', '"+ddate+"','"+dis+"','"+road+"','"+fr+"','"+tot+"')";
            stm=conn.createStatement();
            res = stm.executeUpdate(SQL);

            this.delCharge=tot;

            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
    
    public void clear(){
        delivIdTxt.setText(null);
        addressTxt.setText(null);
        //delivDate.setDate(null);  --by bhanuka
//        vehiTxt.setSelectedItem(null);
//        driverIdTxt.setSelectedItem(null);
        distanceTxt.setText(null);
//        busyhBox.setEnabled(false);
        rdConBox.setEnabled(false);
        fragileBox.setEnabled(false);
        totalTxt.setText(null);
//        statuscombo.setSelectedItem(null);   
    }

    public void loadVehicles(){
        Statement stm=null;
        ResultSet rs=null;
        try {
            String SQL = "Select vehiNo from Vehicle";
            stm = conn.createStatement();
            rs=stm.executeQuery(SQL);
            while(rs.next()){
                //add(rs.getString("id"));
                allVehiCombo.addItem(rs.getString("vehiNo"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void loadDrivers(){
        Statement stm=null;
        ResultSet rs=null;
        try {
            String SQL= "SELECT Name from Driver";
            stm = conn.createStatement();
            rs=stm.executeQuery(SQL);
            while(rs.next()){
                allDriverCombo.addItem(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public String getMap(String destination){
        try {
            String origin= "No+12,Nuwara+Eliya+Road,Keppetipola";
            //String origin = "SLIIT,Kandy+rd,Malabe";
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
            ResultSet rs = s.executeQuery("SELECT MAX(orderDelivId) AS itemid FROM OrderDelivery");
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

    public void loadODeliveryTable(){
        
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date sysDate = new Date();
        String date = dateformat.format(sysDate);
        Statement stm=null;
        ResultSet rs=null;
        try {
            stm=conn.createStatement();
            String SQL="SELECT * FROM OrderDelivery WHERE delivDate = '"+date+"'";
            rs=stm.executeQuery(SQL);
            
            orderTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    public void loadSupplierDelivTable(){
        
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date sysDate = new Date();
        String date = dateformat.format(sysDate);
        Statement stm=null;
        ResultSet rs=null;
        try {
            stm=conn.createStatement();
            String SQL="SELECT * FROM SupplierDelivery WHERE DelivDate = '"+date+"'";
            rs=stm.executeQuery(SQL);
            
            supTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void validateOrderDeliveID(){
  
        PreparedStatement pst =null;
        ResultSet rs=null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sql = "SELECT orderDelivId as odid FROM OrderDelivery";
            
            pst=conn.prepareStatement(sql);
            
            rs = pst.executeQuery();
            while(rs.next())
            {
                list.add(rs.getString("odid"));
            }
            
            
            String a = list.get(list.size()-1);
           
            String ino[]=a.split("OD");
            
                
            int no=Integer.parseInt(ino[1]);
            no=no+1;
            delivIdTxt.setText("OD"+no);
    
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                pst.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }

        
    }
    
    public void loadAvailableDrivers(){
        Statement stm=null;
        ResultSet rs=null;
        try {
            //String date = delivDate.getDateFormatString();
            
            String SQL= "SELECT Name FROM Driver WHERE availability = 'Available'";
            stm = conn.createStatement();
            rs=stm.executeQuery(SQL);
            while(rs.next()){
                driverCombo.addItem(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    public void loadAvailableVehicles(){
        
        Statement stm=null;
        ResultSet rs=null;
        try {
            //String date = delivDate.getDateFormatString();
            
            String SQL= "SELECT vehiNo FROM Vehicle WHERE availability = 'Available'";
            stm = conn.createStatement();
            rs=stm.executeQuery(SQL);
            while(rs.next()){
                vehiCombo.addItem(rs.getString("vehiNo"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(OrderDeliveryInt.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    private void setDelivID(){
    
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(delivId) AS itemid FROM ScheduledDelivery");
            //int itemid;
            String itemid;
            while(rs.next()){
                itemid=rs.getString("itemid");
                System.out.println("itemid "+itemid);
//                String ino[]=itemid.split("OD");
//                int no=Integer.parseInt(ino[1]);
                int no = Integer.parseInt(itemid);
//                no=no+1;
                no = no + 1;
                delivIdtxt.setText(""+no);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void loadSchduledDelivery(){
        Statement stm=null;
        ResultSet rs=null;
        try {
            stm=conn.createStatement();
            String SQL="SELECT * FROM ScheduledDelivery";
            rs=stm.executeQuery(SQL);
            
            delivTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    public void clearScheDeliv(){
        setDelivID();
        idtxt.setText("");
        addrstxt.setText("");
        vehiCombo.setSelectedItem("");
        driverCombo.setSelectedItem("");
    }
    
    private int addScheduledDelivery(){
        Statement stm=null;
        int res = 0;
        try {
            String did = delivIdtxt.getText();
            String type = typeCombo.getSelectedItem().toString();
            String tid = idtxt.getText();
            String vehi = vehiCombo.getSelectedItem().toString();
            String drvr = driverCombo.getSelectedItem().toString();
            String adrs = addrstxt.getText();
            
            DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
            Date sysDate = new Date();
            String date = dateformat.format(sysDate);
            
            String SQL = "INSERT INTO ScheduledDelivery VALUES ('"+did+"','"+type+"', '"+tid+"', '"+vehi+"', '"+drvr+"', '"+date+"', '"+adrs+"')";
            stm = conn.createStatement();
            res = stm.executeUpdate(SQL);
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
    
    private void updateVehiStatus(){
        Statement stm=null;
        try {
            String vNo = vehiCombo.getSelectedItem().toString();
            
            String SQL = "UPDATE Vehicle SET availability = 'Unavailable' WHERE vehiNo = '"+vNo+"'";
            stm = conn.createStatement();
            stm.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void updateDrivStatus(){
        Statement stm=null;
        try {
            String dname = driverCombo.getSelectedItem().toString();
            
            String SQL = "UPDATE Driver SET availability = 'Unavailable' WHERE Name = '"+dname+"'";
            stm = conn.createStatement();
            stm.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private String getStoreAddress(String inID){
        String ad = "";
        Statement stm=null;
        ResultSet rs=null;
        try {            
            String SQL = "SELECT address FROM store WHERE id = (SELECT id FROM invoiceSummary WHERE invoiceno = '"+inID+"')";
            stm = conn.createStatement();
            rs = stm.executeQuery(SQL);
            
            while(rs.next()){
                ad = rs.getString("address");
            }
            
//            return ad;
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ad;
    }
    
    public void loadAgencyDelivTable(){
    
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date sysDate = new Date();
        String date = dateformat.format(sysDate);
        Statement stm=null;
        ResultSet rs=null;
        try {
            stm=conn.createStatement();
            String SQL="SELECT * FROM agencyDelivery WHERE date = '"+date+"'";
            rs=stm.executeQuery(SQL);
            
            agencyTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
//    private String getLastOrderID() throws SQLException{
//        String oid = null;
//        String did = txt_delID.getText();
//        String SQL = "SELECT orderID FROM \"Order\" WHERE delID = '"+did+"'";
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery(SQL);
//        
//        while(rs.next()){
//            System.out.println("getLastOrderID eke oid"+oid);
//            oid = rs.getString("delID");
//        }
//        
//        return oid;
//    }



    private void updateODelivTable(String OrderID){
    
        String did = txt_delID.getText();
        String SQL = "UPDATE OrderDelivery SET orderId = '"+OrderID+"' WHERE orderDelivId = '"+did+"'";
        Statement stm=null;
        try{
            stm = conn.createStatement();
            stm.executeUpdate(SQL);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Order_sub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    private void clearItemTable(){
    
        DefaultTableModel model = new DefaultTableModel();
        itemTable.setModel(model);
        model.setRowCount(0);
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTxt;
    private javax.swing.JTextField addrstxt;
    private javax.swing.JTable agencyTable;
    private javax.swing.JComboBox allDriverCombo;
    private javax.swing.JComboBox allVehiCombo;
    private javax.swing.JComboBox avaDriverCombo;
    private javax.swing.JComboBox avaVehiCombo;
    private javax.swing.JButton btn_addCus;
    private javax.swing.JButton btn_addEntry;
    protected javax.swing.JButton btn_assignCus;
    private javax.swing.JButton btn_calculate;
    private javax.swing.JButton btn_clr_rt;
    private javax.swing.JButton btn_confirmCash1;
    private javax.swing.JButton btn_confirmCheque;
    private javax.swing.JButton btn_giveLoan;
    private javax.swing.JButton btn_removeCus;
    private javax.swing.JButton btn_removeEntry;
    private javax.swing.JButton btn_return;
    private javax.swing.JButton btn_searchAndAssign;
    private javax.swing.JButton calbutton;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JCheckBox chkbx_addPastLoan;
    private javax.swing.JComboBox<String> cmbx_payType;
    private javax.swing.JComboBox<String> cmbx_type;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JButton confirmbtn;
    private com.toedter.calendar.JDateChooser delivDate;
    private javax.swing.JTextField delivIdTxt;
    private javax.swing.JTextField delivIdtxt;
    private javax.swing.JTable delivTable;
    private javax.swing.JTextField distanceTxt;
    private javax.swing.JComboBox driverCombo;
    private javax.swing.JButton driverUpdateBtn;
    private javax.swing.JCheckBox fragileBox;
    private javax.swing.JTextField idtxt;
    private javax.swing.JTable itemTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    protected javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_confirmCash;
    private javax.swing.JLabel lbl_confirmCheque;
    private javax.swing.JLabel lbl_currentTrans;
    private javax.swing.JLabel lbl_cusResult;
    private javax.swing.JLabel lbl_cusStatus;
    private javax.swing.JLabel lbl_orderID;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JTable orderDelTable;
    private javax.swing.JDialog orderDeliveryInt;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton postpnBtn;
    private javax.swing.JCheckBox rdConBox;
    private javax.swing.JTable supTable;
    private javax.swing.JTable tbl_ItemList;
    private javax.swing.JTable tbl_availableList;
    private javax.swing.JTable tbl_cusTransactions;
    private javax.swing.JTable tbl_history;
    private javax.swing.JTable tbl_invoice;
    private javax.swing.JTable tbl_itemBought;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JTextArea txtA_itemDes;
    private javax.swing.JTextField txtA_itemDes_rt;
    private javax.swing.JTextField txt_OrderID_history;
    private javax.swing.JTextField txt_amountCash1;
    private javax.swing.JTextField txt_amountCheque;
    private javax.swing.JTextField txt_amount_sd;
    private javax.swing.JTextField txt_balanceCash1;
    private javax.swing.JTextField txt_btQty_rt;
    private javax.swing.JTextField txt_cash1;
    private javax.swing.JTextField txt_chequeNo_sd;
    protected javax.swing.JTextField txt_cusID;
    private javax.swing.JTextField txt_cusID_history;
    private javax.swing.JTextField txt_cusID_rt;
    protected javax.swing.JTextField txt_cusID_sd;
    protected javax.swing.JTextField txt_cusName;
    private javax.swing.JTextField txt_cusName_rt;
    private javax.swing.JTextField txt_delID;
    private javax.swing.JTextField txt_invoiceID_rt;
    private javax.swing.JTextField txt_issuedDate_rt;
    private javax.swing.JTextField txt_itemID;
    private javax.swing.JTextField txt_netUnitPrice_rt;
    private javax.swing.JTextField txt_netValue;
    private javax.swing.JTextField txt_nowPaying;
    private javax.swing.JTextField txt_numCheque;
    private javax.swing.JTextField txt_other;
    private javax.swing.JTextField txt_pre;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_remaining;
    private javax.swing.JTextField txt_returnDes_rt;
    private javax.swing.JTextField txt_returnPrice_rt;
    private javax.swing.JTextField txt_returnQty_rt;
    private javax.swing.JTextField txt_totDebt;
    private javax.swing.JTextField txt_totDis;
    private javax.swing.JTextField txt_totToBePaid;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_unitDis;
    private javax.swing.JTextField txt_unitPrice;
    private javax.swing.JComboBox typeCombo;
    private javax.swing.JComboBox vehiCombo;
    private javax.swing.JButton vehiUpdateBtn;
    // End of variables declaration//GEN-END:variables
}
