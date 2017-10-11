/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;


import DBconnection.DBconnect;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL-PC
 */
public class Loginface extends javax.swing.JFrame {
    
    Connection  conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1=null;
    ResultSet rs = null,rs1=null;
    public int x;

    /**
     * Creates new form LoginFace
     */
    public Loginface() {
        super("Login");
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 4, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 4);
        initComponents();
        conn=DBconnect.connectDb();
        error.hide();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        error = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(675, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(140, 52, 180, 18);

        jPasswordField2.setBorder(null);
        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordField2);
        jPasswordField2.setBounds(140, 92, 180, 18);

        jButton3.setText("SignUp");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(50, 180, 80, 23);

        jButton2.setText("Forgot Password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(50, 140, 150, 23);

        error.setForeground(new java.awt.Color(255, 0, 0));
        error.setText("Trouble Login..!");
        getContentPane().add(error);
        error.setBounds(190, 120, 76, 14);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 255));
        jLabel3.setText("Login");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(250, 130, 40, 20);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hms/Untitled-1.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(290, 120, 40, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hms/Capture123.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 660, 223);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

        String str=jTextField1.getText();

        if(str.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Username cannot be empty");
        }
        else
        jPasswordField2.grabFocus();

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        String sql = "SELECT * FROM user WHERE username=? and pass=?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, jPasswordField2.getText());

            rs=pst.executeQuery();

            if(rs.next())
            {
                rs.close();
                pst.close();

                String sql1="SELECT * FROM user WHERE username = '"+jTextField1.getText()+"'";

                int type;
                pst1=conn.prepareStatement(sql1);
                rs1=pst1.executeQuery();
                if(rs1.next())
                {
                    type=Integer.parseInt(rs1.getString("type"));
                     
                    if(type==1 || type==2 || type==3)
                    {
                    setVisible(false);
                    Main.Main m1 = new Main.Main(type);
                    m1.setVisible(true);
                    }
                    else
                    {
                        Customer_and_Order.Order o = new Customer_and_Order.Order(type);
                        o.setVisible(true);
                        this.dispose();
                        
                    }

                }

            }
            else
            {
                error.setVisible(true);
                JOptionPane.showMessageDialog(null,"Username and Password is incorrect");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {

            }

        }
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        forgotPass f1 =new forgotPass();
        f1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
         String sql = "SELECT * FROM user WHERE username=? and pass=?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, jPasswordField2.getText());

            rs=pst.executeQuery();

            if(rs.next())
            {
                rs.close();
                pst.close();

                String sql1="SELECT * FROM user WHERE username = '"+jTextField1.getText()+"'";

                int type;
                pst1=conn.prepareStatement(sql1);
                rs1=pst1.executeQuery();
                if(rs1.next())
                {
                    type=Integer.parseInt(rs1.getString("type"));

                    setVisible(false);
                    
                    if(type==1||type==2||type==3)
                    {
                    Main.Main m1 = new Main.Main(type);
                    m1.setVisible(true);
                    }
                    else if(type==4)
                    {
                        Customer_and_Order.Order m1 = new Customer_and_Order.Order(type);
                        m1.setVisible(true);
                    }
                }

            }
            else
            {
                error.setVisible(true);
                JOptionPane.showMessageDialog(null,"Username and Password is incorrect");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {

            }

        }
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(Loginface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loginface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loginface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loginface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loginface().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel error;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
