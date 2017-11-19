/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import DBconnection.DBconnect;
import DBclass.me;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author DELL-PC
 */
public class addEmployee1 extends javax.swing.JInternalFrame {
    
       String s;
       Connection  conn = null;
       PreparedStatement pst = null,pst1=null;
       ResultSet rs = null,rs1=null;
       byte[] person_image = null;
       byte [] ss;
       String selectedb=null;
       int agee=0;
       

    /**
     * Creates new form addEmployee
     */
    public addEmployee1() {
        
        initComponents();
        
        conn=DBconnect.connectDb();
        auto();
        datevalid.setVisible(false);
        afname.setText("First Name");
        alname.setText("Surname");
        ButtonGroup group = new ButtonGroup();
        group.add(aft);
        group.add(apt);
        aft.setSelected(true);
        
        
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Calendar cal = Calendar.getInstance();
        
        adate.setSelectableDateRange(null,cal.getTime());
        

        
        
        tableload();
        showtime();

        
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
    
    public void auto(){

            ArrayList<String> list = new ArrayList<String>();
            
            try {
            String sql = "SELECT employee_id FROM Employee";
            
            pst=conn.prepareStatement(sql);
            
            rs1 = pst.executeQuery();
            while(rs1.next())
            {
                list.add(rs1.getString("employee_id"));
            }
            
            
            int max=0;
            
            for(int l=0;l<list.size();l++)
            {
                String ino1[]=list.get(l).split("E");
                int no2=Integer.parseInt(ino1[1]);
                if(max<no2)
                {
                    max=no2;
                }
            }
            
            
            
            
            
//           String a = list.get(list.size()-1);
//           String ino[]=a.split("E");
                
                int no;
                        //=Integer.parseInt(ino[1]);
                no=max+1;
//                System.out.println(a);
              aid.setText("E"+no);
           
            
//            System.out.println(a);
        } catch (Exception e) {
            
            System.out.println(e);
            
        }finally
            {
                try {
                    pst.close();
                    rs1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(addEmployee1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
    }
   public void clear()
   {
    aid.setText("");
        afname.setText("");
        alname.setText("");
        auname.setText("");
//        alang.setText("");
//        aage.setText("");
        anic.setText("");
        aadd.setText("");
        aphn.setText("");
        adate.setDate(null);
//        ((JTextField)adate.getDateEditor().getUiComponent()).setText("");
        apic.setText("");
        adeg.setSelectedIndex(0);
        jLabel2.setIcon(null);
        mcom.setSelectedItem(null);
        dcom.setSelectedItem(null);
        anicf.setSelectedItem(null);
        yyyy.setText("");
        auto();
   }
    public Boolean Validate(){

        String var_name1 = afname.getText();
        String var_name2 = alname.getText();
        String var_name3 = yyyy.getText();
        
		
        //validating for Name
        for(int i=0; i<var_name1.length(); i++){ 
            
            if((!Character.isAlphabetic(var_name1.charAt(i))) && (var_name1.charAt(i)!=' ')){
                JOptionPane.showMessageDialog(null,"Please Enter a valid First Name. Only the letters characters are allowed.","Invalid Name", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
	for(int i=0; i<var_name2.length(); i++){ 
            
            if((!Character.isAlphabetic(var_name2.charAt(i))) && (var_name2.charAt(i)!=' ')){
                JOptionPane.showMessageDialog(null,"Please Enter a valid Last Name. Only the letters characters are allowed.","Invalid Name", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        for(int i=0; i<var_name3.length(); i++){ 
            
            if((!Character.isDigit(var_name3.charAt(i)))){
                JOptionPane.showMessageDialog(null,"Please Enter a valid birth year. Only the numbers characters are allowed.","Invalid Name", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
}
    public void tableload()
    {
            conn=DBconnect.connectDb();
        String sql = "select * from Employee";
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
    
    
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        alname = new javax.swing.JTextField();
        aphn = new javax.swing.JTextField();
        afname = new javax.swing.JTextField();
        aid = new javax.swing.JTextField();
        apic = new javax.swing.JTextField();
        aage = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        aadd = new javax.swing.JTextArea();
        anic = new javax.swing.JTextField();
        adeg = new javax.swing.JComboBox<>();
        aft = new javax.swing.JRadioButton();
        apt = new javax.swing.JRadioButton();
        adate = new com.toedter.calendar.JDateChooser();
        auname = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        yyyy = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        datevalid = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        anicf = new javax.swing.JComboBox<>();
        alang = new javax.swing.JComboBox<>();
        mcom = new javax.swing.JComboBox<>();
        dcom = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1218, 685));
        getContentPane().setLayout(null);

        alname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        alname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alnameMouseClicked(evt);
            }
        });
        alname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                alnameKeyTyped(evt);
            }
        });
        getContentPane().add(alname);
        alname.setBounds(310, 120, 100, 30);

        aphn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        aphn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aphnMouseClicked(evt);
            }
        });
        aphn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aphnKeyTyped(evt);
            }
        });
        getContentPane().add(aphn);
        aphn.setBounds(170, 510, 240, 30);

        afname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        afname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                afnameMouseClicked(evt);
            }
        });
        afname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                afnameKeyTyped(evt);
            }
        });
        getContentPane().add(afname);
        afname.setBounds(170, 120, 100, 30);

        aid.setEditable(false);
        aid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        getContentPane().add(aid);
        aid.setBounds(170, 70, 100, 30);

        apic.setEditable(false);
        apic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        apic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apicMouseClicked(evt);
            }
        });
        getContentPane().add(apic);
        apic.setBounds(590, 120, 180, 30);

        aage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aageMouseClicked(evt);
            }
        });
        aage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aageActionPerformed(evt);
            }
        });
        getContentPane().add(aage);
        aage.setBounds(170, 330, 150, 30);

        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);

        aadd.setColumns(20);
        aadd.setRows(5);
        aadd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        aadd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aaddKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(aadd);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(170, 420, 240, 70);

        anic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        anic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anicMouseClicked(evt);
            }
        });
        anic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                anicKeyTyped(evt);
            }
        });
        getContentPane().add(anic);
        anic.setBounds(240, 370, 170, 30);

        adeg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Select Designation---", "Admin", "Cashier", "Driver", "Worker", "Stock Keeper", "Agency Manager" }));
        getContentPane().add(adeg);
        adeg.setBounds(170, 560, 240, 30);

        aft.setText("Full Time");
        aft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aftActionPerformed(evt);
            }
        });
        getContentPane().add(aft);
        aft.setBounds(180, 610, 90, 23);

        apt.setText("Part Time");
        getContentPane().add(apt);
        apt.setBounds(310, 610, 100, 23);

        adate.setBackground(new java.awt.Color(204, 204, 255));
        adate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        adate.setOpaque(false);
        adate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adateKeyTyped(evt);
            }
        });
        getContentPane().add(adate);
        adate.setBounds(590, 70, 240, 30);

        auname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        auname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aunameMouseClicked(evt);
            }
        });
        getContentPane().add(auname);
        auname.setBounds(170, 177, 240, 30);

        jButton1.setText("Browse");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(770, 120, 60, 30);

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
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(460, 380, 680, 200);

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(590, 180, 240, 180);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Full name          :-   ");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 120, 130, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Username         :-");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 170, 130, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Language         :-");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 220, 130, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Age                   :-");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 330, 130, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("NIC No             :-");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(30, 360, 130, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Address            :-");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 400, 120, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Phone no          :-");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(30, 510, 130, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Designation      :-");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(30, 560, 130, 30);

        yyyy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        yyyy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yyyyMouseClicked(evt);
            }
        });
        yyyy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yyyyActionPerformed(evt);
            }
        });
        yyyy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                yyyyKeyTyped(evt);
            }
        });
        getContentPane().add(yyyy);
        yyyy.setBounds(330, 290, 80, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Date of birth    :-");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(30, 290, 130, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Status               :-");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(30, 610, 130, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Employee ID    :-");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(30, 70, 130, 30);
        getContentPane().add(datevalid);
        datevalid.setBounds(850, 70, 50, 40);

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));
        jPanel9.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Employees");
        jPanel9.add(jLabel1);
        jLabel1.setBounds(10, 0, 243, 44);

        time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel9.add(time);
        time.setBounds(1025, 0, 130, 50);

        getContentPane().add(jPanel9);
        jPanel9.setBounds(0, 0, 1160, 50);

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("All fields are required*");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(290, 70, 140, 30);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Recruitment date:-");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(460, 70, 140, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Photograph        :-");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(460, 120, 130, 30);

        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(480, 600, 100, 40);

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(750, 600, 100, 40);

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(1010, 600, 100, 40);

        getContentPane().add(anicf);
        anicf.setBounds(170, 370, 70, 30);

        alang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sinhala", "Tamil" }));
        getContentPane().add(alang);
        alang.setBounds(170, 220, 130, 30);

        mcom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        getContentPane().add(mcom);
        mcom.setBounds(170, 290, 70, 30);

        dcom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(dcom);
        dcom.setBounds(250, 290, 70, 30);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("yyyy");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(360, 260, 30, 30);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("dd");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(270, 260, 30, 30);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("mm");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(190, 260, 30, 30);

        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(448, 60, 700, 590);

        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 60, 440, 590);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alnameMouseClicked
        alname.setText("");
    }//GEN-LAST:event_alnameMouseClicked

    private void aphnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aphnMouseClicked
        
    }//GEN-LAST:event_aphnMouseClicked

    private void afnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_afnameMouseClicked
        afname.setText("");
    }//GEN-LAST:event_afnameMouseClicked

    private void apicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apicMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_apicMouseClicked

    private void anicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anicMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_anicMouseClicked

    private void aftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aftActionPerformed

    private void aunameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aunameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_aunameMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         JFileChooser filechooser = new JFileChooser();
         filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","jpg","gif","png");
         filechooser.addChoosableFileFilter(filter);
         int result = filechooser.showSaveDialog(null);
         
         
         if(result == JFileChooser.APPROVE_OPTION)
         {
             File selectedfile = filechooser.getSelectedFile();
             String path = selectedfile.getAbsolutePath();
             jLabel2.setIcon(ResizeImage(path));
             
             apic.setText(path.toString());
             s=apic.getText();
         }
         else if(result == JFileChooser.CANCEL_OPTION)
         {
             System.out.println("no data");
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void aphnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aphnKeyTyped
         if(agee<14)
        {
            JOptionPane.showMessageDialog(null,"Age must be greater than 14 years");
            evt.consume();
        }
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Only numbers are allowed");
        }
        String check = aphn.getText()+c;
        
        if(check.length()>10)
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Phone number must contain only 10 digits");
        }
    }//GEN-LAST:event_aphnKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
     clear();   
        

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
//        System.out.println(adate.getDate().toString().equals(""));

//        DateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
//        Calendar cal = Calendar.getInstance();
//        String D1 = dateFormat.format(cal.getTime());
//        
//        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
//        SimpleDateFormat dm = new SimpleDateFormat("MM");
//        SimpleDateFormat dd = new SimpleDateFormat("dd");
//        
//        int year1 = Integer.parseInt(dy.format(D1));
//        int month1 = Integer.parseInt(dm.format(D1));
//        int day1= Integer.parseInt(dd.format(D1));
       if(!Validate())
       {
           return;
       }
        String dob1 = ""+mcom.getSelectedItem().toString()+" "+dcom.getSelectedItem().toString()+", "+yyyy.getText()+"";
        
        Date dobd = me.getStringDate(dob1);
        
        
         if(yyyy.getText().equals("")||aid.getText().equals("")||afname.getText().equals("")||alname.getText().equals("")||auname.getText().equals("")||alang.getSelectedItem().toString().equals("")|| anic.getText().equals("")|| aadd.getText().equals("")||aphn.getText().equals("")||apic.getText().toString().equals("")||adeg.getSelectedItem().toString().equals("---Select Designation---"))
            {
                
                JOptionPane.showMessageDialog(null,"All the fields are required");
            }
            else
            {
               

                
          try
            {
         if(adate.getDate()==null)
         {
         JOptionPane.showMessageDialog(null,"All the fields are required");
         }
         else
         {
             
        if(me.nicCheck(anicf.getSelectedItem().toString()+anic.getText()))
        {
                if(me.phnCheck(aphn.getText()))
                {
                    if(adate.getDate().after(new Date())){
            
                    JOptionPane.showMessageDialog(null,"Please Enter a valid date. You entered a future date!");
            
            
                }
                else{
                        
                if(dobd.compareTo(adate.getDate())>0){
            
                JOptionPane.showMessageDialog(null,"Please Enter a valid date. The entered recruitment date is earlier than your date of birth!","Invalid Dates", JOptionPane.INFORMATION_MESSAGE);
                            
            
                }
                else
                {
           
           
                //conn=DBconnect.connectDb();
                String sql = "INSERT INTO Employee(employee_id,first_name,last_name,username,language,birth_day,age,nic_no,address,phone_no,designation,status,addded_date,photograph) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                InputStream is = new FileInputStream(new File(s));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                byte[] buf = new byte[1024];

                for(int readNum;(readNum=is.read(buf))!= -1;)
                {

                    bos.write(buf,0,readNum);

                }
                person_image = bos.toByteArray();

                if(aft.isSelected())
                {
                    selectedb=aft.getText();
                }
                else if(apt.isSelected())
                {
                    selectedb=apt.getText();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Select a status");
                }

                pst = conn.prepareStatement(sql);
                pst.setString(1,aid.getText());
                pst.setString(2,afname.getText());
                pst.setString(3,alname.getText());
                pst.setString(4,auname.getText());
                pst.setString(5,alang.getSelectedItem().toString());
                pst.setString(6, ""+mcom.getSelectedItem().toString()+" "+dcom.getSelectedItem().toString()+", "+yyyy.getText()+"");
                pst.setString(7,aage.getSelectedItem().toString());
                pst.setString(8,anicf.getSelectedItem().toString()+anic.getText());
                pst.setString(9,aadd.getText());
                pst.setString(10,aphn.getText());
                pst.setString(11,adeg.getSelectedItem().toString());
                pst.setString(12,selectedb);
                pst.setString(13,((JTextField)adate.getDateEditor().getUiComponent()).getText());
                
                pst.setBytes(14, person_image);
                
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Employee added successfully");
                tableload();
                clear();
                }
                }
                }
                else
                {
                JOptionPane.showMessageDialog(null,"Invalid Phone Number");
                }
            
         }   else
        JOptionPane.showMessageDialog(null,"Invalid NIC");
         }
            
        }
            catch(org.sqlite.SQLiteException e)
            {
                if(e.toString().contains("[SQLITE_CONSTRAINT_UNIQUE]") ){                   
                    JOptionPane.showMessageDialog(null,"Sorry! This NIC is already in the system.","Registration Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Sorry! Something went wrong. Please contact the developing team.","Registration Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
            }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(addEmployee1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
         
        
           
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          
    }//GEN-LAST:event_jButton3ActionPerformed

    private void afnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afnameKeyTyped
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Numbers not allowed");
        }   
    }//GEN-LAST:event_afnameKeyTyped

    private void alnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alnameKeyTyped
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Numbers not allowed");
        }   
    }//GEN-LAST:event_alnameKeyTyped

    private void aageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aageActionPerformed
    
    }//GEN-LAST:event_aageActionPerformed

    private void aageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aageMouseClicked
        
        
    }//GEN-LAST:event_aageMouseClicked

    private void anicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anicKeyTyped
        char c = evt.getKeyChar();
        if(agee<18)
        {
            JOptionPane.showMessageDialog(null,"Age must be above 18 years");
            evt.consume();
        }
        String check = anic.getText()+c;
        
        if(check.length()>8)
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "you have already entered the maximum number of characters ");
        }
    }//GEN-LAST:event_anicKeyTyped

    private void aaddKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aaddKeyTyped
         if(agee<14)
        {
            JOptionPane.showMessageDialog(null,"Age must be greater than 14 years");
            evt.consume();
        }
    }//GEN-LAST:event_aaddKeyTyped

    private void adateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adateKeyTyped
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))
        {
        JOptionPane.showMessageDialog(null,"Please Select a date");
        evt.consume();
        }
    }//GEN-LAST:event_adateKeyTyped

    private void yyyyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yyyyKeyTyped
        aage.removeAllItems();
        anicf.removeAllItems();

        DateFormat dy = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        String year1 = dy.format(cal.getTime());

        DateFormat dm = new SimpleDateFormat("MM");
        Calendar calm = Calendar.getInstance();
        String month1 = dm.format(calm.getTime());

        String a = Character.toString(evt.getKeyChar());

        //        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        //        SimpleDateFormat dm = new SimpleDateFormat("MM");
        //        SimpleDateFormat dd = new SimpleDateFormat("dd");

        //        int year1 = Integer.parseInt(dy.format(D1));
        //        int month1 = Integer.parseInt(dm.format(D1));
        //        int day1= Integer.parseInt(dd.format(D1));

        int age = (Integer.parseInt(year1)-Integer.parseInt(yyyy.getText()+a));

        System.out.print(year1);

        aage.addItem(Integer.toString(age-1));
        aage.addItem(Integer.toString(age));
        aage.addItem(Integer.toString(age+1));

        if((yyyy.getText()+a).length()>=4)
        {
            anicf.addItem((yyyy.getText()+a).substring((year1.length())-2));
            anicf.addItem(yyyy.getText()+a);
        }
        agee = age;
    }//GEN-LAST:event_yyyyKeyTyped

    private void yyyyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yyyyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yyyyActionPerformed

    private void yyyyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yyyyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_yyyyMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea aadd;
    private javax.swing.JComboBox<String> aage;
    private com.toedter.calendar.JDateChooser adate;
    private javax.swing.JComboBox<String> adeg;
    private javax.swing.JTextField afname;
    private javax.swing.JRadioButton aft;
    private javax.swing.JTextField aid;
    private javax.swing.JComboBox<String> alang;
    private javax.swing.JTextField alname;
    private javax.swing.JTextField anic;
    private javax.swing.JComboBox<String> anicf;
    private javax.swing.JTextField aphn;
    private javax.swing.JTextField apic;
    private javax.swing.JRadioButton apt;
    private javax.swing.JTextField auname;
    private javax.swing.JLabel datevalid;
    private javax.swing.JComboBox<String> dcom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> mcom;
    private javax.swing.JLabel time;
    private javax.swing.JTextField yyyy;
    // End of variables declaration//GEN-END:variables
}
