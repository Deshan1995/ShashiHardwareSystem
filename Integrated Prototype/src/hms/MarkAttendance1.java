/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import DBconnection.DBconnect;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DELL-PC
 */
public class MarkAttendance1 extends javax.swing.JFrame {
String s;
       Connection  conn = null;
       PreparedStatement pst = null,pst1=null,pst2=null;
       ResultSet rs = null,rs1=null;
       byte[] person_image = null;
       byte [] ss;
       String selectedb=null;
    /**
     * Creates new form MarkAttendance1
     */
    public MarkAttendance1() {
        conn=DBconnect.connectDb();
        setExtendedState(MAXIMIZED_BOTH);

        initComponents();
        showtime();
        tableload();
        setDefaultCloseOperation(MarkAttendance1.HIDE_ON_CLOSE);
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Calendar cal = Calendar.getInstance();
        jLabel6.setText(dateFormat.format(cal.getTime()));
        
        
        
        
    }
    
    void showtime(){
        new Timer(0,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d=new Date();
                SimpleDateFormat s=new SimpleDateFormat("hh:mm:ss a");
                time.setText(s.format(d));
            }
        }).start();
    }
        public void tableload()
    {
                DateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        String D1 = dateFormat.format(cal.getTime());
        
        String sql = "select EID,status,On_time,Off_time from Record where Date = '"+D1+"'";
        try{
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try {
                pst.close();
                rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(updateEmployee.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        AEID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel11.setBackground(new java.awt.Color(153, 153, 153));
        jPanel11.setLayout(null);

        jPanel1.setLayout(null);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Mark Day End");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(530, 360, 310, 100);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Employee ID");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(340, 170, 190, 60);

        AEID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AEID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel1.add(AEID);
        AEID.setBounds(530, 170, 310, 60);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Mark Attendance");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(530, 240, 310, 100);

        jTable1.setBackground(new java.awt.Color(204, 255, 204));
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(850, 170, 360, 530);

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add List");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(850, 140, 90, 30);

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Please make sure that the pc's current date and time are correct");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(340, 120, 360, 14);

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(470, 380, 370, 180);

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        jPanel1.add(jLabel3);
        jLabel3.setBounds(310, 100, 1040, 640);

        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        jPanel1.add(jLabel5);
        jLabel5.setBounds(280, 60, 1110, 720);

        jPanel11.add(jPanel1);
        jPanel1.setBounds(0, 50, 2030, 830);

        time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel11.add(time);
        time.setBounds(1740, 0, 130, 50);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mark Attendance");
        jPanel11.add(jLabel4);
        jLabel4.setBounds(0, 0, 410, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 2006, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try {
        //        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //        Date date = new Date();
        //        System.out.println(dateFormat.format(date));
        ArrayList<String> list = new ArrayList<String>();
        
        DateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        String D1 = dateFormat.format(cal.getTime());
        
        Date d = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        String T1 = timeFormat.format(d);
        
        String eid = AEID.getText();
        try{
        String sql1 = "SELECT * FROM Record where Date = '"+D1+"' AND EID = '"+eid+"'";
        pst1 = conn.prepareStatement(sql1);
        rs1 = pst1.executeQuery();
        
        
        
        
        if(rs1.next())
        {
            
            if(rs1.getInt("on_Time")==0 && rs1.getInt("status")==0)
            {
            try{
            String addQuery = "update Record set On_time = '"+T1+"',status = 1 where EID='"+eid+"' AND Date = '"+D1+"'";
            pst2=conn.prepareStatement(addQuery);
            pst2.executeUpdate();
            tableload();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Already Marked");
            }
        
            
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Employee ID is required");
        }
        System.out.println(list.size());
        
    } catch (SQLException ex) {
        Logger.getLogger(MarkAttendance1.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally{
        try {
            pst1.close();
            rs1.close();
            pst2.close();
            
        } catch (Exception e) {
        }
    }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try{
        ArrayList<String> list = new ArrayList<String>();
        
        DateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        String D1 = dateFormat.format(cal.getTime());
        
        Date d = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        String T1 = timeFormat.format(d);
        
        String sql = "SELECT employee_id FROM Employee";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        
        while(rs.next())
        {
            list.add(rs.getString("employee_id"));
        }
        
        try{
        String sql1 = "SELECT * FROM Record where Date = '"+D1+"'";
        pst1 = conn.prepareStatement(sql1);
        rs1 = pst1.executeQuery();
        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        if(!rs1.next())
        {
            
        for(int i=0;i<list.size();i++)
        {
            try{
            String addQuery = "Insert into Record values ('"+list.get(i)+"','"+D1+"',0,0,0)";
            pst2=conn.prepareStatement(addQuery);
            pst2.executeUpdate();
            tableload();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Already added the list for'"+D1+"'");
        }
        System.out.println(list.size());
        
    } catch (SQLException ex) {
        Logger.getLogger(MarkAttendance1.class.getName()).log(Level.SEVERE, null, ex);
    }finally
        {
            try {
                pst.close();
                pst1.close();
                pst2.close();
               
                
            } catch (SQLException ex) {
                Logger.getLogger(updateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        
        AEID.setText(jTable1.getValueAt(row,0).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    try{
        DateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
        Calendar cal = Calendar.getInstance();
        String D1 = dateFormat.format(cal.getTime());
        
        Date d = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        String T1 = timeFormat.format(d);
        
        String eid = AEID.getText();
        try{
        String sql1 = "SELECT * FROM Record where Date = '"+D1+"' AND EID = '"+eid+"'";
        pst1 = conn.prepareStatement(sql1);
        rs1 = pst1.executeQuery();
        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        if(rs1.next())
        {
            if(rs1.getInt("Off_Time")==0 && rs1.getInt("status")==1)
            {
            if(rs1.getInt("Off_Time")==0)
            {
            try{
            String addQuery = "update Record set Off_time = '"+T1+"' where EID='"+eid+"' AND Date = '"+D1+"'";
            pst2=conn.prepareStatement(addQuery);
            pst2.executeUpdate();
            tableload();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Already Marked");
            }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Invalid Operation");
            }
        
            
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Employee ID is required");
        }
        } catch (SQLException ex) {
        Logger.getLogger(MarkAttendance1.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally{
        try {
            pst1.close();
            rs1.close();
            pst2.close();
            
        } catch (Exception e) {
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MarkAttendance1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarkAttendance1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarkAttendance1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarkAttendance1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarkAttendance1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AEID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
