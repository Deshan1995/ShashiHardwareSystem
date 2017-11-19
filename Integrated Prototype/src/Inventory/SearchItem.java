package Inventory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damith
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
//import DBClass.conect;
import DBconnection.DBconnect;
import javax.swing.table.TableModel;
import java.text.DateFormat;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;



public class SearchItem extends javax.swing.JInternalFrame {
     Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
  
    public SearchItem() {
        initComponents();
        conn = DBconnect.connectDb();
        
      //  tableload();
     
    }
    
    public void clearall()
    {
        SItemID.setText("");
    
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SItemID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableSearch = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(2147483647, 2147483647));
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true), "Search Item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Semibold", 0, 15), new java.awt.Color(0, 204, 204))); // NOI18N
        jPanel1.setLayout(null);

        SItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SItemIDActionPerformed(evt);
            }
        });
        SItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SItemIDKeyReleased(evt);
            }
        });
        jPanel1.add(SItemID);
        SItemID.setBounds(140, 40, 140, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Item ID");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 40, 100, 27);

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(370, 60, 63, 25);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 20, 470, 100);

        TableSearch.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TableSearch.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableSearch);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 130, 1617, 786);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SItemIDActionPerformed

    private void SItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SItemIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = SItemID.getText();
        String key=null;
        
        String itemName=null;
        String q=null;
        
        String category=null;
        
        try{
            if(itemID.length()>=3)
                key = itemID.substring(0, 3);
            else
                key = itemID;
            
            System.out.println(key);
            
            ps_category = conn.prepareStatement("select category from ItemCodes where keyword like '%"+key+"%'");  
          
            rs_category = ps_category.executeQuery();
            rs_category.next();
            category = rs_category.getString("category");
            System.out.println(category);
           
            
            if(category.equals("Paint&Thinner")){
                q = "SELECT * FROM Paint_Thinner WHERE ItemID LIKE '%"+itemID+"%'";
                
            }else if(category.equals("Construction")){
                q = "SELECT * FROM Construction WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Roofing&Fitting")){
                q = "SELECT * FROM Roofing_Fitting WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Waterpipe&Fittings")){
                q = "SELECT * FROM WaterPipe_Fitting WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Chemical&Farming")){
                q = "SELECT * FROM Chemical_Farming WHERE ItemID LIKE '%"+itemID+"%'";
            }else if(category.equals("Other")){
                q = "SELECT * FROM OtherItem WHERE ItemID LIKE '%"+itemID+"%'";
            }
           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableSearch.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
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
    }//GEN-LAST:event_SItemIDKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearall();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField SItemID;
    private javax.swing.JTable TableSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
