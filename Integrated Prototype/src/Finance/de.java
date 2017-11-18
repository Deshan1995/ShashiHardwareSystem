/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finance;

/**
 *
 * @author Deshan
 */
import DBconnection.DBconnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
public class de extends javax.swing.JFrame {
Connection conn = null;
    ResultSet rs,rs1 = null;
    PreparedStatement ps,ps1 = null;
    
    String date = null;
    
    /**
     * Creates new form de
     */
    public de() {
        initComponents();
        conn = DBconnect.connectDb();
        
        calendar();
        
        Update_loandate();
        notification();
    }
int day=0,month=0,year=0,day1=0,month1=0,year1=0,day2=0,month2=0,year2=0,diff=0;
     
    public void calendar()
    { 
        Calendar cal=new GregorianCalendar();
        year= cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
       // day=cal.get(Calendar.DAY_OF_MONTH);
       day=4;
    
    }
   
    public void Update_loandate(){
        String loanid=null, sDate=null,eDate=null;       
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        SimpleDateFormat dm = new SimpleDateFormat("MM");
        SimpleDateFormat dd = new SimpleDateFormat("dd");

        java.util.Date d1 = null;
        java.util.Date d2 = null;

	try{

                String sql1="Select loanId,startdate,enddate from new1 ";
            
                ps=conn.prepareStatement(sql1);
                rs=ps.executeQuery();

                while(rs.next()){
                    loanid = rs.getString("loanId");
                    sDate = rs.getString("startdate");
                    eDate = rs.getString("enddate");
            
                    d1 = format.parse(sDate);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));

		    d2 = format.parse(eDate);
                    year2 = Integer.parseInt(dy.format(d2));
                    month2 = Integer.parseInt(dm.format(d2));
                    day2= Integer.parseInt(dd.format(d2));

//      		while((year1==year2)&&(month1==month2)&&(day1==day2)){
//ps.close();
			String val1 = year+"-"+month+"-"+day1;
                    
                        String sql = "Update new1 set loandate='"+val1+"' where loanId='"+loanid+"' ";
            
                           
                        ps=conn.prepareStatement(sql);
                        //Statement stm = conn.createStatement();
                        ps.execute();
                        //stm.execute(sql);

//		}
                }          
	}catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
                try{
                ps.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
    }
    
    public void notification(){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");

            java.util.Date d1 = null;

	try{
	    String sql="Select loandate from new1 ";
            String sql1="select loanid from new1";
            
            ps=conn.prepareStatement(sql);
            ps1=conn.prepareStatement(sql1);
            
            rs=ps.executeQuery();
            rs1=ps1.executeQuery();
               

		while(rs.next()){
  
                    date = rs.getString("loandate");
                    String id=rs1.getString("loanid");

                    d1 = format.parse(date);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));
      
                    //diff=day1-3;
                    
                    if((year==year1)&&(month==month1)&&(day<day1)) {
                        try{
                        String sql2="Insert into Notifyl(Loan_ID,Notification) values(?,?)";
           
                        ps=conn.prepareStatement(sql2);
            
                        ps.setString(1,"L1");
                        ps.setString(2,"You have to pay "+id+" Installment within "+(day1-day)+" days");

                            ps.execute();
            
            
            
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null,e);
                            }
                    } 
                    else {
                    }
	}
    }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
         finally{
                try{
                ps.close();
                rs.close();
                rs1.close();
                ps1.close();
                }
                catch(Exception e)
                {
                    
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_loanId = new javax.swing.JTextField();
        start_date = new com.toedter.calendar.JDateChooser();
        end_date = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Loan Id");

        jLabel2.setText("Start Date");

        jLabel3.setText("End Date");

        start_date.setDateFormatString("yyyy-MM-dd");

        end_date.setDateFormatString("yyyy-MM-dd");

        jButton1.setText("Insert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(start_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(end_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_loanId, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButton1)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_loanId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(start_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(end_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            String sql="Insert into new1(loanId,startdate,enddate) values(?,?,?)";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_loanId.getText());
            ps.setString(2,((JTextField)start_date.getDateEditor().getUiComponent()).getText());
            ps.setString(3,((JTextField)end_date.getDateEditor().getUiComponent()).getText());

            ps.execute();
            JOptionPane.showMessageDialog(null,"saved");
            Update_loandate();
  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
                try{
                ps.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(de.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(de.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(de.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(de.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new de().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser end_date;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.toedter.calendar.JDateChooser start_date;
    private javax.swing.JTextField txt_loanId;
    // End of variables declaration//GEN-END:variables
}
