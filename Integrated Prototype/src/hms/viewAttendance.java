/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import DBconnection.DBconnect;
import DBclass.me;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DELL-PC
 */
public class viewAttendance extends javax.swing.JInternalFrame {
    String s,s1;
       Connection  conn = null;
       PreparedStatement pst = null,pst1=null,pst3=null,pst2=null;
       ResultSet rs = null,rs1 = null,rs3=null,rs2=null;
       byte[] person_image = null;
       String selectedb=null;

    /**
     * Creates new form updateEmployee
     */
    public viewAttendance() {
        conn=DBconnect.connectDb();
        initComponents();
        jLabel1.setVisible(false);
        jLabel5.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fromdate = new com.toedter.calendar.JDateChooser();
        todate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        id = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        workingdays = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1230, 685));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(null);

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

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(490, 80, 450, 210);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("View employee attendance");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(0, 0, 1170, 50);

        fromdate.setDateFormatString("MM dd, yyyy");
        jPanel2.add(fromdate);
        fromdate.setBounds(140, 90, 150, 30);

        todate.setDateFormatString("MM dd, yyyy");
        jPanel2.add(todate);
        todate.setBounds(330, 90, 150, 30);

        jButton1.setText("View attendance of all employees");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(140, 130, 220, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("EID");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(140, 210, 40, 30);

        jButton2.setText("View attendance of given employee");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(140, 250, 220, 40);
        jPanel2.add(id);
        id.setBounds(190, 210, 59, 30);

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
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(140, 350, 800, 230);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" to");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(300, 100, 20, 20);

        workingdays.setBackground(new java.awt.Color(153, 153, 255));
        workingdays.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        workingdays.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(workingdays);
        workingdays.setBounds(620, 300, 50, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("WORKING DAYS");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(680, 300, 180, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("OUT OF");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(560, 300, 60, 30);

        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));
        jPanel2.add(jLabel6);
        jLabel6.setBounds(70, 60, 990, 580);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1170, 650);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(fromdate.getDate()==null||todate.getDate()==null)
         {
         JOptionPane.showMessageDialog(null,"Dates are required");
         }
         else
         {
             SimpleDateFormat format1 = new SimpleDateFormat("MM dd, yyyy");
        
        Date dd1=null;
        Date dd2=null;
        
        try {
            
           dd1=format1.parse(format1.format(fromdate.getDate()));
            dd2=format1.parse(format1.format(todate.getDate()));
            
            long difff=dd2.getTime()-dd1.getTime();
            
            long hours=difff/(60*60*1000);
            long days=hours/24;
                    
            if(difff<0){
            
                JOptionPane.showMessageDialog(null,"Invalid Date selection");
                
          }
            else{
        String D1 =((JTextField)fromdate.getDateEditor().getUiComponent()).getText();
        String D2 = ((JTextField)todate.getDateEditor().getUiComponent()).getText();
        
        String sql = "SELECT EID, COUNT(EID) AS Attended_Days FROM Record WHERE status = 1 AND Date BETWEEN '"+D1+"' AND '"+D2+"'group by EID";
        String sqll = "SELECT EID,Date,status From Record where status = 0";
        try {
            pst1 = conn.prepareStatement(sql);
            pst2 = conn.prepareStatement(sqll);
            rs2=pst2.executeQuery();
            rs = pst1.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable2.setModel(DbUtils.resultSetToTableModel(rs2));  
        }catch (Exception e) {
            System.out.println(e);
        }finally{
            try {
                pst1.close();
                pst2.close();
                
            } catch (Exception e) {
            }
        }
        String sql1 = "SELECT DISTINCT count(EID) as days From Record where Date BETWEEN '"+D1+"' AND '"+D2+"'group by EID ";
        try {
            int wdays=0;
            pst = conn.prepareStatement(sql1);
            rs1 = pst.executeQuery();
            
            if(rs1.next())
            {
                wdays = rs1.getInt("days");
            }
           
            workingdays.setText(Integer.toString(wdays));
            jLabel1.setVisible(true);
            jLabel5.setVisible(true);
            
        }catch (Exception e) {
            System.out.println(e);
        }finally{
            try {
                pst.close();
                rs.close();
                rs2.close();
                
            } catch (Exception e) {
            }
        }
           
            
            }
                    
                
            }catch(Exception e)
            {
         }
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM dd, yyyy");
            
            String D1 =((JTextField)fromdate.getDateEditor().getUiComponent()).getText();
            String D2 = ((JTextField)todate.getDateEditor().getUiComponent()).getText();
            
            Date d1=format.parse(format.format(fromdate.getDate()));
            Date d2=format.parse(format.format(todate.getDate()));
            
            if(fromdate.getDate()==null||todate.getDate()==null||id.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Dates and ID  required");
            }
            else
            {
 
            
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        
        Date dd1=null;
        Date dd2=null;
        
        try {
            
           dd1=format.parse(format.format(fromdate.getDate()));
            dd2=format.parse(format.format(todate.getDate()));
            
            long difff=dd2.getTime()-dd1.getTime();
            
            //long hours=diff/(60*60*1000);
            //long days=hours/24;
                    
            if(difff<0){
            
                JOptionPane.showMessageDialog(null,"Expire date Should be Upcomming Manufacture Date");
                
          }
            else{
                    
                    
                    String sql = "SELECT EID, COUNT(EID) AS Attended_Days FROM Record WHERE status = 1 AND Date BETWEEN '"+D1+"' AND '"+D2+"' AND EID = '"+id.getText()+"' group by EID";
                    try {
                        pst1 = conn.prepareStatement(sql);
                        rs = pst1.executeQuery();
                        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    }catch (Exception e) {
                        System.out.println(e);
                    }finally{
                        try {
                            pst1.close();
                            
                        } catch (Exception e) {
                        }
                    }
                    String sql1 = "SELECT DISTINCT count(EID) as days From Record where Date BETWEEN '"+D1+"' AND '"+D2+"'group by EID ";
                    try {
                        int wdays=0;
                        pst = conn.prepareStatement(sql1);
                        rs1 = pst.executeQuery();
                        
                        if(rs1.next())
                        {
                            wdays = rs1.getInt("days");
                        }
                        
                        workingdays.setText(Integer.toString(wdays));
                        jLabel1.setVisible(true);
                        jLabel5.setVisible(true);
                        
                    }catch (Exception e) {
                        System.out.println(e);
                    }finally{
                        try {
                            pst.close();
                            rs.close();
                            
                        } catch (Exception e) {
                        }
                    }   
            
            }
                    
                
            }catch(Exception e)
            {
                
                
            }}
        } catch (ParseException ex) {
            Logger.getLogger(viewAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fromdate;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private com.toedter.calendar.JDateChooser todate;
    private javax.swing.JLabel workingdays;
    // End of variables declaration//GEN-END:variables
}
